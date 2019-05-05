package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.CuCustomerCheckApplyExtends;

/**
 * CuCustomerCheckApplyExtendsDao数据库操作接口类bean
 * @author kenzhao
 * @date Sun Sep 24 16:59:24 CST 2017
 **/
@Mapper
public interface CuCustomerCheckApplyExtendsDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CuCustomerCheckApplyExtends  getEntityById(@Param("id") String id);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<CuCustomerCheckApplyExtends>  findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	boolean saveEntity(CuCustomerCheckApplyExtends record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuCustomerCheckApplyExtends record);
	
	/**
	 * add: xiajiyun 
	 * 修改对象信息
	 * @param map
	 * @return
	 */
	public boolean updateEntity(Map<String, String> map);
	
	/**
	 *查询单个数据测试单号所有明细 
	 * @param cuCustomerCheckApplyExtends
	 * @return
	 */
	Page<CuCustomerCheckApplyExtends> findAllByObj(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends);

	/**
	 * 统计
	 * @param cuCustomerCheckApplyExtends
	 * @return
	 */
	int listCountAll(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends);
	
	/**
	 * 查询是否满足一条重发条件
	 * @param cuCustomerCheckApplyExtends
	 * @return
	 */
	public int getCount(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends);
	
	
}