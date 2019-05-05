package com.shangyong.backend.service.approval.service;

import com.shangyong.backend.entity.approval.CustomerEquipmentIos;

/**
 * 设备信息
 * @author hxf
 * @date 2017/8/10
 **/
public interface CustomerEquipmentIosService {

	/** 查询设备关联数Ios **/
	public int listAllCount(String customerId);
	
	/** 查询设备名称（最早的1条）**/
	public CustomerEquipmentIos findEquipmentNameIos(String customerId);
}
