package com.mindtree.ordermyfood.ordermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.entity.Restaurant;
import com.mindtree.ordermyfood.ordermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.ordermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.ordermanagement.exception.OrderMangementException;
import com.mindtree.ordermyfood.ordermanagement.repository.OrderManagementRepository;
import com.mindtree.ordermyfood.ordermanagement.service.OrderManagementService;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

	@Autowired
	OrderManagementRepository orderManagementRepo;
	
	@Override
	public RestaurantResponseDto getRestaurantDetails(int restaurantId) throws OrderMangementException {
		try {
			return orderManagementRepo.getRestaurantDetails(restaurantId);
		} catch(DataNotFoundException exception) {
			throw new OrderMangementException("Requested data is missing.", exception);
		}catch (DatabaseException exception) {
			throw new OrderMangementException("Can not connect to resource",exception);
		} 
		
	}
	
	@Override
	public KafkaResponseDto getRestaurantDetailsForKafka(int restaurantId) throws OrderMangementException {
		try {
			return orderManagementRepo.getRestaurantDetailsForKafka(restaurantId);
		} catch(DataNotFoundException exception) {
			throw new OrderMangementException("Requested data is missing.", exception);
		}catch (DatabaseException exception) {
			throw new OrderMangementException("Can not connect to resource",exception);
		} 
	}

	@Override
	public ImagesResponseDto getFoodImages(int restaurantId) throws OrderMangementException {
		try {
		return orderManagementRepo.getFoodImages(restaurantId);
	} catch(DataNotFoundException exception) {
		throw new OrderMangementException("Requested data is missing.", exception);
	}catch (DatabaseException exception) {
		throw new OrderMangementException("Can not connect to resource",exception);
	} 
		
	}


	@Override
	public List<ReviewsResponseDto> getRestaurantReviews(int restaurantId) throws OrderMangementException {
		try {
		return orderManagementRepo.getRestaurantReviews(restaurantId);
	} catch(DataNotFoundException exception) {
		throw new OrderMangementException("Requested data is missing.", exception);
	}catch (DatabaseException exception) {
		throw new OrderMangementException("Can not connect to resource",exception);
	} 
	}

	@Override
	public List<OfferResponseDto> getRestaurantOffer(int restaurantId) throws OrderMangementException {
		try {
		return orderManagementRepo.getRestaurantOffers(restaurantId);
	} catch(DataNotFoundException exception) {
		throw new OrderMangementException("Requested data is missing.", exception);
	}catch (DatabaseException exception) {
		throw new OrderMangementException("Can not connect to resource",exception);
	} 
		
	}

	@Override
	public List<ItemResponseDto> getFoodMenu(int restaurantId) throws OrderMangementException {
		try {
			return orderManagementRepo.getFoodMenu(restaurantId);
		} catch(DataNotFoundException exception) {
			throw new OrderMangementException("Requested data is missing.", exception);
		}catch (DatabaseException exception) {
			throw new OrderMangementException("Can not connect to resource",exception);
		} 
		
	}


}
