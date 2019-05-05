package com.shangyong.backend.entity.tdReport;


/**
 * 同盾信息检测行为评分表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportInfoCheck {

	/**报告的唯一标识**/
	private String infoCheckId;

	/**申请单编号**/
	private String applicationId;

	/**手机号账户状态是否正常**/
	private String isMobileStatusActive;

	/**身份证号码是否有效**/
	private String isIdentityCodeValid;

	/**运营商是否实名认证**/
	private String isIdentityCodeReliable;

	/**入网时长是否大于3个月**/
	private String isNetAgeOver3month;

	/**近6个月内是否和紧急联系人1通话**/
	private String isContact1Called6month;

	/**近6个月内是否和紧急联系人2通话**/
	private String isContact2Called6month;

	/**近6个月内是否和紧急联系人3通话**/
	private String isContact3Called6month;

	/**近6个月内是否和紧急联系人4通话**/
	private String isContact4Called6month;

	/**近6个月内是否和紧急联系人5通话**/
	private String isContact5Called6month;

	/**近6个月内是否和家庭电话通话**/
	private String isHomeTelCalled6month;

	/**近6个月内是否和工作电话通话**/
	private String isWorkTelCalled6month;

	/**近1个月常用通话地是否和号码归属地一致**/
	private String isNetAddrCallAddr1month;

	/**近3个月常用通话地是否和号码归属地一致**/
	private String isNetAddrCallAddr3month;

	/**近6个月常用通话地是否和号码归属地一致**/
	private String isNetAddrCallAddr6month;

	/**基本信息评分**/
	private String baseInfoScore;

	/**通话行为评分**/
	private String callInfoScore;

	/**风险联系人评分**/
	private String riskContactInfoScore;

	/**缴费消费评分**/
	private String billInfoScore;

	/**综合评分**/
	private String totalScore;

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


	public TdReportInfoCheck() {
		super();
	}
	public TdReportInfoCheck(String infoCheckId,String applicationId,String isMobileStatusActive,String isIdentityCodeValid,String isIdentityCodeReliable,String isNetAgeOver3month,String isContact1Called6month,String isContact2Called6month,String isContact3Called6month,String isContact4Called6month,String isContact5Called6month,String isHomeTelCalled6month,String isWorkTelCalled6month,String isNetAddrCallAddr1month,String isNetAddrCallAddr3month,String isNetAddrCallAddr6month,String baseInfoScore,String callInfoScore,String riskContactInfoScore,String billInfoScore,String totalScore,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.infoCheckId = infoCheckId;
		this.applicationId = applicationId;
		this.isMobileStatusActive = isMobileStatusActive;
		this.isIdentityCodeValid = isIdentityCodeValid;
		this.isIdentityCodeReliable = isIdentityCodeReliable;
		this.isNetAgeOver3month = isNetAgeOver3month;
		this.isContact1Called6month = isContact1Called6month;
		this.isContact2Called6month = isContact2Called6month;
		this.isContact3Called6month = isContact3Called6month;
		this.isContact4Called6month = isContact4Called6month;
		this.isContact5Called6month = isContact5Called6month;
		this.isHomeTelCalled6month = isHomeTelCalled6month;
		this.isWorkTelCalled6month = isWorkTelCalled6month;
		this.isNetAddrCallAddr1month = isNetAddrCallAddr1month;
		this.isNetAddrCallAddr3month = isNetAddrCallAddr3month;
		this.isNetAddrCallAddr6month = isNetAddrCallAddr6month;
		this.baseInfoScore = baseInfoScore;
		this.callInfoScore = callInfoScore;
		this.riskContactInfoScore = riskContactInfoScore;
		this.billInfoScore = billInfoScore;
		this.totalScore = totalScore;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setInfoCheckId(String infoCheckId){
		this.infoCheckId = infoCheckId;
	}

	public String getInfoCheckId(){
		return this.infoCheckId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setIsMobileStatusActive(String isMobileStatusActive){
		this.isMobileStatusActive = isMobileStatusActive;
	}

	public String getIsMobileStatusActive(){
		return this.isMobileStatusActive;
	}

	public void setIsIdentityCodeValid(String isIdentityCodeValid){
		this.isIdentityCodeValid = isIdentityCodeValid;
	}

	public String getIsIdentityCodeValid(){
		return this.isIdentityCodeValid;
	}

	public void setIsIdentityCodeReliable(String isIdentityCodeReliable){
		this.isIdentityCodeReliable = isIdentityCodeReliable;
	}

	public String getIsIdentityCodeReliable(){
		return this.isIdentityCodeReliable;
	}

	public void setIsNetAgeOver3month(String isNetAgeOver3month){
		this.isNetAgeOver3month = isNetAgeOver3month;
	}

	public String getIsNetAgeOver3month(){
		return this.isNetAgeOver3month;
	}

	public void setIsContact1Called6month(String isContact1Called6month){
		this.isContact1Called6month = isContact1Called6month;
	}

	public String getIsContact1Called6month(){
		return this.isContact1Called6month;
	}

	public void setIsContact2Called6month(String isContact2Called6month){
		this.isContact2Called6month = isContact2Called6month;
	}

	public String getIsContact2Called6month(){
		return this.isContact2Called6month;
	}

	public void setIsContact3Called6month(String isContact3Called6month){
		this.isContact3Called6month = isContact3Called6month;
	}

	public String getIsContact3Called6month(){
		return this.isContact3Called6month;
	}

	public void setIsContact4Called6month(String isContact4Called6month){
		this.isContact4Called6month = isContact4Called6month;
	}

	public String getIsContact4Called6month(){
		return this.isContact4Called6month;
	}

	public void setIsContact5Called6month(String isContact5Called6month){
		this.isContact5Called6month = isContact5Called6month;
	}

	public String getIsContact5Called6month(){
		return this.isContact5Called6month;
	}

	public void setIsHomeTelCalled6month(String isHomeTelCalled6month){
		this.isHomeTelCalled6month = isHomeTelCalled6month;
	}

	public String getIsHomeTelCalled6month(){
		return this.isHomeTelCalled6month;
	}

	public void setIsWorkTelCalled6month(String isWorkTelCalled6month){
		this.isWorkTelCalled6month = isWorkTelCalled6month;
	}

	public String getIsWorkTelCalled6month(){
		return this.isWorkTelCalled6month;
	}

	public void setIsNetAddrCallAddr1month(String isNetAddrCallAddr1month){
		this.isNetAddrCallAddr1month = isNetAddrCallAddr1month;
	}

	public String getIsNetAddrCallAddr1month(){
		return this.isNetAddrCallAddr1month;
	}

	public void setIsNetAddrCallAddr3month(String isNetAddrCallAddr3month){
		this.isNetAddrCallAddr3month = isNetAddrCallAddr3month;
	}

	public String getIsNetAddrCallAddr3month(){
		return this.isNetAddrCallAddr3month;
	}

	public void setIsNetAddrCallAddr6month(String isNetAddrCallAddr6month){
		this.isNetAddrCallAddr6month = isNetAddrCallAddr6month;
	}

	public String getIsNetAddrCallAddr6month(){
		return this.isNetAddrCallAddr6month;
	}

	public void setBaseInfoScore(String baseInfoScore){
		this.baseInfoScore = baseInfoScore;
	}

	public String getBaseInfoScore(){
		return this.baseInfoScore;
	}

	public void setCallInfoScore(String callInfoScore){
		this.callInfoScore = callInfoScore;
	}

	public String getCallInfoScore(){
		return this.callInfoScore;
	}

	public void setRiskContactInfoScore(String riskContactInfoScore){
		this.riskContactInfoScore = riskContactInfoScore;
	}

	public String getRiskContactInfoScore(){
		return this.riskContactInfoScore;
	}

	public void setBillInfoScore(String billInfoScore){
		this.billInfoScore = billInfoScore;
	}

	public String getBillInfoScore(){
		return this.billInfoScore;
	}

	public void setTotalScore(String totalScore){
		this.totalScore = totalScore;
	}

	public String getTotalScore(){
		return this.totalScore;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
