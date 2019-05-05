package com.shangyong.backend.controller;

import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ZhiMaIndustryDetails;
import com.shangyong.backend.entity.ZhiMaIndustryDetailsInfo;
import com.shangyong.backend.service.ZhiMaIndustryDetailsListService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/zhimaInfo/")
public class ZhiMaScoreController {
	private static Logger logger = Logger.getLogger(ZhiMaScoreController.class);
	@Autowired
	private ZhiMaIndustryDetailsListService zhiMaIndustryDetailsListService;
	@Autowired
	private ApplicationServiceImpl applicationServiceImpl;
	
	
	/**
	 * mysql获取芝麻风险报告
	 * 
	 * @param request
	 * @param response
	 * @param applicationId
	 */
	@RequestMapping(value = "getZhiMaMysqlInfoByApplicationId.do", method = RequestMethod.POST)
	public void ZhiMaInfoByApplicationId(HttpServletRequest request, HttpServletResponse response, String applicationId) {
		try {
			if(StringUtils.isBlank(applicationId)){
					JSONUtils.toJSON(response, CodeUtils.APPLICATION_ID_NULL);
				return;
			}
			ZhiMaIndustryDetailsInfo zhiMaIndustryDetailsInfo = new ZhiMaIndustryDetailsInfo();
			//查询出Application里的芝麻分
			Application application = applicationServiceImpl.getEntityByApplicationId(applicationId);
			if(application != null){
				zhiMaIndustryDetailsInfo.setApplication(application);
			}
			
			//查询行业清单
			List<ZhiMaIndustryDetails> zhiMaIndustryDetailsList = zhiMaIndustryDetailsListService.getEntityById(applicationId);
			if(!zhiMaIndustryDetailsList.isEmpty() && zhiMaIndustryDetailsList.size() > 0){
				for (ZhiMaIndustryDetails zhiMaIndustryDetails : zhiMaIndustryDetailsList) {
					BeanUtils.copyProperties(zhiMaIndustryDetails, zhiMaIndustryDetailsInfo);
				}
			}
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, zhiMaIndustryDetailsInfo, "zhiMaIndustryDetailsInfo");
		} catch (Exception e) {
			logger.error("ZhiMaInfoByApplicationId exception[applicationId="+applicationId, e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
