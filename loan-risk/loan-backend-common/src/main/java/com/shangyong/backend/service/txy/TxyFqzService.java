package com.shangyong.backend.service.txy;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.txy.vo.TxyAntiFraudVo;

/**
 * 腾讯云反欺诈接口
 * 
 * 
 * 2017-12-9  石明科
 * **/
public interface TxyFqzService {
	/**
	 * 保存腾讯反欺诈数据
	 * @param application
	 * @return
	 */
	RuleResult getTxyFqzInfo(Application application);
	/**
	 * 查询腾讯云反欺诈数据
	 * @param applicationId
	 * @return
	 */
	TxyAntiFraudVo queryTxyAntFraudVo(String applicationId);
	
}
