package com.mindtree.ordermyfood.searchservice.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.ordermyfood.searchservice.dto.RestaurantDto;
import com.mindtree.ordermyfood.searchservice.exception.ErrorResponse;
import com.mindtree.ordermyfood.searchservice.exception.SearchServiceException;
import com.mindtree.ordermyfood.searchservice.service.SearchService;

@RestController
@Validated
@RequestMapping("/api/v1/restaurant")
public class SearchController {

	private static Logger logger = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	SearchService searchService;

	@GetMapping("/search")
	public @ResponseBody ResponseEntity<List<RestaurantDto>> getRestaurantList(
			@RequestParam (name="distance",required=false) @Positive @Max(15)Double distance,
			@RequestParam (name="latitude",required=false) @Min(-90) @Max(+90) Double latitude,
			@RequestParam (name="longitude",required=false) @Min(-180) @Max(+180)Double longitude,
			@RequestParam (name="foodtype",required=false) String foodtype,
			@RequestParam (name="budget",required=false) @Positive Double budget,
			@RequestParam (name="rating",required=false) @PositiveOrZero @Max(5)Double rating,
			@RequestParam (name="veg",required=false) Character veg,
			@RequestParam (name="searchkeyword",required=false) String searchkeyword) throws SearchServiceException {

		logger.info("method getRestaurantList request param are: {},{},{},{},{},{},{},{}",distance,latitude,longitude,foodtype,budget,rating,veg,searchkeyword);
		
		List <RestaurantDto> restaurants =searchService.getAllRestaurant(distance,latitude,longitude,foodtype,budget,rating,searchkeyword,veg);		
		
		logger.info("method getRestaurantList response size: {}",restaurants.size());
		
		return new ResponseEntity<List<RestaurantDto>>(restaurants,HttpStatus.OK);
	}	
	
	  @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ErrorResponse> onValidationError(Exception ex) {
		  ErrorResponse error = new ErrorResponse("Validation error occured in"+ex.getMessage(), "ERR001", new Timestamp(System.currentTimeMillis()));
		  
	        return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	    }

}
