package com.mindtree.ordermyfood.searchservice.repository;

import java.util.List;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.entity.Restaurants;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;

public interface SearchServiceRepository {

	List<RestaurantDto> findRestaurants(Double distance, Double lat, Double lng, String foodtype, Double budget,
			Double rating, String searchkeyword, Character veg) throws DatabaseException;

	List<RestaurantDto> findRestaurants(String foodtype, Double budget, Double rating, String searchkeyword,
			Character veg) throws DatabaseException;

}
