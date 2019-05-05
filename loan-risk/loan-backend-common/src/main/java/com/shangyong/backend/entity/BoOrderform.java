package com.shangyong.backend.entity;

import java.math.BigDecimal;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 订单信息entity对象
 * @author xixinghui
 *
 */
public class BoOrderform extends BaseBo{
	
	/**闪贷订单编号**/
	private String boOrderformId;

	/**平台用户编号**/
	private String platformId;

	/**使用编号**/
	private String useId;

	/**申请单编号**/
	private String applicationId;

	/**APP应用客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**证件类型 1.身份证 2.护照 3.其他**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**金融产品编号**/
	private String finaProductId;

	/**订单生成时间**/
	private String orderTime;

	/**产品额度**/
	private String productQuota;

	/**实际放款金额**/
	private String realQuota;

	/**周期**/
	private Integer days;

	/**产品利率**/
	private String productRate;

	/**信审查询费**/
	private String inquiryFee;

	/**账户管理费**/
	private String managementCost;

	/**续期费总金额**/
	private String sumRenewalMoney;

	/**续期次数**/
	private Integer renewalNum;

	/**借款利息总金额**/
	private String sumLoanInterest;

	/**原始应还款日期**/
	private String originalRepayDate;

	/**最新应还款日期**/
	private String newRepayDate;

	/**实际还款日期**/
	private String actualRepayDate;

	/**还款金额**/
	private String repayMoney;

	/**红包抵扣利息总金额**/
	private String rpSumMoney;

	/**逾期费总金额**/
	private String sumDelayMoney;

	/**逾期天数**/
	private Integer delayDays;

	/**还款方式(自动扣款1，人工还款2)**/
	private String repayType;

	/**逾期标志（1-是，2-否）**/
	private String overdueMark;

	/**订单交易状态（待还款1，已还款2）**/
	private String state;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;
	
	/**App平台**/
	private String appName;
	
	/**开始时间**/
	private String startTime;
	
	/**结束时间**/
	private String endTime;
	
	/**应还款金额**/
	private String needRepayMoney;

	public BoOrderform() {
		super();
	}
	public BoOrderform(String boOrderformId,String platformId,String useId,String applicationId,String customerId,String name,String certType,String certCode,String phoneNum,String finaProductId,String orderTime,String productQuota,String realQuota,Integer days,String productRate,String inquiryFee,String managementCost,String sumRenewalMoney,Integer renewalNum,String sumLoanInterest,String originalRepayDate,String newRepayDate,String actualRepayDate,String repayMoney,String rpSumMoney,String sumDelayMoney,Integer delayDays,String repayType,String overdueMark,String state,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.boOrderformId = boOrderformId;
		this.platformId = platformId;
		this.useId = useId;
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.finaProductId = finaProductId;
		this.orderTime = orderTime;
		this.productQuota = productQuota;
		this.realQuota = realQuota;
		this.days = days;
		this.productRate = productRate;
		this.inquiryFee = inquiryFee;
		this.managementCost = managementCost;
		this.sumRenewalMoney = sumRenewalMoney;
		this.renewalNum = renewalNum;
		this.sumLoanInterest = sumLoanInterest;
		this.originalRepayDate = originalRepayDate;
		this.newRepayDate = newRepayDate;
		this.actualRepayDate = actualRepayDate;
		this.repayMoney = repayMoney;
		this.rpSumMoney = rpSumMoney;
		this.sumDelayMoney = sumDelayMoney;
		this.delayDays = delayDays;
		this.repayType = repayType;
		this.overdueMark = overdueMark;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}

	public String getBoOrderformId() {
		return boOrderformId;
	}
	
	public void setBoOrderformId(String boOrderformId) {
		this.boOrderformId = boOrderformId;
	}
	
	public String getPlatformId() {
		return platformId;
	}
	
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	
	public String getUseId() {
		return useId;
	}
	
