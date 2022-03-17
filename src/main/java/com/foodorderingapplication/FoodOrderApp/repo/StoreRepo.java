package com.foodorderingapplication.FoodOrderApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.dto.StoreDetails;
import com.foodorderingapplication.FoodOrderApp.entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer>{
	@Query(value="SELECT new com.foodorderingapplication.FoodOrderApp.dto.StoreDetails(s.storeId, s.storeName, s.storeDescription, s.rating) FROM Store s")
	List<StoreDetails> findAllStores();
}
