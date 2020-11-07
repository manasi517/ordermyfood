package com.mindtree.ordermyfood.usermanagement.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.RestaurantDto;

@FeignClient(name = "order-management-service")
	public interface FeignServiceClient {
	        
	 @GetMapping("/api/v1/display/restaurant/{id}")
	 public RestaurantDto getRestaurantDetails(@PathVariable(value="id") int id);
	 
	 @GetMapping("/api/v1/display/menu/{id}")
	 public List<ItemDto> getItemDetails(@PathVariable(value="id") int id);
	 
	}


	
