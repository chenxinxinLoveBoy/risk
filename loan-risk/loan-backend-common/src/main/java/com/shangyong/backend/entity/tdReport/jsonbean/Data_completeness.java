/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Data_completeness {
	@JsonProperty("is_call_data_complete_3month")
    private String isCallDataComplete3month;
	@JsonProperty("is_msg_data_complete_6month")
    private String isMsgDataComplete6month;
    private List<Data_completeness_per_month> data_completeness_per_month;
	@JsonProperty("is_msg_data_complete_1month")
    private String isMsgDataComplete1month;
	@JsonProperty("is_consume_data_complete_1month")
    private String isConsumeDataComplete1month;
	@JsonProperty("is_consume_data_complete_6month")
    private String isConsumeDataComplete6month;
	@JsonProperty("is_call_data_complete_1month")
    private String isCallDataComplete1month;
	@JsonProperty("is_consume_data_complete_3month")
    private String isConsumeDataComplete3month;
	@JsonProperty("is_call_data_complete_6month")
    private String isCallDataComplete6month;
	@JsonProperty("is_msg_data_complete_3month")
    private String isMsgDataComplete3month;
	public String getIsCallDataComplete3month() {
		return isCallDataComplete3month;
	}
	public void setIsCallDataComplete3month(String isCallDataComplete3month) {
		this.isCallDataComplete3month = isCallDataComplete3month;
	}
	public String getIsMsgDataComplete6month() {
		return isMsgDataComplete6month;
	}
	public void setIsMsgDataComplete6month(String isMsgDataComplete6month) {
		this.isMsgDataComplete6month = isMsgDataComplete6month;
	}
	public List<Data_completeness_per_month> getData_completeness_per_month() {
		return data_completeness_per_month;
	}
	public void setData_completeness_per_month(List<Data_completeness_per_month> data_completeness_per_month) {
		this.data_completeness_per_month = data_completeness_per_month;
	}
	public String getIsMsgDataComplete1month() {
		return isMsgDataComplete1month;
	}
	public void setIsMsgDataComplete1month(String isMsgDataComplete1month) {
		this.isMsgDataComplete1month = isMsgDataComplete1month;
	}
	public String getIsConsumeDataComplete1month() {
		return isConsumeDataComplete1month;
	}
	public void setIsConsumeDataComplete1month(String isConsumeDataComplete1month) {
		this.isConsumeDataComplete1month = isConsumeDataComplete1month;
	}
	public String getIsConsumeDataComplete6month() {
		return isConsumeDataComplete6month;
	}
	public void setIsConsumeDataComplete6month(String isConsumeDataComplete6month) {
		this.isConsumeDataComplete6month = isConsumeDataComplete6month;
	}
	public String getIsCallDataComplete1month() {
		return isCallDataComplete1month;
	}
	public void setIsCallDataComplete1month(String isCallDataComplete1month) {
		this.isCallDataComplete1month = isCallDataComplete1month;
	}
	public String getIsConsumeDataComplete3month() {
		return isConsumeDataComplete3month;
	}
	public void setIsConsumeDataComplete3month(String isConsumeDataComplete3month) {
		this.isConsumeDataComplete3month = isConsumeDataComplete3month;
	}
	public String getIsCallDataComplete6month() {
		return isCallDataComplete6month;
	}
	public void setIsCallDataComplete6month(String isCallDataComplete6month) {
		this.isCallDataComplete6month = isCallDataComplete6month;
	}
	public String getIsMsgDataComplete3month() {
		return isMsgDataComplete3month;
	}
	public void setIsMsgDataComplete3month(String isMsgDataComplete3month) {
		this.isMsgDataComplete3month = isMsgDataComplete3month;
	}
   
}