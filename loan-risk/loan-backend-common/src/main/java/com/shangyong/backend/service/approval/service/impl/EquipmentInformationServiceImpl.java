package com.shangyong.backend.service.approval.service.impl;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.common.BQSConstant;
import com.shangyong.backend.dao.IdEntityCardDao;
import com.shangyong.backend.dao.approval.CustomerEquipmentAndroidDao;
import com.shangyong.backend.dao.approval.CustomerEquipmentIosDao;
import com.shangyong.backend.dao.bqsrep.BqsRepContactsInfoServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepCrossValidationServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepPetitionerServiceDao;
import com.shangyong.backend.entity.approval.Address;
import com.shangyong.backend.entity.approval.ContactInfo;
import com.shangyong.backend.entity.approval.CustomerEquipmentAndroid;
import com.shangyong.backend.entity.approval.CustomerEquipmentIos;
import com.shangyong.backend.entity.approval.EquipmentInformation;
import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;
import com.shangyong.backend.entity.bqsrep.BqsRepPetitioner;
import com.shangyong.backend.service.approval.service.EquipmentInformationService;
import com.shangyong.utils.DateUtils;

@Service
public class EquipmentInformationServiceImpl implements EquipmentInformationService {

	@Autowired
	private CustomerEquipmentAndroidDao customerEquipmentAndroidDao;

	@Autowired
	private CustomerEquipmentIosDao customerEquipmentIosDao;
	
	@Autowired
	private IdEntityCardDao idEntityCardDao;
	
	@Autowired
	private BqsRepPetitionerServiceDao bqsRepPetitionerServiceDao;
	
	@Autowired
	private BqsRepContactsInfoServiceDao bqsRepContactsInfoServiceDao;
	
	@Autowired
	private BqsRepCrossValidationServiceDao bqsRepCrossValidationServiceDao;//白骑士

