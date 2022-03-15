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
	
//	@Query("Select new com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO(order.orderDetailId, order.orderDate, order.userId, order.storeId,"
//			+ "	order.orderProductList, order.totalPrice) from OrderDetail order "
//			+ "where order.userId=:userId ")
	List<OrderDetailView> findAllDTOByUserId(Integer userId);
	
//	@Query("Select a from OrderDetail a left join fetch a.OrderProduct")
//	List<OrderDetail> findAllDTOByUserId(Integer userId);
	

}