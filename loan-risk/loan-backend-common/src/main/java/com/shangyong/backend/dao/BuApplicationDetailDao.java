package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuApplicationDetail;

/**
 * BuApplicationDetailDao数据库操作接口类bean
 * @author kenzhao
 * @date Thu Dec 21 17:26:56 CST 2017
 **/
@Mapper
public interface BuApplicationDetailDao{

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(BuApplicationDetail record);


	/**
	 * 查看拒绝步骤个数
	 */
	int refuseStepCount(@Param("applicationId") String applicationId);


}