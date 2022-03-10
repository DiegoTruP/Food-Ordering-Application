package com.foodorderingapplication.FoodOrderApp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.entity.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer>{
	
}
