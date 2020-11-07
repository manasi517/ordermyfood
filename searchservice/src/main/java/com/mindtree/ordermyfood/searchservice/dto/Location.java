package com.mindtree.ordermyfood.searchservice.dto;

public class Location {

	private int id;
	private String latitude;
    private String longitude; 
    private String address ;
    
    public Location() {}
    
	public Location(String latitude, String longitude, String address) {
	
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    
    
}
