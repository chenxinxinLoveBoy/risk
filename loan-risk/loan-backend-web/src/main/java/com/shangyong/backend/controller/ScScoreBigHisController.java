package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.service.impl.ScScoreBigHisServiceImpl;
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
@RequestMapping("/backend/scScoreBigHis/")
public class ScScoreBigHisController extends BaseController {
	private static Logger logger = Logger.getLogger(ScScoreBigHisController.class);
	@Autowired
	private ScScoreBigHisServiceImpl scScoreBigHisService;

	/**
	 * 历史评分大类配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScScoreBigHis.do", method = RequestMethod.POST)
	public void getAllScScoreBigHis(HttpServletRequest request, HttpServletResponse response, ScScoreBig scScoreBig) {
		try {
			int count = scScoreBigHisService.listAllCount(scScoreBig);// 统计总条数
			List<ScScoreBig> list = scScoreBigHisService.findAll(scScoreBig);
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
	 * @param scScoreBig
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreBig scScoreBig) {
		try {
			ScScoreBig scScoreBigHisBo = scScoreBigHisService.getEntityById(scScoreBig.getBigHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scScoreBigHisBo, "scScoreBigHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
