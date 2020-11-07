package com.mindtree.ordermyfood.ordermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.mindtree.ordermyfood.ordermanagement.entity.Restaurant;

@Component
public interface RestaurantDAO extends JpaRepository<Restaurant, Integer>{
	
	Restaurant findById(int restaurantId);
}
