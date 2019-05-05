package com.shangyong.backend.service.approval.service;

import java.util.List;

import com.shangyong.backend.entity.approval.TdPlatformCheck;

public interface TdPlatformCheckService {
	
	/**
	 *  查询同盾多平台借贷信息
	 * @param appId
	 * @return
	 */
	public List<TdPlatformCheck> getListById(String applicationId); 
}
