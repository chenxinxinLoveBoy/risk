package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.impl.ScScoreSmallHisServiceImpl;
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
@RequestMapping("/backend/scScoreSmallHis/")
public class ScScoreSmallHisController extends BaseController {
	private static Logger logger = Logger.getLogger(ScScoreSmallHisController.class);
	@Autowired
	private ScScoreSmallHisServiceImpl scScoreSmallHisService;

	/**
	 * 历史评分小类配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScScoreSmallHis.do", method = RequestMethod.POST)
	public void getAllScScoreSmallHis(HttpServletRequest request, HttpServletResponse response,
                                      ScScoreSmall scScoreSmall) {
		try {
			int count = scScoreSmallHisService.listAllCount(scScoreSmall);// 统计总条数
			List<ScScoreSmall> list = scScoreSmallHisService.findAll(scScoreSmall);
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
	 * @param scScoreSmall
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreSmall scScoreSmall) {
		try {
			ScScoreSmall scScoreSmallHisBo = scScoreSmallHisService.getEntityById(scScoreSmall.getSmallHisId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scScoreSmallHisBo, "scScoreSmallHisObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
