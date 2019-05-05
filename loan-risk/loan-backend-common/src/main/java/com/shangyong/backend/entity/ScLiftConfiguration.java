package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 提额配置表bean
 * 
 * @author xk
 * @date Wed Sep 13 18:51:24 CST 2017
 **/
public class ScLiftConfiguration extends BaseBo {

	/** 提额配置Id **/
	private Integer scLiftConfigurationId;

	/** 渠道名称 **/
	private String channelName;

	/** 客户标识（0-新客户，1-老客户） **/
	private Integer appLevel;

	/** 授信额度值 **/
	private String creditMoney;

	/** 是否有公积金（0-否，1-是） **/
	private Integer isAccumulationFund;

	/** 是否有社保（0-否，1-是） **/
	private Integer isSocialSecurity;

	/** 按时还款次数 **/
	private Integer repaymentTimes;

	/** 当天借当天还次数 **/
	private Integer todayTimes;

	/** 提额百分比 **/
	private String percentageOfLift;

	/** 提额尾数 **/
	private Integer percentageOfLiftMantissa;

	/** 版本编号 **/
	private Integer version;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 创建人姓名 **/
	private String createName;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 修改人姓名 **/
	private String modifyName;

	/** 备注 **/
	private String remark;

	/** 状态（01-正常、02-失效） **/
	private String state;

	/** 是否匹配姓名（0-否、1-是） **/
	private String mateName;

	/** 是否匹配手机号（0-否、1-是） **/
	private String matePhone;

	/** 是否匹配证件号码（0-否、1-是） **/
	private String mateCertCode;

	public ScLiftConfiguration() {
		super();
	}

	public ScLiftConfiguration(Integer scLiftConfigurationId, String channelName, Integer appLevel, String creditMoney,
			Integer isAccumulationFund, Integer isSocialSecurity, Integer repaymentTimes, Integer todayTimes,
			String percentageOfLift, Integer percentageOfLiftMantissa, Integer version, String createTime,
			String createMan, String createName, String modifyTime, String modifyMan, String modifyName, String remark,
			String state, String mateName, String matePhone, String mateCertCode) {
		super();
		this.scLiftConfigurationId = scLiftConfigurationId;
		this.channelName = channelName;
		this.appLevel = appLevel;
		this.creditMoney = creditMoney;
		this.isAccumulationFund = isAccumulationFund;
		this.isSocialSecurity = isSocialSecurity;
		this.repaymentTimes = repaymentTimes;
		this.todayTimes = todayTimes;
		this.percentageOfLift = percentageOfLift;
		this.percentageOfLiftMantissa = percentageOfLiftMantissa;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.createName = createName;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.modifyName = modifyName;
		this.remark = remark;
		this.state = state;
		this.mateName = mateName;
		this.matePhone = matePhone;
		this.mateCertCode = mateCertCode;
	}

	public Integer getScLiftConfigurationId() {
		return scLiftConfigurationId;
	}

	public void setScLiftConfigurationId(Integer scLiftConfigurationId) {
		this.scLiftConfigurationId = scLiftConfigurationId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(Integer appLevel) {
		this.appLevel = appLevel;
	}

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public Integer getIsAccumulationFund() {
		return isAccumulationFund;
	}

	public void setIsAccumulationFund(Integer isAccumulationFund) {
		this.isAccumulationFund = isAccumulationFund;
	}

	public Integer getIsSocialSecurity() {
		return isSocialSecurity;
	}

	public void setIsSocialSecurity(Integer isSocialSecurity) {
		this.isSocialSecurity = isSocialSecurity;
	}

	public Integer getRepaymentTimes() {
		return repaymentTimes;
	}

	public void setRepaymentTimes(Integer repaymentTimes) {
		this.repaymentTimes = repaymentTimes;
	}

	public Integer getTodayTimes() {
		return todayTimes;
	}

	public void setTodayTimes(Integer todayTimes) {
		this.todayTimes = todayTimes;
	}

	public String getPercentageOfLift() {
		return percentageOfLift;
	}

	public void setPercentageOfLift(String percentageOfLift) {
		this.percentageOfLift = percentageOfLift;
	}

	public Integer getPercentageOfLiftMantissa() {
		return percentageOfLiftMantissa;
	}

	public void setPercentageOfLiftMantissa(Integer percentageOfLiftMantissa) {
		this.percentageOfLiftMantissa = percentageOfLiftMantissa;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMatePhone() {
		return matePhone;
	}

	public void setMatePhone(String matePhone) {
		this.matePhone = matePhone;
	}

	public String getMateCertCode() {
		return mateCertCode;
	}

	public void setMateCertCode(String mateCertCode) {
		this.mateCertCode = mateCertCode;
	}

}
