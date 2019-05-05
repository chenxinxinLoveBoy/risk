package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScDecisionTree;

/**
 * ScDecisionTreeDao数据库操作接口类bean
 * 
 * @author xiajiyun
 * @date Thu Aug 03 21:51:02 CST 2017
 **/
@Mapper
public interface ScDecisionTreeDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScDecisionTree getEntityByDecisionTreeId(@Param("decisionTreeId") String decisionTreeId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScDecisionTree> findAll(@Param("decisionTreeId") String decisionTreeId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("decisionTreeId") String decisionTreeId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScDecisionTree record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(ScDecisionTree record);

	/**
	 * 分页统计使用
	 * 
	 * @return
	 */
	List<ScDecisionTree> findAll(ScDecisionTree scDecisionTree);

	/**
	 * 查询所有信息数
	 * 
	 * @return
	 */
	int listAllCount(ScDecisionTree scDecisionTree);

	/**
	 * 根据id查询信息
	 * 
	 * @return
	 */
	ScDecisionTree getEntityById(ScDecisionTree scDecisionTree);

	/**
	 * decision_tree_code查询
	 * 
	 * @return
	 */
	ScDecisionTree findCode(ScDecisionTree scDecisionTree);

	/**
	 * 查询生效状态决策树的百分比
	 * 
	 * @return
	 */
	double getSumTplPercent(@Param("defaultTreeTemplateId") String defaultTreeTemplateId,
                            @Param("decisionTreeId") String decisionTreeId);

	/**
	 * 当前生效决策树
	 * 
	 * @param params
	 * @return
	 */
	List<ScDecisionTree> getScDecisionTree(Map<String, Object> params);

}