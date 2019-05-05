package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:49 CST 2017
 **/
public class BqsCallsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsCallsInfoId;

	/**白骑士个人信息表id**/
	private String bqsPersonalInfoId;

	/**通话开始时间**/
	private String beginTime;

	/**通话地点**/
	private String homeArea;

	/**通话号码**/
	private String otherNum;

	/**通话类型(主叫，被叫）**/
	private String callType;

	/**通话时间**/
	private String callDuration;

	/**通话类型（国内通话）**/
	private String landType;

	/**通话费用**/
	private String totalFee;

	/**备注**/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;


	public BqsCallsInfo() {
		super();
	}
	public BqsCallsInfo(String bqsCallsInfoId,String bqsPersonalInfoId,String beginTime,String homeArea,String otherNum,String callType,String callDuration,String landType,String totalFee,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsCallsInfoId = bqsCallsInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.beginTime = beginTime;
		this.homeArea = homeArea;
		this.otherNum = otherNum;
		this.callType = callType;
		this.callDuration = callDuration;
		this.landType = landType;
		this.totalFee = totalFee;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsCallsInfoId(String bqsCallsInfoId){
		this.bqsCallsInfoId = bqsCallsInfoId;
	}

	public String getBqsCallsInfoId(){
		return this.bqsCallsInfoId;
	}

	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setHomeArea(String homeArea){
		this.homeArea = homeArea;
	}

	public String getHomeArea(){
		return this.homeArea;
	}

	public void setOtherNum(String otherNum){
		this.otherNum = otherNum;
	}

	public String getOtherNum(){
		return this.otherNum;
	}

	public void setCallType(String callType){
		this.callType = callType;
	}

	public String getCallType(){
		return this.callType;
	}

	

	public String getCallDuration() {
		return callDuration;
	}
	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}
	public void setLandType(String landType){
		this.landType = landType;
	}

	public String getLandType(){
		return this.landType;
	}

	public void setTotalFee(String totalFee){
		this.totalFee = totalFee;
	}

	public String getTotalFee(){
		return this.totalFee;
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
