package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 平台客户其它账号信息bean
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
public class CuCustomerOther  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**客户信息编号**/
	private String otherInfoId;

	/**平台用户账号**/
	private String platformCustomerId;

	/**APP应用客户编号**/
	private String customerId;

	/**其它账号信息类型：1-QQ，2-邮件，3-微信，4-淘宝，5-京东，6-支付宝**/
	private String otherType;

	/**其它账号用户名**/
	private String otherAccount;

	/**其它账号密码**/
	private String otherPass;

	/**账号认证标志（1-已认证，2-未认证）**/
	private String certificateMark;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public CuCustomerOther() {
		super();
	}
	public CuCustomerOther(String otherInfoId,String platformCustomerId,String customerId,String otherType,String otherAccount,String otherPass,String certificateMark,String createTime,String modifyTime) {
		super();
		this.otherInfoId = otherInfoId;
		this.platformCustomerId = platformCustomerId;
		this.customerId = customerId;
		this.otherType = otherType;
		this.otherAccount = otherAccount;
		this.otherPass = otherPass;
		this.certificateMark = certificateMark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setOtherInfoId(String otherInfoId){
		this.otherInfoId = otherInfoId;
	}

	public String getOtherInfoId(){
		return this.otherInfoId;
	}

	public void setPlatformCustomerId(String platformCustomerId){
		this.platformCustomerId = platformCustomerId;
	}

	public String getPlatformCustomerId(){
		return this.platformCustomerId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setOtherType(String otherType){
		this.otherType = otherType;
	}

	public String getOtherType(){
		return this.otherType;
	}

	public void setOtherAccount(String otherAccount){
		this.otherAccount = otherAccount;
	}

	public String getOtherAccount(){
		return this.otherAccount;
	}

	public void setOtherPass(String otherPass){
		this.otherPass = otherPass;
	}

	public String getOtherPass(){
		return this.otherPass;
	}

	public void setCertificateMark(String certificateMark){
		this.certificateMark = certificateMark;
	}

	public String getCertificateMark(){
		return this.certificateMark;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

}
