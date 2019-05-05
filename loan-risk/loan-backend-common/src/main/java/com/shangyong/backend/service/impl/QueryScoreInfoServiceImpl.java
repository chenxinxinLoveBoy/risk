package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreDetailDao;
import com.shangyong.backend.entity.ScScoreCredit;
import com.shangyong.backend.service.QueryScoreInfoService;

/**
 *  评分 - 分数等级
 *  @author xiangxianjin
 */
@Service
public class QueryScoreInfoServiceImpl implements QueryScoreInfoService{

	/**
	 * 闪贷评分与授信额度定义service
	 */
	@Autowired
	private ScoreCreditServiceImpl scoreCreditServiceImpl;
	
	/**
	 * 评分明细
	 */
	@Autowired
	private ScScoreDetailDao scScoreDetailDao;

	/**
	 * 根据申请单号获取 评分和对应的放款金额
	 * @param applicationId	申请单编号
	 * @param scoreTplId		信用评分模板编号
	 * @return map {"score":90, "amount":500}
	 * @throws Throwable
	 */
	public Map<String, Object> queryScoreInfoApi(String applicationId, String scoreTplId) throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		String score = scScoreDetailDao.findAllScore(applicationId);
		if(StringUtils.isBlank(score)){
			score = "0";
		}
		ScScoreCredit scScoreCredit= scoreCreditServiceImpl.getByScore(score, scoreTplId);
		if(scScoreCredit != null){
			map.put("score", score);
			map.put("amount", scScoreCredit.getCreditMoney());
			map.put("promoteCreditMoney", scScoreCredit.getPromoteCreditMoney());
		}else{
			throw new RuntimeException("申请单编号："+applicationId+"，信用评分总分："+score+"，信用评分模板编号："+scoreTplId+"，信用评分对应配置不存在!");
		}
		return map;
	}

}
