/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.tdf.jackson.common;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2017-12-13 21:57:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Policy_set implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty("policy_uuid")
	private String policyUuid;
	@JsonProperty("policy_decision")
    private String policyDecision;
	@JsonProperty("policy_mode")
    private String policyMode;
	@JsonProperty("policy_score")
    private String policyScore ;
	@JsonProperty("policy_name")
    private String policyName;
	@JsonProperty("risk_type")
    private String riskType;
    private List<Hit_rules> hit_rules;
	
	public String getPolicyUuid() {
		return policyUuid;
	}

	public void setPolicyUuid(String policyUuid) {
		this.policyUuid = policyUuid;
	}

	public String getPolicyDecision() {
		return policyDecision;
	}

	public void setPolicyDecision(String policyDecision) {
		this.policyDecision = policyDecision;
	}

	public String getPolicyMode() {
		return policyMode;
	}

	public void setPolicyMode(String policyMode) {
		this.policyMode = policyMode;
	}

	public String getPolicyScore() {
		return policyScore;
	}

	public void setPolicyScore(String policyScore) {
		this.policyScore = policyScore;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public List<Hit_rules> getHit_rules() {
		return hit_rules;
	}

	public void setHit_rules(List<Hit_rules> hit_rules) {
		this.hit_rules = hit_rules;
	}
	
}