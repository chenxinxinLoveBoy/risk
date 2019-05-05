package com.shangyong.backend.controller.mongodb;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Component
@RequestMapping("/backend/mongodb/")
public class MongodbTestController {

//	private static Logger log = LoggerFactory.getLogger(MongodbTestController.class);
//    
//	@Autowired
//	MongoUtils mongoUtils;
//
//	/**保存通讯录信息**/
//	@RequestMapping(value = "saveCognateAnalysis", method = RequestMethod.POST)
//    public void saveCognateAnalysis(String resultJson, HttpServletRequest request, HttpServletResponse response) {
//		log.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";-------------通讯录消息保存开始--------------------");
//        if(StringUtils.isBlank(resultJson)){
//            log.info("通讯录消息为null");
//            return;
//        }
//        try{
//            JSONArray jsonArray = JSONArray.fromObject(resultJson);
//			List<CustomerDirectories> list = JSONArray.toList(jsonArray,CustomerDirectories.class);
//			
//			if (CollectionUtils.isNotEmpty(list)) {
//				CustomerDirectories cc = list.get(0);
//				String customerId = cc.getCustomerId();
//				Integer appName = cc.getAppName();
//				
//				AppAddressBook addressBook = new AppAddressBook();
//				addressBook.setCreateTime(new Date());
//				addressBook.setAppName(appName);
//				addressBook.setCustomerId(customerId);
//				
//				Map<String,Object> map = new HashMap<String,Object>();
//				map.put("list", jsonArray);
//				addressBook.setJsonInfo(JSONObject.fromObject(map));
//				mongoUtils.saveByClazz(addressBook);
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS, addressBook);
//			}
//        }catch (Exception ex){
//            log.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";app推送客户【通讯录】保存失败;resultJsons=" + resultJson + "; 运行时异常：" + ex.getMessage());
//        }
//        log.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";-------------通讯录消息结束开始--------------------");
//    }
//
//	/**通过客户编号查询通讯录信息**/
//	@RequestMapping(value = "cognateAnalysis", method = RequestMethod.POST)
//	public void cognateAnalysis(String customerId,String appName, HttpServletRequest request, HttpServletResponse response) {
//		log.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";【通讯录信息】查询开始，customerId=" + customerId + ",appName=" + appName);
//		try {
//			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
//				Map<String, Object> paramMap = new HashMap<String, Object>();
//				int appType = 1;
//				if (checkNum(appName)) {
//					appType = Integer.parseInt(appName);
//				}
//				paramMap.put("appName", appType);
//				paramMap.put("customerId", customerId);
//				AppAddressBook appAddressBook = (AppAddressBook) mongoUtils.findByClazz(paramMap, null, AppAddressBook.class);
//				if (appAddressBook == null) {
//					List<CustomerDirectories> directories = new ArrayList<CustomerDirectories>();
//					JSONUtils.toJSON(response, CodeUtils.SUCCESS, directories);
//					return;
//				}
//				JSONArray jsonArray = JSONArray.fromObject(appAddressBook.getJsonInfo().get("list"));
//				if (jsonArray == null) {
//					List<CustomerDirectories> directories = new ArrayList<CustomerDirectories>();
//					JSONUtils.toJSON(response, CodeUtils.SUCCESS, directories);
//					return;
//				}
//				List<CustomerDirectories> directories = JSONArray.toList(jsonArray,CustomerDirectories.class);
//				log.info("EquipmentCognateController.cognateAnalysis-->>【通讯录信息】查询结束，customerId=" + customerId + ",appName=" + appName);
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS, directories);
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
//			}
//		} catch (Exception e) {
//			log.error("EquipmentCognateController.cognateAnalysis-->>【通讯录信息】查询异常，运行时异常：" + e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//		log.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";【通讯录信息】查询结束，customerId=" + customerId + ",appName=" + appName);
//	}
//
//	public boolean checkNum(String str) {
//		Pattern pattern = Pattern.compile("[0-9]*");
//		return pattern.matcher(str).matches();
//	}
}
