package com.shangyong.backend.dubbo;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;

public interface GetCustomerDubboService {
	/**
	 * 通过客户平台编号获取平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录
	 * 
	 */
	public CustomerInfo getCustomer(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 通过平台的客户编号和平台类型获取平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录
	 *
	 */
	public CustomerInfo getCustomerByCustomerIdAndAppName(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 获取平台客户信息列表
	 * 
	 * @return
	 */
	public Map<String, Object> getCustomerList(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 获取平台客户信息
	 * 
	 */
	public CuPlatformCustomer getCustomerInfo(CuPlatformCustomer cuPlatformCustomer);

	/**
	 * 通过客户平台编号获取客户紧急联系人信息
	 * 
	 */
	public List<CuIcePerson> getCustomerCuIcePersonList(String platformCustomerId);
	
	/**
	 * 通过客户编号和平台类型获取平台客户信息、客户公司信息
	 * 
	 */
	public CustomerInfo getCustomerInfoAndCompanyByCustomerIdAndAppName(CuPlatformCustomer cuPlatformCustomer);
}
