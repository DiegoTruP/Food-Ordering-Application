package com.foodorderingapplication.FoodOrderApp.repo;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDTOProjection;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.entity.OrderDetail;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;


public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer>{

	Page<OrderDetail> findAllByUserId(Integer userId, Pageable pages);
	
	

//	List<OrderDTOProjection> findAllByUserId(Integer userId);
//	
//	
//	@Query(value = "select product_id, product_price, quantity from order_products where order_products.order_detail_id=:orderId",nativeQuery = true)
//	List<OrderProduct> findAllProductsByOrderId(@Param(value = "orderId") Integer orderId);
	
}