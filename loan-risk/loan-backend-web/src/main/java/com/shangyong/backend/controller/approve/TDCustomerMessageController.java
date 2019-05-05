package com.shangyong.backend.controller.approve;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.service.approval.service.TDCustomerMessageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/backend/summary/")
public class TDCustomerMessageController {
	
	private static Logger log = LoggerFactory.getLogger("dsjTdSummaryMessageLog");

	@Autowired
	private TDCustomerMessageService tDCustomerMessageService;

	/**通过APP客户编号和appName查询大数据短信汇总信息**/
	@RequestMapping(value = "summaryFewMessage", method = RequestMethod.POST)
	public void summaryFewMessage(String customerId, String appName, HttpServletRequest request, HttpServletResponse response) {
		log.info("【大数据短信汇总信息】查询开始,customerId=" + customerId + ",appName=" + appName);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
				String result = tDCustomerMessageService.summaryFewMessage(customerId,appName);
				JSONObject totalJsonObj = JSONObject.fromObject(result);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, totalJsonObj);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【大数据短信汇总信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		log.info("【大数据短信汇总信息】查询结束,customerId=" + customerId + ",appName=" + appName);
	}

	/**通过APP客户编号和appName查询大数据app应用列表汇总信息**/
	@RequestMapping(value = "summaryAppInfo", method = RequestMethod.POST)
	public void summaryAppInfo(String customerId, String appName, HttpServletRequest request, HttpServletResponse response) {
		log.info("【大数据app应用列表汇总信息】查询开始,customerId=" + customerId + ",appName=" + appName);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
				String result = tDCustomerMessageService.summaryAppInfo(customerId,appName);
				JSONObject totalJsonObj = JSONObject.fromObject(result);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, totalJsonObj);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【大数据app应用列表汇总信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		log.info("【大数据app应用列表汇总信息】查询结束,customerId=" + customerId + ",appName=" + appName);
	}

	/**通过APP客户编号和appName查询大数据设备汇总信息**/
	@RequestMapping(value = "summaryEquipment", method = RequestMethod.POST)
	public void summaryEquipment(String customerId, String appName, HttpServletRequest request, HttpServletResponse response) {
		log.info("【大数据设备汇总信息】查询开始,customerId=" + customerId + ",appName=" + appName);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
				String result = tDCustomerMessageService.summaryEquipment(customerId,appName);
				JSONObject totalJsonObj = JSONObject.fromObject(result);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, totalJsonObj);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【大数据设备汇总信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		log.info("【大数据设备汇总信息】查询结束,customerId=" + customerId + ",appName=" + appName);
	}
}
