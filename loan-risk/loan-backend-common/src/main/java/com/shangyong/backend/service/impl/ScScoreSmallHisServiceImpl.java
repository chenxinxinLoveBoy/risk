package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreSmallHisDao;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.BaseService;

@Service
public class ScScoreSmallHisServiceImpl implements BaseService<ScScoreSmall> {

	@Autowired
	private ScScoreSmallHisDao scScoreSmallHisDao;

	@Override
	public List<ScScoreSmall> findAll(ScScoreSmall scScoreSmall) {
		return scScoreSmallHisDao.getAll(scScoreSmall);
	}

	@Override
	public ScScoreSmall getEntityById(String SmallHisId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreSmall getEntityById(Integer SmallHisId) {
		return scScoreSmallHisDao.selectByPrimaryKey(SmallHisId);
	}

	@Override
	public ScScoreSmall getEntityById(ScScoreSmall scScoreSmall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScScoreSmall scScoreSmall) {

		return false;
	}

	@Override
	public boolean updateEntity(ScScoreSmall scScoreSmall) {
		return false;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScScoreSmall scScoreSmall) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScScoreSmall scScoreSmall) {
		return scScoreSmallHisDao.listAllCount(scScoreSmall);
	}

}
