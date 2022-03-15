package com.foodorderingapplication.FoodOrderApp.dto;

public class StoreDetails {
	private Integer storeId;
	private String storeName;
	private String storeDescription;
	private float rating;
	
	public StoreDetails() {
		super();
	}
	public StoreDetails(Integer storeId, String storeName, String storeDescription, float rating) {
		super();
		this.storeId = storeId;
		this.storeName = storeName;
		this.storeDescription = storeDescription;
		this.rating = rating;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreDescription() {
		return storeDescription;
	}
	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	
}
