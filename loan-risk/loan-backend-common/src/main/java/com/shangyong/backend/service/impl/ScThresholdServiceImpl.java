package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScThresholdDao;
import com.shangyong.backend.dao.ScThresholdHistoryDao;
import com.shangyong.backend.entity.ScThreshold;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

/**
 *  欺诈风险阈值service实现
 * @author hc
 *
 */
@Service
public class ScThresholdServiceImpl implements BaseService<ScThreshold>{
	
	/**
	 * ScThresholdDao数据库操作接口类bean
	 * @author xxj
	 * @date Wed Jul 05 20:55:50 CST 2017
	 **/
	@Autowired
	private ScThresholdDao scThresholdDao;
	
	@Autowired
	private ScThresholdHistoryDao scThresholdHistoryDao;

	@Override
	public List<ScThreshold> findAll(ScThreshold scThreshold) { 
		return scThresholdDao.findAll(scThreshold);
	}

	@Override
	public ScThreshold getEntityById(String id) { 
		return scThresholdDao.getEntityById(id);
	}

	@Override
	public ScThreshold getEntityById(Integer id) { 
		return null;
	}
 
	@Override
	@Transactional
	public boolean saveEntity(ScThreshold scThreshold) { 
		boolean flag = false; 
		scThreshold.setCreateMan(TokenManager.getUser().getCreateMan());
		scThreshold.setVersion(1);
		scThreshold.setCreateTime(DateUtils.getDate(new Date()));
		scThreshold.setModifyTime(DateUtils.getDate(new Date()));
		
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scThreshold.setModifyMan(user.getNickName()); 
			scThreshold.setCreateMan(user.getNickName()); 
		} else {
			scThreshold.setModifyMan(""); 
			scThreshold.setCreateMan(""); 
		}
		int count =  scThresholdDao.saveEntity(scThreshold); 
		scThresholdHistoryDao.insert(scThreshold);
		if(count > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScThreshold scThreshold) { 
		boolean flag  = false; 
		ScThreshold info = scThresholdDao.getEntityByObj(scThreshold);
		if(info == null){
			throw new RuntimeException("要修改的记录不存在");
		}
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scThreshold.setModifyMan(user.getNickName()); 
		} else {
			scThreshold.setModifyMan(""); 
		}
		scThreshold.setModifyTime(DateUtils.getDate(new Date()));
		scThreshold.setVersion(info.getVersion() + 1 );
		int count = scThresholdDao.updateEntity(scThreshold);
		ScThreshold infonew = scThresholdDao.getEntityByObj(scThreshold);
		scThresholdHistoryDao.insert(infonew);
		if(count > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteEntity(String id) { 
		return false;
	}

	@Override
	public boolean deleteEntity(ScThreshold t) { 
		return false;
	}

	/**
	 * 获取所有信息
	 * @param scThreshold
	 * @return
	 */
	public int listAllCount(ScThreshold scThreshold) { 
		int count = scThresholdDao.listAllCount(scThreshold);
		return count;
	}

	@Override
	public ScThreshold getEntityById(ScThreshold scThreshold) { 
		return scThresholdDao.getEntityByObj(scThreshold);
	}
	
	/**
	 * 根据分数获取结果标签
	 * 
	 * @param score
	 * @return
	 */
	public ScThreshold getByScore(String score, String fraudRuleTplId) {
		return scThresholdDao.getByScore(score, fraudRuleTplId);
	}

	/**
	 * 新增时根据分查询，分数范围是否存在
	 * 
	 * @param scThreshold
	 * @return
	 */
	public List<ScThreshold> queryByScore(ScThreshold scThreshold) { 
		return scThresholdDao.queryByScore(scThreshold);
	}

}
