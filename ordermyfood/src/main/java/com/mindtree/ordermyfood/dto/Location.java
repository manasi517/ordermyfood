package com.mindtree.ordermyfood.dto;

public class Location {

	 private String address ;
     private String locality;
     private String city;
     private String latitude;
     private String longitude; 
     private String zipcode;
     private String country_id;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	@Override
	public String toString() {
		return "Location [address=" + address + ", locality=" + locality + ", city=" + city + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", zipcode=" + zipcode + ", country_id=" + country_id + "]";
	}
     
     
}
