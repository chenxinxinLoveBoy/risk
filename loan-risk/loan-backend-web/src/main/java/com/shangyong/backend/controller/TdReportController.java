package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.SysParamRedisService;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backend/tdReport/")
public class TdReportController {
	private static Logger logger = Logger.getLogger(TdReportController.class);
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	@Autowired
	private BuThirdpartyReportService buThirdpartyReportService;
	
	/**
	 * 白骑士资信报告
	 * @param request
	 * @param response
	 * @param applicationId
	 * @param platformId
	 */
	@RequestMapping(value = "tdReport.do", method = RequestMethod.POST)
	public void bqiReport(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		
		try {
			
			if(StringUtils.isBlank(applicationId)){
				JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
		
			Map<String, String> requestMap = new HashMap<>();
			
			BuThirdpartyReport buThirdpartyReport = new BuThirdpartyReport();
			buThirdpartyReport.setBuApplicationId(applicationId);
			buThirdpartyReport.setTaskType(TaskTypeConstants.TD_REPORT_TYPE);
			String taskId = buThirdpartyReportService.getTaskId(buThirdpartyReport);
			/** 从获取具体配置参数**/
			SysParam param = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_REPORT_URL);
			String url = param.getParamValueOne();
			requestMap.put("url", url);
			requestMap.put("taskId", taskId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, requestMap, "bqsReportPageObject");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

}
