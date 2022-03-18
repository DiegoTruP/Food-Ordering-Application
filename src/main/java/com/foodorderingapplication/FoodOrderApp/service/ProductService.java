package com.foodorderingapplication.FoodOrderApp.service;

import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ProductResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Product;

public interface ProductService {

	Product saveProductDetails(ProductRequestDTO productRequestDto);
	ProductResponseDTO getAllProductsByStore(Integer storeId);
	
}
