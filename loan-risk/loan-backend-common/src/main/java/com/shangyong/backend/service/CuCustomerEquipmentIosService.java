package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.approval.CustomerEquipmentIos;

public interface CuCustomerEquipmentIosService {
	
	/**
	 * 根据客户编号查询所有IOS设备信息
	 * @return
	 */
	public List<CustomerEquipmentIos> findListById(String customerId);
	
	
	/**
	 * 
	 * 统计 （根据客户编号统计）
	 * 
	 **/
	public int listAllCount(String customerId);
	
	/** 查询设备通过记录号**/
	public CustomerEquipmentIos findEntityById(String equipmentId);
}
