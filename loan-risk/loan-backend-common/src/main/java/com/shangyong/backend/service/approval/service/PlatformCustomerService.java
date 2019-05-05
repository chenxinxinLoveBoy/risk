package com.shangyong.backend.service.approval.service;

import com.shangyong.backend.entity.CuPlatformCustomer;

/**
 * 平台客户信息表
 * @author hxf
 * @date 2017/8/10
 **/
public interface PlatformCustomerService {

	/**通过平台用户编号查询基本信息**/
	public CuPlatformCustomer findPlatformCustomerById(String platformId);
}
