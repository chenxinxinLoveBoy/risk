package com.shangyong.backend.entity;

import java.io.Serializable;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈大类表bean
 * @author xxh
 * @date Fri Jul 21 13:39:50 CST 2017
 **/
public class ScFraudRuleBig extends BaseBo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**欺诈大类序号**/
	private Integer fraudRuleBigId;

	/**征信机构类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚）**/
	private String creditType;

	/**欺诈项名称**/
	private String fraudRuleName;

	/**欺诈规则大类编号**/
	private String fraudRuleBigCode;

	/**欺诈规则明细**/
	private String fraudRuleDetail;

	/**状态（01-正常、02-失效）**/
	private String state;

	/**版本编号 **/
	private Integer version;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**创建人姓名**/
	private String createName;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**修改人姓名**/
	private String modifyName;

	/****/
	private String remark;
	
	private String updateFraudRule;
	private String percent;//权重

	public ScFraudRuleBig() {
		super();
	}

	public ScFraudRuleBig(Integer fraudRuleBigId, String creditType, String fraudRuleName, String fraudRuleBigCode,
			String fraudRuleDetail, String state, Integer version, String createTime, String createMan,
			String createName, String modifyTime, String modifyMan, String modifyName, String remark,
			String updateFraudRule, String percent) {
		super();
		this.fraudRuleBigId = fraudRuleBigId;
		this.creditType = creditType;
		this.fraudRuleName = fraudRuleName;
		this.fraudRuleBigCode = fraudRuleBigCode;
		this.fraudRuleDetail = fraudRuleDetail;
		this.state = state;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.createName = createName;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.modifyName = modifyName;
		this.remark = remark;
		this.updateFraudRule = updateFraudRule;
		this.percent = percent;
	}

	public Integer getFraudRuleBigId() {
		return fraudRuleBigId;
	}

	public void setFraudRuleBigId(Integer fraudRuleBigId) {
		this.fraudRuleBigId = fraudRuleBigId;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getFraudRuleName() {
		return fraudRuleName;
	}

	public void setFraudRuleName(String fraudRuleName) {
		this.fraudRuleName = fraudRuleName;
	}

	public String getFraudRuleBigCode() {
		return fraudRuleBigCode;
	}

	public void setFraudRuleBigCode(String fraudRuleBigCode) {
		this.fraudRuleBigCode = fraudRuleBigCode;
	}

	public String getFraudRuleDetail() {
		return fraudRuleDetail;
	}

	public void setFraudRuleDetail(String fraudRuleDetail) {
		this.fraudRuleDetail = fraudRuleDetail;
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

	public String getUpdateFraudRule() {
		return updateFraudRule;
	}

	public void setUpdateFraudRule(String updateFraudRule) {
		this.updateFraudRule = updateFraudRule;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
}
