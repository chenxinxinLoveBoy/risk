package com.shangyong.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 评分卡小类表bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScScoreSmall extends BaseBo {

	private Integer smallHisId;// 历史表主键
	/** 评分卡小类序号 **/
	private Integer scoreSmallId;

	/** 评分卡大类序号 **/
	private Integer scoreBigId;

	/** 评分项规则名称 **/
	private String scoreRuleName;

	/** 评分项规则小类编号 **/
	private String scoreSmallCode;

	/** 评分项规则技术比对值 **/
	private String ruleScoreValue;

	/** 分值 **/
	private String score;

	/** 技术比对值类型（01-数值、02-字符、03-集合） **/
	private String ruleComparisonType;

	/** 技术校验规则（0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）、0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null、0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致、0301-（集合）集合不为null且size大于0、0302-（集合）集合为null或size小于1） **/
	private String validateRule;

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
	
	/**历史时间开始区间startTimeInterval**/
	private String startTimeInterval;
	
	/**历史时间结束区间endTimeInterval**/
	private String endTimeInterval;

	/** 权重 */
	private String percent;

	/** 评分模板id **/
	private Integer scoreTplId;

	/** 多选操作 **/
	private String[] ids;

	public ScScoreSmall() {
		super();
	}

	public ScScoreSmall(Integer smallHisId, Integer scoreSmallId, Integer scoreBigId, String scoreRuleName,
			String scoreSmallCode, String ruleScoreValue, String score, String ruleComparisonType, String validateRule,
			String state, Integer version, String createTime, String createMan, String modifyTime, String modifyMan,
			String remark, String createName, String modifyName, String percent, Integer scoreTplId, String[] ids) {
		super();
		this.smallHisId = smallHisId;
		this.scoreSmallId = scoreSmallId;
		this.scoreBigId = scoreBigId;
		this.scoreRuleName = scoreRuleName;
		this.scoreSmallCode = scoreSmallCode;
		this.ruleScoreValue = ruleScoreValue;
		this.score = score;
		this.ruleComparisonType = ruleComparisonType;
		this.validateRule = validateRule;
		this.state = state;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.createName = createName;
		this.modifyName = modifyName;
		this.percent = percent;
		this.scoreTplId = scoreTplId;
		this.ids = ids;
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

	public Integer getSmallHisId() {
		return smallHisId;
	}

	public void setSmallHisId(Integer smallHisId) {
		this.smallHisId = smallHisId;
	}

	public Integer getScoreSmallId() {
		return scoreSmallId;
	}

	public void setScoreSmallId(Integer scoreSmallId) {
		this.scoreSmallId = scoreSmallId;
	}

	public Integer getScoreBigId() {
		return scoreBigId;
	}

	public void setScoreBigId(Integer scoreBigId) {
		this.scoreBigId = scoreBigId;
	}

	public String getScoreRuleName() {
		return scoreRuleName;
	}

	public void setScoreRuleName(String scoreRuleName) {
		this.scoreRuleName = scoreRuleName;
	}

	public String getScoreSmallCode() {
		return scoreSmallCode;
	}

	public void setScoreSmallCode(String scoreSmallCode) {
		this.scoreSmallCode = scoreSmallCode;
	}

	public String getRuleScoreValue() {
		return ruleScoreValue;
	}

	public void setRuleScoreValue(String ruleScoreValue) {
		this.ruleScoreValue = ruleScoreValue;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getRuleComparisonType() {
		return ruleComparisonType;
	}

	public void setRuleComparisonType(String ruleComparisonType) {
		this.ruleComparisonType = ruleComparisonType;
	}

	public String getValidateRule() {
		return validateRule;
	}

	public void setValidateRule(String validateRule) {
		this.validateRule = validateRule;
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

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public Integer getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(Integer scoreTplId) {
		this.scoreTplId = scoreTplId;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
