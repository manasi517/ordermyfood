package com.mindtree.ordermyfood.searchservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;
import com.mindtree.ordermyfood.searchservice.exception.SearchServiceException;
import com.mindtree.ordermyfood.searchservice.repository.SearchServiceRepository;

@RunWith(SpringRunner.class)
public class SearchServiceTest {

    @TestConfiguration
    static class SearchServiceTestContextConfiguration {
         
        @Bean
        public SearchService searchServiceRepo() {
            return new SearchServiceImpl();
        }
    }
    
    @MockBean
    SearchServiceRepository searchServiceRepo;
    
    @Autowired
    SearchService searchService;
	
	List<RestaurantDto> restaurant = new ArrayList<RestaurantDto>();
	
	@Before
	public void before() {
	    
		RestaurantDto restaurant1 = new RestaurantDto();
	    restaurant1.setResId(50474);
	    restaurant1.setBudget(800);
	    restaurant1.setName("Empire Restaurant");
	    restaurant1.setImageUrl("https://www.zomato.com/bangalore/empire-restaurant-koramangala-5th-block/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop");
	    restaurant1.setWaitTime("30 min");
	    restaurant1.setRating(4.5);
	    restaurant1.setRatingText("Excellent");
	    restaurant.add(restaurant1);
	}
    
    @Test
    public void getAllRestaurantTest() throws SearchServiceException, DatabaseException {
    	
    	Mockito.when(searchServiceRepo.findRestaurants(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurant);
    	List<RestaurantDto> restaurantDto =searchService.getAllRestaurant(null, null, null, Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any());
    	assertEquals(restaurant.size(), restaurantDto.size());
        assertEquals(restaurant.get(0).getName(), restaurantDto.get(0).getName());
    }
    
    @Test
    public void getAllRestaurantTestForDistance() throws SearchServiceException, DatabaseException {
    	
    	Mockito.when(searchServiceRepo.findRestaurants(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurant);
    	List<RestaurantDto> restaurantDto =searchService.getAllRestaurant(Mockito.anyDouble(), Mockito.anyDouble(),Mockito.anyDouble(), Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any());
    	assertEquals(restaurant.size(), restaurantDto.size());
        assertEquals(restaurant.get(0).getName(), restaurantDto.get(0).getName());
    }
    
    @Test(expected= SearchServiceException.class )
    public void getAllRestaurantTestException() throws SearchServiceException, DatabaseException {
    	
    	Mockito.when(searchServiceRepo.findRestaurants(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenThrow(DatabaseException.class);
    	searchService.getAllRestaurant(Mockito.anyDouble(), Mockito.anyDouble(),Mockito.anyDouble(), Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any());

    }
    
}
