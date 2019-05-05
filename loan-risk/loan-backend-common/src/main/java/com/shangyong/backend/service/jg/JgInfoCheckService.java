package com.shangyong.backend.service.jg;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.jg.JgInfoCheck;

public interface JgInfoCheckService {
/**
 * 获取极光黑名单认证信息
 * @param application
 * @return
 */
	public RuleResult getJqInfoCheck(Application application);
	/**
	 * 查取JgInfoCheck对象
	 * @param applicationId
	 * @return
	 */
	public JgInfoCheck getJgInfoCheckByAid(String applicationId);
}
