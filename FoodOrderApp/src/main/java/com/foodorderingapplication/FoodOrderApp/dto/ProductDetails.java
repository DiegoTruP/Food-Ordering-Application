package com.foodorderingapplication.FoodOrderApp.dto;

import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;

public class ProductDetails {

	private Integer productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private boolean isAvailable;
	private ProductCategory productCategory;
	
	public ProductDetails() {}
	
	public ProductDetails(Integer productId, String productName, String productDescription, double productPrice,
			boolean isAvailable, ProductCategory productCategory) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.isAvailable = isAvailable;
		this.productCategory = productCategory;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public ProductCategory getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	
}
