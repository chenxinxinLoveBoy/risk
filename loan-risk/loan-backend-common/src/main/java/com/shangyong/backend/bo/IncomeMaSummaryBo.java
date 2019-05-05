package com.shangyong.backend.bo;

/**
 * 收益管理顶部统计数据
 * @author zhouhl
 */
public class IncomeMaSummaryBo {
	private String loanSumMoney = "0.00"; //实际放款总额
	private String overdueSumMoney = "0.00"; //逾期待催缴金额
	private String realIncomeProfit = "0.00"; //合计实收利润
	private String sumProfit = "0.00"; //合计利润总额
	private String hasRepaySumMoney = "0.00"; //收到还款总额
	private String shouldIncomeProfit = "0.00"; //合计应收利润
	private String serviceSumMoney = "0.00"; //合计服务费总收入
	
	public String getLoanSumMoney() {
		return loanSumMoney;
	}
	public void setLoanSumMoney(String loanSumMoney) {
		this.loanSumMoney = loanSumMoney;
	}
	public String getOverdueSumMoney() {
		return overdueSumMoney;
	}
	public void setOverdueSumMoney(String overdueSumMoney) {
		this.overdueSumMoney = overdueSumMoney;
	}
	public String getRealIncomeProfit() {
		return realIncomeProfit;
	}
	public void setRealIncomeProfit(String realIncomeProfit) {
		this.realIncomeProfit = realIncomeProfit;
	}
	public String getSumProfit() {
		return sumProfit;
	}
	public void setSumProfit(String sumProfit) {
		this.sumProfit = sumProfit;
	}
	public String getHasRepaySumMoney() {
		return hasRepaySumMoney;
	}
	public void setHasRepaySumMoney(String hasRepaySumMoney) {
		this.hasRepaySumMoney = hasRepaySumMoney;
	}
	public String getShouldIncomeProfit() {
		return shouldIncomeProfit;
	}
	public void setShouldIncomeProfit(String shouldIncomeProfit) {
		this.shouldIncomeProfit = shouldIncomeProfit;
	}
	public String getServiceSumMoney() {
		return serviceSumMoney;
	}
	public void setServiceSumMoney(String serviceSumMoney) {
		this.serviceSumMoney = serviceSumMoney;
	}

}