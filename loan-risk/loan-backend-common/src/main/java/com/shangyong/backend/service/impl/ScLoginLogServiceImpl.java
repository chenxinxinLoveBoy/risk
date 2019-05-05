package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScLoginLogDao;
import com.shangyong.backend.entity.ScLoginLog;
import com.shangyong.backend.service.BaseService;

@Service
public class ScLoginLogServiceImpl implements BaseService<ScLoginLog> {
	@Autowired
	private ScLoginLogDao scLoginLogDao;

	public int listAllCount(ScLoginLog scLoginLog) {
		return scLoginLogDao.listAllCount(scLoginLog);
	}

	public List<ScLoginLog> getAll(ScLoginLog scLoginLog) {
		return scLoginLogDao.getAll(scLoginLog);

	}

	@Override
	public List<ScLoginLog> findAll(ScLoginLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScLoginLog getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScLoginLog getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScLoginLog getEntityById(ScLoginLog t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScLoginLog t) {
		return scLoginLogDao.saveEntity(t);
	}

	@Override
	public boolean updateEntity(ScLoginLog t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScLoginLog t) {
		// TODO Auto-generated method stub
		return false;
	}
}
