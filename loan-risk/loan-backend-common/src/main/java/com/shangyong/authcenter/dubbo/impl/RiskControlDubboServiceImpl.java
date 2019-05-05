package com.shangyong.authcenter.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.shangyong.authcenter.dubbo.RiskControlDubboService;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.entity.BaseResult;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class RiskControlDubboServiceImpl {
	@Reference(version ="1.0.0",retries = -1,timeout = 5000)
	private RiskControlDubboService riskControlDubboService;

	@Autowired
	private IScAlarm scAlarmImpl;
	
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("RiskControlDubboServiceImpl");

	public void increaseQuota(String quotaProportio) {
		logger.info("当前时间：" + new Date() + ", 服务开始,向app客户端推送【提升额度比例】：" + quotaProportio);
		BaseResult<T> baseResult = null;
		try {
			baseResult = riskControlDubboService.increaseQuota(quotaProportio);
		} catch (Exception e) {
			// 获取DD通知URL
			scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,"向app客户端推送【提升额度比例】：" + quotaProportio + "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
//			DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 向app客户端推送【提升额度比例】：" + quotaProportio + "; 错误信息：" + e.getMessage());
		    throw new RuntimeException("向app客户端推送【提升额度比例】异常");
		}
		logger.info("当前时间：" + new Date() + ", 向app客户端推送【提升额度比例】：" + quotaProportio + ", 调用结果response："	+ baseResult.getCode() + ",服务结束");
	}
	
	
	public BaseResult<T> backExpTask(Map<String,Object> map){
		logger.info("当前时间：" + new Date() + ", APP用户提额申请上送数据：" + map.toString());
		BaseResult<T> baseResult = null;
		try {
			baseResult = riskControlDubboService.backExpTask(map);
		} catch (Exception e) {
			e.printStackTrace();
			// 获取DD通知URL
			scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,", APP用户提额申请：" + map.toString() + "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
//			DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", APP用户提额申请：" + map.toString() + "; 错误信息：" + e.getMessage());
		    throw new RuntimeException("APP用户提额申请异常");
		}
		logger.info("当前时间：" + new Date() + ", 调用结果response："	+ baseResult.getCode() + ",服务结束");
		return baseResult;
	}
	
}
