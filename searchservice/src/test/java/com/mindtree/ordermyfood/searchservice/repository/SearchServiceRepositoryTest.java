package com.mindtree.ordermyfood.searchservice.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.ordermyfood.searchservice.dao.SearchDao;
import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.entity.Restaurants;
import com.mindtree.ordermyfood.searchservice.exception.DatabaseException;
import com.mindtree.ordermyfood.searchservice.mapper.RestaurantMapper;
import com.mindtree.ordermyfood.searchservice.mapper.RestaurantMapperImpl;

@RunWith(SpringRunner.class)
public class SearchServiceRepositoryTest{
 
    @TestConfiguration
    static class SearchServiceRepositoryContextConfiguration {
  
        @Bean
        public RestaurantMapper restaurantMapper() {
            return new RestaurantMapperImpl();
        }
        
        @Bean
        public SearchServiceRepository searchServiceRepo(RestaurantMapper mapper) {
            return new SearchServiceRepositoryImpl();
        }
    }
	@MockBean
	SearchDao searchDao;
	
	@Autowired
	SearchServiceRepository searchServiceRepo;
	
	List<Restaurants> restaurant = new ArrayList<Restaurants>();
	
	@Before
	public void before() {
	    
	    Restaurants restaurant1 = new Restaurants();
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
    public void findRestaurantsTestForDistance() throws DatabaseException
    {
        Mockito.when(searchDao.findRestaurants(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble(),
        		Mockito.any(),Mockito.anyDouble(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurant);
        List<RestaurantDto> restaurantDto = searchServiceRepo.findRestaurants(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble(),
        		Mockito.any(),Mockito.anyDouble(), Mockito.any(), Mockito.any(), Mockito.any());
        assertEquals(restaurant.size(), restaurantDto.size());
        assertEquals(restaurant.get(0).getName(), restaurantDto.get(0).getName());
    }
	
	@Test
	public void  findRestaurantsTest() throws DatabaseException {
		Mockito.when(searchDao.findRestaurants(Mockito.any(),Mockito.anyDouble(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurant);
        List<RestaurantDto> restaurantDto = searchServiceRepo.findRestaurants(Mockito.any(),Mockito.anyDouble(), Mockito.any(), Mockito.any(), Mockito.any());
        assertEquals(restaurant.size(), restaurantDto.size());
        assertEquals(restaurant.get(0).getName(), restaurantDto.get(0).getName());
	}


}
