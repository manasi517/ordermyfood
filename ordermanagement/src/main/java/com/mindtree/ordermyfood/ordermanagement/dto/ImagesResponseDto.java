package com.mindtree.ordermyfood.ordermanagement.dto;

import java.util.List;

public class ImagesResponseDto {

	private String thumbUrl;
	private String imageUrl;
	private List<Photos> photos;
	
	
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
}
