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
public class Carrier_consumption_stats_per_month {
	@JsonProperty("consume_amount")
    private String consumeAmount;
	@JsonProperty("recharge_amount")
    private String rechargeAmount;
    private String month;
	@JsonProperty("recharge_count")
    private String rechargeCount;
	public String getConsumeAmount() {
		return consumeAmount;
	}
	public void setConsumeAmount(String consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
	public String getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(String rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	
  
}