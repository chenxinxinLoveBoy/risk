package com.shangyong.backend.dubbo;

import java.util.Map;

import com.shangyong.backend.entity.CustomerInfo;

public interface CuPlatformCustomerDubboService {
	/**
	 * 获取平台客户信息
	 * 
	 * @param map
	 * @return
	 */
	public CustomerInfo getEntity(Map<String, String> map);
}