	public void setUseId(String useId) {
		this.useId = useId;
	}
	
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCertType() {
		return certType;
	}
	
	public void setCertType(String certType) {
		this.certType = certType;
	}
	
	public String getCertCode() {
		return certCode;
	}
	
	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public String getFinaProductId() {
		return finaProductId;
	}
	
	public void setFinaProductId(String finaProductId) {
		this.finaProductId = finaProductId;
	}
	
	public String getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public String getProductQuota() {
		return productQuota;
	}
	
	public void setProductQuota(String productQuota) {
		this.productQuota = productQuota;
	}
	
	public String getRealQuota() {
		return realQuota;
	}
	
	public void setRealQuota(String realQuota) {
		this.realQuota = realQuota;
	}
	
	public Integer getDays() {
		return days;
	}
	
	public void setDays(Integer days) {
		this.days = days;
	}
	
	public String getProductRate() {
		return productRate;
	}
	
	public void setProductRate(String productRate) {
		this.productRate = productRate;
	}
	
	public String getInquiryFee() {
		return inquiryFee;
	}
	
	public void setInquiryFee(String inquiryFee) {
		this.inquiryFee = inquiryFee;
	}
	
	public String getManagementCost() {
		return managementCost;
	}
	
	public void setManagementCost(String managementCost) {
		this.managementCost = managementCost;
	}
	
	public String getSumRenewalMoney() {
		return sumRenewalMoney;
	}
	
	public void setSumRenewalMoney(String sumRenewalMoney) {
		this.sumRenewalMoney = sumRenewalMoney;
	}
	
	public Integer getRenewalNum() {
		return renewalNum;
	}
	
	public void setRenewalNum(Integer renewalNum) {
		this.renewalNum = renewalNum;
	}
	
	public String getSumLoanInterest() {
		return sumLoanInterest;
	}
	
	public void setSumLoanInterest(String sumLoanInterest) {
		this.sumLoanInterest = sumLoanInterest;
	}
	
	public String getOriginalRepayDate() {
		return originalRepayDate;
	}
	
	public void setOriginalRepayDate(String originalRepayDate) {
		this.originalRepayDate = originalRepayDate;
	}
	
	public String getNewRepayDate() {
		return newRepayDate;
	}
	
	public void setNewRepayDate(String newRepayDate) {
		this.newRepayDate = newRepayDate;
	}
	
	public String getActualRepayDate() {
		return actualRepayDate;
	}
	
	public void setActualRepayDate(String actualRepayDate) {
		this.actualRepayDate = actualRepayDate;
	}
	
	public String getRepayMoney() {
		return repayMoney;
	}
	
	public void setRepayMoney(String repayMoney) {
		this.repayMoney = repayMoney;
	}
	
	public String getRpSumMoney() {
		return rpSumMoney;
	}
	
	public void setRpSumMoney(String rpSumMoney) {
		this.rpSumMoney = rpSumMoney;
	}
	
	public String getSumDelayMoney() {
		return sumDelayMoney;
	}
	
	public void setSumDelayMoney(String sumDelayMoney) {
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
		this.repayType = repayType;
	}
	
	public String getOverdueMark() {
		return overdueMark;
	}
	
	public void setOverdueMark(String overdueMark) {
		this.overdueMark = overdueMark;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
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
	
	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
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
	
	public String getNeedRepayMoney() {
		BigDecimal productMoney = new BigDecimal(productQuota);
		if (sumDelayMoney != null) {
			productMoney = productMoney.add(new BigDecimal(sumDelayMoney));
		}
		if (sumLoanInterest != null) {
			productMoney = productMoney.add(new BigDecimal(sumLoanInterest));
		}
		needRepayMoney = productMoney.toString();
		return needRepayMoney;
	}
	
	public void setNeedRepayMoney(String needRepayMoney) {
		this.needRepayMoney = needRepayMoney;
	}

}
