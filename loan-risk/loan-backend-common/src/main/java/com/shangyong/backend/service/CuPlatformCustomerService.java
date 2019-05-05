package com.shangyong.backend.service;

import com.shangyong.backend.entity.CuPlatformCustomerWeb;

public interface CuPlatformCustomerService {

	/**
	 * 通过客户编号查询平台用户编号
	 * @param customerId
	 * @return
	 */
	CuPlatformCustomerWeb findObjByCustomerId(CuPlatformCustomerWeb cuPlatformCustomer);

}
