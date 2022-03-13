package com.foodorderingapplication.FoodOrderApp.service;

import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ProductResponseDTO;

public interface ProductService {

	void saveProductDetails(ProductRequestDTO productRequestDto);
	ProductResponseDTO getAllProductsByStore(Integer storeId);
	
}
