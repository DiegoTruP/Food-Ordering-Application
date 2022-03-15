package com.foodorderingapplication.FoodOrderApp.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailView;
import com.foodorderingapplication.FoodOrderApp.entity.OrderDetail;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{

	List<OrderDetail> findAllByUserId(Integer userId, Pageable pages);
	

	List<OrderDetailView> findAllDTOByUserId(Integer userId);
	

}