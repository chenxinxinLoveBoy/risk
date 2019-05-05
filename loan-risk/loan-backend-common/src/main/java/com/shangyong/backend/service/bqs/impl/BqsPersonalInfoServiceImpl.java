package com.shangyong.backend.service.bqs.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.dao.bqs.BqsBillDetailsServiceDao;
import com.shangyong.backend.dao.bqs.BqsBillsInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsCallsInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsForwardInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsNetplayInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsPaymentsInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsPersonalInfoServiceDao;
import com.shangyong.backend.dao.bqs.BqsSmsInfoServiceDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.bqs.BqsBillDetails;
import com.shangyong.backend.entity.bqs.BqsBillsInfo;
import com.shangyong.backend.entity.bqs.BqsCallsInfo;
import com.shangyong.backend.entity.bqs.BqsForwardInfo;
import com.shangyong.backend.entity.bqs.BqsNetplayInfo;
import com.shangyong.backend.entity.bqs.BqsPaymentsInfo;
import com.shangyong.backend.entity.bqs.BqsPersonalInfo;
import com.shangyong.backend.entity.bqs.BqsSmsInfo;
import com.shangyong.backend.entity.bqs.jsonbean.BaiQiShiBean;
import com.shangyong.backend.entity.bqs.jsonbean.MnoBillRecordDetails;
import com.shangyong.backend.entity.bqs.jsonbean.MnoBillRecords;
import com.shangyong.backend.entity.bqs.jsonbean.MnoCallRecords;
import com.shangyong.backend.entity.bqs.jsonbean.MnoDetail;
import com.shangyong.backend.entity.bqs.jsonbean.MnoForwardRecords;
import com.shangyong.backend.entity.bqs.jsonbean.MnoNetPlayRecords;
import com.shangyong.backend.entity.bqs.jsonbean.MnoPaymentRecords;
import com.shangyong.backend.entity.bqs.jsonbean.MnoPersonalInfo;
import com.shangyong.backend.entity.bqs.jsonbean.MnoSmsRecords;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.bqs.BqsPersonalInfoService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.backend.utils.SpringIocUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

@Service
public class BqsPersonalInfoServiceImpl implements BqsPersonalInfoService {
	private static  Logger logger = LoggerFactory.getLogger("bqsYys");
	@Autowired
	BqsPersonalInfoServiceDao bqsPersonalInfoServiceDao;
	@Autowired
	BqsCallsInfoServiceDao bqsCallsInfoServiceDao;
	@Autowired
	BqsSmsInfoServiceDao bqsSmsInfoServiceDao;
	@Autowired
	BqsBillsInfoServiceDao bqsBillsInfoServiceDao;
	@Autowired
	BqsBillDetailsServiceDao bqsBillDetailsServiceDao;
	@Autowired
	BqsPaymentsInfoServiceDao bqsPaymentsInfoServiceDao;
	@Autowired
	BqsNetplayInfoServiceDao bqsNetplayInfoServiceDao;
	@Autowired
	BqsForwardInfoServiceDao bqsForwardInfoServiceDao;
	@Autowired
	private JsonReportService jsonReportService;

