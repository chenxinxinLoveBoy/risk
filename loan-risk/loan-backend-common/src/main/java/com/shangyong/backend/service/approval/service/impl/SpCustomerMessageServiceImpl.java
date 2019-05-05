package com.shangyong.backend.service.approval.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.dao.approval.AppInfoDao;
import com.shangyong.backend.dao.approval.CuCustomerCallRecordDao;
import com.shangyong.backend.dao.approval.CuCustomerCollectMessageDao;
import com.shangyong.backend.dao.approval.CuCustomerFewMessageDao;
import com.shangyong.backend.dao.approval.CustomerDirectoriesDao;
import com.shangyong.backend.entity.approval.AppInfo;
import com.shangyong.backend.entity.approval.CuCustomerCallRecord;
import com.shangyong.backend.entity.approval.CuCustomerCollectMessage;
import com.shangyong.backend.entity.approval.CuCustomerFewMessage;
import com.shangyong.backend.entity.approval.CustomerDirectories;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.approval.service.SpCustomerMessageService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.common.enums.DirectoriesCodeEnum;
import com.shangyong.backend.utils.DirectoriesRuleUtils;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONArray;

@Service
public class SpCustomerMessageServiceImpl implements SpCustomerMessageService {

	private static Logger logger = LoggerFactory.getLogger("rabbitmqSpCustomerMessage");

	@Autowired
	private CuCustomerCollectMessageDao cuCustomerCollectMessageDao;
	
	@Autowired
	private CuCustomerFewMessageDao cuCustomerFewMessageDao;

	@Autowired
	private CuCustomerCallRecordDao cuCustomerCallRecordDao;

	@Autowired
	private CustomerDirectoriesDao customerDirectoriesDao;
	
	@Autowired
	private AppInfoDao appInfoDao;

    @Autowired
    private IScAlarm scAlarmImpl;
	
