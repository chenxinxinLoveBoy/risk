package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.CuPlatformCustomerDao;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class CuPlatformCustomerServiceImpl{

	@Autowired
	private CuPlatformCustomerDao cuPlatformCustomerDao;

	public int listAllCount(CuPlatformCustomer cuPlatformCustomer) {
		return cuPlatformCustomerDao.listAllCount(cuPlatformCustomer);
	}

	public List<CuPlatformCustomer> findAllCustomer() {
		return cuPlatformCustomerDao.findAllCustomer();
	}

	@Transactional
	public boolean saveEntity(CuPlatformCustomer cuPlatformCustomer) {
		cuPlatformCustomer.setPlatformCustomerId(UUIDUtils.getUUID());
		String time=DateUtils.getDate(new Date());
		cuPlatformCustomer.setCreateTime(time);
		cuPlatformCustomer.setModifyTime(time);
		return cuPlatformCustomerDao.saveEntity(cuPlatformCustomer);
	}

	@Transactional
	public boolean updateEntity(CuPlatformCustomer cuPlatformCustomer) {
		cuPlatformCustomer.setModifyTime(DateUtils.getDate(new Date()));
		return cuPlatformCustomerDao.updateEntity(cuPlatformCustomer);
	}

	public List<CuPlatformCustomer> findAll(CuPlatformCustomer cuPlatformCustomer) {
		return cuPlatformCustomerDao.findAll(cuPlatformCustomer);
	}

	public CuPlatformCustomer getEntityById(CuPlatformCustomer cuPlatformCustomer) {
		return cuPlatformCustomerDao.getEntityById(cuPlatformCustomer);
	}

}
