package com.shangyong.backend.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 审批app推送短信，通讯录，通话记录，app列表 信息收集
 * 
 * @author hxf
 *
 */
@Controller
@RequestMapping("/backend/app/customer/")
public class AppCustomerMessagesController {
//	/** 日志 */
//	private static Logger appLog = LoggerFactory.getLogger("rabbitmqSpCustomerMessage");
//
//    private static Logger directoriesLog = LoggerFactory.getLogger("rabbitmqDirectoriesCustomerLog");
//
//    private static Logger fewMessageLog = LoggerFactory.getLogger("rabbitmqFewMessageCustomerLog");
//
//    private static Logger callRecordLog = LoggerFactory.getLogger("rabbitmqCallRecordCustomerLog");
//
//	@Autowired
//    private AppCustomerMessageService customerMessageService;
//
//	@Autowired
//	private IScAlarm scAlarmImpl;
//
//	@RequestMapping(value = "collectCuAppInfo.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String collectCuAppInfo(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//		
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("code", "200");
//		long startTime = System.currentTimeMillis();
//		if (StringUtils.isBlank(json)) {
//			paramMap.put("code", "-1");
//			paramMap.put("msg","参数为空json=" + json);
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//    	Map<String, Object> jsons = JacksonUtils.JsonToMapObject(json);
//    	appLog.info("app推送【app应用列表】开始uuid=" + String.valueOf(jsons.get("uuid")));
//		if (!jsons.containsKey("list") || !jsons.containsKey("appName") || !jsons.containsKey("customerId") 
//				|| StringUtils.isEmpty(String.valueOf(jsons.get("appName"))) ||StringUtils.isEmpty(String.valueOf(jsons.get("customerId")))) {
//			paramMap.put("code", "500");
//			paramMap.put("msg","缺少必要参数json=" + json);
//			appLog.info("缺少必要参数uuid=" + String.valueOf(jsons.get("uuid")));
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//		String customerId = String.valueOf(jsons.get("customerId"));
//		String list = String.valueOf(jsons.get("list"));
//		int appName = (int) jsons.get("appName");
//		try {
//			customerMessageService.saveAppInfo(list, customerId, appName);
//		} catch (Exception ex) {
//			try {
//				String message = ex.getMessage();
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_APP_LIST,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户app应用列表入库mongodb异常;customerId=" + customerId + ",appName=" + appName 
//						+ ",准备入mongodb异常集合中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_APP_LIST);
//        		customerMessageService.saveExceptionAppInfo(list, message);
//
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_APP_LIST,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",入mongodb异常集合成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_APP_LIST);
//				appLog.info("【app应用列表】入mongodb异常集合成功; 运行时异常：" + message);
//			} catch (Exception e) {
//				String message = e.getMessage();
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_APP_LIST,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户app应用列表入mongodb异常集合失败;customerId=" + customerId + ",appName=" + appName 
//						+ ",准备入redis中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_APP_LIST);
//
//				String key = customerId + appName;
//				RedisUtils.set(RedisConstant.COLLECT_APP_INFO_REDIS_KEY + key, json);
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_APP_LIST,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",入redis成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_APP_LIST);
//				appLog.error("【app应用列表】入redis成功; 运行时异常：" + message);
//			}
//		}
//		appLog.info("app推送客户app应用列表结束，耗时=" + (System.currentTimeMillis() - startTime) + "ms;uuid=" + String.valueOf(jsons.get("uuid")));
//		
//		return JacksonUtils.ObjectToJson(paramMap);
//	}
//
//	@RequestMapping(value = "collectCustomerDirectories.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String collectCustomerDirectories(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//		long startTime = System.currentTimeMillis();
//    	Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("code", "200");
//		if (StringUtils.isBlank(json)) {
//			paramMap.put("code", "-1");
//			paramMap.put("msg","参数为空json=" + json);
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//    	Map<String, Object> jsons = JacksonUtils.JsonToMapObject(json);
//    	directoriesLog.info("app推送客户手机通讯录数据开始uuid=" + String.valueOf(jsons.get("uuid")));
//		if (jsons == null || !jsons.containsKey("list") || !jsons.containsKey("appName") || !jsons.containsKey("customerId") 
//				|| StringUtils.isEmpty(String.valueOf(jsons.get("appName"))) ||StringUtils.isEmpty(String.valueOf(jsons.get("customerId")))) {
//			paramMap.put("code", "500");
//			paramMap.put("msg","缺少必要参数json=" + json);
//			directoriesLog.info("缺少必要参数uuid=" + String.valueOf(jsons.get("uuid")));
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//		String customerId = String.valueOf(jsons.get("customerId"));
//		String list = String.valueOf(jsons.get("list"));
//		int appName = (int) jsons.get("appName");
//        try{
//        	customerMessageService.saveCustomerDirectories(list, customerId, appName);
//        }catch (Exception ex){
//        	try {
//        		String message = ex.getMessage();
//        		scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CONTACTS,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//        				",app推送客户通讯录入库mongodb异常;customerId=" + customerId + ",appName=" + appName 
//        				+ ",准备入mongodb异常集合中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CONTACTS);
//
//        		customerMessageService.saveExceptionCuCustomerDirectories(list, message);
//
//        		scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CONTACTS, "当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//        				",入mongodb异常集合成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CONTACTS);
//				directoriesLog.info("app推送客户【通讯录】入mongodb异常集合成功; 运行时异常：" + message);
//			} catch (Exception e) {
//				String message = e.getMessage();
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CONTACTS,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户通讯录入mongodb异常集合失败;customerId=" + customerId + ",appName=" + appName 
//						+ ";准备入redis中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CONTACTS);
//
//				String key = customerId + appName;
//				RedisUtils.set(RedisConstant.COLLECT_DIRECTORIES_REDIS_KEY + key, json);
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CONTACTS,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",【通讯录】入redis成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CONTACTS);
//		        directoriesLog.info("app推送客户【通讯录】入redis成功; 运行时异常：" + message);
//			}
//        }
//        directoriesLog.info("app推送客户手机通讯录数据结束，耗时=" + (System.currentTimeMillis() - startTime) + "ms;uuid=" + String.valueOf(jsons.get("uuid")));
//        return JacksonUtils.ObjectToJson(paramMap);
//	}
//
//	@RequestMapping(value = "collectCustomerFewMessage.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String collectCustomerFewMessage(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//		long startTime = System.currentTimeMillis();
//    	Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("code", "200");
//		if (StringUtils.isBlank(json)) {
//			paramMap.put("code", "-1");
//			paramMap.put("msg","参数为空json=" + json);
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//    	Map<String, Object> jsons = JacksonUtils.JsonToMapObject(json);
//    	fewMessageLog.info("app推送客户短信数据开始uuid=" + String.valueOf(jsons.get("uuid")));
//		if (jsons == null || !jsons.containsKey("list") || !jsons.containsKey("appName") || !jsons.containsKey("customerId") 
//				|| StringUtils.isEmpty(String.valueOf(jsons.get("appName"))) ||StringUtils.isEmpty(String.valueOf(jsons.get("customerId")))) {
//			paramMap.put("code", "500");
//			paramMap.put("msg","缺少必要参数json=" + json);
//			fewMessageLog.info("缺少必要参数uuid=" + String.valueOf(jsons.get("uuid")));
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//		String customerId = String.valueOf(jsons.get("customerId"));
//		String list = String.valueOf(jsons.get("list"));
//		int appName = (int) jsons.get("appName");
//        try{
//        	customerMessageService.saveCuCustomerFewMessage(list, customerId, appName);
//        }catch (Exception ex){
//        	try {
//        		String message = ex.getMessage();
//        		scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_MESSAGE,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//        				",app推送客户短信入库mongodb异常;customerId=" + customerId + ",appName=" + appName 
//        				+ ";准备入mongodb异常集合中…… 异常：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_MESSAGE);
//        		customerMessageService.saveExceptionCuCustomerFewMessage(list, message);
//
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_MESSAGE,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) 
//				+ ",app推送客户短信入mongodb异常集合成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_MESSAGE);
//				fewMessageLog.info("app推送客户【短信】入mongodb异常集合成功; 运行时异常：" + message);
//			} catch (Exception e) {
//				String message = e.getMessage();
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_MESSAGE,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户短信异常数据入mongodb失败;customerId=" + customerId + ",appName=" + appName + ";准备入redis中…… 异常：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_MESSAGE);
//
//				String key = customerId + appName;
//				RedisUtils.set(RedisConstant.COLLECT_FEW_MESSAGE_REDIS_KEY + key, json);
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_MESSAGE,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ",app推送客户短信入redis成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_MESSAGE);
//		        fewMessageLog.info("app推送客户【短信】推送异常数据入redis成功; 运行时异常：" + message);
//			}
//        }
//        fewMessageLog.info("app推送客户【短信】数据结束，耗时=" + (System.currentTimeMillis() - startTime) + "ms;uuid=" + String.valueOf(jsons.get("uuid")));
//        return JacksonUtils.ObjectToJson(paramMap);
//	}
//
//	@RequestMapping(value = "collectCustomerCallRecord.do", method = RequestMethod.POST)
//	@ResponseBody
//	public String collectCustomerCallRecord(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
//		long startTime = System.currentTimeMillis();
//    	Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("code", "200");
//		if (StringUtils.isBlank(json)) {
//			paramMap.put("code", "-1");
//			paramMap.put("msg","参数为空json=" + json);
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//    	Map<String, Object> jsons = JacksonUtils.JsonToMapObject(json);
//    	callRecordLog.info("app推送客户通话记录开始uuid=" + String.valueOf(jsons.get("uuid")));
//		if (jsons == null || !jsons.containsKey("list") || !jsons.containsKey("appName") || !jsons.containsKey("customerId") 
//				|| StringUtils.isEmpty(String.valueOf(jsons.get("appName"))) ||StringUtils.isEmpty(String.valueOf(jsons.get("customerId"))) || StringUtils.isEmpty(String.valueOf(jsons.get("uuid")))) {
//			paramMap.put("code", "500");
//			paramMap.put("msg","缺少必要参数json=" + json);
//			callRecordLog.info("缺少必要参数uuid=" + String.valueOf(jsons.get("uuid")));
//			return JacksonUtils.ObjectToJson(paramMap);
//		}
//		String customerId = String.valueOf(jsons.get("customerId"));
//		String list = String.valueOf(jsons.get("list"));
//		int appName = (int) jsons.get("appName");
//        try{
//        	customerMessageService.saveCuCustomerCallRecord(list, customerId, appName);
//        }catch (Exception ex){
//        	try {
//        		String message = ex.getMessage();
//        		scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CALL_RECORD,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//        				",app推送客户通话记录入库mongodb异常;customerId=" + customerId + ",appName=" + appName + ";准备入mongodb异常集合中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CALL_RECORD);
//
//        		customerMessageService.saveExceptionCuCustomerCallRecord(list, message);
//
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CALL_RECORD,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户通话记录入mongodb异常集合成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CALL_RECORD);
//				callRecordLog.info("app推送客户【通话记录】入mongodb异常集合成功; 运行时异常：" + message);
//			} catch (Exception e) {
//				String message = e.getMessage();
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CALL_RECORD,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户通话记录入mongodb异常集合失败 ;customerId=" + customerId + ",appName=" + appName + ";准备入redis中…… 异常message：" + message, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CALL_RECORD);
//
//				String key = customerId + appName;
//				RedisUtils.set(RedisConstant.COLLECT_CALL_RECORD_REDIS_KEY + key, json);
//
//				scAlarmImpl.contains(AlarmCodeEnum.CUSTOMER_CALL_RECORD,"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + 
//						",app推送客户通话记录入redis成功;customerId=" + customerId + ",appName=" + appName, AlarmThirdPartyCreditInvestigationEnum.CUSTOMER_CALL_RECORD);
//		        callRecordLog.info("app推送客户【通话记录】入redis成功; 运行时异常：" + message);
//			}
//        }
//        callRecordLog.info("app推送客户【通话记录】数据结束，耗时=" + (System.currentTimeMillis() - startTime) + "ms;uuid=" + String.valueOf(jsons.get("uuid")));
//        return JacksonUtils.ObjectToJson(paramMap);
//	}

}
