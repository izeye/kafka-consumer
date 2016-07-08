package com.izeye.kafka.consumer.core.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by izeye on 16. 7. 8..
 */
@Service
@Profile("print")
public class PrintStringKafkaConsumer extends AbstractStringKafkaConsumer {

	@Override
	protected void doConsume(String input) {
		System.out.println(input);
	}
	
}
