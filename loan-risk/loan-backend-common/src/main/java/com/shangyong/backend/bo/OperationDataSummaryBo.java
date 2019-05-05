package com.shangyong.backend.bo;

/**
 * 平台运营管理顶部统计数据
 * @author zhouhl
 */
public class OperationDataSummaryBo {
	private Integer totalUser = 0; //总用户量
	private Integer loanTotalUser = 0; //借款用户总数
	
	private Integer newRegUserNum = 0; //新增用户总量
	private String loanSumMoney = "0.00"; //新增放款总量
	private Integer applayNum =  0; //新增借款申请数
	private String overdueSumMoney = "0.00"; //新增逾期待催缴总金额
	private Integer loanSumCount = 0; //成功放款次数
	private String loanRate = "0%"; //放款率
	private String loanBadRate = "0%"; //不良率
	private Integer m0OverdueCount = 0; //M0逾期次数
	private Integer m1OverdueCount = 0; //M1逾期次数
	private Integer m2OverdueCount = 0; //M2逾期次数
	
	public Integer getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}
	public Integer getLoanTotalUser() {
		return loanTotalUser;
	}
	public void setLoanTotalUser(Integer loanTotalUser) {
		this.loanTotalUser = loanTotalUser;
	}
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
	public String getOverdueSumMoney() {
		return overdueSumMoney;
	}
	public void setOverdueSumMoney(String overdueSumMoney) {
		this.overdueSumMoney = overdueSumMoney;
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
	public String getLoanBadRate() {
		return loanBadRate;
	}
	public void setLoanBadRate(String loanBadRate) {
		this.loanBadRate = loanBadRate;
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
	
}