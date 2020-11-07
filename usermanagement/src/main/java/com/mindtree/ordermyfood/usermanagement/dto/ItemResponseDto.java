package com.mindtree.ordermyfood.usermanagement.dto;

import java.util.List;

public class ItemResponseDto {

	private List<ItemDto> items;
	private double totalCost;
	private double tax;
	public List<ItemDto> getItems() {
		return items;
	}
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	
}
