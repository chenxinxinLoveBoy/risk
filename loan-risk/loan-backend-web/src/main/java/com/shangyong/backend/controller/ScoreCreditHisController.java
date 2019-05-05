package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScScoreCredit;
import com.shangyong.backend.service.ScoreCreditServiceHis;
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
@RequestMapping("/backend/scoreCreditHis/")
public class ScoreCreditHisController {

	private static Logger logger = Logger.getLogger(ScoreCreditHisController.class);

	@Autowired
	private ScoreCreditServiceHis scoreCreditServiceHis;

	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listAll.do", method = RequestMethod.POST)
	public void listAllScoreCreditHis(HttpServletRequest request, HttpServletResponse response, ScScoreCredit scoreCredit) {
		try {
			int count = scoreCreditServiceHis.listAllCount(scoreCredit);// 统计总条数
			List<ScScoreCredit> list = scoreCreditServiceHis.listAll(scoreCredit);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
