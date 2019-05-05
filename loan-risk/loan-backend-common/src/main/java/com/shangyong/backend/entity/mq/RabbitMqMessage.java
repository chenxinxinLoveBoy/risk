package com.shangyong.backend.entity.mq;

public class RabbitMqMessage {
	
	private String queue ;
	
	private String msg ;
	
	public RabbitMqMessage() {
		super();
		
	}
	public RabbitMqMessage(String queue, String msg) {
		super();
		this.queue = queue;
		this.msg = msg;
	}
	public String getQueue() {
		return queue;
	}
	public void setQueue(String queue) {
		this.queue = queue;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
