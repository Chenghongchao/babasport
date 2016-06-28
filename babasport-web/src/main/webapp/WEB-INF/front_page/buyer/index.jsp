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
		<h2 class="h2 h2_filter"><em style="color:red">全部订单</em></h2>
		<table cellspacing="0" summary="" class="tab tab4">
		<thead>
		<tr>
		<th width="12%">订单编号</th>
		<th>商品名称</th>
		<th width="10%">收货人</th>
		<th width="10%">总金额（元）</th>
		<th width="10%">下单时间</th>
		<th width="10%">订单状态</th>
		<th width="10%">操作</th>
		</tr>     
		</thead>
		<tbody>        
			<tr class="over">
				<td><a href="#" title="H7859454">H333333</a></td>
				<td class="nwp pic">
					<ul class="uls">
						<li>
							<a href="#" title="依琦莲2014瑜伽服套装新款" class="pic"><img src="../../res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新款" /></a>
							<dl>
								<dt><a href="#" title="依琦莲2014瑜伽服套装新款">依琦莲2014瑜伽服套装新款</a></dt>
							</dl>
						</li>
					</ul>
				</td>
				<td>我自己</td>
				<td>￥128.00</td>
				<td>2014-10-19<br />12:09:45</td>
				<td>已经完成<br /></td>
				<td class="blue"><a href="javascript:void(0);" title="查看详情">查看详情</a><br /><a href="javascript:void(0);" title="跟踪物流">跟踪物流</a></td>
			</tr>
			<tr class="over">
				<td><a href="#" title="H7859454">H333333</a></td>
				<td class="nwp pic">
					<ul class="uls">
						<li>
							<a href="#" title="依琦莲2014瑜伽服套装新款" class="pic"><img src="../../res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新款" /></a>
							<dl>
								<dt><a href="#" title="依琦莲2014瑜伽服套装新款">依琦莲2014瑜伽服套装新款</a></dt>
							</dl>
						</li>
					</ul>
				</td>
				<td>我自己</td>
				<td>￥128.00</td>
				<td>2014-10-19<br />12:09:45</td>
				<td>已经完成<br /></td>
				<td class="blue"><a href="javascript:void(0);" title="查看详情">查看详情</a><br /><a href="javascript:void(0);" title="跟踪物流">跟踪物流</a></td>
			</tr>
			<tr class="over">
				<td><a href="#" title="H7859454">H333333</a></td>
				<td class="nwp pic">
					<ul class="uls">
						<li>
							<a href="#" title="依琦莲2014瑜伽服套装新款" class="pic"><img src="../../res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新款" /></a>
							<dl>
								<dt><a href="#" title="依琦莲2014瑜伽服套装新款">依琦莲2014瑜伽服套装新款</a></dt>
							</dl>
						</li>
					</ul>
				</td>
				<td>我自己</td>
				<td>￥128.00</td>
				<td>2014-10-19<br />12:09:45</td>
				<td>已经完成<br /></td>
				<td class="blue"><a href="javascript:void(0);" title="查看详情">查看详情</a><br /><a href="javascript:void(0);" title="跟踪物流">跟踪物流</a></td>
			</tr>
			<tr class="over">
				<td><a href="#" title="H7859454">H333333</a></td>
				<td class="nwp pic">
					<ul class="uls">
						<li>
							<a href="#" title="依琦莲2014瑜伽服套装新款" class="pic"><img src="../../res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新款" /></a>
							<dl>
								<dt><a href="#" title="依琦莲2014瑜伽服套装新款">依琦莲2014瑜伽服套装新款</a></dt>
							</dl>
						</li>
					</ul>
				</td>
				<td>我自己</td>
				<td>￥128.00</td>
				<td>2014-10-19<br />12:09:45</td>
				<td>已经完成<br /></td>
				<td class="blue"><a href="javascript:void(0);" title="查看详情">查看详情</a><br /><a href="javascript:void(0);" title="跟踪物流">跟踪物流</a></td>
			</tr>
		</tbody>
		</table>
	</div>
</div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>