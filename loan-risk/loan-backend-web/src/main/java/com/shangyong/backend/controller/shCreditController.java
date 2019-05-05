package com.shangyong.backend.controller;

import com.shangyong.backend.entity.sh.ShCreditGuarantee;
import com.shangyong.backend.entity.sh.ShCreditLoans;
import com.shangyong.backend.entity.sh.ShCreditSpecialDeal;
import com.shangyong.backend.entity.sh.ShCreditTheme;
import com.shangyong.backend.service.shzx.ShCreditService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/shCredit/")
public class shCreditController {
	private static Logger logger = Logger.getLogger(shCreditController.class);
	@Autowired
	private ShCreditService shCreditService;
	/**
	 * 获取客户信息详情
	 * 
	 * @param request
	 * @param response
	 * @param cuPlatformCustomer
	 */
	@RequestMapping(value = "getShCreditByApplicationId.do", method = RequestMethod.POST)
	public void getShCreditByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		
		try {
			//判断申请单号是否为空
			if(StringUtils.isBlank(applicationId)){
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
			ShCreditTheme shCreditTheme = shCreditService.queryByApplicationId(applicationId);
			
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, shCreditTheme);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	@RequestMapping(value = "getAllDealsByApplicationId.do", method = RequestMethod.POST)
	public void getAllDealsByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		
		try {
			//判断申请单号是否为空
			if(StringUtils.isBlank(applicationId)){
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
			List<ShCreditLoans> allLoans= shCreditService.queryAllDealsByApplicationId(applicationId);
			
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, allLoans);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	@RequestMapping(value = "getAllGuranteeByApplicationId.do", method = RequestMethod.POST)
	public void getAllGuranteeByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		
		try {
			//判断申请单号是否为空
			if(StringUtils.isBlank(applicationId)){
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
			List<ShCreditGuarantee> gurantees= shCreditService.getAllGuranteeByApplicationId(applicationId);
			
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, gurantees);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	@RequestMapping(value = "getAllSpecialDealByApplicationId.do", method = RequestMethod.POST)
	public void getAllSpecialDealByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		
		try {
			//判断申请单号是否为空
			if(StringUtils.isBlank(applicationId)){
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
			List<ShCreditSpecialDeal> specialDeal= shCreditService.getAllSpecialDealByApplicationId(applicationId);
			
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, specialDeal);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

}
