package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 平台运营管理业务
 * @author zhouhl
 */
public class OperationDataBo extends BaseBo {
	private Integer newRegUserNum = 0; //新增用户（个）
	private String loanSumMoney = "0.00"; //新增放款金额（元）
	private Integer applayNum = 0; //新增借款申请数（次）
	private Integer loanSumCount = 0; //成功放款（次）
	private String loanRate = "0.00"; //放款率（%）
	private String overdueSumMoney = "0.00"; //新增逾期待催缴金额（元）
	private Integer m0OverdueCount = 0; //M0逾期（次）
	private Integer m1OverdueCount = 0; //M1逾期（次）
	private Integer m2OverdueCount = 0; //M2逾期（次）
	private String loanBadRate = "0%"; //不良率（%）
	
	private String calcUnit; //统计计算单位 (天 月 季度 年)
	private String startTime; //开始时间
	private String endTime; //结束时间
	private String appChannel; //查询哪个app渠道数据
	
	public Integer getNewRegUserNum() {
		return newRegUserNum;
	}
	public void setNewRegUserNum(Integer newRegUserNum) {
		this.newRegUserNum = newRegUserNum;
	}
	public String getLoanSumMoney() {
		return loanSumMoney;
	}
	public void setLoanSumMoney(String loanSumMoney) {
		this.loanSumMoney = loanSumMoney;
	}
	public Integer getApplayNum() {
		return applayNum;
	}
	public void setApplayNum(Integer applayNum) {
		this.applayNum = applayNum;
	}
	public Integer getLoanSumCount() {
		return loanSumCount;
	}
	public void setLoanSumCount(Integer loanSumCount) {
		this.loanSumCount = loanSumCount;
	}
	public String getLoanRate() {
		return loanRate;
	}
	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}
	public String getOverdueSumMoney() {
		return overdueSumMoney;
	}
	public void setOverdueSumMoney(String overdueSumMoney) {
		this.overdueSumMoney = overdueSumMoney;
	}
	public Integer getM0OverdueCount() {
		return m0OverdueCount;
	}
	public void setM0OverdueCount(Integer m0OverdueCount) {
		this.m0OverdueCount = m0OverdueCount;
	}
	public Integer getM1OverdueCount() {
		return m1OverdueCount;
	}
	public void setM1OverdueCount(Integer m1OverdueCount) {
		this.m1OverdueCount = m1OverdueCount;
	}
	public Integer getM2OverdueCount() {
		return m2OverdueCount;
	}
	public void setM2OverdueCount(Integer m2OverdueCount) {
		this.m2OverdueCount = m2OverdueCount;
	}
	public String getLoanBadRate() {
		return loanBadRate;
	}
	public void setLoanBadRate(String loanBadRate) {
		this.loanBadRate = loanBadRate;
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