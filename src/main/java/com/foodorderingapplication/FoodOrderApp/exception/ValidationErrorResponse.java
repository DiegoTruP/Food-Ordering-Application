package com.foodorderingapplication.FoodOrderApp.exception;

import java.util.HashMap;
import java.util.Map;

import com.foodorderingapplication.FoodOrderApp.dto.ErrorResponse;

public class ValidationErrorResponse extends ErrorResponse{
	
	Map<String,String> invalidArguments = new HashMap<String, String>();

	public Map<String, String> getInvalidArguments() {
		return invalidArguments;
	}

	public void setInvalidArguments(Map<String, String> invalidArguments) {
		this.invalidArguments = invalidArguments;
	}

	public ValidationErrorResponse(String message, String statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}
}