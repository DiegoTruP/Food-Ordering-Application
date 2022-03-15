package com.foodorderingapplication.FoodOrderApp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodorderingapplication.FoodOrderApp.dto.StoreDetails;
import com.foodorderingapplication.FoodOrderApp.dto.StoreResponseDTO;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;
import com.foodorderingapplication.FoodOrderApp.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepo storeRepo;
	
	@Override
	public StoreResponseDTO getAllStoreDetails() {
		List<StoreDetails> storeDetailList = storeRepo.findAllStores();
		
		StoreResponseDTO storeResponseDTO = new StoreResponseDTO("Store Details fetched success", 200);
		storeResponseDTO.setStoreList(storeDetailList);
		
		return storeResponseDTO;
	}
	
}
