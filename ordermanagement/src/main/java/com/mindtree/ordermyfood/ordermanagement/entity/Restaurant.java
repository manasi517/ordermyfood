package com.mindtree.ordermyfood.ordermanagement.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Restaurants")
public class Restaurant {

	@Id
	private int resId;
	private String name;
	
	@OneToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	//@JsonIgnore
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<Item> items;
	
	private double rating;
	private String ratingText;
	private String thumb;
	private String waitTime;
	private long minOrder;
	private double budget;
	private String imageUrl;
	
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Photos> photos;

	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Reviews> reviews;
	
	@OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
	private List<Offer> offers;
	

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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
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
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	 
	
}
