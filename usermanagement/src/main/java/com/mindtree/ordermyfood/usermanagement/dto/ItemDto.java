package com.mindtree.ordermyfood.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemDto {
	
	@JsonProperty("Item Id")
	private int id;
	@JsonProperty("Item Name")
	private String name;
	@JsonIgnore
	private int price;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Item Id=" + id + ", Item Name=" + name + "";
	}
	
	
}
