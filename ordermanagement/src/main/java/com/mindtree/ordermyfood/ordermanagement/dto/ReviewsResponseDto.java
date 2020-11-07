package com.mindtree.ordermyfood.ordermanagement.dto;

public class ReviewsResponseDto {
	
	private double rating ;
    private String review_text ;     
    private String review_time_friendly ;
    private String rating_text ;
    private int likes ;
    private String userName;
   

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
