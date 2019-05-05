package com.shangyong.backend.entity;


import com.shangyong.backend.common.baseEntityBo.BaseBo;
import com.shangyong.backend.common.baseEntityBo.PageHelperBo;

/**
 * 客户提现申请记录表bean
 * @author kenzhao
 * @date Fri Sep 15 22:55:03 CST 2017
 **/
public class BuPresentApplication  extends PageHelperBo {

	/**提取申请单编号**/
	private String presentApplicationId;

	/**APP应用请求流水号**/
	private String appSerialNumber;

	/**提现申请单业务编号**/
	private String presentApplicationBuId;

	/**APP名称：1-闪贷；2-速贷**/
	private Integer appName;

	/**APP应用客户编号**/
	private String customerId;

	/**客户姓名**/
	private String name;

	/**证件类型（1.身份证 2.护照 3.其他）**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**距离审核通过日期天数**/
	private Integer days;

	/**提现用户公网IP**/
	private String loanIp;

	/**提现审核时间**/
	private String auditingTime;

	/**提现审批状态（1-待审批、2-审批通过、3-审批未通过）**/
	private String auditingState;

	/**申请来源（0——Android；1——IOS）**/
	private String source;

	/**提现包规则对应编号**/
	private String banCode;

	/**审批结果描述**/
	private String auditResult;

	/**外部系统交互标识（0-未推送App、1-已推送APP）**/
	private String isPushApp;

	/**步骤标识（0：待处理、1：同盾、2：白骑士）**/
	private String isStep;

	/**步骤处理异常描述**/
	private String errorDescription;

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

	/**失败次数**/
	private Integer failureTimes;


	/**失败次数-最小**/
	private Integer failureTimesMin;
	/**失败次数-最小**/
	private Integer failureTimesMax;

	/**提现包分发时间**/
	private String presentDistributionTime;

	/**提现包主键ID**/
	private Integer presentRuleId;

	/**客户标识（0-新客户，1-老客户）**/
	private Integer appLevel;

	/**app下载渠道**/
	private String appChannel;

	/**App版本号**/
	private String appVersion;

	/** 交互类型 0.同步(dubbo) 1.异步(mq) **/
	private Integer actionType;

	/**提现类型（1.新用户提现 2.老用户复借 大于等于20天 3.老用户复借 小于20天 4. 续借）**/
	private Integer presentType;

	/** 同盾授权的token **/
	private String tongdunToken;

	/** 芝麻信用分的授权openid **/
	private String zhiMaOpenId;

	/** 自填的公司名称 **/
	private String companyName;


	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;

	/**
	 * APP的保留字段，推送时需要返回
	 */
	private String rechargeInfo;
	/**
	 * 提现类型
	 */
	private String presentSort;

	/**
	 * 聚信立
	 */
	private String jxlToken;

	public BuPresentApplication(){

	}

	public BuPresentApplication(String presentApplicationId, String appSerialNumber, String presentApplicationBuId, Integer appName, String customerId, String name, String certType, String certCode, String phoneNum, Integer days, String loanIp, String auditingTime, String auditingState, String source, String banCode, String auditResult, String isPushApp, String isStep, String errorDescription, String createTime, String createMan, String modifyTime, String modifyMan, String remark, Integer failureTimes, String presentDistributionTime, Integer presentRuleId, Integer appLevel, String appChannel, String appVersion, Integer actionType, Integer presentType, String tongdunToken, String zhiMaOpenId, String companyName, String startTime, String endTime, String rechargeInfo, String presentSort, String jxlToken) {
		this.presentApplicationId = presentApplicationId;
		this.appSerialNumber = appSerialNumber;
		this.presentApplicationBuId = presentApplicationBuId;
		this.appName = appName;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.days = days;
		this.loanIp = loanIp;
		this.auditingTime = auditingTime;
		this.auditingState = auditingState;
		this.source = source;
		this.banCode = banCode;
		this.auditResult = auditResult;
		this.isPushApp = isPushApp;
		this.isStep = isStep;
		this.errorDescription = errorDescription;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.failureTimes = failureTimes;
		this.presentDistributionTime = presentDistributionTime;
		this.presentRuleId = presentRuleId;
		this.appLevel = appLevel;
		this.appChannel = appChannel;
		this.appVersion = appVersion;
		this.actionType = actionType;
		this.presentType = presentType;
		this.tongdunToken = tongdunToken;
		this.zhiMaOpenId = zhiMaOpenId;
		this.companyName = companyName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.rechargeInfo = rechargeInfo;
		this.presentSort = presentSort;
		this.jxlToken = jxlToken;
	}

