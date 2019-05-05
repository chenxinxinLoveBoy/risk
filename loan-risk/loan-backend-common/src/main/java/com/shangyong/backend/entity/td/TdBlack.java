package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdBlack {

	/****/
	private String tdBlackId;

	/****/
	private String tdRiskItemsId;

	/**匹配类型显示名**/
	private String hitTypeDisplayName;

	/**风险类型显示名**/
	private String fraudTypeDisplayName;

	/**规则描述**/
	private String description;

	/**命中的属性值**/
	private String value;

	/**风险类型**/
	private String fraudType;

	/**被执行人姓名**/
	private String executedName;

	/**年龄**/
	private String age;

	/**性别**/
	private String gender;

	/**省份**/
	private String province;

	/**立案时间**/
	private String caseDate;

	/**执行法院**/
	private String executeCourt;

	/**生效法律文书确定的义务**/
	private String termDuty;

	/**执行标的**/
	private String executeSubject;

	/**执行状态**/
	private String executeStatus;

	/**做出依据执行法院**/
	private String evidenceCourt;

	/**被执行人履行情况**/
	private String carryOut;

	/**信贷逾期被执行人行为具体情形**/
	private String specificCircumstances;

	/**执行依据文号**/
	private String executeCode;

	/**案号**/
	private String caseCode;

	/**证据时间戳形式**/
	private String evidenceTime;


	public TdBlack() {
		super();
	}
	public TdBlack(String tdBlackId,String tdRiskItemsId,String hitTypeDisplayName,String fraudTypeDisplayName,String description,String value,String fraudType,String executedName,String age,String gender,String province,String caseDate,String executeCourt,String termDuty,String executeSubject,String executeStatus,String evidenceCourt,String carryOut,String specificCircumstances,String executeCode,String caseCode,String evidenceTime) {
		super();
		this.tdBlackId = tdBlackId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.hitTypeDisplayName = hitTypeDisplayName;
		this.fraudTypeDisplayName = fraudTypeDisplayName;
		this.description = description;
		this.value = value;
		this.fraudType = fraudType;
		this.executedName = executedName;
		this.age = age;
		this.gender = gender;
		this.province = province;
		this.caseDate = caseDate;
		this.executeCourt = executeCourt;
		this.termDuty = termDuty;
		this.executeSubject = executeSubject;
		this.executeStatus = executeStatus;
		this.evidenceCourt = evidenceCourt;
		this.carryOut = carryOut;
		this.specificCircumstances = specificCircumstances;
		this.executeCode = executeCode;
		this.caseCode = caseCode;
		this.evidenceTime = evidenceTime;
	}
	public void setTdBlackId(String tdBlackId){
		this.tdBlackId = tdBlackId;
	}

	public String getTdBlackId(){
		return this.tdBlackId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setHitTypeDisplayName(String hitTypeDisplayName){
		this.hitTypeDisplayName = hitTypeDisplayName;
	}

	public String getHitTypeDisplayName(){
		return this.hitTypeDisplayName;
	}

	public void setFraudTypeDisplayName(String fraudTypeDisplayName){
		this.fraudTypeDisplayName = fraudTypeDisplayName;
	}

	public String getFraudTypeDisplayName(){
		return this.fraudTypeDisplayName;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	public void setFraudType(String fraudType){
		this.fraudType = fraudType;
	}

	public String getFraudType(){
		return this.fraudType;
	}

	public void setExecutedName(String executedName){
		this.executedName = executedName;
	}

	public String getExecutedName(){
		return this.executedName;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setProvince(String province){
		this.province = province;
	}

	public String getProvince(){
		return this.province;
	}

	public void setCaseDate(String caseDate){
		this.caseDate = caseDate;
	}

	public String getCaseDate(){
		return this.caseDate;
	}

	public void setExecuteCourt(String executeCourt){
		this.executeCourt = executeCourt;
	}

	public String getExecuteCourt(){
		return this.executeCourt;
	}

	public void setTermDuty(String termDuty){
		this.termDuty = termDuty;
	}

	public String getTermDuty(){
		return this.termDuty;
	}

	public void setExecuteSubject(String executeSubject){
		this.executeSubject = executeSubject;
	}

	public String getExecuteSubject(){
		return this.executeSubject;
	}

	public void setExecuteStatus(String executeStatus){
		this.executeStatus = executeStatus;
	}

	public String getExecuteStatus(){
		return this.executeStatus;
	}

	public void setEvidenceCourt(String evidenceCourt){
		this.evidenceCourt = evidenceCourt;
	}

	public String getEvidenceCourt(){
		return this.evidenceCourt;
	}

	public void setCarryOut(String carryOut){
		this.carryOut = carryOut;
	}

	public String getCarryOut(){
		return this.carryOut;
	}

	public void setSpecificCircumstances(String specificCircumstances){
		this.specificCircumstances = specificCircumstances;
	}

	public String getSpecificCircumstances(){
		return this.specificCircumstances;
	}

	public void setExecuteCode(String executeCode){
		this.executeCode = executeCode;
	}

	public String getExecuteCode(){
		return this.executeCode;
	}

	public void setCaseCode(String caseCode){
		this.caseCode = caseCode;
	}

	public String getCaseCode(){
		return this.caseCode;
	}

	public void setEvidenceTime(String evidenceTime){
		this.evidenceTime = evidenceTime;
	}

	public String getEvidenceTime(){
		return this.evidenceTime;
	}

}
