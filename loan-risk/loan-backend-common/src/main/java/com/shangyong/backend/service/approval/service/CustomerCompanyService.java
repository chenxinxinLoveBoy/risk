package com.shangyong.backend.service.approval.service;

import com.shangyong.backend.entity.CuCustomerCompany;

/**
 * 平台客户所属公司信息
 * @author hxf
 * @date 2017/8/10
 **/
public interface CustomerCompanyService {

	/**通过用户编号查询客户所属公司信息**/
	public CuCustomerCompany findCuCustomerCompanyByPlatformCustomerId(String platformCustomerId);
}
