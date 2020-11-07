package com.mindtree.ordermyfood.usermanagement.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mindtree.ordermyfood.usermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserDetailsDto;
import com.mindtree.ordermyfood.usermanagement.dto.UserResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.Customer;
import com.mindtree.ordermyfood.usermanagement.service.UserManagementService;


@RunWith(SpringRunner.class)
@WebMvcTest(UserManagementController.class)
@TestPropertySource(locations="classpath:test.properties")
public class UserManagementControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	UserManagementService usermanagementService;
	
	Customer customerDetails = new Customer();
	UserResponseDto userResponse = new UserResponseDto();
	OrderResponseDto orderSummary = new OrderResponseDto();
	ItemResponseDto itemResponseDto = new ItemResponseDto() ;
	List<Integer> item = new ArrayList<Integer>() ;
	UserDetailsDto user = new UserDetailsDto();
	
	@Before
	public void before() {
	   
		itemResponseDto.setTax(50.00);
		itemResponseDto.setTotalCost(400.00);
		
		customerDetails.setId(1);
		customerDetails.setName("sampat");
		
		userResponse.setName("sampat");
		userResponse.setId(3421);
	    
		orderSummary.setTotalCost(200.0);
		orderSummary.setPhoneNumber(98764311);
	    
	}
	
	
	@Test
	public void getPricingDetailsTest() throws Exception {
		
		Mockito.when(usermanagementService.getPricingDetails(Mockito.anyList())).thenReturn(itemResponseDto);	
		
		  mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/display/customer/price")
		  .contentType(MediaType.APPLICATION_JSON)) 
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$.totalCost", is(400.00)));
	}

	
	  @Test 
	  public void userDetailsTest() throws Exception {
	  
	  Mockito.when(usermanagementService.userDetails(Mockito.any())).thenReturn(
	  userResponse);
	  
	  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/user")
	  .contentType(MediaType.APPLICATION_JSON))
	  .andExpect(MockMvcResultMatchers.status().isOk())
	  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) 
	  .andExpect(jsonPath("$.name", is("sampat"))); }
	 
	@Test
	public void userLoginTest() throws Exception {
		
		Mockito.when(usermanagementService.userLogin(Mockito.anyInt())).thenReturn(userResponse);	
		
		  mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/login/1")
		  .contentType(MediaType.APPLICATION_JSON)) 
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
		  .andExpect(jsonPath("$.*", hasSize(1)));
	}

	
	  @Test 
	  public void placeOrderTest() throws Exception {
	  
	  Mockito.when(usermanagementService.placeOrder(Mockito.any())).thenReturn(orderSummary);
	  
	  mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/order")
	  .contentType(MediaType.APPLICATION_JSON))
	  .andExpect(MockMvcResultMatchers.status().isOk())
	  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
	  .andExpect((ResultMatcher) jsonPath("$.totalCost", 200.0)); 
	  }
	 

	
}
