package com.foodorderingapplication.FoodOrderApp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.UserResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.User;
import com.foodorderingapplication.FoodOrderApp.exception.UserNotFoundException;
import com.foodorderingapplication.FoodOrderApp.exception.UserPasswordIncorrectException;
import com.foodorderingapplication.FoodOrderApp.repo.UserRepo;
import com.foodorderingapplication.FoodOrderApp.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserResponseDTO AuthenticateUser(UserRequestDTO userRequestDTO) {
		//List<User> user = userRepo.findAll();
		UserResponseDTO userResponseDTO;
		User userDB = userRepo.findByUsername(userRequestDTO.getUserName());		
		
		if(userDB==null) {
			throw new UserNotFoundException("User "+ userRequestDTO.getUserName() +" not found: ");
		}
		if (!userDB.getPassword().equals(userRequestDTO.getPassword())) {
			throw new UserPasswordIncorrectException("Password incorrect: "+userRequestDTO.getUserName());
		}
		
		userResponseDTO = new UserResponseDTO("Login Successful", 200);
		System.out.print(userDB.getUsername());
		User user = userDB;
		userResponseDTO.setUserId(user.getUserId());
		
		return userResponseDTO;
	
	}
	
}
