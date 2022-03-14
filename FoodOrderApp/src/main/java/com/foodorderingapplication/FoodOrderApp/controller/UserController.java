package com.foodorderingapplication.FoodOrderApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapplication.FoodOrderApp.constant.ApiConstants;
import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.UserResponseDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ErrorResponse;
import com.foodorderingapplication.FoodOrderApp.exception.UserNotFoundException;
import com.foodorderingapplication.FoodOrderApp.exception.UserPasswordIncorrectException;
import com.foodorderingapplication.FoodOrderApp.exception.ValidationErrorResponse;
import com.foodorderingapplication.FoodOrderApp.service.UserService;


@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/Login")
	
	public ResponseEntity<UserResponseDTO> Login(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		UserResponseDTO userResponseDTO = userService.AuthenticateUser(userRequestDTO);
		
		return new ResponseEntity<UserResponseDTO>(userResponseDTO,HttpStatus.OK);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handlerException(UserNotFoundException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ApiConstants.USER_NOT_FOUND);
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(UserPasswordIncorrectException.class)
	public ResponseEntity<ErrorResponse> handlerPasswordException(UserPasswordIncorrectException ex){
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(),ApiConstants.INCORRECT_PASSWORD);
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
//	//Handler all validation exceptions
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<ValidationErrorResponse> handleExceptionVal(MethodArgumentNotValidException ex){
//		ValidationErrorResponse errorResponse = new ValidationErrorResponse("Invalid Arguments Passed",ApiContants.INVALID_ARGS);
//		
//		ex.getBindingResult().getFieldErrors().stream().forEach(error -> {
//			errorResponse.getInvalidArguments().put(error.getField(), error.getDefaultMessage());
//		});
//		
//		return new ResponseEntity<ValidationErrorResponse>(errorResponse,HttpStatus.OK);
//	}
}
