package com.shangyong.backend.service;

/**
 * 规则比对
 * @author xiangxianjin
 *
 */
public interface RiskRuleCheckService {
	
	/**
	 * 比对并返回结果，是否比对命中
	 * @param ruleComparisonType  比对类型
	 * @param validateRule	技术校验规则	
	 * @param ruleValue			禁止项规则技术比对值
	 * @param checkValue		需比对值
	 */
	public boolean ruleCheck(String ruleComparisonType, String validateRule, String ruleValue, Object checkValue);
	
}
