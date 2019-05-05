package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScFraudRuleBigHis;
import com.shangyong.backend.service.impl.ScFraudRuleBigHisServiceImpl;
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
@RequestMapping("/backend/scFraudRuleBigHis/")
public class ScFraudRuleBigHisController extends BaseController {
	private static Logger logger = Logger.getLogger(ScFraudRuleBigHisController.class);
	@Autowired
	private ScFraudRuleBigHisServiceImpl scFraudRuleBigHisService;

	/**
	 * 配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScFraudRuleBigHis.do", method = RequestMethod.POST)
	public void getAllScFraudRuleBigHis(HttpServletRequest request, HttpServletResponse response,
                                        ScFraudRuleBigHis scFraudRuleBigHis) {
		try {
			int count = scFraudRuleBigHisService.listAllCount(scFraudRuleBigHis);// 统计总条数
			List<ScFraudRuleBigHis> list = scFraudRuleBigHisService.findAll(scFraudRuleBigHis);
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
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response,
                              ScFraudRuleBigHis scFraudRuleBigHis) {
		try {
			ScFraudRuleBigHis scFraudRuleBigHisBo = scFraudRuleBigHisService.getEntityById(scFraudRuleBigHis.getFraudRuleBigHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scFraudRuleBigHisBo, "scFraudRuleBigHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
