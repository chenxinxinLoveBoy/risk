package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.CuCustomerCompanyDao;
import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class CuCustomerCompanyServiceImpl {

	@Autowired
	private CuCustomerCompanyDao cuCustomerCompanyDao;

	public CuCustomerCompany getEntityById(String platformCustomerId) {
		return cuCustomerCompanyDao.getEntityById(platformCustomerId);
	}

	@Transactional
	public boolean saveEntity(CuCustomerCompany cuCustomerCompany) {
		cuCustomerCompany.setCustomerCompanyId(UUIDUtils.getUUID());
		cuCustomerCompany.setModifyTime(DateUtils.getDate(new Date()));
 		return cuCustomerCompanyDao.saveEntity(cuCustomerCompany);
	}

	@Transactional
	public boolean updateEntity(CuCustomerCompany cuCustomerCompany) {
		cuCustomerCompany.setModifyTime(DateUtils.getDate(new Date()));
		return cuCustomerCompanyDao.updateEntity(cuCustomerCompany);
	}

}
