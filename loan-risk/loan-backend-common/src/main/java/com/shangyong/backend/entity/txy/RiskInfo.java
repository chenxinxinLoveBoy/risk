package com.shangyong.backend.entity.txy;

import java.io.Serializable;

/**
 * 腾讯反欺诈风险详情表bean
 * @author mingke.shi
 * @date Sun Dec 10 17:19:38 CST 2017
 **/
public class RiskInfo {

	
	/** 风险值**/
	private String riskCode;
	
	/** 风险详情**/
	private String riskCodeValue;
	
	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskCodeValue() {
		return riskCodeValue;
	}

	public void setRiskCodeValue(String riskCodeValue) {
		this.riskCodeValue = riskCodeValue;
	}

	@Override
	public String toString() {
		return "RiskInfo [riskCode=" + riskCode + ", riskCodeValue=" + riskCodeValue + "]";
	}

	public RiskInfo(String riskCode, String riskCodeValue) {
		super();
		this.riskCode = riskCode;
		this.riskCodeValue = riskCodeValue;
	}

	public RiskInfo() {
		super();
	}
	
	


}
