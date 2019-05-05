package com.shangyong.backend.service;

import java.util.Map;

public interface TplRulePlatformLoanRemindService {

	/**
	 * 查询申请单模板数量
	 * @param recordSize 申请单数量
	 * @param tplType 模板分发类型 （决策树 禁止项 欺诈项 评分项）
	 * @return
	 */
	public Map<String, Object> getValidTmpl(Integer recordSize, String tplType);

}
