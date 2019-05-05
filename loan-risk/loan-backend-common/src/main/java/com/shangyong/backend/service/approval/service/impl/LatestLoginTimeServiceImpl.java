package com.shangyong.backend.service.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.LatestLoginTimeDao;
import com.shangyong.backend.entity.CuCustomerExpand;
import com.shangyong.backend.service.approval.service.LatestLoginTimeService;

@Service
public class LatestLoginTimeServiceImpl implements LatestLoginTimeService {

	@Autowired
	private LatestLoginTimeDao latestLoginTimeDao;

	@Override
	public List<CuCustomerExpand> findListEntity() {
		return latestLoginTimeDao.findListEntity();
	}

	@Override
	public boolean updateEntity(CuCustomerExpand customerExpand) {
		return latestLoginTimeDao.updateEntity(customerExpand);
	}
	
}
