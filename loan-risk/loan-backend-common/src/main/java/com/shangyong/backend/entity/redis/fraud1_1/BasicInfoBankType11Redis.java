package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;

public class BasicInfoBankType11Redis {

	private String bankType;//1-工商银行,2-农业银行,3-建设银行,4-交通银行,5-中国银行,6-招商银行,7-其他
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("bankType", bankType==null?"unknow":bankType);
		return map;
	}
}
