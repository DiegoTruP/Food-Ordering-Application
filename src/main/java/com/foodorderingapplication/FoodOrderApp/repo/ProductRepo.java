package com.foodorderingapplication.FoodOrderApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.dto.ProductDetails;
import com.foodorderingapplication.FoodOrderApp.entity.Product;
import com.foodorderingapplication.FoodOrderApp.entity.Store;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer>{
	
	@Query(value="SELECT new com.foodorderingapplication.FoodOrderApp.dto.ProductDetails(p.productId, p.productName, p.productDescription, p.productPrice, p.isAvailable, p.productCategory) FROM Product p WHERE p.store = :store")
	List<ProductDetails> findAllProductsByStore(@Param("store") Store store);
}
