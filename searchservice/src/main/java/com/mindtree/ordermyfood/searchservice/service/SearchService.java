package com.mindtree.ordermyfood.searchservice.service;

import java.util.List;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;
import com.mindtree.ordermyfood.searchservice.exception.SearchServiceException;

public interface SearchService {


	List<RestaurantDto> getAllRestaurant(Double distance, Double lat, Double lng, String foodtype, Double budget,
			Double rating, String searchkeyword, Character veg) throws SearchServiceException;

}
