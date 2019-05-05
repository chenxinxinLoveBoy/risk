package com.shangyong.backend.service.approval.service;

import java.util.Map;

/**
 *用户信息查询
 * @author Smk
 *
 */
public interface CustomerAppService {
	/**
	 * 根据客户编号查询用户信息
	 * @param customerId
	 * @return
	 */
	public Map<String,String> queryCustomerById(String customerId);

}
