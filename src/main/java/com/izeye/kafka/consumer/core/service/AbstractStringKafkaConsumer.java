package com.izeye.kafka.consumer.core.service;

import com.izeye.kafka.consumer.config.KafkaProperties;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by izeye on 16. 7. 8..
 */
@EnableConfigurationProperties(KafkaProperties.class)
public abstract class AbstractStringKafkaConsumer implements StringKafkaConsumer {
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	private final AtomicLong counter = new AtomicLong(0);
	
	@Override
	public void run() {
		Map<String, Object> properties = createConsumerProperties();
		Consumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Arrays.asList(kafkaProperties.getConsumer().getTopic()));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				doConsume(record.value());
				
				counter.incrementAndGet();
			}
		}
	}

	private Map<String, Object> createConsumerProperties() {
		Map<String, Object> properties = new HashMap<>();
		KafkaProperties.Consumer consumer = this.kafkaProperties.getConsumer();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				consumer.getBootstrapServers());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumer.getGroupId());
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				consumer.getKeyDeserializer());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				consumer.getValueDeserializer());
		return properties;
	}
	
	protected abstract void doConsume(String input);
	
	@Scheduled(cron = "* * * * * ?")
	public void printStatistics() {
		long count = counter.getAndSet(0);
		System.out.println("# of consumed logs per second: " + count);
	}
	
}
