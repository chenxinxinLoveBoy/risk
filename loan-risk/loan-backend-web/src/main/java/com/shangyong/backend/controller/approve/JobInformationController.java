package com.shangyong.backend.controller.approve;

import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.service.approval.service.CustomerCompanyService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/backend/approve/")
public class JobInformationController {

	private static Logger log = LoggerFactory.getLogger(JobInformationController.class);
	
	@Autowired
	private CustomerCompanyService customerCompanyService;
	
	/**通过平台用户编号查询客户所属公司信息**/
	@RequestMapping(value = "jobInformation")
	public void jobInformation(String platformId, HttpServletRequest request, HttpServletResponse response) {
		log.info("JobInformationController.jobInformation-->>【客户所属公司信息】查询开始,platformId=" + platformId);
		try {
			if (StringUtils.isNotBlank(platformId)) {
				CuCustomerCompany cuCustomerCompany = customerCompanyService.findCuCustomerCompanyByPlatformCustomerId(platformId);
				log.info("JobInformationController.jobInformation-->>【客户所属公司信息】查询结束,platformId=" + platformId);
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, cuCustomerCompany);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
			}
		} catch (Exception e) {
			log.error("【客户所属公司信息】异常，运行时异常：" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
