package com.shangyong.backend.utils.xczx;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.service.impl.ScAlarmServiceImpl;
import com.shangyong.backend.utils.SpringIocUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class HttpClientZX {

    private static Logger logger = (Logger) LoggerFactory.getLogger("creditXC");

    private final static int CONNECT_TIME_OUT = 5000;
    private final static int CONNECT_REQUEST_TIME_OUT = 5000;
    private final static int SOCKET_TIME_OUT = 5000;

    public static PkgHeader sendPostReqeust(String requestUrl, PkgHeader reqPkg, RequestConfig config) {
        PkgHeader rspPkg = new PkgHeader();
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = createSSLClientDefault();
        try {
            HttpPost post = new HttpPost(requestUrl);

            //设置超时时间
            if (config == null) {
                config = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT)
                        .setConnectionRequestTimeout(CONNECT_REQUEST_TIME_OUT).setSocketTimeout(SOCKET_TIME_OUT)
                        .build();
            }
            logger.info("HttpClientZX-->sendPostReqeust-->接口请求参数：" + reqPkg.toString());

            ByteArrayEntity reqEntity = new ByteArrayEntity(reqPkg.toPkgBytes("UTF-8"));
            post.setEntity(reqEntity);
            post.setConfig(config);

            String result = "";

            response = httpclient.execute(post);

            //调用接口状态不为200的就走钉钉报警
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
                SpringIocUtils.getBean(ScAlarmServiceImpl.class).contains(AlarmCodeEnum.SYSTEM_CONFIG, "HTTP【doPostJson】-->POST-->URL:" + requestUrl + "响应状态码：" + response.getStatusLine().getStatusCode(), AlarmThirdPartyCreditInvestigationEnum.SYSTEM_CONFIG);
                //scAlarmImpl.contains(AlarmCodeEnum.SYSTEM_CONFIG,"HTTP【doPostJson】-->POST-->URL:" + requestUrl + "响应状态码：" + response.getStatusLine().getStatusCode(), AlarmThirdPartyCreditInvestigationEnum.SYSTEM_CONFIG);
//    			DingdingUtil.setMessage(Constants.WEB_DD_SYS_URL_CODE, "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";HTTP【doPostJson】-->POST-->URL:" + requestUrl + "响应状态码：" + response.getStatusLine().getStatusCode());
//    			throw new RuntimeException(String.format("POST接口异常，请求返回状态为：【%s】", response.getStatusLine().getStatusCode()));
            }

            HttpEntity rspEntity = response.getEntity();

            if (rspEntity != null) {
                result = EntityUtils.toString(rspEntity);
            }

            logger.info("HttpClientZX-->sendPostReqeust-->接口响应参数[解密前]：" + result);
            rspPkg.parseFromString(result); // 解析报文头
            logger.info("HttpClientZX-->sendPostReqeust-->接口响应参数[解密后]：" + rspPkg.toString());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("HttpClientZX-->接口工具类异常：" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("HttpClientZX-->接口工具类IOException异常：" + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }

        return rspPkg;
    }

    private static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }
}
