/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Behavior_score {
	@JsonProperty("risk_contact_info_score")
    private String riskContactInfoScore;
	@JsonProperty("base_info_score")
    private String baseInfoScore;
	@JsonProperty("bill_info_score")
    private String billInfoScore;
	@JsonProperty("total_score")
    private String totalScore;
	@JsonProperty("call_info_score")
    private String callInfoScore;
	public String getRiskContactInfoScore() {
		return riskContactInfoScore;
	}
	public void setRiskContactInfoScore(String riskContactInfoScore) {
		this.riskContactInfoScore = riskContactInfoScore;
	}
	public String getBaseInfoScore() {
		return baseInfoScore;
	}
	public void setBaseInfoScore(String baseInfoScore) {
		this.baseInfoScore = baseInfoScore;
	}
	public String getBillInfoScore() {
		return billInfoScore;
	}
	public void setBillInfoScore(String billInfoScore) {
		this.billInfoScore = billInfoScore;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getCallInfoScore() {
		return callInfoScore;
	}
	public void setCallInfoScore(String callInfoScore) {
		this.callInfoScore = callInfoScore;
	}
   
}