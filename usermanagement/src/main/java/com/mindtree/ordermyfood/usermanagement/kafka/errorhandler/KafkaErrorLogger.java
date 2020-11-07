package com.mindtree.ordermyfood.usermanagement.kafka.errorhandler;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaErrorLogger {

	private static Logger logger = LoggerFactory.getLogger(KafkaErrorLogger.class);
	
	@KafkaListener(topics="OMF_Order.DLT",
			containerFactory = "listenerFactoryInt")
	public void dltListner(@Payload Integer order, @Headers MessageHeaders headers) {
		String orderException = new String((byte[])headers.get("kafka_dlt-exception-stacktrace"), StandardCharsets.UTF_8);
		logger.info(orderException);
	}
}
