package com.mindtree.ordermyfood.ordermanagement.dto;

import java.util.List;

public class KafkaResponseDto {

	private int resId;
	private String name;
	private List<ItemResponseDto> items;
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
	public List<ItemResponseDto> getItems() {
		return items;
	}
	public void setItems(List<ItemResponseDto> items) {
		this.items = items;
	}


	
	
}
