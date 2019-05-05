package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.dao.CuAppInfoDao;
import com.shangyong.backend.dao.CuCustomerDirectoriesDao;
import com.shangyong.backend.dao.CuCustomerEquipmentAndroidDao;
import com.shangyong.backend.dao.CuCustomerEquipmentIosDao;
import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.entity.CuCustomerEquipmentAndroid;
import com.shangyong.backend.entity.CuCustomerEquipmentIos;
import com.shangyong.backend.service.MobileMessageRecordService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.common.enums.DirectoriesCodeEnum;
import com.shangyong.backend.common.consts.DirectoriesRuleConst;
import com.shangyong.backend.utils.EmojiUtil;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;

@Service
public class MobileMessageRecordServiceImpl implements MobileMessageRecordService {
	
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("MobileMessageRecolrdServiceImpl");

	@Autowired
	private CuAppInfoDao cuAppInfoDao;
	
	@Autowired
	private CuCustomerDirectoriesDao cuCustomerDirectoriesDao;
	
	@Autowired
	private CuCustomerEquipmentAndroidDao cuCustomerEquipmentAndroidDao;
	
	@Autowired
	private CuCustomerEquipmentIosDao cuCustomerEquipmentIosDao;
	
	@Override
	@Transactional
	public String saveCuAppInfo(List<CuAppInfo> cuAppInfo) {
		CuAppInfo cuapp = cuAppInfo.get(0);
		String returnMessage = this.checkCuAppInfo(cuapp);
		if (returnMessage != null) {
			return returnMessage;
		}
		List<CuAppInfo> list = cuAppInfoDao.findAppInfo(cuapp);
		if (CollectionUtils.isNotEmpty(list)) {
			logger.info("数据已存在，不做入库操作；cuAppInfo："+JSON.toJSONString(cuapp));
			return null;
		}
		
		List<CuAppInfo> cuAppInfoList = new ArrayList<CuAppInfo>();
		int size = cuAppInfo.size();
		for (int j = 0; j < size; j++) {
			CuAppInfo cu = cuAppInfo.get(j);
			returnMessage = checkCuAppInfo(cu);
			if (returnMessage != null) {
				continue;
			}
			cu.setAppInfoId(UUIDUtils.getUUID());
			cu.setCreateTime(DateUtils.getDate(new Date()));
			cu.setModifyTime(DateUtils.getDate(new Date()));
			cuAppInfoList.add(cu);
			if (j%100 == 0 || j == (size - 1)) {
				cuAppInfoDao.saveAllEntity(cuAppInfoList);
				cuAppInfoList.clear();
			}
		}
		logger.info("MobileMessageRecordService接口cuAppInfo信息保存完成");
		return null;
	}

	@Override
	@Transactional
	public String saveCuCustomerDirectories(List<CuCustomerDirectories> cuCustomerDirectories) {
		CuCustomerDirectories cuCust = cuCustomerDirectories.get(0);
		String returnMessage = this.checkCuCustomerDirectories(cuCust);
		if (returnMessage != null) {
			return returnMessage;
		}
		List<CuCustomerDirectories> list = cuCustomerDirectoriesDao.findCustomerDirectories(cuCust);
		if (CollectionUtils.isNotEmpty(list)) {
			logger.info("数据已存在，不做入库操作；CuCustomerDirectories："+JSON.toJSONString(cuCust));
			return null;
		}
		List<CuCustomerDirectories> cuDirectories = this.checkNameAndPhone(cuCustomerDirectories);
		List<CuCustomerDirectories> directoriesList = new ArrayList<CuCustomerDirectories>();
		int size = cuDirectories.size();
		for (int i = 0; i < size; i++) {
			CuCustomerDirectories cu = cuDirectories.get(i);
			returnMessage = this.checkCuCustomerDirectories(cu);
			if (returnMessage != null) {
				continue;
			}
			if (StringUtil.checkNotNull(cu.getContactName())) {
				String contactName = EmojiUtil.emojiConverterToAlias(cu.getContactName());
				cu.setContactName(contactName);
			}
			cu.setDirectoriesId(UUIDUtils.getUUID());
			cu.setCreateTime(DateUtils.getDate(new Date()));
			cu.setModifyTime(DateUtils.getDate(new Date()));
			directoriesList.add(cu);
			if (i%100 == 0 || i == (size - 1)) {
				cuCustomerDirectoriesDao.saveAllEntity(directoriesList);
				directoriesList.clear();
			}
		}
		logger.info("MobileMessageRecordService接口cuCustomerDirectories信息保存完成");
		return null;
	}

