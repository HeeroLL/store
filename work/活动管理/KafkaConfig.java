package com.ihidea.starcharge.monitor.dxp;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

//@Configuration
//@EnableKafka
public class KafkaConfig {

	@Value("${kafka.broker.address}")
	private String brokerAddress;

	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		// 消费的并发数
		factory.setConcurrency(5);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> propsMap = new HashMap<>();
		propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
		propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "starcharge.monitor.data");
		propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		return propsMap;
	}

	@Bean
	public KafkaConsumer listener() {
		return new KafkaConsumer();
	}

}
