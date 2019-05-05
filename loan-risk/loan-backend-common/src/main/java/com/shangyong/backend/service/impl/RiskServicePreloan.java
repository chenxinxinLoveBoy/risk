package com.shangyong.backend.service.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.PreloanQueryResponse;
import com.shangyong.backend.entity.PreloanSubmitResponse;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.SpringIocUtils;
/**
 * 
 * @author ChenGeng
 *
 */
public class RiskServicePreloan {

    private static final Log  log = LogFactory.getLog(RiskServicePreloan.class);
	private static Logger tongdunLogger = (Logger) LoggerFactory.getLogger("tdBackendTask");
    private static Logger tongdunHttpLogger = (Logger) LoggerFactory.getLogger("tdBackendHttpTask");
    private static Logger tongdunHttpQueryLogger = (Logger) LoggerFactory.getLogger("tdBackendHttpQueryTask");

//    private static final String submitUrl    = "https://apitest.tongdun.cn/preloan/apply/v5";//提交请求URL-->stg
//    private static final String queryUrl     = "https://apitest.tongdun.cn/preloan/report/v8";//查询请求URL-->stg
//    private static final String submitUrl    = "https://api.tongdun.cn/preloan/apply/v5";//提交请求URL-->prd
//    private static final String queryUrl     = "https://api.tongdun.cn/preloan/report/v8";//查询请求URL-->prd

//    private static final String PARTNER_APP  = "hlxx_ios";//应用名
//    private static final String PARTNER_CODE = "hlxx";// 合作方标识
//    private static final String PARTNER_KEY  = "2ba7f51a9ef64fd688e30dc50686d38e";//合作方密钥-->stg
//    private static final String PARTNER_KEY  = "4f1eb9f63bf04536922b3e8971950a3b";//合作方密钥-->prd

    private SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
    /**
     * submit接口示例
     *
     * @param params
     * @return
     */
    public PreloanSubmitResponse apply(Map<String, Object> params) {
        Long dateStart = new Date().getTime();
        tongdunHttpLogger.info("apply方法 同盾submit接口开始");
        HttpsURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
        	SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
        	// 获取安全凭证码请求URL
    		SysParam tdSubmitUrlParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_SUBMIT_URL);
    		if (tdSubmitUrlParam == null) {
    			tongdunLogger.error("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
                tongdunHttpLogger.error("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
    			throw new RuntimeException("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
    		} 
    		String tdSubmitUrl = tdSubmitUrlParam.getParamValueOne();
    		// 获取请求参数常量
    		SysParam tdConstantParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_CONSTANT);
    		if (tdConstantParam == null) {
    			tongdunLogger.error("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
                tongdunHttpLogger.error("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
    			throw new RuntimeException("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
    		}
    		String partnerApp = "";
    		String appName=(String) params.get("source");//申请来源
    		if(appName.equals("1")){
    			 partnerApp = tdConstantParam.getParamValueTwo();
    		}else{
    			 partnerApp = tdConstantParam.getParamValueFour();
    		}
    		
    		String partnerCode = tdConstantParam.getParamValueOne();
    		String partnerKey = tdConstantParam.getParamValueThree();

            tongdunHttpLogger.info("apply方法 SSLSocketFactory构建");
//            SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            tongdunHttpLogger.info("apply方法 urlString 拼装");
            String urlString = new StringBuffer().append(tdSubmitUrl).append("?partner_code=").append(partnerCode).append("&partner_key=").append(partnerKey).append("&app_name=").append(partnerApp).toString();

            tongdunHttpLogger.info("apply方法 URL 创建");
            URL url = new URL(urlString);
            tongdunLogger.info("同盾提交URL路径:"+urlString);
            tongdunHttpLogger.info("同盾提交URL路径:"+urlString);
            // 组织请求参数
            StringBuffer postBody = new StringBuffer();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null) continue;
                postBody.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),"utf-8")).append("&");
            }

            if (!params.isEmpty()) {
                postBody.deleteCharAt(postBody.length() - 1);
            }

