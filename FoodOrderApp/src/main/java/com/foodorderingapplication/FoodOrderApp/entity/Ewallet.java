package com.foodorderingapplication.FoodOrderApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ewallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ewalletId;
	private double balance;
	

}
