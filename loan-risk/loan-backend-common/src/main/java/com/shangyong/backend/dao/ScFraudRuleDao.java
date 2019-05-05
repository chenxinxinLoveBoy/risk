package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.entity.ScFraudRule;

/**
 * ScFraudRuleDao数据库操作接口类bean
 * 
 * @author xiajiyun
 * @date Wed Jul 19 11:24:24 CST 2017
 **/
@Mapper
public interface ScFraudRuleDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScFraudRule getEntityById(Integer fraudRuleId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<ScFraudRule> findAll(ScFraudRule scFraudRule);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	boolean saveEntity(ScFraudRule record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(ScFraudRule record);

	int listAllCount(ScFraudRule scFraudRule);

	List<ScFraudRule> queryByFraudRuleCode(String fraudRuleCode);

	/**
	 * 批量保存信息
	 * 
	 * @return
	 */
	public int saveListEntity(List<ScFraudRule> scFraudRuleList);

	/**
	 * 查询模板信息
	 * 
	 * @param map
	 * @return
	 */
	public int listAllTemplateCount(Map<?, ?> map);

	/**
	 * 查询模板信息分页
	 * 
	 * @param map
	 * @return
	 */
	public List<ScFraudRule> findAllTemplate(Map<?, ?> map);

	/**
	 * 当前模板下关联的欺诈规则项
	 */
	public int listTemplateCount(ScFraudRule scFraudRule);

	/**
	 * 当前模板下关联的数据 分页
	 */
	public List<ScFraudRule> findTemplate(ScFraudRule scFraudRule);

	boolean updateFraudRuleByBigId(ScFraudRule fraudRule);

	List<ScFraudRule> queryByFraudRuleBigIdAndTplId(ScFraudRule scFraudRule);

	ScFraudRule queryByFraudRuleCodeAndTplId(ScFraudRule scFraudRule);
	
	double getSumPercent(String fraudRuleTplId);
	
	/**
	 * 加载树菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<ZTree> getScFraudRuleTree(String fraudRuleTplId);

}