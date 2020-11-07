package com.mindtree.ordermyfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mindtree.ordermyfood.dto.RequestDto;
import com.mindtree.ordermyfood.sevice.OrderMyFoodService;

@Controller
public class OrderMyFoodController {

	@Autowired
	OrderMyFoodService service;
	
	@GetMapping("/Restaurants")
	public ResponseEntity<RequestDto> loadRestaurants() {
	
		RequestDto restaurants=service.loadRestuarantsDetails();
		return new ResponseEntity<RequestDto>(restaurants, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/Items") public ResponseEntity<RequestDto> loadItems() {
	 * 
	 * RequestDto restaurants=service.loadItemDetails(); return new
	 * ResponseEntity<RequestDto>(restaurants, HttpStatus.OK); }
	 */
}
