package com.shangyong.backend.dubbo.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dubbo.CuPlatformCustomerDubboService;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;
import com.shangyong.backend.service.impl.CuCustomerCompanyServiceImpl;
import com.shangyong.backend.service.impl.CuIcePersonServiceImpl;
import com.shangyong.backend.service.impl.CuPlatformCustomerServiceImpl;
import com.shangyong.backend.service.impl.FaceRecognitionScoreServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.DingdingUtil;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

/**
 * 催收系统获取客户信息dubbo服务
 * 
 * @author xk
 *
 */
@Service(version = "1.0.0")
public class CuPlatformCustomerDubboServiceImpl implements CuPlatformCustomerDubboService {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("CuPlatformCustomerDubboServiceImpl");
	@Autowired
	private CuPlatformCustomerServiceImpl cuPlatformCustomerService;
	@Autowired
	private CuCustomerCompanyServiceImpl cuCustomerCompanyService;
	@Autowired
	private CuIcePersonServiceImpl cuIcePersonService;
	@Autowired
	private FaceRecognitionScoreServiceImpl faceRecognitionScoreService;

	@Override
	public CustomerInfo getEntity(Map<String, String> map) {
		CustomerInfo customerInfo = null;
		String customerId = map.get("customerId");
		String appName = map.get("appName");
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 催收系统调取【平台客户信息】服务开始---传入客户编号：" + customerId
				+ ",传入平台类型(1:闪贷;2:速贷)：" + appName);
		try {
			if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotEmpty(appName)) {
				CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
				cuPlatformCustomer.setCustomerId(customerId);
				cuPlatformCustomer.setAppName(appName);
				cuPlatformCustomer = cuPlatformCustomerService.getEntityById(cuPlatformCustomer);

				customerInfo = new CustomerInfo();
				customerInfo.setCuPlatformCustomer(cuPlatformCustomer);
				customerInfo.setCuIcePersonList(
						cuIcePersonService.getCuIcePersonList(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setCuCustomerCompany(
						cuCustomerCompanyService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setFaceRecognitionScore(faceRecognitionScoreService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
			} else {
				logger.info("传入的参数不能为空,返回null");
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
					+ ", 传入客户编号：" + customerId + ",传入平台类型(1:闪贷;2:速贷)：" + appName + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
		}
		return customerInfo;
	}

}
