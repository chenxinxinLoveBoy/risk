package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.approval.CustomerEquipmentAndroid;

public interface CuCustomerEquipmentAndroidService {
	
	/**
	 * 根据客户编号查询所有Android设备信息
	 * @return
	 */
	public List<CustomerEquipmentAndroid> findListById(String customerId);
	
	
	/**
	 * 
	 * 统计 （根据客户编号统计）
	 * 
	 **/
	public int listAllCount(String customerId);
	
	/** 查询设备通过记录号**/
	public CustomerEquipmentAndroid findEntityById(String equipmentId);
}
