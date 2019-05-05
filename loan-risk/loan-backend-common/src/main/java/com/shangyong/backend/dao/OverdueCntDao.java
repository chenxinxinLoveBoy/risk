package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.OverdueCnt;

/**
 * OverdueCntDao数据库操作接口逾期金额汇总类bean
 * @author hxf
 * @date Wed Aug 16 23:56:19 CST 2017
 **/
@Mapper
public interface OverdueCntDao{

	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(@Param("list") List<OverdueCnt> record);

	/**
	 * 查询逾期金额汇总表(根据申请单编号applicationId)
	 * @param applicationId
	 * @return
	 */
	List<OverdueCnt> findEntityByApplicationId(@Param("applicationId") String applicationId);
	
	/**
	 * 查询逾期金额汇总表(根据申请单编号OverdueCnt对象)
	 * @param applicationId
	 * @return
	 */
	List<OverdueCnt> findOverdueCntByEntity(OverdueCnt overdueCnt);
}