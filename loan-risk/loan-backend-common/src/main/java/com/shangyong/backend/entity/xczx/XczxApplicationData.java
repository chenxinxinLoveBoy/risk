package com.shangyong.backend.entity.xczx;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 91征信数据报告bean
 * @author ailiqiang
 * @date Wed Jul 26 18:13:27 CST 2017
 **/
public class XczxApplicationData extends BaseBo{

	/**主键id**/
	private String applicationDataId;
	
	/**申请单id**/
	private String buApplicationId;

	/**借款申请扩展表编号**/
	private String thirdpartyReportId;

	/**91征信查询唯一单号**/
	private String guid;

	/**借款类型 0.未知1.个人信贷2.个人抵押3.企业信贷4.企业抵押**/
	private String borrowType;

	/**借款状态 0.未知1.拒贷2.批贷已放款4.借款人放弃申请5.审核中6.待放款**/
	private String borrowState;

	/**合同金额 (单位:万元)**/
	private String borrowAmount;

	/**合同日期 0.未知 (使用时精确到月)日期Long类型 (毫秒)**/
	private String contractDate;

	/**批贷期数**/
	private String loanPeriod;

	/**还款状态 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清**/
	private String repayState;

	/**欠款金额 实际金额*100000 取整**/
	private String arrearsAmount;

	/**公司代码**/
	private String companyCode;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注信息**/
	private String remark;

	
	public String getBuApplicationId() {
		return buApplicationId;
	}

	public void setBuApplicationId(String buApplicationId) {
		this.buApplicationId = buApplicationId;
	}

	public String getApplicationDataId() {
		return applicationDataId;
	}

	public void setApplicationDataId(String applicationDataId) {
		this.applicationDataId = applicationDataId;
	}

	public String getThirdpartyReportId() {
		return thirdpartyReportId;
	}

	public void setThirdpartyReportId(String thirdpartyReportId) {
		this.thirdpartyReportId = thirdpartyReportId;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
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

	@Override
	public String toString() {
		return "XczxApplicationData [applicationDataId=" + applicationDataId + ", thirdpartyReportId="
				+ thirdpartyReportId + ", guid=" + guid + ", borrowType=" + borrowType + ", borrowState=" + borrowState
				+ ", borrowAmount=" + borrowAmount + ", contractDate=" + contractDate + ", loanPeriod=" + loanPeriod
				+ ", repayState=" + repayState + ", arrearsAmount=" + arrearsAmount + ", companyCode=" + companyCode
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
