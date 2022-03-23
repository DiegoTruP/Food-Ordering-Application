package com.foodorderingapplication.FoodOrderApp.dto;

import java.time.LocalDate;
import java.util.List;

public interface OrderDetailView {
	
	Integer getOrderDetailId();
	LocalDate getOrderDate();
	Integer getUserId();
	Integer getStoreId();
	List<OrderProduct> getOrderProductList();
	double getTotalPrice();
	
	interface OrderProduct {
		
		Integer getProductId();
		double getQuantity();
		double getProductPrice();
	}
	
}
