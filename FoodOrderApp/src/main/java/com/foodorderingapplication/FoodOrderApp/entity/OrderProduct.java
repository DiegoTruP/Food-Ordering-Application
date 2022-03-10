package com.foodorderingapplication.FoodOrderApp.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderProduct {

	private Integer productId;
	private double quantity;
	private double productPrice;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}	
}
