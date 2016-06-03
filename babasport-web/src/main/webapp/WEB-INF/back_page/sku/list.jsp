<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>babasport-list</title>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 库存管理 - 列表</div>
	<div class="clear"></div>
</div>
<div class="body-box">
<form method="post" id="tableForm">
<table cellspacing="1" cellpadding="0" border="0" width="100%" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品颜色</th>
			<th>商品尺码</th>
			<th>市场价格</th>
			<th>销售价格</th>
			<th>库       存</th>
			<th>购买限制</th>
			<th>运       费</th>
			<th>是否赠品</th>
			<th>操       作</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${skus}" var="sku">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'" >
				<td><input type="checkbox" name="ids" value="${sku.id}"/></td>
				<td>${productNo}</td>
				<td align="center">${sku.colorName}</td>
				<td align="center">${sku.sizeName}</td>
				<td align="center"><input type="text" name="marketPrice" value="${sku.marketPrice}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" name="skuPrice" value="${sku.skuPrice}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" name="stockInventory" value="${sku.stockInventory}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" name="stockUpperLimit" value="${sku.stockUpperLimit}" disabled="disabled" size="10"/></td>
				<td align="center"><input type="text" name="deliveFee" value="${sku.deliveFee}" disabled="disabled" size="10"/></td>
				<td align="center"><c:if test="${sku.skuType == 1}" >不是</c:if><c:if test="${sku.skuType == 0}" >是</c:if></td>
				<td id="tr_sku" align="center"><a class="pn-opt" id="edit_sku">修改</a> | <a data_id="${sku.id}" class="pn-opt" id="save_sku">保存</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
</div>
<script type="text/javascript">
	var price_reg=/^[-\+]?\d+(\.\d+)?$/; // 金额正则
	var num_reg=/^\d*$/; // 整数正则
	$(function() {
		$("#tr_sku #edit_sku").click(function(){
			var _tr = $(this).parent().parent();
			_tr.find("input").each(function(){
				if ($(this).attr("disabled")){
					$(this).attr("disabled",false);
				}
			});
		});

		$("#tr_sku #save_sku").click(function(){
			var _tr = $(this).parent().parent();
			if (!checkSaveSku(_tr)){
				return;
			}
			var id = $(this).attr("data_id");
			_tr.find("input").each(function(){
				if (!$(this).attr("disabled")){
					$(this).attr("disabled",true);
				}
			});

			saveSku(id, _tr);
		});
	});

	/**
	 * ajax异步保存数据商品库存信息
	 * @param id
	 * @param node
	 */
	function saveSku(id, node){
		var marketPrice = node.find("input[name='marketPrice']").val();
		var skuPrice = node.find("input[name='skuPrice']").val();
		var stockInventory = node.find("input[name='stockInventory']").val();
		var stockUpperLimit = node.find("input[name='stockUpperLimit']").val();
		var deliveFee = node.find("input[name='deliveFee']").val();
		$.ajax({
			type: "POST",
			url: "/back/sku/update.do",
			data: {id:id,marketPrice:marketPrice,skuPrice:skuPrice,stockInventory:stockInventory,stockUpperLimit:stockUpperLimit,deliveFee:deliveFee},
			dataType: "json",
			success: function(data){
				var jsondata = null;//eval('(' + data + ')');
				if (data instanceof Object){
					jsondata = data;
				}else{
					jsondata = eval('(' + data + ')');
				}
				if(jsondata.status == 200){
					alert(jsondata.message);
				}else{
					alert(jsondata.message);
				}
			}
		});
	}

	/**
	 * 校验库存保存信息是否存在不合法
	 * @param node
	 */
	function checkSaveSku(node){
		var mp = node.find("input[name='marketPrice']");
		var sp = node.find("input[name='skuPrice']");
		var inv = node.find("input[name='stockInventory']");
		var limit = node.find("input[name='stockUpperLimit']");
		var fee = node.find("input[name='deliveFee']");
		if (!checkPrice(mp,"市场价格不能为空!","市场价格格式有误!")){
			return false;
		}
		if (!checkPrice(sp,"销售价格不能为空!","销售价格格式有误!")){
			return false;
		}
		if (!checkNum(inv,"库存不能为空!","库存格式有误!")){
			return false;
		}
		if (!checkNum(limit,"购买限制不能为空!","购买限制格式有误!")){
			return false;
		}
		if (!checkPrice(fee,"运费不能为空!","运费格式有误!")){
			return false;
		}

		return true;
	}

	/**
	* 金额校验器
	* @param n 节点
	* @param msg 非空提示
	* @param error 格式错误提醒
	* @returns {boolean}
	 */
	function checkPrice(n,msg,error){
		return chekcReg(n, msg, error, price_reg);
	}

	/**
	* 整数正则判断
	* @param n 节点
	* @param msg 非空提示
	* @param error 格式错误提示
	* @returns {boolean}
	 */
	function checkNum(n,msg,error){
		return chekcReg(n, msg, error, num_reg);
	}

	function chekcReg(n, msg, error, reg){
		var val = n.val();
		// 非空判断
		if (val == ''){
			n.focus();
			alert(msg);
			return false
		}
		// 正则表达式判断
		if(!reg.test(val)){
			n.focus();
			alert(error);
			return false;
		}
		return true;
	}
</script>
</body>
</html>