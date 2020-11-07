package com.mindtree.ordermyfood.usermanagement.service;

import java.util.List;
import java.util.Map;

import com.mindtree.ordermyfood.usermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.KafkaListnerException;
import com.mindtree.ordermyfood.usermanagement.exception.UserMangementException;

public interface UserManagementService {

	public ItemResponseDto getPricingDetails(List<Integer> itemList) throws UserMangementException;

	public UserResponseDto userDetails(UserDetailsDto userDetails) throws UserMangementException;

	public UserResponseDto userLogin(int id) throws UserMangementException;

	public OrderResponseDto placeOrder(OrderDetails orederDetails) throws UserMangementException;

}
