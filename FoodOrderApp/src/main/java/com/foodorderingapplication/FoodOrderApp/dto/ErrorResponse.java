package com.foodorderingapplication.FoodOrderApp.dto;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	private String message;
	private String statusCode;
	private LocalDateTime date;
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public ErrorResponse(String message, String statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
