package com.lionxxw.babasport.cart.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * <p>Description: 购物车 </p>
 * 面向对象,设置购物车
 * 1.购物车对象
 * 2.用户可以不登陆
 * 3.关闭浏览器,再打开的时候还是用之前添加的商品
 *
 * 存放地点
 * 1.cookie(方案选择)
 * 	关闭浏览器依然存在
 * 2.session
 * 	关闭浏览器依然存在
 * 	浪费性能
 * 3.数据库
 * 	浪费性能
 *
 * 	面向对象的定义:
 * 		谁拥有数据,谁就拥有对该数据操作的权限
 *
 * @author wangxiang
 * @date 16/6/20 下午4:51
 * @version 1.0
 */
public class BuyCart {

	//购物项  集合
	
	List<BuyItem> items = new ArrayList<BuyItem>();
	//继续购物   最后一款
	private Integer productId;
	
	//添加方法
	public void addItem(BuyItem item){
		//判断是否重复
		if(items.contains(item)){
			for(BuyItem it : items){
				if(it.equals(item)){
					int result = it.getAmount() + item.getAmount();
					if(it.getSku().getStockUpperLimit() >= result){
						it.setAmount(result);
					}else{
						it.setAmount(it.getSku().getStockUpperLimit());
					}
				}
			}
		}else{
			if (item.getAmount() > 0){
				items.add(item);
			}
		}
	}
	//删除一个
	public void deleteItem(BuyItem item){
		items.remove(item);
	}
	//小计
	//商品数量
	@JsonIgnore
	public int getProductAmount(){
		int result = 0;
		for(BuyItem item : items){
			result += item.getAmount();
		}
		return result;
	}
	//商品金额
	@JsonIgnore
	public Double getProductPrice(){
		Double result = 0.00;
		for(BuyItem item : items){
			result += item.getSku().getSkuPrice()*item.getAmount();
		}
		return result;
	}
	//运费
	@JsonIgnore
	public Double getFee(){
		Double result = 0.00;
		if(getProductPrice() <= 39){
				result = 10.00;
		}
		return result;
	}
	
	//清空购物车
	public void clearCart(){
		items.clear();
	}
	//应付金额
	@JsonIgnore
	public Double getTotalPrice(){
		return getFee() + getProductPrice();
	}

	public List<BuyItem> getItems() {
		return items;
	}

	public void setItems(List<BuyItem> items) {
		this.items = items;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
}
