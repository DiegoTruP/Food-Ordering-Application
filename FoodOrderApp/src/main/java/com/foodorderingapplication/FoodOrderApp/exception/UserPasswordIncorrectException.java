package com.foodorderingapplication.FoodOrderApp.exception;


public class UserPasswordIncorrectException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserPasswordIncorrectException(String message) {
		super(message);
		
	}
}