	@Override
	@Transactional
	public EquipmentInformation findEquipmentInformation(String customerId,String appName) throws Exception {

		//设备信息汇总bean
		EquipmentInformation equipmentInformation = null;
		long iosCreateTime = 0;
		long androidCreateTime = 0;
		//根据APP客户编号customerId查询客户的ios查询最早的设备信息（gprs_ip,create_time,equipment_type）
		CustomerEquipmentIos ios = customerEquipmentIosDao.findFirstOneByCustomerId(customerId,appName);
		//根据APP客户编号customerId查询客户的android查询最早的设备信息（gprs_ip,create_time,equipment_type）
		CustomerEquipmentAndroid android = customerEquipmentAndroidDao.findFirstOneByCustomerId(customerId,appName);
		if (ios != null) {
			iosCreateTime = DateUtils.convertStringToDate(DateUtils.dateTimePattern, ios.getCreateTime()).getTime();
		}
		if (android != null) {
			androidCreateTime = DateUtils.convertStringToDate(DateUtils.dateTimePattern, android.getCreateTime()).getTime();
		}

		String[] gprs = null;
		if (iosCreateTime == 0 && androidCreateTime == 0) {
			return equipmentInformation;
		} else if (iosCreateTime == 0) {
			gprs = getGprs(android.getGprsIp());
			return this.perfectEquipmentInformation(android.getBrand(), gprs);
		} else if (androidCreateTime == 0) {
			gprs = getGprs(ios.getGprsIp());
			return this.perfectEquipmentInformation(ios.getEquipmentType(), gprs);
		}
		if (androidCreateTime > iosCreateTime) {
			gprs = getGprs(ios.getGprsIp());
			equipmentInformation = this.perfectEquipmentInformation(ios.getEquipmentType(), gprs);
		} else {
			gprs = getGprs(ios.getGprsIp());
			equipmentInformation = this.perfectEquipmentInformation(android.getBrand(), gprs);
		}
		return equipmentInformation;
		
//		int androidNum = listAndroid.size();
//		int iosNum = listIos.size();
//		//放入关联设备数
//		equipmentInformation.setEquipmentNum(androidNum+iosNum);
//		
//		CustomerEquipmentAndroid customerEquipmentAndroid = new CustomerEquipmentAndroid();
//		CustomerEquipmentIos customerEquipmentIos = new CustomerEquipmentIos();
//		Date androidDate = null;
//		Date iosDate = null;
//		Set<String> set = new HashSet<String>();
//		Map<String, Object> map = getEquipmentInformationByDeviceId(listAndroid, listIos);
//		List<CustomerEquipmentAndroid> androidList = (List<CustomerEquipmentAndroid>) map.get("androidList");
//		List<CustomerEquipmentIos> iosList = (List<CustomerEquipmentIos>) map.get("iosList");
//		if (CollectionUtils.isNotEmpty(androidList)) {
//			customerEquipmentAndroid = listAndroid.get(0);
//			androidDate = DateUtils.convertStringToDate(DateUtils.dateTimePattern, customerEquipmentAndroid.getCreateTime());
//			for (CustomerEquipmentAndroid android : androidList) {
//				set.add(android.getCustomerId());
//			}
//		}
//		if (CollectionUtils.isNotEmpty(iosList)) {
//			customerEquipmentIos = listIos.get(0);
//			iosDate = DateUtils.convertStringToDate(DateUtils.dateTimePattern, customerEquipmentIos.getCreateTime());
//			for (CustomerEquipmentIos ios : iosList) {
//				set.add(ios.getCustomerId());
//			}
//		}
//		//放入案件数（客户关联设备的所有使用人数）
//		equipmentInformation.setApplicationNum(set.size());
//		
//		//放入设备名称（取最早注册的设备名称）和gprs地址
//		String[] gprs = null;
//		if (androidDate != null && iosDate != null) {
//			if (androidDate.getTime()>iosDate.getTime()) {
//				equipmentInformation.setEquipmentName(customerEquipmentIos.getEquipmentType());
//				if (StringUtils.isNotBlank(customerEquipmentIos.getGprsIp())) {
//					gprs = customerEquipmentIos.getGprsIp().split(",");
//				}
//			} else {
//				equipmentInformation.setEquipmentName(customerEquipmentAndroid.getPhoneModel());
//				if (StringUtils.isNotBlank(customerEquipmentAndroid.getGprsIp())) {
//					gprs = customerEquipmentAndroid.getGprsIp().split(",");
//				}
//			}
//		} else if (iosDate != null) {
//			equipmentInformation.setEquipmentName(customerEquipmentIos.getEquipmentType());
//			if (StringUtils.isNotBlank(customerEquipmentIos.getGprsIp())) {
//				gprs = customerEquipmentIos.getGprsIp().split(",");
//			}
//		} else if (androidDate != null) {
//			equipmentInformation.setEquipmentName(customerEquipmentAndroid.getPhoneModel());
//			if (StringUtils.isNotBlank(customerEquipmentAndroid.getGprsIp())) {
//				gprs = customerEquipmentAndroid.getGprsIp().split(",");
//			}
//		}
//		if (gprs != null && gprs.length > 1) {
//			equipmentInformation.setGprsjingIp(gprs[0]);
//			equipmentInformation.setGprsWeiIp(gprs[1]);
//		}
	}

	@Override
	public List<EquipmentInformation> findEquipmentInformationInfo(String customerId, String appName) throws Exception {
		//根据APP客户编号customerId查询客户所有的ios设备信息
		List<CustomerEquipmentIos> listIos = customerEquipmentIosDao.findAllByCustomerId(customerId,appName);
		//根据APP客户编号customerId查询客户所有的android设备信息
		List<CustomerEquipmentAndroid> listAndroid = customerEquipmentAndroidDao.findAllByCustomerId(customerId,appName);
		
		List<EquipmentInformation> list = new ArrayList<EquipmentInformation>();
		if (CollectionUtils.isNotEmpty(listAndroid)) {
			for (CustomerEquipmentAndroid android : listAndroid) {
				EquipmentInformation equipmentInformation = new EquipmentInformation();
				equipmentInformation.setCustomerId(android.getCustomerId());
				equipmentInformation.setDeviceId(android.getDeviceId());
				list.add(equipmentInformation);
			}
		}
		if (CollectionUtils.isNotEmpty(listIos)) {
			for (CustomerEquipmentIos ios : listIos) {
				EquipmentInformation equipmentInformation = new EquipmentInformation();
				equipmentInformation.setCustomerId(ios.getCustomerId());
				equipmentInformation.setDeviceId(ios.getDeviceId());
				list.add(equipmentInformation);
			}
		}
		return list;
	}

