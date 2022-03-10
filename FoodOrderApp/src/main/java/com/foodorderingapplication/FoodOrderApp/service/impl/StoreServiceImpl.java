package com.foodorderingapplication.FoodOrderApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.foodorderingapplication.FoodOrderApp.dto.StoreDetails;
import com.foodorderingapplication.FoodOrderApp.dto.StoreResponseDTO;
import com.foodorderingapplication.FoodOrderApp.entity.Store;
import com.foodorderingapplication.FoodOrderApp.repo.StoreRepo;
import com.foodorderingapplication.FoodOrderApp.service.StoreService;

public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepo storeRepo;
	
	@Override
	public StoreResponseDTO getAllStoreDetails() {
		List<Store> storeList = storeRepo.findAll();
		
		List<StoreDetails> storeDetailList = storeList.stream().map(store -> {
			StoreDetails storeDetail = new StoreDetails();
			BeanUtils.copyProperties(store, storeDetail);
			
			return storeDetail;
		}).collect(Collectors.toList());
		
		StoreResponseDTO storeResponseDTO = new StoreResponseDTO("Store Details fetched success", 200);
		storeResponseDTO.setStoreList(storeDetailList);
		
		return storeResponseDTO;
	}
	
}
