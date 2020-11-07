package com.mindtree.ordermyfood.usermanagement.repository;

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


import com.mindtree.ordermyfood.usermanagement.dao.ItemManagementDao;
import com.mindtree.ordermyfood.usermanagement.dao.OrderManagementDao;
import com.mindtree.ordermyfood.usermanagement.dao.UserManagementDao;
import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.Customer;
import com.mindtree.ordermyfood.usermanagement.entity.Item;
import com.mindtree.ordermyfood.usermanagement.entity.Location;
import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.DatabaseException;
import com.mindtree.ordermyfood.usermanagement.exception.InvalidDataException;
import com.mindtree.ordermyfood.usermanagement.mapper.ItemMapper;
import com.mindtree.ordermyfood.usermanagement.mapper.ItemMapperImpl;
import com.mindtree.ordermyfood.usermanagement.mapper.OrderSummaryMapper;
import com.mindtree.ordermyfood.usermanagement.mapper.OrderSummaryMapperImpl;
import com.mindtree.ordermyfood.usermanagement.mapper.UserMapper;
import com.mindtree.ordermyfood.usermanagement.mapper.UserMapperImpl;
import com.mindtree.ordermyfood.usermanagement.repositoryimpl.UserManagementRepositoryImpl;


@RunWith(SpringRunner.class)
public class UserManagementRepositoryTest{
 
    @TestConfiguration
    static class UserManagementRepositoryTestContextConfiguration {
  
        @Bean
        public ItemMapper itemMapper() {
            return new ItemMapperImpl();
        }
        
        @Bean
        public OrderSummaryMapper orderSummaryMapper() {
            return new OrderSummaryMapperImpl();
        }
        
        @Bean
        public UserMapper userMapper() {
            return new UserMapperImpl();
        }
        
        @Bean
        public UserManagementRepository searchServiceRepo(ItemMapper itemMapper,OrderSummaryMapper orderSummaryMapper,UserMapper userMapper) {
            return new UserManagementRepositoryImpl();
        }
    }
    
	@MockBean
	ItemManagementDao itemManagementDao;
	
	@MockBean
	OrderManagementDao orderManagementDao;
	
	@MockBean
	UserManagementDao userManagementDao;
	
	@Autowired
	UserManagementRepository userManagementRepository;
	
	Customer customerDetails = new Customer();
	Location location = new Location();
	OrderSummary orderSummary = new OrderSummary();
	List<Item> items = new ArrayList<Item>() ;
	List<Integer> item = new ArrayList<Integer>() ;
	UserDetailsDto user = new UserDetailsDto();
	com.mindtree.ordermyfood.usermanagement.dto.Location locationDto = new com.mindtree.ordermyfood.usermanagement.dto.Location();

	@Before
	public void before() {
	    
		Item item1= new Item();
		item1.setId(1);
		item1.setName("Kaju curry");
		item1.setPrice(137);
		item1.setType("Indian");
		items.add(item1);
		
		item.add(1);
		
		location.setId(1);
		location.setAddress("dummy address");
		
		customerDetails.setId(1);
		customerDetails.setName("sampat");
		customerDetails.setLocation(location);
		
		user.setName("sampat");
		user.setLocation(locationDto);
	    
		orderSummary.setTotalCost(200.0);
		orderSummary.setPhoneNumber(98764311);
	    
		/*
		 * restaurant.setResId(50474); restaurant.setBudget(800);
		 * restaurant.setName("Empire Restaurant"); restaurant.setImageUrl(
		 * "https://www.zomato.com/bangalore/empire-restaurant-koramangala-5th-block/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop"
		 * ); restaurant.setThumb(
		 * "https://b.zmtcdn.com/data/pictures/1/50471/bcf68da39dcfb0fe5bcfb742c337385e.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A"
		 * ); restaurant.setWaitTime("30 min"); restaurant.setRating(4.5);
		 * restaurant.setRatingText("Excellent"); restaurant.setItems(items);
		 * restaurant.setOffers(offers); restaurant.setReviews(reviews);
		 */
	    	    
	}
	
	@Test
    public void getPricingDetailsTest() throws DatabaseException, DataNotFoundException
    {
        Mockito.when(itemManagementDao.findAllByIdIn(Mockito.anyList())).thenReturn(items);
        List<ItemDto> itemDto = userManagementRepository.getPricingDetails(item);
        assertEquals(items.size(), itemDto.size());
        assertEquals(items.get(0).getName(), itemDto.get(0).getName());
        assertEquals(items.get(0).getPrice(), itemDto.get(0).getPrice());
    }
	
	@Test(expected=DataNotFoundException.class)
    public void getPricingDetailsTestForException() throws DatabaseException, DataNotFoundException
    {
        Mockito.when(itemManagementDao.findAllByIdIn(Mockito.anyList())).thenReturn(items);
        List<ItemDto> itemDto = userManagementRepository.getPricingDetails(Mockito.anyListOf(Integer.class));
        assertEquals(items.size(), itemDto.size());
        assertEquals(items.get(0).getName(), itemDto.get(0).getName());
        assertEquals(items.get(0).getPrice(), itemDto.get(0).getPrice());
    }
	
	@Test(expected=DataNotFoundException.class)
	public void getPricingDetailsTestException() throws DatabaseException, DataNotFoundException {
		Mockito.when(itemManagementDao.findAllByIdIn(Mockito.anyList())).thenReturn(items);
		userManagementRepository.getPricingDetails(Mockito.anyList());	
	}
	
	@Test
	public void userDetailsTest() throws DatabaseException, DataNotFoundException, InvalidDataException {
		Mockito.when(userManagementDao.findByMailId(Mockito.any())).thenReturn(customerDetails);
		Mockito.when(userManagementDao.saveAndFlush(Mockito.any())).thenReturn(customerDetails);
		UserResponseDto userResponseDto = userManagementRepository.userDetails(user);
		assertEquals(customerDetails.getId(), userResponseDto.getId());
        assertEquals(customerDetails.getName(), userResponseDto.getName());
	}

	@Test
	public void getRegisteredUserTest() throws DatabaseException, DataNotFoundException {
		Mockito.when(userManagementDao.findById(Mockito.anyInt())).thenReturn(customerDetails);
		UserResponseDto userResponseDto = userManagementRepository.getRegisteredUser(Mockito.anyInt());
		assertEquals(customerDetails.getId(), userResponseDto.getId());
        assertEquals(customerDetails.getName(), userResponseDto.getName());
	}

	@Test(expected=DataNotFoundException.class)
	public void getRegisteredUserTestException() throws DatabaseException, DataNotFoundException {
		Mockito.when(userManagementDao.findById(Mockito.anyInt())).thenReturn(null);
		userManagementRepository.getRegisteredUser(Mockito.anyInt());	
	}
	
	@Test
	public void placeOrderTest() throws DatabaseException, DataNotFoundException {
		Mockito.when(orderManagementDao.saveAndFlush(Mockito.any())).thenReturn(orderSummary);
		OrderResponseDto orderResponse= userManagementRepository.placeOrder(Mockito.any());
		assertEquals(orderSummary.getTotalCost(), orderResponse.getTotalCost());
        assertEquals(orderSummary.getPhoneNumber(), orderResponse.getPhoneNumber());
	}
	

}
