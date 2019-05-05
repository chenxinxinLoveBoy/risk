package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 闪贷评分与授信额度定义表bean
 * 
 * @author xk
 * @date Fri Jun 16 21:20:14 CST 2017
 **/
public class ScScoreCredit extends BaseBo {

	/** 评分与授信额度序号 **/
	private Integer scoreCreditId;

	/** 分数最大值 **/
	private String scoreMax;

	/** 分数最小值 **/
	private String scoreMin;

	/** 授信额度值 **/
	private String creditMoney;

	/** 状态（01-正常、02-失效） **/
	private String state;

	/** 版本编号 **/
	private Integer version;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 备注 **/
	private String remark;

	/** 分数 **/
	private String score;

	/** 创建人姓名 **/
	private String createName;

	/** 修改人姓名 **/
	private String modifyName;

	/** 历史时间开始区间startTimeInterval **/
	private String startTimeInterval;

	/** 历史时间结束区间endTimeInterval **/
	private String endTimeInterval;

	/** 模板id **/
	private String scoreTplId;

	private String scoreTplName;// 模板名称

	/** 提升授信额度值 **/
	private String promoteCreditMoney;

	public ScScoreCredit() {
		super();
	}

	public ScScoreCredit(Integer scoreCreditId, String scoreMax, String scoreMin, String creditMoney, String state,
			Integer version, String createTime, String createMan, String modifyTime, String modifyMan, String remark,
			String score, String createName, String modifyName, String startTimeInterval, String endTimeInterval,
			String scoreTplId, String scoreTplName, String promoteCreditMoney) {
		super();
		this.scoreCreditId = scoreCreditId;
		this.scoreMax = scoreMax;
		this.scoreMin = scoreMin;
		this.creditMoney = creditMoney;
		this.state = state;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.score = score;
		this.createName = createName;
		this.modifyName = modifyName;
		this.startTimeInterval = startTimeInterval;
		this.endTimeInterval = endTimeInterval;
		this.scoreTplId = scoreTplId;
		this.scoreTplName = scoreTplName;
		this.promoteCreditMoney = promoteCreditMoney;
	}

	public Integer getScoreCreditId() {
		return scoreCreditId;
	}

	public void setScoreCreditId(Integer scoreCreditId) {
		this.scoreCreditId = scoreCreditId;
	}

	public String getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(String scoreMax) {
		this.scoreMax = scoreMax;
	}

	public String getScoreMin() {
		return scoreMin;
	}

	public void setScoreMin(String scoreMin) {
		this.scoreMin = scoreMin;
	}

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
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

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
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

	public String getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(String scoreTplId) {
		this.scoreTplId = scoreTplId;
	}

	public String getScoreTplName() {
		return scoreTplName;
	}

	public void setScoreTplName(String scoreTplName) {
		this.scoreTplName = scoreTplName;
	}

	public String getPromoteCreditMoney() {
		return promoteCreditMoney;
	}

	public void setPromoteCreditMoney(String promoteCreditMoney) {
		this.promoteCreditMoney = promoteCreditMoney;
	}

	@Override
	public String toString() {
		return "ScScoreCredit [scoreCreditId=" + scoreCreditId + ", scoreMax=" + scoreMax + ", scoreMin=" + scoreMin
				+ ", creditMoney=" + creditMoney + ", state=" + state + ", version=" + version + ", createTime="
				+ createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan
				+ ", remark=" + remark + ", score=" + score + ", createName=" + createName + ", modifyName="
				+ modifyName + ", startTimeInterval=" + startTimeInterval + ", endTimeInterval=" + endTimeInterval
				+ ", scoreTplId=" + scoreTplId + ", scoreTplName=" + scoreTplName + ", promoteCreditMoney="
				+ promoteCreditMoney + "]";
	}

}
