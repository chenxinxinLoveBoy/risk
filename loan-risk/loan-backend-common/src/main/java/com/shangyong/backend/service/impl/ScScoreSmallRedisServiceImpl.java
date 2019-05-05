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
import com.shangyong.backend.dao.ScScoreBigDao;
import com.shangyong.backend.dao.ScScoreSmallDao;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.ScScoreSmallRedisService;

/**
 * 评分卡小类 查询接口实现
 * 
 * @author xk
 *
 */
@Service
public class ScScoreSmallRedisServiceImpl implements ScScoreSmallRedisService {
	
	private Logger logger = LoggerFactory.getLogger(ScScoreSmallRedisServiceImpl.class);
	
	@Autowired
	private ScScoreSmallDao scScoreSmallDao; // 小类DAO
	
	@Autowired
	private ScScoreBigDao scScoreBigDao;// 大类Dao

	@Autowired
	private ScDecisionTreeDao scDecisionTreeDao;

	/**
	 * 根据评分卡大类序号查询对应的小类评分参数信息
	 * 
	 * @param decisionTreeId 	决策树编号
	 * @param scoreBigCode 	大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	@Override
	public List<ScScoreSmall> queryScScoreSmallByDecisionTree(String decisionTreeId, String scoreBigCode){
		ScDecisionTree scDecisionTree = new ScDecisionTree();
		scDecisionTree.setDecisionTreeId(Integer.parseInt(decisionTreeId));
		scDecisionTree = scDecisionTreeDao.getEntityById(scDecisionTree);
		if(scDecisionTree!=null && Constants.STATE_NORMAL.equals(scDecisionTree.getState())){
			return queryScScoreSmallByScoreBigIdRedis(scDecisionTree.getScoreTplId(), scoreBigCode);
		}
		return null;
	}

	/**
	 * 根据评分卡大类序号查询对应的小类评分参数信息
	 * 
	 * @param scoreTplId 		信用评分模板编号
	 * @param scoreBigCode 	大类编号
	 * @return 如果数据不存在就返回null,存在就返回对象信息，并刷新到redis
	 * @throws Throwable
	 */
	@Override
	public List<ScScoreSmall> queryScScoreSmallByScoreBigIdRedis(Integer scoreTplId, String scoreBigCode){
		List<ScScoreSmall> list = new ArrayList<ScScoreSmall>();
		// 根据大类编号与状态（有效）查询系统参数信息是否已存在
		ScScoreBig scScoreBig = scScoreBigDao.queryByScoreBigCode(scoreBigCode);
		if(scScoreBig == null || StringUtils.isBlank(scScoreBig.getScoreBigCode())){
			logger.info("评分卡大类记录不存在");
		}else{
			list = refreshScScoreSmallByScoreBigIdRedis(scoreTplId, scScoreBig.getScoreBigId());
		}
		return list;
	}

	private List<ScScoreSmall> refreshScScoreSmallByScoreBigIdRedis(Integer scoreTplId, Integer scoreBigId) {
		ScScoreSmall scScoreSmall = new ScScoreSmall();
		scScoreSmall.setScoreBigId(scoreBigId);
		scScoreSmall.setState("01");// 查询正常状态
		scScoreSmall.setScoreTplId(scoreTplId);
		scScoreSmall.setPageIndex(-1);// 不做分页
		return scScoreSmallDao.getAll(scScoreSmall);
	}

}
