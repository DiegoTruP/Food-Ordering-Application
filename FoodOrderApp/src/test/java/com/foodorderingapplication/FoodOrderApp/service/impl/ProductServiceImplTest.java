package com.foodorderingapplication.FoodOrderApp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodorderingapplication.FoodOrderApp.dto.ProductDetails;
import com.foodorderingapplication.FoodOrderApp.dto.ProductRequestDTO;
import com.foodorderingapplication.FoodOrderApp.dto.ProductResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Address;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.ProductCategory;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.exception.StoreNotFoundException;
import com.foodorderingapplication.FoodOrderApp.repo.ProductRepo;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	StoreRepo storeRepo;
	
	@Mock
	ProductRepo productRepo;
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;
	
	ProductRequestDTO productRequestDto;
	Store store;
	ProductDetails productDetails1;
	ProductDetails productDetails2;
	ProductDetails productDetails3;
	
	@BeforeEach
	public void init() {
		productRequestDto = new ProductRequestDTO();
		productRequestDto.setProductName("Pizza del perro negro");
		productRequestDto.setProductCategory("VEG");
		productRequestDto.setProductPrice(100);
		productRequestDto.setProductDescription("La original pizza del perro negro con extra queso");
		productRequestDto.setStoreId(2);
		productRequestDto.setAvailable(true);
		
		Product product = new Product();
		product.setProductId(5);
		product.setProductName("Pizza del perro negro");
		product.setProductCategory(ProductCategory.VEG);
		product.setProductDescription("Las mejores pizzas de la CDMX");
		product.setProductPrice(100);
		product.setAvailable(true);
		product.setStore(store);
		
		Address address = new Address();
		address.setCity("Nezahualcoyotl");
		address.setPincode("57200");
		address.setStreet("Riva palacio");
		
		store = new Store();
		store.setStoreId(2);
		store.setStoreName("Perro negro");
		store.setStoreDescription("Pizzas para llevar");
		store.setProductList(List.of(product));
		store.setRating(4);
		store.setStoreAddress(address);
		
		productDetails1 = new ProductDetails();
		productDetails1.setProductId(3);
		productDetails1.setProductName("Jabon vanish");
		productDetails1.setProductDescription("Jabon liquido para ropa color");
		productDetails1.setProductPrice(450);
		productDetails1.setProductCategory(ProductCategory.CENAS);
		productDetails1.setAvailable(true);
		
		productDetails2 = new ProductDetails();
		productDetails2.setProductId(5);
		productDetails2.setProductName("Te Canela");
		productDetails2.setProductDescription("Te de canela con rallado de jengibre");
		productDetails2.setProductPrice(99);
		productDetails2.setProductCategory(ProductCategory.BEBIDAS);
		productDetails2.setAvailable(false);
		
		productDetails3 = new ProductDetails();
		productDetails3.setProductId(10);
		productDetails3.setProductName("Ensalada con semillas");
		productDetails3.setProductDescription("Ensalada de lechuga con manzanas y grasas saludables");
		productDetails3.setProductPrice(320);
		productDetails3.setProductCategory(ProductCategory.VEG);
		productDetails3.setAvailable(true);
	}
	
	@Test
	@DisplayName("Save Product Details: POSITIVE")
	public void saveProductDetailsTest() {
		// stub storeRepo.findById
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		// stub productRepo.save
		when(productRepo.save(any(Product.class))).thenAnswer(i -> {
			Product product = i.getArgument(0);
			product.setProductId(2);
			
			return product;
		});
		
		Product productResult = productServiceImpl.saveProductDetails(productRequestDto);
		assertNotNull(productResult);
		assertEquals(2, productResult.getStore().getStoreId());
		assertEquals("Pizza del perro negro", productResult.getProductName());
	}
	
	@Test
	@DisplayName("Save Product Details: NEGATIVE")
	public void saveProductDetailsTestNegative() {
		// stub storeRepo.findById
		when(storeRepo.findById(2)).thenReturn(Optional.empty());
		
		assertThrows(StoreNotFoundException.class, () -> productServiceImpl.saveProductDetails(productRequestDto));
	}
	
	@Test
	@DisplayName("Get All Products By Store: POSITIVE")
	public void getAllProductsByStoreTest() {
		// stub storeRepo.findById
		when(storeRepo.findById(2)).thenReturn(Optional.of(store));
		
		// stub productRepo.findAllProductsByStore
		when(productRepo.findAllProductsByStore(any(Store.class))).thenReturn(List.of(productDetails1, productDetails2, productDetails3));
		
		ProductResponseDTO productResponseDto = productServiceImpl.getAllProductsByStore(2);
		assertNotNull(productResponseDto);
		assertEquals(10, productResponseDto.getProductList().get(2).getProductId());
		assertEquals(3, productResponseDto.getProductList().size());
		assertEquals(ProductCategory.BEBIDAS, productResponseDto.getProductList().get(1).getProductCategory());
		assertEquals(200, productResponseDto.getStatusCode());
	}
	
	@Test
	@Disabled
	@DisplayName("Get All Products By Store: NEGATIVE")
	public void getAllProductsByStoreTestNegative() {
		// stub storeRepo.findById
		when(storeRepo.findById(5)).thenReturn(Optional.empty());
		
		assertThrows(StoreNotFoundException.class, () -> productServiceImpl.getAllProductsByStore(5));
	}
}
