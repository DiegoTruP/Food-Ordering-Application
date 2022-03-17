package com.foodorderingapplication.FoodOrderApp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.entity.Address;
import com.foodorderingapplication.FoodOrderApp.repo.ProductRepo;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	StoreRepo storeRepo; //Mock Object
	
	@Mock
	ProductRepo productRepo; //Mock Object

	@InjectMocks
	ProductServiceImpl productServiceImpl; // real object with mock dependent objects
	
	
	ProductRequestDTO productRequestDto;
	Store store;
	
	@BeforeEach
	public void setUp() {
	
		productRequestDto = new ProductRequestDTO();
		productRequestDto.setProductName("Veg Pizza");
		productRequestDto.setProductCategory("VEG");
		productRequestDto.setProductPrice(500);
		productRequestDto.setProductDescription("CHESSE PIZZA");
		productRequestDto.setStoreId(2);
		productRequestDto.setAvailable(true);
		
		Product product = new Product();
		product.setProductId(1);
		product.setProductCategory(ProductCategory.VEG);
		product.setProductDescription("Veg Sandwich");
		product.setProductName("Sandwich");
		product.setProductPrice(100);
		product.setStore(store);
		
		Address address = new Address();
		address.setCity("Bengaluru");
		address.setPincode("560062");
		address.setStreet("Brigade Street");
		
		store = new Store();
		store.setStoreId(2);
		store.setProductList(List.of(product));
		store.setRating(3);
		store.setStoreAddress(address);
		store.setStoreName("Hari Sandwich");
		store.setStoreDescription("Pizza, Sandwich, coke..");
	}

	
	@Test
	@DisplayName("save product details:positive")
	public void saveProductDetailsTest() {
		
		// stub storeRepo.findById
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		//productRepo.save
		when(productRepo.save(any(Product.class))).thenAnswer(i ->{
			Product product = i.getArgument(0);
			product.setProductId(2);
			return product;			
		});
		
		Product productResult = productServiceImpl.saveProductDetails(productRequestDto);
		assertNotNull(productResult);
		assertEquals(2, productResult.getStore().getStoreId());
		assertEquals("Veg Pizza", productResult.getProductName());
	}
	
}
