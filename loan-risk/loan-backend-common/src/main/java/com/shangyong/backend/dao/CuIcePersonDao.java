package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.CuIcePerson;

/**
 * CuIcePersonDao数据库操作接口类bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
@Mapper
public interface CuIcePersonDao {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<CuIcePerson> getEntityById(String platformCustomerId);

	/**
	 * 
	 * 查询（根据applicationId查询）
	 * 
	 **/
	List<CuIcePerson> getEntityByApplicationId(String applicationId);
	
	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean saveEntity(CuIcePerson cuIcePerson);
	
	/**
	 *
	 * @param cuIcePersonList
	 * @return
	 */
	boolean saveEntityAll(@Param("cuIcePersonList") List<CuIcePerson> cuIcePersonList);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(CuIcePerson cuIcePerson);

	/**
	 * 根据平台编号删除平台客户所属联系人信息
	 * 
	 * @param platformCustomerId
	 * @return
	 */
	int delete(String platformCustomerId);

}