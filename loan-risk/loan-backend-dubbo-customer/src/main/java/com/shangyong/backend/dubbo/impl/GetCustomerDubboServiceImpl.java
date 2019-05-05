package com.shangyong.backend.dubbo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dubbo.GetCustomerDubboService;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;
import com.shangyong.backend.service.impl.CuCustomerCompanyServiceImpl;
import com.shangyong.backend.service.impl.CuIcePersonServiceImpl;
import com.shangyong.backend.service.impl.CuPlatformCustomerServiceImpl;
import com.shangyong.backend.service.impl.FaceRecognitionScoreServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.DingdingUtil;

@Service(version = "1.0.0")
public class GetCustomerDubboServiceImpl implements GetCustomerDubboService {
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("getCustomerDubboServiceImpl");
	@Autowired
	private CuPlatformCustomerServiceImpl cuPlatformCustomerService;
	@Autowired
	private CuCustomerCompanyServiceImpl cuCustomerCompanyService;
	@Autowired
	private CuIcePersonServiceImpl cuIcePersonService;
	@Autowired
	private FaceRecognitionScoreServiceImpl faceRecognitionScoreService;
	/**
	 * 通过客户平台编号获取平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录
	 */
	@Override
	public CustomerInfo getCustomer(CuPlatformCustomer cuPlatformCustomer) {
		CustomerInfo customerInfo = null;
		String platformCustomerId = cuPlatformCustomer.getPlatformCustomerId();
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 潘多拉系统调取【平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录】服务开始---传入平台客户编号：" + platformCustomerId);
		try {
			if (StringUtils.isNotEmpty(platformCustomerId)) {
				customerInfo = new CustomerInfo();
				customerInfo.setCuPlatformCustomer(cuPlatformCustomerService.getEntityById(cuPlatformCustomer));
				customerInfo.setCuIcePersonList(cuIcePersonService.getCuIcePersonList(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setCuCustomerCompany(cuCustomerCompanyService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setFaceRecognitionScore(faceRecognitionScoreService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
			} else {
				logger.info("传入的平台客户编号不能为空,返回null");
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
					+ ", 传入平台客户编号：" + platformCustomerId + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
		}
		return customerInfo;
	}

	/**
	 * 通过客户平台编号获取平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录
	 */
	@Override
	public CustomerInfo getCustomerByCustomerIdAndAppName(CuPlatformCustomer cuPlatformCustomer) {
		CustomerInfo customerInfo = null;
		String customerId = cuPlatformCustomer.getCustomerId();
		String appName = cuPlatformCustomer.getAppName();
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 潘多拉系统调取【平台客户信息、客户紧急联系人信息、客户公司信息、客户人脸识别评分记录】服务开始---传入客户编号：" + customerId + ",平台名称：" + appName);
		try {
			if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotEmpty(appName)) {
				customerInfo = new CustomerInfo();
				cuPlatformCustomer = cuPlatformCustomerService.getEntityById(cuPlatformCustomer);
				customerInfo.setCuPlatformCustomer(cuPlatformCustomer);
				customerInfo.setCuIcePersonList(
						cuIcePersonService.getCuIcePersonList(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setCuCustomerCompany(
						cuCustomerCompanyService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
				customerInfo.setFaceRecognitionScore(faceRecognitionScoreService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));
			} else {
				logger.info("传入的客户编号和平台名称不能为空,返回null");
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
					+ ", 传入客户编号：" + customerId + ",平台名称：" + appName + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
			throw new RuntimeException("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 传入客户编号：" + customerId + ",平台名称：" + appName + "; 错误信息：" + e.getMessage());
		}
		return customerInfo;
	}
	/**
	 * 获取平台客户信息列表
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getCustomerList(CuPlatformCustomer cuPlatformCustomer) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 潘多拉系统调取【平台客户信息列表】服务开始！");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int count = cuPlatformCustomerService.listAllCount(cuPlatformCustomer);// 统计总条数
			if (count != 0) {
				List<CuPlatformCustomer> list = cuPlatformCustomerService.findAll(cuPlatformCustomer);
				map.put("count", count);
				map.put("list", list);
			} else {
				map.put("count", 0);
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE,
					"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 获取平台客户信息
	 */
	@Override
	public CuPlatformCustomer getCustomerInfo(CuPlatformCustomer cuPlatformCustomer) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 获取【平台客户信息】服务开始---传入平台客户编号："
				+ cuPlatformCustomer.getPlatformCustomerId() + ",传入app客户编号：" + cuPlatformCustomer.getCustomerId()
				+ ",传入平台类型(1:闪贷;2:速贷)：" + cuPlatformCustomer.getAppName());
		try {
			cuPlatformCustomer = cuPlatformCustomerService.getEntityById(cuPlatformCustomer);
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE,
					"当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 传入平台客户编号："
							+ cuPlatformCustomer.getPlatformCustomerId() + ",传入app客户编号："
							+ cuPlatformCustomer.getCustomerId() + ",传入平台类型(1:闪贷;2:速贷)："
							+ cuPlatformCustomer.getAppName() + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
		}
		return cuPlatformCustomer;
	}

	/**
	 * 通过客户平台编号获取客户紧急联系人信息
	 * 
	 */
	@Override
	public List<CuIcePerson> getCustomerCuIcePersonList(String platformCustomerId) {
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 获取【平台客户紧急联系人信息】服务开始---传入平台客户编号："
				+ platformCustomerId);
		List<CuIcePerson> list = new ArrayList<CuIcePerson>();
		try {
			if (StringUtils.isNotEmpty(platformCustomerId)) {
				list = cuIcePersonService.getCuIcePersonList(platformCustomerId);
			} else {
				logger.info("传入的平台客户编号不能为空,返回null");
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
					+ ", 传入平台客户编号：" + platformCustomerId + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
		}
		return list;
	}

	/**
	 * 通过客户编号和平台类型获取平台客户信息、客户公司信息
	 */
	@Override
	public CustomerInfo getCustomerInfoAndCompanyByCustomerIdAndAppName(CuPlatformCustomer cuPlatformCustomer) {
		CustomerInfo customerInfo = null;
		String customerId = cuPlatformCustomer.getCustomerId();
		String appName = cuPlatformCustomer.getAppName();
		logger.info("当前时间：" + DateUtils.parseToDateTimeStr(new Date())
				+ ", 获取取【平台客户信息、客户公司信息】服务开始---传入客户编号：" + customerId + ",平台名称：" + appName);
		try {
			if (StringUtils.isNotEmpty(customerId) && StringUtils.isNotEmpty(appName)) {
				customerInfo = new CustomerInfo();
				cuPlatformCustomer = cuPlatformCustomerService.getEntityById(cuPlatformCustomer);//平台客户信息表cu_platform_customer
				customerInfo.setCuPlatformCustomer(cuPlatformCustomer);
 				customerInfo.setCuCustomerCompany(
				cuCustomerCompanyService.getEntityById(cuPlatformCustomer.getPlatformCustomerId()));//平台客户所属公司信息cu_customer_company
 			} else {
				logger.info("传入的客户编号和平台名称不能为空,返回null");
			}
		} catch (Exception e) {
			DingdingUtil.setMessage(Constants.DUBBO_DD_CUS_URL_CODE, "当前时间：" + DateUtils.parseToDateTimeStr(new Date())
					+ ", 传入客户编号：" + customerId + ",平台名称：" + appName + "; 错误信息：" + e.getMessage());
			logger.error(e.getMessage());
			throw new RuntimeException("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 传入客户编号：" + customerId + ",平台名称：" + appName + "; 错误信息：" + e.getMessage());
		}
		return customerInfo;
	}

}
