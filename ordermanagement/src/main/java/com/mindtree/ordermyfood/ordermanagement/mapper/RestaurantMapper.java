package com.mindtree.ordermyfood.ordermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.entity.Item;
import com.mindtree.ordermyfood.ordermanagement.entity.Offer;
import com.mindtree.ordermyfood.ordermanagement.entity.Restaurant;
import com.mindtree.ordermyfood.ordermanagement.entity.Reviews;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

	
	RestaurantResponseDto restaurantToRestaurantResponseDto(Restaurant entity);
	
	KafkaResponseDto restaurantToKafkaResponseDto(Restaurant entity);

	
	//List<com.mindtree.ordermyfood.ordermanagement.dto.Photos> photosToPhotosDto(List<Photos> photos);

	List<ItemResponseDto> itemToItemResponseDto(List<Item> list);

	List<ReviewsResponseDto> reviewsToReviewResponseDto(List<Reviews> list);
	
	  @Mappings({	  
	  @Mapping(target="thumbUrl",source="restaurant.thumb"),	  
	  @Mapping(target="imageUrl",source="restaurant.imageUrl"),	  
	  @Mapping(target="photos",source="restaurant.photos")})	 
	ImagesResponseDto restaurantToImagesResponseDto(Restaurant restaurant);


	List<OfferResponseDto> reviewsToOfferResponseDto(List<Offer> offers);
}
