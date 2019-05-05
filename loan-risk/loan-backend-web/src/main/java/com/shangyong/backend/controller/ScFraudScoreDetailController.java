package com.shangyong.backend.controller;

import com.shangyong.backend.dao.ScFraudScoreDetailDao;
import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.entity.ScoreDetailStatistics;
import com.shangyong.backend.service.impl.ScFraudScoreDetailServiceImpl;
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
import java.util.List;

/**
 * 欺诈评分明细Controller
 * @author hc
 *
 */
@Controller
@RequestMapping(value = "/backend/scFraudScoreDetail")
public class ScFraudScoreDetailController {
	
	/**
	 * 欺诈评分明细service实现
	 * @author hc
	 *
	 */
	@Autowired
	private ScFraudScoreDetailServiceImpl scFraudScoreDetailServiceImpl;
	@Autowired
	private ScFraudScoreDetailDao scFraudScoreDetailDao;
	
	private static Logger logger = Logger.getLogger(ScFraudScoreDetailController.class);

	/**
	 * 查询欺诈分明细信息
	 * @param request
	 * @param response
	 * @param scFraudScoreDetail
	 */
	@RequestMapping(value = "/listAllCount.do", method = RequestMethod.POST)
	public void listAllCount(HttpServletRequest request, HttpServletResponse response, String  applicationId,
                             String applicationBuId) {
		try { 
			if (StringUtils.isBlank(applicationId) && StringUtils.isBlank(applicationBuId)) {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			
			} 
			List<ScFraudScoreDetail> ScFraudScoreDetailList = scFraudScoreDetailServiceImpl.findAll(applicationId,applicationBuId);
			logger.info("欺诈评分明细信息:ScFraudScoreDetailList="+ScFraudScoreDetailList.toString());
			JSONUtils.toListJSON(response, ScFraudScoreDetailList, ScFraudScoreDetailList.size());
			} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	} 
	

	/**
	 * 统计欺诈分小表中规则名称及出现次数
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
			int count = scFraudScoreDetailDao.getSmallStatisticsCount(scoreDetailStatistics);// 统计总条数
			List<ScoreDetailStatistics> list = scFraudScoreDetailDao.getSmallStatistics(scoreDetailStatistics);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 统计欺诈分小表中规则名称及出现次数(饼状图) 注意：该饼状图需要的json数据必须是value和name属性的，例如 {value:310,
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
			List<ScoreDetailStatistics> list = scFraudScoreDetailDao.getSmallStatisticsPie(scoreDetailStatistics);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, list, "smallStatisticsPieObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
