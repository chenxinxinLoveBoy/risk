package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScFraudRuleTpl;

/**
 * ScFraudRuleTplDao数据库操作接口类bean
 * 
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
@Mapper
public interface ScFraudRuleTplDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudRuleTpl getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScFraudRuleTpl> findAll(@Param("id") String id);

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
	int saveEntity(ScFraudRuleTpl record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScFraudRuleTpl record);

	List<ScFraudRuleTpl> findAll(ScFraudRuleTpl scFraudRuleTpl);

	/**
	 * 查询所有信息数
	 * 
	 */
	int listAllCount(ScFraudRuleTpl scFraudRuleTpl);

	/**
	 * 根据id查询信息
	 * 
	 * @return
	 */
	ScFraudRuleTpl getEntityById(ScFraudRuleTpl scFraudRuleTpl);

	/**
	 * 
	 * @return
	 */
	ScFraudRuleTpl findCode(ScFraudRuleTpl scFraudRuleTpl);

	List<ScFraudRuleTpl> getFraudRuleTpl(Map<String, Object> params);

	/**
	 * 获取所有欺诈规则的权重总和
	 * 
	 * @return
	 */
	double getSumTplPercent(@Param("defaultFraudTemplateId") String defaultFraudTemplateId, @Param("fraudRuleTplId") String fraudRuleTplId);

}