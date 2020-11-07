package com.mindtree.ordermyfood.searchservice.exception;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value=SearchServiceException.class)
	public ResponseEntity<ErrorResponse> subscriptionException(SearchServiceException searchServiceException){
		
		ErrorResponse errorResponse = new ErrorResponse(searchServiceException.getMessage(),"EXCP001",new Timestamp(System.currentTimeMillis()) );
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
