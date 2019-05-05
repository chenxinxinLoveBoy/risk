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
public class Contact_creditscore_analysis {
	@JsonProperty("creditscore_top10_contact_count")
    private String creditscoreTop10ContactCount;
	@JsonProperty("creditscore_top10_contact_median")
    private String creditscoreTop10ContactMedian;
	@JsonProperty("creditscore_top10_contact_avg")
    private String creditscoreTop10ContactAvg;
	@JsonProperty("creditscore_top10_contact_min")
    private String creditscoreTop10ContactMin;
	@JsonProperty("creditscore_top10_contact_max")
    private String creditscoreTop10ContactMax;
	public String getCreditscoreTop10ContactCount() {
		return creditscoreTop10ContactCount;
	}
	public void setCreditscoreTop10ContactCount(String creditscoreTop10ContactCount) {
		this.creditscoreTop10ContactCount = creditscoreTop10ContactCount;
	}
	public String getCreditscoreTop10ContactMedian() {
		return creditscoreTop10ContactMedian;
	}
	public void setCreditscoreTop10ContactMedian(String creditscoreTop10ContactMedian) {
		this.creditscoreTop10ContactMedian = creditscoreTop10ContactMedian;
	}
	public String getCreditscoreTop10ContactAvg() {
		return creditscoreTop10ContactAvg;
	}
	public void setCreditscoreTop10ContactAvg(String creditscoreTop10ContactAvg) {
		this.creditscoreTop10ContactAvg = creditscoreTop10ContactAvg;
	}
	public String getCreditscoreTop10ContactMin() {
		return creditscoreTop10ContactMin;
	}
	public void setCreditscoreTop10ContactMin(String creditscoreTop10ContactMin) {
		this.creditscoreTop10ContactMin = creditscoreTop10ContactMin;
	}
	public String getCreditscoreTop10ContactMax() {
		return creditscoreTop10ContactMax;
	}
	public void setCreditscoreTop10ContactMax(String creditscoreTop10ContactMax) {
		this.creditscoreTop10ContactMax = creditscoreTop10ContactMax;
	}
   
}