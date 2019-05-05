package com.shangyong.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.BuBoOrderformDao;
import com.shangyong.backend.entity.BuBoOrderform;

/**
 * @author xk
 *
 */
@Service
public class BuBoOrderformServiceImpl implements BaseService<BuBoOrderform> {

	@Autowired
	private BuBoOrderformDao buBoOrderformDao;

	public List<BuBoOrderform> getAllPrepaymentBuBoOrderform() {
		return buBoOrderformDao.getAllPrepaymentBuBoOrderform();
	}

	public List<BuBoOrderform> getAllRepaymentBuBoOrderform() {
		return buBoOrderformDao.getAllRepaymentBuBoOrderform();
	}

	public List<BuBoOrderform> getAllOverdueBuBoOrderform() {
		return buBoOrderformDao.getAllOverdueBuBoOrderform();
	}

	@Override
	public List<BuBoOrderform> findAll(BuBoOrderform t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuBoOrderform getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuBoOrderform getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuBoOrderform getEntityById(BuBoOrderform t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(BuBoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(BuBoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(BuBoOrderform t) {
		// TODO Auto-generated method stub
		return false;
	}

}
