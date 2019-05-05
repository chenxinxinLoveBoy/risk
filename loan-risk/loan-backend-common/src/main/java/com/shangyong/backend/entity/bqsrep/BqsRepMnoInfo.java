package com.shangyong.backend.entity.bqsrep;


/**
 * 运营商基本信息bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
public class BqsRepMnoInfo {

	/****/
	private String bqsMnoInfoId;

	/****/
	private String bqsPetitionerId;

	/**号码**/
	private String mobile;

	/**运营商类型(移动、联通、电信)	**/
	private String monType;

	/**归属地**/
	private String belongTo;

	/**绑定身份证**/
	private String boundCertNo;

	/**绑定姓名	**/
	private String boundName;

	/**绑定email**/
	private String boundEmail;


	public BqsRepMnoInfo() {
		super();
	}
	public BqsRepMnoInfo(String bqsMnoInfoId,String bqsPetitionerId,String mobile,String monType,String belongTo,String boundCertNo,String boundName,String boundEmail) {
		super();
		this.bqsMnoInfoId = bqsMnoInfoId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.mobile = mobile;
		this.monType = monType;
		this.belongTo = belongTo;
		this.boundCertNo = boundCertNo;
		this.boundName = boundName;
		this.boundEmail = boundEmail;
	}
	public void setBqsMnoInfoId(String bqsMnoInfoId){
		this.bqsMnoInfoId = bqsMnoInfoId;
	}

	public String getBqsMnoInfoId(){
		return this.bqsMnoInfoId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
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

	public void setBoundCertNo(String boundCertNo){
		this.boundCertNo = boundCertNo;
	}

	public String getBoundCertNo(){
		return this.boundCertNo;
	}

	public void setBoundName(String boundName){
		this.boundName = boundName;
	}

	public String getBoundName(){
		return this.boundName;
	}

	public void setBoundEmail(String boundEmail){
		this.boundEmail = boundEmail;
	}

	public String getBoundEmail(){
		return this.boundEmail;
	}

}
