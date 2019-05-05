package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.entity.CuCustomerEquipmentAndroid;
import com.shangyong.backend.entity.CuCustomerEquipmentIos;

/**
 * 客户信息采集
 * @author hxf
 * @date 2017/8/6
 **/
public interface MobileMessageRecordService {
	/**
	 * 客户手机应用列表处理
	 * @param cuAppInfo 应用列表集合
	 * @return String 错误信息返回
	 */
	public String saveCuAppInfo(List<CuAppInfo> cuAppInfo);

	/**
	 * 用户手机通讯录处理
	 * @param cuCustomerDirectories 通讯录集合
	 * @return String 错误信息返回
	 */
	public String saveCuCustomerDirectories(List<CuCustomerDirectories> cuCustomerDirectories);
	
	/**
	 * 安卓设备环境信息处理
	 * @param cuCustomerEquipmentAndroid 设备环境信息
	 * @return String 错误信息返回
	 */
	public String saveCuCustomerEquipmentAndroid(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid);
	
	/**
	 * IOS设备环境信息处理
	 * @param cuCustomerEquipmentIos 设备环境信息
	 * @return String 错误信息返回
	 */
	public String saveCuCustomerEquipmentIos(CuCustomerEquipmentIos cuCustomerEquipmentIos);
}
