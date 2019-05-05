package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.CuCustomerExpandDao;
import com.shangyong.backend.entity.CuCustomerExpand;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class CuCustomerExpandServiceImpl implements BaseService<CuCustomerExpand> {

	@Autowired
	private CuCustomerExpandDao cuCustomerExpandDao;

	@Override
	public List<CuCustomerExpand> findAll(CuCustomerExpand cuCustomerExpand) {
		return cuCustomerExpandDao.findAll(cuCustomerExpand);
	}

	@Override
	public CuCustomerExpand getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuCustomerExpand getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuCustomerExpand getEntityById(CuCustomerExpand cuCustomerExpand) {
 		return cuCustomerExpandDao.getEntityById(cuCustomerExpand);
	}

	@Override
	public boolean saveEntity(CuCustomerExpand cuCustomerExpand) {
		cuCustomerExpand.setCusInfoId(UUIDUtils.getUUID());
		cuCustomerExpand.setModifyTime(DateUtils.getDate(new Date()));
		return cuCustomerExpandDao.saveEntity(cuCustomerExpand);
	}

	@Override
	public boolean updateEntity(CuCustomerExpand cuCustomerExpand) {
		cuCustomerExpand.setModifyTime(DateUtils.getDate(new Date()));
		return cuCustomerExpandDao.updateEntity(cuCustomerExpand);
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(CuCustomerExpand t) {
		// TODO Auto-generated method stub
		return false;
	}
}
