package com.foodorderingapplication.FoodOrderApp.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.foodorderingapplication.FoodOrderApp.dto.OrderDTOProjection;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailDTO;
import com.foodorderingapplication.FoodOrderApp.dto.OrderDetailRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.OrderDetail;
import com.foodorderingapplication.FoodOrderApp.entity.OrderProduct;
import com.foodorderingapplication.FoodOrderApp.entity.OrderStatus;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.User;
import com.foodorderingapplication.FoodOrderApp.exception.MismatchingPriceException;
import com.foodorderingapplication.FoodOrderApp.exception.ProductNotFoundException;
import com.foodorderingapplication.FoodOrderApp.exception.UserNotFoundException;
import com.foodorderingapplication.FoodOrderApp.repo.OrderDetailRepo;
import com.foodorderingapplication.FoodOrderApp.repo.ProductRepo;
import com.foodorderingapplication.FoodOrderApp.repo.UserRepo;
import com.foodorderingapplication.FoodOrderApp.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	OrderDetailRepo orderDetailRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public OrderDetailDTO saveOrderDetail(OrderDetailRequestDTO orderRequestDTO) {
		
		List<OrderProduct> orderProductList = orderRequestDTO.getOrderProductList();
		
		List<OrderProduct> productNotFound = orderProductList.stream().filter(myOrderProduct -> {	
				Optional<Product> product = productRepo.findById(myOrderProduct.getProductId());
				return product.isEmpty();
		}).collect(Collectors.toList());
		
		if(!productNotFound.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found",productNotFound);
		}
		
		Boolean isPriceMatch =  orderRequestDTO.getOrderProductList().stream().allMatch(orderProduct -> {
							Optional<Product> product = productRepo.findById(orderProduct.getProductId());
							return product.get().getProductPrice()==orderProduct.getProductPrice();	
						});
		
		if(!isPriceMatch) {
			//Throw mismatching price error
			throw new MismatchingPriceException("Product mismatch price!");
		}
		
		Boolean isTotalMatch = orderRequestDTO.getOrderProductList().stream().map(product -> product.getProductPrice()*product.getQuantity())
											.reduce(0.0, (total,productTotal) -> total+productTotal ).equals(orderRequestDTO.getTotalPrice());
		
		if(!isTotalMatch) {
			throw new MismatchingPriceException("Total mismatch!");
		}
		
		
		OrderDetail orderDetail = new OrderDetail();
		BeanUtils.copyProperties(orderRequestDTO, orderDetail);
		orderDetail.setOrderDate(LocalDate.now());
		orderDetail.setStatus(OrderStatus.PLACED);	
		
		orderDetail = orderDetailRepo.save(orderDetail);
		OrderDetailDTO orderDetailDto = new OrderDetailDTO();
		BeanUtils.copyProperties(orderDetail, orderDetailDto);
		
		return orderDetailDto;
		
	}

	@Override
	public List<OrderDetailDTO> getOrderDetailByUserId(Integer userId,Integer pageNo, Integer pageSize) {
		
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("User ID "+ userId +" not found: ");
		}
		
		Pageable pages = PageRequest.of(pageNo,pageSize);
		
		List<OrderDetail> orderDetailList = orderDetailRepo.findAllByUserId(userId,pages).getContent();
		List<OrderDetailDTO> orderDetailDtoList = new ArrayList<OrderDetailDTO>();
		//List<OrderDTOProjection> orderDtoProjection = orderDetailRepo.findAllByUserId(userId);

		//orderDtoProjection.stream().forEach(order -> order.setOrderProductList(orderDetailRepo.findAllProductsByOrderId(order.getOrderDetailId())));
		
		orderDetailDtoList = orderDetailList.stream().map(orderDetail -> {
			OrderDetailDTO orderDetailDto = new OrderDetailDTO();
			BeanUtils.copyProperties(orderDetail, orderDetailDto);
			return orderDetailDto;
		}).collect(Collectors.toList());
		
		
		return orderDetailDtoList;
	}

}
