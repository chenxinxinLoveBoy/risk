package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScLiftConfigurationHis;
import com.shangyong.backend.service.impl.ScLiftConfigurationHisServiceImpl;
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
@RequestMapping(value = "/backend/scLiftConfigurationHis")
public class ScLiftConfigurationHisController {

	@Autowired
	private ScLiftConfigurationHisServiceImpl scLiftConfigurationHisService;

	private static Logger logger = Logger.getLogger(ScLiftConfigurationHisController.class);

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scLiftConfigurationHisListAll.do", method = RequestMethod.POST)
	public void scLiftConfigurationHisListAll(HttpServletRequest request, HttpServletResponse response,
                                              ScLiftConfigurationHis scLiftConfigurationHis) {
		try {
			int count = scLiftConfigurationHisService.listAllCount(scLiftConfigurationHis);
			List<ScLiftConfigurationHis> list = scLiftConfigurationHisService.findAll(scLiftConfigurationHis);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
