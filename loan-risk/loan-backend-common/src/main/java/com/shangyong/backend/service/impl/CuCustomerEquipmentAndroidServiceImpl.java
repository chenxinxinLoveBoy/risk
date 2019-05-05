package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.CustomerEquipmentAndroidDao;
import com.shangyong.backend.entity.approval.CustomerEquipmentAndroid;
import com.shangyong.backend.service.CuCustomerEquipmentAndroidService;


@Service
public class CuCustomerEquipmentAndroidServiceImpl implements CuCustomerEquipmentAndroidService {

	@Autowired
	CustomerEquipmentAndroidDao customerEquipmentAndroidDao;

	@Override
	public List<CustomerEquipmentAndroid> findListById(String customerId) {
		// TODO Auto-generated method stub
		return customerEquipmentAndroidDao.findListById(customerId);
	}

	@Override
	public int listAllCount(String customerId) {
		// TODO Auto-generated method stub
		return customerEquipmentAndroidDao.listAllCount(customerId);
	}

	@Override
	public CustomerEquipmentAndroid findEntityById(String equipmentId) {
		
		return customerEquipmentAndroidDao.findEntityById(equipmentId);
	}
	

	
}
