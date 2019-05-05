package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.PageHelperBo;

/**
 * 导入数据记录表bean
 * @author kenzhao
 * @date Sat Sep 23 18:07:05 CST 2017
 **/
public class CuCustomerCheckManage extends PageHelperBo{

	/**批次编号**/
	private Integer customerCheckManageId;

	/**批次号**/
	private String customerCheckCodeId;

	/**上传地址**/
	private String uploadAddress;

	/**上传时间**/
	private String uploadTime;

	/**上传用户**/
	private String uploadMan;

	/**处理成功记录描述**/
	private String uploadSuccess;

	/**处理失败记录描述**/
	private String uploadFailure;

	/**征信机构 （ 05001- 聚信立蜜罐 07001-91征信 08001-白骑士欺诈 09001-宜信  11001-葫芦索伦  12001-小视科技 银行  12002-小视科技 网贷 40004- 芝麻信用行业关注名单）**/
	private String taskType;

	/**备注**/
	private String remark;

	/**开始时间**/
	private String startTime;
	
	/**结束时间**/
	private String endTime;
	
	public CuCustomerCheckManage() {
		super();
	}
	public CuCustomerCheckManage(Integer customerCheckManageId,String customerCheckCodeId,String uploadAddress,String uploadTime,String uploadMan,String uploadSuccess,String uploadFailure,String taskType,String remark) {
		super();
		this.customerCheckManageId = customerCheckManageId;
		this.customerCheckCodeId = customerCheckCodeId;
		this.uploadAddress = uploadAddress;
		this.uploadTime = uploadTime;
		this.uploadMan = uploadMan;
		this.uploadSuccess = uploadSuccess;
		this.uploadFailure = uploadFailure;
		this.taskType = taskType;
		this.remark = remark;
	}
	public void setCustomerCheckManageId(Integer customerCheckManageId){
		this.customerCheckManageId = customerCheckManageId;
	}

	public Integer getCustomerCheckManageId(){
		return this.customerCheckManageId;
	}

	public void setCustomerCheckCodeId(String customerCheckCodeId){
		this.customerCheckCodeId = customerCheckCodeId;
	}

	public String getCustomerCheckCodeId(){
		return this.customerCheckCodeId;
	}

	public void setUploadAddress(String uploadAddress){
		this.uploadAddress = uploadAddress;
	}

	public String getUploadAddress(){
		return this.uploadAddress;
	}

	public void setUploadTime(String uploadTime){
		this.uploadTime = uploadTime;
	}

	public String getUploadTime(){
		return this.uploadTime;
	}

	public void setUploadMan(String uploadMan){
		this.uploadMan = uploadMan;
	}

	public String getUploadMan(){
		return this.uploadMan;
	}

	public void setUploadSuccess(String uploadSuccess){
		this.uploadSuccess = uploadSuccess;
	}

	public String getUploadSuccess(){
		return this.uploadSuccess;
	}

	public void setUploadFailure(String uploadFailure){
		this.uploadFailure = uploadFailure;
	}

	public String getUploadFailure(){
		return this.uploadFailure;
	}

	public void setTaskType(String taskType){
		this.taskType = taskType;
	}

	public String getTaskType(){
		return this.taskType;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "CuCustomerCheckManage [customerCheckManageId=" + customerCheckManageId + ", customerCheckCodeId="
				+ customerCheckCodeId + ", uploadAddress=" + uploadAddress + ", uploadTime=" + uploadTime
				+ ", uploadMan=" + uploadMan + ", uploadSuccess=" + uploadSuccess + ", uploadFailure=" + uploadFailure
				+ ", taskType=" + taskType + ", remark=" + remark + "]";
	}
	
	
	
}
