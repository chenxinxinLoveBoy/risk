package com.shangyong.backend.service.approval.service;

/**
 * 同盾数据展示
 * @author hxf
 * @date 2017/9/20
 **/
public interface TDCustomerMessageService {

	/**
	 * 查询大数据短信汇总信息
	 * @param customerId
	 * @param appName
	 * @return
	 */
	public String summaryFewMessage(String customerId, String appName);

	/**
	 * 查询大数据app应用列表汇总信息
	 * @param customerId
	 * @param appName
	 * @return
	 */
	public String summaryAppInfo(String customerId, String appName);

	/**
	 * 查询大数据设备汇总信息
	 * @param customerId
	 * @param appName
	 * @return
	 */
	public String summaryEquipment(String customerId, String appName);
}
