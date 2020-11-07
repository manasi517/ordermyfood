package com.mindtree.ordermyfood.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name ="res_id")
	private Restaurants restaurant;
	
	private Character vegFlag;
	private String type;
	private int price;
	
	@ManyToMany(mappedBy="items")
	private List<OrderSummary> orderSummanry;
	
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
	public Character isVegFlag() {
		return vegFlag;
	}
	public void setVegFlag(Character vegFlag) {
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
	public List<OrderSummary> getOrderSummanry() {
		return orderSummanry;
	}
	public void setOrderSummanry(List<OrderSummary> orderSummanry) {
		this.orderSummanry = orderSummanry;
	}
	public Character getVegFlag() {
		return vegFlag;
	}
	
	
}
