package com.shangyong.backend.dao.txy;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.txy.TxyRiskInfo;

/**
 * TxyRiskInfoServiceDao数据库操作接口类bean
 * @author mingke.shi
 * @date Sun Dec 10 17:19:38 CST 2017
 **/
@Mapper
public interface TxyRiskInfoServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TxyRiskInfo  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(TxyRiskInfo record);
	
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<TxyRiskInfo> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TxyRiskInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TxyRiskInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TxyRiskInfo record);
	
	/**
	 * 
	 * @param applicationId
	 * @return
	 */
	List<TxyRiskInfo> queryById(String txyAntiFraudId);

}