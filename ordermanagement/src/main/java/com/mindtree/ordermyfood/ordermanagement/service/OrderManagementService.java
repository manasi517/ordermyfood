package com.mindtree.ordermyfood.ordermanagement.service;

import java.util.List;

import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.exception.OrderMangementException;

public interface OrderManagementService {

	public RestaurantResponseDto getRestaurantDetails(int restaurantId) throws OrderMangementException;
	
	public KafkaResponseDto getRestaurantDetailsForKafka(int restaurantId) throws OrderMangementException;
	
	public ImagesResponseDto getFoodImages(int restaurantId) throws OrderMangementException;

	List<ItemResponseDto> getFoodMenu(int restaurantId) throws OrderMangementException;

	List<ReviewsResponseDto> getRestaurantReviews(int restaurantId) throws OrderMangementException;

	List<OfferResponseDto> getRestaurantOffer(int restaurantId) throws OrderMangementException;

}
