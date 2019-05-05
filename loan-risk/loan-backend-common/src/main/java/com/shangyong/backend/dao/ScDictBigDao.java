package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.ScDictBig;

/**
 * ScDictBigDao数据库操作接口类bean
 * @author xk
 * @date Fri Jun 02 22:44:25 CST 2017
 **/
@Mapper
public interface ScDictBigDao{

	@SuppressWarnings("rawtypes")
	public List<Map> getAll(Integer scDictBig);
	
	public int listAllCount();

	public List<ScDictBig> listViewAll(ScDictBig scDictBig);

	public boolean update(ScDictBig scDictBig);

	public int save(ScDictBig sc);

	public int getCountDicBigCode(ScDictBig scDictBig);

	public ScDictBig getObjectById(ScDictBig scDictBig);

	public boolean delete(ScDictBig scDictBig);
 
	public ScDictBig queryByBigCode(String dicBigCode);
 

}