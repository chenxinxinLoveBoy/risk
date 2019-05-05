package com.shangyong.backend.entity;

import net.sf.json.JSONObject;

/**
 * 第三方征信接口数据信息
 */
public class CreditDataInfo {

    /**
     * 文件上传目录
     */
    private String taskTypeDirParam;
    /**
     * 文件上传的报告
     */
    private JSONObject obj;
    /**
     * 任务编号
     */
    private String reportTaskType;
    /**
     * 申请单号
     */
    private String applicationId;
    private String openId;
    
    /**机构名称**/
    private String orgName;
    
    /**是否最终步骤 1:最  0：否**/
    private String isEnd;
    
    
	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTaskTypeDirParam() {
        return taskTypeDirParam;
    }

    public void setTaskTypeDirParam(String taskTypeDirParam) {
        this.taskTypeDirParam = taskTypeDirParam;
    }

    public JSONObject getObj() {
        return obj;
    }

    public void setObj(JSONObject obj) {
        this.obj = obj;
    }

    public String getReportTaskType() {
        return reportTaskType;
    }

    public void setReportTaskType(String reportTaskType) {
        this.reportTaskType = reportTaskType;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public CreditDataInfo() {
    }

	@Override
	public String toString() {
		return "CreditDataInfo [taskTypeDirParam=" + taskTypeDirParam + ", obj=" + obj + ", reportTaskType="
				+ reportTaskType + ", applicationId=" + applicationId + ", openId=" + openId + ", orgName=" + orgName
				+ ", isEnd=" + isEnd + "]";
	}

}
