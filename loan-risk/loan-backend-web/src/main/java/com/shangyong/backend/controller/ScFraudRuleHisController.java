package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScFraudRuleHis;
import com.shangyong.backend.service.impl.ScFraudRuleHisServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/scFraudRuleHis/")
public class ScFraudRuleHisController {

	private static Logger logger = Logger.getLogger(ScFraudRuleHisController.class);
	
	@Autowired
	private ScFraudRuleHisServiceImpl scFraudRuleHisService;
	
	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScFraudRuleHis.do", method = RequestMethod.POST)
	public void getAllScFraudRule(HttpServletRequest request, HttpServletResponse response,
                                  ScFraudRuleHis scFraudRuleHis) {
		try {
			int count = scFraudRuleHisService.listAllCount(scFraudRuleHis);// 统计总条数
			List<ScFraudRuleHis> list = scFraudRuleHisService.findAll(scFraudRuleHis);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据id获取对象信息
	 * 
	 * @param request
	 * @param response
	 * @param scFraudRuleHis
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScFraudRuleHis scFraudRuleHis) {
		try {
			ScFraudRuleHis scFraudRuleHisBo = scFraudRuleHisService.getEntityById(scFraudRuleHis.getFraudRuleHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scFraudRuleHisBo, "scFraudRuleHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
