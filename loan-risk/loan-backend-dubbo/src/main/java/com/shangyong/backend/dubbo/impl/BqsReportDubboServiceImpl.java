package com.shangyong.backend.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.dubbo.BqsReportDubboService;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.common.DubboBaseResponse;
import com.shangyong.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 
 * @author daniel
 *
 */
@Service(version = "1.0.0",timeout=15000)
public class BqsReportDubboServiceImpl implements BqsReportDubboService{

	private static Logger bqsReportDubboService = LoggerFactory.getLogger("bqsRep");
	
	@Autowired
	private BuThirdpartyReportDao buThirdpartyReportDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Override
	public DubboBaseResponse getBqsReport(String applicationId,String appSerialNumber,String taskId) {
		return updateBsqReport(applicationId,appSerialNumber,taskId);
	}
	public DubboBaseResponse updateBsqReport(String applicationId,String appSerialNumber,String taskId){
		DubboBaseResponse response = new DubboBaseResponse();
		Application application = new Application();
		try {
			Application app = applicationDao.getEntityById(appSerialNumber);
			
			if (app==null) {
				response.setErrorCode("500");
				response.setErrorMsg("未查询到用户的申请单数据，application"+application);
				response.setState("failure");
				bqsReportDubboService.info("未查询到用户的申请单数据，application"+application);
				return response;
			}
			app.setFailureTimes("0");
			app.setMobileWebsite("tdReport");
			applicationDao.updateEntityByApplicationId(app);
			bqsReportDubboService.info("更新申请单ApplicationId："+applicationId);
			BuThirdpartyReport thirdpartyReport = new BuThirdpartyReport();
			thirdpartyReport.setThirdpartyReportId(UUIDUtils.getUUID());
			thirdpartyReport.setBuApplicationId(app.getApplicationId());
			thirdpartyReport.setCreateTime(DateUtils.getDate(new Date()));
			thirdpartyReport.setTaskType("08010");
			thirdpartyReport.setTaskId(taskId);
			thirdpartyReport.setRemark("同盾taskid");
			buThirdpartyReportDao.saveEntity(thirdpartyReport);
			bqsReportDubboService.info("新增白骑士数据报告task_id");
			response.setErrorCode("200");
			response.setErrorMsg("成功，application"+application);
			response.setState("success");
			return response;
		} catch (Exception e) {
			bqsReportDubboService.error("更新白骑士错误数据报告失败：applicationId:"+applicationId, e);
			response.setErrorCode("500");
			response.setErrorMsg("更新白骑士错误数据报告失败：applicationId:"+applicationId);
			response.setState("failure");
			return response;
		}
	}
}
