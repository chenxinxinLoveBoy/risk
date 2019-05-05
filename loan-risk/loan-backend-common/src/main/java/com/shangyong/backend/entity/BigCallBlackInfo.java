package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class BigCallBlackInfo extends BaseBo{
	
	/** 申请单编号  */
	private String application_id;
	
	/** 客户编号  */
	private String customer_id;

	/** 分数  */
	private String score;
	
	/** 创建时间  */
	private String create_time;
	
	/** 审核状态  */
	private String pass_or_reject;


	/** 消息编号  */
	private String messageId;

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getPass_or_reject() {
		return pass_or_reject;
	}

	public void setPass_or_reject(String pass_or_reject) {
		this.pass_or_reject = pass_or_reject;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
