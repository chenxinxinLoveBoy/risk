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
public class Travel_track_analysis_per_city {
	@JsonProperty("arrive_city")
    private String arriveCity;
	@JsonProperty("arrive_day_type")
    private String arriveDayType;
	@JsonProperty("leave_city")
    private String leaveCity;
	@JsonProperty("leave_day_type")
    private String leaveDayType;
	@JsonProperty("arrive_day")
    private String arriveDay;
	@JsonProperty("leave_day")
    private String leaveDay;
	public String getArriveCity() {
		return arriveCity;
	}
	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}
	public String getArriveDayType() {
		return arriveDayType;
	}
	public void setArriveDayType(String arriveDayType) {
		this.arriveDayType = arriveDayType;
	}
	public String getLeaveCity() {
		return leaveCity;
	}
	public void setLeaveCity(String leaveCity) {
		this.leaveCity = leaveCity;
	}
	public String getLeaveDayType() {
		return leaveDayType;
	}
	public void setLeaveDayType(String leaveDayType) {
		this.leaveDayType = leaveDayType;
	}
	public String getArriveDay() {
		return arriveDay;
	}
	public void setArriveDay(String arriveDay) {
		this.arriveDay = arriveDay;
	}
	public String getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(String leaveDay) {
		this.leaveDay = leaveDay;
	}
  
}