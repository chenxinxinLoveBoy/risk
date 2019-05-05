package com.shangyong.backend.entity.mq;

/**
 *
 */
public class RabbitMqApplicationId {
	
	private String applicationId;

	public RabbitMqApplicationId() {
		super();
	}

	public RabbitMqApplicationId(String applicationId) {
		super();
		this.applicationId = applicationId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
}
