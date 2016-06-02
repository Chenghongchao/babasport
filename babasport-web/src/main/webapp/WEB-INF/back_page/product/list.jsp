<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/back_page/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-list</title>
<script type="text/javascript">
function getTableForm() {
	return document.getElementById('tableForm');
}
function optDelete() {
	if(Pn.checkedCount('ids')<=0) {
		alert("请至少选择一个!");
		return;
	}
	if(!confirm("确定删除吗?")) {
		return;
	}
	var f = getTableForm();
	f.action="o_delete.do";
	f.submit();
}
function changePageNo(){
	$("input[name='currPage']").val(1);
}
</script>
</head>
<body>
<div class="box-positon">
	<div class="rpos">当前位置: 商品管理 - 列表</div>
	<form class="ropt">
		<input class="add" type="button" value="添加" onclick="javascript:window.location.href='toAdd.do'"/>
	</form>
	<div class="clear"></div>
</div>
<div class="body-box">
<form action="/back/product/list.do" method="post" style="padding-top:5px;">
<input type="hidden" value="1" name="pageNo"/>
名称: <input type="text" onkeyup="changePageNo()" value="" name="name"/>
	<select onchange="changePageNo()" name="brandId">
		<option value="">请选择品牌</option>
		<c:forEach items="${brands}" var="brand">
			<option <c:if test="${params.brandId == brand.id}">selected="true"</c:if> value="${brand.id}">${brand.name}</option>
		</c:forEach>
	</select>
	<select onchange="changePageNo()" name="isShow">
		<option value="">全部</option>
		<option <c:if test="${null != params.isShow && params.isShow}">selected="true"</c:if>  value="true">上架</option>
		<option <c:if test="${null != params.isShow && !params.isShow}">selected="true"</c:if>  value="false">下架</option>
	</select>
	<input type="submit" class="query" value="查询"/>
</form>
<form method="post" id="tableForm">
<input type="hidden" value="1" name="currPage"/>
<table cellspacing="1" cellpadding="0" width="100%" border="0" class="pn-ltable">
	<thead class="pn-lthead">
		<tr>
			<th width="20"><input type="checkbox" onclick="Pn.checkbox('ids',this.checked)"/></th>
			<th>商品编号</th>
			<th>商品名称</th>
			<th>图片</th>
			<th width="4%">新品</th>
			<th width="4%">热卖</th>
			<th width="4%">推荐</th>
			<th width="4%">上下架</th>
			<th width="12%">操作选项</th>
		</tr>
	</thead>
	<tbody class="pn-ltbody">
		<c:forEach items="${products.results}" var="product">
			<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'" onmouseout="this.bgColor='#ffffff'">
				<td><input type="checkbox" name="ids" value="73"/></td>
				<td>${product.no}</td>
				<td align="center">${product.name}</td>
				<td align="center"><img width="50" height="50" src="/res/img/pic/ppp0.jpg"/></td>
				<td align="center"><c:if test="${product.isNew}">是</c:if><c:if test="${!product.isNew}">否</c:if></td>
				<td align="center"><c:if test="${product.isHot}">是</c:if><c:if test="${!product.isHot}">否</c:if></td>
				<td align="center"><c:if test="${product.isCommend}">是</c:if><c:if test="${!product.isCommend}">否</c:if></td>
				<td align="center"><c:if test="${product.isShow}">上架</c:if><c:if test="${!product.isShow}">下架</c:if></td>
				<td align="center">
					<a href="#" class="pn-opt">查看</a> | <a href="#" class="pn-opt">修改</a> | <a href="#" onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a> | <a href="../sku/list.jsp" class="pn-opt">库存</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<%@ include file="../common/page.jsp" %>
<div style="margin-top:15px;"><input class="del-button" type="button" value="删除" onclick="optDelete();"/><input class="add" type="button" value="上架" onclick="optDelete();"/><input class="del-button" type="button" value="下架" onclick="optDelete();"/></div>
</form>
</div>
</body>
</html>