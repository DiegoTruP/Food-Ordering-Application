package com.foodorderingapplication.FoodOrderApp.dto;

import java.util.ArrayList;
import java.util.List;

public class StoreResponseDTO extends ResponseDTO {
	
	public StoreResponseDTO(String message, int statusCode) {
		super(message, statusCode);
		// TODO Auto-generated constructor stub
	}
	private List<StoreDetails> storeList = new ArrayList<>();
	
	public List<StoreDetails> getStoreList() {
		return storeList;
	}
	public void setStoreList(List<StoreDetails> storeList) {
		this.storeList = storeList;
	}
}
