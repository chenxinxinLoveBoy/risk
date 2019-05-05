package com.shangyong.backend.dubbo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dubbo.MobileMessageRecolrdDubboService;
import com.shangyong.backend.entity.CuAppInfo;
import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.entity.CuCustomerEquipmentAndroid;
import com.shangyong.backend.entity.CuCustomerEquipmentIos;
import com.shangyong.backend.service.MobileMessageRecordService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.DingdingUtil;

/**
 * 客户信息采集
 * @author hxf
 * @date 2017/8/6
 **/
@Service(version = "1.0.0")
public class MobileMessageRecolrdServiceImpl implements MobileMessageRecolrdDubboService {

	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("mobileMessageRecolrdServiceImpl");
	
	@Autowired
	private MobileMessageRecordService mobileMessageRecordService;
	
	@Override
	public Map<String, Object> appMessageSave(List<CuAppInfo> cuAppInfo, List<CuCustomerDirectories> cuCustomerDirectories,
			CuCustomerEquipmentAndroid cuCustomerEquipmentAndroid, CuCustomerEquipmentIos cuCustomerEquipmentIos) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 客户信息采集系统调取MobileMessageRecolrdServiceImpl【客户应用列表、客户通讯录、客户设备环境信息】服务开始---");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", true);
		//客户手机应用列表处理
		if (CollectionUtils.isNotEmpty(cuAppInfo)) {
			logger.info("MobileMessageRecolrdService接口cuAppInfo的条数:"+cuAppInfo.size());
			try {
				String returnMessage = mobileMessageRecordService.saveCuAppInfo(cuAppInfo);
				if (returnMessage != null) {
					map.put("result", false);
					map.put("errorObject", cuAppInfo);
					map.put("errorMessage", returnMessage);
					return map;
				}
			} catch (Exception e) {
				map.put("result", false);
				map.put("errorObject", cuAppInfo);
				map.put("errorMessage", e.getMessage());
				DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 传入平台客户编号：" + cuAppInfo.get(0).getCustomerId() + "; 错误信息：" + e.getMessage());
				logger.error(e.getMessage());
				return map;
			}
		}
		//客户手机通讯录处理
		if (CollectionUtils.isNotEmpty(cuCustomerDirectories)) {
			logger.info("MobileMessageRecolrdService接口cuCustomerDirectories的条数:"+cuCustomerDirectories.size());
			try {
				String returnMessage = mobileMessageRecordService.saveCuCustomerDirectories(cuCustomerDirectories);
				if (returnMessage != null) {
					map.put("result", false);
					map.put("errorObject", cuCustomerDirectories);
					map.put("errorMessage", returnMessage);
					return map;
				}
			} catch (Exception e) {
				map.put("result", false);
				map.put("errorObject", cuCustomerDirectories);
				map.put("errorMessage", e.getMessage());
				DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 传入平台客户编号：" + cuCustomerDirectories.get(0).getCustomerId() + "; 错误信息：" + e.getMessage());
				logger.error(e.getMessage());
				return map;
			}
		}
		//安卓设备环境信息处理
		if (cuCustomerEquipmentAndroid != null) {
			try {
				logger.info("MobileMessageRecolrdService接口cuCustomerEquipmentAndroid数据处理customerId="+cuCustomerEquipmentAndroid.getCustomerId());
				String returnMessage = mobileMessageRecordService.saveCuCustomerEquipmentAndroid(cuCustomerEquipmentAndroid);
				if (returnMessage != null) {
					map.put("result", false);
					map.put("errorObject", cuCustomerEquipmentAndroid);
					map.put("errorMessage", returnMessage);
					return map;
				}
			} catch (Exception e) {
				map.put("result", false);
				map.put("errorObject", cuCustomerEquipmentAndroid);
				map.put("errorMessage", e.getMessage());
				DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 传入平台客户编号：" + cuCustomerEquipmentAndroid.getCustomerId() + "; 错误信息：" + e.getMessage());
				logger.error(e.getMessage());
				return map;
			}
		}
		//IOS设备环境信息处理
		if (cuCustomerEquipmentIos != null) {
			try {
				logger.info("MobileMessageRecolrdService接口cuCustomerEquipmentIos数据处理customerId="+cuCustomerEquipmentIos.getCustomerId());
				String returnMessage = mobileMessageRecordService.saveCuCustomerEquipmentIos(cuCustomerEquipmentIos);
				if (returnMessage != null) {
					map.put("result", false);
					map.put("errorObject", cuCustomerEquipmentIos);
					map.put("errorMessage", returnMessage);
					return map;
				}
			} catch (Exception e) {
				map.put("result", false);
				map.put("errorObject", cuCustomerEquipmentIos);
				map.put("errorMessage", e.getMessage());
				DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 传入平台客户编号：" + cuCustomerEquipmentIos.getCustomerId() + "; 错误信息：" + e.getMessage());
				logger.error(e.getMessage());
				return map;
			}
		}
		return map;
	}

}