            conn = (HttpsURLConnection) url.openConnection();
            //设置https
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(3000);
            // 设置读取超时
            conn.setReadTimeout(1000);
            // 提交参数
            tongdunHttpLogger.info("同盾提交请求,setRequestMethod POST");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            tongdunHttpLogger.info("同盾提交请求参数:"+postBody.toString());
            conn.getOutputStream().write(postBody.toString().getBytes());
            conn.getOutputStream().flush();
            int responseCode = conn.getResponseCode();
            tongdunHttpLogger.info("同盾提交请求响应Code:"+responseCode);
            if (responseCode == 200) {
                tongdunHttpLogger.info("同盾开始获取inputStream");
                inputStream = conn.getInputStream();
                tongdunHttpLogger.info("同盾开始获取result");
                bufferedReader = new BufferedReader( new InputStreamReader(inputStream, "utf-8"));
                StringBuffer result = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                tongdunHttpLogger.info("同盾获取result: " + result.toString());
                return JSON.parseObject(result.toString().trim(), PreloanSubmitResponse.class);
            }else{
            	log.error("同盾返回响应吗：" + responseCode);
            	tongdunLogger.error("同盾返回响应吗：" + responseCode);
                tongdunHttpLogger.error("同盾返回响应吗：" + responseCode);
            	throw new RuntimeException("调用同盾个人贷前审核服务的submit接口，获取审核报告编号失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用同盾个人贷前审核服务的submit接口异常, 详细信息: " + e);
            tongdunLogger.error("调用同盾个人贷前审核服务的submit接口异常, 详细信息:  " + e);
            tongdunHttpLogger.error("调用同盾个人贷前审核服务的submit接口异常, 详细信息:  " + e);
            throw new RuntimeException("调用同盾个人贷前审核服务的submit接口异常, 详细信息: " + e.getMessage());
        }finally {
            if (bufferedReader != null) {
                try {
                    //同盾释放响应信息流
                    bufferedReader.close();
                    tongdunLogger.error("同盾释放读取的响应信息流信息");
                    tongdunHttpLogger.error("同盾释放读取的响应信息流信息");
                } catch (IOException ioe) {
                    tongdunLogger.error("同盾释放读取的响应信息流信息失败：" + ioe.getMessage());
                    tongdunHttpLogger.error("同盾释放读取的响应信息流信息失败：" + ioe.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    //释放响应信息流
                    inputStream.close();
                    tongdunLogger.error("同盾释放响应信息流");
                    tongdunHttpLogger.error("同盾释放响应信息流");
                } catch (IOException ioe) {
                    tongdunLogger.error("同盾释放响应信息流失败：" + ioe.getMessage());
                    tongdunHttpLogger.error("同盾释放响应信息流失败：" + ioe.getMessage());
                }
            }
            if (conn != null) {
                conn.disconnect();
                tongdunLogger.error("同盾释放连接");
                tongdunHttpLogger.error("同盾释放连接");
            }
            Long dateEnd = new Date().getTime();
            tongdunHttpLogger.info("apply方法 同盾submit接口结束，用时(ms):" + (dateEnd-dateStart));
        }
    }

