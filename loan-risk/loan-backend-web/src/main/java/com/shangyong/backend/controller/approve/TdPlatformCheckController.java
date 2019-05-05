package com.shangyong.backend.controller.approve;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.approval.BuSpApproval;
import com.shangyong.backend.entity.approval.TdPlatformCheck;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.approval.service.BuSpApprovalService;
import com.shangyong.backend.service.approval.service.TdPlatformCheckService;
import com.shangyong.backend.service.impl.UUserServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

//import com.shangyong.msgcenter.parameter.SmsReqInfo;
//import com.shangyong.msgcenter.parameter.SmsRspInfo;
//import com.shangyong.msgcenter.smsService.SmsService;


@Controller
@RequestMapping(value ="/backend/approval")
public class TdPlatformCheckController {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(TdPlatformCheckController.class);
	
	
	/**
	 * 多头借贷申请查询接口
	 */
	@Autowired
	private TdPlatformCheckService tdPlatformCheckService;
	
//	@Reference(version="1.0.0",retries=-1,timeout=15000)
//	private SmsService smsService;
	
	@Autowired
	private IScAlarm iScAlarm;
	
	/**
	 * 操作日志查询接口
	 */
	@Autowired
	private BuSpApprovalService buSpApprovalService;
	/**
	 * 用户service
	 */
	@Autowired
	private UUserServiceImpl uUserServiceImpl;
	/***
	 * 同盾多头信息查询
	 * @param request
	 * @param response
	 * @param appId
	 */
	@RequestMapping(value = "/findPlatformInfo.do", method = RequestMethod.POST)
	public void findPlatformInfo(HttpServletRequest request, HttpServletResponse response, String applicationId){
		try { 
			List<TdPlatformCheck> platform;//多头借贷信息
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();//返回结果
			if(StringUtils.isNotBlank(applicationId)){
				platform=tdPlatformCheckService.getListById(applicationId);//最多只有3条数据
				if(platform != null &&platform.size()>0){
					map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
					map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
					map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
					map.put("platform", platform);
					logger.info("==>查询多头借贷信息成功 ！");
					SpringUtils.renderJson(response, map);
				} else{
					logger.info("==>无多头借贷信息 ！");
				}
			}else{
				logger.info("==>查询多头借贷信息失败 ！applicationId不正确");
				map.put(Constants.CODE, CodeUtils.BACKEND_NOT_ALOW.getCode());
				map.put(Constants.MESSAGE, CodeUtils.BACKEND_NOT_ALOW.getMessage());
				SpringUtils.renderJson(response, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		
	}

	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @param content 短信内容
	 * @param Mobile 手机号码
	 */
	@RequestMapping(value = "/sendMessage.do", method = RequestMethod.POST)
	public void sendMessage(HttpServletRequest request, HttpServletResponse response, String contents, String mobile){
		try {
			if( StringUtils.isNotBlank(mobile) ){
				byte[] utf8=contents.getBytes("UTF-8");
				contents=new String(utf8,"UTF-8");
				String msg=java.net.URLEncoder.encode(contents,  "utf-8");//短信内容乱码处理
//				SmsReqInfo smsReqInfo = new SmsReqInfo();
//				smsReqInfo.setBusinessType("4");
                ////
				List<String> list = new ArrayList<String>();
				list.add(mobile);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("phoneNum", list);
				//将map转为json格式
				JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
				String phoneNumStr = itemJSONObj.toString();
				//////////
//				smsReqInfo.setPhoneNum(phoneNumStr);
//				SmsRspInfo sendSms = smsService.sendSms(smsReqInfo);
//				if (!"200".equals(sendSms.getRspCode())) {
//					iScAlarm.sendDingdingMsg("审核短信发送失败,:"+mobile+",错误信息:"+sendSms.getRspMsg(), "1");
//				}
				logger.info("==>发送短信成功！");
//				logger.info("==>发送短信返回信息：" + sendSms);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS);
			}else{
				logger.info("==>发送短信失败！手机号码不正确");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_NOT_ALOW);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		
	}
	
	/**
	 * 系统操作日志
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getBuSpApprovalList.do", method = RequestMethod.POST)
	public void getBuSpApprovalListById(HttpServletRequest request, HttpServletResponse response, BuSpApproval buSpApproval) {
		try {
			UUserBo user= TokenManager.getUser();// 直接从token取
			UUserBo userBo= uUserServiceImpl.getObjectById(user);// 查詢用戶信息表
			 buSpApproval.setReceiveName(userBo.getNickName());
			 int count=buSpApprovalService.listAllCount(buSpApproval);
			
			 //BuSpApproval buSpApproval=new BuSpApproval();
			 List<BuSpApproval> list=buSpApprovalService.getBuSpApprovalList(buSpApproval);
			 logger.info("操作日志查询结果====>总条数："+count+"条");
			 JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
