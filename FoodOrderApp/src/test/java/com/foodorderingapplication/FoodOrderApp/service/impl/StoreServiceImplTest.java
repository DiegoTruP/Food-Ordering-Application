package com.foodorderingapplication.FoodOrderApp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foodorderingapplication.FoodOrderApp.dto.StoreDetails;
import com.foodorderingapplication.FoodOrderApp.dto.StoreResponseDTO;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest {
	
	@Mock
	StoreRepo storeRepo;
	
	@InjectMocks
	StoreServiceImpl storeServiceImpl;
	
	StoreDetails storeDetails1;
	StoreDetails storeDetails2;
	
	@BeforeEach
	public void init() {
		storeDetails1 = new StoreDetails();
		storeDetails1.setStoreId(2);
		storeDetails1.setStoreName("La polar");
		storeDetails1.setStoreDescription("Restaurante bar gourmet");
		storeDetails1.setRating(4);
		
		storeDetails2 = new StoreDetails();
		storeDetails2.setStoreId(1);
		storeDetails2.setStoreName("Raccon");
		storeDetails2.setStoreDescription("Cafeteria y fondita :)");
		storeDetails2.setRating(5);
	}
	
	@Test
	@DisplayName("Get all store details: POSITIVE")
	public void getAllStoreDetailsTest() {
		// stub storeRepo.findAllStores
		when(storeRepo.findAllStores()).thenReturn(List.of(storeDetails1, storeDetails2));
		
		StoreResponseDTO storeResponseDto = storeServiceImpl.getAllStoreDetails();
		assertEquals(2, storeResponseDto.getStoreList().size());
		assertEquals("Raccon", storeResponseDto.getStoreList().get(1).getStoreName());
		assertEquals(4, storeResponseDto.getStoreList().get(0).getRating());
		assertEquals("Store Details fetched success", storeResponseDto.getMessage());
	}
	
	@Test
	@Disabled
	@DisplayName("Get all store details: NEGATIVE")
	public void getAllStoreDetailsTestNegative() {
		// stub storeRepo.findAllStores
		when(storeRepo.findAllStores()).thenReturn(null);
		
		StoreResponseDTO storeResponseDto = storeServiceImpl.getAllStoreDetails();
		assertNull(storeResponseDto.getStoreList());
	}

}
