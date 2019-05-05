package com.shangyong.backend.service.xczx;

import com.shangyong.backend.common.RuleResult;

public interface TaskCallBackService {

	public void callBackUpdateStatus(RuleResult result, String applicationId, String appLevel);
}
