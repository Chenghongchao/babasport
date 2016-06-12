<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="bar">
    <div class="bar_w">
        <p class="l">
		<span class="l">
			收藏本网站！北京<a href="#" title="更换">[更换]</a>
		</span>
        </p>
        <ul class="r uls">
            <li class="dev">您好,欢迎来到新巴巴运动网！</li>
            <c:if test="${!isLogin}">
                <li class="dev"><a href="javascript:void(0)" onclick="login()"  title="登陆">[登陆]</a></li>
                <li class="dev"><a href="javascript:void(0)" title="免费注册">[免费注册]</a></li>
            </c:if>
            <c:if test="${isLogin}">
                <li class="dev"><a href="javascript:void(0)" onclick="logout()" title="退出">[退出]</a></li>
                <li class="dev"><a href="/buyer/index.shtml" title="我的订单">我的订单</a></li>
            </c:if>
            <li class="dev"><a href="#" title="在线客服">在线客服</a></li>
            <li class="dev after"><a href="#" title="English">English</a></li>
        </ul>
    </div>
</div>