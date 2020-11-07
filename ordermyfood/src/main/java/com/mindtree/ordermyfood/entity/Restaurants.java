package com.mindtree.ordermyfood.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mindtree.ordermyfood.dto.UserRating;

@Entity
public class Restaurants {

	@Id
	private int resId;
	private String name;
	
	@OneToOne
	@JoinColumn(name="location_id")
	private LocationEntity location;
	
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Item> items;
	
	private double rating;
	private String ratingText;
	private String thumb;
	private String waitTime;
	private int minOrder;
	private double budget;
	private String imageUrl;
	
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Photos> photos;

	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Reviews> reviews;
	
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Offer> offers;
	
	
	public Restaurants() {};
	
	public Restaurants(int id, String nameRest, UserRating user_rating,
			String thumbRest, double average_cost_for_two,LocationEntity location, String imageUrl) {
		this.resId = id;
		this.name = nameRest;
		this.rating = Double.parseDouble(user_rating.getAggrerateRating());
		this.ratingText=user_rating.getRatingText();
		this.thumb = thumbRest;
		this.budget = average_cost_for_two;
		this.location=location;
		this.minOrder=(int) (average_cost_for_two/2);
		this.waitTime="30 min";
		this.imageUrl=imageUrl;
	}
	
	
	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setMinOrder(int minOrder) {
		this.minOrder = minOrder;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocationEntity getLocation() {
		return location;
	}
	public void setLocation(LocationEntity location) {
		this.location = location;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
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
	public int getMinOrder() {
		return minOrder;
	}

	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getRatingText() {
		return ratingText;
	}

	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
}
