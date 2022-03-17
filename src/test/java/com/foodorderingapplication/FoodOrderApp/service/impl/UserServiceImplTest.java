package com.foodorderingapplication.FoodOrderApp.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.UserResponseDTO;
import com.foodorderingapplication.FoodOrderApp.exception.UserNotFoundException;
import com.foodorderingapplication.FoodOrderApp.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@Mock
	UserRepo userRepo;
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	UserRequestDTO userRequestDTO;
	
	@BeforeEach
	public void setUp() {
		userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUserId(1);
		userRequestDTO.setUserName("Kevin");
		userRequestDTO.setPassword("Kevin");		
	}
	
	@Test
	@DisplayName("User Login")
	public void AuthenticateUserTest() {
		
		//userRepo.findByUsername
		//when(userRepo.findByUsername("Kevin")).thenReturn(userRequestDTO);
		when(userRepo.findByUsername("Kevin")).thenReturn(null);
		
		
		assertThrows(UserNotFoundException.class, () -> userServiceImpl.AuthenticateUser(userRequestDTO));
		
//		UserResponseDTO loginResult = userServiceImpl.AuthenticateUser(userRequestDTO);
//		assertNotNull(loginResult);
//		assertEquals(1, loginResult.getUserId());
		
		
	}
	
}
