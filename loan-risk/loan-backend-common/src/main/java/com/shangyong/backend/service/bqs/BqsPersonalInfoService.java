package com.shangyong.backend.service.bqs;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;

public interface BqsPersonalInfoService {
	/**
	 * 根据运营商获取用户信息
	 * @param application
	 * @return
	 */
	public RuleResult getBqsPersonalInfo(Application application);
}
