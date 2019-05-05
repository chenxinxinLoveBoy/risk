package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScLiftConfiguration;

/**
 * ScLiftConfigurationDao数据库操作接口类bean
 * 
 * @author xk
 * @date Wed Sep 13 18:51:24 CST 2017
 **/
@Mapper
public interface ScLiftConfigurationDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScLiftConfiguration getEntityByScLiftConfigurationId(@Param("scLiftConfigurationId") String scLiftConfigurationId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScLiftConfiguration> findAll(@Param("scLiftConfigurationId") String scLiftConfigurationId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("scLiftConfigurationId") String scLiftConfigurationId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScLiftConfiguration scLiftConfiguration);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScLiftConfiguration scLiftConfiguration);

	/**
	 * 分页统计使用
	 * 
	 * @return
	 */
	List<ScLiftConfiguration> findAll(ScLiftConfiguration scLiftConfiguration);

	/**
	 * 查询所有信息数
	 * 
	 * @return
	 */
	int listAllCount(ScLiftConfiguration scLiftConfiguration);

	ScLiftConfiguration getEntityById(ScLiftConfiguration scLiftConfiguration);

	/**
	 * 根据对象查询提额值百分比和提额尾数
	 * 
	 * @param scLiftConfiguration
	 * @return
	 */
	ScLiftConfiguration getLift(ScLiftConfiguration scLiftConfiguration);

}