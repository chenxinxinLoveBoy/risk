package com.shangyong.backend.common.response;

import java.util.List;

/**
 * 欺诈关注清单，返回response
 * @author xiajiyun
 *
 */
public class ZhimaAntifraudScoreBillResponse extends ZhimaResponse{

	private static final long serialVersionUID = 7555568174931059350L;

	/**
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	private String bizNo;
	
	/**
	 * 欺诈关注清单是否命中，yes标识命中，no标识未命中
	 */
	private String hit;
	
	/**
	 * 欺诈关注清单的RiskCode列表，对应的描述见产品文档 
	 */
	private List<String> riskCode;
	
	/**
	 * 商户请求的唯一标志，长度64位以内字符串，仅限字母数字下划线组合。该标识作为业务调用的唯一标识，商户要保证其业务唯一性，使用相同transaction_id的查询，芝麻在一段时间内（一般为1天）返回首次查询结果，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费 
	 */
	private String transactionId;
	
	

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public List<String> getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(List<String> riskCode) {
		this.riskCode = riskCode;
	}
	
	
}
