package com.mindtree.ordermyfood.dto;

public class Review {

	
	private String rating ;
    private String review_text ; 
    private String id  ;
    private String review_time_friendly ;
    private String rating_text ;
    private String timestamp ;
    private String likes ;
    private User user ;
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getReview_text() {
		return review_text;
	}
	public void setReview_text(String review_text) {
		this.review_text = review_text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Review [rating=" + rating + ", review_text=" + review_text + ", id=" + id + ", review_time_friendly="
				+ review_time_friendly + ", rating_text=" + rating_text + ", timestamp=" + timestamp + ", likes="
				+ likes + ", user=" + user + "]";
	}
    
    
}
