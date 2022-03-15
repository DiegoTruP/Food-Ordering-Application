package com.foodorderingapplication.FoodOrderApp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;

public interface OrderDetailView {
	
	Integer getOrderDetail();
	LocalDate getOrderDate();
	Integer getUserId();
	Integer getStoreId();
	List<OrderProductView> getOrderProductList();
	double getTotalPrice();
	
	interface OrderProductView {
		
		Integer getProductId();
		double getQuantity();
		double getProductPrice();
	}
	

}
