package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.List;

public class ApplicationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**APP应用请求流水号**/
	private String appSerialNumber;

	/**
	 * APP名称：
	 * @see  com.shangyong.backend.common.enums.AppNameEnum
	 */
	private String appName;

	/**APP应用客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**证件类型（1.身份证 2.护照 3.其他）**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**产品额度**/
	private String productQuota;

	/**周期**/
	private Integer days;

	/**借款用户公网IP**/
	private String loanIp;

	/**完善资料时间**/
	private String completeDataTime;

	/**申请来源（0——Android；1——IOS）**/
	private String source;

	/**同盾black_box**/
	private String tdBlackBox;

	/**聚信立用户token**/
	private String jxlUseToken;
	
	/**芝麻用户标识：芝麻会员在商户端的身份标识**/
	private String openId;
	
	/**审批结果描述**/
	private String auditResult;
	
	/**禁止项规则对应编号**/
	private String banCode;
	
	/**客户标识(0:新客户 1：老客户)**/
	private String appLevel;
	
	/**app渠道标识**/
	private String appChannel;
	
	/**APP版本号**/
	private String appVersion;
	
	/**用户已还款次数**/
	private int refundConut;
	
	/**用户逾期次数**/
	private int overdueCount;

	/** 芝麻分数 **/
	private String score;

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getAppSerialNumber() {
		return appSerialNumber;
	}

	public void setAppSerialNumber(String appSerialNumber) {
		this.appSerialNumber = appSerialNumber;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getProductQuota() {
		return productQuota;
	}

	public void setProductQuota(String productQuota) {
		this.productQuota = productQuota;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getLoanIp() {
		return loanIp;
	}

	public void setLoanIp(String loanIp) {
		this.loanIp = loanIp;
	}

	public String getCompleteDataTime() {
		return completeDataTime;
	}

	public void setCompleteDataTime(String completeDataTime) {
		this.completeDataTime = completeDataTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTdBlackBox() {
		return tdBlackBox;
	}

	public void setTdBlackBox(String tdBlackBox) {
		this.tdBlackBox = tdBlackBox;
	}

	public String getJxlUseToken() {
		return jxlUseToken;
	}

	public void setJxlUseToken(String jxlUseToken) {
		this.jxlUseToken = jxlUseToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	public String getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getRefundConut() {
		return refundConut;
	}

	public void setRefundConut(int refundConut) {
		this.refundConut = refundConut;
	}

	public int getOverdueCount() {
		return overdueCount;
	}

	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


}
