package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 行业关注名单详情信息
 * 
 * @author huangyufa
 *
 *         2018年6月4日
 */
public class IndustryDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 风险信息行业编码 **/
	private String bizCode;
	/** 风险等级 **/
	private String level;
	/** 行业名单风险类型 **/
	private String type;
	/** 风险编码 **/
	private String code;

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
