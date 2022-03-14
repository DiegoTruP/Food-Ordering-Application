package com.foodorderingapplication.FoodOrderApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequestDTO {
	
	@NotEmpty(message = "User Name should not be emty")	
	private String userName;
	@NotEmpty(message = "Password should not be emty")
	@Size(min=8,message="Password must have more than 7 characters")
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
