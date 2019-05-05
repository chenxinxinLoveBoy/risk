package com.shangyong.backend.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.dubbo.GetCustomerAppLevelService;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * 获取客户标识
 * 
 * @author xk
 *
 */
@Service(version = "1.0.0")
public class GetCustomerApplevelDubboServiceImpl implements GetCustomerAppLevelService {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("customerApplevel");
	@Autowired
	private ApplicationServiceImpl applicationService;

	@Autowired
	private IScAlarm scAlarmImpl;

	@Override
	public String getCustomerAppLevelService(Map<String, String> map) {
		String appLevel = "";
		logger.info("当前时间：" + new Date() + ", 催收系统调取【获取客户标识】服务开始");
		String customerId = "";
		String appName = "";
		try {
			if (map != null) {
				customerId = map.get("customerId");
				appName = map.get("appName");
			}
			logger.info("当前时间：" + new Date() + ", 传入客户编号：" + customerId + ",传入平台类型(1:闪贷;2:速贷)：" + appName);
			if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotEmpty(appName)) {
				appLevel = applicationService.getCustomerAppLevel(customerId, appName);
				if (StringUtils.isEmpty(appLevel)) {
					logger.info("根据传入的参数查询客户标识为空,返回空字符");
				}
			} else {
				logger.info("传入的参数不能为空,返回空字符");
			}
		} catch (Exception e) {
			scAlarmImpl.contains(AlarmCodeEnum.COLLECTION_DOCKING,"传入客户编号：" + customerId + ",传入平台类型(1:闪贷;2:速贷)：" + appName + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.COLLECTION_DOCKING);
			logger.error("GetCustomerAppLevelService error", e);
		}
		return appLevel;
	}
}
