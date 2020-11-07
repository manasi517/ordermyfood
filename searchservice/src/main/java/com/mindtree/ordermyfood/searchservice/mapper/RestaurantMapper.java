package com.mindtree.ordermyfood.searchservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.entity.Restaurants;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RestaurantMapper {

	public List<RestaurantDto> restaurantsToRestaurantsDto(List<Restaurants> findRestaurants);
				

}
