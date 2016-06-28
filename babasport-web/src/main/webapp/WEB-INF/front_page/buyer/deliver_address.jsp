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
							<tr class="here">
								<td>${addr.consignee}</td>
								<td>${addr.provinceId} ${addr.cityId} ${addr.townId}</td>
								<td>${addr.address}</td>
								<td>${addr.mobile}</td>
								<td><a href="javascript:void(0);" title="修改" onclick="modify('1')" class="blue">[修改]</a><a href="javascript:void(0);" title="删除" onclick="del(this)" class="blue">[删除]</a></td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>

				<h3 class="h3_r">新增/修改收货地址<span>手机、固定电话选填一项，其余均为必填</span></h3>

				<form id="jvForm" method="post">
					<ul class="uls form">
					<li id="errorName" class="errorTip" style="display:none">${error}</li>
					<li>
						<label for="username"><samp>*</samp>收货人姓名：</label>
						<span class="bg_text"><input type="text" id="username" name="username" vld="{required:true}" maxLength="100" /></span>
						<span class="pos"><span class="tip okTip">&nbsp;</span></span>
					</li>
					<li>
						<label for="residence"><samp>*</samp>地　　址：</label>
						<span class="word">
						<select name="">
							<option value="" selected>省/直辖市</option>
							<option value=""></option>
						</select><select name="">
							<option value="" selected>城市</option>
							<option value=""></option>
						</select><select name="">
							<option value="" selected>县/区</option>
							<option value=""></option>
						</select></span>
					</li>
					<li>
						<label for="nick"><samp>*</samp>街道地址：</label>
						<span class="bg_text"><input type="text" id="nick" name="nick" maxLength="32"/></span>
						<span class="pos"><span class="tip errorTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>
					</li>
					<li>
						<label for="telphone"><samp>*</samp>联系电话：</label>
						<span class="bg_text"><input type="text" id="telphone" name="telphone" maxLength="32"/></span>
						<span class="pos"><span class="tip warningTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>
					</li>
					<li>
						<label for="statusAddr">&nbsp;</label>
						<span><input type="checkbox" name="statusAddr" />设为默认收货地址</span>
					</li>
					<li><label for="">&nbsp;</label><input type="submit" value="保存" class="hand btn66x23" /></li>
					</ul>
				</form>
			</div>
		</div>

	</div>
</div>

<%@ include file="../common/footer.jsp" %>
</body>
</html>
