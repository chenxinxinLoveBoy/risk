package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.CustomerEquipmentIosDao;
import com.shangyong.backend.entity.approval.CustomerEquipmentIos;
import com.shangyong.backend.service.CuCustomerEquipmentIosService;


@Service
public class CuCustomerEquipmentIosServiceImpl implements CuCustomerEquipmentIosService {

	@Autowired
	CustomerEquipmentIosDao cuCustomerEquipmentIosDao;
	
	@Override
	public List<CustomerEquipmentIos> findListById(String customerId) {
		
		return cuCustomerEquipmentIosDao.findListById(customerId);
	}

	@Override
	public int listAllCount(String customerId) {
		
		return cuCustomerEquipmentIosDao.listAllCount(customerId);
	}

	@Override
	public CustomerEquipmentIos findEntityById(String equipmentId) {
		
		return cuCustomerEquipmentIosDao.findEntityById(equipmentId);
	}

	
}
