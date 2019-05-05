package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 信用评分项模板历史表bean
 * 
 * @date Thu Jul 27 13:54:27 CST 2017
 **/
public class ScScoreTplHis extends BaseBo {

	/** 信用评分项模板历史表Id **/
	private Integer scoreTplHisId;

	/** 信用评分项模板Id **/
	private Integer scoreTplId;

	/** 信用评分项模板业务编号 **/
	private String scoreTplCode;

	/** 信用评分项模板名称 **/
	private String scoreTplName;

	/** 模板生效开始时间（大于等于） **/
	private String startTime;

	/** 模板生效结束时间（小于） **/
	private String endTime;

	/** 模板权重 **/
	private String tplPercent;

	/** 执行类型 1 按时段执行 2 跨天执行 **/
	private Integer executeType;

	/** 优先级（值越小优先级越高） **/
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

	/** 记录新增时间 **/
	private String recordNewtime;

	private String beginTime1;// 用于页面查询

	private String endTime1;// 用于页面查询

	public ScScoreTplHis() {
		super();
	}

	public ScScoreTplHis(Integer scoreTplHisId, Integer scoreTplId, String scoreTplCode, String scoreTplName,
			String startTime, String endTime, String tplPercent, Integer executeType, Integer level, String operation,
			String state, Integer version, String createTime, String createMan, String createName, String modifyTime,
			String modifyMan, String modifyName, String remark, String recordNewtime, String beginTime1,
			String endTime1) {
		super();
		this.scoreTplHisId = scoreTplHisId;
		this.scoreTplId = scoreTplId;
		this.scoreTplCode = scoreTplCode;
		this.scoreTplName = scoreTplName;
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
		this.beginTime1 = beginTime1;
		this.endTime1 = endTime1;
	}

	public Integer getScoreTplHisId() {
		return scoreTplHisId;
	}

	public void setScoreTplHisId(Integer scoreTplHisId) {
		this.scoreTplHisId = scoreTplHisId;
	}

	public Integer getScoreTplId() {
		return scoreTplId;
	}

	public void setScoreTplId(Integer scoreTplId) {
		this.scoreTplId = scoreTplId;
	}

	public String getScoreTplCode() {
		return scoreTplCode;
	}

	public void setScoreTplCode(String scoreTplCode) {
		this.scoreTplCode = scoreTplCode;
	}

	public String getScoreTplName() {
		return scoreTplName;
	}

	public void setScoreTplName(String scoreTplName) {
		this.scoreTplName = scoreTplName;
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

	public String getBeginTime1() {
		return beginTime1;
	}

	public void setBeginTime1(String beginTime1) {
		this.beginTime1 = beginTime1;
	}

	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}

}
