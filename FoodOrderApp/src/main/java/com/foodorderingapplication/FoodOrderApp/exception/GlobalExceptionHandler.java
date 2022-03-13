package com.foodorderingapplication.FoodOrderApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.foodorderingapplication.FoodOrderApp.constant.ApiConstants;
import com.foodorderingapplication.FoodOrderApp.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(StoreNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(StoreNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ApiConstants.STORE_NOT_FOUND);
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}
}
