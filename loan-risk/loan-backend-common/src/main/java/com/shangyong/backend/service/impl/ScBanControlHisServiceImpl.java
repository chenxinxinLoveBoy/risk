package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.ScBanControlHisDao;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.service.BaseService;

@Service
public class ScBanControlHisServiceImpl implements BaseService<ScBanControl> {

	@Autowired
	private ScBanControlHisDao scBanControlHisDao;
	
	public List<ScBanControl> findAllScBanControlHisInfo(String startTimeInterval,String endTimeInterval) {
		return scBanControlHisDao.findAllScBanControlHis(startTimeInterval,endTimeInterval);
	}
	
	@Override
	public List<ScBanControl> findAll(ScBanControl scBanControl) {
		return scBanControlHisDao.getAll(scBanControl);
	}

	@Override
	public ScBanControl getEntityById(String banControlId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScBanControl getEntityById(Integer controlHisId) {
		return scBanControlHisDao.selectByPrimaryKey(controlHisId);
	}

	@Override
	public ScBanControl getEntityById(ScBanControl scBanControl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScBanControl scBanControl) {

		return false;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScBanControl scBanControl) {
		return false;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScBanControl scBanControl) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScBanControl scBanControl) {
		return scBanControlHisDao.listAllCount(scBanControl);
	}

}
