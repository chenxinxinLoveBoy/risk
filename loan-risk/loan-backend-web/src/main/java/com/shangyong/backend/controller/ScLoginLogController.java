package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScLoginLog;
import com.shangyong.backend.service.impl.ScLoginLogServiceImpl;
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
@RequestMapping("/backend/scLoginLog/")
public class ScLoginLogController extends BaseController {
	private static Logger logger = Logger.getLogger(ScLoginLogController.class);
	@Autowired
	private ScLoginLogServiceImpl scLoginLogService;

	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScLoginLogById.do", method = RequestMethod.POST)
	public void getAllScLoginLogById(HttpServletRequest request, HttpServletResponse response, ScLoginLog scLoginLog) {
		try {
			int count = scLoginLogService.listAllCount(scLoginLog);// 统计总条数
			List<ScLoginLog> list = scLoginLogService.getAll(scLoginLog);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
