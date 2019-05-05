package com.shangyong.backend.common.response;

import java.io.Serializable;


/**
 * 白骑士，接收结果对象（接收非空参数） 
 * @author xiajiyun
 *
 */

public abstract class BQSAntiFraudAbstractResponse implements Serializable{

	private static final long serialVersionUID = 5891734396653495686L;
	
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
	
	
	
	
}
