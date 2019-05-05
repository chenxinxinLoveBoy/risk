package com.shangyong.backend.utils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;  
 
/**
 * httprequest 工具类
 * @author xiangxianjin
 *
 */
public class RiskHttpClientUtil {  
	private static Logger logger = LoggerFactory.getLogger("httpclient");
	
	private final static int CONNECT_TIME_OUT = 5000;
	private final static int CONNECT_REQUEST_TIME_OUT = 5000;
	private final static int SOCKET_TIME_OUT = 5000;

    /**
     * url get request , 异常抛出
     * @param url
     * @param param
     * @param config
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, String> param, RequestConfig config) throws Exception {  
  
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
  
        String resultString = "";  
        CloseableHttpResponse response = null;  
        try {  
            // 创建uri  
            URIBuilder builder = new URIBuilder(url);  
            if (param != null) {  
                for (String key : param.keySet()) {  
                    builder.addParameter(key, param.get(key));  
                }  
            }  
            URI uri = builder.build();  
            logger.info("HttpClientUtil>doGet>uri="+uri);
            // 创建http GET请求  
            HttpGet httpGet = new HttpGet(uri);  
            //设置超时时间，单位ms
            if (config == null) {
            	config = RequestConfig.custom()  
                        .setConnectTimeout(CONNECT_TIME_OUT).setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT)  
                        .setSocketTimeout(SOCKET_TIME_OUT).build();  
            }
            httpGet.setConfig(config);  
            // 执行请求  
            response = httpClient.execute(httpGet);  
            // 判断返回状态是否为200  
            if (response.getStatusLine().getStatusCode() == 200) {  
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");  
            }else{
                logger.error("HttpClientUtil>doGet>response="+response);
                throw new Exception("HttpClientUtil>doGet>response="+response);
            }
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {  
            try {  
                if (response != null) {  
                    response.close();  
                }  
                if(httpClient !=null){
                	httpClient.close();  
                }
            } catch (IOException e) {  
                logger.error(e.getMessage(), e);
                throw new Exception(e);
            }  
        }  
        return resultString;  
    }  
    
	/**
	 * url get request , 异常抛出
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
    public static String doGet(String url, Map<String, String> param) throws Exception {  
    	return doGet(url, param, null);
    }
  
    public static String doGet(String url) throws Exception {  
        return doGet(url, null);  
    }  
  
    /**
     * post 异常抛出
     * @param url
     * @param param
     * @param config
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> param, RequestConfig config, String contentType) throws Exception {  
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpResponse response = null;  
        String resultString = "";  
        try {  
            logger.info("HttpClientUtil>doPost[url="+url+",param="+param.toString()+"]");
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);  
            // 创建参数列表  
            if (param != null) {  
                List<NameValuePair> paramList = new ArrayList<>();  
                for (String key : param.keySet()) {  
                    paramList.add(new BasicNameValuePair(key, param.get(key)));  
                }  
                // 模拟表单  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");  
                httpPost.setEntity(entity);  
            }  
            if (StringUtils.isNotEmpty(contentType)) {
            	httpPost.addHeader("Content-type", contentType);  
            }
            //设置超时时间，单位ms
            if (config == null) {
            	config = RequestConfig.custom()  
                        .setConnectTimeout(CONNECT_TIME_OUT)
                        .setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT)
                        .setSocketTimeout(SOCKET_TIME_OUT)
                        .build();
            }
            httpPost.setConfig(config);
            // 执行http请求  
            response = httpClient.execute(httpPost);  
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {  
            try {  
            	if(response!=null){
            	   response.close();  
            	}
                if(httpClient !=null){
                	httpClient.close();  
                }
            } catch (IOException e) {  
                logger.error(e.getMessage(), e);
                throw new Exception(e);
            }  
        }  
  
        return resultString;  
    }  
    
    /**
     * post 请求
     * @param url
     * @param param
     * @param config
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> param, RequestConfig config) throws Exception {  
    	return doPost(url, param, config, null);
    }
    
    /**
     * post 请求
     * @param url
     * @param param
     * @param contentType
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> param, String contentType) throws Exception {  
    	return doPost(url, param, null, contentType);
    }
    
    /**
     * post 异常抛出
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> param) throws Exception {  
    	return doPost(url, param, null, null);
    }
  
    public static String doPost(String url) throws Exception {  
        return doPost(url, null);  
    }  
      
    /**
     * json post 异常抛出
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPostJson(String url, String json) throws Exception {  
        // 创建Httpclient对象  
        CloseableHttpClient httpClient = HttpClients.createDefault();  
        CloseableHttpResponse response = null;  
        String resultString = "";  
        try {  
            logger.info("HttpClientUtil>doPostJson[url="+url+",json="+json+"]");
            // 创建Http Post请求  
            HttpPost httpPost = new HttpPost(url);  
            // 创建请求内容  
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);  
            httpPost.setEntity(entity);  
            //设置超时时间，单位ms
            RequestConfig requestConfig = RequestConfig.custom()  
                    .setConnectTimeout(CONNECT_TIME_OUT)
                    .setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT)
                    .setSocketTimeout(SOCKET_TIME_OUT)
                    .build();
            httpPost.setConfig(requestConfig);
            // 执行http请求  
            response = httpClient.execute(httpPost);  
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");  
        } catch (Exception e) {  
            logger.error(e.getMessage(), e);
            throw new Exception(e);
        } finally {  
            try {  
            	if(response!=null){
             	   response.close();  
             	}
                if(httpClient !=null){
                	httpClient.close();  
                }
            } catch (IOException e) {  
                logger.error(e.getMessage(), e);
                throw new Exception(e);
            }  
        }  
  
        return resultString;  
    }  
    
    public static void main(String[] args) throws Exception {
    	String url = "http://localhost:8080/loan-backend-web/index/login.html";
		String res = doGet(url);
		System.out.println(res);
    	url = "http://localhost:8080/loan-backend-web/backend/login/ajaxLogin.do";
    	Map<String, String> param = new HashMap<String, String>();
    	param.put("userName", "a");
    	param.put("password", "1");
		res = doPost(url,param);
		System.out.println(res);
	}
}
