package com.shangyong.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * httprequest 工具类
 *
 * @author xiangxianjin
 */

@Component
public class RiskHttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger("httpclient");

	private final static int CONNECT_TIME_OUT = 5000;
	private final static int CONNECT_REQUEST_TIME_OUT = 5000;
	private final static int SOCKET_TIME_OUT = 5000;

	// private static IScAlarm scAlarmImpl;
	//
	// @Autowired
	// public void setScAlarmImpl(IScAlarm scAlarmImpl) {
	// RiskHttpClientUtil.scAlarmImpl = scAlarmImpl;
	// }

	/**
	 * url get request , 异常抛出
	 *
	 * @param url
	 * @param param
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, Map<String, String> param, RequestConfig config) {

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
			logger.info("HttpClientUtil>doGet>uri=" + uri);
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 设置超时时间，单位ms
			if (config == null) {
				config = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
						.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
						.build();
			}
			httpGet.setConfig(config);
			// 执行请求
			response = httpClient.execute(httpGet);
			// 调用接口状态不为200的就走钉钉报警
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
				// scAlarmImpl.contains(AlarmCodeEnum.SYSTEM_CONFIG,
				// "HTTP【doGet】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode(),
				// AlarmThirdPartyCreditInvestigationEnum.SYSTEM_CONFIG);
				// DingdingUtil.setMessage(Constants.WEB_DD_SYS_URL_CODE,
				// "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) +
				// ";HTTP【doGet】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode());
				// throw new
				// RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】",
				// response.getStatusLine().getStatusCode()));
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}

			// // 判断返回状态是否为200
			// if (response.getStatusLine().getStatusCode() == 200) {
			// resultString = EntityUtils.toString(response.getEntity(),
			// "UTF-8");
			// }else{
			// logger.error("HttpClientUtil>doGet>response="+response);
			// throw new
			// RuntimeException("HttpClientUtil>doGet>response="+response);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}
		return resultString;
	}

	/**
	 * url get request , 异常抛出
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, Map<String, String> param) {
		return doGet(url, param, null);
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	/**
	 * url get请求 包括header头信息
	 * 
	 * @param url
	 * @param headers
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String doGetFromHeader(String url, Map<String, String> headers) {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		if (headers != null) {
			for (String key : headers.keySet()) {
				get.setHeader(key, headers.get(key));// 循环headers
			}
		}
		RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
		.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
		.build();
//		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIME_OUT); // 设置连接时间
//		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, SOCKET_TIME_OUT);// socketTime
		String content = "";
		try {
			HttpResponse response = client.execute(get);
			content = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return content;
	}

	/**
	 * post 异常抛出
	 *
	 * @param url
	 * @param param
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> param, RequestConfig config, String contentType) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			logger.info("HttpClientUtil>doPost[url=" + url + ",param=" + param.toString() + "]");
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
				httpPost.setEntity(entity);
			}
			if (StringUtils.isNotEmpty(contentType)) {
				httpPost.addHeader("Content-type", contentType);
			}
			// 设置超时时间，单位ms
			if (config == null) {
				config = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
						.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
						.build();
			}
			httpPost.setConfig(config);
			// 执行http请求
			response = httpClient.execute(httpPost);
			// 调用接口状态不为200的就走钉钉报警
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
				// scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,
				// "HTTP【doPost】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode(),
				// AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
				// DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE,
				// "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) +
				// ";HTTP【doPost】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode());
				// throw new
				// RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】",
				// response.getStatusLine().getStatusCode()));
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}

		return resultString;
	}

	/**
	 * post 请求
	 *
	 * @param url
	 * @param param
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> param, RequestConfig config) {
		return doPost(url, param, config, null);
	}

	/**
	 * post 请求
	 *
	 * @param url
	 * @param param
	 * @param contentType
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> param, String contentType) {
		return doPost(url, param, null, contentType);
	}

	/**
	 * post 异常抛出
	 *
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> param) {
		return doPost(url, param, null, null);
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}

	/**
	 * json post 异常抛出
	 *
	 * @param url
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			logger.info("HttpClientUtil>doPostJson[url=" + url + ",json=" + json + "]");
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 设置超时时间，单位ms
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
					.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
			httpPost.setConfig(requestConfig);
			// 执行http请求
			response = httpClient.execute(httpPost);

			// 调用接口状态不为200的就走钉钉报警
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
				// scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,
				// "HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode(),
				// AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
				// DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE,
				// "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) +
				// ";HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode());
				// throw new
				// RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】",
				// response.getStatusLine().getStatusCode()));
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		}

		return resultString;
	}

	public static String doPostDingdingJson(String url, String json) throws Exception {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			logger.info("HttpClientUtil>doPostJson[url=" + url + ",json=" + json + "]");
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			httpPost.addHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
			StringEntity entity = new StringEntity(json, "utf-8");
			httpPost.setEntity(entity);
			// 设置超时时间，单位ms
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
					.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
			httpPost.setConfig(requestConfig);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");

			// 调用接口状态不为200的就走钉钉报警
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				throw new Exception(e.getMessage());
			}
		}
		return resultString;
	}

	/**
	 * 白骑士调用
	 *
	 * @param url
	 * @param param
	 * @param config
	 * @return
	 * @throws Exception
	 */
	public static String doPostBQS(String url, Map<String, String> param, RequestConfig config, String contentType)
			throws Exception {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			logger.info("HttpClientUtil>doPost[url=" + url + ",param=" + param.toString() + "]");
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);

			if (StringUtils.isNotEmpty(contentType)) {
				httpPost.addHeader("Content-type", contentType);
			}
			// 设置超时时间，单位ms
			if (config == null) {
				config = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
						.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
						.build();
			}
			StringEntity se = new StringEntity(JSONObject.toJSON(param).toString(), "utf-8");
			// 赋值参数
			httpPost.setEntity(se);
			// 赋值超时时间
			httpPost.setConfig(config);
			// 执行http请求
			response = httpClient.execute(httpPost);

			// 调用接口状态不为200的就走钉钉报警
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
				// scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,
				// "HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode(),
				// AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
				// DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE,
				// "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) +
				// ";HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
				// response.getStatusLine().getStatusCode());
				// throw new
				// RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】",
				// response.getStatusLine().getStatusCode()));
			} else {
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			}

			// // 状态为200 表示请求成功，否则抛出异常
			// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			// {
			// logger.info(String.format("请求白骑士成功，状态=【%s】",
			// response.getStatusLine().getStatusCode()));
			// resultString = EntityUtils.toString(response.getEntity(),
			// "utf-8");
			// }else {
			// logger.error(String.format("POST请求白骑士接口异常，请求返回状态为：【%s】",
			// response.getStatusLine().getStatusCode()));
			// throw new
			// RuntimeException(String.format("POST请求白骑士接口异常，请求返回状态为：【%s】",
			// response.getStatusLine().getStatusCode()));
			// }
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new Exception(e.getMessage());
			}
		}

		return resultString;
	}

	/**
	 * HTTPS post (信任所有证书)
	 *
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
	public static String doPostXSKJ(String url, String jsonStr, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = acceptsUntrustedCertsHttpClient(); // jdk1.6用法
			// httpClient = new SSLClient(); //一般用法
			httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsonStr, charset);
			httpPost.setEntity(entity);

			// 设置超时时间，单位ms
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
					.setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT).build();
			httpPost.setConfig(requestConfig);

			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				// if (resEntity != null) {
				// result = EntityUtils.toString(resEntity, charset);
				// }
				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
					// scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,
					// "HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
					// response.getStatusLine().getStatusCode(),
					// AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
					// DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE,
					// "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) +
					// ";HTTP【doPostJson】-->POST-->URL:" + url + "响应状态码：" +
					// response.getStatusLine().getStatusCode());
					// throw new
					// RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】",
					// response.getStatusLine().getStatusCode()));
				} else {
					result = EntityUtils.toString(resEntity, "utf-8");
				}
			}

		} catch (Exception ex) {
			logger.error("HTTP【doPostJson】-->POST-->URL:" + url + ";异常信息：" + ex);
			// scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,
			// "HTTP【doPostJson】-->POST-->URL:" + url,
			// AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
			// DingdingUtil.setMessage(Constants.DUBBO_DD_APP_URL_CODE, "系统时间："
			// + DateUtils.parseToDateTimeStr(new Date()) +
			// ";HTTP【doPostJson】-->POST-->URL:" + url);
		}
		return result;
	}

	private static CloseableHttpClient acceptsUntrustedCertsHttpClient() throws Exception {
		HttpClientBuilder b = HttpClientBuilder.create();

		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		}).build();
		b.setSslcontext(sslContext);

		// don't check Hostnames, either.
		// -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if
		// you don't want to weaken
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

		// here's the special part:
		// -- need to create an SSL Socket Factory, to use our weakened "trust
		// strategy";
		// -- and create a Registry, to register it.
		//
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslSocketFactory)
				.build();

		// now, we create connection-manager using our Registry.
		// -- allows multi-threaded use
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		b.setConnectionManager(connMgr);

		// finally, build the HttpClient;
		// -- done!
		CloseableHttpClient client = b.build();
		return client;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://localhost:8080/loan-backend-web/index/login.html";
		String res = doGet(url);
		System.out.println(res);
		url = "http://localhost:8080/loan-backend-web/backend/login/ajaxLogin.do";
		Map<String, String> param = new HashMap<String, String>();
		param.put("userName", "a");
		param.put("password", "1");
		res = doPost(url, param);
		System.out.println(res);
	}
}
