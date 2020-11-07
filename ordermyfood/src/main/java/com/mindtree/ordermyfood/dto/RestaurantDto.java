package com.mindtree.ordermyfood.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;


@JsonTypeName("restaurant")                                                                                         
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME)
public class RestaurantDto  {

    private int id ;
    private String name ;
    private Location location ; 
    private double average_cost_for_two ;
    private String thumb ;
    private String photos_url ; 
   
    private UserRating user_rating;
    private String phone_numbers;
    @JsonIgnore
    private List<Review> all_reviews;
    private List<Photos> photos;
    
    
    
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public double getAverage_cost_for_two() {
		return average_cost_for_two;
	}
	public void setAverage_cost_for_two(double average_cost_for_two) {
		this.average_cost_for_two = average_cost_for_two;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getPhotos_url() {
		return photos_url;
	}
	public void setPhotos_url(String photos_url) {
		this.photos_url = photos_url;
	}
	public UserRating getUser_rating() {
		return user_rating;
	}
	public void setUser_rating(UserRating user_rating) {
		this.user_rating = user_rating;
	}
	public String getPhone_numbers() {
		return phone_numbers;
	}
	public void setPhone_numbers(String phone_numbers) {
		this.phone_numbers = phone_numbers;
	}
	public List<Review> getAll_reviews() {
		return all_reviews;
	}
	public void setAll_reviews(List<Review> all_reviews) {
		this.all_reviews = all_reviews;
	}
	@Override
	public String toString() {
		return "RestaurantDto [id=" + id + ", name=" + name + ", location=" + location + ", average_cost_for_two="
				+ average_cost_for_two + ", thumb=" + thumb + ", photos_url=" + photos_url + ", user_rating="
				+ user_rating + ", phone_numbers=" + phone_numbers + ", all_reviews=" + all_reviews + "]";
	}  
    
    
	
	/*
	 * private Restaurant restaurant;
	 * 
	 * public Restaurant getRestaurant() { return restaurant; }
	 * 
	 * public void setRestaurant(Restaurant restaurant) { this.restaurant =
	 * restaurant; }
	 */
	
	
}
