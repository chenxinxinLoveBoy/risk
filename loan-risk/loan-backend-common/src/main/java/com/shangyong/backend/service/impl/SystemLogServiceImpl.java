package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.SystemLogBo;
import com.shangyong.backend.dao.SystemLogDao;
import com.shangyong.backend.service.BaseService;

/**
 * 系统日志dao
 * 
 * @author xiajiyun
 *
 */
@Service
public class SystemLogServiceImpl implements BaseService<SystemLogBo> {

	@Autowired
	private SystemLogDao systemLogDao;

	@Override
	public List<SystemLogBo> findAll(SystemLogBo t) {
		return systemLogDao.findAll(t);
	}
	
	public int listAllCount(SystemLogBo t){
		return systemLogDao.findAllCount(t);
	}

	@Override
	public SystemLogBo getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemLogBo getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SystemLogBo getEntityById(SystemLogBo systemLog) { 
		return systemLogDao.getEntityById(systemLog);
	}

	@Override
	public boolean saveEntity(SystemLogBo t) {
		return systemLogDao.saveEntity(t);
	}

	@Override
	public boolean updateEntity(SystemLogBo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(SystemLogBo t) {
		// TODO Auto-generated method stub
		return false;
	}

}
