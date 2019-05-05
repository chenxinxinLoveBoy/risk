package com.shangyong.backend.common.response;

import java.io.Serializable;
import java.util.List;

/**
 * 白骑士，接收strategySet字段返回json对象
 * @author xiajiyun
 *
 */
public class BQSStrategySetResponse implements Serializable{
	
	private static final long serialVersionUID = -5183569482080739548L;
	
	/**
	 * 策略名称
	 */
	private String strategyName;
	
	/**
	 * 策略ID
	 */
	private String strategyId;
	
	/**
	 * 策略决策结果，参见decision附录码表
	 */
	private String strategyDecision;
	
	/**
	 * 策略匹配模式，参见strategyMode附录码表
	 */
	private String strategyMode;
	
	/**
	 * 策略风险系数，只有权重策略模式下有效
	 */
	private Integer strategyScore;
	
	/**
	 * 权重区间上限系数（只有权重策略模式下有值）
	 */
	private Integer rejectValue;
	
	/**
	 * 权重区间下限系数（只有权重策略模式下有值）
	 */
	private Integer reviewValue;
	
	/**
	 * 策略风险类型，参考riskType附录码表
	 */
	private String riskType;
	
	/**
	 * 策略击中话术提示
	 */
	private String tips;
	
	/**
	 * 规则内容明细，参考rule字段说明
	 */
	private List<BQShitRulesResponse> hitRules;

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public String getStrategyDecision() {
		return strategyDecision;
	}

	public void setStrategyDecision(String strategyDecision) {
		this.strategyDecision = strategyDecision;
	}

	public String getStrategyMode() {
		return strategyMode;
	}

	public void setStrategyMode(String strategyMode) {
		this.strategyMode = strategyMode;
	}

	public Integer getStrategyScore() {
		return strategyScore;
	}

	public void setStrategyScore(Integer strategyScore) {
		this.strategyScore = strategyScore;
	}

	public Integer getRejectValue() {
		return rejectValue;
	}

	public void setRejectValue(Integer rejectValue) {
		this.rejectValue = rejectValue;
	}

	public Integer getReviewValue() {
		return reviewValue;
	}

	public void setReviewValue(Integer reviewValue) {
		this.reviewValue = reviewValue;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<BQShitRulesResponse> getHitRules() {
		return hitRules;
	}

	public void setHitRules(List<BQShitRulesResponse> hitRules) {
		this.hitRules = hitRules;
	}
	
}
