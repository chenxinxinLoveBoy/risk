package com.shangyong.backend.entity.td.vo;
/**
 * 同盾命中统计
 * @author Smk
 *
 */
public class TdRiskItemCount {
	/**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime; 
    
    /**
     * 规则描述
     */
    private String riskName;
    
    /**
     * 规则编号
     */
    private String ruleId;
    
    /**
     * 命中次数
     */
    private String number;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getruleId() {
		return ruleId;
	}

	public void setruleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "TdRiskItemCount [startTime=" + startTime + ", endTime=" + endTime + ", riskName=" + riskName
				+ ", ruleId=" + ruleId + ", number=" + number + "]";
	}
    
    
    
}
