package com.foodorderingapplication.FoodOrderApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapplication.FoodOrderApp.dto.ProductResponseDTO;
import com.foodorderingapplication.FoodOrderApp.dto.StoreResponseDTO;
import com.foodorderingapplication.FoodOrderApp.service.ProductService;
import com.foodorderingapplication.FoodOrderApp.service.StoreService;

@RestController
public class StoreController {

	@Autowired
	StoreService storeService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/stores")
	public ResponseEntity<StoreResponseDTO> getAllStoreDetails() {
		StoreResponseDTO storeResponseDto = storeService.getAllStoreDetails();
		return new ResponseEntity<StoreResponseDTO>(storeResponseDto, HttpStatus.OK);
	}
	
	@GetMapping("/stores/{storeId}/products")
	public ResponseEntity<ProductResponseDTO> getAllProductsByStore(@PathVariable Integer storeId) {
		ProductResponseDTO productResponseDto = productService.getAllProductsByStore(storeId);
		return new ResponseEntity<ProductResponseDTO>(productResponseDto, HttpStatus.OK);
	}
}
