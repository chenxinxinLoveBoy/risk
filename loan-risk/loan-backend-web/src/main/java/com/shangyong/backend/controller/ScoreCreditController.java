package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScScoreCredit;
import com.shangyong.backend.service.impl.ScoreCreditServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/scoreCredit/")
public class ScoreCreditController {

	private static Logger logger = Logger.getLogger(ScoreCreditController.class);

	@Autowired
	private ScoreCreditServiceImpl scoreCreditService;

	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "listAll.do", method = RequestMethod.POST)
	public void getAllScoreCredit(HttpServletRequest request, HttpServletResponse response, ScScoreCredit scoreCredit) {
		try {
			int count = scoreCreditService.listAllCount(scoreCredit);// 统计总条数
			List<ScScoreCredit> list = scoreCreditService.findAll(scoreCredit);
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
	 * @param scoreCredit
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreCredit scoreCredit) {
		try {
			ScScoreCredit scoreCreditBo = scoreCreditService.getEntityById(scoreCredit.getScoreCreditId().toString());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scoreCreditBo, "scoreCreditObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 新增/修改
	 * 
	 * @param request
	 * @param response
	 * @param scoreCredit
	 */
	@RequestMapping(value = "saveScoreCredit.do")
	public void saveScoreCredit(HttpServletRequest request, HttpServletResponse response, ScScoreCredit scoreCredit) {
		try {
			if (scoreCredit != null && scoreCredit.getScoreCreditId() != null
					&& !"".equals(scoreCredit.getScoreCreditId())) {// 修改
				List<ScScoreCredit> scoreCreditList = scoreCreditService.queryByScore(scoreCredit);
				if (CollectionUtils.isEmpty(scoreCreditList) || (scoreCreditList.size() == 1
						&& scoreCreditList.get(0).getScoreCreditId() == scoreCredit.getScoreCreditId())) {
					boolean flag = scoreCreditService.updateEntity(scoreCredit);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						return;
					}
				} else {// 该参数记录已存在，直接返回
					JSONUtils.toJSON(response, CodeUtils.SCORE_PARAM_FAIL);
					return;
				}
			} else {// 新增
				List<ScScoreCredit> scoreCreditList = scoreCreditService.queryByScore(scoreCredit);
				if (CollectionUtils.isEmpty(scoreCreditList)) {// 该参数记录不存在，执行新增操作
					boolean flag = scoreCreditService.saveEntity(scoreCredit);
					if (flag) {// 成功
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {// 失败
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				} else {// 该参数记录已存在，直接返回
					JSONUtils.toJSON(response, CodeUtils.SCORE_PARAM_FAIL);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
