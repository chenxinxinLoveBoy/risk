package com.shangyong.backend.controller;

import com.shangyong.backend.dubbo.impl.GetCustomerInfoService;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backend/customer/")
public class CustomerInfoController {
	private static Logger logger = Logger.getLogger(CustomerInfoController.class);

	@Autowired
	private GetCustomerInfoService getCustomerInfoService;

	/**
	 * 客户信息集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllCustomerInfo.do", method = RequestMethod.POST)
	public void getAllCustomerInfo(HttpServletRequest request, HttpServletResponse response,
                                   CuPlatformCustomer cuPlatformCustomer) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = getCustomerInfoService.getAllCustomerInfo(cuPlatformCustomer);
			if (map != null) {
				int count = Integer.parseInt(map.get("count").toString());
				JSONUtils.toListJSON(response, map.get("list"), count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 获取客户信息详情
	 * 
	 * @param request
	 * @param response
	 * @param cuPlatformCustomer
	 */
	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, String platformCustomerId) {
		try {
			CustomerInfo customerInfo = new CustomerInfo();
			CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
			cuPlatformCustomer.setPlatformCustomerId(platformCustomerId);
			customerInfo = getCustomerInfoService.getEntityById(cuPlatformCustomer);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, customerInfo, "customerInfoObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
