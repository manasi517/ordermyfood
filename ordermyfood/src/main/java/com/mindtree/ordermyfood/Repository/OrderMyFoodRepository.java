package com.mindtree.ordermyfood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.entity.Restaurants;

public interface OrderMyFoodRepository extends JpaRepository<Restaurants, Integer>{

	public Restaurants saveAndFlush(Restaurants restaurant);

}
