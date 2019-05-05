package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.CuIcePersonDao;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class CuIcePersonServiceImpl {

	@Autowired
	private CuIcePersonDao cuIcePersonDao;

	public List<CuIcePerson> getCuIcePersonList(String platformCustomerId) {
		return cuIcePersonDao.getEntityById(platformCustomerId);
	}

	@Transactional
	public boolean saveEntity(CuIcePerson cuIcePerson) {
		cuIcePerson.setIcePersonId(UUIDUtils.getUUID());
		cuIcePerson.setModifyTime(DateUtils.getDate(new Date()));
 		return cuIcePersonDao.saveEntity(cuIcePerson);
	}

	@Transactional
	public boolean updateEntity(CuIcePerson cuIcePerson) {
		cuIcePerson.setModifyTime(DateUtils.getDate(new Date()));
		return cuIcePersonDao.updateEntity(cuIcePerson);
	}

}
