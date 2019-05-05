package com.shangyong.backend.service.yjf;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.yjf.YjfInfoCheck;

/**
 * 易极付黑名单查询
 * @author Smk
 *
 */
public interface YjfBlackListService {

	/**
	 * 易极付黑名单数据落地
	 * @param application
	 * @return
	 */
	RuleResult saveYjfBlackList(Application application);

	/**
	 * 易极付黑名单数据获取	
	 * @param applicationId
	 * @return
	 */
	YjfInfoCheck getJyjfBlackListkByAid(String applicationId);

}
