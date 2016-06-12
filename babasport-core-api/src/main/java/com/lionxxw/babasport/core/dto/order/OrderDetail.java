package com.lionxxw.babasport.core.dto.order;

import java.io.Serializable;

/**
 * <p>Description: 订单 -- 详情 </p>
 *
 * @author wangxiang
 * @date 16/6/12 上午9:35
 * @version 1.0
 */
public class OrderDetail implements Serializable {

	private Integer id;
	private Integer orderId;
	private String productNo;
	private String productName;
	private String color;
	private String size;
	private Double skuPrice;
	private Integer amount;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getSkuPrice() {
		return skuPrice;
	}
	public void setSkuPrice(Double skuPrice) {
		this.skuPrice = skuPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String toString() {
		return "Detail [id=" + id + ",orderId=" + orderId + ",productNo=" + productNo + ",productName=" + productName + ",color=" + color + ",size=" + size + ",skuPrice=" + skuPrice + ",amount=" + amount + "]";
	}
}
