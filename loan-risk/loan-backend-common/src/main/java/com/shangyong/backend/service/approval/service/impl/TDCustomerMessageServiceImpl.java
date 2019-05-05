package com.shangyong.backend.service.approval.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.approval.service.TDCustomerMessageService;
import com.shangyong.backend.utils.RiskHttpClientUtil;

@Service
public class TDCustomerMessageServiceImpl implements TDCustomerMessageService {

	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

	@Override
	public String summaryFewMessage(String customerId, String appName) {
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.DSJ_TD_MESSAGE_URL);
		String requestUrl = sysParam.getParamValueOne();

		Map<String, String> param = new HashMap<String, String>();
		param.put("customer_id", customerId);
		param.put("app_name", appName);

		String result = RiskHttpClientUtil.doPost(requestUrl, param);
		return result;
	}

	@Override
	public String summaryAppInfo(String customerId, String appName) {
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.DSJ_TD_MESSAGE_URL);
		String requestUrl = sysParam.getParamValueTwo();

		Map<String, String> param = new HashMap<String, String>();
		param.put("customer_id", customerId);
		param.put("app_name", appName);

		String result = RiskHttpClientUtil.doPost(requestUrl, param);
		return result;
	}

	@Override
	public String summaryEquipment(String customerId, String appName) {
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.DSJ_TD_MESSAGE_URL);
		String requestUrl = sysParam.getParamValueThree();

		Map<String, String> param = new HashMap<String, String>();
		param.put("customer_id", customerId);
		param.put("app_name", appName);

		String result = RiskHttpClientUtil.doPost(requestUrl, param);
		return result;
	}

}
