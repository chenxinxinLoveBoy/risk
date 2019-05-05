package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScScoreTplHis;
import com.shangyong.backend.service.impl.ScScoreTplHisServiceImpl;
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
@RequestMapping(value = "/backend/scScoreTplHis")
public class ScScoreTplHisController {

	@Autowired
	private ScScoreTplHisServiceImpl scScoreTplHisService;

	private static Logger logger = Logger.getLogger(ScScoreTplHisController.class);

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scScoreTplHisListAll.do", method = RequestMethod.POST)
	public void scScoreTplHisListAll(HttpServletRequest request, HttpServletResponse response,
                                     ScScoreTplHis scScoreTplHis) {
		try {
			int count = scScoreTplHisService.listAllCount(scScoreTplHis);
			List<ScScoreTplHis> list = scScoreTplHisService.findAll(scScoreTplHis);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
