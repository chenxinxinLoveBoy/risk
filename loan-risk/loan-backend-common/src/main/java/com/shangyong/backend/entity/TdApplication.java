package com.shangyong.backend.entity;


/**
 * 同盾客户表bean
 * @author kenzhao
 * @date Thu Mar 08 11:20:34 CST 2018
 **/
public class TdApplication {

	/**申请单编号**/
	private String applicationId;

	/**名字**/
	private String name;

	/**身份证**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**ip地址**/
	private String loanIp;

	/**创建时间**/
	private String createTime;

	/**任务编号**/
	private String taskId;


	public TdApplication() {
		super();
	}
	public TdApplication(String applicationId,String name,String certCode,String phoneNum,String loanIp,String createTime,String taskId) {
		super();
		this.applicationId = applicationId;
		this.name = name;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.loanIp = loanIp;
		this.createTime = createTime;
		this.taskId = taskId;
	}
	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setLoanIp(String loanIp){
		this.loanIp = loanIp;
	}

	public String getLoanIp(){
		return this.loanIp;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setTaskId(String taskId){
		this.taskId = taskId;
	}

	public String getTaskId(){
		return this.taskId;
	}

}
