package com.shangyong.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScLiftConfigurationHisDao;
import com.shangyong.backend.entity.ScLiftConfigurationHis;
import com.shangyong.backend.service.BaseService;

@Service
public class ScLiftConfigurationHisServiceImpl implements BaseService<ScLiftConfigurationHis> {

	Logger logger = LoggerFactory.getLogger(ScLiftConfigurationHisServiceImpl.class);

	@Autowired
	private ScLiftConfigurationHisDao scLiftConfigurationHisDao;

	public List<ScLiftConfigurationHis> findAll(ScLiftConfigurationHis scLiftConfigurationHis) {
		return scLiftConfigurationHisDao.findAll(scLiftConfigurationHis);
	}

	public int listAllCount(ScLiftConfigurationHis scLiftConfigurationHis) {
		int count = scLiftConfigurationHisDao.listAllCount(scLiftConfigurationHis);
		return count;
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	@Override
	public ScLiftConfigurationHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScLiftConfigurationHis getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScLiftConfigurationHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveEntity(ScLiftConfigurationHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(ScLiftConfigurationHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScLiftConfigurationHis getEntityById(ScLiftConfigurationHis t) {
		// TODO Auto-generated method stub
		return null;
	}

}
