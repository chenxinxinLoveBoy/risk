package com.shangyong.backend.utils;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DingdingUtil {
	private static Logger logger = LoggerFactory.getLogger(DingdingUtil.class);
	private final static int CONNECT_TIME_OUT = 5000;
	private final static int CONNECT_REQUEST_TIME_OUT = 5000;
	private final static int SOCKET_TIME_OUT = 5000;

	public static final String setMessage(String WEBHOOK_TOKEN, String content) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			logger.info("请求钉钉url:"+WEBHOOK_TOKEN+",开始发送内容："+content);
			HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
			httppost.addHeader("Content-Type", "application/json; charset=utf-8");
			String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"" + content + "\"}}";
			StringEntity se = new StringEntity(textMsg, "utf-8");
			httppost.setEntity(se);
			//设置超时时间
			RequestConfig config = RequestConfig.custom()
					.setConnectTimeout(CONNECT_TIME_OUT)
					.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT)
					.setSocketTimeout(SOCKET_TIME_OUT)
					.build();
			httppost.setConfig(config);
			response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
				return result;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpclient != null) {
					httpclient.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return result;
	}

}