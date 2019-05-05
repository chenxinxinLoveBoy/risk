package com.shangyong.backend.entity.redis.fraud2_0;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * 评分卡 2.0 基本信息
 */
public class BasicInfo20Redis {

	private String isMale;//传如身份证判断1-男,0-女
	private String birthProvinceId;	//传如身份证判断生日省份
		
	public String getIsMale() {
		return isMale;
	}
	public void setIsMale(String isMale) {
		this.isMale = isMale;
	}
	public String getBirthProvinceId() {
		return birthProvinceId;
	}
	public void setBirthProvinceId(String birthProvinceId) {
		this.birthProvinceId = birthProvinceId;
	}
	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("isMale", !StringUtils.hasText(isMale)?"unknow":isMale);
		map.put("birthProvinceId", !StringUtils.hasText(birthProvinceId)?"unknow":birthProvinceId);
		return map;
	}
}
