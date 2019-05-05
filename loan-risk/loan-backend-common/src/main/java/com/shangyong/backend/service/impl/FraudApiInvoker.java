package com.shangyong.backend.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.entity.FraudApiResponse;

public class FraudApiInvoker {

    private final Log           log    = LogFactory.getLog(this.getClass());
    private static final String apiUrl = "https://apitest.tongdun.cn/riskService/v1.1";

    private HttpsURLConnection conn;

    public FraudApiResponse invoke(Map<String, Object> params) {
        try {
            URL url = new URL(apiUrl);
            // 组织请求参数
            StringBuilder postBody = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null) continue;
                postBody.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),
                                                                                     "utf-8")).append("&");
            }

            if (!params.isEmpty()) {
                postBody.deleteCharAt(postBody.length() - 1);
            }

            SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(1000);
            // 设置读取超时，建议设置为500ms。若同时调用了信息核验服务，请与客户经理协商确认具体时间
            conn.setReadTimeout(500);
            // 提交参数
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postBody.toString().getBytes());
            conn.getOutputStream().flush();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                log.warn("[FraudApiInvoker] invoke failed, response status:" + responseCode);
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line).append("\n");
            }
            return JSON.parseObject(result.toString().trim(), FraudApiResponse.class);
        } catch (Exception e) {
            log.error("[FraudApiInvoker] invoke throw exception, details: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("partner_code", "hlxx");// 此处值填写您的合作方标识
        params.put("secret_key", "a4bbbc0eb02d4ace8d1941d89807159d");// 此处填写对应app密钥
        params.put("event_id", "Login_ios_20170522");// 此处填写策略集上的事件标识
        params.put("black_box", "your_black_box_to_send_to_api");//此处填写移动端sdk采集到的信息black_box
        params.put("account_login", "zhangsan");// 以下填写其他要传的参数，比如系统字段，扩展字段
        params.put("ip_address", "192.168.6.2");
        FraudApiResponse apiResp = new FraudApiInvoker().invoke(params);
        System.out.println(apiResp.getSuccess());
        System.out.println(apiResp.getReason_code());
    }

    public static void heartbeat(){
        final FraudApiInvoker invoker = new FraudApiInvoker();
        final Map<String, Object> params = new HashMap<>();
        params.put("partner_code","hlxx");
        params.put("secret_key","heartbeat");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        invoker.invoke(params);
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        }, "FraudApiInvoker Heartbeat Thread").start();
    }
  
}

