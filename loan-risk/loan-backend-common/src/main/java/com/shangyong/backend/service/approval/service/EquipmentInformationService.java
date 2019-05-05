package com.shangyong.backend.service.approval.service;

import java.util.List;

import com.shangyong.backend.entity.approval.Address;
import com.shangyong.backend.entity.approval.EquipmentInformation;
import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;

/**
 * 设备信息汇总
 * @author hxf
 * @date 2017/8/10
 **/
public interface EquipmentInformationService {

	/**
	 * 获取设备关联数和设备名称信息（取第一次注册的设备信息）
	 * @param customerId
	 * @param appName
	 * @return
	 * @throws Exception
	 */
	public EquipmentInformation findEquipmentInformation(String customerId, String appName) throws Exception;
	
	/**
	 * 通过客户编号查询客户使用的全部设备信息
	 * @param customerId
	 * @param appName
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentInformation> findEquipmentInformationInfo(String customerId, String appName) throws Exception;
	
	/**
	 * 通过客户编号查询设备关联情况信息
	 * @param customerId
	 * @param appName
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentInformation> findAjInfo(String customerId, String appName) throws Exception;
	
	/**
	 * 查询本人设备号
	 * @param customerId
	 * @param appName
	 * @return
	 * @throws Exception
	 */
	public List<EquipmentInformation> queryAjInfo(String customerId, String appName) throws Exception;

	/**
	 * 查询身份证归属地与手机归属地
	 * @param certCode1
	 * @param phone
	 * @return
	 */
	public Address queryMgUserMessage(String certCode1, String phone);

	/**
	 * 获取紧急联系人信息
	 * @return
	 */
	public List<BqsRepContactsInfo> queryContact(String applicationId);

	/**
	 * 查询手机使用时长 + 手机入网使用时长
	 * @param applicationId
	 * @return
	 */
	public List<BqsRepCrossValidation> queryBqsRepCrossForPhoneNo(String applicationId);
}
