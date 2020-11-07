package com.mindtree.ordermyfood.usermanagement.repository;

import java.util.List;

import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.InvalidDataException;

public interface UserManagementRepository {

	 public List<ItemDto> getPricingDetails(List<Integer> itemList) throws DatabaseException, DataNotFoundException;

	public UserResponseDto userDetails(UserDetailsDto userDetails) throws InvalidDataException, DatabaseException;

	public UserResponseDto getRegisteredUser(int id) throws DatabaseException, DataNotFoundException;

	public OrderResponseDto placeOrder(OrderDetails orederDetails) throws DatabaseException, DataNotFoundException;

}
