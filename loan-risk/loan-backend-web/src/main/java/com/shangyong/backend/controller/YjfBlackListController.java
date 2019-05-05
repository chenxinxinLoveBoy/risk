package com.shangyong.backend.controller;

import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.yjf.YjfInfoCheck;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.yjf.YjfBlackListService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.DataReport;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/backend/yjfBlackList/")
public class YjfBlackListController {
	private static Logger logger = Logger.getLogger(YjfBlackListController.class);
	@Autowired
	private YjfBlackListService yjfBlackListService;
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	@Autowired
	private MongoUtils mongoUtils;

	/**
	 * 获取客户信息详情
	 * 
	 * @param request
	 * @param response
	 * @param applicationId
	 * @param platformId
	 */
	@RequestMapping(value = "getYjfBlackMysqlByApplicationId.do", method = RequestMethod.POST)
	public void getYjfBlackMysqlByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId, String platformId) {
		
		try {
			if(StringUtils.isBlank(applicationId)){
				Application application= applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				if(application!=null){
					applicationId = application.getApplicationId();
				}else{
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
				}
			}

			YjfInfoCheck yjfInfoCheck = yjfBlackListService.getJyjfBlackListkByAid(applicationId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, yjfInfoCheck, "yjfInfoObject");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 易极付黑名单mongo获取数据
	 * @param request
	 * @param response
	 * @param applicationId
	 * @param type
	 * @param platformId
	 */
	@RequestMapping(value = "getYjfBlackByApplicationId.do", method = RequestMethod.POST)
	public void getYjfBlackByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId, String type, String platformId) {
		try {

			if(StringUtils.isBlank(applicationId)){
				
				Application application= applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				
				if(application!=null){
					applicationId = application.getApplicationId();
				}else{
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
				}
			}
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("applicationId",applicationId);
			paramMap.put("taskType", TaskTypeConstants.YJF_TASK_TYPE);
			DataReport report=(DataReport) mongoUtils.findByClazz(paramMap,null, DataReport.class);
			if(report!=null){
					JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
				}
				else
				{
					JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

}
