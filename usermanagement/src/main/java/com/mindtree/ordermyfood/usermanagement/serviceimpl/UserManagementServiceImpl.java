package com.mindtree.ordermyfood.usermanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mindtree.ordermyfood.usermanagement.constants.UserContants;
import com.mindtree.ordermyfood.usermanagement.constants.ErrorMessageConstants;
import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.InvalidDataException;
import com.mindtree.ordermyfood.usermanagement.exception.KafkaListnerException;
import com.mindtree.ordermyfood.usermanagement.exception.UserMangementException;
import com.mindtree.ordermyfood.usermanagement.kafka.servicehandler.RestaurantInfoValidator;
import com.mindtree.ordermyfood.usermanagement.repository.UserManagementRepository;
import com.mindtree.ordermyfood.usermanagement.service.UserManagementService;

@Service
public class UserManagementServiceImpl implements UserManagementService{

	@Value("${orderresponse.prefix}")
	String uriPrefix;

	@Autowired
	UserManagementRepository userManagementRepository;

	@Autowired
	RestaurantInfoValidator restaurantValidator;

	@Override
	public ItemResponseDto getPricingDetails(List<Integer> itemIdList) throws UserMangementException {

		try {
			return getResponseDto(userManagementRepository.getPricingDetails(itemIdList));
		} catch(DataNotFoundException exception) {
			throw new UserMangementException(ErrorMessageConstants.DATA_UNAVAILABLE_EXCEPTON, exception);
		}catch (DatabaseException exception) {
			throw new UserMangementException(ErrorMessageConstants.RESOURCE_CONNECTION_EXCEPTON,exception);
		} 
	}


	@Override
	public UserResponseDto userDetails(UserDetailsDto userDetails) throws UserMangementException {

		UserResponseDto userResponseDto;
		try {
			userResponseDto = userManagementRepository.userDetails(userDetails);
		} 	catch(InvalidDataException exception) {
			throw new UserMangementException(ErrorMessageConstants.USER_ANONYMOUS_EXCEPTON, exception);
		}catch (DatabaseException exception) {
			throw new UserMangementException(ErrorMessageConstants.RESOURCE_CONNECTION_EXCEPTON,exception);
		}
		return getResponse(userResponseDto);
	}

	@Override
	public UserResponseDto userLogin(int id) throws UserMangementException  {

		try {
			return userManagementRepository.getRegisteredUser(id);
		}catch(DatabaseException exception) 
		{
			throw new UserMangementException(ErrorMessageConstants.RESOURCE_CONNECTION_EXCEPTON,exception);
		}
		catch(DataNotFoundException exception) {
			throw new UserMangementException(ErrorMessageConstants.DATA_UNAVAILABLE_EXCEPTON, exception);
		}

	}


	@Override
	public OrderResponseDto placeOrder(OrderDetails orderDetails) throws UserMangementException {

		OrderResponseDto responseDto = null;
		try {

			if(restaurantValidator.getRestaurantFromKafka(orderDetails)) {

				responseDto = userManagementRepository.placeOrder(setDeliveryDetails(orderDetails));
			}

			return responseDto;
		}catch(DatabaseException exception) 
		{
			throw new UserMangementException(ErrorMessageConstants.RESOURCE_CONNECTION_EXCEPTON,exception);
		}catch(DataNotFoundException exception)
		{
			throw new UserMangementException(exception.getMessage().toString(), exception);
		}catch (KafkaListnerException exception) 
		{
			throw new UserMangementException(ErrorMessageConstants.DETAILS_FETCH_EXCEPTON,exception);
		}

	}




	private OrderDetails setDeliveryDetails(OrderDetails orderDetails) {
		orderDetails.setDeliveryPersonName("qwert");
		orderDetails.setDeliveryPersonContact("079393"+Math.round(Math.random()*10000));
		return orderDetails;		
	}

	private UserResponseDto getResponse(UserResponseDto userResponseDto) {
		StringBuilder url = new StringBuilder();
		if(userResponseDto.isAnonymousFlag()) {	
			url.append(uriPrefix+UserContants.ORDER_SUFFIX);
		}
		else{
			url.append(uriPrefix+UserContants.LOGIN_SUFFIX);
			url.append(userResponseDto.getId());
		}	
		userResponseDto.setRedirectionUrl(url.toString());
		return userResponseDto;
	}

	private ItemResponseDto getResponseDto(List<ItemDto> listItems) {
		ItemResponseDto itemResponseDto = new ItemResponseDto();
		itemResponseDto.setItems(listItems);
		int price = listItems.stream()
				.mapToInt(item -> item.getPrice())
				.sum();
		double tax=price*0.18;
		itemResponseDto.setTax(tax);
		itemResponseDto.setTotalCost(price+tax);

		return itemResponseDto;		
	}
}
