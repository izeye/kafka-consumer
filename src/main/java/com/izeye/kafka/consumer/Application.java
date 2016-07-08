package com.izeye.kafka.consumer;

import com.izeye.kafka.consumer.core.service.StringKafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by izeye on 16. 7. 8..
 */
@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {
	
	@Autowired
	private StringKafkaConsumer kafkaConsumer;
	
	@Override
	public void run(String... args) throws Exception {
		kafkaConsumer.run();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
