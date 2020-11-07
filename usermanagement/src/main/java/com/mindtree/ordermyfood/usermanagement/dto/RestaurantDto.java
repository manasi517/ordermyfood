package com.mindtree.ordermyfood.usermanagement.dto;

import java.util.List;

public class RestaurantDto {

	private int resId;
	private String name;
	private List<ItemDto> items;
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
	public List<ItemDto> getItems() {
		return items;
	}
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}


	
	
}
