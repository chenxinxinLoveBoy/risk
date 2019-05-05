package com.shangyong.backend.entity.approval;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Customer {
	private String customerId;
	private String useId;
	private String phoneNum;
	private String tokenCode;
	private String password;
	private String salt;
	private String verifyCode;
	private String deviceId;
	private String osVersion;
	private String appVersion;
	private String sdkVersion;
	private String market;
	private String pushToken;
	private String nickName;
	private String lng;
	private String lat;
	private String source;
	private String userAvatar;
	private Integer loginCounts;
	private Integer loginErrorCounts;
	private String lastLoginTime;
	private String loginState;
	private String gesturePass;
	private String inviter;
	private String tokenEffective;
	private String tokenExpired;
	private Integer appNum;
	private String state;
	private String createTime;
	private String createMan;
	private String modifyTime;
	private String modifyMan;
	private String remark;
	
	private String blackBox;
	private Integer origin; 
	private String lastAppVersion;
	private String lastMarket; 
	
	
	public Customer() {
		super();
	}

	public Customer(String customerId, String useId, String phoneNum,
			String tokenCode, String password, String salt, String verifyCode,
			String deviceId, String osVersion, String appVersion,
			String sdkVersion, String market, String pushToken,
			String nickName, String lng, String lat, String source,
			String userAvatar, Integer loginCounts, Integer loginErrorCounts,
			String lastLoginTime, String loginState, String gesturePass,
			String inviter, String tokenEffective, String tokenExpired,
			Integer appNum, String state, String createTime, String createMan,
			String modifyTime, String modifyMan, String remark) {
		super();
		this.customerId = customerId;
		this.useId = useId;
		this.phoneNum = phoneNum;
		this.tokenCode = tokenCode;
		this.password = password;
		this.salt = salt;
		this.verifyCode = verifyCode;
		this.deviceId = deviceId;
		this.osVersion = osVersion;
		this.appVersion = appVersion;
		this.sdkVersion = sdkVersion;
		this.market = market;
		this.pushToken = pushToken;
		this.nickName = nickName;
		this.lng = lng;
		this.lat = lat;
		this.source = source;
		this.userAvatar = userAvatar;
		this.loginCounts = loginCounts;
		this.loginErrorCounts = loginErrorCounts;
		this.lastLoginTime = lastLoginTime;
		this.loginState = loginState;
		this.gesturePass = gesturePass;
		this.inviter = inviter;
		this.tokenEffective = tokenEffective;
		this.tokenExpired = tokenExpired;
		this.appNum = appNum;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getSdkVersion() {
		return sdkVersion;
	}

	public void setSdkVersion(String sdkVersion) {
		this.sdkVersion = sdkVersion;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public Integer getLoginCounts() {
		return loginCounts;
	}

	public void setLoginCounts(Integer loginCounts) {
		this.loginCounts = loginCounts;
	}

	public Integer getLoginErrorCounts() {
		return loginErrorCounts;
	}

	public void setLoginErrorCounts(Integer loginErrorCounts) {
		this.loginErrorCounts = loginErrorCounts;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getGesturePass() {
		return gesturePass;
	}

	public void setGesturePass(String gesturePass) {
		this.gesturePass = gesturePass;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getTokenEffective() {
		return tokenEffective;
	}

	public void setTokenEffective(String tokenEffective) {
		this.tokenEffective = tokenEffective;
	}

	public String getTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(String tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public Integer getAppNum() {
		return appNum;
	}

	public void setAppNum(Integer appNum) {
		this.appNum = appNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getBlackBox() {
		return blackBox;
	}

	public void setBlackBox(String blackBox) {
		this.blackBox = blackBox;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public String getLastAppVersion() {
		return lastAppVersion;
	}

	public void setLastAppVersion(String lastAppVersion) {
		this.lastAppVersion = lastAppVersion;
	}

	public String getLastMarket() {
		return lastMarket;
	}

	public void setLastMarket(String lastMarket) {
		this.lastMarket = lastMarket;
	}

}
