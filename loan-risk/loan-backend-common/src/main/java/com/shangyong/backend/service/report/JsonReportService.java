package com.shangyong.backend.service.report;

import com.shangyong.mongo.entity.BaseEntity;

/**
 * 第三方数据源保存service方法
 */
public interface JsonReportService {

    /**
     * 上传文件至阿里云服务器 同时保存到MongoDB
     * 更新相对路径至第三方报表中 bu_thirdparty_report
     * @param taskTypeDirParam 上传文件的路径配置
     * @param jsonObjectResponse JSONObject 对象
     * @param reportTaskType 报表类型
     * @param applicationId 申请单号
     * @param openId 任务编号
     */
	public void uploadJson(String taskTypeDirParam, Object obj, String reportTaskType, String orgName, String isEnd, String applicationId, String openId);

	 /**
     * 上传文件至阿里云服务器 
     * 更新相对路径至第三方报表中 bu_thirdparty_report
     * @param taskTypeDirParam 上传文件的路径配置
     * @param jsonObjectResponse JSONObject 对象
     * @param reportTaskType 报表类型
     * @param applicationId 申请单号
     * @param openId 任务编号
     */
    public void uploadJson(String reportTaskType, String applicationId, String openId, String jsonStr, String taskTypeDirParam);
	
	/**
	 * 保存用户短信、通话记录、通讯录、应用列表到MongoDB中
	 * @param baseEntity
	 */
	public void saveAppInfoForMongo(BaseEntity baseEntity);
    
    /**
     * 根据url地址获取阿里云服务器json数据
     * @param josnStoragePath
     * @return
     */
    String downloadossJson(String josnStoragePath);
   
}