package com.mindtree.ordermyfood.usermanagement.repositoryimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.usermanagement.constants.ErrorMessageConstants;
import com.mindtree.ordermyfood.usermanagement.dao.ItemManagementDao;
import com.mindtree.ordermyfood.usermanagement.dao.OrderManagementDao;
import com.mindtree.ordermyfood.usermanagement.dao.UserManagementDao;
import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.Customer;
import com.mindtree.ordermyfood.usermanagement.entity.Item;
import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.InvalidDataException;
import com.mindtree.ordermyfood.usermanagement.mapper.ItemMapper;
import com.mindtree.ordermyfood.usermanagement.mapper.OrderSummaryMapper;
import com.mindtree.ordermyfood.usermanagement.mapper.UserMapper;
import com.mindtree.ordermyfood.usermanagement.repository.UserManagementRepository;

@Repository
public class UserManagementRepositoryImpl implements UserManagementRepository{

	@Autowired
	ItemManagementDao itemManagementDao;

	@Autowired
	UserManagementDao userManagementDao;

	@Autowired
	OrderManagementDao orderManagementDao;

	@Autowired
	ItemMapper itemMapper;

	@Autowired
	OrderSummaryMapper orderSummaryMapper;

	@Autowired
	UserMapper userMapper;


	@Override
	public List<ItemDto> getPricingDetails(List<Integer> itemIdList) throws DatabaseException,DataNotFoundException {	

        return itemMapper.itemToItemDto(getItemsById(itemIdList));
        
	}
	@Override
	public UserResponseDto userDetails(UserDetailsDto userDto) throws InvalidDataException,DatabaseException {
		Customer userDetails=userMapper.userDetailsDtotoCustomer(userDto);
		Customer customerDetails=null;

		try {
			customerDetails=userManagementDao.findByMailId(userDetails.getMailId());
		}catch(Exception exception) {
			throw new DatabaseException(ErrorMessageConstants.DATABASE_CONNECTION_EXCEPTON,exception);
		}

		if(null !=customerDetails && customerDetails.isAnonymousFlag()==userDetails.isAnonymousFlag()){

			userDetails = mappingUserDetailsToCustomerDetails(userDetails,customerDetails);
			return userMapper.customerToUserResponseDto(saveUserDeatils(userDetails));
		}

		else if(null !=customerDetails && customerDetails.isAnonymousFlag()!=userDetails.isAnonymousFlag()){
			throw new InvalidDataException(ErrorMessageConstants.MISMATCH_ANANYMOUS_FLAG_EXCEPTON);
		}

		return userMapper.customerToUserResponseDto(saveUserDeatils(userDetails));
	}

	@Override
	public UserResponseDto getRegisteredUser(int id) throws DatabaseException, DataNotFoundException{

		Customer customer =null;
		try {
			customer = userManagementDao.findById(id);
		}catch(Exception exception) {
			throw new DatabaseException(ErrorMessageConstants.DATABASE_CONNECTION_EXCEPTON, exception);
		}
		if(customer==null) {
			throw new DataNotFoundException(ErrorMessageConstants.USER_UNAVAILABLE_EXCEPTON+id);
		}
		return userMapper.customerToUserResponseDto(userManagementDao.findById(id));
	}


	@Override
	public OrderResponseDto placeOrder(OrderDetails orderDetails) throws DatabaseException, DataNotFoundException {
		
		OrderSummary order=orderSummaryMapper.orderDetailsToOrderSummary(orderDetails);
		//order.setItems(getItemsById(orderDetails.getItemsId()));
		try {
		return (orderSummaryMapper.orderSummaryToOrderResponseDto(orderManagementDao.saveAndFlush(order)));
		}catch(Exception exception) {
			throw new DatabaseException(ErrorMessageConstants.DATABASE_CONNECTION_EXCEPTON, exception);
		}
	}


	private Customer mappingUserDetailsToCustomerDetails(Customer userDetails, Customer customerDetails) {
		userDetails.setId(customerDetails.getId());
		userDetails.getLocation().setId(customerDetails.getLocation().getId());
		return userDetails;

	}

	private Customer saveUserDeatils(Customer userDetails) throws DatabaseException {
		try {
			return userManagementDao.saveAndFlush(userDetails);
		}catch(Exception exception) {
			throw new DatabaseException(ErrorMessageConstants.DATABASE_CONNECTION_EXCEPTON, exception);
		}
	}
	
	private List<Item> getItemsById(List<Integer> itemIdList) throws DatabaseException, DataNotFoundException {
		List<Item> items = null;
		try {
			items = itemManagementDao.findAllByIdIn(itemIdList);
		}catch(Exception exception) {
			throw new DatabaseException(ErrorMessageConstants.DATABASE_CONNECTION_EXCEPTON,exception);
		}
		if(items == null || items.isEmpty()) {
			throw new DataNotFoundException(ErrorMessageConstants.ITEM_UNAVAILABLE_EXCEPTON+itemIdList);
		}
		List<Item> unavailableItems =null;
		if(itemIdList.size() != items.size()) {
			unavailableItems = items.stream()
					.filter(item -> !itemIdList.contains(item.getId()))
					.collect(Collectors.toList());
		}

		if(unavailableItems != null) {
			throw new DataNotFoundException(ErrorMessageConstants.ITEM_UNAVAILABLE_EXCEPTON+unavailableItems);
		}
		return items;
	}



}
