package com.foodorderingapplication.FoodOrderApp.dto;


import java.util.ArrayList;
import java.util.List;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;

public class OrderDetailRequestDTO {
	
	@NotNull
	@Min(value = 1,message = "userId should be a number greater or equal to 1")
	private Integer userId;
	@NotNull
	@Min(value = 1,message = "storeId should be a number greater or equal to 1")
	private Integer storeId;
	@NotNull
	@NotEmpty(message = "Order Product List should not be empty")
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	@NotNull
	@Min(value = 0,message = "total price should be a number greater or equal to 0")
	@Max(value = Long.MAX_VALUE)
	private double totalPrice;
	@NotBlank
	private String instruction;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
}