package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.ScDecisionTreeDao;
import com.shangyong.backend.dao.ScFraudRuleBigDao;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudRuleBig;
import com.shangyong.backend.service.ScScoreSmallFraudService;

/**
 * 欺诈评分卡小类 查询接口实现
 * 
 * @author xk
 *
 */
@Service
public class ScScoreSmallFraudServiceImpl implements ScScoreSmallFraudService {
	
	private Logger logger = LoggerFactory.getLogger(ScScoreSmallFraudServiceImpl.class);
	
	@Autowired
	private ScFraudRuleServiceImpl scFraudRuleServiceImpl; // 小类
	
	@Autowired
	private ScFraudRuleBigDao scFraudRuleBigDao;// 大类Dao

	@Autowired
	private ScDecisionTreeDao scDecisionTreeDao;

	/**
	 * 根据欺诈评分大类序号查询对应的小类评分参数信息
	 * @param decisionTreeId 决策树序号
	 * @param scoreBigCode 大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	@Override
	public List<ScFraudRule>  queryScFraudScoreSmallByDecisionTree(String decisionTreeId, String scoreBigCode){
		ScDecisionTree scDecisionTree = new ScDecisionTree();
		scDecisionTree.setDecisionTreeId(Integer.parseInt(decisionTreeId));
		scDecisionTree = scDecisionTreeDao.getEntityById(scDecisionTree);
		if(scDecisionTree!=null && Constants.STATE_NORMAL.equals(scDecisionTree.getState())){
			return queryScFraudScoreSmallByScoreBigCode(scDecisionTree.getFraudRuleTplId().toString(), scoreBigCode);
		}
		return null;
	}

	/**
	 * 根据欺诈评分大类序号查询对应的小类评分参数信息
	 * @param fraudRuleTplId 模板序号
	 * @param scoreBigCode 大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	@Override
	public List<ScFraudRule> queryScFraudScoreSmallByScoreBigCode(String fraudRuleTplId, String scoreBigCode){
		List<ScFraudRule> list = new ArrayList<ScFraudRule>();
		// 根据大类编号与状态（有效）查询系统参数信息是否已存在
		ScFraudRuleBig scFraudRuleBig = scFraudRuleBigDao.queryByFraudRuleBigCode(scoreBigCode);
		
		if(scFraudRuleBig == null || StringUtils.isBlank(scFraudRuleBig.getFraudRuleBigCode())){
			logger.info("欺诈评分卡大类记录不存在");
		}else{
			list = refreshScScoreSmallByScoreBigId(fraudRuleTplId, scFraudRuleBig.getFraudRuleBigId());
		}
		return list;
	}

	private List<ScFraudRule> refreshScScoreSmallByScoreBigId(String fraudRuleTplId, Integer fraudRuleBigId) {
		ScFraudRule scFraudRule = new ScFraudRule();
		scFraudRule.setFraudRuleTplId(fraudRuleTplId);
		scFraudRule.setFraudRuleBigId(fraudRuleBigId);
		scFraudRule.setState("01");// 查询正常状态
		return scFraudRuleServiceImpl.queryByFraudRuleBigIdAndTplId(scFraudRule);
	}

}
