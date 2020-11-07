package com.mindtree.ordermyfood.ordermanagement.kafkaconfig;

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
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mindtree.ordermyfood.ordermanagement.dto.ItemResponseDto;
import com.mindtree.ordermyfood.ordermanagement.dto.KafkaResponseDto;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value("${bootstrap.server}")
	String bootstrapServers;
	
	@Value("${group.id}")
	String groupId ;


	/*
	 * @Bean public ReplyingKafkaTemplate<String,KafkaResponseDto,ItemResponseDto>
	 * replyKafkaTemplate(ProducerFactory<String,KafkaResponseDto>
	 * pf,KafkaMessageListenerContainer<String, ItemResponseDto> container) { return
	 * new ReplyingKafkaTemplate<>(pf, container); }
	 * 
	 * 
	 * @Bean public KafkaMessageListenerContainer<String,
	 * ItemResponseDto>replyContainer(ConsumerFactory<String, ItemResponseDto> cf) {
	 * ContainerProperties containerProperties = new
	 * ContainerProperties("responsetest"); return new
	 * KafkaMessageListenerContainer<>(cf, containerProperties); }
	 */
	// Default Consumer Factory
	@Bean
	public ConsumerFactory<String, ItemResponseDto> consumerFactoryInt() {

		
		  JsonDeserializer<ItemResponseDto> deserializer = new JsonDeserializer<>(ItemResponseDto.class); 
		  deserializer.setRemoveTypeHeaders(false);
		  deserializer.addTrustedPackages("*");
		  deserializer.setUseTypeMapperForKey(true);	 
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),deserializer);
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		//props.put("request.timeout.ms", 20000);
		return props;
	}
	
	@Bean
	public Map<String, Object> errorResponseproducerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		//props.put("request.timeout.ms", 20000);
		return props;
	}


	@Bean
	public ProducerFactory<String,KafkaResponseDto> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public ProducerFactory<String,Integer> errorResponseProducerFactory() {
		return new DefaultKafkaProducerFactory<>(errorResponseproducerConfigs());
	}
	
	// Standard KafkaTemplate
	@Bean
	public KafkaTemplate<String, KafkaResponseDto> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	
	@Bean
	public KafkaTemplate<String, Integer> errorResponseKafkaTemplate() {
		return new KafkaTemplate<>(errorResponseProducerFactory());
	}

	// Concurrent Listner container factory
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Integer>> kafkaListenerContainerFactory(KafkaTemplate<String, Integer> template) {
		ConcurrentKafkaListenerContainerFactory<String, Integer> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		// NOTE - set up of reply template
		factory.setReplyTemplate(kafkaTemplate());
		factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template),1));
		return factory;
	}

	
	// Default Consumer Factory
	@Bean
	public ConsumerFactory<String, Integer> consumerFactory() {

		/*
		 * JsonDeserializer<Integer> deserializer = new
		 * JsonDeserializer<>(Integer.class); deserializer.setRemoveTypeHeaders(false);
		 * deserializer.addTrustedPackages("*");
		 * deserializer.setUseTypeMapperForKey(true);
		 */
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(),new IntegerDeserializer());
	}

	// Standard KafkaProducer settings - specifying brokerand serializer 
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put("request.timeout.ms", 20000);
		return props;
	}
	/*
	 * @Bean public ConsumerFactory<String, String> consumerFactory() { Map<String,
	 * Object> props = new HashMap<>(); props.put(
	 * ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress); props.put(
	 * ConsumerConfig.GROUP_ID_CONFIG, groupId); props.put(
	 * ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	 * props.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	 * StringDeserializer.class); return new DefaultKafkaConsumerFactory<>(props); }
	 * 
	 * @Bean public ConcurrentKafkaListenerContainerFactory<String, String>
	 * kafkaListenerContainerFactory() {
	 * 
	 * ConcurrentKafkaListenerContainerFactory<String, String> factory = new
	 * ConcurrentKafkaListenerContainerFactory<>();
	 * factory.setConsumerFactory(consumerFactory()); return factory; }
	 */
}
