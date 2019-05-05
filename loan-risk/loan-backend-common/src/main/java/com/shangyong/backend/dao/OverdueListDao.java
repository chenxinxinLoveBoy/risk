package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.OverdueList;

/**
 * OverdueListDao数据库操作接口逾期名单类bean
 * @author hxf
 * @date Wed Aug 16 23:56:19 CST 2017
 **/
@Mapper
public interface OverdueListDao{

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(OverdueList record);
	
	/**
	 * 查询逾期名单(根据申请单编号applicationId)
	 * @param applicationId
	 * @return
	 */
	List<OverdueList> findEntityByApplicationId(@Param("applicationId") String applicationId);
	
	/**
	 * 
	 * 修改逾期名单remark(备注) （根据id）
	 * 
	 **/
	int updateRemarkById(OverdueList record);
}