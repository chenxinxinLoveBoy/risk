package com.shangyong.backend.controller.xczx;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.xczx.XczxQueryUserLogService;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 91 征信数据查询异步回调处理controller
 * @author es_ai
 *
 */
@RestController
@RequestMapping(value = "/backend/xczx")
public class XczxCallBackController {
		
	private static Logger creditLogger = (Logger) LoggerFactory.getLogger("creditXC");
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private XczxQueryUserLogService xczxQueryUserLogService;
	
	
	/**
	 * 91征信回调接口
	 * 这个方法里面包含了两个业务逻辑处理
	 * 1：给我们推送我们1001发起的查询请求数据
	 * 2：调用我们接口查询该用户的借贷情况，并返回给对方
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/queryCallBack.do")
	public String queryCallBack(HttpServletRequest request, HttpServletResponse response){
		
		
		
//		JSONObject json = new JSONObject();
//		json.put("name", "张三");
//		json.put("age", "40");
//		json.put("sex", "男");
		
//        jsonReportService.uploadJson(Constants.XCZX_FILE_UPLOAD_DIR, json, TaskTypeConstants.XCZX_TASK_TYPE, "", "noext");

//		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.XCZX_ANTIFRAUD_CODE);
//		String sign = sysParam.getParamValueThree();		//加密key
//		String companyCode =  sysParam.getParamValueTwo();	//机构编码
//		
//		creditLogger.info("91征信开始回调该接口");
//		
//		PkgHeader reqPkg = new PkgHeader();
//		PkgHeader rspPkg = new PkgHeader();
//		
//		try {
//			
//			BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//			String body = IOUtils.read(reader);
//			
//			//接口返回参数4002初始化
//			rspPkg.setSign(sign);
//			rspPkg.setCustNo(companyCode);
//			
//			if (StringUtils.isNotBlank(body)) {
//
//				reqPkg.parseFromString(body);//请求参数解密
//
//				creditLogger.info("91征信回调该接口 --> body参数有效 请求接口号：" + reqPkg.getTrxCode() + "回调请求参数：" + reqPkg.toString());
//
//				if ("3002".equals(reqPkg.getTrxCode())) {//91征信推送查询申请数据
//
//					creditLogger.info("91征信回调该接口 --> 91征信返回查询申请所对应的数据报告");
//
//					xczxCallBackService.dataSynchronized(reqPkg);//同步对方放回的数据到数据库
//
//					rspPkg.setRetCode(XczxConstants.XCZX_HTTP_SUCCESS);
//					rspPkg.setRetMsg("成功接收到结果");
//					rspPkg.setMsgBody("");
//					rspPkg.setTrxCode("4002");
//
//					creditLogger.info("91征信回调该接口 --> 91征信数据报告同步成功");
//
//
//				} else if ("3001".equals(reqPkg.getTrxCode())) {//91征信发起查询请求
//
//					creditLogger.info("91征信回调该接口 --> body参数有效 --> 平台查询机构 --> 请求接口号：" + reqPkg.getTrxCode() + "回调请求参数：" + reqPkg.toString());
//					
//					//调用app接口获取数据
//					rspPkg.setRetCode(XczxConstants.XCZX_HTTP_SUCCESS);
//					rspPkg.setRetMsg("获取数据成功");
//					rspPkg.setMsgBody(getAppData(reqPkg));
//					rspPkg.setTrxCode("4001");
//					
//					creditLogger.info("91征信回调该接口 --> body参数有效 --> 平台查询机构 --> 请求接口号：" + reqPkg.getTrxCode() + "回调应答参数：" + rspPkg.toString());
//					
//				} else {//未知项
//
//					rspPkg.setRetCode(XczxConstants.XCZX_HTTP_FAIL);
//					rspPkg.setRetMsg("接口号未知");
//					rspPkg.setMsgBody("");
//					rspPkg.setTrxCode("4002");
//
//					creditLogger.info("91征信回调该接口 --> 接口号未知" + reqPkg.toString());
//				}
//
//			} else {
//
//				rspPkg.setRetCode(XczxConstants.XCZX_HTTP_FAIL);
//				rspPkg.setRetMsg("body参数为空");
//				rspPkg.setMsgBody("");
//				rspPkg.setTrxCode("4002");
//
//				creditLogger.info("91征信回调该接口 --> 请求body参数为空 回调失败");
//			} 
//		} catch (Exception e) {
//			
//			rspPkg.setRetCode(XczxConstants.XCZX_HTTP_FAIL);
//			rspPkg.setRetMsg("同步失败");
//			rspPkg.setMsgBody("");
//			rspPkg.setTrxCode("4002");
//			scAlarmImpl.contains(AlarmCodeEnum.JYZX,"91征信风控回调接口异常, 错误信息：-->" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.JYZX);
////			DingdingUtil.setMessage(Constants.WEB_DD_SYS_URL_CODE, "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";91征信风控回调接口异常, 错误信息：-->" + e.getMessage());
//			creditLogger.error(e.getMessage()+"91征信风控回调接口异常", e);
//			e.printStackTrace();
//		}
//		return rspPkg.toPkgStr();
        
        return null;
	}
	
	
	/**
	 * 获取接待人的借贷信息（从APP拉取数据）
	 * @param reqPkg
	 * @return
	 * @throws Exception
	 */
	public String getAppData(PkgHeader reqPkg){
		
		String msgBody = reqPkg.getMsgBody();
		
		String resultStr = "[]";
		
		Map<String, String> resultMap = new HashMap<String,String>();
		
		if(StringUtils.isBlank(msgBody)){
			throw new RuntimeException("91征信回调上送MsgBody对象为空");
		}
		
		JSONObject jsonStr = JSONObject.fromObject(msgBody);
		
		String realName = jsonStr.getString("realName");
		String idCard = jsonStr.getString("idCard");
		String companyCode = jsonStr.getString("companyCode");
		
		if(StringUtils.isBlank(realName) || StringUtils.isBlank(idCard) || StringUtils.isBlank(companyCode)){
			throw new RuntimeException("请求参数不全");
		}
		
		xczxQueryUserLogService.saveEntity(realName,idCard, Constants.QUERY_LOG_TYPE_XCZX);//保存查询记录
		
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.XCZX_ANTIFRAUD_CODE);
		String requestUrl = sysParam.getParamValueFour();
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("realName", realName);
		params.put("idCard", idCard);
		params.put("companyCode", companyCode);
		
