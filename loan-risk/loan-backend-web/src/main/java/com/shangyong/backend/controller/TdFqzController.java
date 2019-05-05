package com.shangyong.backend.controller;

import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
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
@RequestMapping("/backend/tdFqz/")
public class TdFqzController {
	private static Logger logger = Logger.getLogger(TdFqzController.class);
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	@Autowired
	private MongoUtils mongoUtils;
	/**
	 * 获取客户信息详情
	 * 
	 * @param request
	 * @param response
	 * @param cuPlatformCustomer
	 */
	/*@RequestMapping(value = "getTdReportMysqlByApplicationId.do", method = RequestMethod.POST)
	public void getTdReportMysqlByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId,String platformId) {
		
		try {
			
			if(StringUtils.isBlank(applicationId)){
				
				Application application= applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				
				if(application!=null){
					applicationId = application.getApplicationId();
				}else{
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
				}
			}
			
			JgInfoCheck jgInfoCheck=new JgInfoCheck();
			jgInfoCheck=jgInfoCheckService.getJgInfoCheckByAid(applicationId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, jgInfoCheck, "jgInfoObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}*/
	
	@RequestMapping(value = "getTdReportByApplicationId.do", method = RequestMethod.POST)
	public void getTdReportByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId, String type, String platformId) {
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
			paramMap.put("taskType", TaskTypeConstants.TD_TASK_TYPE);
			DataReport report=(DataReport) mongoUtils.findByClazz(paramMap,null, DataReport.class);
			if(report!=null){
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
			}else{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, CodeUtils.APPLICATION_MONGO_NULL);
			}
		} catch (Exception e) {
			logger.error("查询同盾报告错误,[applicationId=" + applicationId + "][type=" + type + "][platformId=" + platformId + "]", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}