	public Integer getFailureTimesMin() {
		return failureTimesMin;
	}

	public void setFailureTimesMin(Integer failureTimesMin) {
		this.failureTimesMin = failureTimesMin;
	}

	public Integer getFailureTimesMax() {
		return failureTimesMax;
	}

	public void setFailureTimesMax(Integer failureTimesMax) {
		this.failureTimesMax = failureTimesMax;
	}

	public String getJxlToken() {
		return jxlToken;
	}

	public void setJxlToken(String jxlToken) {
		this.jxlToken = jxlToken;
	}

	public String getZhiMaOpenId() {
		return zhiMaOpenId;
	}

	public void setZhiMaOpenId(String zhiMaOpenId) {
		this.zhiMaOpenId = zhiMaOpenId;
	}

	public String getPresentApplicationId() {
		return presentApplicationId;
	}

	public void setPresentApplicationId(String presentApplicationId) {
		this.presentApplicationId = presentApplicationId;
	}

	public String getAppSerialNumber() {
		return appSerialNumber;
	}

	public void setAppSerialNumber(String appSerialNumber) {
		this.appSerialNumber = appSerialNumber;
	}

	public String getPresentApplicationBuId() {
		return presentApplicationBuId;
	}

	public void setPresentApplicationBuId(String presentApplicationBuId) {
		this.presentApplicationBuId = presentApplicationBuId;
	}

	public Integer getAppName() {
		return appName;
	}

	public void setAppName(Integer appName) {
		this.appName = appName;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getLoanIp() {
		return loanIp;
	}

	public void setLoanIp(String loanIp) {
		this.loanIp = loanIp;
	}

	public String getAuditingTime() {
		return auditingTime;
	}

	public void setAuditingTime(String auditingTime) {
		this.auditingTime = auditingTime;
	}

	public String getAuditingState() {
		return auditingState;
	}

	public void setAuditingState(String auditingState) {
		this.auditingState = auditingState;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getIsPushApp() {
		return isPushApp;
	}

	public void setIsPushApp(String isPushApp) {
		this.isPushApp = isPushApp;
	}

	public String getIsStep() {
		return isStep;
	}

	public void setIsStep(String isStep) {
		this.isStep = isStep;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
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

	public Integer getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(Integer failureTimes) {
		this.failureTimes = failureTimes;
	}

	public String getPresentDistributionTime() {
		return presentDistributionTime;
	}

	public void setPresentDistributionTime(String presentDistributionTime) {
		this.presentDistributionTime = presentDistributionTime;
	}

	public Integer getPresentRuleId() {
		return presentRuleId;
	}

	public void setPresentRuleId(Integer presentRuleId) {
		this.presentRuleId = presentRuleId;
	}

	public Integer getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(Integer appLevel) {
		this.appLevel = appLevel;
	}

	public String getAppChannel() {
		return appChannel;
	}

	public void setAppChannel(String appChannel) {
		this.appChannel = appChannel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public Integer getPresentType() {
		return presentType;
	}

	public void setPresentType(Integer presentType) {
		this.presentType = presentType;
	}

	public String getTongdunToken() {
		return tongdunToken;
	}

	public void setTongdunToken(String tongdunToken) {
		this.tongdunToken = tongdunToken;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getRechargeInfo() {
		return rechargeInfo;
	}

	public void setRechargeInfo(String rechargeInfo) {
		this.rechargeInfo = rechargeInfo;
	}

	public String getPresentSort() {
		return presentSort;
	}

	public void setPresentSort(String presentSort) {
		this.presentSort = presentSort;
	}
}
