package com.mindtree.ordermyfood.searchservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Restaurants {

	@Id
	private int resId;
	private String name;
	
	@OneToOne
	@JoinColumn(name="location_id")
	private LocationEntity location;
	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL)
	private List<Item> items;
	
	private double rating;
	private String ratingText;
	private String thumb;
	private String waitTime;
	private long minOrder;
	private double budget;
	private String imageUrl;
	
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

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String image) {
		this.imageUrl = image;
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
	
	
}
