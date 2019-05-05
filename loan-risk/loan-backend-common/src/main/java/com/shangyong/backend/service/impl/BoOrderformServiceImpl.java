package com.shangyong.backend.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.BoOrderformDao;
import com.shangyong.backend.entity.BoOrderform;
import com.shangyong.backend.service.BaseService;

/**
 * 订单信息service实现类
 * @author xixinghui
 *
 */
@Service
public class BoOrderformServiceImpl implements BaseService<BoOrderform> {
	
	@Autowired
	private BoOrderformDao billDao;
	
	/**
	 * 统计查询数据量
	 * @param boOrderform    订单信息entity对象
	 * @return
	 */
	public int findAllCount(BoOrderform boOrderform) { 
		return billDao.findAllCount(boOrderform);
	}

	/**
	 * 查询所有订单信息
	 * @param boOrderform    订单信息entity对象
	 * @return 
	 */
	@Override
	public List<BoOrderform> findAll(BoOrderform boOrderform) {
		List<BoOrderform> boOrderFormList = billDao.findAll(boOrderform);
		for (BoOrderform boOrder : boOrderFormList) {
			if (StringUtils.isNotEmpty(boOrder.getOrderTime()))
			boOrder.setOrderTime(formatTimeString(boOrder.getOrderTime()));
			if (StringUtils.isNotEmpty(boOrder.getNewRepayDate()))
			boOrder.setNewRepayDate(formatTimeString(boOrder.getNewRepayDate()));
			if (StringUtils.isNotEmpty(boOrder.getActualRepayDate()))
			boOrder.setActualRepayDate(formatTimeString(boOrder.getActualRepayDate()));
		}
		return boOrderFormList;
	}
	
	/**
	 * 取得时间字段的年月日时分秒
	 * @param str
	 * @return
	 */
	private String formatTimeString(String str) {
		return str.split("\\.")[0];
	}

	@Override
	public BoOrderform getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoOrderform getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoOrderform getEntityById(BoOrderform boOrderform) {
		return billDao.getEntityById(boOrderform);
	}

	@Override
	public boolean saveEntity(BoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(BoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(BoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

}
