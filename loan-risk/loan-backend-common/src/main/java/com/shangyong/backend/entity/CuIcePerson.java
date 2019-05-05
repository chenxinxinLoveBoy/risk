package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 平台客户紧急联系人bean
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
public class CuIcePerson  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**紧急联系人编号**/
	private String icePersonId;

	/**平台用户账号**/
	private String platformCustomerId;

	/**申请单编号 **/
	private String applicationId;
	
	/**APP应用客户编号**/
	private String customerId;

	/**类型说明：father	父亲、mother	母亲、spouse	配偶、child	子女、other_relative	其他亲属、friend	朋友、coworker	同事、others	其他**/
	private String type;

	/**真实姓名**/
	private String trueName;

	/**手机号码**/
	private String phoneNum;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	public CuIcePerson() {
		super();
	}

	public CuIcePerson(String icePersonId, String platformCustomerId, String applicationId, String customerId,
			String type, String trueName, String phoneNum, String createTime, String createMan, String modifyTime,
			String modifyMan, String remark) {
		super();
		this.icePersonId = icePersonId;
		this.platformCustomerId = platformCustomerId;
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.type = type;
		this.trueName = trueName;
		this.phoneNum = phoneNum;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CuIcePerson [icePersonId=" + icePersonId + ", platformCustomerId=" + platformCustomerId
				+ ", applicationId=" + applicationId + ", customerId=" + customerId + ", type=" + type + ", trueName="
				+ trueName + ", phoneNum=" + phoneNum + ", createTime=" + createTime + ", createMan=" + createMan
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}


}
