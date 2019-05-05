package com.shangyong.backend.entity;

public class ValidTemplateData {

	private String templateId;
	
	private double percent;
	
	private Integer templateSize;
	
	private Integer recordSize;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public Integer getTemplateSize() {
		return templateSize;
	}

	public void setTemplateSize(Integer templateSize) {
		this.templateSize = templateSize;
	}

	public Integer getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(Integer recordSize) {
		this.recordSize = recordSize;
	}
	
}
