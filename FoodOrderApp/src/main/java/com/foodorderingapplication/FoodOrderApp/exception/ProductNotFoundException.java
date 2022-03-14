package com.foodorderingapplication.FoodOrderApp.exception;

import java.util.List;

import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OrderProduct> productNotFoundList;
	
	public ProductNotFoundException(String message,List<OrderProduct> productNotFoundList) {
		super(message);
		this.productNotFoundList = productNotFoundList;
	}

	public List<OrderProduct> getProductNotFoundList() {
		return productNotFoundList;
	}

	public void setProductNotFoundList(List<OrderProduct> productNotFoundList) {
		this.productNotFoundList = productNotFoundList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
