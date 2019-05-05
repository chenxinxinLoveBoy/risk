package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScScoreTpl;

/**
 * ScScoreTplDao数据库操作接口类bean
 * 
 * @date Thu Jul 27 13:54:27 CST 2017
 **/
@Mapper
public interface ScScoreTplDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreTpl getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScScoreTpl> findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScScoreTpl record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScScoreTpl scScoreTpl);

	List<ScScoreTpl> findAll(ScScoreTpl scScoreTpl);

	/**
	 * 查询所有信息数
	 * 
	 */
	int listAllCount(ScScoreTpl scScoreTpl);

	/**
	 * 根据id查询信息
	 * 
	 * @return
	 */
	ScScoreTpl getEntityById(ScScoreTpl scScoreTpl);

	/**
	 * 
	 * @return
	 */
	ScScoreTpl findCode(ScScoreTpl scScoreTpl);

	List<ScScoreTpl> getScScoreTpl(Map<String, Object> params);

	/**
	 * 获取所有欺诈规则的权重总和
	 * 
	 * @return
	 */
	double getSumTplPercent(@Param("defaultScoreTemplateId") String defaultScoreTemplateId,
                            @Param("scoreTplId") String scoreTplId);

}