package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈风险阈值历史表bean
 * 
 * @author xxj
 * @date Thu Jul 06 20:09:28 CST 2017
 **/
public class ScThresholdHistory extends BaseBo {

	/** 历史表序号 **/
	private Integer historySerialnumber;

	/** 欺诈风险阈值表序号 **/
	private Integer serialnumber;

	/** 欺诈阈值最小值 **/
	private String minimum;

	/** 欺诈阈值最大值 **/
	private String maximum;
	
	/**欺诈阈值**/
	private String mum;

	/**欺诈对比方式**/
	private String mumType;

	/** 结果标签（编号） **/
	private String resultsTab;

	/** 状态 **/
	private Integer state;

	/** 版本编号 **/
	private Integer version;

	/** 创建人编号 **/
	private String createNo;

	/** 创建人姓名 **/
	private String createMan;

	/** 修改人编号 **/
	private String modifyNo;

	/** 修改人姓名 **/
	private String modifyMan;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String modifyTime;

	/** 备注 **/
	private String remark;

	/** 分数 **/
	private String score;

	/** 记录新增时间 **/
	private String recordNewtime;

	/** 历史时间区间startTimeInterval **/
	private String startTimeInterval;

	/** 历史时间区间endTimeInterval **/
	private String endTimeInterval;

	/** 模板id **/
	private String fraudRuleTplId;

	private String fraudRuleTplName;// 模板名称

	public ScThresholdHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ScThresholdHistory(Integer historySerialnumber, Integer serialnumber, String minimum, String maximum,
			String mum, String mumType, String resultsTab, Integer state, Integer version, String createNo,
			String createMan, String modifyNo, String modifyMan, String createTime, String modifyTime, String remark,
			String score, String recordNewtime, String startTimeInterval, String endTimeInterval, String fraudRuleTplId,
			String fraudRuleTplName) {
		super();
		this.historySerialnumber = historySerialnumber;
		this.serialnumber = serialnumber;
		this.minimum = minimum;
		this.maximum = maximum;
		this.mum = mum;
		this.mumType = mumType;
		this.resultsTab = resultsTab;
		this.state = state;
		this.version = version;
		this.createNo = createNo;
		this.createMan = createMan;
		this.modifyNo = modifyNo;
		this.modifyMan = modifyMan;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
		this.score = score;
		this.recordNewtime = recordNewtime;
		this.startTimeInterval = startTimeInterval;
		this.endTimeInterval = endTimeInterval;
		this.fraudRuleTplId = fraudRuleTplId;
		this.fraudRuleTplName = fraudRuleTplName;
	}


	public String getMum() {
		return mum;
	}


	public void setMum(String mum) {
		this.mum = mum;
	}


	public String getMumType() {
		return mumType;
	}


	public void setMumType(String mumType) {
		this.mumType = mumType;
	}


	public Integer getHistorySerialnumber() {
		return historySerialnumber;
	}

	public void setHistorySerialnumber(Integer historySerialnumber) {
		this.historySerialnumber = historySerialnumber;
	}

	public Integer getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getResultsTab() {
		return resultsTab;
	}

	public void setResultsTab(String resultsTab) {
		this.resultsTab = resultsTab;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreateNo() {
		return createNo;
	}

	public void setCreateNo(String createNo) {
		this.createNo = createNo;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyNo() {
		return modifyNo;
	}

	public void setModifyNo(String modifyNo) {
		this.modifyNo = modifyNo;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getRecordNewtime() {
		return recordNewtime;
	}

	public void setRecordNewtime(String recordNewtime) {
		this.recordNewtime = recordNewtime;
	}

	public String getStartTimeInterval() {
		return startTimeInterval;
	}

	public void setStartTimeInterval(String startTimeInterval) {
		this.startTimeInterval = startTimeInterval;
	}

	public String getEndTimeInterval() {
		return endTimeInterval;
	}

	public void setEndTimeInterval(String endTimeInterval) {
		this.endTimeInterval = endTimeInterval;
	}

	public String getFraudRuleTplId() {
		return fraudRuleTplId;
	}

	public void setFraudRuleTplId(String fraudRuleTplId) {
		this.fraudRuleTplId = fraudRuleTplId;
	}

	public String getFraudRuleTplName() {
		return fraudRuleTplName;
	}

	public void setFraudRuleTplName(String fraudRuleTplName) {
		this.fraudRuleTplName = fraudRuleTplName;
	}


	@Override
	public String toString() {
		return "ScThresholdHistory [historySerialnumber=" + historySerialnumber + ", serialnumber=" + serialnumber
				+ ", minimum=" + minimum + ", maximum=" + maximum + ", mum=" + mum + ", mumType=" + mumType
				+ ", resultsTab=" + resultsTab + ", state=" + state + ", version=" + version + ", createNo=" + createNo
				+ ", createMan=" + createMan + ", modifyNo=" + modifyNo + ", modifyMan=" + modifyMan + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", remark=" + remark + ", score=" + score
				+ ", recordNewtime=" + recordNewtime + ", startTimeInterval=" + startTimeInterval + ", endTimeInterval="
				+ endTimeInterval + ", fraudRuleTplId=" + fraudRuleTplId + ", fraudRuleTplName=" + fraudRuleTplName
				+ "]";
	}



}
