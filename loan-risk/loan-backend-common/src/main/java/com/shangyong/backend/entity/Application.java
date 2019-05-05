package com.shangyong.backend.entity;
 

import java.util.Arrays;

import com.shangyong.backend.common.baseEntityBo.BaseBo; 
public class Application extends BaseBo {

	/** 申请单编号 **/
	private String applicationId;

	/** APP应用请求流水号 **/
	private String appSerialNumber;

	/** 平台用户编号 **/
	private String platformId;

	/** 申请单业务编号 **/
	private String applicationBuId;

	/** APP名称：1-闪贷；2-速贷 **/
	private String appName;

	/** APP应用客户编号 **/
	private String customerId;

	/** 客户姓名 **/
	private String name;

	/** 证件类型（1.身份证 2.护照 3.其他） **/
	private String certType;

	/** 证件号码 **/
	private String certCode;

	/** 手机号 **/
	private String phoneNum;

	/** 授信额度值 **/
	private String creditMoney;

	/** 产品额度 **/
	private String productQuota;

	/** 周期 **/
	private Integer days;

	/** 评估分数值 **/
	private String appraiseMoney;

	/** 借款用户公网IP **/
	private String loanIp;

	/** 审核时间 **/
	private String auditingTime;

	/** 审批状态（1-待审批、2-审批通过、3-审批未通过、4-待人工确认、 5:信息重新采集） **/
	private String auditingState;

	/** 申请来源（0——Android；1——IOS） **/
	private String source;

	/** 同盾black_box **/
	private String tdBlackBox;

	/** 聚信立用户token **/
	private String jxlUseToken;

	/** 同盾reportId **/
	private String reportId;

	/** 审批结果描述 **/
	private String auditResult;

	/** 步骤标识 **/
	private String isStep;

	/** 是否通知App（0-否、1-是  2 已发送到大数据 3已回调大数据） **/
	private String isPushApp;

	/** 芝麻用户标识：芝麻会员在商户端的身份标识 **/
	private String openId;

	/** 审批人用户编号 **/
	private String auditMan;

	/**运营商平台（jxl-聚信立，bqs-白骑士，sjmh-数聚魔盒）**/
	private String mobileWebsite;
	
	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 备注 **/
	private String remark;

	/** 开始时间 **/
	private String startTime;

	/** 结束时间 **/
	private String endTime;

	/** 禁止项规则对应编号 **/
	private String banCode;

	/** 步骤处理异常描述 **/
	private String errorDescription;

	/** 评分小类编号 **/
	private String scoreSmallId;

	/** 禁止项模板主键ID **/
	private String banCodeTplId;

	/** 欺诈项模板主键ID **/
	private String fraudTplId;

	/** 评分项模板主键ID **/
	private String scoreTplId;
	
	/** 决策树ID **/
	private String decisionTreeId;
	
	/** 模板分发时间 **/
	private String distributionTime;

	/** 失败次数 **/
	private String failureTimes;
	
	/**多选操作**/
	private String [] ids;
	
	/** 葫芦Token **/
	private String hlUserToken;
	
	/**客户标识(0:新客户 1：老客户)**/
	private String appLevel;
	
	/**app渠道标识**/
	private String appChannel;
	
	/**APP版本号**/
	private String appVersion;
	
	/**用户已还款次数**/
	private int refundConut;
	
	/**用户逾期次数**/
	private int overdueCount;
	
	/**查询标识**/
	private String flag;
	
	/**是否同步大数据, 0-否、1-是**/
	private String isHbaseSyn;
	    
	/**
	 * 人工审批日期
	 */
	private String rgAuditingTime;
	
	private String endRgAuditingTime; 
	
	/**
	 * 人工审核额度  
	 */
	private String rgCreditMoney;
	
	/**
	 * 人工审批状态（1-待审批、2-审批通过、3-审批未通过）   
	 */
	private String rgAuditingState;
	
	/**
	 *  领取人姓名    
	 */
	private String receiveName;
	/**
	 * 领取人编号  
	 */
	private String receiveCode ;
	
	/**
	 * 是否与本人电核（0：未电核，1：已电核）   
	 */
	private String isCallPhone;
	/**
	 * 网查无异常（0：无异常，1：有异常
	 */
	private String netcheckNoAbnormal;
	/**
	 * 人工审核备注
	 */
	private String rgAuditingRemark;
	/**
	 * MQ用的参数
	 * cu_customer_check_apply表的主键id
	 */
	private String customerCheckApplyId;
	/**
	 * 拒绝原因 (1-禁止项拒绝，2-模型拒绝，3-人工审核拒绝。0-默认值)
	 */
	private String auditingRejectCode;
	/**
	 * 是否已调用app步骤号修改接口(0-未调用，1-已调用）
	 */
	private int isCallSucess;
	/**
	 * 异常状态
	 */
	private String ycNumber;

