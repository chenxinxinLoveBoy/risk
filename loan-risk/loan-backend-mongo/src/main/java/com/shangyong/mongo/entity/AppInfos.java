package com.shangyong.mongo.entity;

import org.springframework.data.annotation.Id;

/**
 * 应用列表
 * @author es_ai
 *
 */
public class AppInfos extends BaseEntity {
	
    private static final long serialVersionUID = -2742148489606189447L;

    @Id
    private String id;
    
    private String customerId;
    
    private String appName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
}
