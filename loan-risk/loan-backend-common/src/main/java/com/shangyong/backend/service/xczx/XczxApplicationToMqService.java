package com.shangyong.backend.service.xczx;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

public interface XczxApplicationToMqService {

	
	/**
	 * 根据用户信息向91征信发起查询申请 1001 接口
	 * @param realName 用户真实姓名
 	 * @param idCard 用户身份证号
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws Exception 
	 */
	public String sendQuery(String realName, String idCard);
	
	
	/**
	 * 主动从91征信同步数据报告到本地
	 * @param applicationNumber
	 */
	public void taskInfo(Map<String, String> msgMap) throws Exception;
}
