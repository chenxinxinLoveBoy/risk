package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScDecisionTreeHis;
import com.shangyong.backend.service.impl.ScDecisionTreeHisServiceImpl;
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
@RequestMapping(value = "/backend/scDecisionTreeHis")
public class ScDecisionTreeHisController {

	@Autowired
	private ScDecisionTreeHisServiceImpl scDecisionTreeHisService;

	private static Logger logger = Logger.getLogger(ScDecisionTreeHisController.class);

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scDecisionTreeHisListAll.do", method = RequestMethod.POST)
	public void scDecisionTreeHisListAll(HttpServletRequest request, HttpServletResponse response,
                                         ScDecisionTreeHis scDecisionTreeHis) {
		try {
			int count = scDecisionTreeHisService.listAllCount(scDecisionTreeHis);
			List<ScDecisionTreeHis> list = scDecisionTreeHisService.findAll(scDecisionTreeHis);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
