/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Mobile_info {
	@JsonProperty("identity_code")
    private String identityCode;
	@JsonProperty("contact_addr")
    private String contactAddr;
	@JsonProperty("account_balance")
    private String accountBalance;
	@JsonProperty("user_mobile")
    private String userMobile;
	@JsonProperty("mobile_net_addr")
    private String mobileNetAddr;
	@JsonProperty("mobile_carrier")
    private String mobileCarrier;
	@JsonProperty("real_name")
    private String realName;
	@JsonProperty("account_status")
    private String accountStatus;
	@JsonProperty("mobile_net_time")
    private String mobileNetTime;
	@JsonProperty("package_type")
    private String packageType;
	@JsonProperty("mobile_net_age")
    private String mobileNetAge;
    private String email;
	public String getIdentityCode() {
		return identityCode;
	}
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	public String getContactAddr() {
		return contactAddr;
	}
	public void setContactAddr(String contactAddr) {
		this.contactAddr = contactAddr;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getMobileNetAddr() {
		return mobileNetAddr;
	}
	public void setMobileNetAddr(String mobileNetAddr) {
		this.mobileNetAddr = mobileNetAddr;
	}
	public String getMobileCarrier() {
		return mobileCarrier;
	}
	public void setMobileCarrier(String mobileCarrier) {
		this.mobileCarrier = mobileCarrier;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getMobileNetTime() {
		return mobileNetTime;
	}
	public void setMobileNetTime(String mobileNetTime) {
		this.mobileNetTime = mobileNetTime;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getMobileNetAge() {
		return mobileNetAge;
	}
	public void setMobileNetAge(String mobileNetAge) {
		this.mobileNetAge = mobileNetAge;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
  
}