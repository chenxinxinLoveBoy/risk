package com.shangyong.backend.controller.xczx;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.entity.xczx.XczxApplicationData;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.xczx.XczxApplicationDataService;
import com.shangyong.backend.service.xczx.XczxApplicationToService;
import com.shangyong.backend.service.xczx.XczxCallBackService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.xczx.HttpClientZX;
import com.shangyong.utils.SpringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/credit")
public class XczxGetDataForGuid {
		
	private static Logger creditLogger = (Logger) LoggerFactory.getLogger("creditXC");
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	@Autowired
	private XczxCallBackService xczxCallBackService;
	
	@Autowired
	private XczxApplicationDataService xczxApplicationDataService;
	
	@Autowired
	private XczxApplicationToService service;
	
	@Autowired
    ApplicationServiceImpl applicationServiceImpl;

	@Autowired
	private IScAlarm scAlarmImpl;
	/**
	 * 91征信根据查询申请单id获取数据报表
	 * @desc: 当91征信给我们推送数据报告失败的情况下，我们就需要手动触发该操作，主动的去91征信根据前面所返回的查询申请单id调用1002接口来获取被查询人的数据报告
	 * 调用这个接口对方会同步返回我们相应的数据报告
	 */
	@RequestMapping(value = "/queryByGuid.do")
	public void queryByGuid(HttpServletRequest request, HttpServletResponse response, Application application){
		
		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
		
		String applicationNumber = application.getApplicationId();
		
		try {
			
			checkParamsVarification(applicationNumber);//参数校验
			
			String trxNo = getGuidForApplicationid(applicationNumber);//91查询申请单id
			
			if(StringUtils.isNotBlank(trxNo)){
			
				creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo);
				
				SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.XCZX_ANTIFRAUD_CODE);
				
				String requestUrl = sysParam.getParamValueOne();	//请求url
				String companyCode =  sysParam.getParamValueTwo();	//机构编码
				String sign = sysParam.getParamValueThree();		//加密key
				
				Map<String,String> paramsMap = new HashMap<String,String>();
				paramsMap.put("trxNo", trxNo); 
				
				String bodyStr = JSONObject.fromObject(paramsMap).toString();
				
				creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->http请求参数：" + bodyStr);
				
				PkgHeader reqPkg = new PkgHeader();
				reqPkg.setVersion("01");		//默认01
				reqPkg.setCustNo(companyCode);	//公司代码
				reqPkg.setEncode("01");			//01.UTF8 02.GBK
				reqPkg.setTrxCode("1002");		//报文编号 默认四位 例:0001
				reqPkg.setEncryptType("01");	//加密类型 01.不加密 02.RSA
				reqPkg.setMsgType("01");		//01.JSON 02.XML 03.Protobuf
				reqPkg.setMsgBody(bodyStr);		//报文主体
				reqPkg.setSign(sign);           //签名
	
				PkgHeader dataPkg = HttpClientZX.sendPostReqeust(requestUrl, reqPkg, null);
				
				creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->http响应参数：" + dataPkg);
				
				if(TaskTypeConstants.XCZX_HTTP_SUCCESS.equals(dataPkg.getRetCode())){//0000 接口调用成功返回code
	
					xczxCallBackService.dataSynchronized(dataPkg);//同步对方放回的数据到数据库
						
					creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->数据报告同步成功");
					resultMap.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
					resultMap.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
				
				}else{
					
					creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->返回查询状态不为0000");
					resultMap.put(Constants.CODE, CodeUtils.FAIL.getCode());
					resultMap.put(Constants.MESSAGE, CodeUtils.FAIL.getMessage());
				
				}
			}else{
				
				creditLogger.info("91征信根据查询申请单号获取数据报表 --> 洪澄申请单号：" + applicationNumber + "-->91查询申请单id为空");
				resultMap.put(Constants.CODE, CodeUtils.XCZX_FAIL.getCode());
				resultMap.put(Constants.MESSAGE, CodeUtils.XCZX_FAIL.getMessage());
			}
			
		} catch (Exception e) {
			
			resultMap.put(Constants.CODE, CodeUtils.FAIL.getCode());
			resultMap.put(Constants.MESSAGE, CodeUtils.SYS_PARAM_FAIL.getMessage());

			scAlarmImpl.contains(AlarmCodeEnum.JYZX,"91征信根据查询申请单号获取数据报表接口处理异常, 错误信息：-->" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.JYZX);
//			DingdingUtil.setMessage(Constants.XCZX_ANTIFRAUD_CODE, "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";91征信根据查询申请单号获取数据报表接口处理异常, 错误信息：-->" + e.getMessage());
			creditLogger.error(e.getMessage()+"91征信根据查询申请单号获取数据报表接口处理异常", e);
			e.printStackTrace();
		}
		
		SpringUtils.renderJson(response, resultMap);
		return;
	}
	
	/**
	 * 接口请求参数校验
	 * @param applicationNumber 洪澄申请单id
	 */
	public void checkParamsVarification(String applicationNumber){
		
		if(StringUtils.isEmpty(applicationNumber)){
			throw new RuntimeException("洪澄申请单号为空！！！");
		}
	}
	
	/**
	 * 根据洪澄申请单号获取91征信查询申请单id
	 * @param applicationNumber 洪澄申请单号
	 * @return 91征信查询申请单id
	 */
	public String getGuidForApplicationid(String applicationNumber){
		
		String result = "";
		
		BuThirdpartyReport thirdpartyReport = new BuThirdpartyReport();
		thirdpartyReport.setTaskType(TaskTypeConstants.XCZX_TASK_TYPE);
		thirdpartyReport.setBuApplicationId(applicationNumber);
		thirdpartyReport = xczxCallBackService.getInfoByObj(thirdpartyReport);
		
		if(thirdpartyReport != null && StringUtils.isNotBlank(thirdpartyReport.getTaskId())){
			
			if(StringUtils.isNotBlank(thirdpartyReport.getJosnStoragePath())){
				throw new RuntimeException("已存在该用户的请求记录，请勿重新操作！！！");
			}
			
			result = thirdpartyReport.getTaskId();
		}
		
		return result;
	}
	
	
	@RequestMapping(value="/test.do")
	public void Test(){
		
		service.sendQuery("56fbd31ed8bd4fa99ab54a556bd9d8d7", "陈水金", "350424198301082038");
	}
	
	
	@RequestMapping(value = "/getDataInfo.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, XczxApplicationData xczxApplicationData, String platformId) {
		
		try {
			if(!"".equals(platformId) && "".equals(xczxApplicationData.getBuApplicationId())){
				Application application=applicationServiceImpl.getApplicationIdByPlatformId(platformId);
				xczxApplicationData.setBuApplicationId(application.getApplicationId());
			}
			int count = xczxApplicationDataService.findAllCount(xczxApplicationData);
			List<XczxApplicationData> list = xczxApplicationDataService.getDataInfo(xczxApplicationData);
			JSONUtils.toListJSON(response, list, count);
			
		} catch (Exception e) {
			e.printStackTrace();
			creditLogger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
}