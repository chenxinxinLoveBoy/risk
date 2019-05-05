package com.shangyong.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScFraudRuleTplHisDao;
import com.shangyong.backend.entity.ScFraudRuleTplHis;
import com.shangyong.backend.service.BaseService;

/**
 * 
 * @author hc
 *
 */
@Service
public class ScFraudRuleTplHisServiceImpl implements BaseService<ScFraudRuleTplHis> {

	Logger logger = LoggerFactory.getLogger(ScFraudRuleTplHisServiceImpl.class);

	@Autowired
	private ScFraudRuleTplHisDao scFraudRuleTplHisDao;

	public List<ScFraudRuleTplHis> findAll(ScFraudRuleTplHis scFraudRuleTplHis) {
		return scFraudRuleTplHisDao.findAll(scFraudRuleTplHis);
	}

	public int listAllCount(ScFraudRuleTplHis scFraudRuleTplHis) {
		int count = scFraudRuleTplHisDao.listAllCount(scFraudRuleTplHis);
		return count;
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	@Override
	public ScFraudRuleTplHis getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRuleTplHis getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScFraudRuleTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveEntity(ScFraudRuleTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(ScFraudRuleTplHis t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScFraudRuleTplHis getEntityById(ScFraudRuleTplHis t) {
		// TODO Auto-generated method stub
		return null;
	}

}
