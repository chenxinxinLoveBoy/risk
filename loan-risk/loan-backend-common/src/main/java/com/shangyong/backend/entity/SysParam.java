package com.shangyong.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 系统参数表bean
 * 
 * @author xk
 * @date Wed Jun 07 20:37:07 CST 2017
 **/
@JsonIgnoreProperties(ignoreUnknown = true) // 可以忽略掉从JSON(由于在应用中没有完全匹配的POJO)中获得的所有“多余的”属性(此处忽略从Basebo继承来的pageIndex和pageSize属性)
public class SysParam extends BaseBo {

	private Integer paramHisId;// 历史表主键
	/** 参数序号 **/
	private Integer sysParamId;

	/** 参数编号 **/
	private String paramValue;

	/** 参数名称 **/
	private String paramName;

	/** 参数值1 **/
	private String paramValueOne;

	/** 参数1名称 **/
	private String paramNameOne;

	/** 参数值2 **/
	private String paramValueTwo;

	/** 参数2名称 **/
	private String paramNameTwo;

	/** 参数值3 **/
	private String paramValueThree;

	/** 参数3名称 **/
	private String paramNameThree;

	/** 参数值4 **/
	private String paramValueFour;

	/** 参数4名称 **/
	private String paramNameFour;

	/** 参数值5 **/
	private String paramValueFive;

	/** 参数5名称 **/
	private String paramNameFive;

	/** 状态 **/
	private String statue;

	/** 创建时间 **/
	private String createTime;

	/** 创建人 **/
	private String createMan;

	/** 修改人 **/
	private String modifyMan;

	/** 修改时间 **/
	private String modifyTime;

	/** 版本号 **/
	private Integer version;

	/** 备注 **/
	private String remark;

	/** 业务表主键 **/
	private Integer business_param_id;

	/** 记录新增时间 **/
	private String recordNewtime;

	private String beginTime1;// 用于页面查询

	private String endTime1;// 用于页面查询

	public SysParam() {
		super();
	}

	public SysParam(Integer paramHisId, Integer sysParamId, String paramValue, String paramName, String paramValueOne,
			String paramNameOne, String paramValueTwo, String paramNameTwo, String paramValueThree,
			String paramNameThree, String paramValueFour, String paramNameFour, String paramValueFive,
			String paramNameFive, String statue, String createTime, String createMan, String modifyMan,
			String modifyTime, Integer version, String remark, Integer business_param_id, String recordNewtime,
			String beginTime1, String endTime1) {
		super();
		this.paramHisId = paramHisId;
		this.sysParamId = sysParamId;
		this.paramValue = paramValue;
		this.paramName = paramName;
		this.paramValueOne = paramValueOne;
		this.paramNameOne = paramNameOne;
		this.paramValueTwo = paramValueTwo;
		this.paramNameTwo = paramNameTwo;
		this.paramValueThree = paramValueThree;
		this.paramNameThree = paramNameThree;
		this.paramValueFour = paramValueFour;
		this.paramNameFour = paramNameFour;
		this.paramValueFive = paramValueFive;
		this.paramNameFive = paramNameFive;
		this.statue = statue;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.modifyTime = modifyTime;
		this.version = version;
		this.remark = remark;
		this.business_param_id = business_param_id;
		this.recordNewtime = recordNewtime;
		this.beginTime1 = beginTime1;
		this.endTime1 = endTime1;
	}

	public Integer getParamHisId() {
		return paramHisId;
	}

	public void setParamHisId(Integer paramHisId) {
		this.paramHisId = paramHisId;
	}

	public Integer getSysParamId() {
		return sysParamId;
	}

	public void setSysParamId(Integer sysParamId) {
		this.sysParamId = sysParamId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValueOne() {
		return paramValueOne;
	}

	public void setParamValueOne(String paramValueOne) {
		this.paramValueOne = paramValueOne;
	}

	public String getParamNameOne() {
		return paramNameOne;
	}

	public void setParamNameOne(String paramNameOne) {
		this.paramNameOne = paramNameOne;
	}

	public String getParamValueTwo() {
		return paramValueTwo;
	}

	public void setParamValueTwo(String paramValueTwo) {
		this.paramValueTwo = paramValueTwo;
	}

	public String getParamNameTwo() {
		return paramNameTwo;
	}

	public void setParamNameTwo(String paramNameTwo) {
		this.paramNameTwo = paramNameTwo;
	}

	public String getParamValueThree() {
		return paramValueThree;
	}

	public void setParamValueThree(String paramValueThree) {
		this.paramValueThree = paramValueThree;
	}

	public String getParamNameThree() {
		return paramNameThree;
	}

	public void setParamNameThree(String paramNameThree) {
		this.paramNameThree = paramNameThree;
	}

	public String getParamValueFour() {
		return paramValueFour;
	}

	public void setParamValueFour(String paramValueFour) {
		this.paramValueFour = paramValueFour;
	}

	public String getParamNameFour() {
		return paramNameFour;
	}

	public void setParamNameFour(String paramNameFour) {
		this.paramNameFour = paramNameFour;
	}

	public String getParamValueFive() {
		return paramValueFive;
	}

	public void setParamValueFive(String paramValueFive) {
		this.paramValueFive = paramValueFive;
	}

	public String getParamNameFive() {
		return paramNameFive;
	}

	public void setParamNameFive(String paramNameFive) {
		this.paramNameFive = paramNameFive;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
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

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBusiness_param_id() {
		return business_param_id;
	}

	public void setBusiness_param_id(Integer business_param_id) {
		this.business_param_id = business_param_id;
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

	@Override
	public String toString() {
		return "SysParam [paramHisId=" + paramHisId + ", sysParamId=" + sysParamId + ", paramValue=" + paramValue
				+ ", paramName=" + paramName + ", paramValueOne=" + paramValueOne + ", paramNameOne=" + paramNameOne
				+ ", paramValueTwo=" + paramValueTwo + ", paramNameTwo=" + paramNameTwo + ", paramValueThree="
				+ paramValueThree + ", paramNameThree=" + paramNameThree + ", paramValueFour=" + paramValueFour
				+ ", paramNameFour=" + paramNameFour + ", paramValueFive=" + paramValueFive + ", paramNameFive="
				+ paramNameFive + ", statue=" + statue + ", createTime=" + createTime + ", createMan=" + createMan
				+ ", modifyMan=" + modifyMan + ", modifyTime=" + modifyTime + ", version=" + version + ", remark="
				+ remark + ", business_param_id=" + business_param_id + ", recordNewtime=" + recordNewtime
				+ ", beginTime1=" + beginTime1 + ", endTime1=" + endTime1 + "]";
	}

	
}