	@Override
	public RuleResult getBqsPersonalInfo(Application application) {
		RuleResult ruleResult = new RuleResult();
		logger.info(">>>>开始调用白骑士获取运营商原数据接口>>application:" + application.toString());
		SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
    	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.BQS_YYS_NEW_CODE);
		String operatorInfoUrl = sysParam.getParamValueOne();
		logger.info(">>>>调用白骑士获取运营商原数据请求地址>>bqsRiskUrl:" + operatorInfoUrl);
		String bqsPartnerId = sysParam.getParamValueTwo();
		String bqsVerifyKey = sysParam.getParamValueThree();
		String applicationId = application.getApplicationId();
		/** 判断证件类型**/ 
		 if (!"1".equals(application.getCertType())) {
			/** 不是身份证,报错**/
			 throw new RuntimeException("证件类型异常,请输入身份证类型:"+application.getCertType());
		}
		Assert.hasText(application.getApplicationId(),"ApplicationId参数为空");
		Assert.hasText(application.getName(),"Name参数为空");
		Assert.hasText(application.getPhoneNum(),"PhoneNum参数为空");
		Assert.hasText(application.getCertCode(),"CertCode参数为空");
		Map<String, String> requestMap = new HashMap<>();
		requestMap.put("name", application.getName());
		requestMap.put("mobile", application.getPhoneNum());
		requestMap.put("certNo", application.getCertCode());
		requestMap.put("partnerId", bqsPartnerId);
		requestMap.put("verifyKey", bqsVerifyKey);
		String jsonStr = JSON.toJSONString(requestMap);
		logger.info(">>>>调用白骑士获取运营商原数据参数>>jsonStr:" + jsonStr);
		try {
			String content = RiskHttpClientUtil.doPostJson(operatorInfoUrl, jsonStr);
			if(StringUtils.isBlank(content)){
        		throw new RuntimeException("白骑士获取运营商原数据为空");
        	}
			BaiQiShiBean rootBean = (BaiQiShiBean) JacksonUtils.JsonToBean(content, BaiQiShiBean.class);
			if (!"CCOM1000".equals(rootBean.getResultCode())) {
				logger.error("白骑士获取运营商原数据ResultCode有误, 返回结果:result:" + content);
				throw new RuntimeException("白骑士获取运营商原数据ResultCode有误" + rootBean.getResultDesc());
			}
			if (rootBean.getData() != null) {
				String personalInfoId = UUIDUtils.getUUID();
				MnoDetail mnoDetail = rootBean.getData().getMnoDetail();
				MnoPersonalInfo mnoPersonalInfo = mnoDetail.getMnoPersonalInfo();
				if (mnoPersonalInfo != null) {
					//保存用户主表信息
					boolean flag = savePersonalInfo(applicationId, personalInfoId, mnoPersonalInfo);
					if (!flag) {
						throw new RuntimeException("保存白骑士运营商数据用户信息有误");
					}
					// 是否有通话记录
					List<MnoCallRecords> mnoCallRecords = mnoDetail.getMnoCallRecords();
					logger.info("保存用户其他信息>>>personalInfoId：" + personalInfoId);
					saveCallsInfo(personalInfoId, mnoCallRecords);
					
					// 是否有短信记录
					List<MnoSmsRecords> mnoSmsRecords = mnoDetail.getMnoSmsRecords();
					saveSmsInfo(personalInfoId, mnoSmsRecords);
					
					// 是否有账单记录
					List<MnoBillRecords> mnoBillRecords = mnoDetail.getMnoBillRecords();
					saveBillInfo(personalInfoId, mnoBillRecords);
					
					// 是否有支付记录
					List<MnoPaymentRecords> mnoPaymentRecords = mnoDetail.getMnoPaymentRecords();
					savePayment(personalInfoId, mnoPaymentRecords);
					
					// 是否有上网记录
					List<MnoNetPlayRecords> mnoNetPlayRecords = mnoDetail.getMnoNetPlayRecords();
					saveNetPlay(personalInfoId, mnoNetPlayRecords);
					
					// 是否有流量记录
					List<MnoForwardRecords> mnoForwardRecords = mnoDetail.getMnoForwardRecords();
					saveForward(personalInfoId, mnoForwardRecords);
					
				}
			}
			//存阿里云 mongodb
            jsonReportService.uploadJson(Constants.BQS_INFO_UPLOAD_DIR, JSONObject.fromObject(content), TaskTypeConstants.BQS_INFO_TASK_TYPE, TaskTypeConstants.BQS_INFO_TASK_NAME, TaskTypeConstants.BQS_INFO_TASK_ISEND, application.getApplicationId(), "noext");
			
		} catch (Exception e) {
			throw new RuntimeException("保存白骑士运营商数据用户信息有误"+e.getMessage(), e);
		}
		logger.info("骑士运营商数据,处理成功");
		return ruleResult;
	}
	

	/**
	 * 流量记录
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void saveForward(String personalInfoId, List<MnoForwardRecords> mnoForwardRecords){
		try {
			for (MnoForwardRecords mnoForwardRecord : mnoForwardRecords) {
				BqsForwardInfo forward = new BqsForwardInfo();
				BeanUtils.copyProperties(mnoForwardRecord, forward);
				forward.setBqsPersonalInfoId(personalInfoId);
				forward.setBqsForwardInfoId(UUIDUtils.getUUID());
				forward.setCreateTime(DateUtils.getDate(new Date()));
				bqsForwardInfoServiceDao.saveEntity(forward);
			}
		} catch (Exception e) {
			logger.info("保存流量记录异常" + e.getMessage(), e);
			throw new RuntimeException("保存流量记录异常"+e.getMessage(), e);
		}
	}

	/**
	 * 网络对战信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void saveNetPlay(String personalInfoId, List<MnoNetPlayRecords> mnoNetPlayRecords){
		try {
			for (MnoNetPlayRecords mnoNetPlayRecord : mnoNetPlayRecords) {
				BqsNetplayInfo netPlay = new BqsNetplayInfo();
				BeanUtils.copyProperties(mnoNetPlayRecord, netPlay);
				netPlay.setBqsPersonalInfoId(personalInfoId);
				netPlay.setBqsNetplayInfoId(UUIDUtils.getUUID());
				netPlay.setCreateTime(DateUtils.getDate(new Date()));
				bqsNetplayInfoServiceDao.saveEntity(netPlay);
			}
		} catch (Exception e) {
			logger.info("保存上网记录异常" + e.getMessage());
			throw new RuntimeException("保存上网记录异常"+e.getMessage(), e);
		}
	}

	/**
	 * 保存运营商支付信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void savePayment(String personalInfoId, List<MnoPaymentRecords> mnoPaymentRecords){
		try {
			for (MnoPaymentRecords mnoPaymentRecord : mnoPaymentRecords) {
				BqsPaymentsInfo paymentInfo = new BqsPaymentsInfo();
				BeanUtils.copyProperties(mnoPaymentRecord, paymentInfo);
				paymentInfo.setBqsPersonalInfoId(personalInfoId);
				paymentInfo.setBqsPaymentsInfoId(UUIDUtils.getUUID());
				paymentInfo.setCreateTime(DateUtils.getDate(new Date()));
				bqsPaymentsInfoServiceDao.saveEntity(paymentInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存支付记录异常" + e.getMessage());
			throw new RuntimeException("保存支付记录异常"+e.getMessage());
		}
	}

	/**
	 * 保存账单信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void saveBillInfo(String personalInfoId, List<MnoBillRecords> mnoBillRecords){
		try {
			for (MnoBillRecords mnoBillRecord : mnoBillRecords) {
				BqsBillsInfo billInfo = new BqsBillsInfo();
				BeanUtils.copyProperties(mnoBillRecord, billInfo);
				String billId = UUIDUtils.getUUID();
				billInfo.setBqsBillsInfoId(billId);
				billInfo.setBqsPersonalInfoId(personalInfoId);
				billInfo.setCreateTime(DateUtils.getDate(new Date()));
				int count = bqsBillsInfoServiceDao.saveEntity(billInfo);
				if (count > 0) {
					List<MnoBillRecordDetails> mnoBillRecordDetails = mnoBillRecord.getMnoBillRecordDetails();
					saveBillDetail(billId, mnoBillRecordDetails);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存账单记录异常" + e.getMessage());
			throw new RuntimeException("保存账单记录异常"+e.getMessage());
		}

	}

	/**
	 * 保存账单详情信息
	 * 
	 * @param billId
	 * @param
	 * @throws Exception
	 */
	private void saveBillDetail(String billId, List<MnoBillRecordDetails> mnoBillRecordDetails){
		try {
			for (MnoBillRecordDetails mnoBillRecordDetail : mnoBillRecordDetails) {
				BqsBillDetails billDetail = new BqsBillDetails();
				BeanUtils.copyProperties(mnoBillRecordDetail, billDetail);
				billDetail.setBqsBillDetailsId(UUIDUtils.getUUID());
				billDetail.setBqsBillsInfoId(billId);
				billDetail.setCreateTime(DateUtils.getDate(new Date()));
				bqsBillDetailsServiceDao.saveEntity(billDetail);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存账单详情异常"+e.getMessage());
		}
	}

	/**
	 * 保存短信记录信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void saveSmsInfo(String personalInfoId, List<MnoSmsRecords> mnoSmsRecords){
		try {
			for (MnoSmsRecords mnoSmsRecord : mnoSmsRecords) {
				BqsSmsInfo smsInfo = new BqsSmsInfo();
				BeanUtils.copyProperties(mnoSmsRecord, smsInfo);
				smsInfo.setBqsSmsInfoId(UUIDUtils.getUUID());
				smsInfo.setBqsPersonalInfoId(personalInfoId);
				smsInfo.setCreateTime(DateUtils.getDate(new Date()));
				bqsSmsInfoServiceDao.saveEntity(smsInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("保存短信记录异常" + e.getMessage());
			throw new RuntimeException("保存短信记录异常"+e.getMessage());
		}
	}

	/**
	 * 保存通话信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @throws Exception
	 */
	private void saveCallsInfo(String personalInfoId, List<MnoCallRecords> mnoCallRecords){
		try {
			for (MnoCallRecords mnoCallRecord : mnoCallRecords) {
				BqsCallsInfo bqsCallsInfo = new BqsCallsInfo();
				BeanUtils.copyProperties(mnoCallRecord, bqsCallsInfo);
				bqsCallsInfo.setBqsCallsInfoId(UUIDUtils.getUUID());
				bqsCallsInfo.setBqsPersonalInfoId(personalInfoId);
				bqsCallsInfo.setCreateTime(DateUtils.getDate(new Date()));
				bqsCallsInfoServiceDao.saveEntity(bqsCallsInfo);
			}
		} catch (Exception e) {
			logger.info("保存通话记录异常"+e.getMessage());
			throw new RuntimeException("保存通话记录异常"+e.getMessage(), e);
		}
	}

	/**
	 * 保存用户主表信息
	 * 
	 * @param personalInfoId
	 * @param
	 * @return
	 * @throws Exception
	 */
	private boolean savePersonalInfo(String applicationId, String personalInfoId, MnoPersonalInfo mnoPersonalInfo){
		BqsPersonalInfo bqsPersonalInfo = new BqsPersonalInfo();
		BeanUtils.copyProperties(mnoPersonalInfo, bqsPersonalInfo);
		bqsPersonalInfo.setBuApplicationId(applicationId);
		bqsPersonalInfo.setBqsPersonalInfoId(personalInfoId);
		bqsPersonalInfo.setCreateTime(DateUtils.getDate(new Date()));
		bqsPersonalInfo.setModifyTime(DateUtils.getDate(new Date()));
		return bqsPersonalInfoServiceDao.saveEntity(bqsPersonalInfo) > 0;
	}

}
