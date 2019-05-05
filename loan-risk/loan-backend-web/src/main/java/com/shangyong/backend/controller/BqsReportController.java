package com.shangyong.backend.controller;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.bqsrep.vo.BqsRepPetitionerVo;
import com.shangyong.backend.entity.bqsrep.vo.BqsReportPage;
import com.shangyong.backend.service.SysParamService;
import com.shangyong.backend.service.bqs.BqsReportInfoService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.DataReport;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/backend/bqsReport/")
public class BqsReportController {
	private static Logger logger = Logger.getLogger(BqsReportController.class);
	@Autowired
	private BqsReportInfoService bqsReportInfoService;
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	@Autowired
	private MongoUtils mongoUtils;
	@Autowired
	private SysParamService sysParamService;
	
	/**
	 * mysql获取bqs客户信息详情
	 * 
	 * @param request
	 * @param response
	 * @param cuPlatformCustomer
	 */
	@RequestMapping(value = "getbqsReportMysqlByApplicationId.do", method = RequestMethod.POST)
	public void getbqsReportMysqlByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId, String platformId) {
		
		try {
			
			if(StringUtils.isBlank(applicationId)){
				
				Application application= applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				
				if(application!=null){
					applicationId = application.getApplicationId();
				}else{
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
				}
			}
			BqsRepPetitionerVo bqsRepPetitionerVo = new BqsRepPetitionerVo();
			bqsRepPetitionerVo = bqsReportInfoService.queryById(applicationId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, bqsRepPetitionerVo, "bqsRepPetitionerVoObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * mongo获取bqs客户信息详情	
	 * @param request
	 * @param response
	 * @param applicationId
	 * @param type
	 */
	@RequestMapping(value = "getbqsReportByApplicationId.do", method = RequestMethod.POST)
	public void getbqsReportByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId, String type, String platformId) {
		
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
			paramMap.put("taskType", TaskTypeConstants.BQS_REPORT_UPLOAD_DIR);
			DataReport report=(DataReport) mongoUtils.findByClazz(paramMap,null, DataReport.class);
			if(report!=null){
				JSONObject jsonInfo = JSONObject.fromObject(report.getJsonInfo());
				JSONObject dataJson = jsonInfo.getJSONObject("data");
				dataJson.remove("mnoCommonlyConnectMobiles");
				dataJson.remove("mnoOneMonthCommonlyConnectMobiles");
				dataJson.remove("mnoThreeMonthCommonlyConnectMobiles");
				dataJson.remove("mno7daysCommonlyConnectMobiles");
				dataJson.remove("mno3daysCommonlyConnectMobiles");
				dataJson.remove("mnoCommonlyConnectAreas");
				dataJson.remove("mnoContactsCommonlyConnectAreas");
				dataJson.remove("mnoConnectMobilesExt");
				dataJson.remove("mnoPeriodUsedInfos");
		
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,dataJson);
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
	
	/**
	 * 白骑士资信报告
	 * @param request
	 * @param response
	 * @param applicationId
	 * @param platformId
	 */
	@RequestMapping(value = "bqiReport.do", method = RequestMethod.POST)
	public void bqiReport(HttpServletRequest request, HttpServletResponse response, String applicationId, String platformId) {
		
		try {
			
			if(StringUtils.isBlank(applicationId) && StringUtils.isBlank(platformId)){
				JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
			}
			Map<String, String> param = new HashMap<>();
			param.put("applicationId", applicationId);
			param.put("platformId", platformId);
			
			Application application= applicationServiceImpl.selectApplicationByParam(param);
			
			Map<String, String> requestMap = new HashMap<>();
			
			String certNo = application.getCertCode();
			
			SysParam sysParam = sysParamService.queryByParamValue(Constants.BQS_CLWEB_TOKEN);
			String bqsPartnerId = sysParam.getParamValueTwo();
			String bqsVerifyKey = sysParam.getParamValueThree();
			String dateTime = DateUtils.getDate(new Date());
			requestMap.put("partnerId", bqsPartnerId);
			requestMap.put("verifyKey", bqsVerifyKey);
			requestMap.put("timeStamp", dateTime);
			requestMap.put("certNo", certNo);
			String jsonStr = JSON.toJSONString(requestMap);
			String operatorInfoUrl = sysParam.getParamValueOne();
			logger.info(">>>>调用白骑士获取token数据请求>>jsonStr:" + jsonStr);
			String content = RiskHttpClientUtil.doPostJson(operatorInfoUrl, jsonStr);
			logger.info(">>>>调用白骑士获取token返回数据>>content:" + content);
			JSONObject jsonObj = JSONObject.fromObject(content);
			
			if(jsonObj.get("resultCode").equals("CCOM9999")){
				JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
			}
			SysParam bqiSysParam = sysParamService.queryByParamValue(Constants.BQS_CLWEB_PAGE);
			String token = jsonObj.get("data").toString();
			String name = application.getName();
			String mobile = application.getPhoneNum();
			
			BqsReportPage bqsReportPage = new BqsReportPage();
			bqsReportPage.setCertNo(certNo);
			bqsReportPage.setMobile(mobile);
			bqsReportPage.setName(name);
			bqsReportPage.setPartnerId(bqsPartnerId);
			bqsReportPage.setTimeStamp(dateTime);
			bqsReportPage.setToken(token);
			bqsReportPage.setUrl(bqiSysParam.getParamValueOne());
			
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, bqsReportPage, "bqsReportPageObject");
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

}
