package com.shangyong.backend.entity.bqsrep;


/**
 * 白骑士运营商报告申请人信息bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
public class BqsRepPetitioner {

	/****/
	private String bqsPetitionerId;

	/**申请单ID**/
	private String applicationId;

	/**报告编号**/
	private String reportSn;

	/**报告生成时间**/
	private String reportTime;

	/**姓名**/
	private String name;

	/**身份证**/
	private String certNo;

	/**手机号码**/
	private String mobile;

	/**手机号归属地**/
	private String mobileBelongTo;

	/**手机号运营商类型**/
	private String mobileMnoType;

	/**性别**/
	private String gender;

	/**星座**/
	private String constellation;

	/**年龄**/
	private String age;

	/**出生地**/
	private String birthAddress;

	/**备注**/
	private String bar;

	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;


	public BqsRepPetitioner() {
		super();
	}
	public BqsRepPetitioner(String bqsPetitionerId,String applicationId,String reportSn,String reportTime,String name,String certNo,String mobile,String mobileBelongTo,String mobileMnoType,String gender,String constellation,String age,String birthAddress,String bar,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsPetitionerId = bqsPetitionerId;
		this.applicationId = applicationId;
		this.reportSn = reportSn;
		this.reportTime = reportTime;
		this.name = name;
		this.certNo = certNo;
		this.mobile = mobile;
		this.mobileBelongTo = mobileBelongTo;
		this.mobileMnoType = mobileMnoType;
		this.gender = gender;
		this.constellation = constellation;
		this.age = age;
		this.birthAddress = birthAddress;
		this.bar = bar;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setReportSn(String reportSn){
		this.reportSn = reportSn;
	}

	public String getReportSn(){
		return this.reportSn;
	}

	public void setReportTime(String reportTime){
		this.reportTime = reportTime;
	}

	public String getReportTime(){
		return this.reportTime;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertNo(String certNo){
		this.certNo = certNo;
	}

	public String getCertNo(){
		return this.certNo;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setMobileBelongTo(String mobileBelongTo){
		this.mobileBelongTo = mobileBelongTo;
	}

	public String getMobileBelongTo(){
		return this.mobileBelongTo;
	}

	public void setMobileMnoType(String mobileMnoType){
		this.mobileMnoType = mobileMnoType;
	}

	public String getMobileMnoType(){
		return this.mobileMnoType;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setConstellation(String constellation){
		this.constellation = constellation;
	}

	public String getConstellation(){
		return this.constellation;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setBirthAddress(String birthAddress){
		this.birthAddress = birthAddress;
	}

	public String getBirthAddress(){
		return this.birthAddress;
	}

	public void setBar(String bar){
		this.bar = bar;
	}

	public String getBar(){
		return this.bar;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

}
