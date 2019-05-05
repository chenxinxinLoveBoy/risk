package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 评分卡大类表bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
public class ScScoreBig extends BaseBo {

	private Integer bigHisId;// 历史表主键

	/** 评分卡大类序号 **/
	private Integer scoreBigId;

	/** 征信机构类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚） **/
	private String creditType;

	/** 评分项名称 **/
	private String scoreName;

	/** 评分项规则大类编号 **/
	private String scoreBigCode;

	/** 评分项规则明细 **/
	private String scoreRuleDetail;

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

	/** 创建人姓名 **/
	private String createName;

	/** 修改人姓名 **/
	private String modifyName;

	private String updateScScoreSmall; // 页面条件用到

	/** 历史时间开始区间startTimeInterval **/
	private String startTimeInterval;

	/** 历史时间结束区间endTimeInterval **/
	private String endTimeInterval;

	private String percent;// 权重

	public ScScoreBig() {
		super();
	}

	public ScScoreBig(Integer bigHisId, Integer scoreBigId, String creditType, String scoreName, String scoreBigCode,
			String scoreRuleDetail, String state, Integer version, String createTime, String createMan,
			String modifyTime, String modifyMan, String remark, String createName, String modifyName,
			String updateScScoreSmall, String startTimeInterval, String endTimeInterval, String percent) {
		super();
		this.bigHisId = bigHisId;
		this.scoreBigId = scoreBigId;
		this.creditType = creditType;
		this.scoreName = scoreName;
		this.scoreBigCode = scoreBigCode;
		this.scoreRuleDetail = scoreRuleDetail;
		this.state = state;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.createName = createName;
		this.modifyName = modifyName;
		this.updateScScoreSmall = updateScScoreSmall;
		this.startTimeInterval = startTimeInterval;
		this.endTimeInterval = endTimeInterval;
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "ScScoreBig [bigHisId=" + bigHisId + ", scoreBigId=" + scoreBigId + ", creditType=" + creditType
				+ ", scoreName=" + scoreName + ", scoreBigCode=" + scoreBigCode + ", scoreRuleDetail=" + scoreRuleDetail
				+ ", state=" + state + ", version=" + version + ", createTime=" + createTime + ", createMan="
				+ createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark=" + remark
				+ ", createName=" + createName + ", modifyName=" + modifyName + ", updateScScoreSmall="
				+ updateScScoreSmall + ", startTimeInterval=" + startTimeInterval + ", endTimeInterval="
				+ endTimeInterval + ", percent=" + percent + "]";
	}

	public Integer getBigHisId() {
		return bigHisId;
	}

	public void setBigHisId(Integer bigHisId) {
		this.bigHisId = bigHisId;
	}

	public Integer getScoreBigId() {
		return scoreBigId;
	}

	public void setScoreBigId(Integer scoreBigId) {
		this.scoreBigId = scoreBigId;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getScoreName() {
		return scoreName;
	}

	public void setScoreName(String scoreName) {
		this.scoreName = scoreName;
	}

	public String getScoreBigCode() {
		return scoreBigCode;
	}

	public void setScoreBigCode(String scoreBigCode) {
		this.scoreBigCode = scoreBigCode;
	}

	public String getScoreRuleDetail() {
		return scoreRuleDetail;
	}

	public void setScoreRuleDetail(String scoreRuleDetail) {
		this.scoreRuleDetail = scoreRuleDetail;
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

	public String getUpdateScScoreSmall() {
		return updateScScoreSmall;
	}

	public void setUpdateScScoreSmall(String updateScScoreSmall) {
		this.updateScScoreSmall = updateScScoreSmall;
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

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

}
