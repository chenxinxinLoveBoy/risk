package com.shangyong.backend.controller.mongodb;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Component
@RequestMapping("/backend/mongodb/")
public class MongodbAppCustomerController {

//	private static Logger log = LoggerFactory.getLogger(MongodbAppCustomerController.class);
//
//	@Autowired
//	MongoUtils mongoUtils;
//
//	/**查询mongodb客户【app应用列表,手手机通讯录,手机通话记录,短信】异常数据信息**/
//	@RequestMapping(value = "findExceptionCustomerMessage", method = RequestMethod.POST)
//	public void findExceptionCustomerMessage(String type, HttpServletRequest request, HttpServletResponse response) {
//		log.info("MongodbAppCustomerController.findExceptionCustomerMessage-->>mongodb【app应用列表(app),手手机通讯录(directories),手机通话记录(callRecord),短信(fewMessage)】所有异常数据查询开始，type=" + type);
//		try {
//			List list = null;
//			if (StringUtils.equals(type, "app")) {
//				list = (List<ExceptionCuAppInfo>) mongoUtils.findListByClazz(null, null, ExceptionCuAppInfo.class);
//			} else if (StringUtils.equals(type, "directories")) {
//				list = (List<ExceptionCuCustomerDirectories>) mongoUtils.findListByClazz(null, null, ExceptionCuCustomerDirectories.class);
//			} else if (StringUtils.equals(type, "callRecord")) {
//				list = (List<ExceptionCuCustomerCallRecord>) mongoUtils.findListByClazz(null, null, ExceptionCuCustomerCallRecord.class);
//			} else if (StringUtils.equals(type, "fewMessage")) {
//				list = (List<ExceptionCuCustomerFewMessage>) mongoUtils.findListByClazz(null, null, ExceptionCuCustomerFewMessage.class);
//			}
//			if (CollectionUtils.isNotEmpty(list)) {
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS, JSON.toJSON(list));
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
//			}
//		} catch (Exception e) {
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//
//	/**通过id查询mongodb客户【app应用列表,手手机通讯录,手机通话记录,短信】异常详情数据信息**/
//	@RequestMapping(value = "findExceptionCustomerMessageInfo", method = RequestMethod.POST)
//	public void findExceptionCustomerMessage(String id, String type, HttpServletRequest request, HttpServletResponse response) {
//		log.info("MongodbAppCustomerController.findExceptionCustomerMessageInfo-->>mongodb【app应用列表(app),手手机通讯录(directories),手机通话记录(callRecord),短信(fewMessage)】[单个客户]异常数据查询开始，type=" + type);
//		try {
//			if (StringUtils.isBlank(id) || StringUtils.isBlank(type)) {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
//				return;
//			}
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("_id", id);
//			if (StringUtils.equals(type, "app")) {
//				ExceptionCuAppInfo ex = null;
//				SpringUtils.renderJsonArray(response, ex.getJsonInfo().get("list"));
//			} else if (StringUtils.equals(type, "directories")) {
//				ExceptionCuCustomerDirectories ex = (ExceptionCuCustomerDirectories) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerDirectories.class);
//				SpringUtils.renderJsonArray(response, ex.getJsonInfo().get("list"));
//			} else if (StringUtils.equals(type, "callRecord")) {
//				ExceptionCuCustomerCallRecord ex = (ExceptionCuCustomerCallRecord) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerCallRecord.class);
//				SpringUtils.renderJsonArray(response, ex.getJsonInfo().get("list"));
//			} else if (StringUtils.equals(type, "fewMessage")) {
//				ExceptionCuCustomerFewMessage ex = (ExceptionCuCustomerFewMessage) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerFewMessage.class);
//				SpringUtils.renderJsonArray(response, ex.getJsonInfo().get("list"));
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_NOT_ALOW);
//			}
//		} catch (Exception e) {
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//	
//	/**发送MQ消息将客户【app应用列表,手手机通讯录,手机通话记录,短信】重新推送mongodb库**/
//	@RequestMapping(value = "saveCustomerMessageInfo", method = RequestMethod.POST)
//	public void saveCustomerMessageInfo(String id, String type, HttpServletRequest request, HttpServletResponse response) {
//		log.info("MongodbAppCustomerController.saveCustomerMessageInfo-->>发送MQ消息将客户【app应用列表(app),手手机通讯录(directories),手机通话记录(callRecord),短信(fewMessage)】[单个客户]重新推送mongodb库，type=" + type);
//		try {
//			if (StringUtils.isBlank(id) || StringUtils.isBlank(type)) {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
//				return;
//			}
//			boolean flag = checkType(type, id);
//			if (flag) {
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS);
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
//			}
//		} catch (Exception e) {
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//
//	private boolean checkType(String type, String id){
//		boolean flag = false;
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("_id", id);
//		if (StringUtils.equals(type, "app")) {
//			ExceptionCuAppInfo ex = null;
//			Object contentText = ex.getJsonInfo().get("list");
//			flag = RabbitUtil.send(JSON.toJSONString(contentText), MQConstants.MQ_EXCHANGE_SPLB_BACK, MQConstants.MQ_ROUTINGKEY_SPLB_BACK);
//			if (flag) {
//				mongoUtils.removeAllByClazz(paramMap, ExceptionCuAppInfo.class);
//			}
//		} else if (StringUtils.equals(type, "directories")) {
//			ExceptionCuCustomerDirectories ex = (ExceptionCuCustomerDirectories) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerDirectories.class);
//			Object contentText = ex.getJsonInfo().get("list");
//			flag = RabbitUtil.send(JSON.toJSONString(contentText), MQConstants.MQ_EXCHANGE_SPTXL_BACK, MQConstants.MQ_ROUTINGKEY_SPTXL_BACK);
//			if (flag) {
//				mongoUtils.removeAllByClazz(paramMap, ExceptionCuCustomerDirectories.class);
//			} 
//		} else if (StringUtils.equals(type, "callRecord")) {
//			ExceptionCuCustomerCallRecord ex = (ExceptionCuCustomerCallRecord) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerCallRecord.class);
//			Object contentText = ex.getJsonInfo().get("list");
//			flag = RabbitUtil.send(JSON.toJSONString(contentText), MQConstants.MQ_EXCHANGE_SPTH_BACK, MQConstants.MQ_ROUTINGKEY_SPTH_BACK);
//			if (flag) {
//				mongoUtils.removeAllByClazz(paramMap, ExceptionCuCustomerCallRecord.class);
//			}
//		} else if (StringUtils.equals(type, "fewMessage")) {
//			ExceptionCuCustomerFewMessage ex = (ExceptionCuCustomerFewMessage) mongoUtils.findByClazz(paramMap, null, ExceptionCuCustomerFewMessage.class);
//			Object contentText = ex.getJsonInfo().get("list");
//			flag = RabbitUtil.send(JSON.toJSONString(contentText), MQConstants.MQ_EXCHANGE_SPDX_BACK, MQConstants.MQ_ROUTINGKEY_SPDX_BACK);
//			if (flag) {
//				mongoUtils.removeAllByClazz(paramMap, ExceptionCuCustomerFewMessage.class);
//			}
//		}
//		return flag;
//	}
//	
}