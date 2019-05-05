package com.shangyong.backend.controller.mongodb;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.AppCallRecords;
import com.shangyong.mongo.entity.DataReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/mongo")
public class MongodbController {
	
	private static Logger logger = LoggerFactory.getLogger(MongodbController.class);
	
	@Autowired
	private MongoUtils mongoUtils;

	@Autowired
    ApplicationServiceImpl applicationServiceImpl;
	
//	/**
//	 * 用于mongodb数据展示，比如91、白骑士等等
//	 * @param request
//	 * @param response
//	 * @param dataReport
//	 */
//	
	@RequestMapping(value = "/getMongoDisplay.do",method= RequestMethod.POST)
	public void getMongoDisplay(HttpServletRequest request, HttpServletResponse response, DataReport dataReport, String platformId){
		
		try {
			
			if(dataReport.getApplicationId() == null || dataReport.getApplicationId().equals(""))
			{
				Application application= applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				
				if(application!=null)
				{
					dataReport.setApplicationId(application.getApplicationId());
				}
			}
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("applicationId",dataReport.getApplicationId());
			paramMap.put("taskType", TaskTypeConstants.XCZX_TASK_TYPE);
			
			DataReport report=(DataReport) mongoUtils.findByClazz(paramMap,null, DataReport.class);
			if(report!=null)
			{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
			}
			else
			{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
//	
//	/**
//	 * 短信记录
//	 * @param request
//	 * @param response
//	 * @param appMessage
//	 */
//	@RequestMapping(value = "/getMongoMessageDisplay.do",method=RequestMethod.POST)
//	public void getMongoMessageDisplay(HttpServletRequest request,HttpServletResponse response,AppMessage appMessage){
//		
//		try {
//			logger.info("----------------开始进入短信记录Controller------------------------------");
//			long startime=System.currentTimeMillis();
//			Map<String,Object> paramMap=new HashMap<String, Object>();
//			paramMap.put("customerId",appMessage.getCustomerId());
//			paramMap.put("appName",appMessage.getAppName());
//			logger.info("----------------------调用短信记录mongodb查询------------------");
//			long startDate=System.currentTimeMillis();
//			AppMessage report=(AppMessage) mongoUtils.findByClazz(paramMap,null,AppMessage.class);
//			if(report!=null)
//			{
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
//			}
//			else
//			{
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
//			}
//			logger.info("----------------调用短信记录mongodb返回----------------------");
//			long endDate=System.currentTimeMillis();
//			logger.info("----------------mongodb查询总共耗时"+(endDate-startDate)/1000+"毫秒------------------");
//			long endtime=System.currentTimeMillis();
//			logger.info("----------------调用短信记录Controller总共耗时"+(endtime-startime)/1000+"毫秒------------------");
//		} catch (Exception e) {
//			e.printStackTrace();
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//	
	/**
	 * 通话记录
	 * @param request
	 * @param response
	 * @param appCallRecords
	 */
	@RequestMapping(value = "/getMongoCallDisplay.do",method= RequestMethod.POST)
	public void getMongoCallDisplay(HttpServletRequest request, HttpServletResponse response, AppCallRecords appCallRecords){
		
		try {
			logger.info("----------------开始进入通话记录Controller------------------------------");
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("customerId",appCallRecords.getCustomerId());
			paramMap.put("appName",appCallRecords.getAppName());
			logger.info("----------------------调用通话记录mongodb查询------------------");
			AppCallRecords report=(AppCallRecords) mongoUtils.findByClazz(paramMap,null, AppCallRecords.class);
			if(report!=null)
			{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
			}
			else
			{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
//	
//	/**
//	 * 用于mongod学信.个人征信数据展示
//	 * @param request
//	 * @param response
//	 * @param promoteDetailed
//	 * @param customerId
//	 */
//	@RequestMapping(value = "/getMongoDisplayInfo.do",method=RequestMethod.POST)
//	public void getMongoDisplayInfo(HttpServletRequest request,HttpServletResponse response,PromoteDetailed promoteDetailed){
//		
//		try { 
//			logger.info("----------------开始进入数据魔盒相关Controller------------------------------");
//			long startime=System.currentTimeMillis();
//			Map<String,Object> paramMap=new HashMap<String, Object>();
//			paramMap.put("customerId",promoteDetailed.getCustomerId());
//			paramMap.put("taskType",promoteDetailed.getTaskType());
//			logger.info("----------------------调用数据魔盒相关mongodb查询------------------");
//			long startDate=System.currentTimeMillis();
//			PromoteDetailed report=(PromoteDetailed) mongoUtils.findByClazz(paramMap,null,PromoteDetailed.class);
//			if(report!=null)
//			{
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS,report.getJsonInfo());
//			}
//			else
//			{
//				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
//			}
//			logger.info("----------------调用数据魔盒相关mongodb返回----------------------");
//			long endDate=System.currentTimeMillis();
//			logger.info("----------------mongodb查询总共耗时"+(endDate-startDate)/1000+"毫秒------------------");
//			long endtime=System.currentTimeMillis();
//			logger.info("----------------调用数据魔盒相关Controller总共耗时"+(endtime-startime)/1000+"毫秒------------------");
//		} catch (Exception e) {
//			e.printStackTrace();
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}	
}
