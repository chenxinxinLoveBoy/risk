package com.shangyong.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScDecisionTreeHisDao;
import com.shangyong.backend.entity.ScDecisionTreeHis;
import com.shangyong.backend.service.BaseService;

@Service
public class ScDecisionTreeHisServiceImpl implements BaseService<ScDecisionTreeHis> {

	Logger logger = LoggerFactory.getLogger(ScDecisionTreeHisServiceImpl.class);

	@Autowired
	private ScDecisionTreeHisDao scDecisionTreeHisDao;

	public List<ScDecisionTreeHis> findAll(ScDecisionTreeHis scDecisionTreeHis) {
		return scDecisionTreeHisDao.findAll(scDecisionTreeHis);
	}

	public int listAllCount(ScDecisionTreeHis scDecisionTreeHis) {
		int count = scDecisionTreeHisDao.listAllCount(scDecisionTreeHis);
		return count;
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	@Override
	public ScDecisionTreeHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScDecisionTreeHis getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScDecisionTreeHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveEntity(ScDecisionTreeHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(ScDecisionTreeHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScDecisionTreeHis getEntityById(ScDecisionTreeHis t) {
		// TODO Auto-generated method stub
		return null;
	}

}