	@Override
	@Transactional
	public boolean saveCuCustomerCallRecord(String json) {
		logger.info("客户手机通话记录入库begin");
		try{
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<CuCustomerCallRecord> list = jsonArray.toList(jsonArray,CuCustomerCallRecord.class);
			if (CollectionUtils.isNotEmpty(list)) {
				CuCustomerCallRecord cc = list.get(0);
				String customerId = cc.getCustomerId();
				int appName = cc.getAppName();
				logger.info("客户手机通话记录入库，customerId=" + customerId);
				//获取客户被收集信息汇总表id
				Long customerCollectMessageId = saveCuCustomerCollectMessage(customerId, appName);
				//批量保存客户的手机通话记录
				List<CuCustomerCallRecord> ccrList = new ArrayList<CuCustomerCallRecord>();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					CuCustomerCallRecord ccr = list.get(i);
					ccr.setCustomerCallRecordId(UUIDUtils.getUUID());
					ccr.setCustomerCollectMessageId(customerCollectMessageId);
					ccr.setCustomerId(customerId);
					ccr.setAppName(appName);
					ccr.setCreateTime(DateUtils.getDate(new Date()));
					ccr.setModifyTime(DateUtils.getDate(new Date()));
					ccrList.add(ccr);
					if (i%100 == 0 || i == (size - 1)) {
						cuCustomerCallRecordDao.saveAllEntity(ccrList);
						ccrList.clear();
					}
				}
				logger.info("客户的手机通话记录入库success，序号：" + customerCollectMessageId);
			}
		}catch (Exception e){
			logger.error("客户通话记录入库fail："+e.getMessage());
			throw new RuntimeException("客户通话记录入库fail："+e.getMessage());
		}
		return true;
	}

	@Override
	@Transactional
	public boolean saveCuCustomerFewMessage(String json) {
		logger.info("客户短信数据入库begin：" + json);
		try{
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<CuCustomerFewMessage> list = jsonArray.toList(jsonArray, CuCustomerFewMessage.class);
			if (CollectionUtils.isNotEmpty(list)) {
				CuCustomerFewMessage cc = list.get(0);
				String customerId = cc.getCustomerId();
				int appName = cc.getAppName();
				logger.info("客户短信数据入库，customerId=" + customerId);
				//获取客户被收集信息汇总表id
				Long customerCollectMessageId = saveCuCustomerCollectMessage(customerId, appName);
				//批量保存客户短信数据
				CuCustomerFewMessage customerFewMessage = cuCustomerFewMessageDao.findlateEntity(customerId, appName);
				if (customerFewMessage != null) {
					logger.info("已存在此客户短信数据，此次不做入库处理，customerId=" + customerId);
					return true;
				}
				List<CuCustomerFewMessage> ccrList = new ArrayList<CuCustomerFewMessage>();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					CuCustomerFewMessage ccr = list.get(i);
					ccr.setCustomerFewMessageId(UUIDUtils.getUUID());
					ccr.setCustomerCollectMessageId(customerCollectMessageId);
					ccr.setCustomerId(customerId);
					ccr.setAppName(appName);
					ccr.setCreateTime(DateUtils.getDate(new Date()));
					ccr.setModifyTime(DateUtils.getDate(new Date()));
					ccrList.add(ccr);
					if (i%100 == 0 || i == (size - 1)) {
						cuCustomerFewMessageDao.saveAllEntity(ccrList);
						ccrList.clear();
					}
				}
				logger.info("客户短信数据入库success，序号：" + customerCollectMessageId);
			}
		}catch (Exception e){
			logger.error("客户短信数据入库fail："+e.getMessage());
			throw new RuntimeException("客户短信数据入库fail："+e.getMessage());
		}
		return true;
	}

	@Override
	@Transactional
	public boolean saveAppInfo(String json) {
		logger.info("客户手机应用列表数据入库");
		try{
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<AppInfo> list = jsonArray.toList(jsonArray,AppInfo.class);
			if (CollectionUtils.isNotEmpty(list)) {
				AppInfo cc = list.get(0);
				String customerId = cc.getCustomerId();
				int appName = cc.getAppName();
				logger.info("客户手机应用列表数据入库，customerId=" + customerId);
				//获取客户被收集信息汇总表id
				Long customerCollectMessageId = saveCuCustomerCollectMessage(customerId, appName);
				//批量保存客户手机应用列表
				AppInfo appInfo = appInfoDao.findlateEntity(customerId, appName);
				if (appInfo != null) {
					logger.info("已存在此客户的手机应用列表数据，此次不做入库处理，customerId=" + customerId);
					return true;
				}
				List<AppInfo> ccrList = new ArrayList<AppInfo>();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					AppInfo ccr = list.get(i);
					ccr.setAppInfoId(UUIDUtils.getUUID());
					ccr.setCustomerCollectMessageId(customerCollectMessageId);
					ccr.setCustomerId(customerId);
					ccr.setAppName(appName);
					ccr.setCreateTime(DateUtils.getDate(new Date()));
					ccr.setModifyTime(DateUtils.getDate(new Date()));
					ccrList.add(ccr);
					if (i%100 == 0 || i == (size - 1)) {
						appInfoDao.saveAllEntity(ccrList);
						ccrList.clear();
					}
				}
				logger.info("客户手机应用列表数据入库success，序号：" + customerCollectMessageId);
			}
		}catch (Exception e){
			logger.error("客户手机应用列表数据入库fail："+e.getMessage());
			throw new RuntimeException("客户手机应用列表数据入库fail："+e.getMessage());
		}
		return true;
	}

	@Override
	@Transactional
	public boolean saveCustomerDirectories(String json) {
		logger.info("客户手机通讯录数据入库");
		try{
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<CustomerDirectories> list = jsonArray.toList(jsonArray,CustomerDirectories.class);
			if (CollectionUtils.isNotEmpty(list)) {
				CustomerDirectories cc = list.get(0);
				String customerId = cc.getCustomerId();
				int appName = cc.getAppName();
				logger.info("客户手机通讯录数据入库，customerId=" + customerId);
				//获取客户被收集信息汇总表id
				Long customerCollectMessageId = saveCuCustomerCollectMessage(customerId, appName);
				//批量保存客户手机通讯录数据
				CustomerDirectories customerDirectories = customerDirectoriesDao.findlateEntity(customerId, appName);
				if (customerDirectories != null) {
					logger.info("已存在此客户的手机通讯录数据，此次不做入库处理，customerId=" + customerId);
					return true;
				}
				list = checkNameAndPhone(list);
				List<CustomerDirectories> ccrList = new ArrayList<CustomerDirectories>();
				int size = list.size();
				for (int i = 0; i < size; i++) {
					CustomerDirectories ccr = list.get(i);
					ccr.setDirectoriesId(UUIDUtils.getUUID());
					ccr.setCustomerCollectMessageId(customerCollectMessageId);
					ccr.setCustomerId(customerId);
					ccr.setAppName(appName);
					ccr.setCreateTime(DateUtils.getDate(new Date()));
					ccr.setModifyTime(DateUtils.getDate(new Date()));
					ccrList.add(ccr);
					if (i%100 == 0 || i == (size - 1)) {
						customerDirectoriesDao.saveAllEntity(ccrList);
						ccrList.clear();
					}
				}
				logger.info("客户手机通讯录数据入库success，序号：" + customerCollectMessageId);
			}
		}catch (Exception e){
			logger.error("客户手机通讯录数据入库fail："+e.getMessage());
			throw new RuntimeException("客户手机通讯录数据入库fail："+e.getMessage());
		}
		return true;
	}

	private Long saveCuCustomerCollectMessage(String customerId,int appName){
		Long customerCollectMessageId = -1l;
		CuCustomerCollectMessage cuCustomerCollectMessage = cuCustomerCollectMessageDao.getEntityByCustomerId(customerId, appName);
		if (cuCustomerCollectMessage == null) {
			cuCustomerCollectMessage = new CuCustomerCollectMessage();
			cuCustomerCollectMessage.setCustomerId(customerId);
			cuCustomerCollectMessage.setAppName(appName);
			cuCustomerCollectMessage.setCreateTime(DateUtils.getDate(new Date()));
			cuCustomerCollectMessage.setModifyTime(DateUtils.getDate(new Date()));
			try {
				Integer success = cuCustomerCollectMessageDao.saveEntity(cuCustomerCollectMessage);
				if(success != null && success == 1){
					return cuCustomerCollectMessage.getCustomerCollectMessageId();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
                scAlarmImpl.contains(AlarmCodeEnum.APP_STATUS_SYNC, "MQ推送客户消息主表入库(被收集信息汇总表)处理异常，APP客户编号：" + customerId + "; 平台号:" + appName + "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_STATUS_SYNC);
//				DingdingUtil.setMessage(Constants.TASK_TWO_DD_APP_URL_CODE, "当前时间：" + com.shangyong.backend.utils.DateUtils.parseToDateTimeStr(new Date()) +
//						", MQ推送客户消息主表入库(被收集信息汇总表)处理异常，APP客户编号：" + customerId + "; 平台号:" + appName + "; 错误信息：" + e.getMessage());
			}
			throw new RuntimeException("被收集信息汇总表入库fail,customerId="+customerId);
		} else {
			customerCollectMessageId = cuCustomerCollectMessage.getCustomerCollectMessageId();
		}
		return customerCollectMessageId;
	}
	
	/**
	 * 通讯录中联系人状态判断
	 * @param cu
	 */
	private List<CustomerDirectories> checkNameAndPhone(List<CustomerDirectories> list){
		List<CustomerDirectories> directorieslist = new ArrayList<CustomerDirectories>();
		Map<CustomerDirectories, CustomerDirectories> map = checkContactName(list);
		for (CustomerDirectories customerDirectories : map.values()) {
			String extend = customerDirectories.getExtend();
			String name = customerDirectories.getContactName();
			String phone = customerDirectories.getContactPhone();
			String errorMessage = null;
			/*异类号码逻辑处理*/
			if (!StringUtil.checkNotNull(name)) {
				if (StringUtil.checkNotNull(extend)) {
					extend += "," +DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NULL;
				} else {
					extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NULL;
				}
			} else {
				boolean result=name.matches("[0-9]+");
				if (result) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NUM;
					} else {
						extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NUM;
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
					extend += "," +DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_NULL;
				} else {
					extend = DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_NULL;
				}
			} else {
				if (phone.length()>12 || phone.length()<8) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_IRRATIONAL;
					} else {
						extend = DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_IRRATIONAL;
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
	private Map<CustomerDirectories, CustomerDirectories> checkContactName(List<CustomerDirectories> list){
		Map<CustomerDirectories, CustomerDirectories> map = new HashMap<CustomerDirectories, CustomerDirectories>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i), list.get(i));
		}
		for (CustomerDirectories dire : map.keySet()) {
			String name = dire.getContactName();
			int nameNum = 0;
			for (CustomerDirectories CustomerDirectories : list) {
				if (StringUtils.isEquals(name, CustomerDirectories.getContactName())) {
					nameNum++;
				}
			}
			if (nameNum>=3) {
				for (CustomerDirectories dir : map.keySet()) {
					if (StringUtils.isEquals(name, dir.getContactName())) {
						CustomerDirectories c = dir;
						String extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_MORE;
						dir.setExtend(extend);
						map.put(c, dir);
					}
				}
			}
		}
		return map;
	}
}
