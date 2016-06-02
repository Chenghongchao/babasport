<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page pb15">
	<span class="r inb_a page_b">
		<font size="2">首页</font>
		<font size="2">上一页</font>
		<strong>1</strong>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=2">2</a>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=3">3</a>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=4">4</a>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=5">5</a>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=2"><font size="2">下一页</font></a>
		<a href="/product/list.do?&amp;isShow=0&amp;pageNo=5"><font size="2">尾页</font></a>
		共<var>5</var>页 到第<input type="text" size="3" id="PAGENO"/>页
		<input type="button" onclick="javascript:window.location.href = '/product/list.do?&amp;isShow=0&amp;pageNo=' + $('#PAGENO').val() " value="确定" class="hand btn60x20" id="skip"/>
	</span>
</div>