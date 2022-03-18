package com.foodorderingapplication.FoodOrderApp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.foodorderingapplication.FoodOrderApp.service.ProductService;
import com.foodorderingapplication.FoodOrderApp.service.StoreService;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {
	
	@MockBean
	StoreService storeService;
	
	@MockBean
	ProductService productService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getAllStoreDetailsTest() throws Exception {
		mockMvc.perform(get("/stores")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void getAllProductsByStoreTest() throws Exception {
		mockMvc.perform(get("/stores/{storeId}/products", 2)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
