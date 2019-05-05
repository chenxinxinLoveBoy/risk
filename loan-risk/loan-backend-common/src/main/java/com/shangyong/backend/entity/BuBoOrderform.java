package com.shangyong.backend.entity;

import java.math.BigDecimal;
import java.util.Date;

public class BuBoOrderform {
	private String boOrderformId;

	private String platformId;

	private String useId;

	private String applicationId;

	private String customerId;

	private String name;

	private String certType;

	private String certCode;

	private String phoneNum;

	private String finaProductId;

	private Date orderTime;

	private BigDecimal productQuota;

	private BigDecimal realQuota;

	private Integer days;

	private BigDecimal productRate;

	private BigDecimal inquiryFee;

	private BigDecimal managementCost;

	private BigDecimal sumRenewalMoney;

	private Integer renewalNum;

	private BigDecimal sumLoanInterest;

	private Date originalRepayDate;

	private Date newRepayDate;

	private Date actualRepayDate;

	private BigDecimal repayMoney;

	private BigDecimal rpSumMoney;

	private BigDecimal sumDelayMoney;

	private Integer delayDays;

	private String repayType;

	private String overdueMark;

	private String state;

	private Date createTime;

	private String createMan;

	private Date modifyTime;

	private String modifyMan;

	private String remark;

//	/** 应还款金额 **/
//	private BigDecimal needRepayMoney;// 应还款金额=产品额度+借款利息总金额+逾期费总金额
//
//	public BigDecimal getNeedRepayMoney() {
//		needRepayMoney=new BigDecimal(0);//初始化
//
//		if (productQuota != null) {
//			needRepayMoney = needRepayMoney.add(productQuota);
//		}
//		if (sumDelayMoney != null) {
//			needRepayMoney = needRepayMoney.add(sumDelayMoney);
//		}
//		if (sumLoanInterest != null) {
//			needRepayMoney = needRepayMoney.add(sumLoanInterest);
//		}
//		return needRepayMoney;
//	}

//	public void setNeedRepayMoney(BigDecimal needRepayMoney) {
//		this.needRepayMoney = needRepayMoney;
//	}

	public String getBoOrderformId() {
		return boOrderformId;
	}

	public void setBoOrderformId(String boOrderformId) {
		this.boOrderformId = boOrderformId == null ? null : boOrderformId.trim();
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId == null ? null : platformId.trim();
	}

	public String getUseId() {
		return useId;
	}

	public void setUseId(String useId) {
		this.useId = useId == null ? null : useId.trim();
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId == null ? null : applicationId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType == null ? null : certType.trim();
	}

	public String getCertCode() {
		return certCode;
	}

	public void setCertCode(String certCode) {
		this.certCode = certCode == null ? null : certCode.trim();
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum == null ? null : phoneNum.trim();
	}

	public String getFinaProductId() {
		return finaProductId;
	}

	public void setFinaProductId(String finaProductId) {
		this.finaProductId = finaProductId == null ? null : finaProductId.trim();
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public BigDecimal getProductQuota() {
		return productQuota;
	}

	public void setProductQuota(BigDecimal productQuota) {
		this.productQuota = productQuota;
	}

	public BigDecimal getRealQuota() {
		return realQuota;
	}

	public void setRealQuota(BigDecimal realQuota) {
		this.realQuota = realQuota;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public BigDecimal getInquiryFee() {
		return inquiryFee;
	}

	public void setInquiryFee(BigDecimal inquiryFee) {
		this.inquiryFee = inquiryFee;
	}

	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	public BigDecimal getSumRenewalMoney() {
		return sumRenewalMoney;
	}

	public void setSumRenewalMoney(BigDecimal sumRenewalMoney) {
		this.sumRenewalMoney = sumRenewalMoney;
	}

	public Integer getRenewalNum() {
		return renewalNum;
	}

	public void setRenewalNum(Integer renewalNum) {
		this.renewalNum = renewalNum;
	}

	public BigDecimal getSumLoanInterest() {
		return sumLoanInterest;
	}

	public void setSumLoanInterest(BigDecimal sumLoanInterest) {
		this.sumLoanInterest = sumLoanInterest;
	}

	public Date getOriginalRepayDate() {
		return originalRepayDate;
	}

	public void setOriginalRepayDate(Date originalRepayDate) {
		this.originalRepayDate = originalRepayDate;
	}

	public Date getNewRepayDate() {
		return newRepayDate;
	}

	public void setNewRepayDate(Date newRepayDate) {
		this.newRepayDate = newRepayDate;
	}

	public Date getActualRepayDate() {
		return actualRepayDate;
	}

	public void setActualRepayDate(Date actualRepayDate) {
		this.actualRepayDate = actualRepayDate;
	}

	public BigDecimal getRepayMoney() {
		return repayMoney;
	}

	public void setRepayMoney(BigDecimal repayMoney) {
		this.repayMoney = repayMoney;
	}

	public BigDecimal getRpSumMoney() {
		return rpSumMoney;
	}

	public void setRpSumMoney(BigDecimal rpSumMoney) {
		this.rpSumMoney = rpSumMoney;
	}

	public BigDecimal getSumDelayMoney() {
		return sumDelayMoney;
	}

	public void setSumDelayMoney(BigDecimal sumDelayMoney) {
		this.sumDelayMoney = sumDelayMoney;
	}

	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType == null ? null : repayType.trim();
	}

	public String getOverdueMark() {
		return overdueMark;
	}

	public void setOverdueMark(String overdueMark) {
		this.overdueMark = overdueMark == null ? null : overdueMark.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan == null ? null : createMan.trim();
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan == null ? null : modifyMan.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}