package com.foodorderingapplication.FoodOrderApp.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductResponseDTO extends ResponseDTO {

	private List<ProductDetails> productList = new ArrayList<ProductDetails>();
	
	public ProductResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}

	public List<ProductDetails> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDetails> productList) {
		this.productList = productList;
	}
}
