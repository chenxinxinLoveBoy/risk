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
public class Report_info {
	@JsonProperty("report_id")
    private String reportId;
	@JsonProperty("report_update_time")
    private String reportUpdateTime;
	@JsonProperty("task_id")
    private String taskId;
	@JsonProperty("report_version")
    private String reportVersion;
	@JsonProperty("task_time")
    private String taskTime;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportUpdateTime() {
		return reportUpdateTime;
	}
	public void setReportUpdateTime(String reportUpdateTime) {
		this.reportUpdateTime = reportUpdateTime;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getReportVersion() {
		return reportVersion;
	}
	public void setReportVersion(String reportVersion) {
		this.reportVersion = reportVersion;
	}
	public String getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}
    
}