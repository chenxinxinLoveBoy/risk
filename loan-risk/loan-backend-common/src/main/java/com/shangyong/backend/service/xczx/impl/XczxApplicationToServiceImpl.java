package com.shangyong.backend.service.xczx.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.xczx.XczxApplicationToService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.xczx.HttpClientZX;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

@Service
public class XczxApplicationToServiceImpl implements XczxApplicationToService{

	private static Logger creditLogger = (Logger) LoggerFactory.getLogger("creditXC");

	@Autowired
	private BuThirdpartyReportDao buThirdpartyReportDao;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

	private int READ_OUT_TIME = 60 * 60 * 24;

	@Override
	public RuleResult sendQuery(String applicationNumber, String realName, String idCard){
		RuleResult result = new RuleResult();
		checkParamsVerification(applicationNumber, realName, idCard);
		creditLogger.info("[91征信-调用1001] 启动，申请单基础参数：appSerialNumber:" + applicationNumber + ",realName：" + realName + ",idCard:" + idCard);
		
		if (!this.checkApplicationIsExit(applicationNumber)) {
			creditLogger.info("[91征信-调用1001] 申请单号：" + applicationNumber + "-->该申请单已向91征信发起过查询申请,请调用91 1002 查询接口获取数据！！！");
			return result;
		}
		
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.XCZX_ANTIFRAUD_CODE);
		String requestUrl = sysParam.getParamValueOne(); //请求url
		String companyCode = sysParam.getParamValueTwo(); //机构编码
		String sign = sysParam.getParamValueThree(); //加密key
		
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("realName", realName);
		paramsMap.put("idCard", idCard);
		
		String bodyStr = JSONObject.fromObject(paramsMap).toString();
		
		PkgHeader reqPkg = new PkgHeader();
		
		reqPkg.setVersion("01"); //默认01
		reqPkg.setCustNo(companyCode); //公司代码
		reqPkg.setEncode("01"); //01.UTF8 02.GBK
		reqPkg.setTrxCode("1001"); //报文编号 默认四位 例:0001
		reqPkg.setEncryptType("01"); //加密类型 01.不加密 02.RSA
		reqPkg.setMsgType("01"); //01.JSON 02.XML 03.Protobuf
		reqPkg.setMsgBody(bodyStr); //报文主体
		reqPkg.setSign(sign); //签名
		
		creditLogger.info("[91征信-调用1001接口] 申请单号：" + applicationNumber + "-->http接口请求参数：" + reqPkg.toString());
		PkgHeader dataPkg = HttpClientZX.sendPostReqeust(requestUrl, reqPkg, null);
		
		creditLogger.info("[91征信-调用1001接口] 申请单号：" + applicationNumber + "http接口响应信息：" + dataPkg.getMsgBody());
		if (TaskTypeConstants.XCZX_HTTP_SUCCESS.equals(dataPkg.getRetCode())) {

			String msgBody = dataPkg.getMsgBody();

			if (StringUtils.isBlank(msgBody)) {
				throw new RuntimeException("[91征信-调用1001接口] 申请单号：" + applicationNumber + "91征信返回的MsgBody对象为空");
			}

			JSONObject msgBodyJson = JSONObject.fromObject(msgBody);
			String guid = msgBodyJson.getString("trxNo");

			if (StringUtils.isBlank(guid)) {
				throw new RuntimeException("[91征信-调用1001接口] 申请单号：" + applicationNumber + "91征信返回的trxNo为空");
			}
			//入库数据
			BuThirdpartyReport buThirdpartyReport = new BuThirdpartyReport();

			String dateStr = DateUtils.getDate(new Date());
			String thirdPartyReportId = UUIDUtils.getUUID();
			
			buThirdpartyReport.setThirdpartyReportId(thirdPartyReportId);
			buThirdpartyReport.setBuApplicationId(applicationNumber);
			buThirdpartyReport.setTaskType(TaskTypeConstants.XCZX_TASK_TYPE);
			buThirdpartyReport.setTaskId(guid);
			buThirdpartyReport.setCreateTime(dateStr);
			buThirdpartyReport.setUpdateTime(dateStr);
			buThirdpartyReport.setRemark("[91征信-调用1001接口]接口查询GUID入库");

			if (buThirdpartyReportDao.saveEntity(buThirdpartyReport) < 1) {
				creditLogger.info("[91征信-调用1001接口] 申请单号：" + applicationNumber + " 数据入库失败");
				throw new RuntimeException("[91征信-调用1001接口] 申请单号：" + applicationNumber + "数据入库失败");
			}
			
			//redis中存放数据
			//判断redis中是否存在该key的数据
			String xczxRsCode = Constants.XCZX_REDIS_CODE + guid;
			if(RedisUtils.exists(xczxRsCode)){
				RedisUtils.del(xczxRsCode);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("buApplicationId", applicationNumber);
			jsonObject.put("taskType", TaskTypeConstants.XCZX_TASK_TYPE);
			jsonObject.put("thirdpartyReportId", thirdPartyReportId);
			
			RedisUtils.setEx(xczxRsCode, READ_OUT_TIME, jsonObject.toString());
			creditLogger.info("[91征信-调用1001接口]  redis中存放值成功：key = " + xczxRsCode + ",val = " + jsonObject.toString());
			creditLogger.info("[91征信-调用1001接口]  APP申请单号：" + applicationNumber + " 调用91征信1001接口请求成功-->数据入库成功");
		} else {
			creditLogger.info("[91征信-调用1001接口] 申请单号：" + applicationNumber + " 调用91征信1001接口请求失败");
			throw new RuntimeException("[91征信-调用1001接口] 申请单号：" + applicationNumber + " 调用91征信1001接口请求失败");
		} 
		return result;
	}
	
	/**
	 * 根据闪贷申请单id查询是否已经向91征信发起过查询申请
	 * @param applicationNumber 闪贷申请单id
	 * @return
	 */
	private boolean checkApplicationIsExit(String applicationNumber){
		boolean flag = false;
		BuThirdpartyReport obj = new BuThirdpartyReport();
		obj.setTaskType(TaskTypeConstants.XCZX_TASK_TYPE);
		obj.setBuApplicationId(applicationNumber);
		obj = buThirdpartyReportDao.getXczxByApplicationId(obj);
		
		creditLogger.info("[91征信-调用1001接口] applicationNumber:"+applicationNumber +  ",obj:" + obj);
		if(obj == null){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 91查询申请接口数据校验
	 * @param applicationNumber 闪贷申请单号
	 * @param realName 用户姓名
	 * @param idCard 用户身份证号
	 * @throws Exception 异常信息
	 */
	public void checkParamsVerification(String applicationNumber, String realName, String idCard){
		if(StringUtils.isEmpty(applicationNumber)){
			throw new RuntimeException("[91征信-调用1001接口] 闪贷申请单号为空！！！");
		}
		if(StringUtils.isEmpty(realName)){
			throw new RuntimeException("[91征信-调用1001接口] 用户姓名为空！！！");
		}
		if(StringUtils.isEmpty(idCard)){
			throw new RuntimeException("[91征信-调用1001接口] 用户身份证号为空！！！");
		}
	}
}
