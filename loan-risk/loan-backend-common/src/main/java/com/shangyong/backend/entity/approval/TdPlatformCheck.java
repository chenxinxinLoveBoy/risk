package com.shangyong.backend.entity.approval;

public class TdPlatformCheck {
	private String seqRiskId;
	
	private String riskLevel;
	
	private String platformCount;
	
	private String descrition;
	
	private String itemName;
	
	private String backup1;
	
	private String reportId;
	
	private String fraudType;
	
	public String getFraudType() {
		return fraudType;
	}
	public void setFraudType(String fraudType) {
		this.fraudType = fraudType;
	}
	public String getBackup1() {
		return backup1;
	}
	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSeqRiskId() {
		return seqRiskId;
	}
	public void setSeqRiskId(String seqRiskId) {
		this.seqRiskId = seqRiskId;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getPlatformCount() {
		return platformCount;
	}
	public void setPlatformCount(String platformCount) {
		this.platformCount = platformCount;
	}
	public String getDescrition() {
		return descrition;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
}
