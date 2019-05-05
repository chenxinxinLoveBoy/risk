package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.BuBlacklistManageDao;
import com.shangyong.backend.entity.BuBlacklistManage;
import com.shangyong.backend.service.BlacklistManageService;

/**
 *
 */
@Service
public class BlacklistManageServiceImpl implements BlacklistManageService {

	@Autowired
	private BuBlacklistManageDao blacklistManageDao;
	
	@Override
	public int saveEntity(BuBlacklistManage record) {
		return blacklistManageDao.saveEntity(record);
	}

	@Override
	public List<BuBlacklistManage> listAll(BuBlacklistManage buBlacklistManage) {
		return blacklistManageDao.listAll(buBlacklistManage);
	}

	@Override
	public int listAllSum(BuBlacklistManage buBlacklistManage) {
		return blacklistManageDao.listAllSum(buBlacklistManage);
	}

}
