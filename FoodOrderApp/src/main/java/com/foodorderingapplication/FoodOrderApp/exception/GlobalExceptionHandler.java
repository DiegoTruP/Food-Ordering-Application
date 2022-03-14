package com.foodorderingapplication.FoodOrderApp.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	
	@ExceptionHandler(MismatchingPriceException.class)
	public ResponseEntity<ErrorResponse> handlerPriceException(MismatchingPriceException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ApiConstants.PRICE_NOT_MATCH);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handlerValidationException(MethodArgumentNotValidException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ApiConstants.INVALID_ARGS);
		ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
			validationErrorResponse.getInvalidArguments().put(error.getField(),error.getDefaultMessage());	
		});
		
		validationErrorResponse.setDate(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(validationErrorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handlerConstraintValidationException(ConstraintViolationException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ApiConstants.INVALID_ARGS);
		ex.getConstraintViolations().stream().forEach(error -> {
			validationErrorResponse.getInvalidArguments().put(error.getPropertyPath().toString(),error.getMessage());	
		});
		
		validationErrorResponse.setDate(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(validationErrorResponse,HttpStatus.OK);
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handlerValidationException(IllegalArgumentException ex){
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ApiConstants.INVALID_ARGS);
		
		validationErrorResponse.getInvalidArguments().put("Enum", ex.getMessage());
		
		validationErrorResponse.setDate(LocalDateTime.now());
		
		return new ResponseEntity<ErrorResponse>(validationErrorResponse,HttpStatus.OK);
	}
	
	
	
	
	
}
