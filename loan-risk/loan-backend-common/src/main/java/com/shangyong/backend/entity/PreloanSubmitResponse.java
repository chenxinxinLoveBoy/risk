package com.shangyong.backend.entity;

import java.io.Serializable;
/**
 * 
 * @author ChenGeng
 *
 */
public class PreloanSubmitResponse implements Serializable {

    private static final long serialVersionUID = 4152462611121573434L;
    private Boolean           success          = false;
    private String            report_id;
    private String            reason_desc;
    

    public String getReason_desc() {
		return reason_desc;
	}



	public void setReason_desc(String reason_desc) {
		this.reason_desc = reason_desc;
	}



	public Boolean getSuccess() {
		return success;
	}



	public void setSuccess(Boolean success) {
		this.success = success;
	}



	public String getReport_id() {
		return report_id;
	}



	public void setReport_id(String report_id) {
		this.report_id = report_id;
	}



	@Override
    public String toString() {
        return "RiskpreloanResponse [success=" + success + ", report_id=" + report_id + "]";
    }

}
  