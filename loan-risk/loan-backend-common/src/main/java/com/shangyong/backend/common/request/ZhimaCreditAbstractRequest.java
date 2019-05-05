package com.shangyong.backend.common.request;

import java.io.Serializable;

/**
 * 参数对象( 芝麻信用评分 )必选参数
 * @author xiajiyun
 *
 */
public abstract class ZhimaCreditAbstractRequest implements Serializable{

	private static final long serialVersionUID = 7968952318920776300L;
	
	// 这里的参数必选
 
	/**
	 * 芝麻会员在商户端的身份标识。 
	 */
	private String openId;
	
	/**
	 * 平台用户编号
	 */
	private String platformCustomerId;

	/**
	 * 申请单编号
	 */
	private String applicationId;
	
	

	public String getPlatformCustomerId() {
		return platformCustomerId;
	}

	public void setPlatformCustomerId(String platformCustomerId) {
		this.platformCustomerId = platformCustomerId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
 
}
