package com.mindtree.ordermyfood.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.mindtree.ordermyfood.entity.Photos;


@Mapper(componentModel = "spring")
public interface RestaurantMapper {

	/*
	 * @Mappings({
	 * 
	 * @Mapping(target="id",source="entity.id"),
	 * 
	 * @Mapping(target="name",source="entity.name"),
	 * 
	 * @Mapping(target="rating",source="entity.user_rating.aggrerateRating"),
	 * 
	 * @Mapping(target="thumb",source="entity.thumb") }) Restaurants
	 * restaurantDtoToRestaurant(RestaurantDto entity);
	 */
	@Mapping(target="userName",source="photos.user.name")
	Photos photosDtoToPhotoEntity(com.mindtree.ordermyfood.dto.Photos photos);
}
