package com.foodorderingapplication.FoodOrderApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.foodorderingapplication.FoodOrderApp.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	User findByUsername(String Username);
}
