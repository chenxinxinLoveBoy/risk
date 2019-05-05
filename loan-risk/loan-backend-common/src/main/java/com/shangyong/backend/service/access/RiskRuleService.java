package com.shangyong.backend.service.access;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;

/**
 *  风控规则校验
 * @author xiangxianjin
 *
 */
public interface RiskRuleService {

	/**
	 * 风控规则校验
	 * @param application
	 * @param checkList                        List<Map<String,Object>> checkList 检测项
	 * @return RuleResult					{isblack: true, message: "金融黑名单"}
	 */
	public RuleResult querySafeRuleApi(Application application, List<Map<String, Object>> checkList);

	/**
	 *  风控规则校验
	 * @param application
	 * @param checkList                        List<Map<String,Object>> checkList 检测项
	 * @param isPresent
	 * @return RuleResult					{isblack: true, message: "金融黑名单"}
	 */
	@Deprecated
	public RuleResult querySafeRuleApi(Application application, List<Map<String, Object>> checkList, boolean isPresent);

	/**
	 * 风控规则校验
	 * 仅适用于准入规则
	 *
	 * @param application
	 * @return
	 * @throws Exception
	 */
	public RuleResult checkSafeRuleBySystem(Application application) throws Exception;
}
