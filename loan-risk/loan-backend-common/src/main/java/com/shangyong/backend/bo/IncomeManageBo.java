package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 收益管理业务
 * @author zhouhl
 */
public class IncomeManageBo extends BaseBo {
	private String loanSumMoney = "0.00"; //实际放款金额（元）
	private String hasRepaySumMoney = "0.00"; //收到还款金额（元）
	private String overdueSumMoney = "0.00"; //逾期待催缴金额（元）
	private String sumLoanInterest = "0.00"; //利息收入（元）
	private String managementCost = "0.00"; //账户管理费
	private String inquiryFee = "0.00"; //信息审查费（元）
	private String serviceSumMoney = "0.00"; //合计服务费（元）
	private String sumDelayMoney = "0.00"; //逾期费收入（元）
	private String sumRenewalMoney = "0.00"; //续期费收入（元）
	private String realIncomeProfit = "0.00"; //实收利润（元）
	private String shouldIncomeProfit = "0.00"; //应收利润（元）
	private String sumProfit = "0.00"; //利润总额（元）
	
	private String calcUnit; //统计计算单位 (天 月 季度 年)
	private String startTime; //开始时间
	private String endTime; //结束时间
	private String appChannel; //查询哪个app渠道数据
	public String getLoanSumMoney() {
		return loanSumMoney;
	}
	public void setLoanSumMoney(String loanSumMoney) {
		this.loanSumMoney = loanSumMoney;
	}
	public String getHasRepaySumMoney() {
		return hasRepaySumMoney;
	}
	public void setHasRepaySumMoney(String hasRepaySumMoney) {
		this.hasRepaySumMoney = hasRepaySumMoney;
	}
	public String getOverdueSumMoney() {
		return overdueSumMoney;
	}
	public void setOverdueSumMoney(String overdueSumMoney) {
		this.overdueSumMoney = overdueSumMoney;
	}
	public String getSumLoanInterest() {
		return sumLoanInterest;
	}
	public void setSumLoanInterest(String sumLoanInterest) {
		this.sumLoanInterest = sumLoanInterest;
	}
	public String getManagementCost() {
		return managementCost;
	}
	public void setManagementCost(String managementCost) {
		this.managementCost = managementCost;
	}
	public String getInquiryFee() {
		return inquiryFee;
	}
	public void setInquiryFee(String inquiryFee) {
		this.inquiryFee = inquiryFee;
	}
	public String getServiceSumMoney() {
		return serviceSumMoney;
	}
	public void setServiceSumMoney(String serviceSumMoney) {
		this.serviceSumMoney = serviceSumMoney;
	}
	public String getSumDelayMoney() {
		return sumDelayMoney;
	}
	public void setSumDelayMoney(String sumDelayMoney) {
		this.sumDelayMoney = sumDelayMoney;
	}
	public String getSumRenewalMoney() {
		return sumRenewalMoney;
	}
	public void setSumRenewalMoney(String sumRenewalMoney) {
		this.sumRenewalMoney = sumRenewalMoney;
	}
	public String getRealIncomeProfit() {
		return realIncomeProfit;
	}
	public void setRealIncomeProfit(String realIncomeProfit) {
		this.realIncomeProfit = realIncomeProfit;
	}
	public String getShouldIncomeProfit() {
		return shouldIncomeProfit;
	}
	public void setShouldIncomeProfit(String shouldIncomeProfit) {
		this.shouldIncomeProfit = shouldIncomeProfit;
	}
	public String getSumProfit() {
		return sumProfit;
	}
	public void setSumProfit(String sumProfit) {
		this.sumProfit = sumProfit;
	}
	public String getCalcUnit() {
		return calcUnit;
	}
	public void setCalcUnit(String calcUnit) {
		this.calcUnit = calcUnit;
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