package com.foodorderingapplication.FoodOrderApp.service;

import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.UserResponseDTO;

public interface UserService {
	UserResponseDTO AuthenticateUser(UserRequestDTO userRequestDTO);
}