package com.shangyong.backend.service.tdReport;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
/**
 * 同盾报告获取接口
 * @author Smk
 *
 */
public interface TdReportService {
	
	RuleResult queryTdReport(Application application);
	
}
