package com.shangyong.backend.service.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.TdPlatformCheckDao;
import com.shangyong.backend.entity.approval.TdPlatformCheck;
import com.shangyong.backend.service.approval.service.TdPlatformCheckService;


@Service
public class TdPlatformCheckServiceImpl implements TdPlatformCheckService {

	@Autowired
	private TdPlatformCheckDao tdPlatformDao;
	@Override
	public List<TdPlatformCheck> getListById(String applicationId) {

		return tdPlatformDao.getListById(applicationId);
	}

}
