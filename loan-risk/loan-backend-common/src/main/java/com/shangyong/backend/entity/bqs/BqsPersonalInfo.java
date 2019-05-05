package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:50 CST 2017
 **/
public class BqsPersonalInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsPersonalInfoId;

	/**申请单编号**/
	private String buApplicationId;

	/**姓名**/
	private String boundName;

	/**身份证号**/
	private String boundCertNo;

	/**手机号**/
	private String mobile;

	/**是否实名认证 true or false**/
	private Boolean isRealCheck;

	/**手机运营商类型**/
	private String monType;

	/**手机号所属地**/
	private String belongTo;

	/**是否开通**/
	private String status;

	/**平均消费**/
	private String blanceMoney;

	/**使用费用**/
	private String availableFee;

	/**实时费用**/
	private String raltimeFee;

	/**用户级别**/
	private String custLevel;

	/**是否命中,1命中0未命中**/
	private String state;

	/**备注**/
	private String remark;

	/**创建时间**/
	private String createTime;

	/****/
	private String createMan;

	/**最后修改时间**/
	private String modifyTime;

	/****/
	private String modifyMan;


	public BqsPersonalInfo() {
		super();
	}
	public BqsPersonalInfo(String bqsPersonalInfoId,String buApplicationId,String boundName,String boundCertNo,String mobile,Boolean isRealCheck,String monType,String belongTo,String status,String blanceMoney,String availableFee,String raltimeFee,String custLevel,String state,String remark,String createTime,String createMan,String modifyTime,String modifyMan) {
		super();
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.buApplicationId = buApplicationId;
		this.boundName = boundName;
		this.boundCertNo = boundCertNo;
		this.mobile = mobile;
		this.isRealCheck = isRealCheck;
		this.monType = monType;
		this.belongTo = belongTo;
		this.status = status;
		this.blanceMoney = blanceMoney;
		this.availableFee = availableFee;
		this.raltimeFee = raltimeFee;
		this.custLevel = custLevel;
		this.state = state;
		this.remark = remark;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}
	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setBoundName(String boundName){
		this.boundName = boundName;
	}

	public String getBoundName(){
		return this.boundName;
	}

	public void setBoundCertNo(String boundCertNo){
		this.boundCertNo = boundCertNo;
	}

	public String getBoundCertNo(){
		return this.boundCertNo;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	

	public Boolean getIsRealCheck() {
		return isRealCheck;
	}
	public void setIsRealCheck(Boolean isRealCheck) {
		this.isRealCheck = isRealCheck;
	}
	public void setMonType(String monType){
		this.monType = monType;
	}

	public String getMonType(){
		return this.monType;
	}

	public void setBelongTo(String belongTo){
		this.belongTo = belongTo;
	}

	public String getBelongTo(){
		return this.belongTo;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setBlanceMoney(String blanceMoney){
		this.blanceMoney = blanceMoney;
	}

	public String getBlanceMoney(){
		return this.blanceMoney;
	}

	public void setAvailableFee(String availableFee){
		this.availableFee = availableFee;
	}

	public String getAvailableFee(){
		return this.availableFee;
	}

	public void setRaltimeFee(String raltimeFee){
		this.raltimeFee = raltimeFee;
	}

	public String getRaltimeFee(){
		return this.raltimeFee;
	}

	public void setCustLevel(String custLevel){
		this.custLevel = custLevel;
	}

	public String getCustLevel(){
		return this.custLevel;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
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

}
