package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.td.TdRiskItemsServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.td.vo.TdRiskItemCount;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/td")
public class TdInfoController {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(TdInfoController.class);
	
	@Autowired
	private MongoUtils mongoUtils;
	
	@Autowired
    ApplicationServiceImpl applicationServiceImpl;
	
	@Autowired
    TdRiskItemsServiceDao tdRiskItemsServiceDao;
	
	/**
	 * 查询报告信息
	 * @param request
	 * @param response
	 * @param platformCustomerId 平台用户编号
	 */
//	@RequestMapping(value="/getReport.do", method=RequestMethod.POST)
//	public void getReport(HttpServletRequest request, HttpServletResponse response, String platformCustomerId,String applicationId) {
//		try{
//			if(StringUtils.isBlank(applicationId) && StringUtils.isBlank(platformCustomerId)){
//				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
//				map1.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
//				map1.put(Constants.MESSAGE, CodeUtils.BACKEND_PRA_MISS.getMessage());
//				SpringUtils.renderJson(response, map1);
//				return;
//			}
//			TdAuditReport query = new TdAuditReport();
//			query.setPlatformCustomerId(platformCustomerId);
//			query.setApplicationNumber(applicationId);
//			TdAuditReport auditReport = this.tdAuditReportDao.getEntity(query);
//			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//			if(auditReport != null){
//				List<TdAscriptionAnalysis>   tdAscriptionAnalysis = this.tdAscriptionAnalysisDao.findAll(auditReport.getReportId());
//				List<TdAuditReport>   tdAuditReport = this.tdAuditReportDao.findAll(auditReport.getReportId());
//				List<TdRiskCheck>   tdRiskCheck = this.tdRiskCheckDao.findAll(auditReport.getReportId());
//				
//				List<TdRiskCheck>   tdRiskCheckByUserinfo = this.tdRiskCheckDao.findAllByUserinfo(auditReport.getReportId());
//				List<TdRiskCheck>   tdRiskCheckByBlxx = this.tdRiskCheckDao.findAllByBlxx(auditReport.getReportId());
//				List<TdRiskCheck>   tdRiskCheckByDpt = this.tdRiskCheckDao.findAllByDpt(auditReport.getReportId());
//				List<TdRiskCheck>   tdRiskCheckByKhxw = this.tdRiskCheckDao.findAllByKhxw(auditReport.getReportId());
//				
//				List<TdRiskCheckDetail>   tdRiskCheckDetail = this.tdRiskCheckDetailDao.findAll(auditReport.getReportId());
//				List<TdRiskCourt>   tdRiskCourt = this.tdRiskCourtDao.findAll(auditReport.getReportId());
//				
//				map.put("tdAscriptionAnalysis", tdAscriptionAnalysis);
//				map.put("tdAuditReport", tdAuditReport);
//				map.put("tdRiskCheck", tdRiskCheck);
//				map.put("tdRiskCheckByUserinfo", tdRiskCheckByUserinfo);
//				map.put("tdRiskCheckByBlxx", tdRiskCheckByBlxx);
//				map.put("tdRiskCheckByDpt", tdRiskCheckByDpt);
//				map.put("tdRiskCheckByKhxw", tdRiskCheckByKhxw);
//				map.put("tdRiskCheckDetail", tdRiskCheckDetail);
//				map.put("tdRiskCourt", tdRiskCourt);
//				map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
//			}else{
//				map.put(Constants.SUCCESSED, CodeUtils.FAIL.getFlag());
//			}
//			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
//			map1.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
//			map1.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
//			map1.put(Constants.DATA, map);
//			SpringUtils.renderJson(response, map1);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
	
	/**
	 * 查询同盾命中审核统计
	 * 
	 * @param request
	 * @param response
	 * @param applicationCount
	 */
	@RequestMapping(value = "/queryTdResult.do", method = RequestMethod.POST)
	public void queryResultCountInfo(HttpServletRequest request, HttpServletResponse response,
                                     TdRiskItemCount tdRiskItemCount) {
		
		try {
			List<TdRiskItemCount> queryTdResultCount = tdRiskItemsServiceDao.queryTdResultCount(tdRiskItemCount);
			JSONUtils.toListJSON(response, queryTdResultCount,queryTdResultCount.size()-1);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR,e.getMessage());
		}
	}
//
//	/**
//	 * 获取同盾时间段内命中项数据统计图
//	 * @param request
//	 * @param response
//	 * @param TdRiskCheckCount
//	 */
//	@RequestMapping(value = "/getTdHistogram.do", method = RequestMethod.POST)
//	public void getTdHistogram(HttpServletRequest request, HttpServletResponse response,
//			TdRiskCheckCount tdRiskCheckCount) {
//		try {
//			List<TdRiskCheckCount> list = tdRiskCheckDao.getTdHistogram(tdRiskCheckCount);
//			int listSize = list.size();
//			String a[] = new String[listSize];
//			int b[] = new int[listSize];
//			for (int i = 0; i < listSize; i++) {
//				a[i] = list.get(i).getItemName();  
//				b[i] =  Integer.valueOf(list.get(i).getCount()).intValue();
//			}
//			Map<Object, Object> map = new HashMap<Object, Object>();
//			map.put("itemName", a);
//			map.put("count", b);
//			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map, "tdRiskCheckCountObject");
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
	/**
	 * 获取同盾Json
	 * @param request
	 * @param response
	 * @param buApplicationId
	 * @param platformId
	 */
	@RequestMapping(value = "/getTdJsonReport.do", method = RequestMethod.POST)
	public void getTdJsonReport(HttpServletRequest request, HttpServletResponse response, String applicationId, String platformId) {
		try { 
			logger.info("----------》开始调用controller 时间:"+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
			long time1=System.currentTimeMillis();
			DataReport obj=new DataReport();
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(platformId) && applicationId == null || "".equals(applicationId)){
				Application application=applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				if(application != null){
					logger.info("----------》开始从mongoDB获取Json报文 时间："+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
					long time2=System.currentTimeMillis();
					map.put("applicationID", application.getApplicationId());
					map.put("taskType", Constants.THIRDPARTY_REPORT_TD);
					obj=(DataReport) mongoUtils.findByClazz(map, null, DataReport.class);
					logger.info("----------》从mongoDB获取Json报文结束 时间："+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
					long time3=System.currentTimeMillis();
					logger.info("----------》mongoDB耗时：" +(time3-time2)+"毫秒");
				}
			}else{
				logger.info("----------》开始从mongoDB获取Json报文 时间："+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
				long time2=System.currentTimeMillis();
				map.put("applicationID", applicationId);
				map.put("taskType", Constants.THIRDPARTY_REPORT_TD);
				obj=(DataReport) mongoUtils.findByClazz(map, null, DataReport.class);
				logger.info("----------》从mongoDB获取Json报文结束 时间："+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
				long time3=System.currentTimeMillis();
				logger.info("----------》mongoDB耗时：" +(time3-time2)+"毫秒");
			}
			long time4=System.currentTimeMillis();
			logger.info("----------》调用controller结束 时间:"+new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date()));
			logger.info("----------》controller耗时：" +(time4-time1)+"毫秒");
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, obj);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
