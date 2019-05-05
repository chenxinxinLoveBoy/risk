package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 黑名单导入数据记录表bean
 * @author kenzhao
 * @date Mon Oct 09 17:04:54 CST 2017
 **/
public class BuBlacklistManage extends BaseBo{

	/**批次编号**/
	private Integer blacklistManageId;

	/**批次号**/
	private String blacklistCodeId;

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

	/**备注**/
	private String remark;

	/**开始时间**/
	private String startTime;
	
	/**结束时间**/
	private String endTime;
	
	public BuBlacklistManage() {
		super();
	}
	public BuBlacklistManage(Integer blacklistManageId,String blacklistCodeId,String uploadAddress,String uploadTime,String uploadMan,String uploadSuccess,String uploadFailure,String remark) {
		super();
		this.blacklistManageId = blacklistManageId;
		this.blacklistCodeId = blacklistCodeId;
		this.uploadAddress = uploadAddress;
		this.uploadTime = uploadTime;
		this.uploadMan = uploadMan;
		this.uploadSuccess = uploadSuccess;
		this.uploadFailure = uploadFailure;
		this.remark = remark;
	}
	public void setBlacklistManageId(Integer blacklistManageId){
		this.blacklistManageId = blacklistManageId;
	}

	public Integer getBlacklistManageId(){
		return this.blacklistManageId;
	}

	public void setBlacklistCodeId(String blacklistCodeId){
		this.blacklistCodeId = blacklistCodeId;
	}

	public String getBlacklistCodeId(){
		return this.blacklistCodeId;
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
		return "BuBlacklistManage [blacklistManageId=" + blacklistManageId + ", blacklistCodeId=" + blacklistCodeId
				+ ", uploadAddress=" + uploadAddress + ", uploadTime=" + uploadTime + ", uploadMan=" + uploadMan
				+ ", uploadSuccess=" + uploadSuccess + ", uploadFailure=" + uploadFailure + ", remark=" + remark
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
}
