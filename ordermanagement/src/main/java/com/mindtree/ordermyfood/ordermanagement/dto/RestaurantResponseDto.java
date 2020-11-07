package com.mindtree.ordermyfood.ordermanagement.dto;

public class RestaurantResponseDto {

	private int resId;
	private String name;
	private LocationResponseDto location;	
	private double rating;
	private String ratingText;
	private String thumb;
	private String waitTime;
	private long minOrder;
	private double budget;
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocationResponseDto getLocation() {
		return location;
	}
	public void setLocation(LocationResponseDto location) {
		this.location = location;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getRatingText() {
		return ratingText;
	}
	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	public long getMinOrder() {
		return minOrder;
	}
	public void setMinOrder(long minOrder) {
		this.minOrder = minOrder;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	
	
}
