package com.shangyong.backend.common.request;

import java.io.Serializable;

/**
 * 参数对象（此对象的参数为必选项）
 * @author xiajiyun
 *
 */
public abstract class ZhimaRequest implements Serializable {

	private static final long serialVersionUID = -4897664849084564630L;

	// 这里的参数为必选参数

	/**
	 * 身份证号码
	 */
	private String certNo;

	/**
	 * 身份证姓名
	 */
	private String name;

	/**
	 * 平台用户编号
	 */
	private String platformCustomerId;

	/**
	 * 申请单编号
	 */
	private String applicationId;

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

}
