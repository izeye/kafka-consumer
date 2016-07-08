package com.izeye.kafka.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by izeye on 16. 7. 8..
 */
@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
	
	private final Consumer consumer = new Consumer();

	@Data
	public static class Consumer {

		private List<String> bootstrapServers;
		private String groupId;
		private Class<?> keyDeserializer;
		private Class<?> valueDeserializer;
		private String topic;

	}
	
}
