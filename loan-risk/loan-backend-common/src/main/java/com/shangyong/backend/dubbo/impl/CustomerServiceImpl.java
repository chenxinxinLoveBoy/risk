package com.shangyong.backend.dubbo.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shangyong.backend.dubbo.CustomerDubboService;
import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.entity.CuCustomerOther;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.FaceRecognitionScore;

@Service
public class CustomerServiceImpl {
	
	@Reference(version ="1.0.0",retries = -1,timeout = 5000)
	private CustomerDubboService customerDubboService;

	/**
	 * 保存客户信息
	 * @param cuCustomerOtherList
	 * @param cuPlatformCustomer
	 * @param cuCustomerCompany
	 * @param faceRecognitionScore
	 * @return
	 */
	public CuPlatformCustomer save(List<CuCustomerOther> cuCustomerOtherList,
								   CuPlatformCustomer cuPlatformCustomer,
								   CuCustomerCompany cuCustomerCompany,
								   FaceRecognitionScore faceRecognitionScore) {

		return customerDubboService.save(cuCustomerOtherList, cuPlatformCustomer, cuCustomerCompany,faceRecognitionScore);
	}

	/**
	 * 保存紧急联系人
	 * @param applicationId
	 * @param customerId
	 * @param platformCustomerId
	 * @param cuIcePersonList
	 */
	public void saveCuIcePersonList(String applicationId,
									String customerId,
									String platformCustomerId,
									List<CuIcePerson> cuIcePersonList) {
		customerDubboService.saveCuIcePersonList(applicationId, customerId, platformCustomerId,cuIcePersonList);
	}
}
