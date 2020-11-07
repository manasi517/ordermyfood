package com.mindtree.ordermyfood.ordermanagement.kafkacontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mindtree.ordermyfood.ordermanagement.controller.OrderManagementController;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.RestaurantResponseDto;
import com.mindtree.ordermyfood.ordermanagement.exception.OrderMangementException;
import com.mindtree.ordermyfood.ordermanagement.service.OrderManagementService;

@Component
public class KafkaConsumerController {

	private static Logger logger = LoggerFactory.getLogger(OrderManagementController.class);

	private String ResponsneTest = "ResponsneTest";

	@Autowired 
	OrderManagementService orderManagementService;

	@KafkaListener(topics = "OMF_Order",groupId="group_id")  
	@SendTo 
	public KafkaResponseDto getRestaurantDetailsForKafka(Integer restaurantId) throws  OrderMangementException{	  
		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++in listner++++++++++++++++++++++++++++++++++"); 
      
		logger.info("method getRestaurantDetailsForKafka executing with restaurant id:{}",restaurantId);
		return orderManagementService.getRestaurantDetailsForKafka(restaurantId); 
		
		//return userManagementService.placeOrder(orderDetails); 
		


	}


	/*	@KafkaListener(topics = "OMF_Order",groupId = "group_id")

		@SendTo public Message<?> getRestaurantDetailsForKafka(Integer restaurantId,@Header(KafkaHeaders.REPLY_TOPIC) byte[] replyTo,@Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) {


			logger.info("method getRestaurantDetailsForKafka request id:{}",restaurantId);

			RestaurantResponseDto restaurant; 
			try { 
				restaurant =orderManagementService.getRestaurantDetails(restaurantId); 
			} catch(OrderMangementException exception) {
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+"in catch");
				return MessageBuilder.withPayload(restaurantId)
						.setHeader(KafkaHeaders.TOPIC, replyTo) .setHeader(KafkaHeaders.MESSAGE_KEY,
								42) .setHeader(KafkaHeaders.CORRELATION_ID, correlation)
						.setHeader(KafkaHeaders.DLT_EXCEPTION_MESSAGE, exception.getMessage())
						.setHeader(KafkaHeaders.DLT_EXCEPTION_STACKTRACE, exception.getStackTrace())
						.build(); 
			}


	 * logger.info("method getRestaurantDetailsForKafka response: {}",restaurant);
	 * return new ResponseEntity<RestaurantResponseDto>(restaurant, HttpStatus.OK);

			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++"+"outside catch");
			return MessageBuilder.withPayload(restaurant) .setHeader(KafkaHeaders.TOPIC,
					replyTo)  .build(); }//.setHeader(KafkaHeaders.MESSAGE_KEY, 42)
					//.setHeader(KafkaHeaders.CORRELATION_ID, correlation)
	 */

}
