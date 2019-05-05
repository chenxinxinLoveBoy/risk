package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:50 CST 2017
 **/
public class BqsPaymentsInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsPaymentsInfoId;

	/****/
	private String bqsPersonalInfoId;

	/**支付渠道**/
	private String paymentChannel;

	/**支付金额**/
	private String paymentFee;

	/**支付时间**/
	private String paymentDate;

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


	public BqsPaymentsInfo() {
		super();
	}
	public BqsPaymentsInfo(String bqsPaymentsInfoId,String bqsPersonalInfoId,String paymentChannel,String paymentFee,String paymentDate,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsPaymentsInfoId = bqsPaymentsInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.paymentChannel = paymentChannel;
		this.paymentFee = paymentFee;
		this.paymentDate = paymentDate;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsPaymentsInfoId(String bqsPaymentsInfoId){
		this.bqsPaymentsInfoId = bqsPaymentsInfoId;
	}

	public String getBqsPaymentsInfoId(){
		return this.bqsPaymentsInfoId;
	}

	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setPaymentChannel(String paymentChannel){
		this.paymentChannel = paymentChannel;
	}

	public String getPaymentChannel(){
		return this.paymentChannel;
	}

	public void setPaymentFee(String paymentFee){
		this.paymentFee = paymentFee;
	}

	public String getPaymentFee(){
		return this.paymentFee;
	}

	public void setPaymentDate(String paymentDate){
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate(){
		return this.paymentDate;
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
