package com.foodorderingapplication.FoodOrderApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer>{

}