		creditLogger.info("91征信回调该接口 --> 3001接口-->获取用户借贷信息，接口请求参数" + params.toString() + ",接口请求地址:" + requestUrl);
		
		String resultData = null;
		
		try {
			RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(3000).setSocketTimeout(3000).build();
			resultData = RiskHttpClientUtil.doPost(requestUrl, params,config);
			
		} catch (Exception e) {

			creditLogger.info("91征信回调该接口 --> 3001接口-->获取用户借贷信息，调用app接口异常，异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		
		creditLogger.info("91征信回调该接口 --> 3001接口-->获取用户借贷信息，接口响应参数" + resultData);
		
		if(StringUtils.isBlank(resultData)){
			creditLogger.info("91征信回调该接口 --> 3001接口-->获取用户借贷信息，调用app接口异常，异常信息：App端获取用户借贷信息失败，失败原因：resultData为空");
		}
		
		try {
			
			resultStr = JSONObject.fromObject(resultData).getString("loanInfos");
			
		} catch (Exception e) {
			
			creditLogger.info("91征信回调该接口 --> 3001接口-->获取用户借贷信息，调用app接口异常，异常信息：" + e.getMessage());
			e.printStackTrace();
		}
		
		resultMap.put("loanInfos", resultStr);
		return JSONObject.fromObject(resultMap.toString()).toString();
	}
	
}