	@Override
	public String saveCuCustomerEquipmentAndroid(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid) {
		String returnMessage = this.checkCuCustomerEquipmentAndroid(cuCustomerEquipmentAndroid);
		if (returnMessage != null) {
			return returnMessage;
		}
		CuCustomerEquipmentAndroid android = cuCustomerEquipmentAndroidDao.findCuCustomerEquipmentAndroid(cuCustomerEquipmentAndroid);
		if (android != null) {
			logger.info("数据已存在，不做入库操作；cuCustomerEquipmentAndroid："+JSON.toJSONString(cuCustomerEquipmentAndroid));
			return null;
		}
		cuCustomerEquipmentAndroid.setEquipmentId(UUIDUtils.getUUID());
		cuCustomerEquipmentAndroid.setCreateTime(DateUtils.getDate(new Date()));
		cuCustomerEquipmentAndroid.setModifyTime(DateUtils.getDate(new Date()));
		cuCustomerEquipmentAndroidDao.saveEntity(cuCustomerEquipmentAndroid);
		logger.info("MobileMessageRecordService接口cuCustomerEquipmentAndroid信息保存完成");
		return null;
	}

	@Override
	public String saveCuCustomerEquipmentIos(CuCustomerEquipmentIos cuCustomerEquipmentIos) {
		String returnMessage = this.checkCuCustomerEquipmentIos(cuCustomerEquipmentIos);
		if (returnMessage != null) {
			return returnMessage;
		}
		CuCustomerEquipmentIos ios = cuCustomerEquipmentIosDao.findCuCustomerEquipmentIos(cuCustomerEquipmentIos);
		if (ios != null) {
			logger.info("数据已存在，不做入库操作；CuCustomerDirectories："+JSON.toJSONString(cuCustomerEquipmentIos));
			return null;
		}
		cuCustomerEquipmentIos.setEquipmentId(UUIDUtils.getUUID());
		cuCustomerEquipmentIos.setCreateTime(DateUtils.getDate(new Date()));
		cuCustomerEquipmentIos.setModifyTime(DateUtils.getDate(new Date()));
		cuCustomerEquipmentIosDao.saveEntity(cuCustomerEquipmentIos);
		logger.info("MobileMessageRecordService接口cuCustomerEquipmentIos信息保存完成");
		return null;
	}

	/**
	 * 客户手机应用cuAppInfo校验
	 * @param cuAppInfo
	 * @return
	 */
	private String checkCuAppInfo(CuAppInfo cuAppInfo){
		if (cuAppInfo == null) {
			return "cuAppInfo不能为null";
		}
		if (cuAppInfo.getAppName() == null) {
			return "appName不能为null";
		}
		if (cuAppInfo.getCustomerId() == null) {
			return "customerId不能为null";
		}
		return null;
	}
	
	/**
	 * 手机通讯录cuCustomerDirectories校验
	 * @param cuCustomerDirectories
	 * @return
	 */
	private String checkCuCustomerDirectories(CuCustomerDirectories cuCustomerDirectories){
		if (cuCustomerDirectories == null) {
			return "cuCustomerDirectories不能为null";
		}
		if (cuCustomerDirectories.getAppName() == null) {
			return "appName不能为null";
		}
		if (cuCustomerDirectories.getCustomerId() == null) {
			return "不能为null";
		}
		return null;
	}
	
	/**
	 * 通讯录中联系人状态判断
	 * @param list
	 */
	private List<CuCustomerDirectories> checkNameAndPhone(List<CuCustomerDirectories> list){

		Map<CuCustomerDirectories, CuCustomerDirectories> map = checkContactName(list);
		List<CuCustomerDirectories> directorieslist = new ArrayList<CuCustomerDirectories>();
		for (CuCustomerDirectories customerDirectories : map.values()) {
			String extend = customerDirectories.getExtend();
			String name = customerDirectories.getContactName();
			String phone = customerDirectories.getContactPhone();
			String errorMessage = null;
			/*异类号码逻辑处理*/
			if (!StringUtil.checkNotNull(name)) {
				if (StringUtil.checkNotNull(extend)) {
					extend += "," +DirectoriesRuleConst.DIRECTORIES_NAME_IS_NULL;
				} else {
					extend = DirectoriesRuleConst.DIRECTORIES_NAME_IS_NULL;
				}
			} else {
				boolean result=name.matches("[0-9]+");
				if (result) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleConst.DIRECTORIES_NAME_IS_NUM;
					} else {
						extend = DirectoriesRuleConst.DIRECTORIES_NAME_IS_NUM;
					}
				}

				/*异常号码逻辑处理*/
				for (DirectoriesCodeEnum dire : DirectoriesCodeEnum.values()) {
					if (name.indexOf(dire.getMessage())!=-1) {
						if (StringUtil.checkNotNull(errorMessage)) {
							errorMessage += "," + dire.getMessage();
						} else {
							errorMessage = dire.getMessage();
						}
					}
				}
			}

