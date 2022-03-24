package com.foodorderingapplication.FoodOrderApp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailResponseDTO;
import com.foodorderingapplication.FoodOrderApp.service.OrderDetailService;

@Validated
@RestController
public class OrderDetailController {
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@PostMapping("/orderdetails")
	public ResponseEntity<OrderDetailResponseDTO> saveOrderDetail(@Valid @RequestBody OrderDetailRequestDTO orderRequestDTO){
		OrderDetailDTO orderDetailDto =  new OrderDetailDTO();
		orderDetailDto =orderDetailService.saveOrderDetail(orderRequestDTO);
		
		OrderDetailResponseDTO orderDetailResponse = new OrderDetailResponseDTO("Order done", 200,String.valueOf(orderDetailDto.getOrderDetailId()));
		//orderDetailResponse.setOrderNumber(String.valueOf(orderDetailDto.getOrderDetailId()));
		
		return new ResponseEntity<OrderDetailResponseDTO>(orderDetailResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users/{userId}/orderdetails")
	public ResponseEntity<List<OrderDetailDTO>> getOrderDetailByUserId(@NotNull@Min(value = 1,message = "User should be greater than 1")@PathVariable Integer userId, 
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize){
		List<OrderDetailDTO> orderDetailDtoList = new ArrayList<OrderDetailDTO>();
		
		orderDetailDtoList = orderDetailService.getOrderDetailByUserId(userId, pageNo, pageSize);
		
		return new ResponseEntity<List<OrderDetailDTO>>(orderDetailDtoList,HttpStatus.OK);
	}

}