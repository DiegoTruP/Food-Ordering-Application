package com.foodorderingapplication.FoodOrderApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodorderingapplication.FoodOrderApp.entity.OrderDetail;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{

	List<OrderDetail> findAllByUserId(Integer userId);
	

}