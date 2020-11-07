package com.mindtree.ordermyfood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRating {

	@JsonProperty("aggregate_rating")
	private String aggrerateRating;
	
	@JsonProperty("rating_text")
	private String ratingText;

	public String getAggrerateRating() {
		return aggrerateRating;
	}

	public void setAggrerateRating(String aggrerateRating) {
		this.aggrerateRating = aggrerateRating;
	}

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}
	
	
}
