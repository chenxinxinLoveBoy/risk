package com.shangyong.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shangyong.backend.dao.CuCustomerCheckManageDao;
import com.shangyong.backend.entity.CuCustomerCheckManage;
import com.shangyong.backend.service.CuCustomerCheckManageService;


@Service
public class CuCustomerCheckManageServiceImpl implements CuCustomerCheckManageService {

	@Autowired
	private CuCustomerCheckManageDao CuCustomerCheckManageDao;
	
	@Override
	public int saveEntity(CuCustomerCheckManage record) {
		
		return CuCustomerCheckManageDao.saveEntity(record);
	}

	@Override
	public Page<CuCustomerCheckManage> findAllByObj(CuCustomerCheckManage record) {
		int pageNo = record.getPageIndex();
		int pageSize = record.getPageSize();
		PageHelper.startPage(pageNo, pageSize);
		return CuCustomerCheckManageDao.findAllByObj(record);
		
	}

	@Override
	public int listCountAllByObj(CuCustomerCheckManage record) {
		
		return CuCustomerCheckManageDao.listCountAllByObj(record);
		
	}

}
