package com.shangyong.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.CuPlatformCustomerDao;
import com.shangyong.backend.entity.CuPlatformCustomerWeb;
import com.shangyong.backend.service.CuPlatformCustomerService;
@Service
public class CuPlatformCustomerServiceImpl implements CuPlatformCustomerService {
	
	@Autowired
	private CuPlatformCustomerDao cuPlatformCustomerDao;

	@Override
	public CuPlatformCustomerWeb findObjByCustomerId(CuPlatformCustomerWeb cuPlatformCustomer) {
		
		return cuPlatformCustomerDao.findObjByCustomerId(cuPlatformCustomer);
		
	}
	
}
