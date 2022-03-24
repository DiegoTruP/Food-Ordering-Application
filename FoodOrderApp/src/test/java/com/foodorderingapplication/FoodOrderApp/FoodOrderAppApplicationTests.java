package com.foodorderingapplication.FoodOrderApp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailResponseDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = FoodOrderAppApplication.class)
class FoodOrderAppApplicationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	Integer port;
	
	OrderDetailRequestDTO orderDetailRequest;
	
	OrderProduct orderProduct;
	
//	Map<String, Integer> pagination;
	
	@BeforeEach
	void setUp() {

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
		
//		pagination.put("pageNo", 0);
//		pagination.put("pageSize", 10);
	}
	

	@Test
	@DisplayName("Save order detail : positive")
	void saveOrderDetailTest() {

		HttpEntity<OrderDetailRequestDTO> request = new HttpEntity<>(orderDetailRequest);
		
		ResponseEntity<OrderDetailResponseDTO> response = testRestTemplate.postForEntity("http://localhost:"+ port +"/orderdetails",orderDetailRequest, OrderDetailResponseDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
		assertThat(response.getBody().getStatusCode()).isEqualTo(200);
		assertThat(response.getBody().getMessage()).isEqualTo("Order done");
		
	}
	
	
	@Test
	@DisplayName("Get Orders by User ID : positive")
	void getOrdersByUserId() {
		ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+ port +"/users/1/orderdetails", List.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().size()).isEqualTo(5);//Limited by default pagination
	}
	
	@Test
	@DisplayName("Get Orders by User ID (Test pagination): positive")
	void getOrdersByUserId_TestPagination() {
		ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:"+ port +"/users/1/orderdetails?pageNo=0&pageSize=10", List.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().size()).isEqualTo(10);//Limited by default pagination
	}


}
