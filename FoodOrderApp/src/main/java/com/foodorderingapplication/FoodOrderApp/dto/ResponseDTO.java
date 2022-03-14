package com.foodorderingapplication.FoodOrderApp.dto;

public class ResponseDTO {
	
	private String message;
	private int statusCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public ResponseDTO(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
}
