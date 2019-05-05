package com.shangyong.backend.dubbo;

import com.shangyong.common.DubboBaseResponse;

public interface BqsReportDubboService {
	
	/**
	 * 用户id：customerId
	 * 平台号：appSerialNumber
	 * 任务id：taskId
	 * **/
	public DubboBaseResponse getBqsReport(String customerId, String appSerialNumber, String taskId);
}
