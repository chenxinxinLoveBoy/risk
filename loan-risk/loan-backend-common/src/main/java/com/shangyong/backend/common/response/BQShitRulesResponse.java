package com.shangyong.backend.common.response;

import java.io.Serializable;

/**
 * 白骑士，接收hitRules字段返回json对象 
 * @author xiajiyun
 *
 */
public class BQShitRulesResponse implements Serializable{

	private static final long serialVersionUID = -7611525675730065766L;

	/**
	 * 规则名称
	 */
	private String ruleName;
	
	/**
	 * 规则ID
	 */
	private String ruleId;
	
	/**
	 * 规则风险系数，只有权重策略模式下有效
	 */
	private Integer score;
	
	/**
	 * 规则决策结果，参见decision附录码表
	 */
	private String decision;
	
	/**
	 * 规则击中信息备注，会根据不同的规则模板返回相对应的格式。
	 */
	private String memo;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
