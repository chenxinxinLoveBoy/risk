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
public class Data_completeness_per_month {
	@JsonProperty("is_msg_data_complete")
    private String isMsgDataComplete;
	@JsonProperty("is_consume_data_complete")
    private String isConsumeDataComplete;
    private String month;
	@JsonProperty("is_call_data_complete")
    private String isCallDataComplete;
	public String getIsMsgDataComplete() {
		return isMsgDataComplete;
	}
	public void setIsMsgDataComplete(String isMsgDataComplete) {
		this.isMsgDataComplete = isMsgDataComplete;
	}
	public String getIsConsumeDataComplete() {
		return isConsumeDataComplete;
	}
	public void setIsConsumeDataComplete(String isConsumeDataComplete) {
		this.isConsumeDataComplete = isConsumeDataComplete;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getIsCallDataComplete() {
		return isCallDataComplete;
	}
	public void setIsCallDataComplete(String isCallDataComplete) {
		this.isCallDataComplete = isCallDataComplete;
	}
    

}