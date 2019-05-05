package com.shangyong.mongo.entity;

import org.springframework.data.annotation.Id;

public class DataReport  extends BaseEntity {
    private static final long serialVersionUID = -2742148489606189447L;

    @Id
    private String id;
    
    /** 申请单id **/
    private String applicationId;
    
    /** 任务编号 **/
    private String taskType;
    
    /** 征信源名称 **/
    private String orgName;
    
    /** 是否最后一个步骤 **/
    private String isEnd;
    
    public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

}
