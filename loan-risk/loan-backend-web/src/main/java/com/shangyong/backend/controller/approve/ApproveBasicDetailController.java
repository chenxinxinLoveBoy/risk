package com.shangyong.backend.controller.approve;

import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.FaceRecognitionScore;
import com.shangyong.backend.entity.approval.Address;
import com.shangyong.backend.entity.approval.EquipmentInformation;
import com.shangyong.backend.entity.bqsrep.BqsRepContactsInfo;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;
import com.shangyong.backend.service.CuPlatformCustomerService;
import com.shangyong.backend.service.approval.service.*;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.IpUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/approve/")
public class ApproveBasicDetailController {
	
	private static Logger log = LoggerFactory.getLogger(ApproveBasicDetailController.class);
	
	@Autowired
	private FaceRecognitionScoreService faceRecognitionScoreService;
	
	@Autowired
	private EquipmentInformationService equipmentInformationService;

	@Autowired
	private PlatformCustomerService platformCustomerService;
	
	@Autowired
	private AddressingService addressingService;
	
	@Autowired
	private CustomerAppService customerService;
	
	@Autowired
	private CuPlatformCustomerService cuPlatformCustomerService;
	
	
//	@Autowired
//	CuCustomerExpandServiceImpl cuCustomerExpandService;
	
//	@Autowired
//	private IcePersonService icePersonService;
	
	/**通过平台用户编号查询基本信息**/
	@RequestMapping(value = "findCustomerBasicInformation.do")
	public void findCustomerBasicInformation(String platformId, HttpServletRequest request, HttpServletResponse response) {
		log.info("ApproveBasicDetailController.findCustomerBasicInformation-->>【基本信息】查询开始,platformId=" + platformId);
		try {
			if (StringUtils.isNotBlank(platformId)) {
				CuPlatformCustomer cuPlatformCustomer = platformCustomerService.findPlatformCustomerById(platformId);
				log.info("ApproveBasicDetailController.findCustomerBasicInformation-->>【基本信息】查询结束,platformId=" + platformId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, cuPlatformCustomer);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【基本信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**通过客户编号查询设备信息**/
	@RequestMapping(value = "findEquipmentInformation.do")
	public void findEquipmentInformation(String applicationId, String appName, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isNotBlank(applicationId) && StringUtils.isNotBlank(appName)) {
				EquipmentInformation equipmentInformation = addressingService.queryInfo(applicationId,appName);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, equipmentInformation);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【设备信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**通过客户编号查询客户使用的全部设备信息**/
	@RequestMapping(value = "findEquipmentInformationInfo")
	public void findEquipmentInformationInfo(String customerId, String source, HttpServletRequest request, HttpServletResponse response) {
		log.info("【设备信息详情】查询开始,customerId=" + customerId + ", appName=" + source);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(source)) {
				List<EquipmentInformation> equipmentInformation = equipmentInformationService.findEquipmentInformationInfo(customerId,source);
				log.info("【设备信息详情】查询结束,customerId=" + customerId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, equipmentInformation);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【设备信息详情】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**通过客户编号查询设备关联情况信息**/
	@RequestMapping(value = "findAjInfo")
	public void findAjInfo(String customerId, String appName, HttpServletRequest request, HttpServletResponse response) {
		log.info("【设备关联】查询开始,customerId=" + customerId + ", appName=" + appName);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
				List<EquipmentInformation> equipmentInformation = equipmentInformationService.findAjInfo(customerId,appName);
				log.info("【设备关联】查询结束,customerId=" + customerId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, equipmentInformation);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【设备关联】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**通过平台用户编号查询客户人脸识别评分**/
	@RequestMapping(value = "faceRecognitionScore.do")
	public void faceRecognitionScore(String platformId, HttpServletRequest request, HttpServletResponse response) {
		log.info("【客户人脸识别信息】查询开始,platformId=" + platformId);
		try {
			if (StringUtils.isNotBlank(platformId)) {
				FaceRecognitionScore faceRecognitionScore = faceRecognitionScoreService.findFaceRecognitionScore(platformId);
				log.info("【客户人脸识别信息】查询结束,platformId=" + platformId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, faceRecognitionScore);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【客户人脸识别信息】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**通过公网ip查询ip归属地信息**/
	@RequestMapping(value = "findIPAddress.do", method = RequestMethod.POST)
	public void findIPAddress(String ip, HttpServletRequest request, HttpServletResponse response) {
		log.info("【ip归属地】查询开始,ip归属地=" + ip);
		try {
			if (StringUtils.isNotBlank(ip)) {
				ip = "ip=" + ip; 
				String ipAddress = IpUtils.getAddresses(ip, "utf-8");
				log.info("【ip归属地】查询结束,ip归属地=" + ipAddress);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, ipAddress);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【ip归属地】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

	/**通过客户编号查询客户最新登录时间**/
	@RequestMapping(value = "findLatestLoginTime.do")
	public void findLatestLoginTime(String customerId, HttpServletRequest request, HttpServletResponse response) {
		log.info("【客户最新登录时间】查询开始,customerId=" + customerId);
		try {
			if (StringUtils.isNotBlank(customerId)) {
				Map<String, String> customer = customerService.queryCustomerById(customerId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, customer);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【客户最新登录时间】查询异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	@RequestMapping(value = "queryAjInfo")
	public void queryAjInfo(String customerId, String appName, HttpServletRequest request, HttpServletResponse response) {
		log.info("【设备号查询设备信息】开始,customerId=" + customerId + ", appName=" + appName);
		try {
			if (StringUtils.isNotBlank(customerId) && StringUtils.isNotBlank(appName)) {
				List<EquipmentInformation> info = equipmentInformationService.queryAjInfo(customerId,appName);
				log.info("【设备号查询设备信息】结束,customerId=" + customerId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, info);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【设备号查询设备信息】异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	@RequestMapping(value = "findMgUserMessage.do")
	public void findMgUserMessage(String certCode1, String phone, HttpServletRequest request, HttpServletResponse response){
		log.info("【身份证手机号归属地查询】开始,certCode=" + certCode1 + ", registerPhone=" + phone);
		
		try {
			if (StringUtils.isNotBlank(certCode1) && StringUtils.isNotBlank(phone)) {
				Address info = equipmentInformationService.queryMgUserMessage(certCode1,phone);
//				log.info("【身份证手机号归属地查询】结束,customerId=" + customerId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, info);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【身份证手机号归属地查询】异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	@RequestMapping(value = "findIcePerson.do", method = RequestMethod.POST)
	public void findIcePerson(String applicationId, HttpServletRequest request, HttpServletResponse response){
		log.info("【紧急联系人查询】开始,applicationId=" + applicationId );
		try {
			if (StringUtils.isNotBlank(applicationId) ) {
				List<BqsRepContactsInfo> infos = equipmentInformationService.queryContact(applicationId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, infos);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【身份证手机号归属地查询】异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	@RequestMapping(value = "findPhoneUseTime.do")
	public void findPhoneUseTime(String applicationId, HttpServletRequest request, HttpServletResponse response){
		log.info("【紧急联系人查询】开始,applicationId=" + applicationId );

		if(StringUtils.isBlank(applicationId)){
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			return;
		}

		try {
			List<BqsRepCrossValidation> bqsRepCrossValidationList = equipmentInformationService.queryBqsRepCrossForPhoneNo(applicationId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, bqsRepCrossValidationList);
		} catch (Exception e) {
			log.error("【身份证手机号归属地查询】异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
