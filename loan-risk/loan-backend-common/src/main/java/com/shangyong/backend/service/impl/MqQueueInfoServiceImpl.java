package com.shangyong.backend.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.mq.MqQueueInfoDao;
import com.shangyong.backend.entity.mq.MqQueueInfo;
import com.shangyong.backend.service.BaseService;

/**
 * 欺诈项模版service实现
 * 
 * @author hc
 *
 */
@Service
public class MqQueueInfoServiceImpl implements BaseService< MqQueueInfo> {

	Logger logger = LoggerFactory.getLogger(MqQueueInfoServiceImpl.class);
	
	@Autowired
	private MqQueueInfoDao mqQueueInfoDao;

	@Override
	public List<MqQueueInfo> findAll(MqQueueInfo mqQueueInfo) {
		return mqQueueInfoDao.findAllMq(mqQueueInfo);
	}

	@Override
	public MqQueueInfo getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MqQueueInfo getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MqQueueInfo getEntityById(MqQueueInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(MqQueueInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(MqQueueInfo t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(MqQueueInfo t) {
		// TODO Auto-generated method stub
		return false;
	}
	public int listAllCount(MqQueueInfo mqQueueInfo) {
		int count = mqQueueInfoDao.listAllCount(mqQueueInfo);
		return count;
	}
	
}
