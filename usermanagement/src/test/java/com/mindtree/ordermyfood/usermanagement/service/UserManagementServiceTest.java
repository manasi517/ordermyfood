package com.mindtree.ordermyfood.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.Customer;
import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.InvalidDataException;
import com.mindtree.ordermyfood.usermanagement.exception.UserMangementException;
import com.mindtree.ordermyfood.usermanagement.kafka.servicehandler.RestaurantInfoValidator;
import com.mindtree.ordermyfood.usermanagement.repository.UserManagementRepository;
import com.mindtree.ordermyfood.usermanagement.serviceimpl.UserManagementServiceImpl;

@RunWith(SpringRunner.class)
public class UserManagementServiceTest{
 
    @TestConfiguration
    static class OrderManagementServiceTestContextConfiguration {
       
        @Bean
        public UserManagementService userManagementService() {
            return new UserManagementServiceImpl();
        }
    }
    
	@MockBean
	UserManagementRepository userManagementRepository;
	
	@MockBean
	RestaurantInfoValidator restaurantValidator;

	
	@Autowired
	UserManagementService userManagementService;
	
	Customer customerDetails = new Customer();
	UserResponseDto userResponse = new UserResponseDto();
	OrderSummary orderSummary = new OrderSummary();
	List<ItemDto> items = new ArrayList<ItemDto>() ;
	List<Integer> item = new ArrayList<Integer>() ;
	UserDetailsDto user = new UserDetailsDto();
	
	@Before
	public void before() {
	    
		ItemDto item1= new ItemDto();
		item1.setId(1);
		item1.setName("Kaju curry");
		item1.setPrice(137);
		items.add(item1);
		
		item.add(1);
		
		customerDetails.setId(1);
		customerDetails.setName("sampat");
		
		userResponse.setName("sampat");
		userResponse.setId(3421);
	    
		orderSummary.setTotalCost(200.0);
		orderSummary.setPhoneNumber(98764311);
	    
	}
	
	@Test
    public void getPricingDetailsTest() throws DatabaseException, DataNotFoundException
    {
        Mockito.when(userManagementRepository.getPricingDetails(Mockito.anyList())).thenReturn(items);
        List<ItemDto> itemDto = userManagementRepository.getPricingDetails(item);
        assertEquals(items.size(), itemDto.size());
        assertEquals(items.get(0).getName(), itemDto.get(0).getName());
        assertEquals(items.get(0).getPrice(), itemDto.get(0).getPrice());
    }
	
	@Test(expected=UserMangementException.class)
    public void getPricingDetailsTestForException() throws DatabaseException, DataNotFoundException, UserMangementException
    {
        Mockito.when(userManagementRepository.getPricingDetails(Mockito.anyList())).thenThrow(DatabaseException.class);
        userManagementService.getPricingDetails(Mockito.anyListOf(Integer.class));
    }

	
	@Test
	public void userDetailsTest() throws UserMangementException, InvalidDataException, DatabaseException {
		Mockito.when(userManagementRepository.userDetails(Mockito.any())).thenReturn(userResponse);
		UserResponseDto userResponseDto = userManagementService.userDetails(Mockito.any());
		assertEquals(userResponse.getId(), userResponseDto.getId());
        assertEquals(userResponse.getName(), userResponseDto.getName());
	}

	@Test
	public void userLoginTest() throws  UserMangementException, DatabaseException, DataNotFoundException {
		Mockito.when(userManagementRepository.getRegisteredUser(Mockito.anyInt())).thenReturn(userResponse);
		UserResponseDto userResponseDto = userManagementService.userLogin(Mockito.anyInt());
		assertEquals(userResponse.getId(), userResponseDto.getId());
        assertEquals(userResponse.getName(), userResponseDto.getName());
	}

	@Test(expected=UserMangementException.class)
	public void userLoginTestException() throws DatabaseException, DataNotFoundException, UserMangementException {
		Mockito.when(userManagementRepository.getRegisteredUser(Mockito.anyInt())).thenThrow(DatabaseException.class);
		userManagementService.userLogin(Mockito.anyInt());	
	}
	
	

}
