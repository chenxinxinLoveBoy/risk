package com.shangyong.backend.service;
 
import java.util.List;

import com.shangyong.backend.entity.CuCustomerDirectories;

public interface CuCustomerDirectoriesService {
	
	/**
	 * 查询用户手机通讯录记录信息
	 * @param cuCustomerDirectories
	 * @return
	 */
	public List<CuCustomerDirectories> getEntityById(CuCustomerDirectories cuCustomerDirectories);
	
	/**
	 * 统计
	 * @param cuCustomerDirectories
	 * @return
	 */
	public int listAllCount(CuCustomerDirectories cuCustomerDirectories); 

}
