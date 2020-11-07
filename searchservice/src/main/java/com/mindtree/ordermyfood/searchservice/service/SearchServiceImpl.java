package com.mindtree.ordermyfood.searchservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;
import com.mindtree.ordermyfood.searchservice.exception.SearchServiceException;
import com.mindtree.ordermyfood.searchservice.repository.SearchServiceRepository;

@Service
public class SearchServiceImpl implements SearchService {

	private static Logger logger = LoggerFactory.getLogger(SearchService.class);
	
	@Autowired
	private SearchServiceRepository searchRepo;

	@Override
	public List<RestaurantDto> getAllRestaurant(Double distance, Double lat,Double lng, String foodtype, 
			Double budget, Double rating,String searchkeyword,Character veg) throws  SearchServiceException {
		List<RestaurantDto> restaurants;
		try {
		if(null == distance || null == lat || null == lng)
			restaurants = searchRepo.findRestaurants(foodtype,budget,rating,searchkeyword,veg);
		else
			restaurants = searchRepo.findRestaurants(distance,lat,lng,foodtype,budget,rating,searchkeyword,veg);
		logger.info("method getAllRestaurant executed.");
		return restaurants;
		}catch(DatabaseException exception) {
			logger.error("Exception occured at getAllRestaurant()", exception);
			throw new SearchServiceException("Can not connect to resources.",exception);
		}
	}
	
}
