package com.mindtree.ordermyfood.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestDto {

	@JsonProperty
	List<RestaurantDto> restaurants;
	
	@JsonProperty
	private String results_found;
	
	

	public String getResults_found() {
		return results_found;
	}

	public void setResults_found(String results_found) {
		this.results_found = results_found;
	}

	public List<RestaurantDto> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<RestaurantDto> restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "RequestDto [restaurants=" + restaurants + ", results_found=" + results_found + "]";
	}
	
	
}
