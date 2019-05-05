package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepMnoSevenDayMobile;

/**
 * BqsRepMnoSevenDayMobileServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:35 CST 2017
 **/
@Mapper
public interface BqsRepMnoSevenDayMobileServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepMnoSevenDayMobile  selectByPrimaryKey(@Param("id") Long id);

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
	int saveEntity(BqsRepMnoSevenDayMobile record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepMnoSevenDayMobile record);
	
	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<BqsRepMnoSevenDayMobile> list);
	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepMnoSevenDayMobile record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepMnoSevenDayMobile record);

}