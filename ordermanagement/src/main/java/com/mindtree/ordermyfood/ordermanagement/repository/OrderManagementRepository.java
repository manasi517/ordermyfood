package com.mindtree.ordermyfood.ordermanagement.repository;

import java.util.List;

import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.ordermanagement.exception.DatabaseException;

public interface OrderManagementRepository{

	public RestaurantResponseDto getRestaurantDetails(int restaurantId) throws DatabaseException, DataNotFoundException;

	public KafkaResponseDto getRestaurantDetailsForKafka(int restaurantId) throws DatabaseException, DataNotFoundException;
	
	public List<ItemResponseDto> getFoodMenu(int restaurantId) throws DataNotFoundException, DatabaseException;

	public ImagesResponseDto getFoodImages(int restaurantId) throws DataNotFoundException, DatabaseException;

	public List<ReviewsResponseDto> getRestaurantReviews(int restaurantId) throws DataNotFoundException, DatabaseException;

	public List<OfferResponseDto> getRestaurantOffers(int restaurantId) throws DataNotFoundException, DatabaseException;


}
