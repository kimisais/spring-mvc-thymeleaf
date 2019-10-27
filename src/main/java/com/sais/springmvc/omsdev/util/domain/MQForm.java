package com.sais.springmvc.omsdev.util.domain;

import javax.validation.constraints.NotNull;

public class MQForm {

	@NotNull
	private String environment;
	@NotNull
	private String queueName;
	@NotNull
	private String message;
	
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
