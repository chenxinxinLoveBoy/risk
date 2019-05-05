package com.shangyong.backend.bo;


/**
 * 白骑士反欺诈策略表Bo
 * @author xiajiyun
 * @date Wed Jul 26 16:37:24 CST 2017
 **/
public class BqsStrategySetBo {

	/**主键自增id**/
	private Integer strategySetId;

	/**请参考bqs_anti_fraud.anti_fraud_id**/
	private Integer antiFraudId;

	/**策略名称**/
	private String strategyName;

	/**策略ID**/
	private String strategyId;

	/**策略决策结果，参见decision附录码表**/
	private String strategyDecision;

	/**策略匹配模式，参见strategyMode附录码表**/
	private String strategyMode;

	/**策略风险系数，只有权重策略模式下有效**/
	private Integer strategyScore;

	/**权重区间上限系数（只有权重策略模式下有值）**/
	private Integer rejectValue;

	/**权重区间下限系数（只有权重策略模式下有值）**/
	private Integer reviewValue;

	/**策略风险类型，参考riskType附录码表**/
	private String riskType;

	/**策略击中话术提示**/
	private String tips;

	/**状态，默认1为可用，0关闭**/
	private Integer state;

	/**备注**/
	private String remark;


	public BqsStrategySetBo() {
		super();
	}
	public BqsStrategySetBo(Integer strategySetId,Integer antiFraudId,String strategyName,String strategyId,String strategyDecision,String strategyMode,Integer strategyScore,Integer rejectValue,Integer reviewValue,String riskType,String tips,Integer state,String remark) {
		super();
		this.strategySetId = strategySetId;
		this.antiFraudId = antiFraudId;
		this.strategyName = strategyName;
		this.strategyId = strategyId;
		this.strategyDecision = strategyDecision;
		this.strategyMode = strategyMode;
		this.strategyScore = strategyScore;
		this.rejectValue = rejectValue;
		this.reviewValue = reviewValue;
		this.riskType = riskType;
		this.tips = tips;
		this.state = state;
		this.remark = remark;
	}
	public void setStrategySetId(Integer strategySetId){
		this.strategySetId = strategySetId;
	}

	public Integer getStrategySetId(){
		return this.strategySetId;
	}

	public void setAntiFraudId(Integer antiFraudId){
		this.antiFraudId = antiFraudId;
	}

	public Integer getAntiFraudId(){
		return this.antiFraudId;
	}

	public void setStrategyName(String strategyName){
		this.strategyName = strategyName;
	}

	public String getStrategyName(){
		return this.strategyName;
	}

	public void setStrategyId(String strategyId){
		this.strategyId = strategyId;
	}

	public String getStrategyId(){
		return this.strategyId;
	}

	public void setStrategyDecision(String strategyDecision){
		this.strategyDecision = strategyDecision;
	}

	public String getStrategyDecision(){
		return this.strategyDecision;
	}

	public void setStrategyMode(String strategyMode){
		this.strategyMode = strategyMode;
	}

	public String getStrategyMode(){
		return this.strategyMode;
	}

	public void setStrategyScore(Integer strategyScore){
		this.strategyScore = strategyScore;
	}

	public Integer getStrategyScore(){
		return this.strategyScore;
	}

	public void setRejectValue(Integer rejectValue){
		this.rejectValue = rejectValue;
	}

	public Integer getRejectValue(){
		return this.rejectValue;
	}

	public void setReviewValue(Integer reviewValue){
		this.reviewValue = reviewValue;
	}

	public Integer getReviewValue(){
		return this.reviewValue;
	}

	public void setRiskType(String riskType){
		this.riskType = riskType;
	}

	public String getRiskType(){
		return this.riskType;
	}

	public void setTips(String tips){
		this.tips = tips;
	}

	public String getTips(){
		return this.tips;
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
