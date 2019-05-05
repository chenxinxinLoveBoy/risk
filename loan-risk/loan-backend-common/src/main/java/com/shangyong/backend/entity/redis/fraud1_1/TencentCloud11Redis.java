package com.shangyong.backend.entity.redis.fraud1_1;

import java.util.HashMap;

public class TencentCloud11Redis {
	
	private String riskScoreTaf;
	
	public TencentCloud11Redis() {
		super();
	}

	public TencentCloud11Redis(String riskScoreTaf) {
		super();
		this.riskScoreTaf = riskScoreTaf;
	}
	
	public String getRiskScoreTaf() {
		return riskScoreTaf;
	}

	public void setRiskScoreTaf(String riskScoreTaf) {
		this.riskScoreTaf = riskScoreTaf;
	}


	public HashMap<String,String> toMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("riskScoreTaf",this.riskScoreTaf==null ? "unknow" : this.riskScoreTaf);
		return map;
	}
}
