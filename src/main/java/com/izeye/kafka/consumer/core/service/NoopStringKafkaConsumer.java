package com.izeye.kafka.consumer.core.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by izeye on 16. 7. 8..
 */
@Service
@Profile("noop")
public class NoopStringKafkaConsumer extends AbstractStringKafkaConsumer {

	@Override
	protected void doConsume(String input) {
	}
	
}
