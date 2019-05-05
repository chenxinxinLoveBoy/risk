package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.CuCustomerCheckApply;

/**
 * CuCustomerCheckApplyDao数据库操作接口类bean
 * @author kenzhao
 * @date Sat Sep 23 18:07:05 CST 2017
 **/
@Mapper
public interface CuCustomerCheckApplyDao{
	/**
	 * 使用分页插件查询所有信息数 add: xuke
	 * 
	 * @return
	 */
	Page<CuCustomerCheckApply> getAll(CuCustomerCheckApply cuCustomerCheckApply);
	
	/**
	 *  
	 * 加载满足一键重发的数据 
	 * @return
	 */
//	List<CuCustomerCheckApply> list(CuCustomerCheckApply cuCustomerCheckApply);
	
	
	/**
	 * add: xiajiyun
	 * 查询（根据主键ID查询）
	 * 
	 **/
	int  getEntityById(Map<String, String> map);

	/**
	 * 
	 * 查询对象信息（根据主键ID查询）
	 * 
	 **/
	CuCustomerCheckApply  find(String customerCheckApplyId);

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
	int saveEntity(CuCustomerCheckApply record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(CuCustomerCheckApply record);
	
	/**
	 * add xiajiyun
	 * 修改对象信息
	 * @param map
	 * @return
	 */
	public boolean updateEntity(Map<String, String> map);

}