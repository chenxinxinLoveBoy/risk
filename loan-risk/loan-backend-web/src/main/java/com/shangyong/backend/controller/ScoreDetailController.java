package com.shangyong.backend.controller;

import com.shangyong.backend.dao.ScScoreDetailDao;
import com.shangyong.backend.entity.ScScoreDetail;
import com.shangyong.backend.entity.ScoreDetailStatistics;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/scoreDetail")
public class ScoreDetailController {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ScoreDetailController.class);

	@Autowired
	private ScScoreDetailDao scoreDetailDao;

	/**
	 * 查询所有申请单信息
	 * 
	 * @param request
	 * @param response
	 * @param Headline
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response, String applicationId,
                        String applicationBuId) {
		try {
			if (StringUtils.isBlank(applicationId) && StringUtils.isBlank(applicationBuId)) {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			}
			List<ScScoreDetail> list = scoreDetailDao.findAll(applicationId, applicationBuId);
			JSONUtils.toListJSON(response, list, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
		}
	}

	/**
	 * 统计评分大表中规则名称及出现次数
	 * 
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 */
	@RequestMapping(value = "/getBigStatistics.do", method = RequestMethod.POST)
	public void getBigStatistics(HttpServletRequest request, HttpServletResponse response,
                                 ScoreDetailStatistics scoreDetailStatistics) {
		try {
			int count = scoreDetailDao.getBigStatisticsCount(scoreDetailStatistics);// 统计总条数
			List<ScoreDetailStatistics> list = scoreDetailDao.getBigStatistics(scoreDetailStatistics);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 统计评分小表中规则名称及出现次数
	 * 
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 */
	@RequestMapping(value = "/getSamllStatistics.do", method = RequestMethod.POST)
	public void getSamllStatistics(HttpServletRequest request, HttpServletResponse response,
                                   ScoreDetailStatistics scoreDetailStatistics) {
		try {
			int count = scoreDetailDao.getSmallStatisticsCount(scoreDetailStatistics);// 统计总条数
			List<ScoreDetailStatistics> list = scoreDetailDao.getSmallStatistics(scoreDetailStatistics);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 统计评分大表中规则名称及出现次数(柱形图显示)
	 * 
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 */
	@RequestMapping(value = "/getBigStatisticsHistogram.do", method = RequestMethod.POST)
	public void getBigStatisticsHistogram(HttpServletRequest request, HttpServletResponse response,
                                          ScoreDetailStatistics scoreDetailStatistics) {
		try {
			List<ScoreDetailStatistics> list = scoreDetailDao.getBigStatisticsHistogram(scoreDetailStatistics);
			int listSize = list.size();
			String a[] = new String[listSize];
			int b[] = new int[listSize];
			for (int i = 0; i < listSize; i++) {
				a[i] = list.get(i).getName();
				b[i] = list.get(i).getCount();
			}
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("name", a);
			map.put("count", b);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map, "bigStatisticsHistograObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 统计评分小表中规则名称及出现次数(饼状图) 注意：该饼状图需要的json数据必须是value和name属性的，例如 {value:310,
	 * name:'邮件营销'}
	 * 
	 * @param request
	 * @param response
	 * @param beginTime
	 * @param endTime
	 */
	@RequestMapping(value = "/getSmallStatisticsPie.do", method = RequestMethod.POST)
	public void getSmallStatisticsPie(HttpServletRequest request, HttpServletResponse response,
                                      ScoreDetailStatistics scoreDetailStatistics) {
		try {
			List<ScoreDetailStatistics> list = scoreDetailDao.getSmallStatisticsPie(scoreDetailStatistics);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, list, "smallStatisticsPieObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
