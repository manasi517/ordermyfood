package com.mindtree.ordermyfood.sevice;

import com.mindtree.ordermyfood.dto.RequestDto;
import com.mindtree.ordermyfood.entity.Restaurants;

public interface OrderMyFoodService {

	RequestDto loadRestuarantsDetails();

	void loadItemDetails(Restaurants restaurantEntity);

}
