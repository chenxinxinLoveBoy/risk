package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 欺诈评分项模板历史表bean
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
public class ScFraudRuleTplHis extends BaseBo {

	/**欺诈评分项模板历史表Id**/
	private Integer fraudRuleTplHisId;

	/**欺诈评分项模板Id**/
	private Integer fraudRuleTplId;

	/**欺诈评分项模板业务编号**/
	private Integer fraudRuleTplCode;

	/**欺诈评分项模板名称**/
	private String fraudRuleTplName;

	/**模板生效开始时间（大于等于）**/
	private String startTime;

	/**模板生效结束时间（小于）**/
	private String endTime;

	/**模板权重**/
	private String tplPercent;

	/**执行类型 1 按时段执行 2 跨天执行**/
	private Integer executeType;

	/**优先级（值越大优先级越高）**/
	private Integer level;

	/**操作标志(1-手动、2-自动）**/
	private String operation;

	/**状态（01-正常、02-失效）**/
	private String state;

	/**版本编号**/
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

	/**备注**/
	private String remark;

	/**记录新增时间**/
	private String recordNewtime;
	
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

	public Integer getFraudRuleTplHisId() {
		return fraudRuleTplHisId;
	}

	public void setFraudRuleTplHisId(Integer fraudRuleTplHisId) {
		this.fraudRuleTplHisId = fraudRuleTplHisId;
	}

	public Integer getFraudRuleTplId() {
		return fraudRuleTplId;
	}

	public void setFraudRuleTplId(Integer fraudRuleTplId) {
		this.fraudRuleTplId = fraudRuleTplId;
	}

	public Integer getFraudRuleTplCode() {
		return fraudRuleTplCode;
	}

	public void setFraudRuleTplCode(Integer fraudRuleTplCode) {
		this.fraudRuleTplCode = fraudRuleTplCode;
	}

	public String getFraudRuleTplName() {
		return fraudRuleTplName;
	}

	public void setFraudRuleTplName(String fraudRuleTplName) {
		this.fraudRuleTplName = fraudRuleTplName;
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

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
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

	public String getRecordNewtime() {
		return recordNewtime;
	}

	public void setRecordNewtime(String recordNewtime) {
		this.recordNewtime = recordNewtime;
	}

	public ScFraudRuleTplHis() {
		super(); 
	}

	public ScFraudRuleTplHis(Integer fraudRuleTplHisId, Integer fraudRuleTplId, Integer fraudRuleTplCode,
			String fraudRuleTplName, String startTime, String endTime, String tplPercent, Integer executeType,
			Integer level, String operation, String state, Integer version, String createTime, String createMan,
			String createName, String modifyTime, String modifyMan, String modifyName, String remark,
			String recordNewtime) {
		super();
		this.fraudRuleTplHisId = fraudRuleTplHisId;
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
		this.recordNewtime = recordNewtime;
	}

	@Override
	public String toString() {
		return "ScFraudRuleTplHis [fraudRuleTplHisId=" + fraudRuleTplHisId + ", fraudRuleTplId=" + fraudRuleTplId
				+ ", fraudRuleTplCode=" + fraudRuleTplCode + ", fraudRuleTplName=" + fraudRuleTplName + ", startTime="
				+ startTime + ", endTime=" + endTime + ", tplPercent=" + tplPercent + ", executeType=" + executeType
				+ ", level=" + level + ", operation=" + operation + ", state=" + state + ", version=" + version
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", createName=" + createName
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", modifyName=" + modifyName + ", remark="
				+ remark + ", recordNewtime=" + recordNewtime + "]";
	}
	 
	
}
