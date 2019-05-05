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
import com.shangyong.backend.dao.ScFraudScoreDetailDao;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.service.RiskFraudScoreService;
import com.shangyong.backend.service.RiskRuleCheckService;
import com.shangyong.backend.service.ScScoreSmallFraudService;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;
 
/**
 * 欺诈评分接口
 * @author xiangxianjin
 *
 */
@Service
public class RiskFraudScoreServiceImpl implements RiskFraudScoreService {

	private static Logger logger = LoggerFactory.getLogger("rule");

	/**
	 * 评分明细
	 */
	@Autowired
	private ScFraudScoreDetailDao scFraudScoreDetailDao;

	/**
	 * 比对接口
	 */
	@Autowired
	private RiskRuleCheckService riskRuleCheckServiceImpl;

	@Autowired
	private ScScoreSmallFraudService scScoreSmallFraudService;

	/**
	 * 根据 大类编号 计算分数明细，并且入库
	 * 
	 * @param fraudRuleTplId
	 *           欺诈模板编号
	 * @param fraudRuleBigCode
	 *            欺诈规则大类编号
	 * @param checkValue
	 *            需比对值
	 * @param applicationId
	 *            申请单号
	 * @throws Throwable
	 */
	@Override
	public void queryApplyScoreApi(String fraudTplId, String fraudRuleBigCode, Object checkValue,
			String applicationId,String type) {
		List<ScFraudRule> scFraudRuleList = scScoreSmallFraudService.queryScFraudScoreSmallByScoreBigCode(fraudTplId, fraudRuleBigCode);

		logger.info("欺诈评分校验，欺诈模板编号["+fraudTplId+"]，大类规则编号："+fraudRuleBigCode+"，申请单编号："+applicationId);
		if (CollectionUtils.isNotEmpty(scFraudRuleList)) {
			for (ScFraudRule scFraudRule : scFraudRuleList) {
				if (scFraudRule == null || Constants.STATE_FORBIDDEN.equals(scFraudRule.getState())) {
					continue;
				}
				/** 评分项规则名称 **/
				String ruleName = scFraudRule.getRuleName();
				/** 评分项规则技术比对值 **/
				String ruleComparisonValue = scFraudRule.getRuleComparisonValue();
				/** 分值 **/
				String score = scFraudRule.getScore();
				/** 技术比对值类型（01-数值、02-字符、03-集合） **/
				String ruleComparisonType = scFraudRule.getRuleComparisonType();
				/** 技术校验规则 **/
				String validateRule = scFraudRule.getValidateRule();
				/** 权重 **/
				String percent = scFraudRule.getPercent();
				if (!type.equals(ruleComparisonType)) {
					continue;
				}
				Integer fraudRuleId = scFraudRule.getFraudRuleId();

				logger.info("---->欺诈评分规则名称：" + ruleName + "，fraudRuleBigCode:" + fraudRuleBigCode + "，validateRule:" + validateRule+ "，checkValue:" + checkValue+ "，applicationId:" + applicationId);

				ScFraudScoreDetail detail = new ScFraudScoreDetail();
				detail.setApplicationId(applicationId);
				String fraudRuleId1 = String.valueOf(fraudRuleId);
				detail.setFraudRuleId(fraudRuleId1);
				detail.setFraudTplId(fraudTplId);//欺诈模板id
				// 应在报告检查之前删除
				int isExisit = this.scFraudScoreDetailDao.deleteByAppIdAndFraudRuleId(detail);

				logger.info("欺诈评分存在删除：count:" + isExisit + "，applicationId=" + applicationId + "， fraudRuleId=" + fraudRuleId+",fraudTplId="+fraudTplId);

				/** 校验规则是否命中，true命中，分数入库 **/
				boolean flag = riskRuleCheckServiceImpl.ruleCheck(ruleComparisonType, validateRule, ruleComparisonValue,
						checkValue);

				logger.info("欺诈评分规则校验结果，是否命中:" + flag + "，分数：" + score + "， 权重：" + percent);
				// 命中规则，评分信息入库
				if (flag) {
					detail.setFraudScoreDetailId(UUIDUtils.getUUID());
					detail.setRuleName(ruleName);
					detail.setScore(score);
					detail.setCreateTime(DateUtils.parseToDateTimeStr(new Date()));
					int count = scFraudScoreDetailDao.saveEntity(detail);
					logger.info("欺诈评分添加入库，count=" + count + "，applicationId=" + applicationId + "，fraudRuleId="
							+ fraudRuleId + ", ruleName=" + ruleName);
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
