package com.foodorderingapplication.FoodOrderApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapplication.FoodOrderApp.dto.ProductDetails;
import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ProductResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.exception.StoreNotFoundException;
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
		if(storeOptional.isEmpty())
			throw new StoreNotFoundException("Store not found: " + productRequestDto.getStoreId());
			
		product.setStore(storeOptional.get());
		productRepo.save(product);
	}

	@Override
	public ProductResponseDTO getAllProductsByStore(Integer storeId) {
		Optional<Store> storeOptional = storeRepo.findById(storeId);
		if(storeOptional.isEmpty())
			throw new StoreNotFoundException("Store not found: " + storeId);
		
		List<ProductDetails> productDetailsList = productRepo.findAllProductsByStore(storeOptional.get());
		
		ProductResponseDTO productResponseDTO = new ProductResponseDTO("Store products fetched success", 200);
		productResponseDTO.setProductList(productDetailsList);
		
		return productResponseDTO;
	}

	
}
