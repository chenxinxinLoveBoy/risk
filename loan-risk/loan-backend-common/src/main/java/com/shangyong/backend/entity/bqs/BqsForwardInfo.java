package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:49 CST 2017
 **/
public class BqsForwardInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsForwardInfoId;

	/**网络类型**/
	private String bqsPersonalInfoId;

	/****/
	private String netType;

	/**流量类型**/
	private String forwardType;

	/**通信起始时间**/
	private String beginTime;

	/**持续时间**/
	private String longHour;

	/**总流量**/
	private String totalBytes;

	/****/
	private String homeArea;

	/**总费用**/
	private String totalFee;

	/**使用套餐**/
	private String meal;

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


	public BqsForwardInfo() {
		super();
	}
	public BqsForwardInfo(String bqsForwardInfoId,String bqsPersonalInfoId,String netType,String forwardType,String beginTime,String longHour,String totalBytes,String homeArea,String totalFee,String meal,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsForwardInfoId = bqsForwardInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.netType = netType;
		this.forwardType = forwardType;
		this.beginTime = beginTime;
		this.longHour = longHour;
		this.totalBytes = totalBytes;
		this.homeArea = homeArea;
		this.totalFee = totalFee;
		this.meal = meal;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsForwardInfoId(String bqsForwardInfoId){
		this.bqsForwardInfoId = bqsForwardInfoId;
	}

	public String getBqsForwardInfoId(){
		return this.bqsForwardInfoId;
	}

	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setNetType(String netType){
		this.netType = netType;
	}

	public String getNetType(){
		return this.netType;
	}

	public void setForwardType(String forwardType){
		this.forwardType = forwardType;
	}

	public String getForwardType(){
		return this.forwardType;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setLongHour(String longHour){
		this.longHour = longHour;
	}

	public String getLongHour(){
		return this.longHour;
	}

	public void setTotalBytes(String totalBytes){
		this.totalBytes = totalBytes;
	}

	public String getTotalBytes(){
		return this.totalBytes;
	}

	public void setHomeArea(String homeArea){
		this.homeArea = homeArea;
	}

	public String getHomeArea(){
		return this.homeArea;
	}

	public void setTotalFee(String totalFee){
		this.totalFee = totalFee;
	}

	public String getTotalFee(){
		return this.totalFee;
	}

	public void setMeal(String meal){
		this.meal = meal;
	}

	public String getMeal(){
		return this.meal;
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