			if (!StringUtil.checkNotNull(phone)) {
				if (StringUtil.checkNotNull(extend)) {
					extend += "," +DirectoriesRuleConst.DIRECTORIES_PHONE_IS_NULL;
				} else {
					extend = DirectoriesRuleConst.DIRECTORIES_PHONE_IS_NULL;
				}
			} else {
				if (phone.length()>12 || phone.length()<8) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleConst.DIRECTORIES_PHONE_IS_IRRATIONAL;
					} else {
						extend = DirectoriesRuleConst.DIRECTORIES_PHONE_IS_IRRATIONAL;
					}
				}
			}
			if (StringUtil.checkNotNull(extend)) {
				customerDirectories.setDirectoriesState(2);
				extend = "异类信息:" +extend;
			} else {
				customerDirectories.setDirectoriesState(1);
			}

			if (StringUtil.checkNotNull(errorMessage)) {
				customerDirectories.setDirectoriesState(3);
			}
			if (StringUtil.checkNotNull(errorMessage) && StringUtil.checkNotNull(extend)) {
				customerDirectories.setDirectoriesState(4);
				extend = extend + ";异常信息:" + errorMessage;
			} else if (StringUtil.checkNotNull(errorMessage)){
				extend = errorMessage;
			}
			customerDirectories.setExtend(extend);
			directorieslist.add(customerDirectories);
		}
		return directorieslist;
	}
	
	/**
	 * 姓名。同一姓名出现3次及以上的
	 */
	private Map<CuCustomerDirectories, CuCustomerDirectories> checkContactName(List<CuCustomerDirectories> list){
		Map<CuCustomerDirectories, CuCustomerDirectories> map = new HashMap<CuCustomerDirectories, CuCustomerDirectories>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i), list.get(i));
		}
		for (CuCustomerDirectories dire : map.keySet()) {
			String name = dire.getContactName();
			int nameNum = 0;
			for (CuCustomerDirectories cuCustomerDirectories : list) {
				if (StringUtils.isEquals(name, cuCustomerDirectories.getContactName())) {
					nameNum++;
				}
			}
			if (nameNum>=3) {
				for (CuCustomerDirectories dir : map.keySet()) {
					if (StringUtils.isEquals(name, dir.getContactName())) {
						CuCustomerDirectories c = dir;
						String extend = DirectoriesRuleConst.DIRECTORIES_NAME_IS_MORE;
						dir.setExtend(extend);
						map.put(c, dir);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 安卓设备环境信息cuCustomerEquipmentAndroid校验
	 * @param cuCustomerEquipmentAndroid
	 * @return
	 */
	private String checkCuCustomerEquipmentAndroid(CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid){
		if (cuCustomerEquipmentAndroid == null) {
			return "cuCustomerDirectories不能为null";
		}
		if (cuCustomerEquipmentAndroid.getAppName() == null) {
			return "appName不能为null";
		}
		if (cuCustomerEquipmentAndroid.getCustomerId() == null) {
			return "customerId不能为null";
		}
		if (cuCustomerEquipmentAndroid.getDeviceId() == null) {
			return "customerId不能为null";
		}
		return null;
	}
	
	/**
	 * IOS设备环境信息cuCustomerEquipmentIos校验
	 * @param cuCustomerEquipmentIos
	 * @return
	 */
	private String checkCuCustomerEquipmentIos(CuCustomerEquipmentIos cuCustomerEquipmentIos){
		if (cuCustomerEquipmentIos == null) {
			return "cuCustomerDirectories不能为null";
		}
		if (cuCustomerEquipmentIos.getAppName() == null) {
			return "appName不能为null";
		}
		if (cuCustomerEquipmentIos.getCustomerId() == null) {
			return "customerId不能为null";
		}
		if (cuCustomerEquipmentIos.getDeviceId() == null) {
			return "customerId不能为null";
		}
		return null;
	}
}
