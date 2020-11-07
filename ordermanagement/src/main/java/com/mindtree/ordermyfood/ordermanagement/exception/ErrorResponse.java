package com.mindtree.ordermyfood.ordermanagement.exception;

import java.sql.Timestamp;

public class ErrorResponse {

	private String errorMessage;
	
	private String errorCode;
	
	private Timestamp timestamp;

	public ErrorResponse(String errorMessage, String errorCode,Timestamp timestamp) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.timestamp = timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
