package com.shangyong.backend.entity.redis.fraud1_8;

import java.util.HashMap;

public class BasicInfo18Redis {

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
		map.put("isMale", isMale==null?"unknow":isMale);
		map.put("birthProvinceId", birthProvinceId==null?"unknow":birthProvinceId);
		return map;
	}
}
