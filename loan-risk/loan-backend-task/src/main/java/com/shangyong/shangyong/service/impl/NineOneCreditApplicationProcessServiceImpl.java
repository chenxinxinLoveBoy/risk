
package com.shangyong.shangyong.service.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.xczx.XczxCallBackService;
import com.shangyong.backend.utils.xczx.HttpClientZX;
import com.shangyong.shangyong.service.NineOneCreditApplicationProcessService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */

@Service
public class NineOneCreditApplicationProcessServiceImpl implements NineOneCreditApplicationProcessService {

	
	private static Logger logger = LoggerFactory.getLogger("nineOneCreditApplicationProcessServiceImpl");
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	@Autowired
	private XczxCallBackService xczxCallBackService;
	
	@Autowired
	private IScAlarm scAlarmImpl;
	
	@Override
	@Transactional
	public void process(List<Map<String, Object>> list) {
		logger.info("[91征信]数据同步定时任务获取待处理数据, 待处理数据条数： " + list.size() + ",处理开始");
		for (int i = 0; i < list.size(); i++) {
			logger.info("[91征信]数据同步定时任务获取待处理数据，当前任务处理心跳正常");
			String applicationId = "";
			try {
				long startTime = System.currentTimeMillis();
				applicationId = (String) list.get(i).get("applicationNumber");

				logger.info("[91征信] 定时任务单笔数据同步处理开始，申请单编号：" + applicationId);
				httpRequestNineOne(applicationId);

				logger.info("[91征信] 单笔数据处理结束，耗时时间： " + (System.currentTimeMillis() - startTime) + "ms");
			} catch (Throwable e) {
				scAlarmImpl.contains(AlarmCodeEnum.JYZX,"单笔91征信处理异常，申请单编号：" + applicationId + "; 错误信息：-->" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.JYZX);
				logger.error("[91征信] 单笔数据同步处理异常，申请单编号：" + applicationId + "; 错误信息：-->" + e.getMessage(), e);
			}
		}
		logger.info("[91征信] 数据同步定时任务处理数据条数： " + list.size() + ",处理结束");
	}

	
	/**
	 * 主动从91征信同步数据报告到本地
	 * @param applicationNumber
	 */
	public void httpRequestNineOne(String applicationNumber){
		try {
			String trxNo = getGuidForApplicationId(applicationNumber);//91查询申请单id
			if(StringUtils.isNotBlank(trxNo)){
				logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo);
				SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.XCZX_ANTIFRAUD_CODE);
				
				String requestUrl = sysParam.getParamValueOne();	//请求url
				String companyCode =  sysParam.getParamValueTwo();	//机构编码
				String sign = sysParam.getParamValueThree();		//加密key
				Map<String,String> paramsMap = new HashMap<String,String>();
				paramsMap.put("trxNo", trxNo); 
				String bodyStr = JSONObject.fromObject(paramsMap).toString();
				
				logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->http请求参数：" + bodyStr);
				
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
				logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->http响应参数：" + dataPkg);
				if(TaskTypeConstants.XCZX_HTTP_SUCCESS.equals(dataPkg.getRetCode())){  //0000 接口调用成功返回code
					
					xczxCallBackService.dataSynchronized(dataPkg);//同步对方放回的数据到数据库
					
					logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->数据报告同步成功");
				}else{
					logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单id："+ applicationNumber + "-->91查询申请单号：" + trxNo + "-->返回查询状态不为0000");
				}
			}else{
				logger.info("[91征信] 根据查询申请单号获取数据报表 --> 申请单号：" + applicationNumber + "-->91查询申请单id为空");
			}
		}catch (Exception e) {
			logger.error("[91征信] 根据查询申请单号获取数据报表 --> 申请单号：" + applicationNumber + "-->数据同步定时任务异常，异常信息：" + e.getMessage(), e);
			throw new RuntimeException("[91征信] 根据查询申请单号获取数据报表 -->申请单号：" + applicationNumber + "-->数据同步定时任务异常，异常信息：" + e.getMessage(), e);
		}
	}
	
	/**
	 * 根据申请单号获取91征信查询申请单id
	 * @param applicationNumber 申请单号
	 * @return 91征信查询申请单id
	 */
	public String getGuidForApplicationId(String applicationNumber){
		
		String result = "";
		
		BuThirdpartyReport thirdPartyReport = new BuThirdpartyReport();
		thirdPartyReport.setTaskType(TaskTypeConstants.XCZX_TASK_TYPE);
		thirdPartyReport.setBuApplicationId(applicationNumber);
		thirdPartyReport = xczxCallBackService.getInfoByObj(thirdPartyReport);
		
		if(thirdPartyReport != null && StringUtils.isNotBlank(thirdPartyReport.getTaskId())){
			if(StringUtils.isNotBlank(thirdPartyReport.getJosnStoragePath())){
				throw new RuntimeException("已存在该用户的征信请求记录，请勿重新操作！！！");
			}
			result = thirdPartyReport.getTaskId();
		}
		return result;
	}
}

