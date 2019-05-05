package com.shangyong.backend.service.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.CustomerCompanyDao;
import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.service.approval.service.CustomerCompanyService;

@Service
public class CustomerCompanyServiceImpl implements CustomerCompanyService {

	@Autowired
	private CustomerCompanyDao customerCompanyDao;
	
	@Override
	public CuCustomerCompany findCuCustomerCompanyByPlatformCustomerId(String platformCustomerId) {
		return customerCompanyDao.getEntityById(platformCustomerId);
	}

}
