package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuIcePerson;

/**
 * CuIcePersonDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface IcePersonDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<CuIcePerson> getEntityById(String platformCustomerId);

}