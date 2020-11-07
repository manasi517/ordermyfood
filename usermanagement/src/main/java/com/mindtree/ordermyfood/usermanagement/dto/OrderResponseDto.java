package com.mindtree.ordermyfood.usermanagement.dto;
  
  import java.util.List;
  
  public class OrderResponseDto{
	  
  private boolean anonymousFlag;
  private double totalCost;
  private long phoneNumber;  
  private int customerId; 
  private String deliveryPersonName;
  private String deliveryPersonContact; 
  private boolean payFlag; 
  private List<ItemDto> items;

  
public List<ItemDto> getItems() {
	return items;
}

public void setItems(List<ItemDto> items) {
	this.items = items;
}

public boolean isAnonymousFlag() {
	return anonymousFlag;
}

public void setAnonymousFlag(boolean anonymousFlag) {
	this.anonymousFlag = anonymousFlag;
}

public double getTotalCost() {
	return totalCost;
}

public void setTotalCost(double totalCost) {
	this.totalCost = totalCost;
}

public long getPhoneNumber() {
	return phoneNumber;
}

public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}

public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId) {
	this.customerId = customerId;
}

public String getDeliveryPersonName() {
	return deliveryPersonName;
}

public void setDeliveryPersonName(String deliveryPersonName) {
	this.deliveryPersonName = deliveryPersonName;
}

public String getDeliveryPersonContact() {
	return deliveryPersonContact;
}

public void setDeliveryPersonContact(String deliveryPersonContact) {
	this.deliveryPersonContact = deliveryPersonContact;
}

public boolean isPayFlag() {
	return payFlag;
}

public void setPayFlag(boolean payFlag) {
	this.payFlag = payFlag;
}
  
  }
 