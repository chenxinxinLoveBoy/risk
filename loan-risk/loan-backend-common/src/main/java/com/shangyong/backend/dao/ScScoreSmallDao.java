package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.entity.ScScoreSmall;

/**
 * ScScoreSmallDao数据库操作接口类bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@Mapper
public interface ScScoreSmallDao {

	/**
	 * 查询所有小类关联大类 数量
	 * 
	 * @param scoreBigCode
	 *            大类编码
	 * @param scoreSmallCode
	 *            小类编码
	 * @param scoreRuleName
	 *            小类评分规则名称
	 * @return
	 */
	public int listAllCountUnion(Map<String, Object> param);

	/**
	 * 查询所有小类关联大类
	 * 
	 * @param scoreBigCode
	 *            大类编码
	 * @param scoreSmallCode
	 *            小类编码
	 * @param scoreRuleName
	 *            小类评分规则名称
	 * @return
	 */
	List<Map<String, String>> getAllUnion(Map<String, Object> param);

	/**
	 * 查询所有小类数量 根据ScScoreSmall对象过滤
	 * 
	 * @param scScoreSmall
	 * @return
	 */
	public int listAllCount(ScScoreSmall scScoreSmall);

	/**
	 * 查询所有小类 根据ScScoreSmall对象过滤
	 * 
	 * @param scScoreSmall
	 * @return
	 */
	List<ScScoreSmall> getAll(ScScoreSmall scScoreSmall);

	/**
	 * 
	 * 查询（根据评分项规则小类编号查询）
	 * 
	 **/
	ScScoreSmall queryByScoreSmallCode(String scoreSmallCode);

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	ScScoreSmall selectByPrimaryKey(Integer scoreSmallId);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(String scoreSmallId);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(ScScoreSmall scScoreSmall);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean insertSelective(ScScoreSmall scScoreSmall);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateByPrimaryKeySelective(ScScoreSmall scScoreSmall);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(ScScoreSmall scScoreSmall);

	/**
	 * 根据大类序号，修改状态
	 * 
	 * @param scScoreSmall
	 * @return
	 */
	public boolean updatescScoreSmall(ScScoreSmall scScoreSmall);

	/**
	 * 批量保存信息
	 * 
	 * @return
	 */
	public int saveListEntity(List<ScScoreSmall> scScoreSmallList);

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
	public List<ScScoreSmall> findAllTemplate(Map<?, ?> map);

	/**
	 * 当前模板下关联的欺诈规则项
	 */
	public int listTemplateCount(ScScoreSmall scScoreSmall);

	/**
	 * 当前模板下关联的数据 分页
	 */
	public List<ScScoreSmall> findTemplate(ScScoreSmall scScoreSmall);

	boolean updateScScoreSmallByBigId(ScScoreSmall scScoreSmall);

	List<ScScoreSmall> queryByScoreBigIdAndTplId(ScScoreSmall scScoreSmall);

	ScScoreSmall queryByScoreSmallCodeAndTplId(ScScoreSmall scScoreSmall);

	double getSumPercent(String scoreTplId);
	/**
	 * 加载树菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<ZTree> getScScoreTree(String scoreTplId);
}