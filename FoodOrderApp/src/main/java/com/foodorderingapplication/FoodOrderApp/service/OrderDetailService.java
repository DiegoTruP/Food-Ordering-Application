package com.foodorderingapplication.FoodOrderApp.service;

import java.util.List;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;

public interface OrderDetailService {

	OrderDetailDTO saveOrderDetail(OrderDetailRequestDTO orderRequestDTO);

	List<OrderDetailDTO> getOrderDetailByUserId(Integer userId, Integer pageNo, Integer pageSize);

}