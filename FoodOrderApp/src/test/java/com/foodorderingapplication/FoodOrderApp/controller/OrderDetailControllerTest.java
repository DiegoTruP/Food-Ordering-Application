package com.foodorderingapplication.FoodOrderApp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorderingapplication.FoodOrderApp.controller.OrderDetailController;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;
import com.foodorderingapplication.FoodOrderApp.service.OrderDetailService;

@WebMvcTest(OrderDetailController.class)
public class OrderDetailControllerTest {	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private OrderDetailService orderDetailService;
	
	OrderDetailRequestDTO orderDetailRequest;
	
	OrderDetailDTO orderDetailDto;
	
	OrderDetailDTO orderDetailDto2;
	
	OrderProduct orderProduct;
	
	OrderProduct orderProduct2;
	
	@BeforeEach
	public void setUp() {
		orderProduct = new OrderProduct();
		orderProduct.setProductId(1);
		orderProduct.setProductPrice(10);
		orderProduct.setQuantity(2);
		
		orderDetailRequest = new OrderDetailRequestDTO();
		orderDetailRequest.setInstruction("Deliver in 15 min");
		orderDetailRequest.setStoreId(1);
		orderDetailRequest.setUserId(1);
		orderDetailRequest.setTotalPrice(20);
		orderDetailRequest.setOrderProductList(List.of(orderProduct));
		
		orderDetailDto = new OrderDetailDTO();
		orderDetailDto.setInstruction("Deliver in 15 min");
		orderDetailDto.setStoreId(1);
		orderDetailDto.setUserId(1);
		orderDetailDto.setTotalPrice(20);
		orderDetailDto.setOrderProductList(List.of(orderProduct));
		
		
		orderProduct2 = new OrderProduct();
		orderProduct2.setProductId(2);
		orderProduct2.setProductPrice(15);
		orderProduct2.setQuantity(1);
		
		List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
		orderProductList.add(orderProduct);
		orderProductList.add(orderProduct2);
		
		orderDetailDto2 = new OrderDetailDTO();
		orderDetailDto2.setInstruction("Deliver in 30 min");
		orderDetailDto2.setStoreId(1);
		orderDetailDto2.setUserId(1);
		orderDetailDto2.setTotalPrice(35);
		orderDetailDto2.setOrderProductList(orderProductList);
		
		
		
	}
	
	@Test
	public void saveOrderDetailTest() throws Exception {
		
		when(orderDetailService.saveOrderDetail(any(OrderDetailRequestDTO.class))).thenReturn(orderDetailDto);
		
		String jsonObj = objectMapper.writeValueAsString(orderDetailRequest);
		mockMvc.perform(post("/orderdetails").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isAccepted())
		.andExpect(jsonPath("$.message").value("Order done"))
		.andExpect(jsonPath("$.statusCode").value(200));
	}
	
	
	@Test
	public void getOrderDetailByUserIdTest() throws Exception {
		 mockMvc.perform(get("/users/{userId}/orderdetails",1).contentType(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk());
	}
	
	@Test
	public void getOrderDetailByUserIdTestList() throws Exception {
		
		//orderDetailService.getOrderDetailByUserId
		when(orderDetailService.getOrderDetailByUserId(any(Integer.class), any(Integer.class), any(Integer.class))).thenReturn(List.of(orderDetailDto,orderDetailDto2));

		 MvcResult mvcResult = mockMvc.perform(get("/users/{userId}/orderdetails",1).contentType(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk())
		 .andDo(MockMvcResultHandlers.print())
		 .andReturn();
		 
		 JSONAssert.assertEquals(mvcResult.getResponse().getContentAsString(), 
				 objectMapper.writeValueAsString(List.of(orderDetailDto,orderDetailDto2)), false);
	}
	
	@Test
	public void saveOrderDetailValidationTest() throws Exception {
		
		orderDetailRequest.setStoreId(null);
		orderDetailRequest.setInstruction(" ");
		orderDetailRequest.setUserId(0);
		String jsonObj = objectMapper.writeValueAsString(orderDetailRequest);
		mockMvc.perform(post("/orderdetails").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print())
		.andExpect(jsonPath("$.invalidArguments.storeId").value("must not be null"))
		.andExpect(jsonPath("$.invalidArguments.instruction").value("must not be blank"))
		.andExpect(jsonPath("$.invalidArguments.userId").value("userId should be a number greater or equal to 1"))
		.andExpect(jsonPath("$.statusCode").value("MA400"));

	}
	

}
