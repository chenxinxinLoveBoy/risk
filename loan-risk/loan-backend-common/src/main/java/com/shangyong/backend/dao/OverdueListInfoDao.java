package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.OverdueListInfo;

/**
 * OverdueListInfoDao数据库操作接口逾期名单详情类bean
 * @author hxf
 * @date Wed Aug 16 23:56:19 CST 2017
 **/
@Mapper
public interface OverdueListInfoDao{

	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(@Param("list") List<OverdueListInfo> record);
	
	/**
	 * 查询逾期名单(根据申请单编号applicationId)
	 * @param applicationId
	 * @return
	 */
	List<OverdueListInfo> findEntityByApplicationId(@Param("applicationId") String applicationId);
}