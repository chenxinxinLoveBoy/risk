package com.shangyong.shangyong.jobs;


import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.ScDecisionTreeDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.backend.service.TplRulePlatformLoanRemindService;
import com.shangyong.shangyong.service.ShangYongLoanRemindProcessService;
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
import java.util.Map.Entry;

/**
 * @Description
 *
 * @author caisheng
 *
 * @date 2018年7月25日
 *
 */
@Component
public class ShangYongLoanRemindJob implements Job {
	private static Logger logger = LoggerFactory.getLogger("SYLoanRemindTask");
	
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	
	@Autowired
	private TplRulePlatformLoanRemindService tplRulePlatformLoanRemindService;

	@Autowired
	private ScDecisionTreeDao scDecisionTreeDao;
	
	@Autowired
	private ShangYongLoanRemindProcessService shangYongLoanRemindProcessService;
//    @Override
//    public SaturnJobReturn handleJavaJob(String jobName, Integer shardItem, String shardParam, SaturnJobExecutionContext shardingContext) throws InterruptedException {
//        scheduler();
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }
    

	public void scheduler() {
		try {
			//设置申请单步骤号
			String isStep = Constants.RULE_ID_SET_STEP;
			
			//获取申请单步骤号为97的数据集合
			List<Map<String, Object>> list = tdLoanInterfaceService.getAppLicationList(isStep);
			
			//判断集合不为空就走下面业务逻辑
			if (CollectionUtils.isNotEmpty(list)) {
				
				logger.info("[决策树分配] 定时任务获取待处理数据" + list.size() + "条，当前定时任务处理开始");
				
				//审批单初始化
				List<Application> appList = initApplication(list);
				//决策树
				appList = processBanCodeTpl(appList, Constants.RULE_DECISION_TREE);
//				//分配禁止项模板
//				appList = processBanCodeTpl(appList, Constants.RULE_TPL_BAN_CODE);
//				//分配欺诈项模板
//				appList = processBanCodeTpl(appList, Constants.RULE_TPL_FRAUD);
//				//分配评分项模板
//				appList = processBanCodeTpl(appList, Constants.RULE_TPL_SCORE);
				//更新审批单模板信息

				shangYongLoanRemindProcessService.process(appList);
				
				logger.info("[决策树分配] 定时任务获取待处理数据" + list.size() + "条，当前定时任务处理结束");
			}else{
				logger.info("[决策树分配] 定时任务获取待处理数据0条，当前定时任务处理结束");
			}
		} catch (Throwable e) {
			logger.error("[决策树分配] 定时任务返回错误信息：-->"+e.getMessage(), e);
		}
	}
	 
	/**
	 * 审批单初始化
	 * @param list
	 * @return
	 */
	public List<Application> initApplication(List<Map<String, Object>> list){
		
		logger.info("[决策树分配] 定时任务审批单初始化处理条数： " + list.size() + "，处理开始");
		
		List<Application> appList = new ArrayList<Application>();
		
		Application application = null; //待更新申请单对象
		
		for(Map<String, Object> map : list){
			
			String applicationId = (String) map.get("applicationNumber");//申请单标号
			application = new Application();
			application.setApplicationId(applicationId);
			appList.add(application);
		}
		
		logger.info("[决策树分配] 定时任务审批单初始化处理条数： " + list.size() + "，处理结束");
		return appList;

	}
	
	
	/**
	 * 为审批单分配模板Id
	 * @param list
	 * @param tplType 模板类型
	 */
	public List<Application> processBanCodeTpl(List<Application> list, String tplType){
		
		logger.info("[决策树分配] 定时任务分配模板处理条数： " + list.size() + "，处理开始");
		
		Map<String, Object> tplMap = tplRulePlatformLoanRemindService.getValidTmpl(list.size(), tplType);
		if(tplMap == null){
			return list;        
		}
		String templId = ""; //模板ID
		int templSize = 0; //模板分配数量
		int start = 0;
		int end = 0;
		
		for (Entry<String, Object> entry : tplMap.entrySet()) {  
			
			templId = entry.getKey();
			templSize = (int)entry.getValue();
			end = end + templSize;
			
			//循环给每个申请单分配决策树
			for(int i=start; i<end; i++){
				
				//更新申请单的模板ID
				Application application = list.get(i);
				
				//分配禁止项模板
				if(Constants.RULE_TPL_BAN_CODE.equals(tplType)){
					application.setBanCodeTplId(templId);
					
				//分配欺诈项模板
				}else if(Constants.RULE_TPL_FRAUD.equals(tplType)){
					application.setFraudTplId(templId);
					
				//分配信用评分模板
				}else if(Constants.RULE_TPL_SCORE.equals(tplType)){
					application.setScoreTplId(templId);
					
				//分配决策树
				}else if(Constants.RULE_DECISION_TREE.equals(tplType)){
					
					application.setDecisionTreeId(templId);
					ScDecisionTree scDecisionTree = new ScDecisionTree();
					scDecisionTree.setDecisionTreeId(Integer.parseInt(templId));
					scDecisionTree = scDecisionTreeDao.getEntityById(scDecisionTree);
					
					if(scDecisionTree!=null && Constants.STATE_NORMAL.equals(scDecisionTree.getState())){
						
						logger.info("[决策树分配] 模板规则引擎定时任务分配禁止项模板："+scDecisionTree.getBanControlTplId()
								+"，欺诈项模板："+scDecisionTree.getFraudRuleTplId()
								+"，信用评分模板："+scDecisionTree.getScoreTplId());
						application.setBanCodeTplId(scDecisionTree.getBanControlTplId().toString());
						application.setFraudTplId(scDecisionTree.getFraudRuleTplId().toString());
						application.setScoreTplId(scDecisionTree.getScoreTplId().toString());
					}
				}
			}
			start = start +templSize;
		}
		logger.info("[决策树分配] 定时任务分配模板处理条数： " + list.size() + "，处理结束");
		return list;
	}
	


    public static Object getObject() {
    	ShangYongLoanRemindJob instance = (ShangYongLoanRemindJob) SpringContextUtils.getBean(ShangYongLoanRemindJob.class);
        return instance;
    }

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
