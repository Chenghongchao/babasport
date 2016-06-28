<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新巴巴运动网_用户中心</title>
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
				<h2 class="h2 h2_r2"><em title="个人资料">个人资料</em></h2>
				<form id="jvForm" method="post">
					<input type="hidden" name="returnUrl" value="${returnUrl}"/>
					<input type="hidden" name="processUrl" value="${processUrl}"/>
					<ul class="uls form">
					<li id="errorName" class="errorTip" style="display:none">${error}</li>
					<li>
						<label>用 户 名：</label>
						<span class="word">${buyer.userName}</span>
					</li>
					<li>
						<label>邮　　箱：</label>
						<span class="word">${buyer.email}</span>
					</li>
					<li>
						<label for="realName">真实姓名：</label>
						<span class="bg_text"><input type="text" id="realName" name="realName" maxLength="20" value="${buyer.realName}"/></span>
						<%--<span class="pos"><span class="tip okTip">&nbsp;</span></span>--%>
					</li>
					<li>
						<label>性　　别：</label>
						<span class="word"><input type="radio" name="gender" <c:if test="${buyer.gender == 2}">checked="checked"</c:if> value="2"/>保密<input type="radio" name="gender" <c:if test="${buyer.gender == 0}">checked="checked"</c:if> value="0"/>男<input type="radio" name="gender" <c:if test="${buyer.gender == 1}">checked="checked"</c:if> value="1"/>女</span>
					</li>
					<li>
						<label>居 住 地：</label>
						<span class="word">
							<select name="province"  id="province" onchange="changeProvince(this.value)">
								<option value="" selected>省/直辖市</option>
								<c:forEach items="${provinces}" var="province">
									<option <c:if test="${province.code == buyer.province}">selected="true"</c:if> value="${province.code}">${province.name}</option>
								</c:forEach>
							</select>
							<select name="city" id="city" onchange="changeCity(this.value)">
								<option value="" selected>城市</option>
								<c:forEach items="${cities}" var="city">
									<option <c:if test="${city.code == buyer.city}">selected="true"</c:if> value="${city.code}">${city.name}</option>
								</c:forEach>
							</select>
							<select name="town" id="town">
								<option value="" selected>县/区</option>
								<c:forEach items="${towns}" var="town">
									<option <c:if test="${town.code == buyer.town}">selected="true"</c:if> value="${town.code}">${town.name}</option>
								</c:forEach>
							</select>
						</span>
					</li>
					<li><label for="addr">详细地址：</label>
						<span class="bg_text"><input type="text" id="addr" name="addr" maxLength="32" value="${buyer.addr}"/></span>
						<%--<span class="pos"><span class="tip errorTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>--%>
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
		$("#save_btn").click(function(){
			save();
		});
	});

	function changeProvince(code){
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
		},"json");
	}

	function changeCity(code){
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
		},"json");
	}

	function save(){
		var data = $("#jvForm").serialize();
		var url = "/buyer/profile_update.shtml";
		$.post(url,data,function(result){
			alert(result.message);
		},"json");
	}
</script>
</body>
</html>