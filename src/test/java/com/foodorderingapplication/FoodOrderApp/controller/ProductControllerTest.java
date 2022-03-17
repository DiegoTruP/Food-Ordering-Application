package com.foodorderingapplication.FoodOrderApp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@MockBean
	ProductService productService;
	
	@Autowired
	private MockMvc mockMvc; 
	
	ProductRequestDTO productRequestDto;	
	
	@BeforeEach
	public void setUp() {
	
		productRequestDto = new ProductRequestDTO();
		productRequestDto.setProductName("Veg Pizza");
		productRequestDto.setProductCategory("VEG");
		productRequestDto.setProductPrice(500);
		productRequestDto.setProductDescription("CHESSE PIZZA");
		productRequestDto.setStoreId(2);
		productRequestDto.setAvailable(true);
	}
	
	@Test
	public void saveProductDetailsTest() throws Exception{
		mockMvc.perform(post("/products").content(asJsonString(productRequestDto)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted())
			.andExpect(jsonPath("$.message").value("Product saved")).andExpect(jsonPath("$.statusCode").value(200));		
		
	}

	public String asJsonString(ProductRequestDTO productRequestDto2) {
		try {
			return new ObjectMapper().writeValueAsString(productRequestDto2);
		} catch (JsonProcessingException e) {		
			e.printStackTrace();
		}
		return null;
	}
	
}
