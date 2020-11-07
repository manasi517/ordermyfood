package com.mindtree.ordermyfood.searchservice.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.service.SearchService;

@RunWith(SpringRunner.class)
@WebMvcTest(SearchController.class)
public class SearchServiceControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
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
	public void getRestaurantListTest() throws Exception {
		
		Mockito.when(searchService.getAllRestaurant(Mockito.any(), Mockito.any(), Mockito.any(),Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(restaurant);	
		
		  mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/restaurant/search")
		  .contentType(MediaType.APPLICATION_JSON)) 
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$.*", hasSize(1)));
		 

	}
	
}
