package com.shangyong.backend.service.bqs;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.bqsrep.vo.BqsRepPetitionerVo;

/**
 * 获取白骑士报告
 * 2017-12-14
 * **/
public interface BqsReportInfoService {
	/**
	 * 保存白骑士报告
	 * @param application
	 * @return
	 */
	public RuleResult getReport(Application application);
	/**
	 * 查询白骑士报告
	 * @param applicationId
	 * @return
	 */
	public BqsRepPetitionerVo queryById(String applicationId);
	
}
