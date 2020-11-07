package com.mindtree.ordermyfood.ordermanagement.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.ordermyfood.ordermanagement.dto.ImagesResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.OfferResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.ReviewsResponseDto;
import com.mindtree.ordermyfood.ordermanagement.exception.ErrorResponse;
import com.mindtree.ordermyfood.ordermanagement.exception.OrderMangementException;
import com.mindtree.ordermyfood.ordermanagement.service.OrderManagementService;

@RestController
@Validated
@RequestMapping("/api/v1/display")
public class OrderManagementController {

	private static Logger logger = LoggerFactory.getLogger(OrderManagementController.class);
	
	@Autowired
	OrderManagementService orderManagementService;

	@GetMapping("/restaurant/{id}")
	@ResponseBody
	public ResponseEntity<RestaurantResponseDto> getRestaurantDetials(@PathVariable(name="id") @Positive int restaurantId) throws OrderMangementException {
    
		logger.info("method getRestaurantDetials request id:{}",restaurantId);
		
		RestaurantResponseDto restaurant =orderManagementService.getRestaurantDetails(restaurantId);
		
		logger.info("method getRestaurantDetials response: {}",restaurant);
		return new ResponseEntity<RestaurantResponseDto>(restaurant, HttpStatus.OK);
	}
	

	@GetMapping("/foodimage/{id}")
	@ResponseBody
	public ResponseEntity<ImagesResponseDto> getFoodImages(@PathVariable(name="id") @Positive int restaurantId) throws OrderMangementException {

		return new ResponseEntity<ImagesResponseDto>(orderManagementService.getFoodImages(restaurantId), HttpStatus.OK); 

	}

	
	  @GetMapping("/offer/{id}") 
	  @ResponseBody
	  public ResponseEntity<List<OfferResponseDto>> getRestaurantOffer(@PathVariable(name="id") @Positive int restaurantId) throws OrderMangementException {	
		  return new ResponseEntity<List<OfferResponseDto>>(orderManagementService.getRestaurantOffer(restaurantId), HttpStatus.OK);   
	  }
	 

	@GetMapping("/menu/{id}")
	@ResponseBody
	public ResponseEntity<List<ItemResponseDto>> getFoodMenu(@PathVariable(name="id") @Positive int restaurantId) throws OrderMangementException {

		return new ResponseEntity<List<ItemResponseDto>>(orderManagementService.getFoodMenu(restaurantId), HttpStatus.OK);
	}


	@GetMapping("/review/{id}")
	@ResponseBody
	public ResponseEntity<List<ReviewsResponseDto>> getRestaurentReiew(@PathVariable(name="id") @Positive int restaurantId) throws OrderMangementException {
		return new ResponseEntity<List<ReviewsResponseDto>>(orderManagementService.getRestaurantReviews(restaurantId), HttpStatus.OK);
		}

	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onValidationError(Exception ex) {
	  ErrorResponse error = new ErrorResponse(ex.getMessage(), "ERR001", new Timestamp(System.currentTimeMillis()));
	  
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }
}
