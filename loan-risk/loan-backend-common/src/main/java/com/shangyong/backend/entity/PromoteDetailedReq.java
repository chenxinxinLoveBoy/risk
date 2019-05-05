package com.shangyong.backend.entity;

import java.io.Serializable;

public class PromoteDetailedReq implements Serializable{

	private static final long serialVersionUID = -2742148489606189447L;
	
	//客户编号
	private String customerId;
	//app名称
	private String appName;
	//数据存储类型 06002- 聚信立学信 06003- 聚信立电商 06004- 聚信立社保 06005- 聚信立公积金 06006-聚信立人行征信
	private String taskType;
	//任务编号
	private String taskId;
	//app下载渠道
	private String appChannel;
	//客户标识（0-新客户，1-老客户）
	private String appLevel;
	//用户已还款次数
	private String refundConut;
	//当天借当天还次数
	private String dayLoanCount;
	//app请求流水号
	private String appSerialNumber;
	//客户已有额度
	private String existingMoney;
	//用户姓名
	private String userName;
	//用户身份证号
	private String idCard;
	//用户手机号
	private String phone;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getAppChannel() {
		return appChannel;
	}
	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}
	public String getAppLevel() {
		return appLevel;
	}
	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}
	public String getRefundConut() {
		return refundConut;
	}
	public void setRefundConut(String refundConut) {
		this.refundConut = refundConut;
	}
	public String getDayLoanCount() {
		return dayLoanCount;
	}
	public void setDayLoanCount(String dayLoanCount) {
		this.dayLoanCount = dayLoanCount;
	}
	public String getAppSerialNumber() {
		return appSerialNumber;
	}
	public void setAppSerialNumber(String appSerialNumber) {
		this.appSerialNumber = appSerialNumber;
	}
	public String getExistingMoney() {
		return existingMoney;
	}
	public void setExistingMoney(String existingMoney) {
		this.existingMoney = existingMoney;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public PromoteDetailedReq(String customerId, String appName, String taskType, String taskId, String appChannel,
			String appLevel, String refundConut, String dayLoanCount, String appSerialNumber, String existingMoney,
			String userName, String idCard, String phone) {
		super();
		this.customerId = customerId;
		this.appName = appName;
		this.taskType = taskType;
		this.taskId = taskId;
		this.appChannel = appChannel;
		this.appLevel = appLevel;
		this.refundConut = refundConut;
		this.dayLoanCount = dayLoanCount;
		this.appSerialNumber = appSerialNumber;
		this.existingMoney = existingMoney;
		this.userName = userName;
		this.idCard = idCard;
		this.phone = phone;
	}
	public PromoteDetailedReq() {
		super();
	}
	@Override
	public String toString() {
		return "PromoteDetailedReq [customerId=" + customerId + ", appName=" + appName + ", taskType=" + taskType
				+ ", taskId=" + taskId + ", appChannel=" + appChannel + ", appLevel=" + appLevel + ", refundConut="
				+ refundConut + ", dayLoanCount=" + dayLoanCount + ", appSerialNumber=" + appSerialNumber
				+ ", existingMoney=" + existingMoney + ", userName=" + userName + ", idCard=" + idCard + ", phone="
				+ phone + "]";
	}

	
}
