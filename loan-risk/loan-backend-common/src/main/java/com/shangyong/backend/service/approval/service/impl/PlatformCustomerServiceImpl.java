package com.shangyong.backend.service.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.PlatformCustomerDao;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.service.approval.service.PlatformCustomerService;

@Service
public class PlatformCustomerServiceImpl implements PlatformCustomerService {

	@Autowired
	private PlatformCustomerDao platformCustomerDao;
	
	@Override
	public CuPlatformCustomer findPlatformCustomerById(String platformId) {
		return platformCustomerDao.getEntityById(platformId);
	}
}
