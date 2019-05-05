package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.InformationSchema;

/**
 * 查询数据库及表信息dao
 * 
 * @author hc
 *
 */
@Mapper
public interface DataBaseDao {

	/**
	 * 分页查询表信息
	 * 
	 * @param map
	 * @return
	 */
	List<InformationSchema> findAll(Map map);

	/**
	 * 统计所有表信息
	 * 
	 * @param informationSchema
	 * @return
	 */
	int listAllCount(InformationSchema informationSchema);

	List<InformationSchema> findOne(Map map);

	List<InformationSchema> findTwo(Map map);

}
