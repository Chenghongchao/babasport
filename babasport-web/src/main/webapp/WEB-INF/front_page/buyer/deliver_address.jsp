<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收货地址_用户中心</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
</head>
<body>
<%@ include file="../common/bar.jsp" %>

<div class="w loc">
	<div class="h-title">
		<div class="h-logo"><a href="http://localhost:8080"><img src="/res/img/pic/logo-1.png" /></a></div>
	    <div class="h-search">
	      	<input type="text" />
	        <div class="h-se-btn"><a href="#">搜索</a></div>
	    </div>
	</div>
	<%@ include file="../common/cart.jsp" %>
</div>

<div class="w mt ofc">
	<%@ include file="left_menu.jsp" %>
	<div class="r wr profile">

		<div class="confirm">
			<div class="tl"></div><div class="tr"></div>
			<div class="ofc">

				<h2 class="h2 h2_r2"><em title="个人资料">收货地址</em></h2>
				<c:if test="${fn:length(addrs) > 0}">
					<h3 class="h3_r">已存收货地址列表</h3>

					<table cellspacing="0" summary="" class="tab tab6">
						<thead>
						<tr>
							<th>收货人</th>
							<th>所在地区</th>
							<th>街道地址</th>
							<th>电话/手机</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${addrs}" var="addr">
							<tr <c:if test="${addr.isDefault}">class="here"</c:if>>
								<td>${addr.consignee}</td>
								<td>${addr.provinceName} ${addr.cityName} ${addr.townName}</td>
								<td>${addr.address}</td>
								<td>${addr.mobile}</td>
								<td><a href="javascript:void(0);" title="修改" onclick="modify(${addr.id})" class="blue">[修改]</a><a href="javascript:void(0);" title="删除" onclick="del(this)" class="blue">[删除]</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>

				<h3 class="h3_r">新增/修改收货地址<span>手机、固定电话选填一项，其余均为必填</span></h3>

				<form id="jvForm" method="post">
					<input type="hidden" name="id" id="addrId" />
					<ul class="uls form">
					<li id="errorName" class="errorTip" style="display:none">${error}</li>
					<li>
						<label for="consignee"><samp>*</samp>收货人姓名：</label>
						<span class="bg_text"><input type="text" id="consignee" name="consignee" vld="{required:true}" maxLength="100" /></span>
						<%--<span class="pos"><span class="tip okTip">&nbsp;</span></span>--%>
					</li>
					<li>
						<label><samp>*</samp>地　　址：</label>
						<span class="word">
							<input type="hidden" name="provinceName" id="provinceName" />
						<select name="province" id="province">
							<option value="" selected>省/直辖市</option>
							<c:forEach items="${provinces}" var="province">
								<option value="${province.code}">${province.name}</option>
							</c:forEach>
						</select>
							<input type="hidden" name="cityName" id="cityName" />
						<select name="city" id="city">
							<option value="" selected>城市</option>
						</select>
							<input type="hidden" name="townName" id="townName" />
						<select name="town" id="town">
							<option value="" selected>县/区</option>
						</select></span>
					</li>
					<li>
						<label for="address"><samp>*</samp>街道地址：</label>
						<span class="bg_text"><input type="text" id="address" name="address" maxLength="32"/></span>
						<%--<span class="pos"><span class="tip errorTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>--%>
					</li>
					<li>
						<label for="mobile"><samp>*</samp>联系电话：</label>
						<span class="bg_text"><input type="text" id="mobile" name="mobile" maxLength="32"/></span>
						<%--<span class="pos"><span class="tip warningTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>--%>
					</li>
					<li>
						<label for="is_default">&nbsp;</label>
						<span><input id="is_default" type="checkbox" name="isDefault" value="1" />设为默认收货地址</span>
					</li>
					<li><label>&nbsp;</label><input type="button" id="save_btn" value="保存" class="hand btn66x23" /></li>
					</ul>
				</form>
			</div>
		</div>

	</div>
</div>

<%@ include file="../common/footer.jsp" %>
<script type="text/javascript">
	$(function(){
		$("#province").change(function(){
			var code = $(this).val();
			changeProvince(code);
		});
		$("#city").change(function(){
			var code = $(this).val();
			changeCity(code);
		});
		$("#save_btn").click(function(){
			save();
		});
	});

	function changeProvince(code, callback){
		var url = '/api/queryCity.do';
		var params = {"code":code};
		$.post(url, params,function(result){
//			console.log(result);
			var html = '<option value="" selected>城市</option>';
			if (null != result && result.length > 0){
				for (var i = 0; i < result.length; i++){
//				console.log(result[i]);
					html += '<option value="'+result[i].code+'">'+result[i].name+'</option>';
				}
			}
			$("#city").html(html);
			$("#town").html('<option value="" selected>县/区</option>');
			if (typeof callback === "function"){
				callback();
			}
		},"json");
	}

	function changeCity(code, callback){
		var url = '/api/queryTown.do';
		var params = {"code":code};
		$.post(url, params,function(result){
//			console.log(result);
			var html = '<option value="" selected>县/区</option>';
			if (null != result && result.length > 0){
				for (var i = 0; i < result.length; i++){
					//				console.log(result[i]);
					html += '<option value="'+result[i].code+'">'+result[i].name+'</option>';
				}
			}
			$("#town").html(html);
			if (typeof callback === "function"){
				callback();
			}
		},"json");
	}

	function save(){
		var provinceName = $("#province option:selected").html();
		var cityName = $("#city option:selected").html();
		var townName = $("#town option:selected").html();
		$("#provinceName").val(provinceName);
		$("#cityName").val(cityName);
		$("#townName").val(townName);
		var data = $("#jvForm").serialize();
		var url = "/buyer/saveAddr.shtml";
		$.post(url,data,function(result){
			alert(result.message);
			window.location.reload();
		},"json");
	}

	function modify(id){
		$("#jvForm")[0].reset();
		var url = '/buyer/getAddr.shtml';
		var params = {"id":id};
		$.post(url, params,function(result){
			console.log(result);
			var addr = result;
			$("#addrId").val(addr.id);
			$("#consignee").val(addr.consignee);
			$("#province").find('option[value="'+addr.province+'"]').attr("selected",true);
			var fn = function(){
				$("#city").find('option[value="'+addr.city+'"]').attr("selected",true);
			};
			changeProvince(addr.province, fn);
			var fn2 = function(){
				$("#town").find('option[value="'+addr.town+'"]').attr("selected",true);
			};
			changeCity(addr.city, fn2);

			$("#address").val(addr.address);
			$("#mobile").val(addr.mobile);
			if (addr.isDefault){
				$("#is_default").attr("checked",true);
			}else{
				$("#is_default").attr("checked",false);
			}
		},"json");
	}

</script>
</body>
</html>
