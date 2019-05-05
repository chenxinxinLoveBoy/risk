package com.shangyong.backend.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.entity.*;
import com.shangyong.backend.entity.ApplicationBean;
import com.shangyong.backend.entity.ApplicationDubboServiceBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.common.enums.CertTypeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.common.enums.SourceEnum;
import com.shangyong.backend.dubbo.impl.CustomerServiceImpl;
import com.shangyong.backend.entity.redis.fraud1_1.BasicInfoBankType11Redis;
import com.shangyong.backend.entity.redis.fraud1_8.BasicInfoBank18Redis;
import com.shangyong.backend.service.ApplicationCustomerInfoService;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.ZhiMaIndustryDetailsListService;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.UUIDUtils;

@Service
public class ApplicationCustomerInfoServiceImpl implements ApplicationCustomerInfoService {

	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("applicationDubboService");

	@Autowired
	private ApplicationServiceImpl applicationService;
	
	@Autowired
	private BuThirdpartyReportService thirdpartyReportService;
	
	@Autowired
	private ZhiMaIndustryDetailsListService zhiMaIndustryDetailsListService;
	
	@Autowired
	private CustomerServiceImpl customerService;
	
	@Override
	@Transactional
	public void saveApplicationCustomerInfo(ApplicationDubboServiceBean applicationDubboServiceBean) {
		if (applicationDubboServiceBean == null) {
			throw new RuntimeException("传入参数为空");
		}
		
		//借款申请单对象bean
		ApplicationBean applicationBean = applicationDubboServiceBean.getApplicationBean();
		// 检测该申请单是否已存在
		Map<String,String> param = new HashMap<String,String>();
		param.put("customerId", applicationBean.getCustomerId());
		Application application = applicationService.findByCustomerIdAndAuditingState(param);
		if (application != null) {
//			String createTime = application.getCreateTime();
//			Date date;
//			try {
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				date = simpleDateFormat.parse(createTime);
//			} catch (ParseException e) {
//				throw new RuntimeException("转换日期失败");
//			}
//			long timeInMillis = date.getTime() + (1800 * 1000);
			//就这样吧。
			if(Constants.DAI_SP_STAATE.equals(application.getAuditingState())
				|| Constants.DAIRG_SP_STAATE.equals(application.getAuditingState())
				|| Constants.AGAIN_SP_STAATE.equals(application.getAuditingState())
				|| Constants.MQ_QZF_STATE.equals(application.getAuditingState())){
				logger.error("bu_application表中已存在借款申请单信息，customerId：" + applicationBean.getCustomerId());
				return;
			}
		}
					
		// 客户信息保存 --> 调用平台客户信息dubbo服务进行客户信息保存
		CuPlatformCustomer customerInfo = customerService.save(
				applicationDubboServiceBean.getCuCustomerOtherList(),
				applicationDubboServiceBean.getCuPlatformCustomer(),
				applicationDubboServiceBean.getCuCustomerCompany(),
				applicationDubboServiceBean.getFaceRecognitionScore());

		// App用户借款申请单数据保存
		String applicationId = this.saveApplication(applicationDubboServiceBean, customerInfo);

		// 保存紧急联系人
		customerService.saveCuIcePersonList(applicationId,
				customerInfo.getCustomerId(),
				customerInfo.getPlatformCustomerId(),
				applicationDubboServiceBean.getCuIcePersonList());

		//存入redis
		CuPlatformCustomer cuPlatformCustomer = applicationDubboServiceBean.getCuPlatformCustomer();
		String basicInfoBankTypeKey = RedisConstant.buildFraudScoresKey(applicationId, FraudBizEnum.BASIC_INFO_BANK_TYPE);
		BasicInfoBankType11Redis basicInfoBankTypeRedis = new BasicInfoBankType11Redis();
		basicInfoBankTypeRedis.setBankType(cuPlatformCustomer.getBankName());
		RedisFraudUtils.hmset(basicInfoBankTypeKey, basicInfoBankTypeRedis.toMap());
		
		//1.8版本银行卡号，学历存入redis  
		String basicInfoBankkey = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.BASIC_INFO_BANK_TYPE);
		BasicInfoBank18Redis basicInfoBank18Redis = new BasicInfoBank18Redis();
		basicInfoBank18Redis.setEducationCode(cuPlatformCustomer.getEducationId());
		basicInfoBank18Redis.setBankType(cuPlatformCustomer.getBankName());
		RedisFraudUtils.hmset(basicInfoBankkey, basicInfoBank18Redis.toMap());
	}

	/**
	 * 申请单信息保存
	 * 
	 * @param applicationDubboServiceBean
	 * @return
	 */
	private String saveApplication(ApplicationDubboServiceBean applicationDubboServiceBean, CuPlatformCustomer customerInfo) {
		if (applicationDubboServiceBean == null || applicationDubboServiceBean.getApplicationBean() == null) {
			logger.error("传入的申请单对象不能为空");
			throw new RuntimeException("传入的申请单对象不能为空");
		}

		//借款申请单对象bean
		ApplicationBean applicationBean = applicationDubboServiceBean.getApplicationBean();
		
		if(applicationBean == null){
			throw new RuntimeException("借款申请单ApplicationBean对象为空");
		}
		
		// APP应用请求流水号
		String appSerialNumber = applicationBean.getAppSerialNumber();
		
		//断言判断参数是否正确
		Assert.hasText(appSerialNumber, "APP应用请求流水号不能为空");
		Assert.hasText(applicationBean.getCustomerId(), "APP应用客户编号不能为空");
		Assert.hasText(applicationBean.getName(), "客户姓名不能为空");
		Assert.hasText(applicationBean.getCertCode(), "证件号码不能为空");
		Assert.hasText(applicationBean.getPhoneNum(), "客户手机号不能为空");
		Assert.hasText(applicationBean.getLoanIp(), "借款用户公网IP不能为空");
		Assert.hasText(applicationBean.getTdBlackBox(), "同盾black_box不能为空");

		// 检验传入参数app标识是否匹配枚举定义
//		if (!AppNameEnum.contain(applicationBean.getAppName())) {
//			logger.error("申请单对象传入的APP名称AppName不在有效范围");
//			throw new RuntimeException("申请单对象传入的APP名称AppName不在有效范围");
//		}
		// 检验传入参数身份证标识是否匹配枚举定义
		if (!CertTypeEnum.contain(applicationBean.getCertType())) {
			logger.error("申请单对象传入的证件类型CertType不在有效范围");
			throw new RuntimeException("申请单对象传入的证件类型CertType不在有效范围");
		}
		// 检验传入参数终端类型标识是否匹配枚举定义
		if (!SourceEnum.contain(applicationBean.getSource())) {
			logger.error("申请单对象传入的申请来源Source不在有效范围");
			throw new RuntimeException("申请单对象传入的申请来源Source不在有效范围");
		}

		// 订单数据保存
		Application application = new Application();
		BeanUtils.copyProperties(applicationBean, application);

		application.setApplicationId(UUIDUtils.getUUID());
		application.setZhiMaScore(applicationBean.getScore());

		logger.info("Application=" + JSON.toJSONString(application));

		//获取平台客户信息标识(同一个用户在风控表示为同一个主体)
		String platFormId = customerInfo.getPlatformCustomerId();
		application.setPlatformId(platFormId);
		
		//默认借款申请单状态为97（待处理）
		application.setIsStep(Constants.SY_RULE_ID);
		
		//插入借款申请单时间
		application.setAuditingTime(DateUtils.getDate(new Date()));
		
		//保存第三方信息
		saveThirdPartyReport(application);
		
		boolean flag = applicationService.saveEntity(application);
		//保存芝麻行业关注清单数据
		zhiMaIndustryDetailsListService.saveEntity(application.getApplicationId(),
				customerInfo.getCustomerId(),
				applicationDubboServiceBean.getIndustryDetailsList());

		if (!flag) {
			logger.error("申请单数据保存失败，APP应用请求流水号：" + appSerialNumber);
			throw new RuntimeException("申请单数据保存失败，APP应用请求流水号：" + appSerialNumber);
		}
		return application.getApplicationId();
	}

	/**
	 * 第三方报告信息保存
	 * @param application
	 */
	private void saveThirdPartyReport(Application application) {
		BuThirdpartyReport thirdpartyReport = null;
		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		List<BuThirdpartyReport> list = new ArrayList<BuThirdpartyReport>();
		thirdpartyReport = new BuThirdpartyReport();
		thirdpartyReport.setThirdpartyReportId(UUIDUtils.getUUID());
		thirdpartyReport.setBuApplicationId(applicationId);
		thirdpartyReport.setTaskType(TaskTypeConstants.TD_FACILITY_TYPE);
		thirdpartyReport.setTaskId(application.getTdBlackBox());
		list.add(thirdpartyReport);
		
		int count = thirdpartyReportService.saveEntityList(list);
		if (count > 0 ) {
			logger.info("第三方报告信息保存成功， 数据条数：" + count);
		} else {
			throw new RuntimeException("第三方报告信息保存失败，APP应用请求流水号：" + appSerialNumber);
		}
	}
}
