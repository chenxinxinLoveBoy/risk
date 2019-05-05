package com.shangyong.shangyong.jobs;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.service.RabbitMqServer;
import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.shangyong.service.access.LoanAccessService;
import com.shangyong.shangyong.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 *
 * @author caisheng
 *
 * @date 2018年7月25日
 *
 */
@Component
public class ShangYongLoanAccessJob implements Job {
	
	private static Logger logger = LoggerFactory.getLogger("SYLoanAccessTask");
	
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	
	@Autowired
	private LoanAccessService loanAccessService;
	
	@Autowired
	private RabbitMqServer rabbitMqServer;
	
//    @Override
//    public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam, SaturnJobExecutionContext shardingContext) throws InterruptedException {
//        scheduler();
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }
    
	public void scheduler() {
		long beginTs = System.currentTimeMillis();
		try {
			logger.info("[准入规则] 定时任务获取待处理数据，当前任务处理心跳正常");
			
			//定时任务处理步骤号97
			String isStep = Constants.SY_RULE_ID;
			
			//查询步骤号为97的申请单信息
			List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep);
			
			if (CollectionUtils.isNotEmpty(list)) {
				
				logger.info("[准入规则] 定时任务获取待处理数据" + list.size() + "条，当前定时任务处理开始");
				
				//审批单初始化（组织申请单信息）
				List<Application> appList = initApplication(list);
				
				//走准入规则并更新审批单信息（进行尚咏准入规则校验）
				for(Application application : appList){
					boolean booleanOldUser = loanAccessService.processApplication(application);
					if (booleanOldUser) {
						//发送消息 给 rabbitMQ
						rabbitMqServer.sendMqForFraudScoreOldUser(application.getApplicationId());
						logger.info("[准入规则] 老用户发送MQ消息,[application="+application.getApplicationId()+"]");	
					}
				}
				logger.info("[准入规则] 定时任务获取待处理数据" + list.size() + "条，当前定时任务处理结束");
			}else{
				logger.info("[准入规则] 定时任务获取待处理0数据，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			logger.error("[准入规则] 查询准入规则待处理数据返回错误信息：-->"+e.getMessage(), e);
		}
		logger.info("[准入规则] 定时任务处理结束，用时:" +  (System.currentTimeMillis() - beginTs) + " ms" );
	}
	
	/**
	 * 审批单初始化
	 * @param list
	 * @return
	 */
	public List<Application> initApplication(List<Map<String, Object>> list){
		
		List<Application> appList = new ArrayList<Application>();

		//待更新申请单对象
		Application application = null;
		
		for(Map<String, Object> map : list){

			String applicationId = (String) map.get("applicationNumber");//申请单标号
			String appLevel = map.get("appLevel") == null ? "" : map.get("appLevel").toString();//客户类型
			String platformId = map.get("platformId") == null ? "" : map.get("platformId").toString();//平台客户编号
			String getAppSerialNumber = map.get("appSerialNumber") == null ? "" : map.get("appSerialNumber").toString();//app申请单号
			String customerId = map.get("customerId") == null ? "" : map.get("customerId").toString();//APP应用客户编号
			String appName = map.get("appName") == null ? "" : map.get("appName").toString();//APP名称：1-闪贷；2-速贷
			String source = map.get("source") == null ? "" : map.get("source").toString();//申请来源（0——Android；1——IOS）
			String certCode = map.get("certCode") == null ? "" : map.get("certCode").toString();//身份证
			String phoneNum = map.get("phoneNum") == null ? "" : map.get("phoneNum").toString();//手机号
			String name = map.get("name") == null ? "" : map.get("name").toString();//客户姓名
			String zhiMaScore = map.get("zhiMaScore") == null ? "" : map.get("zhiMaScore").toString();//芝麻分

			//获取申请单的信息
			application = new Application();
			application.setApplicationId(applicationId);
			application.setAppLevel(appLevel);
			application.setPlatformId(platformId);
			application.setAppSerialNumber(getAppSerialNumber);
			application.setCustomerId(customerId);
			application.setAppName(appName);
			application.setSource(source);
			application.setCertCode(certCode);
			application.setPhoneNum(phoneNum);
			application.setName(name);
			application.setZhiMaScore(zhiMaScore);
			appList.add(application);
		}
		return appList;

	}


    public static Object getObject() {
    	ShangYongLoanAccessJob instance = (ShangYongLoanAccessJob) SpringContextUtils.getBean(ShangYongLoanAccessJob.class);
        return instance;
    }

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
