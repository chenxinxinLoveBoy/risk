package com.shangyong.backend.entity.sh;


/**
 * 上海资信贷款表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditLoans {

	/**主键自增id**/
	private String loansId;

	/**申请单编号**/
	private String applicationId;

	/**贷款项目**/
	private String loansProject;

	/**机构名称**/
	private String organizationName;

	/**授信额度**/
	private String limitMoney;

	/**担保方式**/
	private String guaranteeType;

	/**开户日期**/
	private String openDate;

	/**币种**/
	private String currency;

	/**发生地**/
	private String locality;

	/**共享授信额度**/
	private String shareLimitMoney;
	
	/**最大负债额**/
	private String maxLiabilitiesMoney;
	
	/**还款频率**/
	private String repaymentFrequency;

	/**期末贷款余额**/
	private String endingIoanBalance;

	/**剩余还款月数**/
	private String leftTermsLoan;

	/**本月应还款日期**/
	private String repaymentDateMonth;

	/**本月应还款金额**/
	private String repaymentAmountMonth;

	/**账户状态**/
	private String accountStatus;

	/**实际还款日期**/
	private String actualDateRepayment;

	/**实际还款金额**/
	private String actualPaymentAmount;

	/**当前逾期总额**/
	private String nowOverdueLimit;

	/**当前逾期期数**/
	private String nowOverdueNumber;

	/**累计逾期期数**/
	private String totalOverdueNumber;

	/**最高逾期期数**/
	private String maxOverdueNumber;

	/**
	 * 二十四月内各月还款状况
	 * N:正常
	 * C:结清
	 * *:未知
	 * /：没有记录
	 * 数值:逾期次数  1期 = M1
	 *               2期 = M2 以此类推
	 **/
	private String paymentStatus;

	/**逾期31-60天未归还贷款本金**/
	private String overdueTwoMonth;

	/**逾期61-90天未归还贷款本金**/
	private String overdueThreeMonth;

	/**逾期91-180天未归还贷款本金**/
	private String overdueSixMonth;

	/**逾期180天以上未归还贷款本金**/
	private String overdueYearMonth;

	/**贷款信息获取日期**/
	private String loansTime;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	public String getLoansId() {
		return loansId;
	}

	public void setLoansId(String loansId) {
		this.loansId = loansId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getLoansProject() {
		return loansProject;
	}

	public void setLoansProject(String loansProject) {
		this.loansProject = loansProject;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLimitMoney() {
		return limitMoney;
	}

	public void setLimitMoney(String limitMoney) {
		this.limitMoney = limitMoney;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getShareLimitMoney() {
		return shareLimitMoney;
	}

	public void setShareLimitMoney(String shareLimitMoney) {
		this.shareLimitMoney = shareLimitMoney;
	}

	public String getMaxLiabilitiesMoney() {
		return maxLiabilitiesMoney;
	}

	public void setMaxLiabilitiesMoney(String maxLiabilitiesMoney) {
		this.maxLiabilitiesMoney = maxLiabilitiesMoney;
	}

	public String getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(String repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public String getEndingIoanBalance() {
		return endingIoanBalance;
	}

	public void setEndingIoanBalance(String endingIoanBalance) {
		this.endingIoanBalance = endingIoanBalance;
	}

	public String getLeftTermsLoan() {
		return leftTermsLoan;
	}

	public void setLeftTermsLoan(String leftTermsLoan) {
		this.leftTermsLoan = leftTermsLoan;
	}

	public String getRepaymentDateMonth() {
		return repaymentDateMonth;
	}

	public void setRepaymentDateMonth(String repaymentDateMonth) {
		this.repaymentDateMonth = repaymentDateMonth;
	}

	public String getRepaymentAmountMonth() {
		return repaymentAmountMonth;
	}

	public void setRepaymentAmountMonth(String repaymentAmountMonth) {
		this.repaymentAmountMonth = repaymentAmountMonth;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getActualDateRepayment() {
		return actualDateRepayment;
	}

	public void setActualDateRepayment(String actualDateRepayment) {
		this.actualDateRepayment = actualDateRepayment;
	}

	public String getActualPaymentAmount() {
		return actualPaymentAmount;
	}

	public void setActualPaymentAmount(String actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}

	public String getNowOverdueLimit() {
		return nowOverdueLimit;
	}

	public void setNowOverdueLimit(String nowOverdueLimit) {
		this.nowOverdueLimit = nowOverdueLimit;
	}

	public String getNowOverdueNumber() {
		return nowOverdueNumber;
	}

	public void setNowOverdueNumber(String nowOverdueNumber) {
		this.nowOverdueNumber = nowOverdueNumber;
	}

	public String getTotalOverdueNumber() {
		return totalOverdueNumber;
	}

	public void setTotalOverdueNumber(String totalOverdueNumber) {
		this.totalOverdueNumber = totalOverdueNumber;
	}

	public String getMaxOverdueNumber() {
		return maxOverdueNumber;
	}

	public void setMaxOverdueNumber(String maxOverdueNumber) {
		this.maxOverdueNumber = maxOverdueNumber;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getOverdueTwoMonth() {
		return overdueTwoMonth;
	}

	public void setOverdueTwoMonth(String overdueTwoMonth) {
		this.overdueTwoMonth = overdueTwoMonth;
	}

	public String getOverdueThreeMonth() {
		return overdueThreeMonth;
	}

	public void setOverdueThreeMonth(String overdueThreeMonth) {
		this.overdueThreeMonth = overdueThreeMonth;
	}

	public String getOverdueSixMonth() {
		return overdueSixMonth;
	}

	public void setOverdueSixMonth(String overdueSixMonth) {
		this.overdueSixMonth = overdueSixMonth;
	}

	public String getOverdueYearMonth() {
		return overdueYearMonth;
	}

	public void setOverdueYearMonth(String overdueYearMonth) {
		this.overdueYearMonth = overdueYearMonth;
	}

	public String getLoansTime() {
		return loansTime;
	}

	public void setLoansTime(String loansTime) {
		this.loansTime = loansTime;
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


	

}
