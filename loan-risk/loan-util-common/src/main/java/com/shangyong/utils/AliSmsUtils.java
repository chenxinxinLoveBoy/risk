package com.shangyong.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsRequest;
import com.aliyuncs.sms.model.v20160927.SingleSendSmsResponse;

import javax.servlet.http.HttpServletResponse;

public class AliSmsUtils {

	private static String accessKeyId = PropertiesUtil.get("accessKeyId");

	private static String secret = PropertiesUtil.get("secret");

	private static String endpointName = PropertiesUtil.get("endpointName");

	private static String regionId = PropertiesUtil.get("regionId");

	private static String product = PropertiesUtil.get("product");

	private static String domain = PropertiesUtil.get("domain");
	
	private static String signName = PropertiesUtil.get("signName");
	
	private static String templateCode = PropertiesUtil.get("templateCode");

	public static String getSms(HttpServletResponse response, String phoneNum, String code) {
		try {
			IClientProfile profile = DefaultProfile.getProfile(regionId,
					accessKeyId, secret);
			DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(signName);// 控制台创建的签名名称
			request.setTemplateCode(templateCode);// 控制台创建的模板CODE
			request.setParamString("{\"code\":\""+code+"\"}");// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setRecNum(phoneNum);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			return httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public static String getSms(String phoneNum,String code) {
		try {
			IClientProfile profile = DefaultProfile.getProfile(regionId,
					accessKeyId, secret);
			DefaultProfile.addEndpoint(endpointName, regionId, product, domain);
			IAcsClient client = new DefaultAcsClient(profile);
			SingleSendSmsRequest request = new SingleSendSmsRequest();
			request.setSignName(signName);// 控制台创建的签名名称
			request.setTemplateCode(templateCode);// 控制台创建的模板CODE
			request.setParamString("{\"code\":\""+code+"\"}");// 短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
			request.setRecNum(phoneNum);// 接收号码
			SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
			return httpResponse.getModel();
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String s = getSms("18121071521","123456");
		System.out.println(s);
	}
}
