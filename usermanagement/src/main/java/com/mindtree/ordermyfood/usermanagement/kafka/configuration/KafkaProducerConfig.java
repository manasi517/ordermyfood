package com.mindtree.ordermyfood.usermanagement.kafka.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mindtree.ordermyfood.usermanagement.dto.RestaurantDto;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

	@Value("${bootstrap.server}")
	String bootstrapServers;
	
	@Value("${response.topic}")
	String responseTopic;
	

	@Value("${group.id}")
	String groupId ;
	
	@Bean
	public ReplyingKafkaTemplate<String, Integer, RestaurantDto> replyKafkaTemplate(ProducerFactory<String, Integer> pf, KafkaMessageListenerContainer<String, RestaurantDto> container) {
	  return new ReplyingKafkaTemplate<>(pf, container);
	}
	// Listener Container to be set up in ReplyingKafkaTemplate
	@Bean
	public KafkaMessageListenerContainer<String, RestaurantDto> replyContainer(ConsumerFactory<String, RestaurantDto> cf) {
	  ContainerProperties containerProperties = new ContainerProperties(responseTopic);
	  return new KafkaMessageListenerContainer<>(cf, containerProperties);
	}
	// Default Producer Factory to be used in ReplyingKafkaTemplate
	@Bean
	public ProducerFactory<String,Integer> producerFactory() {
	  return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	// Standard KafkaProducer settings - specifying brokerand serializer 
	@Bean
	public Map<String, Object> producerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	            bootstrapServers);
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	            StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
	  props.put("request.timeout.ms", 20000);
	  return props;
	}
	
	  @Bean
	  public Map<String, Object> consumerConfigs() {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
	    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	    props.put("request.timeout.ms", 20000);
	    return props;
	  }
	

	
	@Bean
	public ConsumerFactory<String, Integer> consumerFactoryInt() {
	  return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new IntegerDeserializer());
	}
	@Bean(name = "listenerFactoryInt")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Integer>> kafkaListenerContainerFactoryInt() {
	  ConcurrentKafkaListenerContainerFactory<String, Integer> factory = new ConcurrentKafkaListenerContainerFactory<>();
	  factory.setConsumerFactory(consumerFactoryInt());
	  // NOTE - set up of reply template
	  factory.setReplyTemplate(kafkaTemplate());
	  return factory;
	}
	
	
	
	
	
	// Default Consumer Factory
		@Bean
		public ConsumerFactory<String, RestaurantDto> consumerFactory() {
			JsonDeserializer<RestaurantDto> deserializer = new JsonDeserializer<>(RestaurantDto.class);
		    deserializer.setRemoveTypeHeaders(false);
		    deserializer.addTrustedPackages("*");
		    deserializer.setUseTypeMapperForKey(true);
		  return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),deserializer);
		}
		
	// Concurrent Listner container factory
	@Bean(name = "listenerFactoryDto")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, RestaurantDto>> kafkaListenerContainerFactory() {
	  ConcurrentKafkaListenerContainerFactory<String, RestaurantDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
	  factory.setConsumerFactory(consumerFactory());
	  // NOTE - set up of reply template
	  factory.setReplyTemplate(kafkaTemplate());
	  return factory;
	}
	// Standard KafkaTemplate
	@Bean
	public KafkaTemplate<String, Integer> kafkaTemplate() {
	  return new KafkaTemplate<>(producerFactory());
	}
	
}