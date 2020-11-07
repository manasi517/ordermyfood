package com.mindtree.ordermyfood.usermanagement.kafka.servicehandler;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.mindtree.ordermyfood.usermanagement.constants.ErrorMessageConstants;
import com.mindtree.ordermyfood.usermanagement.controller.FeignServiceClient;
import com.mindtree.ordermyfood.usermanagement.dto.ItemDto;
import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.RestaurantDto;
import com.mindtree.ordermyfood.usermanagement.exception.DataNotFoundException;
import com.mindtree.ordermyfood.usermanagement.exception.KafkaListnerException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class RestaurantInfoValidator{

	private static final String TOPIC = "OMF_Order";
	private static final String REPLY_TOPIC = "responsetest";

	@Autowired 
	private ReplyingKafkaTemplate<String,Integer,RestaurantDto> kafkaTemplate;

	@Autowired
	private FeignServiceClient feignServiceClient;

	@HystrixCommand(fallbackMethod = "fallback")
	public boolean getRestaurantFromKafka(OrderDetails orderDetails) throws KafkaListnerException, DataNotFoundException {
		// create producer record
		ProducerRecord<String, Integer> record = new ProducerRecord<String, Integer>(TOPIC, orderDetails.getRestaurantId());
		// set reply topic in header
		record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, REPLY_TOPIC.getBytes()));
		// post in kafka topic
		RequestReplyFuture<String, Integer, RestaurantDto> sendAndReceive = kafkaTemplate.sendAndReceive(record);
		// confirm if producer produced successfully
		SendResult<String, Integer> sendResult=null;

		try {
			sendResult = sendAndReceive.getSendFuture().get();
		} catch (InterruptedException | ExecutionException exception) {
			throw new KafkaListnerException(ErrorMessageConstants.RESTAURANTS_DETAILS_FETCH_EXCEPTON,exception);
		}
		
		ConsumerRecord<String, RestaurantDto> consumerRecord=null;

		try {
			consumerRecord = sendAndReceive.get();
		} catch (InterruptedException | ExecutionException exception) {
			throw new KafkaListnerException("",exception);


		}			
		return validateOrderedItems(orderDetails,consumerRecord.value()); 		

	}

	private boolean validateOrderedItems(OrderDetails orderDetails, RestaurantDto restaurant) throws DataNotFoundException {

		Boolean itemValidateFlag =false;
		List<ItemDto> restaurantItemsList = restaurant.getItems();
		
		if(null == restaurantItemsList || restaurantItemsList.isEmpty()) {
			throw new DataNotFoundException(ErrorMessageConstants.ITEM_UNAVAILABLE_EXCEPTON+restaurantItemsList);
		}

		List<ItemDto> unavailableItems =null;
		List<ItemDto> orderItems = orderDetails.getItems();

		unavailableItems = orderItems.stream()
				.filter(t -> 
				orderItems.stream()
				.map(item -> item.getId())
				.filter(item -> 
				!(restaurantItemsList.stream()
						.map(item2 -> item2.getId()).collect(Collectors.toList()).contains(item))
						).collect(Collectors.toList()).contains(t.getId())
						).collect(Collectors.toList());

		if(unavailableItems != null && !unavailableItems.isEmpty()) {
			throw new DataNotFoundException(ErrorMessageConstants.ITEM_UNAVAILABLE_EXCEPTON+unavailableItems.toString());
		}
		itemValidateFlag=true;
		return itemValidateFlag;
	}

	 boolean fallback(OrderDetails orderDetails) throws DataNotFoundException {
		 
		 
		RestaurantDto restaurant = feignServiceClient.getRestaurantDetails(orderDetails.getRestaurantId());		
		List<ItemDto> items = feignServiceClient.getItemDetails(orderDetails.getRestaurantId());
		restaurant.setItems(items);
		return  validateOrderedItems(orderDetails, restaurant);
	}

}
