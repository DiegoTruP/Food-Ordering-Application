package com.foodorderingapplication.FoodOrderApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ResponseDTO;
import com.foodorderingapplication.FoodOrderApp.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<ResponseDTO> saveProductDetails(@RequestBody ProductRequestDTO productRequestDto) {
		productService.saveProductDetails(productRequestDto);
		return new ResponseEntity<ResponseDTO>(new ResponseDTO("Product saved", 200), HttpStatus.ACCEPTED);
	}
}
