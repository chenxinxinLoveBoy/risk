package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.ScFraudScoreDetailDao;
import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.entity.ScThreshold;
import com.shangyong.backend.service.BaseService;
/**
 * 欺诈评分明细service实现
 * @author hc
 *
 */
@Service
public class ScFraudScoreDetailServiceImpl implements BaseService<ScFraudScoreDetail> {
	
	/**
	 *ScFraudScoreDetailDao数据库操作接口类bean
	 */
	@Autowired
	private ScFraudScoreDetailDao scFraudScoreDetailDao;

	@Autowired
	private ScThresholdServiceImpl scThresholdService;

	@Override
	public List<ScFraudScoreDetail> findAll(ScFraudScoreDetail scFraudScoreDetail) {  
		return scFraudScoreDetailDao.findAll(scFraudScoreDetail);
	}

	@Override
	public ScFraudScoreDetail getEntityById(String id) {  
		return scFraudScoreDetailDao.getEntityById(id);
	}

	@Override
	public ScFraudScoreDetail getEntityById(Integer id) {  
		return null;
	}

	@Override
	public ScFraudScoreDetail getEntityById(ScFraudScoreDetail scFraudScoreDetail) {  
		return scFraudScoreDetailDao.getEntityById(scFraudScoreDetail);
	}
	@Transactional
	@Override
	public boolean saveEntity(ScFraudScoreDetail scFraudScoreDetail) { 
		boolean flag = false;
		int count = scFraudScoreDetailDao.saveEntity(scFraudScoreDetail);
		if(count > 0 ){
			flag = true;
		}
		return flag;
	}
	@Transactional
	@Override
	public boolean updateEntity(ScFraudScoreDetail t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Transactional
	@Override
	public boolean deleteEntity(ScFraudScoreDetail t) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScFraudScoreDetail scFraudScoreDetail) { 
		int count  = scFraudScoreDetailDao.listAllCount(scFraudScoreDetail);
		return count;
	}

	/**
	 * 
	 * 查询所有分数 （匹配申请单编号）
	 * 
	 **/
	public String findAllScore(String applicationId) { 
		return scFraudScoreDetailDao.findAllScore(applicationId);
	}

	/**
	 * 根据申请单号获取 欺诈评分和对应的配置项
	 * @param applicationId
	 * @return map {"score":90, "amount":500}
	 * @throws Throwable
	 */
	public Map<String, Object> queryFraudScoreInfoApi(String applicationId, String fraudRuleTplId) throws Throwable {
		Map<String, Object> map = new HashMap<String, Object>();
		String score = findAllScore(applicationId);
		if(StringUtils.isBlank(score)){
			score = "0";
		}
		ScThreshold scThreshold= scThresholdService.getByScore(score, fraudRuleTplId);
		if(scThreshold != null){
			map.put("scThreshold", scThreshold);
		}else{
			throw new RuntimeException("申请单编号："+applicationId+"，欺诈总分："+score+"，欺诈模板编号："+fraudRuleTplId+"，欺诈评分对应配置不存在!");
		}
		return map;
	}

	/**
	 * 根据申请单编号,借款编号查询欺诈分明细
	 * @param applicationId
	 * @param applicationBuId
	 * @return
	 */
	public List<ScFraudScoreDetail> findAll(String applicationId, String applicationBuId) { 
		return scFraudScoreDetailDao.findAll(applicationId,applicationBuId);
	}

}
