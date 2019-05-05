package com.shangyong.backend.entity.xczx;
 

import com.shangyong.backend.common.baseEntityBo.BaseBo; 
public class XczxApplication extends BaseBo {

	/** 申请单编号 **/
	private String applicationId;

	/** APP应用客户编号 **/
	private String customerId;

	/** 客户姓名 **/
	private String realName;

	/** 身份证证件号码 **/
	private String idCard;

	/** 手机号 **/
	private String phoneNum;

	/**借款类型（0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押 **/
	private String borrowType;

	/** 借款状态（0.未知1.拒贷2.批贷已放款4.借款人放弃申请5.审核中6.待放款） **/
	private String borrowState;

	/** 合同金额（-7.[0,0.1) -6.[0.1,0.2) -5.[0.2,0.3) -4.[0.3,0.4) -3.[0.4,0.6) -2.[0.6,0.8) -1.[0.8,1) 0.未知 1.[1,2) 2.[2,4) 3.[4,6)(单位:万元) 2万一档依次类推） **/
	private String borrowAmount;

	/** 合同日期（精确到毫秒） **/
	private String contractDate;

	/** 批贷期数（借款状态为1、4、5,批贷期数为0） **/
	private String loanPeriod;

	/** 还款状态（0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清） **/
	private String repayState;

	/** 审批状态（1-待审批、2-审批通过、3-审批未通过、4-待人工确认） **/
	private String auditingState;

	/** 欠款金额（实际金额 * 100000 取整 （不要小数点）;当前客户逾期后应被催收的金额，如未出现逾期，请将此值赋为0） **/
	private String arrearsAmount;

	/** 公司代码（我公司分配的公司编码） **/
	private String companyCode;

	/** 创建时间 **/
	private String createTime;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public String getBorrowState() {
		return borrowState;
	}

	public void setBorrowState(String borrowState) {
		this.borrowState = borrowState;
	}

	public String getBorrowAmount() {
		return borrowAmount;
	}

	public void setBorrowAmount(String borrowAmount) {
		this.borrowAmount = borrowAmount;
	}

	public String getContractDate() {
		return contractDate;
	}

	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public String getRepayState() {
		return repayState;
	}

	public void setRepayState(String repayState) {
		this.repayState = repayState;
	}

	public String getAuditingState() {
		return auditingState;
	}

	public void setAuditingState(String auditingState) {
		this.auditingState = auditingState;
	}

	public String getArrearsAmount() {
		return arrearsAmount;
	}

	public void setArrearsAmount(String arrearsAmount) {
		this.arrearsAmount = arrearsAmount;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "XczxApplication [applicationId=" + applicationId + ", customerId=" + customerId + ", realName="
				+ realName + ", idCard=" + idCard + ", phoneNum=" + phoneNum + ", borrowType=" + borrowType
				+ ", borrowState=" + borrowState + ", borrowAmount=" + borrowAmount + ", contractDate=" + contractDate
				+ ", loanPeriod=" + loanPeriod + ", repayState=" + repayState + ", auditingState=" + auditingState
				+ ", arrearsAmount=" + arrearsAmount + ", companyCode=" + companyCode + ", createTime=" + createTime
				+ "]";
	}

	
	
}
