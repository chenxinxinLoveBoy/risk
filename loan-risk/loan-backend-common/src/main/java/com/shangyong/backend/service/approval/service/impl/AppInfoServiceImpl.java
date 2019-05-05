package com.shangyong.backend.service.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.AppInfoDao;
import com.shangyong.backend.entity.approval.AppInfo;
import com.shangyong.backend.service.approval.service.AppInfoService;

@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoDao appInfoDao;
	
	@Override
	public List<AppInfo> findAppInfoBycustomerId(String customerId, String appName) {
		return appInfoDao.findAppInfo(customerId, appName);
	}
}