	@Override
	public List<EquipmentInformation> findAjInfo(String customerId, String appName) throws Exception {
		//根据APP客户编号customerId查询客户所有的android设备信息
		List<CustomerEquipmentAndroid> listAndroid = customerEquipmentAndroidDao.findAllByCustomerId(customerId,appName);
		//根据APP客户编号customerId查询客户所有的ios设备信息
		List<CustomerEquipmentIos> listIos = customerEquipmentIosDao.findAllByCustomerId(customerId,appName);
		
		List<EquipmentInformation> list = new ArrayList<EquipmentInformation>();
		Map<String, Object> map = getEquipmentInformationByDeviceId(listAndroid, listIos);
		List<CustomerEquipmentAndroid> androidList = (List<CustomerEquipmentAndroid>) map.get("androidList");
		List<CustomerEquipmentIos> iosList = (List<CustomerEquipmentIos>) map.get("iosList");
		if (CollectionUtils.isNotEmpty(androidList)) {
			for (CustomerEquipmentAndroid android : androidList) {
				EquipmentInformation equipment = new EquipmentInformation();
				equipment.setCustomerId(android.getCustomerId());
				equipment.setDeviceId(android.getDeviceId());
				list.add(equipment);
			}
		}
		if (CollectionUtils.isNotEmpty(iosList)) {
			for (CustomerEquipmentIos ios : iosList) {
				EquipmentInformation equipment = new EquipmentInformation();
				equipment.setCustomerId(ios.getCustomerId());
				equipment.setDeviceId(ios.getDeviceId());
				list.add(equipment);
			}
		}
		return list;
	}
	
	/**
	 * 通过deviceId获取设备信息
	 * @param list1(android信息)
	 * @param list2(ios信息)
	 * @return
	 */
	private Map<String,Object> getEquipmentInformationByDeviceId(List<CustomerEquipmentAndroid> list1,List<CustomerEquipmentIos> list2){
		Map<String,Object> map = new HashMap<String,Object>();
		//拼装所有的android设备的deviceId
		if (CollectionUtils.isNotEmpty(list1)) {
			List<String> array = new ArrayList<String>();
			for (int i = 0; i < list1.size(); i++) {
				array.add(list1.get(i).getDeviceId());
			}
			//根据APP客户编号device_id查询
			List<CustomerEquipmentAndroid> androidList = customerEquipmentAndroidDao.findAllByDeviceId(array);
			map.put("androidList", androidList);
		}
		if (CollectionUtils.isNotEmpty(list2)) {
			List<String> array = new ArrayList<String>();
			//拼装所有的ios设备的deviceId
			for (int i = 0; i < list2.size(); i++) {
				array.add(list2.get(i).getDeviceId());
			}
			//根据APP客户编号device_id查询
			List<CustomerEquipmentIos> iosList = customerEquipmentIosDao.findAllByDeviceId(array);
			map.put("iosList", iosList);
		}
		return map;
	}

	@Override
	public List<EquipmentInformation> queryAjInfo(String customerId, String appName) throws Exception { 
		//根据APP客户编号customerId查询客户所有的ios设备信息
				List<CustomerEquipmentIos> listIos = customerEquipmentIosDao.findByCustomerId(customerId,appName);
				//根据APP客户编号customerId查询客户所有的android设备信息
				List<CustomerEquipmentAndroid> listAndroid = customerEquipmentAndroidDao.findByCustomerId(customerId,appName);
				
				List<EquipmentInformation> list = new ArrayList<EquipmentInformation>();
				if (CollectionUtils.isNotEmpty(listAndroid) && !listAndroid.contains(null)) {
					for (CustomerEquipmentAndroid android : listAndroid) {
						EquipmentInformation equipmentInformation = new EquipmentInformation();
						String cAndrId = android.getCustomerId();
						String cDevAndrId = android.getDeviceId();
						if(StringUtils.isNotBlank(cAndrId)){
							equipmentInformation.setCustomerId(cAndrId);
							equipmentInformation.setDeviceId(cDevAndrId);
							list.add(equipmentInformation);
						} 
					}
				}
				if (CollectionUtils.isNotEmpty(listIos) && !listIos.contains(null)) {
					for (CustomerEquipmentIos ios : listIos) {
						EquipmentInformation equipmentInformation = new EquipmentInformation();
						String cIosId  =  ios.getCustomerId();
						String cDevIosId  =  ios.getDeviceId();
						if(StringUtils.isNotBlank(cIosId)){
							equipmentInformation.setCustomerId(cIosId);
							equipmentInformation.setDeviceId(cDevIosId);
							list.add(equipmentInformation);
						}
						  
					}
				}
				return list;
	}

