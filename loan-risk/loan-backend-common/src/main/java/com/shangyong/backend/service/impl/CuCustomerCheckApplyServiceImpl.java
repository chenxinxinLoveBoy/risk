package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shangyong.backend.dao.CuCustomerCheckApplyDao;
import com.shangyong.backend.entity.CuCustomerCheckApply;
import com.shangyong.backend.service.BaseService;

@Service
public class CuCustomerCheckApplyServiceImpl implements BaseService<CuCustomerCheckApply> {
	@Autowired
	private CuCustomerCheckApplyDao cuCustomerCheckApplyDao;
 
	@Override
	public List<CuCustomerCheckApply> findAll(CuCustomerCheckApply t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuCustomerCheckApply getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuCustomerCheckApply getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CuCustomerCheckApply getEntityById(CuCustomerCheckApply t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(CuCustomerCheckApply t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(CuCustomerCheckApply t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(CuCustomerCheckApply t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo 页号
	 * @param pageSize 每页显示记录数
	 * @return
	 */
	public Page<CuCustomerCheckApply> getAll(CuCustomerCheckApply cuCustomerCheckApply) {
		int pageNo = cuCustomerCheckApply.getPageIndex();
		int pageSize = cuCustomerCheckApply.getPageSize();
		PageHelper.startPage(pageNo, pageSize);
		return cuCustomerCheckApplyDao.getAll(cuCustomerCheckApply);
	}
	
	
	/**
	 * 加载满足一键重发的数据 
	 * @return
	 */
//	public List<CuCustomerCheckApply> list(CuCustomerCheckApply cuCustomerCheckApply) {
//		return cuCustomerCheckApplyDao.list(cuCustomerCheckApply);
//	}

}
