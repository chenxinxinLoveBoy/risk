package com.shangyong.backend.service.checkapply.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.CuCustomerCheckApplyDao;
import com.shangyong.backend.entity.CuCustomerCheckApply;
import com.shangyong.backend.service.checkapply.CuCustomerCheckApplyService;


@Service
public class CuCustomerCheckApplyImpl implements CuCustomerCheckApplyService{
	
	@Autowired
	private CuCustomerCheckApplyDao cuCustomerCheckApplyDao;

	@Override
	public int getEntityById(Map<String, String> map) {
		return cuCustomerCheckApplyDao.getEntityById(map);
	}

	@Override
	@Transactional
	public boolean updateEntity(Map<String, String> map) {
		return cuCustomerCheckApplyDao.updateEntity(map);
	}

	
}
