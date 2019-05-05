package com.shangyong.backend.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 
 * @author ChenGeng
 *
 */
public class PreloanQueryResponse implements Serializable {

    private static final long serialVersionUID = 4152462211121573434L;

    private Boolean           success          = false;
    private JSONArray         risk_items;		//扫描出来的风险项
    private JSONObject        address_detect;	//归属地解析
    private String            application_id;	//申请编号
    private String            final_decision;	//风险结果
    private String            report_id;		//报告编号
    private Long              apply_time;		//扫描时间
    private Long              report_time;		//报告时间
    private Integer           final_score;		//风险分数
    
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public JSONArray getRisk_items() {
		return risk_items;
	}
	public void setRisk_items(JSONArray risk_items) {
		this.risk_items = risk_items;
	}
	public JSONObject getAddress_detect() {
		return address_detect;
	}
	public void setAddress_detect(JSONObject address_detect) {
		this.address_detect = address_detect;
	}
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getFinal_decision() {
		return final_decision;
	}
	public void setFinal_decision(String final_decision) {
		this.final_decision = final_decision;
	}
	public String getReport_id() {
		return report_id;
	}
	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}
	public Long getApply_time() {
		return apply_time;
	}
	public void setApply_time(Long apply_time) {
		this.apply_time = apply_time;
	}
	public Long getReport_time() {
		return report_time;
	}
	public void setReport_time(Long report_time) {
		this.report_time = report_time;
	}
	public Integer getFinal_score() {
		return final_score;
	}
	public void setFinal_score(Integer final_score) {
		this.final_score = final_score;
	}

}