	/** 芝麻分数 **/
	private String zhiMaScore;

	/** 黑名单数据来源 **/
	private String blacklistDsSource;

	/** 禁止项分类 **/
	private String banClassCode;
	
	public Application() {
		super();
	}

	public Application(String applicationId, String appSerialNumber, String platformId, String applicationBuId,
					   String appName, String customerId, String name, String certType, String certCode, String phoneNum,
					   String creditMoney, String productQuota, Integer days, String appraiseMoney, String loanIp,
					   String auditingTime, String auditingState, String source, String tdBlackBox, String jxlUseToken,
					   String reportId, String auditResult, String isStep, String isPushApp, String openId, String auditMan,
					   String mobileWebsite, String createTime, String createMan, String modifyTime, String modifyMan,
					   String remark, String startTime, String endTime, String banCode, String errorDescription,
					   String scoreSmallId, String banCodeTplId, String fraudTplId, String scoreTplId, String decisionTreeId,
					   String distributionTime, String failureTimes, String[] ids, String hlUserToken, String appLevel,
					   String appChannel, String appVersion, int refundConut, int overdueCount, String flag,
					   String isHbaseSyn, String rgAuditingTime, String endRgAuditingTime, String rgCreditMoney,
					   String rgAuditingState, String receiveName, String receiveCode, String isCallPhone,
					   String netcheckNoAbnormal, String rgAuditingRemark, String customerCheckApplyId,
					   String auditingRejectCode, int isCallSucess, String ycNumber, String zhiMaScore,
					   String blacklistDsSource, String banClassCode) {
		this.applicationId = applicationId;
		this.appSerialNumber = appSerialNumber;
		this.platformId = platformId;
		this.applicationBuId = applicationBuId;
		this.appName = appName;
		this.customerId = customerId;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.creditMoney = creditMoney;
		this.productQuota = productQuota;
		this.days = days;
		this.appraiseMoney = appraiseMoney;
		this.loanIp = loanIp;
		this.auditingTime = auditingTime;
		this.auditingState = auditingState;
		this.source = source;
		this.tdBlackBox = tdBlackBox;
		this.jxlUseToken = jxlUseToken;
		this.reportId = reportId;
		this.auditResult = auditResult;
		this.isStep = isStep;
		this.isPushApp = isPushApp;
		this.openId = openId;
		this.auditMan = auditMan;
		this.mobileWebsite = mobileWebsite;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.startTime = startTime;
		this.endTime = endTime;
		this.banCode = banCode;
		this.errorDescription = errorDescription;
		this.scoreSmallId = scoreSmallId;
		this.banCodeTplId = banCodeTplId;
		this.fraudTplId = fraudTplId;
		this.scoreTplId = scoreTplId;
		this.decisionTreeId = decisionTreeId;
		this.distributionTime = distributionTime;
		this.failureTimes = failureTimes;
		this.ids = ids;
		this.hlUserToken = hlUserToken;
		this.appLevel = appLevel;
		this.appChannel = appChannel;
		this.appVersion = appVersion;
		this.refundConut = refundConut;
		this.overdueCount = overdueCount;
		this.flag = flag;
		this.isHbaseSyn = isHbaseSyn;
		this.rgAuditingTime = rgAuditingTime;
		this.endRgAuditingTime = endRgAuditingTime;
		this.rgCreditMoney = rgCreditMoney;
		this.rgAuditingState = rgAuditingState;
		this.receiveName = receiveName;
		this.receiveCode = receiveCode;
		this.isCallPhone = isCallPhone;
		this.netcheckNoAbnormal = netcheckNoAbnormal;
		this.rgAuditingRemark = rgAuditingRemark;
		this.customerCheckApplyId = customerCheckApplyId;
		this.auditingRejectCode = auditingRejectCode;
		this.isCallSucess = isCallSucess;
		this.ycNumber = ycNumber;
		this.zhiMaScore = zhiMaScore;
		this.blacklistDsSource = blacklistDsSource;
		this.banClassCode = banClassCode;
	}

	public String getBlacklistDsSource() {
		return blacklistDsSource;
	}

	public void setBlacklistDsSource(String blacklistDsSource) {
		this.blacklistDsSource = blacklistDsSource;
	}

	public String getBanClassCode() {
		return banClassCode;
	}

