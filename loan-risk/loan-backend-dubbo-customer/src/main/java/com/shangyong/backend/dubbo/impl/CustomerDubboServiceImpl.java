package com.shangyong.backend.dubbo.impl;

import java.util.List;

import com.shangyong.backend.service.GetCustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.dubbo.CustomerDubboService;
import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.entity.CuCustomerOther;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.FaceRecognitionScore;

/**
 * 平台客户信息落库
 * 
 * @author xk
 *
 */
@Service(version = "1.0.0")
public class CustomerDubboServiceImpl implements CustomerDubboService {

	@Autowired
	private GetCustomerService getCustomerService;

	@Override
	public CuPlatformCustomer save(List<CuCustomerOther> cuCustomerOtherList,
								   CuPlatformCustomer cuPlatformCustomer,
								   CuCustomerCompany cuCustomerCompany,
								   FaceRecognitionScore faceRecognitionScore) {
		return getCustomerService.save(cuCustomerOtherList, cuPlatformCustomer, cuCustomerCompany,faceRecognitionScore);
	}

	@Override
	public void saveCuIcePersonList(String applicationId, String customerId, String platformCustomerId, List<CuIcePerson> cuIcePersonList) {
		getCustomerService.saveCuIcePersonList(applicationId, customerId, platformCustomerId, cuIcePersonList);
	}
}
