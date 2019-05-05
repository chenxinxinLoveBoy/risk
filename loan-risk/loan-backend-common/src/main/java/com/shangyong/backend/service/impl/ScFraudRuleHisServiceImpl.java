package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScFraudRuleHisDao;
import com.shangyong.backend.entity.ScFraudRuleHis;
import com.shangyong.backend.service.BaseService;

@Service
public class ScFraudRuleHisServiceImpl implements BaseService<ScFraudRuleHis> {

	@Autowired
	private ScFraudRuleHisDao scFraudRuleHisDao;
	
	@Override
	public List<ScFraudRuleHis> findAll(ScFraudRuleHis scFraudRuleHis) {
		return scFraudRuleHisDao.findAll(scFraudRuleHis);
	}

	@Override
	public ScFraudRuleHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRuleHis getEntityById(Integer id) {
		return scFraudRuleHisDao.getEntityById(id);
	}

	@Override
	public ScFraudRuleHis getEntityById(ScFraudRuleHis scFraudRuleHis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(ScFraudRuleHis scFraudRuleHis) {
		return scFraudRuleHisDao.saveEntity(scFraudRuleHis);
	}

	@Override
	public boolean updateEntity(ScFraudRuleHis scFraudRuleHis) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScFraudRuleHis scFraudRuleHis) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScFraudRuleHis scFraudRuleHis) {
		// TODO Auto-generated method stub
		return scFraudRuleHisDao.listAllCount(scFraudRuleHis);
	}

}
