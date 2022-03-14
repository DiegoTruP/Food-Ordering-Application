package com.foodorderingapplication.FoodOrderApp.dto;

public class UserResponseDTO extends ResponseDTO{
	public UserResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}
	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}