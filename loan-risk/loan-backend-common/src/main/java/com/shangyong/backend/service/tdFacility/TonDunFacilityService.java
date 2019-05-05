package com.shangyong.backend.service.tdFacility;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;

public interface TonDunFacilityService {
	
	/** 查询同盾设备指纹接口**/
	RuleResult acquire(Application application);
}
