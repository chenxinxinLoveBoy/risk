package com.shangyong.backend.service;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.CuCustomerCheckManage;

public interface CuCustomerCheckManageService {

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(CuCustomerCheckManage record);
	
	/**
	 * 根据条件查询
	 * @return
	 */
	Page<CuCustomerCheckManage> findAllByObj(CuCustomerCheckManage record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	int listCountAllByObj(CuCustomerCheckManage record);
}
