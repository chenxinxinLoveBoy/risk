package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.CustomerEquipmentAndroid;

/**
 * CuCustomerEquipmentAndroidDao数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:34 CST 2017
 **/
@Mapper
public interface CustomerEquipmentAndroidDao{

	/**
	 * 
	 * 统计 （根据客户编号统计）
	 * 
	 **/
	int listAllCount(@Param("customerId") String customerId);
	
	/** 查询设备（最早的1条）**/
	CustomerEquipmentAndroid findEquipmentNameAndroid(@Param("customerId") String customerId);
	
	/** 查询（根据APP客户编号customer_id查询）**/
	List<CustomerEquipmentAndroid> findAllByCustomerId(@Param("customerId") String customerId, @Param("appName") String appName);

	/** 查询最早的设备信息（根据APP客户编号customer_id查询gprs_ip,create_time,equipment_type）**/
	CustomerEquipmentAndroid findFirstOneByCustomerId(@Param("customerId") String customerId, @Param("appName") String appName);

	/** 查询（根据APP客户编号device_id查询）**/
	List<CustomerEquipmentAndroid> findAllByDeviceId(@Param("deviceId") List<String> deviceId);
	
	/**
	 * 根据客户编号查所有
	 * @param customerId
	 * @return
	 */
	List<CustomerEquipmentAndroid> findListById(String customerId);
	
	/** 查询设备通过记录号**/
	CustomerEquipmentAndroid findEntityById(String equipmentId);

	/**
	 * 去除重复查询
	 * @param customerId
	 * @param appName
	 * @return
	 */
	List<CustomerEquipmentAndroid> findByCustomerId(@Param("customerId") String customerId, @Param("appName") String appName);
}