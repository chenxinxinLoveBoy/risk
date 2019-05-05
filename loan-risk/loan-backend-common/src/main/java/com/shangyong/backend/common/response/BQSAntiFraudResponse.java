package com.shangyong.backend.common.response;

import java.util.List;

/**
 * 白骑士，接收结果对象（可以为空的参数）
 * @author xiajiyun
 *
 */
public class BQSAntiFraudResponse {

	private static final long serialVersionUID = -1909996442787626699L;

	/**
	 * 结果描述，如果结果码非成功则会返回失败明细
	 */
	private String resultDesc;
	
	/**
	 * 最终风险系数，只有权重策略模式下有效
	 */
	private String finalScore;
	
	/**
	 * 策略集内容明细，参考BQSStrategySetBo类说明
	 */
	private List<BQSStrategySetResponse> strategySet;


	/**
	 * 结果码，参见ressultCode附录码表
	 */
	private String resultCode;

	/**
	 * 本次请求的流水号，用于事后案件调查
	 */
	private String flowNo;

	/**
	 * 决策结果码，参见decision码表
	 */
	private String finalDecision;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}
 
	
	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}
 

	public String getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	public List<BQSStrategySetResponse> getStrategySet() {
		return strategySet;
	}

	public void setStrategySet(List<BQSStrategySetResponse> strategySet) {
		this.strategySet = strategySet;
	}
	
	
	
}
