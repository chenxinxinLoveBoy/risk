package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈评分项模板表bean
 * 
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
public class ScFraudRuleTpl extends BaseBo {

	/** 欺诈评分项模板Id **/
	private String fraudRuleTplId;

	/** 欺诈评分项模板业务编号 **/
	private Integer fraudRuleTplCode;

	/** 欺诈评分项模板名称 **/
	private String fraudRuleTplName;

	/** 模板生效开始时间（大于等于） **/
	private String startTime;

	/** 模板生效结束时间（小于） **/
	private String endTime;

	/** 模板权重 **/
	private String tplPercent;

	/** 执行类型 1 按时段执行 2 跨天执行 **/
	private Integer executeType;

	/** 优先级（值越大优先级越高） **/
	private Integer level;

	/** 操作标志(1-手动、2-自动） **/
	private String operation;

	/** 状态（01-正常、02-失效） **/
	private String state;

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

	public ScFraudRuleTpl() {
		super();
	}

	public ScFraudRuleTpl(String fraudRuleTplId, Integer fraudRuleTplCode, String fraudRuleTplName, String startTime,
			String endTime, String tplPercent, Integer executeType, Integer level, String operation, String state,
			Integer version, String createTime, String createMan, String createName, String modifyTime,
			String modifyMan, String modifyName, String remark) {
		super();
		this.fraudRuleTplId = fraudRuleTplId;
		this.fraudRuleTplCode = fraudRuleTplCode;
		this.fraudRuleTplName = fraudRuleTplName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tplPercent = tplPercent;
		this.executeType = executeType;
		this.level = level;
		this.operation = operation;
		this.state = state;
		this.version = version;
		this.createTime = createTime;
		this.createMan = createMan;
		this.createName = createName;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.modifyName = modifyName;
		this.remark = remark;
	}

	public void setFraudRuleTplId(String fraudRuleTplId) {
		this.fraudRuleTplId = fraudRuleTplId;
	}

	public String getFraudRuleTplId() {
		return this.fraudRuleTplId;
	}

	public void setFraudRuleTplCode(Integer fraudRuleTplCode) {
		this.fraudRuleTplCode = fraudRuleTplCode;
	}

	public Integer getFraudRuleTplCode() {
		return this.fraudRuleTplCode;
	}

	public void setFraudRuleTplName(String fraudRuleTplName) {
		this.fraudRuleTplName = fraudRuleTplName;
	}

	public String getFraudRuleTplName() {
		return this.fraudRuleTplName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setTplPercent(String tplPercent) {
		this.tplPercent = tplPercent;
	}

	public String getTplPercent() {
		return this.tplPercent;
	}

	public void setExecuteType(Integer executeType) {
		this.executeType = executeType;
	}

	public Integer getExecuteType() {
		return this.executeType;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getCreateMan() {
		return this.createMan;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateName() {
		return this.createName;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getModifyMan() {
		return this.modifyMan;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getModifyName() {
		return this.modifyName;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

}
