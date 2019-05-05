package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;

public class ZX9111Redis {

	private String lendMoney91;
	private String lendCntX391;

	public String getLendMoney91() {
		return lendMoney91;
	}

	public void setLendMoney91(String lendMoney91) {
		this.lendMoney91 = lendMoney91;
	}

	public String getLendCntX391() {
		return lendCntX391;
	}

	public void setLendCntX391(String lendCntX391) {
		this.lendCntX391 = lendCntX391;
	}

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("lendMoney91", lendMoney91==null?"unknow":lendMoney91);
		map.put("lendCntX391", lendCntX391==null?"unknow":lendCntX391);
		return map;
	}
	
	
}
