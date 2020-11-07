package com.mindtree.ordermyfood.usermanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserResponseDto {

	private int id;
	
	 @JsonInclude(Include.NON_NULL)
	private boolean anonymousFlag;
	 
	 @JsonInclude(Include.NON_NULL)
	private String redirectionUrl;
	 
	 @JsonInclude(Include.NON_NULL)
	 private String name;
	 
	 private String offer;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isAnonymousFlag() {
		return anonymousFlag;
	}
	public void setAnonymousFlag(boolean anonymousFlag) {
		this.anonymousFlag = anonymousFlag;
	}
	public String getRedirectionUrl() {
		return redirectionUrl;
	}
	public void setRedirectionUrl(String redirectionUrl) {
		this.redirectionUrl = redirectionUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	
	
	
}
