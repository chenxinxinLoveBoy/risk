package com.shangyong.backend.common.response;

import java.util.List;

/**
 * 欺诈信息验证，返回response
 * @author xiajiyun
 *
 */
public class ZhimaAntifraudScoreCheckResponse extends ZhimaResponse{

	private static final long serialVersionUID = -5743210950014865378L;

	/**
	 * 芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
	 */
	private String bizNo;
	
	/**
	 * 欺诈信息验证，输出验证码verifyCode列表,verifyCode和文案的映射关系参见"产品接口说明" 
	 */
	private List<String> verifyCode;
	
	/**
	 * 商户请求的唯一标志，长度64位以内字符串，仅限字母数字下划线组合。该标识作为业务调用的唯一标识，商户要保证其业务唯一性，使用相同transaction_id的查询，芝麻在一段时间内（一般为1天）返回首次查询结果，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费 
	 */
	private String transactionId;
	

	public List<String> getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(List<String> verifyCode) {
		this.verifyCode = verifyCode;
	}

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
 
	
	
}
