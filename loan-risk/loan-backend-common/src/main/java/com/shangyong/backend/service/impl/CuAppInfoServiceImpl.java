package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.CuAppInfoDao;
import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.service.CuAppInfoService;

/**
 * 客户手机应用列表记录service实现
 * @author hc
 *
 */
@Service
public class CuAppInfoServiceImpl implements CuAppInfoService {
	
	@Autowired
	private CuAppInfoDao CuAppInfoDao;

	/**
	 * 查询对象信息
	 */
	@Override
	public List<CuAppInfo> getEntityById(CuAppInfo cuAppInfo) { 
		return CuAppInfoDao.getEntityById(cuAppInfo);
	}
	
	/**
	 * 统计
	 */
	@Override
	public int listAllCount(CuAppInfo cuAppInfo) { 
		int count = CuAppInfoDao.listAllCount(cuAppInfo);
		return count;
	}
 
}
