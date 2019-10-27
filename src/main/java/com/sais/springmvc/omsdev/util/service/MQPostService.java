package com.sais.springmvc.omsdev.util.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sais.springmvc.omsdev.util.domain.MQForm;

@Service
public class MQPostService {
	
	private static final Logger logger = LoggerFactory.getLogger(MQPostService.class);
	
	public void postMessageToMQ(MQForm mqform) {
		
		logger.info("Message Posted to MQ successfully - {} to env - {} - to Q - {}",mqform.getMessage(), mqform.getEnvironment(), mqform.getQueueName());
	}
	
} 