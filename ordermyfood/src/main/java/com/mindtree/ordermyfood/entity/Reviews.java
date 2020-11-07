package com.mindtree.ordermyfood.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id  ;
	
	private double rating ;
    private String review_text ; 
    
    private String review_time_friendly ;
    private String rating_text ;
    private int likes ;
    private String userName;
    
    @ManyToOne
	@JoinColumn(name ="res_id")
    private Restaurants restaurant;
    
    
    
	public Restaurants getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReview_time_friendly() {
		return review_time_friendly;
	}
	public void setReview_time_friendly(String review_time_friendly) {
		this.review_time_friendly = review_time_friendly;
	}
	public String getRating_text() {
		return rating_text;
	}
	public void setRating_text(String rating_text) {
		this.rating_text = rating_text;
	}

	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
	
}
