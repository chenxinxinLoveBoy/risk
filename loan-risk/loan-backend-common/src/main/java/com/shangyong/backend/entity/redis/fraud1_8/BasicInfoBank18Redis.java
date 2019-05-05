package com.shangyong.backend.entity.redis.fraud1_8;

import java.util.HashMap;

public class BasicInfoBank18Redis {

	private String bankType;		//1-工商银行,2-农业银行,3-建设银行,4-交通银行,5-中国银行,6-招商银行,7-其他
	private String educationCode;	//'文化程度编码（1-博士，2-硕士，3-本科，4-大专，5-中专，6-高中，7-职中，8-初中以下）',	
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getEducationCode() {
		return educationCode;
	}
	public void setEducationCode(String educationCode) {
		this.educationCode = educationCode;
	}
	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("bankType", bankType==null?"unknow":bankType);
		map.put("educationCode", educationCode==null?"unknow":educationCode);
		return map;
	}
}
