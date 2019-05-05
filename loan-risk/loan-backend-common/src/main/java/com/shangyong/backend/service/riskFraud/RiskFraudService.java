package com.shangyong.backend.service.riskFraud;

import com.shangyong.backend.entity.Application;

/**
 * 欺诈分1.0.0
 * @author Smk
 *
 */
public interface RiskFraudService {
	
	
	public String riskFraud(String fraudTplId, Application application);

}
