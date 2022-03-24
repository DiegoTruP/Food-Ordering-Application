package com.foodorderingapplication.FoodOrderApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO;
import com.foodorderingapplication.FoodOrderApp.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
	@Query("Select new com.foodorderingapplication.FoodOrderApp.dto.UserRequestDTO(a.userId,a. username, a.password) from User a where a.username = :username")
	UserRequestDTO findByUsername(String username);
}