	public void setBanClassCode(String banClassCode) {
		this.banClassCode = banClassCode;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAppSerialNumber() {
		return appSerialNumber;
	}

	public void setAppSerialNumber(String appSerialNumber) {
		this.appSerialNumber = appSerialNumber;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getApplicationBuId() {
		return applicationBuId;
	}

	public void setApplicationBuId(String applicationBuId) {
		this.applicationBuId = applicationBuId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
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

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getProductQuota() {
		return productQuota;
	}

	public void setProductQuota(String productQuota) {
		this.productQuota = productQuota;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getAppraiseMoney() {
		return appraiseMoney;
	}

	public void setAppraiseMoney(String appraiseMoney) {
		this.appraiseMoney = appraiseMoney;
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

	public String getTdBlackBox() {
		return tdBlackBox;
	}

	public void setTdBlackBox(String tdBlackBox) {
		this.tdBlackBox = tdBlackBox;
	}

	public String getJxlUseToken() {
		return jxlUseToken;
	}

	public void setJxlUseToken(String jxlUseToken) {
		this.jxlUseToken = jxlUseToken;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getIsStep() {
		return isStep;
	}

	public void setIsStep(String isStep) {
		this.isStep = isStep;
	}

	public String getIsPushApp() {
		return isPushApp;
	}

	public void setIsPushApp(String isPushApp) {
		this.isPushApp = isPushApp;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAuditMan() {
		return auditMan;
	}

	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}

	public String getMobileWebsite() {
		return mobileWebsite;
	}

	public void setMobileWebsite(String mobileWebsite) {
		this.mobileWebsite = mobileWebsite;
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

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getScoreSmallId() {
		return scoreSmallId;
	}

	public void setScoreSmallId(String scoreSmallId) {
		this.scoreSmallId = scoreSmallId;
	}

	public String getBanCodeTplId() {
		return banCodeTplId;
	}

	public void setBanCodeTplId(String banCodeTplId) {
		this.banCodeTplId = banCodeTplId;
	}

	public String getFraudTplId() {
		return fraudTplId;
	}

	public void setFraudTplId(String fraudTplId) {
		this.fraudTplId = fraudTplId;
	}

	public String getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(String scoreTplId) {
		this.scoreTplId = scoreTplId;
	}

	public String getDecisionTreeId() {
		return decisionTreeId;
	}

	public void setDecisionTreeId(String decisionTreeId) {
		this.decisionTreeId = decisionTreeId;
	}

	public String getDistributionTime() {
		return distributionTime;
	}

	public void setDistributionTime(String distributionTime) {
		this.distributionTime = distributionTime;
	}

	public String getFailureTimes() {
		return failureTimes;
	}

	public void setFailureTimes(String failureTimes) {
		this.failureTimes = failureTimes;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getHlUserToken() {
		return hlUserToken;
	}

	public void setHlUserToken(String hlUserToken) {
		this.hlUserToken = hlUserToken;
	}

	public String getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(String appLevel) {
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

	public int getRefundConut() {
		return refundConut;
	}

	public void setRefundConut(int refundConut) {
		this.refundConut = refundConut;
	}

	public int getOverdueCount() {
		return overdueCount;
	}

	public void setOverdueCount(int overdueCount) {
		this.overdueCount = overdueCount;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsHbaseSyn() {
		return isHbaseSyn;
	}

	public void setIsHbaseSyn(String isHbaseSyn) {
		this.isHbaseSyn = isHbaseSyn;
	}

	public String getRgAuditingTime() {
		return rgAuditingTime;
	}

	public void setRgAuditingTime(String rgAuditingTime) {
		this.rgAuditingTime = rgAuditingTime;
	}

	public String getEndRgAuditingTime() {
		return endRgAuditingTime;
	}

	public void setEndRgAuditingTime(String endRgAuditingTime) {
		this.endRgAuditingTime = endRgAuditingTime;
	}

	public String getRgCreditMoney() {
		return rgCreditMoney;
	}

	public void setRgCreditMoney(String rgCreditMoney) {
		this.rgCreditMoney = rgCreditMoney;
	}

	public String getRgAuditingState() {
		return rgAuditingState;
	}

	public void setRgAuditingState(String rgAuditingState) {
		this.rgAuditingState = rgAuditingState;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveCode() {
		return receiveCode;
	}

	public void setReceiveCode(String receiveCode) {
		this.receiveCode = receiveCode;
	}

	public String getIsCallPhone() {
		return isCallPhone;
	}

	public void setIsCallPhone(String isCallPhone) {
		this.isCallPhone = isCallPhone;
	}

	public String getNetcheckNoAbnormal() {
		return netcheckNoAbnormal;
	}

	public void setNetcheckNoAbnormal(String netcheckNoAbnormal) {
		this.netcheckNoAbnormal = netcheckNoAbnormal;
	}

	public String getRgAuditingRemark() {
		return rgAuditingRemark;
	}

	public void setRgAuditingRemark(String rgAuditingRemark) {
		this.rgAuditingRemark = rgAuditingRemark;
	}

	public String getCustomerCheckApplyId() {
		return customerCheckApplyId;
	}

	public void setCustomerCheckApplyId(String customerCheckApplyId) {
		this.customerCheckApplyId = customerCheckApplyId;
	}

	public String getAuditingRejectCode() {
		return auditingRejectCode;
	}

	public void setAuditingRejectCode(String auditingRejectCode) {
		this.auditingRejectCode = auditingRejectCode;
	}

	public int getIsCallSucess() {
		return isCallSucess;
	}

	public void setIsCallSucess(int isCallSucess) {
		this.isCallSucess = isCallSucess;
	}

	public String getYcNumber() {
		return ycNumber;
	}

	public void setYcNumber(String ycNumber) {
		this.ycNumber = ycNumber;
	}

	public String getZhiMaScore() {
		return zhiMaScore;
	}

	public void setZhiMaScore(String zhiMaScore) {
		this.zhiMaScore = zhiMaScore;
	}

	@Override
	public String toString() {
		return "Application{" +
				"applicationId='" + applicationId + '\'' +
				", appSerialNumber='" + appSerialNumber + '\'' +
				", platformId='" + platformId + '\'' +
				", applicationBuId='" + applicationBuId + '\'' +
				", appName='" + appName + '\'' +
				", customerId='" + customerId + '\'' +
				", name='" + name + '\'' +
				", certType='" + certType + '\'' +
				", certCode='" + certCode + '\'' +
				", phoneNum='" + phoneNum + '\'' +
				", creditMoney='" + creditMoney + '\'' +
				", productQuota='" + productQuota + '\'' +
				", days=" + days +
				", appraiseMoney='" + appraiseMoney + '\'' +
				", loanIp='" + loanIp + '\'' +
				", auditingTime='" + auditingTime + '\'' +
				", auditingState='" + auditingState + '\'' +
				", source='" + source + '\'' +
				", tdBlackBox='" + tdBlackBox + '\'' +
				", jxlUseToken='" + jxlUseToken + '\'' +
				", reportId='" + reportId + '\'' +
				", auditResult='" + auditResult + '\'' +
				", isStep='" + isStep + '\'' +
				", isPushApp='" + isPushApp + '\'' +
				", openId='" + openId + '\'' +
				", auditMan='" + auditMan + '\'' +
				", mobileWebsite='" + mobileWebsite + '\'' +
				", createTime='" + createTime + '\'' +
				", createMan='" + createMan + '\'' +
				", modifyTime='" + modifyTime + '\'' +
				", modifyMan='" + modifyMan + '\'' +
				", remark='" + remark + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", banCode='" + banCode + '\'' +
				", errorDescription='" + errorDescription + '\'' +
				", scoreSmallId='" + scoreSmallId + '\'' +
				", banCodeTplId='" + banCodeTplId + '\'' +
				", fraudTplId='" + fraudTplId + '\'' +
				", scoreTplId='" + scoreTplId + '\'' +
				", decisionTreeId='" + decisionTreeId + '\'' +
				", distributionTime='" + distributionTime + '\'' +
				", failureTimes='" + failureTimes + '\'' +
				", ids=" + Arrays.toString(ids) +
				", hlUserToken='" + hlUserToken + '\'' +
				", appLevel='" + appLevel + '\'' +
				", appChannel='" + appChannel + '\'' +
				", appVersion='" + appVersion + '\'' +
				", refundConut=" + refundConut +
				", overdueCount=" + overdueCount +
				", flag='" + flag + '\'' +
				", isHbaseSyn='" + isHbaseSyn + '\'' +
				", rgAuditingTime='" + rgAuditingTime + '\'' +
				", endRgAuditingTime='" + endRgAuditingTime + '\'' +
				", rgCreditMoney='" + rgCreditMoney + '\'' +
				", rgAuditingState='" + rgAuditingState + '\'' +
				", receiveName='" + receiveName + '\'' +
				", receiveCode='" + receiveCode + '\'' +
				", isCallPhone='" + isCallPhone + '\'' +
				", netcheckNoAbnormal='" + netcheckNoAbnormal + '\'' +
				", rgAuditingRemark='" + rgAuditingRemark + '\'' +
				", customerCheckApplyId='" + customerCheckApplyId + '\'' +
				", auditingRejectCode='" + auditingRejectCode + '\'' +
				", isCallSucess=" + isCallSucess +
				", ycNumber='" + ycNumber + '\'' +
				", zhiMaScore='" + zhiMaScore + '\'' +
				'}';
	}
}
