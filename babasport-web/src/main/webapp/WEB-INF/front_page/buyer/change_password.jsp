<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改密码_用户中心</title>
<link rel="stylesheet" href="/res/css/style.css" />
<script src="/res/js/jquery.js"></script>
<script src="/res/js/com.js"></script>
<script type="text/javascript">
$(function(){

	$("#loginAlertIs").click(function(){
		tipShow('#loginAlert');
	});

	$("#promptAlertIs").click(function(){
		tipShow('#promptAlert');
	});

	$("#transitAlertIs").click(function(){
		tipShow('#transitAlert');
	});

	$('.x_150x150').each(function(i, items_list){ 
		$(items_list).find('li').hover(function(){
			$(items_list).find('li').each(function(j, li){
				$(li).removeClass('here');
			});
			$(this).addClass('here');
		},function(){});
	});
	
});
</script>
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

				<form id="jvForm" action="profile.do" method="post">
					<input type="hidden" name="returnUrl" value="${returnUrl}"/>
					<input type="hidden" name="processUrl" value="${processUrl}"/>
					<ul class="uls form">
					<li id="errorName" class="errorTip" style="display:none">${error}</li>
					<li>
						<label for="nick">当前密码：</label>
						<span class="bg_text"><input type="password" id="password" name="password" maxLength="32" vld="{required:true}" /></span>
						<span class="pos"><span class="tip errorTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>
					</li>
					<li>
						<label for="realPassword">新 密 码：</label>
						<span class="bg_text"><input type="text" id="realPassword" name="realPassword" maxLength="32" vld="{required:true}" /></span>
						<span class="pos"><span class="tip okTip">&nbsp;</span></span>
					</li>
					<li>
						<label for="confirmPassword">确认新密码：</label>
						<span class="bg_text"><input type="text" id="confirmPassword" name="confirmPassword" maxLength="32" vld="{required:true}" /></span>
						<span class="pos"><span class="tip warningTip">用户名为4-20位字母、数字或中文组成，字母区分大小写。</span></span>
					</li>
					<li><label for="">&nbsp;</label><input type="submit" value="确认" class="hand btn66x23" /></li>
					</ul>
				</form>

			</div>
			<div class="fl"></div><div class="fr"></div>
		</div>

	</div>
</div>

<%@ include file="../common/footer.jsp" %>
</body>
</html>
