package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:49 CST 2017
 **/
public class BqsBillsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsBillsInfoId;

	/**白骑士个人信息表id**/
	private String bqsPersonalInfoId;

	/**所有费用**/
	private String allFee;

	/**支付费用**/
	private String dueFee;

	/**账单时间**/
	private String month;

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


	public BqsBillsInfo() {
		super();
	}
	public BqsBillsInfo(String bqsBillsInfoId,String bqsPersonalInfoId,String allFee,String dueFee,String month,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsBillsInfoId = bqsBillsInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.allFee = allFee;
		this.dueFee = dueFee;
		this.month = month;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsBillsInfoId(String bqsBillsInfoId){
		this.bqsBillsInfoId = bqsBillsInfoId;
	}

	public String getBqsBillsInfoId(){
		return this.bqsBillsInfoId;
	}

	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setAllFee(String allFee){
		this.allFee = allFee;
	}

	public String getAllFee(){
		return this.allFee;
	}

	public void setDueFee(String dueFee){
		this.dueFee = dueFee;
	}

	public String getDueFee(){
		return this.dueFee;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return this.month;
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
