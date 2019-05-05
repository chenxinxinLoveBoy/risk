package com.shangyong.backend.dubbo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shangyong.backend.dubbo.GetCustomerDubboService;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;

@Service
public class GetCustomerInfoService {

	@Reference(version = "1.0.0", retries = -1, timeout = 5000) // 调取dubbo接口
	private GetCustomerDubboService getCustomerDubboService;

	public Map<String, Object> getAllCustomerInfo(CuPlatformCustomer cuPlatformCustomer) {
		return getCustomerDubboService.getCustomerList(cuPlatformCustomer);
	}

	public CustomerInfo getEntityById(CuPlatformCustomer cuPlatformCustomer) {
		if(cuPlatformCustomer != null){
			if(StringUtils.isNotEmpty(cuPlatformCustomer.getPlatformCustomerId())){
				return getCustomerDubboService.getCustomer(cuPlatformCustomer);
			}else if(StringUtils.isNotEmpty(cuPlatformCustomer.getCustomerId()) && StringUtils.isNotEmpty(cuPlatformCustomer.getAppName())){
				return getCustomerDubboService.getCustomerByCustomerIdAndAppName(cuPlatformCustomer);
			}else{
				return null;
			}
		}
		return null;
	}

	public CuPlatformCustomer getCustomerInfo(CuPlatformCustomer cuPlatformCustomer) {
		return getCustomerDubboService.getCustomerInfo(cuPlatformCustomer);
	}

	public List<CuIcePerson> getCustomerCuIcePersonList(String platformCustomerId) {
		return getCustomerDubboService.getCustomerCuIcePersonList(platformCustomerId);
	};
	
	public CustomerInfo getCustomerInfoAndCompanyByCustomerIdAndAppName(CuPlatformCustomer cuPlatformCustomer) {
		return getCustomerDubboService.getCustomerInfoAndCompanyByCustomerIdAndAppName(cuPlatformCustomer);
	}
}
