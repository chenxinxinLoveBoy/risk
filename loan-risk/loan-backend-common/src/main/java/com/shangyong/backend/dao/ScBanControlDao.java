package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScBanControl;

/**
 * ScBanControlDao数据库操作接口类bean
 * 
 * @author xk
 * @date Sat Jun 10 16:30:22 CST 2017
 **/
@Mapper
public interface ScBanControlDao {

	/**
	 *
	 * @param scBanControl
	 * @return
	 */
	public int listAllCount(ScBanControl scBanControl);

	/**
	 *
	 * @param scBanControl
	 * @return
	 */
	List<ScBanControl> getAll(ScBanControl scBanControl);

	/**
	 * 查询（根据禁止项规则对应编号查询）
	 **/
	List<ScBanControl> queryByBanCode(String banCode);

	/**
	 *
	 * @param banCode
	 * @param defaultFraudTemplateId
	 * @return
	 */
	ScBanControl queryByBanCodeAndId(@Param("banCode") String banCode, @Param("defaultFraudTemplateId") String defaultFraudTemplateId);

	/**
	 * 
	 * 查询（根据禁止项模板编号和规则对应编号查询）
	 * 
	 **/
	ScBanControl queryByBanCodeAndTplId(@Param("tplId") String tplId, @Param("banCode") String banCode);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScBanControl selectByPrimaryKey(Integer banControlId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(String banControlId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ScBanControl scBanControl);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean insertSelective(ScBanControl scBanControl);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateByPrimaryKeySelective(ScBanControl scBanControl);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(ScBanControl scBanControl);

	/**
	 * 查询所有数据，不分页
	 * @param scBanControl
	 * @return
	 */
	public List<ScBanControl> queryAll(ScBanControl scBanControl);

	/**
	 * 批量保存信息
	 * @param scBanControlList
	 * @return
	 */
	public int saveListEntity(List<ScBanControl> scBanControlList);

	/**
	 * 查询模板信息
	 * @param map
	 * @return
	 */
	public int listAllTemplateCount(Map<?, ?> map);

	/**
	 * 查询模板信息分页
	 * @param map 
	 * @return
	 */
	public List<ScBanControl> findAllTemplate(Map<?, ?> map);

	/**
	 * 当前模板下关联的禁止项
	 * @param scBanControl
	 */
	public int listTemplateCount(ScBanControl scBanControl);

	/**
	 * 当前模板下关联的数据 分页
	 * @param scBanControl
	 */
	public List<ScBanControl> findTemplate(ScBanControl scBanControl); 
 
}