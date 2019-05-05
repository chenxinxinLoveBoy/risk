package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 平台客户紧急联系人bean
 * @author hxf
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
public class CuIcePersonDetails  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**紧急联系人编号**/
	private String icePersonId;

	/**平台用户账号**/
	private String platformCustomerId;

	/**APP应用客户编号**/
	private String customerId;

	/**真实姓名**/
	private String trueName;
	
	/**手机号码**/
	private String phoneNum;

	/**归属地名称**/
	private String attributionName;

	/**联系次数**/
	private Integer linkNum;

	/**最近一次通话时间**/
	private String LastLinkTimes;

	public CuIcePersonDetails() {
		super();
	}

	public CuIcePersonDetails(String icePersonId, String platformCustomerId, String customerId, String trueName,
			String phoneNum, String attributionName, Integer linkNum, String lastLinkTimes) {
		super();
		this.icePersonId = icePersonId;
		this.platformCustomerId = platformCustomerId;
		this.customerId = customerId;
		this.trueName = trueName;
		this.phoneNum = phoneNum;
		this.attributionName = attributionName;
		this.linkNum = linkNum;
		LastLinkTimes = lastLinkTimes;
	}

	public String getIcePersonId() {
		return icePersonId;
	}

	public void setIcePersonId(String icePersonId) {
		this.icePersonId = icePersonId;
	}

	public String getPlatformCustomerId() {
		return platformCustomerId;
	}

	public void setPlatformCustomerId(String platformCustomerId) {
		this.platformCustomerId = platformCustomerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAttributionName() {
		return attributionName;
	}

	public void setAttributionName(String attributionName) {
		this.attributionName = attributionName;
	}

	public Integer getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(Integer linkNum) {
		this.linkNum = linkNum;
	}

	public String getLastLinkTimes() {
		return LastLinkTimes;
	}

	public void setLastLinkTimes(String lastLinkTimes) {
		LastLinkTimes = lastLinkTimes;
	}

}
