package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScFraudRuleBigHisDao;
import com.shangyong.backend.entity.ScFraudRuleBigHis;
import com.shangyong.backend.service.BaseService;

@Service
public class ScFraudRuleBigHisServiceImpl implements BaseService<ScFraudRuleBigHis> {

	@Autowired
	private ScFraudRuleBigHisDao scFraudRuleBigHisDao;

	@Override
	public List<ScFraudRuleBigHis> findAll(ScFraudRuleBigHis scFraudRuleBigHis) {
		// TODO Auto-generated method stub
		return scFraudRuleBigHisDao.findAll(scFraudRuleBigHis);
	}

	@Override
	public ScFraudRuleBigHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRuleBigHis getEntityById(Integer fraudRuleBigHisId) {
 		return scFraudRuleBigHisDao.getEntityById(fraudRuleBigHisId);
	}

	@Override
	public ScFraudRuleBigHis getEntityById(ScFraudRuleBigHis scFraudRuleBigHis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScFraudRuleBigHis scFraudRuleBigHis) {
 		return scFraudRuleBigHisDao.saveEntity(scFraudRuleBigHis);
	}

	@Override
	public boolean updateEntity(ScFraudRuleBigHis scFraudRuleBigHis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScFraudRuleBigHis scFraudRuleBigHis) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScFraudRuleBigHis scFraudRuleBigHis) {
 		return scFraudRuleBigHisDao.listAllCount(scFraudRuleBigHis);
	}

}
