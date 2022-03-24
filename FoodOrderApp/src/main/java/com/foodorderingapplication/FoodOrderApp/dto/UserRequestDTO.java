package com.foodorderingapplication.FoodOrderApp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequestDTO {
	

	private Integer userId;
	@NotEmpty(message = "User Name should not be emty")	
	private String userName;
	@NotEmpty(message = "Password should not be emty")
	@Size(min=8,message="Password must have more than 7 characters")
	private String password;
	
	public UserRequestDTO() {
		
	}
	
	public UserRequestDTO(Integer userId, @NotEmpty(message = "User Name should not be emty") String userName,
			@NotEmpty(message = "Password should not be emty") @Size(min = 8, message = "Password must have more than 7 characters") String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
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
