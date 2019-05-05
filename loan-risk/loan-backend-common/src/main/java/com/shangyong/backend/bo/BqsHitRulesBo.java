package com.shangyong.backend.bo;


/**
 * 白骑士反欺诈规则表BO
 * @author xiajiyun
 * @date Wed Jul 26 16:37:24 CST 2017
 **/
public class BqsHitRulesBo {

	/**主键自增id**/
	private Integer hitRulesId;

	/**请参考bqs_strategy_set.strategy_set_id**/
	private Integer strategySetId;

	/**规则名称**/
	private String ruleName;

	/**规则ID**/
	private String ruleId;

	/**规则风险系数，只有权重策略模式下有效**/
	private Integer score;

	/**规则决策结果，参见decision附录码表**/
	private String decision;

	/**规则击中信息备注，会根据不同的规则模板返回相对应的格式**/
	private String memo;

	/**状态，默认1为可用，0关闭**/
	private Integer state;

	/**备注**/
	private String remark;


	public BqsHitRulesBo() {
		super();
	}
	public BqsHitRulesBo(Integer hitRulesId,Integer strategySetId,String ruleName,String ruleId,Integer score,String decision,String memo,Integer state,String remark) {
		super();
		this.hitRulesId = hitRulesId;
		this.strategySetId = strategySetId;
		this.ruleName = ruleName;
		this.ruleId = ruleId;
		this.score = score;
		this.decision = decision;
		this.memo = memo;
		this.state = state;
		this.remark = remark;
	}
	public void setHitRulesId(Integer hitRulesId){
		this.hitRulesId = hitRulesId;
	}

	public Integer getHitRulesId(){
		return this.hitRulesId;
	}

	public void setStrategySetId(Integer strategySetId){
		this.strategySetId = strategySetId;
	}

	public Integer getStrategySetId(){
		return this.strategySetId;
	}

	public void setRuleName(String ruleName){
		this.ruleName = ruleName;
	}

	public String getRuleName(){
		return this.ruleName;
	}

	public void setRuleId(String ruleId){
		this.ruleId = ruleId;
	}

	public String getRuleId(){
		return this.ruleId;
	}

	public void setScore(Integer score){
		this.score = score;
	}

	public Integer getScore(){
		return this.score;
	}

	public void setDecision(String decision){
		this.decision = decision;
	}

	public String getDecision(){
		return this.decision;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}

	public String getMemo(){
		return this.memo;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
