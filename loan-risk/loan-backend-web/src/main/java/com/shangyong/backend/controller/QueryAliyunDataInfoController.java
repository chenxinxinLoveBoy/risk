package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuPromoteDetailed;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.service.BuPromoteDetailedService;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.report.impl.JsonReportServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;


/**
 * 查询阿里云第三方征信信息
 * @author hc
 *
 */
@Controller
@RequestMapping(value ="/backend/queryAliyunDataInfo")
public class QueryAliyunDataInfoController {

	private static Logger logger = Logger.getLogger(QueryAliyunDataInfoController.class);
	/**
	 * json报告信息service
	 */
	@Autowired
	private JsonReportServiceImpl jsonReportServiceImpl;
	
	/**
     * 第三方报告表service实现
     */
    @Autowired
    private BuThirdpartyReportService buThirdpartyReportService;
    
    /** 
     *  申请单信息 service实现类
     */
    @Autowired
    ApplicationServiceImpl applicationServiceImpl;
    
    /**
     * 客户提额申请明细 service实现类
     */
    @Autowired
    BuPromoteDetailedService buPromoteDetailedService;
	
	/**
	 * 根据applicationId,taskType获取 客户借款申请扩展表响应josn报文存储地址
	 * @param request
	 * @param response
	 * @param taskType
	 * @param applicationId
	 */
	@RequestMapping(value ="/getInfo.do",method = RequestMethod.POST )
	public void getInfo(HttpServletRequest request, HttpServletResponse response, String taskType, String applicationId, String platformId) {
		try {  
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>(); 
			if(StringUtils.isBlank(applicationId)){
				Application application = applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				applicationId = application.getApplicationId();
				BuThirdpartyReport buThirdpartyReport =  new BuThirdpartyReport();
				buThirdpartyReport.setBuApplicationId(applicationId);
				buThirdpartyReport.setTaskType(taskType);
				logger.info("taskType:"+taskType);
				logger.info("applicationId:"+applicationId);
				String josnStoragePath =   buThirdpartyReportService.getJosnStoragePathBytaskTypeApplicationId(buThirdpartyReport);
				logger.info("根据applicationId--taskType查询-->客户借款申请扩展表响应josn报文存储地址"+josnStoragePath); 
				logger.info("响应josn报文存储地址:"+josnStoragePath);
				if(StringUtils.isNotBlank(josnStoragePath)){
					String  info  = jsonReportServiceImpl.downloadossJson(josnStoragePath); 
					logger.info("根据存储地址查询信息为:"+info);
					map2.put("info", info);
					logger.info("阿里云返回信息:"+info);
					map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
					map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
					map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
					map.put(Constants.DATAS, map2);
				} 
				SpringUtils.renderJson(response, map);
			}else{ 
				BuThirdpartyReport buThirdpartyReport =  new BuThirdpartyReport();
				buThirdpartyReport.setBuApplicationId(applicationId);
				buThirdpartyReport.setTaskType(taskType);
				logger.info("taskType:"+taskType);
				logger.info("applicationId:"+applicationId);
				String josnStoragePath =   buThirdpartyReportService.getJosnStoragePathBytaskTypeApplicationId(buThirdpartyReport);
				logger.info("根据applicationId--taskType查询-->客户借款申请扩展表响应josn报文存储地址"+josnStoragePath); 
				logger.info("响应josn报文存储地址:"+josnStoragePath);
				if(StringUtils.isNotBlank(josnStoragePath)){
					String  info  = jsonReportServiceImpl.downloadossJson(josnStoragePath); 
					logger.info("根据存储地址查询信息为:"+info);
					map2.put("info", info);
					logger.info("阿里云返回信息:"+info);
					map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
					map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
					map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
					map.put(Constants.DATAS, map2);
				} 
				SpringUtils.renderJson(response, map);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}  
	
	
	/**
	 * 根据taskType,customerId获取 客户提额申请明细表 响应josn报文存储地址
	 * @param request
	 * @param response
	 * @param taskType
	 * @param customerId
	 */ 
	@RequestMapping(value ="/getMhXinXiInfo.do",method = RequestMethod.POST )
	public void getMhXinXiInfo(HttpServletRequest request, HttpServletResponse response, String taskType, String customerId) {
		try {  
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>(); 
			if(StringUtils.isNotBlank(customerId)){
				BuPromoteDetailed buPromoteDetailed =  new BuPromoteDetailed();
				buPromoteDetailed.setTaskType(taskType);
				buPromoteDetailed.setCustomerId(customerId);
				logger.info("taskType:"+taskType);
				logger.info("customerId:"+customerId);
				String josnStoragePath =  buPromoteDetailedService.getJosnStoragePathBytaskTypeCustomerId(buPromoteDetailed);
				logger.info("根据customerId--taskType查询-->客户提额申请明细表响应josn报文存储地址"+josnStoragePath); 
				logger.info("响应josn报文存储地址:"+josnStoragePath);
				if(StringUtils.isNotBlank(josnStoragePath)){
					String  info  = jsonReportServiceImpl.downloadossJson(josnStoragePath); 
					logger.info("根据存储地址查询信息为:"+info);
					map2.put("info", info);
					logger.info("阿里云返回信息:"+info);
					map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
					map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
					map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
					map.put(Constants.DATAS, map2);
				} 
				SpringUtils.renderJson(response, map);
			} else{
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}  
}
