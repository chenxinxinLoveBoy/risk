package com.shangyong.backend.service.yjf.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.dao.yjf.YjfInfoCheckDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.yjf.YjfInfoCheck;
import com.shangyong.backend.entity.yjf.YjfJsonBean;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.yjf.YjfBlackListService;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;
//import com.yiji.openapi.tool.YijifuGateway;

import net.sf.json.JSONObject;
/**
 * 易极付黑名单
 * @author Smk
 *
 */
@Service
public class YjfBlackListServiceImpl implements YjfBlackListService{
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	@Autowired
	private YjfInfoCheckDao yjfInfoCheckDao;
	
	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private RiskRuleService riskRuleService;
	
	private static Logger yjfLogger = LoggerFactory.getLogger("yjfBlackList");

	@Override
	public RuleResult saveYjfBlackList(Application application) {
		
		RuleResult ruleResult = new RuleResult();
		Map<String,String> params = new HashMap<String,String>();
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
		yjfLogger.info("易极付黑名单开始"+application);
		try {
			SysParam yjfParameter = sysParamRedisService.querySysParamByParamValueRedis(Constants.YJF_BLACK_CODE);
			
			//判断
			if (yjfParameter ==null) {
				yjfLogger.error("从系统参数表获取参数失败,key : YJF_BLACK_CODE");
				throw new RuntimeException("从系统参数表获取参数失败,key : YJF_BLACK_CODE");
			}
			
			//系统请求参数
			String requestUrl = yjfParameter.getParamValueOne();
			String memberId = yjfParameter.getParamValueTwo();
			String securityKey = yjfParameter.getParamValueThree();
			String applicationId = application.getApplicationId();
			String certType = application.getCertType();
			String certCode = application.getCertCode();
			
			//断言判断
			Assert.hasText(requestUrl, "请求参数url地址不能为空");
			Assert.hasText(memberId, "memberId不能为空");
			Assert.hasText(securityKey, "securityKey不能为空");
			Assert.hasText(applicationId, "applicationId申请单编号不能为空");
			Assert.hasText(certCode, "身份证不能为空");

			//添加参数
			params.put("service","noneFocusUserQuery");
			params.put("partnerId",memberId);
			params.put("orderNo", UUIDUtils.getUUID());
			params.put("merchOrderNo", UUIDUtils.getUUID());
			params.put("realName", application.getName());
			 /** 判断证件类型**/ 
			 if (StringUtils.isBlank(certType) && !"1".equals(certType)) {
				/** 不是身份证,报错**/
				 throw new RuntimeException("证件类型异常,请输入身份证类型:"+application.getCertType());
			 }
			params.put("identityNO", certCode);
			
			String result = null ;//YijifuGateway.getOpenApiClientService().doPost(requestUrl, params, securityKey, 10, 10);
			JSONObject resultJson = JSONObject.fromObject(result);
			if(StringUtils.isBlank(result)){
				throw new RuntimeException("调用易极付黑名单数据异常");
			}
			
			YjfJsonBean jsonToBean = (YjfJsonBean) JacksonUtils.JsonToBean(result, YjfJsonBean.class);
			if (!jsonToBean.getSuccess()) {
				throw new RuntimeException("调用易极付黑名单数据失败");
			}
			String dateTime = DateUtils.getDate(new Date());
			//存入主表
			YjfInfoCheck yjfInfoCheck = new YjfInfoCheck();
			BeanUtils.copyProperties(jsonToBean, yjfInfoCheck);
			yjfInfoCheck.setBuApplicationId(applicationId);
			yjfInfoCheck.setYjfInfoId(UUIDUtils.getUUID());
			yjfInfoCheck.setState("1");
			yjfInfoCheck.setCreateTime(dateTime);
			yjfInfoCheck.setModifyTime(dateTime);
			yjfInfoCheck.setSuccess(String.valueOf(jsonToBean.getSuccess()));
			yjfLogger.info("易极付主表入参"+yjfInfoCheck);
			int count = yjfInfoCheckDao.saveEntity(yjfInfoCheck);
			Assert.isTrue(count>0,"易极付插入主表失败");
			//存阿里云存mongo
	        jsonReportService.uploadJson(Constants.YJF_UPLOAD_DIR, resultJson, TaskTypeConstants.YJF_TASK_TYPE, TaskTypeConstants.YJF_TASK_NAME, TaskTypeConstants.YJF_TASK_ISEND, application.getApplicationId(), "noext");
	    	//添加禁止项数据
			BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.YI_JI_PAY_BLACKLIST.getCode(), jsonToBean.getUserType());
			//调用禁止项
			ruleResult = riskRuleService.querySafeRuleApi(application, checkList);
			
			if(ruleResult == null){
				throw new RuntimeException("易极付黑名单数据报告获取-->调用taskCallBackService-->resultObj 为空");
			}
		} catch (Exception e) {
			yjfLogger.error("易极付黑名单调用失败"+e.getMessage(), e);
			throw new RuntimeException("易极付黑名单调用失败"+e.getMessage(), e);
		}
		yjfLogger.info("易极付黑名单,处理完成");
		return ruleResult;
	}

	/**
	 * 查询mysql存储数据
	 * @param applicationId
	 * @return
	 */
	@Override
	public YjfInfoCheck getJyjfBlackListkByAid(String applicationId) {
		Assert.hasText(applicationId, "申请单号不能为空");
		YjfInfoCheck yjfInfoCheck = new YjfInfoCheck();
		try {
			yjfInfoCheck = yjfInfoCheckDao.queryByAId(applicationId);
		} catch (Exception e) {
			throw new RuntimeException("根据申请单号查询失败:"+e.getMessage(), e);
		}
		return yjfInfoCheck;
	}
}
