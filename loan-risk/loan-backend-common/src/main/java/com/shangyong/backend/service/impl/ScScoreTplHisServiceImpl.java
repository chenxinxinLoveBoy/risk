package com.shangyong.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreTplHisDao;
import com.shangyong.backend.entity.ScScoreTplHis;
import com.shangyong.backend.service.BaseService;

/**
 * 
  *
 */
@Service
public class ScScoreTplHisServiceImpl implements BaseService<ScScoreTplHis> {

	Logger logger = LoggerFactory.getLogger(ScScoreTplHisServiceImpl.class);

	@Autowired
	private ScScoreTplHisDao scScoreTplHisDao;

	public List<ScScoreTplHis> findAll(ScScoreTplHis scScoreTplHis) {
		return scScoreTplHisDao.findAll(scScoreTplHis);
	}

	public int listAllCount(ScScoreTplHis scScoreTplHis) {
		int count = scScoreTplHisDao.listAllCount(scScoreTplHis);
		return count;
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	@Override
	public ScScoreTplHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreTplHis getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScScoreTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveEntity(ScScoreTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(ScScoreTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScScoreTplHis getEntityById(ScScoreTplHis t) {
		// TODO Auto-generated method stub
		return null;
	}

}
