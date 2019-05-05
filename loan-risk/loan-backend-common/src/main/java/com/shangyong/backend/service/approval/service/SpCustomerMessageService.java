package com.shangyong.backend.service.approval.service;

/**
 * 审批app推送短信，通讯录，通话记录，app列表 信息收集
 * @author hxf
 */
public interface SpCustomerMessageService {

	/**
	 * 客户通话数据入库
	 * @param json
	 * @return boolean
	 */
	public boolean saveCuCustomerCallRecord(String json);

	/**
	 * 客户短信数据入库
	 * @param json
	 * @return boolean
	 */
	public boolean saveCuCustomerFewMessage(String json);

	/**
	 * 客户手机应用列表数据入库
	 * @param json
	 * @return
	 */
	public boolean saveAppInfo(String json);

	/**
	 * 用户手机通讯录数据入库
	 * @param json
	 * @return
	 */
	public boolean saveCustomerDirectories(String json);
}