    public PreloanQueryResponse query(String reportId,String applicationId) {
        Long dateStart = new Date().getTime();
        tongdunHttpQueryLogger.info("query方法 同盾获取报告接口开始，申请单号：" + applicationId);
        HttpsURLConnection conn = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
        	SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
        	// 获取安全凭证码请求URL
    		SysParam tdQueryUrlParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_QUUERY_URL);
    		if (tdQueryUrlParam == null) {
    			tongdunLogger.error("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
                tongdunHttpQueryLogger.error("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
    			throw new RuntimeException("从系统参数表获取安全凭证码请求URL失败, key : TD_SUBMIT_URL");
    		} 
    		String tdQueryUrl = tdQueryUrlParam.getParamValueOne();
    		// 获取请求参数常量
    		SysParam tdConstantParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_CONSTANT);
    		if (tdConstantParam == null) {
    			tongdunLogger.error("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
                tongdunHttpQueryLogger.error("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
    			throw new RuntimeException("从系统参数表获取同盾请求参数常量失败, key : TD_CONSTANT");
    		}
    		String partnerCode = tdConstantParam.getParamValueOne();
    		String partnerKey = tdConstantParam.getParamValueThree();

            tongdunHttpQueryLogger.info("同盾查询报告,SSLSocketFactory构建");
//        	SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
            tongdunHttpQueryLogger.info("同盾查询报告,urlString构建");
            String urlString = new StringBuffer().append(tdQueryUrl).append("?partner_code=").append(partnerCode).append("&partner_key=").append(partnerKey).append("&report_id=").append(reportId).toString();

            tongdunHttpQueryLogger.info("同盾查询报告,URL 创建");
            URL url = new URL(urlString);

            tongdunLogger.info("同盾查询报告urlString路径:"+urlString);
            tongdunHttpQueryLogger.info("同盾查询报告urlString路径:"+urlString);

            conn = (HttpsURLConnection) url.openConnection();
            //设置https
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(3000);
            // 设置读取超时
            conn.setReadTimeout(3000);
            // 提交参数
            tongdunHttpQueryLogger.info("同盾查询报告,setRequestMethod GET");
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            tongdunHttpQueryLogger.info("同盾查询报告响应Code:"+responseCode);
            if (responseCode == 200) {
                tongdunHttpQueryLogger.info("同盾查询报告,获取inputStream");
                inputStream = conn.getInputStream();
                tongdunHttpQueryLogger.info("同盾查询报告,将inputStream转换为bufferedReader");
                bufferedReader = new BufferedReader(
                                                                   new InputStreamReader(inputStream, "utf-8"));
                StringBuffer result = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                tongdunHttpQueryLogger.info("同盾查询报告,报告转换成功");
                return JSON.parseObject(result.toString().trim(), PreloanQueryResponse.class);
            }else{
            	log.error("调用同盾上送参数：" + urlString + ",同盾返回响应吗：" + responseCode);
            	tongdunLogger.error("调用同盾上送参数：" + urlString + ",同盾返回响应吗：" + responseCode);
                tongdunHttpQueryLogger.error("调用同盾上送参数：" + urlString + ",同盾返回响应吗：" + responseCode);
            	throw new RuntimeException("调用同盾个人贷前审核服务的query接口，获取报告详细信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用同盾个人贷前审核服务的query接口异常, 详细信息: " + e);
            tongdunLogger.error("调用同盾个人贷前审核服务的query接口异常, 详细信息:  " + e);
            tongdunHttpQueryLogger.error("调用同盾个人贷前审核服务的query接口异常, 详细信息:  " + e);
            throw new RuntimeException("调用同盾个人贷前审核服务的query接口异常, 详细信息: " + e.getMessage());
        }finally {
            if (bufferedReader != null) {
                try {
                    //同盾释放响应信息流
                    bufferedReader.close();
                    tongdunLogger.error("同盾释放读取的响应信息流信息");
                    tongdunHttpQueryLogger.error("同盾释放读取的响应信息流信息");
                } catch (IOException ioe) {
                    tongdunLogger.error("同盾释放读取的响应信息流信息失败：" + ioe.getMessage());
                    tongdunHttpQueryLogger.error("同盾释放读取的响应信息流信息失败：" + ioe.getMessage());
                }
            }
            if (inputStream != null) {
                try {
                    //释放响应信息流
                    inputStream.close();
                    tongdunLogger.error("同盾释放响应信息流");
                    tongdunHttpQueryLogger.error("同盾释放响应信息流");
                } catch (IOException ioe) {
                    tongdunLogger.error("同盾释放响应信息流失败：" + ioe.getMessage());
                    tongdunHttpQueryLogger.error("同盾释放响应信息流失败：" + ioe.getMessage());
                }
            }
            if (conn != null) {
                conn.disconnect();
                tongdunLogger.error("同盾释放连接");
                tongdunHttpQueryLogger.error("同盾释放连接");
            }
            Long dateEnd = new Date().getTime();
            tongdunHttpQueryLogger.info("同盾查询报告接口结束，用时(ms):" + (dateEnd-dateStart));
        }
    }

}