	private static String[] getGprs(String gprsId) {
		String[] gprs = null;
		if (StringUtils.isNotBlank(gprsId)) {
			gprs = gprsId.split(",");
		}
		return gprs;
	}

	private static EquipmentInformation perfectEquipmentInformation(String equipmentName, String[] gprs) {
		EquipmentInformation equipmentInformation = new EquipmentInformation();
		if (StringUtils.isNotBlank(equipmentName)) {
 			equipmentInformation.setEquipmentName(equipmentName);
		}
		if (gprs != null && gprs.length == 2) {
			equipmentInformation.setGprsjingIp(gprs[0]);
			equipmentInformation.setGprsWeiIp(gprs[1]);
		}
		return equipmentInformation;
	}
	
	/**
	 * 身份证及手机号码归属地查询
	 */
	@Override
	public Address queryMgUserMessage(String certCode1, String phone) {
		Address add = new Address();
		String code = certCode1.substring(0, 6);
		//身份证归属地查询
		String address = idEntityCardDao.getEntityById(code);
		if (!StringUtils.isBlank(address)) {
			add.setSfzAddress(address);
		}else {
			code = code.substring(0, 2)+"0000";
			address = idEntityCardDao.getEntityById(code);
			add.setSfzAddress(address);
		}
		//根据白骑士
		String phoneAdress = bqsRepPetitionerServiceDao.phoneAdress(phone);
		add.setPhoneAddress(phoneAdress);
		return add;
	}

	@Override
	public List<BqsRepContactsInfo> queryContact(String applicationId) {
		List<ContactInfo> list = new ArrayList<ContactInfo>();
		BqsRepPetitioner petitioner = bqsRepPetitionerServiceDao.queryById(applicationId);
		if(null == petitioner){
			return Collections.EMPTY_LIST;
		}
		List<BqsRepContactsInfo> bqsRepContactsInfos = bqsRepContactsInfoServiceDao.queryListById(petitioner.getBqsPetitionerId());
		return bqsRepContactsInfos;
	}

	/**
	 * 白骑士查询手机号码使用时长
	 */
	@Override
	public List<BqsRepCrossValidation> queryBqsRepCrossForPhoneNo(String applicationId) {
		BqsRepPetitioner bqsRepPetitioner = bqsRepPetitionerServiceDao.queryById(applicationId);
		if (bqsRepPetitioner == null || bqsRepPetitioner.getBqsPetitionerId() == null) {
			return null;
		}
		List<BqsRepCrossValidation> result = new ArrayList<>();

		String bqsPetitionerId = bqsRepPetitioner.getBqsPetitionerId();

		BqsRepCrossValidation bqsRepCrossValidation = new BqsRepCrossValidation();
		bqsRepCrossValidation.setBqsPetitionerId(bqsPetitionerId);
		bqsRepCrossValidation.setType(BQSConstant.NUMBERUSEDLONG); //号码使用时长
		BqsRepCrossValidation validation = bqsRepCrossValidationServiceDao.queryRWById(bqsRepCrossValidation);

		result.add(validation);

		//入网时间
		bqsRepCrossValidation.setType(BQSConstant.OPENTIME);

		BqsRepCrossValidation bqsRepCrossValidation2 = bqsRepCrossValidationServiceDao.queryRWById(bqsRepCrossValidation);

		result.add(bqsRepCrossValidation2);
		return result;
	}
}
