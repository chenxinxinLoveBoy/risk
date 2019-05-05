package com.shangyong.backend.entity;

public class ApplicationPersonCount {

	/** 统计数  */
	private String count;
	
	/** 通过人数统计数  */
	private String passPersonCount;
	
	/** 未通过人数统计数  */
	private String noPassPersonCount;
	
	/** 比率  */
	private String rate;
	
	/** 统计人数  */
	private String personCount;
	
	/** 时间单位（0——时；1——天；2——周；3——月）  */
	private String timeType;
	
	/** 时间单位值  */
	private String timeValue;
	
	/** 开始时间  */
	private String startTime;
	
	/** 结束时间  */
	private String endTime;
	
	/** 决策树ID  */
	private String treeId;
	
	/** 申请来源  */
	private String source;
	
	/** 客户类型  */
	private String appLevel;
	
	/** 用户姓名  */
	private String nickName;
	
	public String getAppLevel() {
		return appLevel;
	}

	public void setAppLevel(String appLevel) {
		this.appLevel = appLevel;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
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

	public String getPersonCount() {
		return personCount;
	}

	public void setPersonCount(String personCount) {
		this.personCount = personCount;
	}

	public String getPassPersonCount() {
		return passPersonCount;
	}

	public void setPassPersonCount(String passPersonCount) {
		this.passPersonCount = passPersonCount;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getNoPassPersonCount() {
		return noPassPersonCount;
	}

	public void setNoPassPersonCount(String noPassPersonCount) {
		this.noPassPersonCount = noPassPersonCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
