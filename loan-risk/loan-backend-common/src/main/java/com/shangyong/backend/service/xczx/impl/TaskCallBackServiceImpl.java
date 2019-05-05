package com.shangyong.backend.service.xczx.impl;

import com.shangyong.backend.service.TdLoanInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.service.xczx.TaskCallBackService;

@Service
public class TaskCallBackServiceImpl implements TaskCallBackService {
	
	private static Logger xczxLogger = (Logger) LoggerFactory.getLogger("creditXC");
	
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;
	
	@Transactional
	@Override
	public  void callBackUpdateStatus(RuleResult result,String applicationId,String appLevel) {
//		tdLoanInterfaceServicesImpl.updateApplicationByRuleResult(result, applicationId, currentStep, Constants.XC_91ZX_STEP, Constants.XC_91ZX_NAME, xczxLogger);
		xczxLogger.info("申请编号："+applicationId+"调用"+Constants.XC_91ZX_NAME+"接口返回信息，isblack：" + result.getIsblack()+"，message："+result.getMessage());
		String auditResult ="";
		String auditingState ="";
		String banCode = "";
		if(result.getIsblack()){
    		 auditingState = Constants.NOPASS_SP_STAATE; //表示命中规则，审批不通过
    		 auditResult = result.getMessage();
    		 banCode = result.getBlackCode();
		}else{
			auditingState = Constants.DAI_SP_STAATE; 
		}


		//接口调用完，更新审批状态等字段。
//		Map<String, Object> dataMapParam = new HashMap<String, Object>();
//		dataMapParam.put("isStep", Constants.XC_91ZX_CALL_STEP); //步骤标识（1：芝麻信用评分、2：芝麻信用欺诈清单、3：同盾贷前审核、4：聚信立蜜蜂、5：聚信立蜜罐、6：芝麻行业清单、7：白骑士、8：91征信）
//		dataMapParam.put("auditResult", auditResult); //审批结果
//		dataMapParam.put("auditingState", auditingState);//审核状态
//		dataMapParam.put("applicationId",applicationId);//申请编号
//		dataMapParam.put("banCode", banCode);//禁止项规则对应编号
//		dataMapParam.put("currentStep", Constants.XC_91ZX_STEP);//当前步骤号
//		dataMapParam.put("errorDescription", "");//清空步骤处理异常描述

		//接口走完，更新步骤标识、审批状态、审批结果描述
		//tdLoanInterfaceServicesImpl.updateApplicationByStep(dataMapParam);
		tdLoanInterfaceService.updateApplicationByRuleResult(result, applicationId,Constants.XC_91ZX_STEP ,Constants.XC_91ZX_CALL_STEP, Constants.XC_91ZXCALL_NAME, appLevel, xczxLogger);
		xczxLogger.info(Constants.XC_91ZX_NAME+"接口定时任务结束，审核状态【"+auditingState+"】，审批结果【"+auditResult+"】");
	}
}
