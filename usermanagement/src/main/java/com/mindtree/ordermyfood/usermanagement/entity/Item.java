package com.mindtree.ordermyfood.usermanagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Items")
public class Item {

	@Id
	private int id;
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="res_id")
	private Restaurants restaurant;
	
	private boolean vegFlag;
	private String type;
	private int price;
	
	@ManyToMany(mappedBy="items")
	private List<OrderSummary> orderSummary;
	
	
	
	public List<OrderSummary> getOrderSummanry() {
		return orderSummary;
	}
	public void setOrderSummanry(List<OrderSummary> orderSummary) {
		this.orderSummary = orderSummary;
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
	public Restaurants getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}
	public boolean isVegFlag() {
		return vegFlag;
	}
	public void setVegFlag(boolean vegFlag) {
		this.vegFlag = vegFlag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
