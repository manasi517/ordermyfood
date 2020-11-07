package com.mindtree.ordermyfood.ordermanagement.dto;

public class Photos {

	private int id;
	private String url;
	private String thumb_url;
	private String friendly_time;
	private String userName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumb_url() {
		return thumb_url;
	}
	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}
	public String getFriendly_time() {
		return friendly_time;
	}
	public void setFriendly_time(String friendly_time) {
		this.friendly_time = friendly_time;
	}
	
	
}
