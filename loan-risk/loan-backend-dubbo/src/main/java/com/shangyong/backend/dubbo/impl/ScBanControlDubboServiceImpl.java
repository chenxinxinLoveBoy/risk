package com.shangyong.backend.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.dubbo.ScBanControlDubboService;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.impl.ScBanControlHisServiceImpl;
import com.shangyong.backend.service.impl.ScBanControlServiceImpl;
import com.shangyong.backend.service.impl.SysParamRedisServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

//注册为 Dubbo 服务

/**
 * 禁止项信息dubbo服务
 * 
 * @author xk
 *
 */
@Service(version = "1.0.0")
public class ScBanControlDubboServiceImpl implements ScBanControlDubboService {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("ScBanControlDubboService");

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	@Autowired
	private ScBanControlServiceImpl scBanControlService;

	@Autowired
	private ScBanControlHisServiceImpl scBanControlHisService;

	@Autowired
	private IScAlarm scAlarmImpl;
	
	@Override
	public ScBanControl queryByBanCode(String banCode) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 闪贷客户端调取服务开始, 传入参数禁止项编号：" + banCode);
		ScBanControl scBanControl = null;
		try {
			if (StringUtils.isNotEmpty(banCode)) {
				String defaultFraudTemplateId = "1";
				SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
				defaultFraudTemplateId = sysParam.getParamValueOne(); // 默认模板ID
				scBanControl = scBanControlService.queryByBanCodeAndId(banCode,defaultFraudTemplateId);
			} else {
				logger.info("传入的参数不能为空,返回null");
			}
		} catch (Exception e) {
			scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,"传入参数禁止项编号：" + banCode+ "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
//			DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 传入参数禁止项编号：" + banCode+ "; 错误信息：" + e.getMessage());
			logger.error("QueryByBanCode error", e);
		}
		return scBanControl;
	}

	@Override
	public List<ScBanControl> getAll(String date) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 闪贷客户端调取服务开始, 传入日期：" + date);
 		List<ScBanControl> list = null;
		try {
			if (StringUtils.isNotEmpty(date)) {
				list = scBanControlHisService.findAllScBanControlHisInfo(date+" 00:00:00",date+" 23:59:59");
			} else {
				logger.info("传入的参数不能为空,返回null");
			}
		} catch (Exception e) {
			scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,"传入日期：" + date + "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
			logger.error("ScBanControlDubboServiceImpl getAll error", e);
		}
		return list;
	}

}
