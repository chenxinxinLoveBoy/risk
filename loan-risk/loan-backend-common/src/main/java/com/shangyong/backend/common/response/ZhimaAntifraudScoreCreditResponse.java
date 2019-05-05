package com.shangyong.backend.common.response;


/**
 * 芝麻信用評分，返回response
 * @author xiajiyun
 *
 */
public class ZhimaAntifraudScoreCreditResponse extends ZhimaResponse{

	private static final long serialVersionUID = -4353118069060571798L;

	/**
	 * 用户的芝麻信用评分。分值范围[350,950]。如果用户数据不足，无法评分时，返回字符串"N/A"
	 */
	private String	ZmScore;
	
	/**
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账 
	 */
	private String bizNo;
	
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

	public String getZmScore() {
		return ZmScore;
	}

	public void setZmScore(String zmScore) {
		ZmScore = zmScore;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	
	
	
}
