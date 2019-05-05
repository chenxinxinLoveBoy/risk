package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.CuCustomerOtherDao;
import com.shangyong.backend.entity.CuCustomerOther;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class CuCustomerOtherServiceImpl {

	@Autowired
	private CuCustomerOtherDao cuCustomerOtherDao;

	public CuCustomerOther getEntityById(String otherInfoId) {
		return cuCustomerOtherDao.getEntityById(otherInfoId);
	}

	@Transactional
	public boolean saveEntity(CuCustomerOther cuCustomerOther) {
		cuCustomerOther.setOtherInfoId(UUIDUtils.getUUID());
		cuCustomerOther.setModifyTime(DateUtils.getDate(new Date()));
 		return cuCustomerOtherDao.saveEntity(cuCustomerOther);
	}

	@Transactional
	public boolean updateEntity(CuCustomerOther cuCustomerOther) {
		cuCustomerOther.setModifyTime(DateUtils.getDate(new Date()));
		return cuCustomerOtherDao.updateEntity(cuCustomerOther);
	}

}
