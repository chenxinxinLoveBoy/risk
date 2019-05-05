package com.shangyong.backend.entity;

import java.io.Serializable;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈评分项规则表bean
 * 
 * @author xiajiyun
 * @date Wed Jul 19 11:24:23 CST 2017
 **/
public class ScFraudRule extends BaseBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 规则序号 **/
	private Integer fraudRuleId;

	/** 欺诈评分项规则对应编号 **/
	private String fraudRuleCode;
	
	/**欺诈大类序号**/
	private Integer fraudRuleBigId;

	/** 欺诈评分项模板主键Id **/
	private String fraudRuleTplId;

	/** 欺诈评分项模板名称 **/
	private String fraudRuleTplName;

	/** 欺诈评分项规则名称 **/
	private String ruleName;

	/** 征信机构类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚） **/
	private String creditType;

	/** 欺诈评分项规则明细 **/
	private String ruleDetail;

	/** 欺诈评分项规则技术比对值 **/
	private String ruleComparisonValue;

	/** 技术比对值类型（01-数值、02-字符、03-集合） **/
	private String ruleComparisonType;

	/** 状态（01-正常、02-失效） **/
	private String state;

	/** 技术校验规则（0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）、0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null、0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致、0301-（集合）集合不为null且size大于0、0302-（集合）集合为null或size小于1） **/
	private String validateRule;

	/** 分值 **/
	private String score;

	/** 版本编号 **/
	private Integer version;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 创建人姓名 **/
	private String createName;

	/** 修改时间 **/
	private String modifyTime;

	/** 修改人 **/
	private String modifyMan;

	/** 修改人姓名 **/
	private String modifyName;

	/****/
	private String remark;
	
	/** 权重 */
	private String percent;

	/**
	 * 多选操作
	 */
	private String[] ids;

	public ScFraudRule() {
		super();
	}

	public ScFraudRule(Integer fraudRuleId, String fraudRuleCode, Integer fraudRuleBigId, String fraudRuleTplId,
			String fraudRuleTplName, String ruleName, String creditType, String ruleDetail, String ruleComparisonValue,
			String ruleComparisonType, String state, String validateRule, String score, Integer version,
			String createTime, String createMan, String createName, String modifyTime, String modifyMan,
			String modifyName, String remark, String percent, String[] ids) {
		super();
		this.fraudRuleId = fraudRuleId;
		this.fraudRuleCode = fraudRuleCode;
		this.fraudRuleBigId = fraudRuleBigId;
		this.fraudRuleTplId = fraudRuleTplId;
		this.fraudRuleTplName = fraudRuleTplName;
		this.ruleName = ruleName;
		this.creditType = creditType;
		this.ruleDetail = ruleDetail;
		this.ruleComparisonValue = ruleComparisonValue;
		this.ruleComparisonType = ruleComparisonType;
		this.state = state;
		this.validateRule = validateRule;
		this.score = score;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.createName = createName;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.modifyName = modifyName;
		this.remark = remark;
		this.percent = percent;
		this.ids = ids;
	}

	public String getFraudRuleTplName() {
		return fraudRuleTplName;
	}

	public void setFraudRuleTplName(String fraudRuleTplName) {
		this.fraudRuleTplName = fraudRuleTplName;
	}

	public Integer getFraudRuleId() {
		return fraudRuleId;
	}

	public void setFraudRuleId(Integer fraudRuleId) {
		this.fraudRuleId = fraudRuleId;
	}

	public String getFraudRuleCode() {
		return fraudRuleCode;
	}

	public void setFraudRuleCode(String fraudRuleCode) {
		this.fraudRuleCode = fraudRuleCode;
	}

	public String getFraudRuleTplId() {
		return fraudRuleTplId;
	}

	public void setFraudRuleTplId(String fraudRuleTplId) {
		this.fraudRuleTplId = fraudRuleTplId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(String ruleDetail) {
		this.ruleDetail = ruleDetail;
	}

	public String getRuleComparisonValue() {
		return ruleComparisonValue;
	}

	public void setRuleComparisonValue(String ruleComparisonValue) {
		this.ruleComparisonValue = ruleComparisonValue;
	}

	public String getRuleComparisonType() {
		return ruleComparisonType;
	}

	public void setRuleComparisonType(String ruleComparisonType) {
		this.ruleComparisonType = ruleComparisonType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getValidateRule() {
		return validateRule;
	}

	public void setValidateRule(String validateRule) {
		this.validateRule = validateRule;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Integer getFraudRuleBigId() {
		return fraudRuleBigId;
	}

	public void setFraudRuleBigId(Integer fraudRuleBigId) {
		this.fraudRuleBigId = fraudRuleBigId;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

}
