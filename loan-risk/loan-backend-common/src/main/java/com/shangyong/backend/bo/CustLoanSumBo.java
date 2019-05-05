package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * @author zhouhl
 */
public class CustLoanSumBo extends BaseBo {
	private String customerId; 
	private String customerName;
	private String platformId;
	private String idCard; //身份证号
	private String mobile;
	private String loanSumMoney = "0.00"; //借款总金额
	private Integer loanSumCount = 0; //借款笔数
	private String readyRepaySumMoney = "0.00"; //待还款总金额
	private String overdueMoney = "0.00"; //逾期金额
	private Integer overdueCount = 0; //逾期次数
	
	private String startTime; //开始时间
	private String endTime; //结束时间
	private String appChannel; //查询哪个app渠道数据
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLoanSumMoney() {
		return loanSumMoney;
	}
	public void setLoanSumMoney(String loanSumMoney) {
		this.loanSumMoney = loanSumMoney;
	}
	public Integer getLoanSumCount() {
		return loanSumCount;
	}
	public void setLoanSumCount(Integer loanSumCount) {
		this.loanSumCount = loanSumCount;
	}
	public String getReadyRepaySumMoney() {
		return readyRepaySumMoney;
	}
	public void setReadyRepaySumMoney(String readyRepaySumMoney) {
		this.readyRepaySumMoney = readyRepaySumMoney;
	}
	public String getOverdueMoney() {
		return overdueMoney;
	}
	public void setOverdueMoney(String overdueMoney) {
		this.overdueMoney = overdueMoney;
	}
	public Integer getOverdueCount() {
		return overdueCount;
	}
	public void setOverdueCount(Integer overdueCount) {
		this.overdueCount = overdueCount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAppChannel() {
		return appChannel;
	}
	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}
	
}