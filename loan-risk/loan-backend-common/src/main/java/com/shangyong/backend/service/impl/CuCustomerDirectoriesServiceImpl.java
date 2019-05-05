package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.CuCustomerDirectoriesDao;
import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.service.CuCustomerDirectoriesService;

@Service
public class CuCustomerDirectoriesServiceImpl implements CuCustomerDirectoriesService {
	
	/**
	 * CuCustomerDirectoriesDao数据库操作接口类bean
	 * @author xiajiyun
	 * @date Sat Aug 12 22:40:57 CST 2017
	 **/
	@Autowired
	private CuCustomerDirectoriesDao  cuCustomerDirectoriesDao;

	/**
	 * 查询对象信息
	 */
	@Override
	public List<CuCustomerDirectories> getEntityById(CuCustomerDirectories cuCustomerDirectories) { 
		return cuCustomerDirectoriesDao.getEntityById(cuCustomerDirectories);
	}

	/**
	 * 统计
	 */
	public int listAllCount(CuCustomerDirectories cuCustomerDirectories) { 
		int count = cuCustomerDirectoriesDao.listAllCount(cuCustomerDirectories);
		return count;
	}

}
