package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScBanControlTpl;

/**
 * ScBanControlTplDao数据库操作接口类bean
 * @author xiajiyun
 * @date Wed Jul 12 13:50:14 CST 2017
 **/
@Mapper
public interface ScBanControlTplDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScBanControlTpl  getEntityById(Integer banControlTplId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScBanControlTpl>  findAll(@Param("id") String id);

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
	int saveEntity(ScBanControlTpl record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScBanControlTpl record);

	/**
	 * 分页统计使用
	 * @param scBanControlTpl
	 * @return
	 */
	List<ScBanControlTpl> findAll(ScBanControlTpl scBanControlTpl);

	/**
	 * 查询所有信息数
	 * @param scBanControlTpl
	 * @return
	 */
	int listAllCount(ScBanControlTpl scBanControlTpl);
	/**
	 * 根据id查询信息
	 * @param scBanControlTpl
	 * @return
	 */
	ScBanControlTpl getEntityById(ScBanControlTpl scBanControlTpl);
	/**
	 * ban_tpl_code查询ban_tpl_code信息
	 * @param scBanControlTpl
	 * @return
	 */
	ScBanControlTpl findCode(ScBanControlTpl scBanControlTpl);
	
	/**
	 * 获取除基础模板外当前有效模板列表
	 * @param params
	 * @return
	 */
	List<ScBanControlTpl> getValidTmpl(Map<String, Object> params);
 
	/**
	 * 查询生效状态模板的百分比
	 * @return
	 */
	double queryNormalPercent(@Param("id") String id, @Param("defaultBanTemplateId") String defaultBanTemplateId);
}