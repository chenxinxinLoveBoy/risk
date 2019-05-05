package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.DataBaseDao;
import com.shangyong.backend.entity.InformationSchema;
import com.shangyong.backend.service.BaseService;

/**
 * 查询数据库及表信息service实现
 * 
 * @author hc
 *
 */
@Service
public class DataBaseImpl implements BaseService<InformationSchema> {

	/**
	 * 查询数据库及表DAO
	 */
	@Autowired
	private DataBaseDao dataBaseDao;

	public List<InformationSchema> findOne(InformationSchema informationSchema) {
		Map map = new HashMap();
		map.put("tableName", informationSchema.getTableName());// 表名
		map.put("tableSchema", informationSchema.getTableSchema());// 库名
		return dataBaseDao.findOne(map);
	}

	public List<InformationSchema> findTwo(InformationSchema informationSchema) {
		Map map = new HashMap();
		map.put("tableName", informationSchema.getTableName());// 表名
		map.put("tableSchema", informationSchema.getTableSchema());// 库名
		return dataBaseDao.findTwo(map);
	}

	@Override
	public List<InformationSchema> findAll(InformationSchema informationSchema) {
		Map map = new HashMap();
		map.put("tableName", informationSchema.getTableName());// 表名
		map.put("tableSchema", informationSchema.getTableSchema());// 库名
		map.put("pageIndex", informationSchema.getPageIndex());
		map.put("pageSize", informationSchema.getPageSize());
		return dataBaseDao.findAll(map);
	}

	@Override
	public InformationSchema getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformationSchema getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InformationSchema getEntityById(InformationSchema t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(InformationSchema t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(InformationSchema t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(InformationSchema t) {
		return false;
	}

	public int listAllCount(InformationSchema informationSchema) {
		int count = dataBaseDao.listAllCount(informationSchema);
		return count;
	}

}
