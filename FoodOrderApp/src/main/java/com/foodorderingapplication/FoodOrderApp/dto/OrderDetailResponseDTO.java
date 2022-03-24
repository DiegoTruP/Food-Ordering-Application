package com.foodorderingapplication.FoodOrderApp.dto;


public class OrderDetailResponseDTO{

	
	private String message;
	private Integer statusCode;
	private String orderNumber;
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer status) {
		this.statusCode = status;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public OrderDetailResponseDTO(String message, Integer status, String orderNumber) {
		super();
		this.message = message;
		this.statusCode = status;
		this.orderNumber = orderNumber;
	}

	

	
	

}