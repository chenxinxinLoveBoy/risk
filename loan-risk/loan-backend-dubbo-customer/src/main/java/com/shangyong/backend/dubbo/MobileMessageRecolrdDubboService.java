package com.shangyong.backend.dubbo;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.entity.CuCustomerEquipmentAndroid;
import com.shangyong.backend.entity.CuCustomerEquipmentIos;

/**
 * 客户信息采集
 * @author hxf
 * @date 2017/8/6
 **/
public interface MobileMessageRecolrdDubboService {
	/**
	 * 客户信息采集
	 * @param cuAppInfo 客户手机应用列表记录
	 * @param cuCustomerDirectories 用户手机通讯录记录
	 * @param cuCustomerEquipmentAndroid 安卓设备环境信息
	 * @param cuCustomerEquipmentIos IOS设备环境信息
	 * @return Map<String, Object>
	 */
	public Map<String, Object> appMessageSave(List<CuAppInfo> cuAppInfo, List<CuCustomerDirectories> cuCustomerDirectories, CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid, CuCustomerEquipmentIos cuCustomerEquipmentIos);
}
