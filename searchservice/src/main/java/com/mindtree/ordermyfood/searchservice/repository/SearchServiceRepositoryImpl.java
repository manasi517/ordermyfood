package com.mindtree.ordermyfood.searchservice.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.searchservice.dao.SearchDao;
import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;
import com.mindtree.ordermyfood.searchservice.mapper.RestaurantMapper;
import com.mindtree.ordermyfood.searchservice.service.SearchService;

@Repository
public class SearchServiceRepositoryImpl implements SearchServiceRepository {
	private static Logger logger = LoggerFactory.getLogger(SearchService.class);
	@Autowired
	SearchDao searchDao;

	@Autowired
	RestaurantMapper restaurantMapper;

	@Override
	public List<RestaurantDto> findRestaurants(Double distance, Double lat, Double lng, String foodtype, Double budget,
			Double rating, String searchkeyword, Character veg) throws DatabaseException {
		try {
			List<RestaurantDto> restaurants= restaurantMapper.restaurantsToRestaurantsDto(searchDao.findRestaurants(distance,lat,lng,foodtype,budget,rating,searchkeyword,veg));
			logger.info("method findRestaurants(distance,lat,lng,foodtype,budget,rating,searchkeyword,veg) executed.");
			return restaurants;
		}catch(Exception exception) {
			logger.error("Exception occured at findRestaurants(distance,lat,lng,foodtype,budget,rating,searchkeyword,veg).", exception);
			throw new DatabaseException("Database connection attempt unsuccessful.",exception);
		}
	}

	@Override
	public List<RestaurantDto> findRestaurants(String foodtype, Double budget, Double rating, String searchkeyword,
			Character veg) throws DatabaseException {
		try {
			List<RestaurantDto> restaurants= restaurantMapper.restaurantsToRestaurantsDto(searchDao.findRestaurants(foodtype,budget,rating,searchkeyword,veg));
			logger.info("method findRestaurants(foodtype,budget,rating,searchkeyword,veg) executed");
			return restaurants;
		}catch(Exception exception) {
			logger.error("Exception occured at findRestaurants(foodtype,budget,rating,searchkeyword,veg).", exception);
			throw new DatabaseException("Database connection attempt unsuccessful.",exception);
		}
	}

}
