package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.Date;

public class ZhiMaIndustryDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;

	/**申请单编号**/
	private String applicationId;
	/**APP应用客户编号**/
	private String customerId;

	/** 风险信息行业编码 **/
	private String bizCode;
	/** 风险等级 **/
	private String zhiMaLevel;
	/** 行业名单风险类型 **/
	private String zhiMaType;
	/** 风险编码 **/
	private String zhiMaCode;

	private Date createTime;
	private String createMan;
	private Date modifyTime;
	private String modifyMan;

	public ZhiMaIndustryDetails() {
	}

	public ZhiMaIndustryDetails(String id, String applicationId, String customerId, String bizCode, String zhiMaLevel,
								String zhiMaType, String zhiMaCode, Date createTime, String createMan, Date modifyTime, String modifyMan) {
		this.id = id;
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.bizCode = bizCode;
		this.zhiMaLevel = zhiMaLevel;
		this.zhiMaType = zhiMaType;
		this.zhiMaCode = zhiMaCode;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getZhiMaLevel() {
		return zhiMaLevel;
	}

	public void setZhiMaLevel(String zhiMaLevel) {
		this.zhiMaLevel = zhiMaLevel;
	}

	public String getZhiMaType() {
		return zhiMaType;
	}

	public void setZhiMaType(String zhiMaType) {
		this.zhiMaType = zhiMaType;
	}

	public String getZhiMaCode() {
		return zhiMaCode;
	}

	public void setZhiMaCode(String zhiMaCode) {
		this.zhiMaCode = zhiMaCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	@Override
	public String toString() {
		return "ZhiMaIndustryDetails{" +
				"id='" + id + '\'' +
				", applicationId='" + applicationId + '\'' +
				", customerId='" + customerId + '\'' +
				", bizCode='" + bizCode + '\'' +
				", zhiMaLevel='" + zhiMaLevel + '\'' +
				", zhiMaType='" + zhiMaType + '\'' +
				", zhiMaCode='" + zhiMaCode + '\'' +
				", createTime=" + createTime +
				", createMan='" + createMan + '\'' +
				", modifyTime=" + modifyTime +
				", modifyMan='" + modifyMan + '\'' +
				'}';
	}
}
