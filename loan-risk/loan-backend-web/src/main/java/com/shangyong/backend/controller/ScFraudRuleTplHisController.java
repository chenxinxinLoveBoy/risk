package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScFraudRuleTplHis;
import com.shangyong.backend.service.impl.ScFraudRuleTplHisServiceImpl;
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
@RequestMapping(value = "/backend/scFraudRuleTplHis")
public class ScFraudRuleTplHisController {

	@Autowired
	private ScFraudRuleTplHisServiceImpl scFraudRuleTplHisService;

	private static Logger logger = Logger.getLogger(ScFraudRuleTplHisController.class);

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scFraudRuleTplHisListAll.do", method = RequestMethod.POST)
	public void scFraudRuleTplHisListAll(HttpServletRequest request, HttpServletResponse response,
                                         ScFraudRuleTplHis scFraudRuleTplHis) {
		try {
			int count = scFraudRuleTplHisService.listAllCount(scFraudRuleTplHis);
			List<ScFraudRuleTplHis> list = scFraudRuleTplHisService.findAll(scFraudRuleTplHis);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
