package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.SysParamHisDao;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;

@Service
public class SysParamHisServiceImpl implements BaseService<SysParam> {
	@Autowired
	private SysParamHisDao sysParamHisDao;

	@Override
	public List<SysParam> findAll(SysParam sysParam) {
		return sysParamHisDao.getAll(sysParam);
	}

	@Override
	public SysParam getEntityById(String paramHisId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysParam getEntityById(Integer paramHisId) {
		return sysParamHisDao.selectByPrimaryKey(paramHisId);
	}

	@Override
	public SysParam getEntityById(SysParam sysParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(SysParam sysParam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(SysParam sysParam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String paramHisId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(SysParam sysParam) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(SysParam sysParam) {
		return sysParamHisDao.listAllCount(sysParam);
	}
}
