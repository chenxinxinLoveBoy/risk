package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;

/**
 * BqsRepContactsInfoServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:34 CST 2017
 **/
@Mapper
public interface BqsRepContactsInfoServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepContactsInfo  selectByPrimaryKey(@Param("id") Long id);
	
	/**
	 * 
	 * 查询（根据主键bqsPetitionerId查询）
	 * 
	 **/
	List<BqsRepContactsInfo>  queryListById(String bqsPetitionerId);

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
	int saveEntity(BqsRepContactsInfo record);
	/**
	 * 批量插入
	 * @param record
	 * @return
	 */
	int saveAllEntity(List<BqsRepContactsInfo> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepContactsInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepContactsInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepContactsInfo record);

}