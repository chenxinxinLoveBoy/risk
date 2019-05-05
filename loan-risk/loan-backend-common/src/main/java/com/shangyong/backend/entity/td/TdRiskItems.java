package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdRiskItems {

	/****/
	private String tdRiskItemsId;

	/****/
	private String tdAntiFraudId;

	/**规则编号**/
	private String ruleId;

	/**风险分数**/
	private String score;

	/**决策结果**/
	private String decision;

	/**风险名称**/
	private String riskName;

	/**风险类型**/
	private String riskType;


	public TdRiskItems() {
		super();
	}
	public TdRiskItems(String tdRiskItemsId,String tdAntiFraudId,String ruleId,String score,String decision,String riskName,String riskType) {
		super();
		this.tdRiskItemsId = tdRiskItemsId;
		this.tdAntiFraudId = tdAntiFraudId;
		this.ruleId = ruleId;
		this.score = score;
		this.decision = decision;
		this.riskName = riskName;
		this.riskType = riskType;
	}
	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setTdAntiFraudId(String tdAntiFraudId){
		this.tdAntiFraudId = tdAntiFraudId;
	}

	public String getTdAntiFraudId(){
		return this.tdAntiFraudId;
	}

	public void setRuleId(String ruleId){
		this.ruleId = ruleId;
	}

	public String getRuleId(){
		return this.ruleId;
	}

	public void setScore(String score){
		this.score = score;
	}

	public String getScore(){
		return this.score;
	}

	public void setDecision(String decision){
		this.decision = decision;
	}

	public String getDecision(){
		return this.decision;
	}

	public void setRiskName(String riskName){
		this.riskName = riskName;
	}

	public String getRiskName(){
		return this.riskName;
	}

	public void setRiskType(String riskType){
		this.riskType = riskType;
	}

	public String getRiskType(){
		return this.riskType;
	}

}
