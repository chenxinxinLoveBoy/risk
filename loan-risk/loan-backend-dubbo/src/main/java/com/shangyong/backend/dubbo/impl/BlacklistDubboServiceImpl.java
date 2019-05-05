package com.shangyong.backend.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.shangyong.backend.common.BlackListCodeRidesConstants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.consts.MongoConstants;
import com.shangyong.backend.common.enums.BlackListCodeEnum;
import com.shangyong.backend.common.enums.BlackListDsSourceEnum;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.BuBlacklistDeviceDao;
import com.shangyong.backend.dubbo.BlacklistDubboService;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.BuBlacklistDevice;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;
import org.bson.BSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service(version = "1.0.0")
public class BlacklistDubboServiceImpl implements BlacklistDubboService {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("blacklistDubboService");
	@Autowired
	private BlacklistService blacklistService;

	@Autowired
    ApplicationDao aplicationDao;
	
	@Autowired
	private IScAlarm scAlarmImpl;

	@Autowired
	protected MongoOperations mongoTemplate;
	
	@Autowired
    BuBlacklistDeviceDao buBlacklistDeviceDao;
	
	@Override
	public boolean saveEntity(Map<String, String> map) {
		String currentTime = DateUtils.parseToDateTimeStr(new Date());
		boolean flag = false;
		BuBlacklist buBlacklist = new BuBlacklist();
		try {
			if (map != null) {
			
				//将参数中的身份证号取出放入黑名单对象，根据身份证号查询黑名单
				buBlacklist.setCertCode(map.get("certCode"));
				BuBlacklist bu = blacklistService.queryInfoByCertCode(buBlacklist);
				
				//如果查询到用户对象，说明用户在黑名单中已存在
				if(bu != null){
					logger.info("[黑名单入库] 重复传入黑名单信息,返回false");
				}else{
					buBlacklist.setBlacklistId(UUIDUtils.getUUID());
					buBlacklist.setAppName( map.get("appName"));
					buBlacklist.setCertCode(map.get("certCode"));
					buBlacklist.setCertType(map.get("certType"));
					buBlacklist.setCustomerId(map.get("customerId"));
					buBlacklist.setPhoneNum(map.get("phoneNum"));
					buBlacklist.setName(map.get("name"));
					buBlacklist.setRejectType( map.get("rejectType"));

					// 列入黑名单原因
					buBlacklist.setRemark(map.get("remark"));
					buBlacklist.setDeviceId(map.get("deviceId"));
					buBlacklist.setCreateTime(DateUtils.parseToDateTimeStr(new Date()));
					buBlacklist.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());

					//逾期黑名单
					buBlacklist.setClassCode(BlackListCodeEnum.OVERDUE_BLACKLIST_CODE.getCode());
					logger.info("[黑名单入库] 当前时间：" + currentTime + ",调取【风控黑名单入库接口】服务开始,传入参数：" + buBlacklist.toString());
					flag = blacklistService.saveEntity(buBlacklist);
					
					//拉取设备ID,无线mac,真实IP地址存入rides
					pullDeviceMacIPIntoRedis(map.get("customerId"));
				}
			} else {
				logger.info("[黑名单入库] 传入的参数不能为null,返回false");
			}
		} catch (Exception e) {
			scAlarmImpl.contains(AlarmCodeEnum.COLLECTION_DOCKING,"传入参数：" + map.toString() + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.COLLECTION_DOCKING);
//			DingdingUtil.setMessage(Constants.DUBBO_DD_REC_URL_CODE,"当前时间：" + currentTime + ",传入参数：" + buBlacklist.toString() + e.getMessage());
			logger.error("[黑名单入库]错误，[param="+ JSON.toJSONString(map)+"]", e);
		}
		return flag;
	}
	
	
	/**
	 * 拉取设备ID,无线mac,真实IP地址存入rides
     * @param customerId
     */
	public void pullDeviceMacIPIntoRedis(String customerId) {
		// 查询用户所有的申请单信息
		List<Application> applicationList = aplicationDao.getApplicationListByCustomerId(customerId);

		if(CollectionUtils.isEmpty(applicationList)){
			logger.info("[黑名单入库][设备黑名单] 没有查询到[customerId=" + customerId + "] 的申请单子");
			return;
		}

		ArrayList<String> applicationIdList = new ArrayList<>();
		Map<String, Application> rel = new HashMap<>();
		for(int i = 0; i < applicationList.size(); i++){
			applicationIdList.add(applicationList.get(i).getApplicationId());
            rel.put(applicationList.get(i).getApplicationId(), applicationList.get(i));
		}

		BasicDBObject fieldsObject = new BasicDBObject();
		fieldsObject.put("jsonInfo.device_info.deviceId", 1);
		fieldsObject.put("jsonInfo.device_info.trueIp", 1);
		fieldsObject.put("jsonInfo.device_info.wifiMac", 1);
		fieldsObject.put("applicationId", 1);

		DBObject query = new BasicDBObject();
		query.put("taskType", new BasicDBObject("$eq", TaskTypeConstants.TD_FACILITY_TYPE));
		query.put("applicationId", new BasicDBObject("$in", applicationIdList));

		DBCursor dbCursor = mongoTemplate.getCollection(MongoConstants.DOC_NAME_DATA_REPORT).find(query, fieldsObject);

		while (dbCursor.hasNext()){
			DBObject object = dbCursor.next();

            Object jsonInfoObj = object.get("jsonInfo");

            if(null == jsonInfoObj){
                continue;
            }
            Object applicationIdObj = object.get("applicationId");
            if(null == applicationIdObj){
                continue;
            }
            String applicationIdTemp = String.valueOf(applicationIdObj);

            BSONObject jsonInfoJson = (BSONObject) jsonInfoObj;
            Object deviceInfoObj = jsonInfoJson.get("device_info");

            if(null == deviceInfoObj){
                continue;
            }

            BSONObject deviceInfoJson = (BSONObject) deviceInfoObj;

            Object deviceIdObj = deviceInfoJson.get("deviceId");
            Object wifiMacObj = deviceInfoJson.get("wifiMac");
            Object trueIpObj = deviceInfoJson.get("trueIp");

            BuBlacklistDevice buBlacklistDevice = new BuBlacklistDevice();
            if (null != deviceIdObj && !StringUtils.isEmpty(String.valueOf(deviceIdObj))) {
                String deviceIdStr = String.valueOf(deviceIdObj);
                RedisUtils.hsetEx(BlackListCodeRidesConstants.DEVICE_KEY, deviceIdStr,deviceIdStr);
                buBlacklistDevice.setDeviceId(deviceIdStr);
            }
            // 无线mac存入rides
            if (null != wifiMacObj && !StringUtils.isEmpty(String.valueOf(wifiMacObj))) {
                String wifiMacStr =  String.valueOf(wifiMacObj);
                RedisUtils.hsetEx(BlackListCodeRidesConstants.MAC_KEY, wifiMacStr, wifiMacStr);
                buBlacklistDevice.setMacAddress(wifiMacStr);
            }
            // 真实IP存入rides
            if (null != trueIpObj && !StringUtils.isEmpty(String.valueOf(trueIpObj))) {
                String trueIpStr = String.valueOf(trueIpObj);
                RedisUtils.hsetEx(BlackListCodeRidesConstants.IP_KEY, trueIpStr, trueIpStr);
                buBlacklistDevice.setIpAddress(trueIpStr);
            }

            //去除重复设备黑名单
            if(buBlacklistDeviceDao.selectRepeat(customerId, buBlacklistDevice.getDeviceId(), buBlacklistDevice.getMacAddress(), buBlacklistDevice.getIpAddress()) > 0){
                continue;
            }

            Application appTemp = rel.get(applicationIdTemp);

            //黑名单设备信息入库
            buBlacklistDevice.setBlacklistId(UUIDUtils.getUUID());
            buBlacklistDevice.setCustomerId(customerId);
            buBlacklistDevice.setName(appTemp.getName());
            buBlacklistDevice.setCertType(appTemp.getCertType());
            buBlacklistDevice.setCertCode(appTemp.getCertCode());
            buBlacklistDevice.setPhoneNum(appTemp.getPhoneNum());
            buBlacklistDevice.setIsFailure("1");
            buBlacklistDevice.setCreateTime(new Date());
            buBlacklistDevice.setModifyTime(new Date());
            buBlacklistDeviceDao.insertSelective(buBlacklistDevice);

			logger.info("[黑名单入库][设备黑名单] [applicationId=" + applicationIdTemp
                    + "][deviceId= " + deviceIdObj
                    + "][trueIp= " + trueIpObj
                    + "][wifiMac= " + wifiMacObj
                    + "] 入库成功");
		}
   }
}
