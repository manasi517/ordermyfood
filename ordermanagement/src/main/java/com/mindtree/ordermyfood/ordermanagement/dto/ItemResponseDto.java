package com.mindtree.ordermyfood.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemResponseDto {

	@JsonProperty("Item Id")
	private int id;
	@JsonProperty("Item Name")
	private String name;	
	private boolean vegFlag;
	private String type;
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
