package com.foodorderingapplication.FoodOrderApp.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.repo.ProductRepo;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;
import com.foodorderingapplication.FoodOrderApp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	StoreRepo storeRepo;
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public void saveProductDetails(ProductRequestDTO productRequestDto) {
		Product product = new Product();
		BeanUtils.copyProperties(productRequestDto, product);
		
		product.setProductCategory(ProductCategory.valueOf(productRequestDto.getProductCategory()));
		
		Optional<Store> storeOptional = storeRepo.findById(productRequestDto.getStoreId());
		if(storeOptional.isPresent())
			product.setStore(storeOptional.get());
		
		productRepo.save(product);
	}

	
}
