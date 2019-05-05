package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 禁止项规则控制表bean
 * 
 * @author xk
 * @date Sat Jun 10 16:30:22 CST 2017
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScBanControl extends BaseBo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer controlHisId;// 历史表查询主键

	/** 规则序号 **/
	private Integer banControlId; 
	/**
	 * 禁止项模板主键Id
	 */
	private String banControlTplId;
	/**
	 * 禁止项模板名称
	 */
	private String banTplName;

	/** 禁止项规则对应编号 **/
	private String banCode;

	/** 禁止项规则名称 **/
	private String ruleName;

	/** 征信机构类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚） **/
	private String creditType;

	/** 禁止项规则明细 **/
	private String ruleDetail;

	/** 禁止项规则技术比对值 **/
	private String ruleComparisonValue;

	/** 技术比对值类型（01-数值、02-字符、03-集合） **/
	private String ruleComparisonType;

	/** 是否添加拒绝名单:0-否，1-是
	 * @see com.shangyong.backend.common.enums.ScBanControlIfRefuseEnum **/
	private String ifRefuse;

	/** 状态（01-正常、02-失效） **/
	private String state;

	/** 技术校验规则（0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）、0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null、0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致、0301-（集合）集合不为null且size大于0、0302-（集合）集合为null或size小于1） **/
	private String validateRule;

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
	/**
	 * 多选操作
	 */
	private String[] tplIds;
	/**
	 * 多选操作
	 */
	private String[] ids;


	/**历史时间区间startTimeInterval**/
	private String startTimeInterval;
	
	/**历史时间区间endTimeInterval**/
	private String endTimeInterval;
	
	
	
	
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

	public String[] getTplIds() {
		return tplIds;
	}

	public void setTplIds(String[] tplIds) {
		this.tplIds = tplIds;
	}

	public ScBanControl() {
		super(); 
	}

	public Integer getControlHisId() {
		return controlHisId;
	}

	public void setControlHisId(Integer controlHisId) {
		this.controlHisId = controlHisId;
	}

	public Integer getBanControlId() {
		return banControlId;
	}

	public void setBanControlId(Integer banControlId) {
		this.banControlId = banControlId;
	}

	public String getBanControlTplId() {
		return banControlTplId;
	}

	public void setBanControlTplId(String banControlTplId) {
		this.banControlTplId = banControlTplId;
	}

	public String getBanCode() {
		return banCode;
	}

	public void setBanCode(String banCode) {
		this.banCode = banCode;
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

	public String getIfRefuse() {
		return ifRefuse;
	}

	public void setIfRefuse(String ifRefuse) {
		this.ifRefuse = ifRefuse;
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

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBanTplName() {
		return banTplName;
	}

	public void setBanTplName(String banTplName) {
		this.banTplName = banTplName;
	}

	public ScBanControl(Integer controlHisId, Integer banControlId, String banControlTplId, String banCode,
			String ruleName, String creditType, String ruleDetail, String ruleComparisonValue,
			String ruleComparisonType, String ifRefuse, String state, String validateRule, Integer version,
			String createTime, String createMan, String modifyTime, String modifyMan, String remark, String createName,
			String modifyName, String[] ids) {
		super();
		this.controlHisId = controlHisId;
		this.banControlId = banControlId;
		this.banControlTplId = banControlTplId;
		this.banCode = banCode;
		this.ruleName = ruleName;
		this.creditType = creditType;
		this.ruleDetail = ruleDetail;
		this.ruleComparisonValue = ruleComparisonValue;
		this.ruleComparisonType = ruleComparisonType;
		this.ifRefuse = ifRefuse;
		this.state = state;
		this.validateRule = validateRule;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.createName = createName;
		this.modifyName = modifyName;
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "ScBanControl [controlHisId=" + controlHisId + ", banControlId=" + banControlId + ", banControlTplId="
				+ banControlTplId + ", banCode=" + banCode + ", ruleName=" + ruleName + ", creditType=" + creditType
				+ ", ruleDetail=" + ruleDetail + ", ruleComparisonValue=" + ruleComparisonValue
				+ ", ruleComparisonType=" + ruleComparisonType + ", ifRefuse=" + ifRefuse + ", state=" + state
				+ ", validateRule=" + validateRule + ", version=" + version + ", createTime=" + createTime
				+ ", createMan=" + createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark="
				+ remark + ", createName=" + createName + ", modifyName=" + modifyName + ", ids=" + Arrays.toString(ids)
				+ "]";
	}

	 
}
