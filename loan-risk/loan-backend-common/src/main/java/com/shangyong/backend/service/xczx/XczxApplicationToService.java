package com.shangyong.backend.service.xczx;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.shangyong.backend.common.RuleResult;

public interface XczxApplicationToService {

	
	/**
	 * 根据用户信息向91征信发起查询申请 1001 接口
	 * @param applicationNumber 闪贷申请单编号
	 * @param realName 用户真实姓名
 	 * @param idCard 用户身份证号
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws Exception 
	 */
	public RuleResult sendQuery(String applicationNumber, String realName, String idCard);
}
