package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:50 CST 2017
 **/
public class BqsSmsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsSmsInfoId;

	/**白骑士个人信息表id**/
	private String bqsPersonalInfoId;

	/**短信发送时间**/
	private String beginTime;

	/**短信类型（发送 or 接收）**/
	private String smsType;

	/****/
	private String amount;

	/**对方号码**/
	private String otherNum;

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


	public BqsSmsInfo() {
		super();
	}
	public BqsSmsInfo(String bqsSmsInfoId,String bqsPersonalInfoId,String beginTime,String smsType,String amount,String otherNum,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsSmsInfoId = bqsSmsInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.beginTime = beginTime;
		this.smsType = smsType;
		this.amount = amount;
		this.otherNum = otherNum;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsSmsInfoId(String bqsSmsInfoId){
		this.bqsSmsInfoId = bqsSmsInfoId;
	}

	public String getBqsSmsInfoId(){
		return this.bqsSmsInfoId;
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

	public void setSmsType(String smsType){
		this.smsType = smsType;
	}

	public String getSmsType(){
		return this.smsType;
	}

	public void setAmount(String amount){
		this.amount = amount;
	}

	public String getAmount(){
		return this.amount;
	}

	public void setOtherNum(String otherNum){
		this.otherNum = otherNum;
	}

	public String getOtherNum(){
		return this.otherNum;
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
