package com.mindtree.ordermyfood.usermanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDetailsDto {

	private String name;
	@NotNull(message="Please enter anonymousFlag")
	private boolean anonymousFlag;
	@NotEmpty(message="Please enter mailId")
	@Email
	private String mailId;
	@NotNull
	@Pattern(regexp="(^$|[0-9]{10})")
	private String phoneNumber;
    @NotNull(message="Please enter location details")
	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAnonymousFlag() {
		return anonymousFlag;
	}

	public void setAnonymousFlag(boolean anonymousFlag) {
		this.anonymousFlag = anonymousFlag;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	
}
