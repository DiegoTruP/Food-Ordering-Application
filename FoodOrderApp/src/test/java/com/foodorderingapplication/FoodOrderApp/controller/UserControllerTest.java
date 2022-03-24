package com.foodorderingapplication.FoodOrderApp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.repo.UserRepo;
import com.foodorderingapplication.FoodOrderApp.service.UserService;
import com.foodorderingapplication.FoodOrderApp.dto.UserResponseDTO;


@WebMvcTest(UserController.class)
public class UserControlleTest {
	
	@MockBean
	UserService userService;
	
	@Mock
	UserRepo userRepo;
	
	@Autowired
	MockMvc mockMvc;
	
	UserRequestDTO userRequestDTO;
	UserResponseDTO userResponseDTO;
	
	@BeforeEach
	public void setUp() {
		userRequestDTO = new UserRequestDTO();
		userRequestDTO.setUserId(1);
		userRequestDTO.setUserName("Kevin");
		userRequestDTO.setPassword("12345678");
		
		userResponseDTO = new UserResponseDTO("Login Successful",200);
		userResponseDTO.setUserId(1);
		
	}
	
	@Test
	public void LoginTest() throws Exception {
				
		when(userService.AuthenticateUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);
		
		MvcResult result = mockMvc.perform(post("/Login").content(asJsonString(userRequestDTO)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())		
			.andExpect(jsonPath("$.statusCode").value(200))
			.andExpect(jsonPath("$.userId").value(1)).andReturn();
		
		String responseBody = result.getResponse().getContentAsString();
		
		System.out.println(responseBody);
		
	}
	
	@Test
	public void LoginTestException() throws Exception {
		
		userRequestDTO.setUserName("");
		userRequestDTO.setPassword("");
		when(userService.AuthenticateUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);
		
		MvcResult result = mockMvc.perform(post("/Login").content(asJsonString(userRequestDTO)).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())		
			.andExpect(jsonPath("$.statusCode").value("MA400"))
			.andExpect(jsonPath("$.message").value("Invalid Arguments Passed")).andReturn();
		
		//mockMvc.perform(asyncDispatch(result));
		String responseBody = result.getResponse().getContentAsString();
		
		System.out.println(responseBody);
		
	}	

	private String asJsonString(UserRequestDTO userRequestDTO2) {

		try {
			return new ObjectMapper().writeValueAsString(userRequestDTO2);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
