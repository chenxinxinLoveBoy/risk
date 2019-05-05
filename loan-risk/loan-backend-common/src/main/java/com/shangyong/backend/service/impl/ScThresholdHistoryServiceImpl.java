package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.ScThresholdHistoryDao;
import com.shangyong.backend.entity.ScThresholdHistory;
import com.shangyong.backend.service.BaseService;

/**
 * 欺诈风险阈值历史service实现
 * @author hc
 *
 */
@Service
public class ScThresholdHistoryServiceImpl implements BaseService<ScThresholdHistory> {
	
	/** 
	 	* ScThresholdHistoryDao数据库操作接口类bean
	 	* @author xxj
	 	* @date Wed Jul 05 20:55:50 CST 2017
	 **/ 
	@Autowired
	private ScThresholdHistoryDao scThresholdHistoryDao;

	@Override
	public List<ScThresholdHistory> findAll(ScThresholdHistory scThresholdHistory) { 
		return scThresholdHistoryDao.findAll(scThresholdHistory);
	}

	@Override
	public ScThresholdHistory getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScThresholdHistory getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScThresholdHistory getEntityById(ScThresholdHistory scThresholdHistory) { 
		return scThresholdHistoryDao.getEntityByObj(scThresholdHistory);
	}

	@Override
	@Transactional
	public boolean saveEntity(ScThresholdHistory scThresholdHistory) { 
		boolean flag = false;
		int count = scThresholdHistoryDao.saveEntity(scThresholdHistory);
		if(count > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScThresholdHistory scThresholdHistory) { 
		boolean flag = false;
		int temp = scThresholdHistoryDao.updateEntity(scThresholdHistory);
		if(temp > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScThresholdHistory t) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScThresholdHistory scThresholdHistory) { 
		int count = scThresholdHistoryDao.listAllCount(scThresholdHistory);
		return count;
	}

}
