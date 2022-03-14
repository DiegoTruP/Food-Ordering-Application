package com.foodorderingapplication.FoodOrderApp.dto;


public class OrderDetailResponseDTO extends ResponseDTO{

	public OrderDetailResponseDTO(String message, Integer status) {
		super(message, status);
	}
	private String orderNumber;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

}