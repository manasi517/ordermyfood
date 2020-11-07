package com.mindtree.ordermyfood.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderSummary {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	private boolean anonymousFlag;
	private double totalCost;
	private String email;
	private long phoneNumber;
	@ManyToOne
	@JoinColumn(name="cust_id")
	private Customer customer;
	private String deliveryPersonName;
	private String deliveryPersonContact;
	@ManyToOne
	@JoinColumn(name="rest_id")
	private Restaurants restaurantId;
	private boolean payFlag;
	 
	/*
	 * @OneToMany(targetEntity=Item.class,cascade = CascadeType.ALL, fetch =
	 * FetchType.LAZY, orphanRemoval = true)
	 */
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "order_summary_items",
            joinColumns = {@JoinColumn(name = "order_summary_id")},
            inverseJoinColumns = {@JoinColumn(name = "items_id")}
    )
    private List<Item> items;
    
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public Restaurants getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Restaurants restaurantId) {
		this.restaurantId = restaurantId;
	}
	public boolean isPayFlag() {
		return payFlag;
	}
	public void setPayFlag(boolean payFlag) {
		this.payFlag = payFlag;
	}
	
	
}
