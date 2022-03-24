package com.foodorderingapplication.FoodOrderApp.service.impl;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.OrderDetail;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.entity.User;
import com.foodorderingapplication.FoodOrderApp.exception.MismatchingPriceException;
import com.foodorderingapplication.FoodOrderApp.exception.ProductNotFoundException;
import com.foodorderingapplication.FoodOrderApp.exception.UserNotFoundException;
import com.foodorderingapplication.FoodOrderApp.repo.OrderDetailRepo;
import com.foodorderingapplication.FoodOrderApp.repo.ProductRepo;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;
import com.foodorderingapplication.FoodOrderApp.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("Order Detail Service Test")
public class OrderDetailServiceImplTest {
	
	@Mock
	OrderDetailRepo orderDetailRepo;
	
	@Mock
	ProductRepo productRepo;
	
	@Mock
	UserRepo userRepo;

	@InjectMocks
	OrderDetailServiceImpl OrderDetailServiceImpl;
	
	OrderDetailRequestDTO orderDetailRequest;
	
	OrderDetail orderDetail;
	
	OrderDetail orderDetail2;
	
	
	Store store;
	
	Product product;
	
	User user;
	
	Page<OrderDetail> page;
	
	
	
	@BeforeEach
	public void setUp() {
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setProductId(1);
		orderProduct.setProductPrice(10);
		orderProduct.setQuantity(2);
		
		orderDetailRequest = new OrderDetailRequestDTO();
		orderDetailRequest.setInstruction("Deliver in 15 min");
		orderDetailRequest.setStoreId(1);
		orderDetailRequest.setUserId(1);
		orderDetailRequest.setTotalPrice(20);
		orderDetailRequest.setOrderProductList(List.of(orderProduct));
		
		product = new Product();
		product.setAvailable(true);
		product.setProductCategory(ProductCategory.VEG);
		product.setProductDescription("Pizza with Portobello");
		product.setProductId(1);
		product.setProductPrice(10);
		product.setStore(store);
		product.setProductName("Veg Pizza");
		
		store = new Store();
		store.setStoreName("Dominos pizza");
		store.setStoreId(1);
		store.setStoreDescription("Pizzeria");
		store.setProductList(List.of(product));
		
		user = new User();
		user.setUserId(1);
		user.setEmail("diego@mail.com");
		user.setPhoneNo("3355667799");
		user.setPassword("HCL12345");
		user.setUsername("Diego Go");
		
		orderDetail = new OrderDetail();
		orderDetail.setInstruction("Deliver in 15 min");
		orderDetail.setStoreId(1);
		orderDetail.setUserId(1);
		orderDetail.setTotalPrice(20);
		orderDetail.setOrderProductList(List.of(orderProduct));
		
		orderDetail2 = new OrderDetail();
		orderDetail2.setInstruction("Deliver in 30 min");
		orderDetail2.setStoreId(1);
		orderDetail2.setUserId(1);
		orderDetail2.setTotalPrice(35);
		orderDetail2.setOrderProductList(List.of(orderProduct));
		
		page = new PageImpl<OrderDetail>(List.of(orderDetail,orderDetail2));
		
		
	}
	
	@Test
	@DisplayName("Save Order Detail : positive")
	public void saveOrderDetailTest() {
		//productRepo.findById
		when(productRepo.findById(1)).thenReturn(Optional.of(product));
		//orderDetailRepo.save
		when(orderDetailRepo.save(any(OrderDetail.class))).thenAnswer(ord -> {
			OrderDetail orderDetail = ord.getArgument(0);
			orderDetail.setOrderDetailId(2);
			return orderDetail;});
		
		OrderDetailDTO orderDetailDTO = OrderDetailServiceImpl.saveOrderDetail(orderDetailRequest);
		
		assertNotNull(orderDetailDTO);
		assertEquals("Deliver in 15 min",orderDetailDTO.getInstruction());
		assertEquals(20, orderDetailDTO.getTotalPrice());
		assertEquals(1, orderDetailDTO.getStoreId());
		assertEquals(1, orderDetailDTO.getUserId());

	}
	
	@Test
	@DisplayName("Save Order Detail : negative 'Product Not Found'")
	public void saveOrderDetailNegativeTest() {
		//productRepo.findById
		when(productRepo.findById(1)).thenReturn(Optional.empty());

		assertThrows(ProductNotFoundException.class, () -> OrderDetailServiceImpl.saveOrderDetail(orderDetailRequest));

	}
	
	@Test
	@DisplayName("Save Order Detail : Negative 'Price mismatch'")
	public void saveOrderDetailPriceTest() {
		//productRepo.findById
		when(productRepo.findById(1)).thenReturn(Optional.of(product));
		
		orderDetailRequest.setTotalPrice(10);
		
		assertThrows(MismatchingPriceException.class, () -> OrderDetailServiceImpl.saveOrderDetail(orderDetailRequest));
	}
	
	@Test
	@DisplayName("Get Order Detail by User ID : positive")
	public void getOrderDetailByUserIdTest() {
		//userRepo.findById
		when(userRepo.findById(1)).thenReturn(Optional.of(user));
		//orderDetailRepo.findAllByUserId
		when(orderDetailRepo.findAllByUserId(any(Integer.class),any(Pageable.class))).thenReturn(page);
		
		List<OrderDetailDTO> orderList =  OrderDetailServiceImpl.getOrderDetailByUserId(1, 0, 5);
		
		assertNotNull(orderList);
		assertEquals(2,orderList.size());
		assertEquals(OrderDetailDTO.class, orderList.get(0).getClass());
		assertEquals(20, orderList.get(0).getTotalPrice());
		assertEquals("Deliver in 15 min", orderList.get(0).getInstruction());
	}
	
	@Test
	@DisplayName("Get Order Detail by User ID : negative")
	public void getOrderDetailByUserIdNegativeTest() {
		//userRepo.findById
		when(userRepo.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class, () -> OrderDetailServiceImpl.getOrderDetailByUserId(1, 0, 5));
	}
	
	
	
	
	
	
}
