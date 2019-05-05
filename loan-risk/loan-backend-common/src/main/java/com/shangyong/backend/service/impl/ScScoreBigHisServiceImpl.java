package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreBigHisDao;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.service.BaseService;

@Service
public class ScScoreBigHisServiceImpl implements BaseService<ScScoreBig> {

	@Autowired
	private ScScoreBigHisDao scScoreBigHisDao;

	@Override
	public List<ScScoreBig> findAll(ScScoreBig scScoreBig) {
		return scScoreBigHisDao.getAll(scScoreBig);
	}

	@Override
	public ScScoreBig getEntityById(String bigHisId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreBig getEntityById(Integer bigHisId) {
		return scScoreBigHisDao.selectByPrimaryKey(bigHisId);
	}

	@Override
	public ScScoreBig getEntityById(ScScoreBig scScoreBig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScScoreBig scScoreBig) {

		return false;
	}

	@Override
	public boolean updateEntity(ScScoreBig scScoreBig) {
		return false;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScScoreBig scScoreBig) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScScoreBig scScoreBig) {
		return scScoreBigHisDao.listAllCount(scScoreBig);
	}

}
