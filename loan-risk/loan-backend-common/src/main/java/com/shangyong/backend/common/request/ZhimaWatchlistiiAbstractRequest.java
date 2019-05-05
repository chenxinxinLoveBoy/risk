package com.shangyong.backend.common.request;

import java.io.Serializable;

/**
 * 参数对象( 芝麻信用行业关注名单 )必选参数
 * @author xiajiyun
 *
 */
public abstract class ZhimaWatchlistiiAbstractRequest implements Serializable{
 
	// 这里的参数必选
 
	private static final long serialVersionUID = -3315169351556451211L;

	/**
	 * 芝麻会员在商户端的身份标识。 
	 */
	private String openId;
	
	/**
	 * 申请单编号
	 */
	private String applicationId;
	
	 
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
