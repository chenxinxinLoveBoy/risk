package com.shangyong.backend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.ScScoreDetailDao;
import com.shangyong.backend.entity.ScScoreDetail;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.RiskRuleCheckService;
import com.shangyong.backend.service.RiskScoreService;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * 信用评分 - 规则接口
 * @author xiangxianjin
 *
 */
@Service
public class RiskScoreServiceImpl implements RiskScoreService {
	private static Logger logger = LoggerFactory.getLogger("rule");
	
	/**
	 * 评分小类service
	 */
	@Autowired
	private ScScoreSmallRedisServiceImpl scScoreSmallRedisServiceImpl;

	/**
	 * 评分明细
	 */
	@Autowired
	private ScScoreDetailDao scScoreDetailDao;
	
	/**
	 * 比对接口
	 */
	@Autowired
	private RiskRuleCheckService riskRuleCheckServiceImpl;

	/**
	 *  根据 大类编号 计算分数明细，并且入库
	 * @param scoreTplId			信用评分模板编号
	 * @param scoreBigCode		大类编号
	 * @param checkValue			需比对值
	 * @param applicationId		申请单号
	 * @throws Throwable
	 */
	public void queryApplyScoreApi(String scoreTplId, String scoreBigCode, Object checkValue, String applicationId){
		List<ScScoreSmall> smallList = scScoreSmallRedisServiceImpl.queryScScoreSmallByScoreBigIdRedis(Integer.parseInt(scoreTplId), scoreBigCode);

		logger.info("信用评分校验，信用评分模板编号["+scoreTplId+"]，大类规则编号："+scoreBigCode+"，申请单编号："+applicationId+"，检查值："+checkValue);
		
		if(CollectionUtils.isNotEmpty(smallList)){
			for(ScScoreSmall scoreSmall : smallList){
				//规则停用，则继续校验下一个规则
				if(Constants.STATE_FORBIDDEN.equals(scoreSmall.getState())){
					continue;
				}
				/**评分项规则名称**/
				String scoreRuleName = scoreSmall.getScoreRuleName();
				/**评分项规则小类编号**/
				String scoreSmallCode = scoreSmall.getScoreSmallCode();
				/**评分项规则技术比对值**/
				String ruleScoreValue = scoreSmall.getRuleScoreValue();
				/**分值**/
				String score = scoreSmall.getScore();
				/**技术比对值类型（01-数值、02-字符、03-集合）**/
				String ruleComparisonType = scoreSmall.getRuleComparisonType();
				/**技术校验规则 **/
				String validateRule = scoreSmall.getValidateRule();
				/** 权重 **/
				String percent = scoreSmall.getPercent();

				logger.info("---->信用评分规则名称：" + scoreRuleName + "，scoreSmallCode:" + scoreSmallCode + "，validateRule:" + validateRule+ "，checkValue:" + checkValue+ "，applicationId:" + applicationId);

				ScScoreDetail detail = new ScScoreDetail();
				String bigId = scoreSmall.getScoreBigId().toString();
				String samllId = scoreSmall.getScoreSmallId().toString();
				detail.setApplicationId(applicationId);
				detail.setScoreBigId(bigId);
				int isExisit = this.scScoreDetailDao.deleteByAppIdAndBigCode(detail);

				logger.info("评分存在删除：count:"+isExisit+"，applicationId="+applicationId+"，scoreBigId="+bigId+"，scoreSmallId="+samllId+"，scoreRuleName="+scoreRuleName);

				/** 校验规则是否命中，true命中，分数入库**/
				boolean flag =  riskRuleCheckServiceImpl.ruleCheck(ruleComparisonType, validateRule, ruleScoreValue, checkValue);
				
				logger.info("信用评分规则校验结果，是否命中:" + flag + "，分数：" + score + "， 权重：" + percent);

				//命中规则，评分信息入库
				if(flag){
					detail.setScoreDetailId(UUIDUtils.getUUID());
					detail.setApplicationId(applicationId);
					detail.setScoreBigId(bigId);
					detail.setScoreSmallId(samllId);
					detail.setScoreRuleName(scoreRuleName);
					detail.setScore(calculateScore(score, percent));
					detail.setRemark(scoreSmall.getScoreSmallCode());
					detail.setCreateTime(DateUtils.parseToDateTimeStr(new Date()));
					int count = scScoreDetailDao.saveEntity(detail);
					logger.info("评分添加入库：count:"+count+"，applicationId="+applicationId+"，scoreBigId="+bigId+"，scoreSmallId="+samllId+"，scoreRuleName="+scoreRuleName);
					return;
				}
			}
		}
	}

	/**
	 * 计算分数
	 * @param score
	 * @param percent
	 * @return
	 */
	private String calculateScore(String score, String percent) {
		BigDecimal scoreBig = new BigDecimal(score);
		BigDecimal percentBig = new BigDecimal(percent);
		score = scoreBig.multiply(percentBig).toString();
		return score;
	}
}
