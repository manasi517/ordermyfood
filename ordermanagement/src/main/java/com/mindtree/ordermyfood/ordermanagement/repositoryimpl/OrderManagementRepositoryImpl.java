package com.mindtree.ordermyfood.ordermanagement.repositoryimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.ordermanagement.dao.ItemDAO;
import com.mindtree.ordermyfood.ordermanagement.dao.RestaurantDAO;
import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.entity.Item;
import com.mindtree.ordermyfood.ordermanagement.entity.Restaurant;
import com.mindtree.ordermyfood.ordermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.ordermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.ordermanagement.mapper.RestaurantMapper;
import com.mindtree.ordermyfood.ordermanagement.repository.OrderManagementRepository;

@Repository
public class OrderManagementRepositoryImpl implements OrderManagementRepository {

	@Autowired
	RestaurantDAO restaurantDao;
	
	/*
	 * @Autowired ItemDAO itemDao;
	 */
	
	@Autowired
	RestaurantMapper restaurantMapper;
	
	  
	@Override
	public RestaurantResponseDto getRestaurantDetails(int restaurantId) throws DatabaseException,DataNotFoundException {
	    
		return restaurantMapper.restaurantToRestaurantResponseDto(findRestaurantById(restaurantId));				 
		
	}
	
	@Override
	public KafkaResponseDto getRestaurantDetailsForKafka(int restaurantId) throws DatabaseException,DataNotFoundException {
	    
		Restaurant restaurant =findRestaurantById(restaurantId);
		System.out.println(restaurant.getItems());
		KafkaResponseDto kafkaResponse=restaurantMapper.restaurantToKafkaResponseDto(restaurant);
		/*
		 * kafkaResponse.setItemsId(restaurant.getItems().stream().map(item ->
		 * item.getId()).collect(Collectors.toList()));
		 */
		return kafkaResponse;	 
		
	}

	@Override
	public List<ItemResponseDto> getFoodMenu(int restaurantId) throws DataNotFoundException, DatabaseException {

		List<Item> items = findRestaurantById(restaurantId).getItems();
		if(items == null || items.isEmpty()) {
			throw new DataNotFoundException("No menu available for Restarant Id: "+restaurantId);
		}
		return restaurantMapper.itemToItemResponseDto(items);
		
	}

	@Override
	public ImagesResponseDto getFoodImages(int restaurantId) throws DataNotFoundException, DatabaseException {
		
		return restaurantMapper.restaurantToImagesResponseDto(findRestaurantById(restaurantId));
		
	}
	
	@Override
	public  List<OfferResponseDto> getRestaurantOffers(int restaurantId) throws DataNotFoundException, DatabaseException {
		
		return restaurantMapper.reviewsToOfferResponseDto(findRestaurantById(restaurantId).getOffers());
	}

	@Override
	public List<ReviewsResponseDto> getRestaurantReviews(int restaurantId) throws DataNotFoundException, DatabaseException {
	
		//Restaurant restaurant=restaurantDao.findById(restaurantId);
		 return restaurantMapper.reviewsToReviewResponseDto(findRestaurantById(restaurantId).getReviews());				 
		
	}
  
	private Restaurant findRestaurantById(int restaurantId) throws DataNotFoundException,DatabaseException {		
		Restaurant restaurant = null;
		try {
		restaurant = restaurantDao.findById(restaurantId);
		}catch(Exception exception) {
			throw new DatabaseException("Database connection attempt unsuccessful.",exception);
		}
		if(restaurant == null) {
			throw new DataNotFoundException("Restaurant not found for given Id: "+restaurantId);
		}
		return restaurant;
	}

	
}
