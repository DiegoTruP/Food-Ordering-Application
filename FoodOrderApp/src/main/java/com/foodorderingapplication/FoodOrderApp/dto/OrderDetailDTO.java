package com.foodorderingapplication.FoodOrderApp.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;
import com.foodorderingapplication.FoodOrderApp.entity.OrderStatus;

public class OrderDetailDTO {
	
	private Integer orderDetailId;
	private LocalDate orderDate;
	private Integer userId;
	private Integer storeId;
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	private double totalPrice;
	private String instruction;
	private OrderStatus status;
	
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
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
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}
