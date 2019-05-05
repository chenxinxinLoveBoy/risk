package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 决策树表bean
 * 
 * @date Thu Aug 03 21:51:02 CST 2017
 **/
public class ScDecisionTree extends BaseBo {

	/** 决策树序号ID **/
	private Integer decisionTreeId;

	/** 决策树业务编号 **/
	private String decisionTreeCode;

	/** 决策树名称 **/
	private String decisionTreeName;

	/** 生效开始时间（大于等于） **/
	private String startTime;

	/** 生效结束时间（小于） **/
	private String endTime;

	/** 模板权重 **/
	private String tplPercent;

	/** 执行类型 1 按时段执行 2 跨天执行 **/
	private Integer executeType;

	/** 优先级（值越小优先级越高） **/
	private Integer level;

	/** 禁止项模板Id **/
	private Integer banControlTplId;

	/** 欺诈评分项模板Id **/
	private String fraudRuleTplId;

	/** 信用评分项模板Id **/
	private Integer scoreTplId;

	/** 状态（01-正常、02-失效） **/
	private String state;
	
	/** 交换机名字（01-正常、02-失效） **/
	private String exchanges;

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

	/** 备注 **/
	private String remark;

	/** 实施类型(01-大数据)
	 * @see com.shangyong.backend.common.enums.DecisionTreeImplementTypeEnum
	 */
	private String implementType;

	public ScDecisionTree() {
		super();
	}

	public ScDecisionTree(Integer decisionTreeId, String decisionTreeCode, String decisionTreeName, String startTime,
			String endTime, String tplPercent, Integer executeType, Integer level, Integer banControlTplId,
			String fraudRuleTplId, Integer scoreTplId, String state, String exchanges, Integer version,
			String createTime, String createMan, String createName, String modifyTime, String modifyMan,
			String modifyName, String remark, String implementType) {
		super();
		this.decisionTreeId = decisionTreeId;
		this.decisionTreeCode = decisionTreeCode;
		this.decisionTreeName = decisionTreeName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tplPercent = tplPercent;
		this.executeType = executeType;
		this.level = level;
		this.banControlTplId = banControlTplId;
		this.fraudRuleTplId = fraudRuleTplId;
		this.scoreTplId = scoreTplId;
		this.state = state;
		this.exchanges = exchanges;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.createName = createName;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.modifyName = modifyName;
		this.remark = remark;
		this.implementType = implementType;
	}



	public String getExchanges() {
		return exchanges;
	}



	public void setExchanges(String exchanges) {
		this.exchanges = exchanges;
	}



	public Integer getDecisionTreeId() {
		return decisionTreeId;
	}

	public void setDecisionTreeId(Integer decisionTreeId) {
		this.decisionTreeId = decisionTreeId;
	}

	public String getDecisionTreeCode() {
		return decisionTreeCode;
	}

	public void setDecisionTreeCode(String decisionTreeCode) {
		this.decisionTreeCode = decisionTreeCode;
	}

	public String getDecisionTreeName() {
		return decisionTreeName;
	}

	public void setDecisionTreeName(String decisionTreeName) {
		this.decisionTreeName = decisionTreeName;
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

	public String getTplPercent() {
		return tplPercent;
	}

	public void setTplPercent(String tplPercent) {
		this.tplPercent = tplPercent;
	}

	public Integer getExecuteType() {
		return executeType;
	}

	public void setExecuteType(Integer executeType) {
		this.executeType = executeType;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getBanControlTplId() {
		return banControlTplId;
	}

	public void setBanControlTplId(Integer banControlTplId) {
		this.banControlTplId = banControlTplId;
	}

	public String getFraudRuleTplId() {
		return fraudRuleTplId;
	}

	public void setFraudRuleTplId(String fraudRuleTplId) {
		this.fraudRuleTplId = fraudRuleTplId;
	}

	public Integer getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(Integer scoreTplId) {
		this.scoreTplId = scoreTplId;
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

	public String getImplementType() {
		return implementType;
	}

	public void setImplementType(String implementType) {
		this.implementType = implementType;
	}

}
