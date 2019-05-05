package com.shangyong.backend.dao.td;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.td.TdRiskItems;
import com.shangyong.backend.entity.td.vo.TdRiskItemCount;

/**
 * TdRiskItemsServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
@Mapper
public interface TdRiskItemsServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	TdRiskItems  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据tdAntiFraudId查询）
	 * 
	 **/
	List<TdRiskItems>  queryById(String tdAntiFraudId);

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
	int saveEntity(TdRiskItems record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(TdRiskItems record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(TdRiskItems record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(TdRiskItems record);
	
	/**
	 * 查询(统计命中同盾)
	 * @param tdRiskItemCount
	 * @return
	 */
	List<TdRiskItemCount> queryTdResultCount(TdRiskItemCount tdRiskItemCount);

}