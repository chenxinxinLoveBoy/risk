package com.shangyong.backend.service.td;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.td.vo.TdAntiFraudVo;

public interface TonDunfqzService {

	/**
	 * 保存同盾反欺诈
	 * @param application
	 * @return
	 */
	RuleResult queryFqz(Application application);

	/**
	 * 获取同盾反欺诈
	 * @param applicationId
	 * @return
	 */
	/*TdAntiFraudVo queryByAid(String applicationId);*/
}
