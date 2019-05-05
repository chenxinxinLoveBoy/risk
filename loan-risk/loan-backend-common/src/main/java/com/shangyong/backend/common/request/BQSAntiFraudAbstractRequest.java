package com.shangyong.backend.common.request;

import java.io.Serializable;

/**
 * 白骑士，反欺诈参数抽象类，这里的参数为必填项
 * @author xiajiyun
 *
 */
public abstract class BQSAntiFraudAbstractRequest implements Serializable{

	private static final long serialVersionUID = 6844859075525903018L;
	 
	
	/**
	 * 事件类型，参考eventType附录码表用来标识应用下某个策略集事件
	 */
	private String eventType;
	
	/**
	 * 申请单编号
	 */
	private String applicationId;
	
	/**
	 * 用户姓名
	 */
	private String name;
	
	/**
	 * 用户手机号
	 */
	private String mobile;
	
	/**
	 * 用户身份证号
	 */
	private String certNo;
	

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	

}
