package com.shangyong.backend.service.approval.service;

import java.util.List;

import com.shangyong.backend.entity.approval.AppInfo;

/**
 * app应用列表
 * @author hxf
 * @date 2017/8/15
 **/
public interface AppInfoService {

	/**通过客户号查询app应用列表记录**/
	public List<AppInfo> findAppInfoBycustomerId(String customerId, String appName);
}
