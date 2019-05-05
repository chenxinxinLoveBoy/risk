package com.shangyong.backend.controller.approve;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.shangyong.backend.dao.approval.CustomerDirectoriesDao;
import com.shangyong.backend.entity.approval.CustomerDirectories;
import com.shangyong.backend.service.approval.service.CustomerDirectoriesService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.DirectoriesRuleUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.AppAddressBook;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


@Controller
@Component
@RequestMapping("/backend/approve/")
public class EquipmentCognateController {

	private static Logger log = LoggerFactory.getLogger(EquipmentCognateController.class);
//	
	@Autowired
MongoUtils mongoUtils;
//	
//	@Autowired
//	private AppInfoService appInfoService;
	
	@Autowired
	private CustomerDirectoriesService customerDirectoriesService;
	
	@Autowired
	private CustomerDirectoriesDao customerDirectoriesDao;
//	
	/**通过客户编号查询通讯录信息**/
	@RequestMapping(value = "cognateAnalysis.do")
	public void cognateAnalysis(HttpServletRequest request, HttpServletResponse response, AppAddressBook appAddressBook) {
		try {
			LinkedHashMap<String,Object> map = null;
			
 			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("customerId", appAddressBook.getCustomerId());

			List<Order> orderList = new ArrayList<Order>();
			Order order = new Order(Direction.DESC,"createTimeLong");
			orderList.add(order);
			AppAddressBook AppAddressBooks = (AppAddressBook) mongoUtils.findByClazz(paramMap, orderList, AppAddressBook.class);

			if (AppAddressBooks == null) {
				log.info("没有获取到用户通讯录信息");
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
				return;
			}
			Object jsonInfo = AppAddressBooks.getJsonInfo();
			JSONArray jsonArray = JSONArray.fromObject(jsonInfo);

			List<CustomerDirectories> directories = DirectoriesRuleUtils.paresToCustomerDirectories(jsonArray);
			
			if(CollectionUtils.isNotEmpty(directories)) {
				map = customerDirectoriesService.regulateDirectories(directories);
				map.remove("txlNum");
				map.remove("list");
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,map);
				return;
			}else{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
				return;
			}
		} catch (Exception e) {
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
			return;
		}
	}
	
//	
//	/**通过申请单编号查询催收号码拨打关联记录**/
//	@RequestMapping(value = "findBuIcePerson")
//	public void findBuIcePerson(String applicationId, HttpServletRequest request, HttpServletResponse response) {
//		log.info("EquipmentCognateController.findBuIcePerson-->>【被催收拨打次数】查询开始,applicationId=" + applicationId);
//		try {
//			if (StringUtils.isNotBlank(applicationId)) {
//				List<BuIcePerson> buIcePerson = null;
//				log.info("EquipmentCognateController.findBuIcePerson-->>【被催收拨打次数】查询结束,applicationId=" + applicationId);
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS, buIcePerson);
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
//			}
//		} catch (Exception e) {
//			log.error("EquipmentCognateController.cognateAnalysis-->>【被催收拨打次数】查询异常，运行时异常：" + e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//	
//	/**通过客户编号查询app应用列表信息**/
//	@RequestMapping(value = "appInfo")
//	public void appInfo(String customerId,String appName, HttpServletRequest request, HttpServletResponse response) {
//		log.info("EquipmentCognateController.cognateAnalysis-->>【app应用列表】查询开始，customerId=" + customerId + ",appName=" + appName);
//		try {
//			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
//				Map<String, Object> paramMap = new HashMap<String, Object>();
//				paramMap.put("customerId", customerId);
//				int appType = 1;
//				if (checkNum(appName)) {
//					appType = Integer.parseInt(appName);
//				}
//				paramMap.put("appName", appType);
//				AppInfos appInfos = (AppInfos) mongoUtils.findByClazz(paramMap, null, AppInfos.class);
//				List<AppInfo> app = new ArrayList<AppInfo>();
//				if (appInfos == null) {
//					JSONUtils.toJSON(response, CodeUtils.SUCCESS, app);
//					return;
//				}
//				JSONArray jsonArray = JSONArray.fromObject(appInfos.getJsonInfo().get("list"));
//				if (jsonArray == null) {
//					JSONUtils.toJSON(response, CodeUtils.SUCCESS, app);
//					return;
//				}
//				app = JSONArray.toList(jsonArray,AppInfo.class);
//				log.info("EquipmentCognateController.cognateAnalysis-->>【app应用列表】查询结束，customerId=" + customerId + ",appName=" + appName);
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS, app);
//			} else {
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
//			}
//		} catch (Exception e) {
//			log.error("EquipmentCognateController.cognateAnalysis-->>【app应用列表】查询异常，运行时异常：" + e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//
//	public boolean checkNum(String str) {
//		Pattern pattern = Pattern.compile("[0-9]*");
//		return pattern.matcher(str).matches();
//	}

}
