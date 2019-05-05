package com.shangyong.backend.service.jg.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.dao.jg.JgInfoCheckServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.jg.JgInfoCheck;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.jg.JgInfoCheckService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.utils.SpringIocUtils;
import com.shangyong.utils.Base64Util;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取极光黑名单信息service类
 * @author 艾李强
 *
 */
@Service
public class JgInfoCheckServiceImpl implements JgInfoCheckService{
	private static Logger logger=LoggerFactory.getLogger("jg");

	
	@Autowired
	JgInfoCheckServiceDao jgInfoCheckServiceDao;
	
	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private RiskRuleService riskRuleService;
	
	
	@SuppressWarnings("static-access")
	@Override
	public RuleResult getJqInfoCheck(Application application) {
		RuleResult result = new RuleResult();
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
    	SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
    	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.JG_FQZ_CODE);
    	String jgBlackUrl=sysParam.getParamValueOne();//请求地址
    	/** 判断证件类型**/ 
		 if (!"1".equals(application.getCertType())) {
			/** 不是身份证,报错**/
			 throw new RuntimeException("证件类型异常,请输入身份证类型:"+application.getCertType());
		}
		Assert.hasText(application.getApplicationId(),"ApplicationId参数为空");
    	Assert.hasText(application.getName(),"Name参数为空");
		Assert.hasText(application.getCertCode(),"CertCode参数为空");
		Assert.hasText(application.getPhoneNum(),"PhoneNum参数为空");
    	String name=application.getName();//用户姓名
    	String id_number=application.getCertCode();//获取身份证信息
    	String phone=application.getPhoneNum();
    	jgBlackUrl+="?name="+name+"&id_number="+id_number+"&phone="+phone;
    	logger.info(">>>>调用极光认证请求地址>>jgBlackUrl:"+jgBlackUrl);
        String devkey=sysParam.getParamValueTwo();//密钥1
        String devSecret=sysParam.getParamValueThree();//密钥2
        String base64_auth_string=devkey+":"+devSecret;
        String AUTH_STR=Base64Util.encode(base64_auth_string.getBytes());
        Map<String, String> headers=new HashMap<>();
        headers.put("Authorization", "Basic "+AUTH_STR);
        headers.put("accept", "application/json");
        try {
        	String content= RiskHttpClientUtil.doGetFromHeader(jgBlackUrl, headers);
        	if(StringUtils.isBlank(content)){
        		throw new RuntimeException("极光认证返回内容为空");
        	}
        	JSONObject returnJson=JSONObject.fromObject(content);
        	String code=returnJson.getString("code");
        	String message=returnJson.getString("message");
        	if ("2001".equals(code)) {
				return result;
			}
        	if(!"2000".equals(code)){
        		throw new RuntimeException("调用极光数据返回异常："+message);
        	} 
        	
        	JgInfoCheck jgInfoCheck=new JgInfoCheck();
        	jgInfoCheck.setBuApplicationId(application.getApplicationId());
        	jgInfoCheck.setIdNumber(id_number);
        	jgInfoCheck.setName(name);
        	jgInfoCheck.setPhone(phone);
        	jgInfoCheck.setRemark(message);
        	jgInfoCheck.setState("0");//是否命中
        	
        	//----------------------------------------获取数据入库---------------------------
        	if(returnJson.has("data")){
        		JSONObject dataJson = returnJson.getJSONObject("data");
        		jgInfoToBean(jgInfoCheck,dataJson);

				//添加禁止项数据 关系圈风险分
				BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.JI_GUANG_RISK_ALLOCATION_AURORA_RELATION_CIRCLE.getCode(),jgInfoCheck.getCircleScore());
				//添加禁止项数据 逾期分
				BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.JI_GUANG_AURORA_OVERDUE_ALLOCATION.getCode(),jgInfoCheck.getOverdueScore());
				//添加禁止项数据 违约分
				BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.JI_GUANG_AURORA_DEFAULT_ALLOCATION.getCode(),jgInfoCheck.getViolationScore());
				//调用禁止项，获取用户校验结果
				result = riskRuleService.querySafeRuleApi(application, checkList);
            	
            	//存mysql
        		jgInfoCheckServiceDao.saveEntity(jgInfoCheck);
            	//存阿里云 mongodb
                jsonReportService.uploadJson(Constants.JG_UPLOAD_DIR,returnJson, TaskTypeConstants.JG_TASK_TYPE, TaskTypeConstants.JG_TASK_NAME, TaskTypeConstants.JG_TASK_ISEND, application.getApplicationId(), "noext");
        	}        	        
		} catch (Exception e) {
			throw new RuntimeException("调用极光数据返回异常："+e.getMessage(), e);
		}
  		return result;
	}
	
	
	private JgInfoCheck jgInfoToBean(JgInfoCheck jgInfoCheck,JSONObject dataJson){
		jgInfoCheck.setJgInfoId(UUIDUtils.getUUID());
		jgInfoCheck.setCreateTime(DateUtils.getDate(new Date()));
		jgInfoCheck.setModifyTime(DateUtils.getDate(new Date()));
		if(dataJson.has("score")){
			jgInfoCheck.setRiskScore(dataJson.getDouble("score"));//风险分数
		}
		if(dataJson.has("jid")){
			jgInfoCheck.setJgReturnId(dataJson.getString("jid"));//极光编号
		}
		if(dataJson.has("description")){
			jgInfoCheck.setRiskDescription(dataJson.getString("description"));//决策描述
		}
		//各项分数入库
		if(dataJson.has("hits")){
			JSONArray hitsArray=dataJson.getJSONArray("hits");
			for (int i = 0; i < hitsArray.size(); i++) {
				JSONObject hit=hitsArray.getJSONObject(i);
				if(hit.has("name")&&hit.has("score")){
					switch (hit.getString("name")) {
					case "逾期分":
						jgInfoCheck.setOverdueScore(hit.getInt("score"));
						break;
					case "违约分":
						jgInfoCheck.setViolationScore(hit.getInt("score"));
						break;
					case "关系圈风险分":
						jgInfoCheck.setCircleScore(hit.getDouble("score"));
						break;
					}
				}
			}
			
		}
		return jgInfoCheck;
	}

	/**
	 *查取JgInfoCheck对象
	 */
	@Override
	public JgInfoCheck getJgInfoCheckByAid(String applicationId) {
		Assert.hasText(applicationId, "申请单不能为空");
		JgInfoCheck jgInfoCheck = this.jgInfoCheckServiceDao.queryByAid(applicationId);
		return jgInfoCheck;
	}
}
