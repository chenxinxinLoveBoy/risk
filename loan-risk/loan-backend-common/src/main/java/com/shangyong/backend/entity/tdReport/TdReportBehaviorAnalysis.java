package com.shangyong.backend.entity.tdReport;


/**
 * 同盾行为分析表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportBehaviorAnalysis {

	/**报告的唯一标识**/
	private String behaviorAnalysisId;

	/**申请单编号**/
	private String applicationId;

	/**近6个月110通话情况**/
	private String call110Analysis6month;

	/**近6个月120通话情况**/
	private String call120Analysis6month;

	/**近6个月澳门电话通话情况**/
	private String callMacauAnalysis6month;

	/**近6个月律师号码通话情况**/
	private String callLawyerAnalysis6month;

	/**近6个月法院号码通话情况**/
	private String callCourtAnalysis6month;

	/**近6个月小贷号码联系情况**/
	private String loanContactAnalysis6month;

	/**近6个月催收号码联系情况**/
	private String collectionContactAnalysis6month;

	/**号码入网时间**/
	private String mobileNetAgeAnalysis;

	/**近6个月互通号码数量**/
	private String mutualNumberAnalysis6month;

	/**近6个月深夜活动情况**/
	private String lateNightAnalysis6month;

	/**近6个月手机静默情况**/
	private String mobileSilenceAnalysis6month;

	/**近6个月紧急联系人1通话情况**/
	private String emergencyContact1Analysis6month;

	/**近6个月紧急联系人2通话情况**/
	private String emergencyContact2Analysis6month;

	/**近6个月紧急联系人3通话情况**/
	private String emergencyContact3Analysis6month;

	/**近6个月紧急联系人4通话情况**/
	private String emergencyContact4Analysis6month;

	/**近6个月紧急联系人5通话情况**/
	private String emergencyContact5Analysis6month;

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


	public TdReportBehaviorAnalysis() {
		super();
	}
	public TdReportBehaviorAnalysis(String behaviorAnalysisId,String applicationId,String call110Analysis6month,String call120Analysis6month,String callMacauAnalysis6month,String callLawyerAnalysis6month,String callCourtAnalysis6month,String loanContactAnalysis6month,String collectionContactAnalysis6month,String mobileNetAgeAnalysis,String mutualNumberAnalysis6month,String lateNightAnalysis6month,String mobileSilenceAnalysis6month,String emergencyContact1Analysis6month,String emergencyContact2Analysis6month,String emergencyContact3Analysis6month,String emergencyContact4Analysis6month,String emergencyContact5Analysis6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.behaviorAnalysisId = behaviorAnalysisId;
		this.applicationId = applicationId;
		this.call110Analysis6month = call110Analysis6month;
		this.call120Analysis6month = call120Analysis6month;
		this.callMacauAnalysis6month = callMacauAnalysis6month;
		this.callLawyerAnalysis6month = callLawyerAnalysis6month;
		this.callCourtAnalysis6month = callCourtAnalysis6month;
		this.loanContactAnalysis6month = loanContactAnalysis6month;
		this.collectionContactAnalysis6month = collectionContactAnalysis6month;
		this.mobileNetAgeAnalysis = mobileNetAgeAnalysis;
		this.mutualNumberAnalysis6month = mutualNumberAnalysis6month;
		this.lateNightAnalysis6month = lateNightAnalysis6month;
		this.mobileSilenceAnalysis6month = mobileSilenceAnalysis6month;
		this.emergencyContact1Analysis6month = emergencyContact1Analysis6month;
		this.emergencyContact2Analysis6month = emergencyContact2Analysis6month;
		this.emergencyContact3Analysis6month = emergencyContact3Analysis6month;
		this.emergencyContact4Analysis6month = emergencyContact4Analysis6month;
		this.emergencyContact5Analysis6month = emergencyContact5Analysis6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setBehaviorAnalysisId(String behaviorAnalysisId){
		this.behaviorAnalysisId = behaviorAnalysisId;
	}

	public String getBehaviorAnalysisId(){
		return this.behaviorAnalysisId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setCall110Analysis6month(String call110Analysis6month){
		this.call110Analysis6month = call110Analysis6month;
	}

	public String getCall110Analysis6month(){
		return this.call110Analysis6month;
	}

	public void setCall120Analysis6month(String call120Analysis6month){
		this.call120Analysis6month = call120Analysis6month;
	}

	public String getCall120Analysis6month(){
		return this.call120Analysis6month;
	}

	public void setCallMacauAnalysis6month(String callMacauAnalysis6month){
		this.callMacauAnalysis6month = callMacauAnalysis6month;
	}

	public String getCallMacauAnalysis6month(){
		return this.callMacauAnalysis6month;
	}

	public void setCallLawyerAnalysis6month(String callLawyerAnalysis6month){
		this.callLawyerAnalysis6month = callLawyerAnalysis6month;
	}

	public String getCallLawyerAnalysis6month(){
		return this.callLawyerAnalysis6month;
	}

	public void setCallCourtAnalysis6month(String callCourtAnalysis6month){
		this.callCourtAnalysis6month = callCourtAnalysis6month;
	}

	public String getCallCourtAnalysis6month(){
		return this.callCourtAnalysis6month;
	}

	public void setLoanContactAnalysis6month(String loanContactAnalysis6month){
		this.loanContactAnalysis6month = loanContactAnalysis6month;
	}

	public String getLoanContactAnalysis6month(){
		return this.loanContactAnalysis6month;
	}

	public void setCollectionContactAnalysis6month(String collectionContactAnalysis6month){
		this.collectionContactAnalysis6month = collectionContactAnalysis6month;
	}

	public String getCollectionContactAnalysis6month(){
		return this.collectionContactAnalysis6month;
	}

	public void setMobileNetAgeAnalysis(String mobileNetAgeAnalysis){
		this.mobileNetAgeAnalysis = mobileNetAgeAnalysis;
	}

	public String getMobileNetAgeAnalysis(){
		return this.mobileNetAgeAnalysis;
	}

	public void setMutualNumberAnalysis6month(String mutualNumberAnalysis6month){
		this.mutualNumberAnalysis6month = mutualNumberAnalysis6month;
	}

	public String getMutualNumberAnalysis6month(){
		return this.mutualNumberAnalysis6month;
	}

	public void setLateNightAnalysis6month(String lateNightAnalysis6month){
		this.lateNightAnalysis6month = lateNightAnalysis6month;
	}

	public String getLateNightAnalysis6month(){
		return this.lateNightAnalysis6month;
	}

	public void setMobileSilenceAnalysis6month(String mobileSilenceAnalysis6month){
		this.mobileSilenceAnalysis6month = mobileSilenceAnalysis6month;
	}

	public String getMobileSilenceAnalysis6month(){
		return this.mobileSilenceAnalysis6month;
	}

	public void setEmergencyContact1Analysis6month(String emergencyContact1Analysis6month){
		this.emergencyContact1Analysis6month = emergencyContact1Analysis6month;
	}

	public String getEmergencyContact1Analysis6month(){
		return this.emergencyContact1Analysis6month;
	}

	public void setEmergencyContact2Analysis6month(String emergencyContact2Analysis6month){
		this.emergencyContact2Analysis6month = emergencyContact2Analysis6month;
	}

	public String getEmergencyContact2Analysis6month(){
		return this.emergencyContact2Analysis6month;
	}

	public void setEmergencyContact3Analysis6month(String emergencyContact3Analysis6month){
		this.emergencyContact3Analysis6month = emergencyContact3Analysis6month;
	}

	public String getEmergencyContact3Analysis6month(){
		return this.emergencyContact3Analysis6month;
	}

	public void setEmergencyContact4Analysis6month(String emergencyContact4Analysis6month){
		this.emergencyContact4Analysis6month = emergencyContact4Analysis6month;
	}

	public String getEmergencyContact4Analysis6month(){
		return this.emergencyContact4Analysis6month;
	}

	public void setEmergencyContact5Analysis6month(String emergencyContact5Analysis6month){
		this.emergencyContact5Analysis6month = emergencyContact5Analysis6month;
	}

	public String getEmergencyContact5Analysis6month(){
		return this.emergencyContact5Analysis6month;
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
