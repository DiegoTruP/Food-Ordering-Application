package com.foodorderingapplication.FoodOrderApp.service;

import java.util.List;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDTOProjection;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;

public interface OrderDetailService {

	OrderDetailDTO saveOrderDetail(OrderDetailRequestDTO orderRequestDTO);

	//List<OrderDTOProjection> getOrderDetailByUserId(Integer userId, Integer pageNo, Integer pageSize);
	
	
	List<OrderDetailDTO> getOrderDetailByUserId(Integer userId, Integer pageNo, Integer pageSize);
}