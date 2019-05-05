package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.ScBanControl;

/**
 * ScBanControlHisDao数据库操作接口类bean
 * 
 * @author xk
 * @date Fri Jun 16 20:30:13 CST 2017
 **/
@Mapper
public interface ScBanControlHisDao {
	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(ScBanControl scBanControl);

	public int listAllCount(ScBanControl scBanControl);

	List<ScBanControl> getAll(ScBanControl scBanControl);

	/**
	 * 
	 * 查询（根据主键查询）
	 * 
	 **/
	public ScBanControl selectByPrimaryKey(Integer controlHisId);

	/**
	 * 根据日期查询当天修改的禁止项规则的所有历史记录
	 * 
	 * @param startTimeInterval
	 * @param endTimeInterval
	 * @return
	 */
	List<ScBanControl> findAllScBanControlHis(@Param("startTimeInterval") String startTimeInterval,
                                              @Param("endTimeInterval") String endTimeInterval);

}