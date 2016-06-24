<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<dl id="cart" class="cart r">
  <dt><a href="#" title="结算">结算</a>购物车:<b id="cart_num"></b>件</dt>
  <dd class="hidden">
    <p class="alg_c hidden">购物车中还没有商品，赶紧选购吧！</p>
    <h3 title="最新加入的商品">最新加入的商品</h3>
    <ul class="uls">
      <%--<li>
        <a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
          <img src="/res/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
        <p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
        <p class="dd">
          <b><var>¥128</var><span>x1</span></b>
          <a href="javascript:void(0);" title="删除" class="del">删除</a>
        </p>
      </li>--%>
    </ul>
    <div>
      <p>共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b></p>
      <a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
    </div>
  </dd>
</dl>
<script type="text/javascript">
  $(function(){
    initCart();
  });

  function initCart(){
    var url = '/shopping/getCart.shtml';
    $.get(url,'',function(result){
      if (null == result || '' == result || null == result.items || 0 == result.items.length){
        $("#cart dd").html('<p class="alg_c">购物车中还没有商品，赶紧选购吧！</p>');
        $("#cart_num").html(0);
      }else{
        var html = '<h3 title="最新加入的商品">最新加入的商品</h3><ul class="uls">';
        var productAmount = 0;
        var amountPrice = 0;
        for (var i = 0; i < result.items.length; i++){
          var name = result.items[i].sku.product.name;
          var img = result.items[i].sku.product.imageUrl;
          var price = result.items[i].sku.skuPrice;
          var amount = result.items[i].amount;
          var skuId = result.items[i].sku.id;
          productAmount += amount;
          amountPrice += amount * price;
          html += '<li><a href="#" title="'+name+'">';
          html += '<img src="'+img+'" alt="'+name+'" /></a>';
          html += '<p class="dt"><a href="#" title="'+name+'">'+name+'</a></p>';
          html += '<p class="dd"><b><var>¥'+price+'</var><span>x'+amount+'</span></b><a href="javascript:deleteItem('+skuId+');" title="删除" class="del">删除</a></p></li>';
        }
        html += '</ul>';
        html += '<div><p>共<b>'+productAmount+'</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥'+amountPrice+'</b></p> <a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a></div>';
        $("#cart dd").html(html);
        $("#cart_num").html(productAmount);
      }
    },"json");
  }

  function deleteItem(skuId){
    if (confirm("你确定删除吗?")){
      var url = '/shopping/deleteItem.shtml';
      var params = {"skuId":skuId};
      $.get(url,params,function(result){
        alert(result.message);
        initCart();
      },"json");
    }
  }
</script>
