package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.service.impl.ScBanControlHisServiceImpl;
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
@RequestMapping("/backend/scBanControlHis/")
public class ScBanControlHisController extends BaseController {
	private static Logger logger = Logger.getLogger(ScBanControlHisController.class);
	@Autowired
	private ScBanControlHisServiceImpl scBanControlHisService;

	/**
	 * 历史规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScBanControlHis.do", method = RequestMethod.POST)
	public void getAllScBanControlHis(HttpServletRequest request, HttpServletResponse response,
                                      ScBanControl scBanControl) {
		try {
			int count = scBanControlHisService.listAllCount(scBanControl);// 统计总条数
			List<ScBanControl> list = scBanControlHisService.findAll(scBanControl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据主键获取对象信息
	 * 
	 * @param request
	 * @param response
	 * @param scBanControl
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScBanControl scBanControl) {
		try {
			ScBanControl scBanControlHisBo = scBanControlHisService.getEntityById(scBanControl.getControlHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scBanControlHisBo, "scBanControlHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
