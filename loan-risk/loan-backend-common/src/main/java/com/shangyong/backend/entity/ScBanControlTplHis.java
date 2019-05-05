package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 禁止项模板历史记录表bean
 * @author xiajiyun
 * @date Tue Jul 18 21:26:23 CST 2017
 **/
public class ScBanControlTplHis extends BaseBo {

	/**禁止项模板历史记录序号**/
	private Integer banControlTplHisId;

	/**禁止项模板Id**/
	private Integer banControlTplId;

	/**禁止项模板业务编号**/
	private String banTplCode;

	/**禁止项模板名称**/
	private String banTplName;

	/**模板生效开始时间（大于等于）**/
	private String startTime;

	/**模板生效结束时间（小于）**/
	private String endTime;

	/**模板权重**/
	private String tplPercent;

	/**优先级（值越小优先级越高）**/
	private Integer level;

	/**执行类型 1 按时段执行 2 按特定日期执行**/
	private Integer executeType;

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

	public Integer getBanControlTplHisId() {
		return banControlTplHisId;
	}

	public void setBanControlTplHisId(Integer banControlTplHisId) {
		this.banControlTplHisId = banControlTplHisId;
	}

	public Integer getBanControlTplId() {
		return banControlTplId;
	}

	public void setBanControlTplId(Integer banControlTplId) {
		this.banControlTplId = banControlTplId;
	}

	public String getBanTplCode() {
		return banTplCode;
	}

	public void setBanTplCode(String banTplCode) {
		this.banTplCode = banTplCode;
	}

	public String getBanTplName() {
		return banTplName;
	}

	public void setBanTplName(String banTplName) {
		this.banTplName = banTplName;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getExecuteType() {
		return executeType;
	}

	public void setExecuteType(Integer executeType) {
		this.executeType = executeType;
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

	public ScBanControlTplHis() {
		super(); 
	}

	public ScBanControlTplHis(Integer banControlTplHisId, Integer banControlTplId, String banTplCode, String banTplName,
			String startTime, String endTime, String tplPercent, Integer level, Integer executeType, String operation,
			String state, Integer version, String createTime, String createMan, String createName, String modifyTime,
			String modifyMan, String modifyName, String remark, String recordNewtime) {
		super();
		this.banControlTplHisId = banControlTplHisId;
		this.banControlTplId = banControlTplId;
		this.banTplCode = banTplCode;
		this.banTplName = banTplName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tplPercent = tplPercent;
		this.level = level;
		this.executeType = executeType;
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
		return "ScBanControlTplHis [banControlTplHisId=" + banControlTplHisId + ", banControlTplId=" + banControlTplId
				+ ", banTplCode=" + banTplCode + ", banTplName=" + banTplName + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", tplPercent=" + tplPercent + ", level=" + level + ", executeType="
				+ executeType + ", operation=" + operation + ", state=" + state + ", version=" + version
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", createName=" + createName
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", modifyName=" + modifyName + ", remark="
				+ remark + ", recordNewtime=" + recordNewtime + "]";
	}


	 
}
