package com.shangyong.backend.service.approval.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.shangyong.backend.entity.approval.CustomerDirectories;

/**
 * 通讯录
 * @author hxf
 * @date 2017/8/10
 **/
public interface CustomerDirectoriesService {

	/**
	 * 通过客户编号查询通讯录信息
	 */
	public LinkedHashMap<String, Object> findAllcustomerDirectories(String customerId, String appName);

	/**
	 * 通过客户编号查询通讯录信息
	 */
	public LinkedHashMap<String, Object> regulateDirectories(List<CustomerDirectories> directories);

	/**
	 * 通过客户编号查询正常通讯录数量
	 * @param directories
	 * @return
	 */
	public int regulateDirectoriesNelist(List<CustomerDirectories> directories);

	/**
	 * 通过客户编号查询正常通讯录数量(准入)
	 * @param applicationId
	 * @param customerId
	 * @param directories
	 * @return
	 */
	public int regulateDirectoriesByNelist(String applicationId, String customerId, List<CustomerDirectories> directories);
}
