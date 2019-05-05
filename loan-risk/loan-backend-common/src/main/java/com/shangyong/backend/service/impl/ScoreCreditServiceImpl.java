package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScScoreCreditDao;
import com.shangyong.backend.dao.ScScoreCreditHisDao;
import com.shangyong.backend.entity.ScScoreCredit;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

/**
 * 闪贷评分与授信额度定义service
 * 
 * @author xixinghui
 *
 */
@Service
public class ScoreCreditServiceImpl implements BaseService<ScScoreCredit> {

	@Autowired
	private ScScoreCreditDao scScoreCreditDao;

	@Autowired
	private ScScoreCreditHisDao scScoreCreditHisDao;

	@Override
	public List<ScScoreCredit> findAll(ScScoreCredit scScoreCredit) {
		return scScoreCreditDao.findAll(scScoreCredit);
	}

	@Override
	public ScScoreCredit getEntityById(String scoreCreditId) {
		return scScoreCreditDao.getEntityById(scoreCreditId);
	}

	@Override
	public ScScoreCredit getEntityById(ScScoreCredit scScoreCredit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScScoreCredit scScoreCredit) {
		scScoreCredit.setVersion(1);// 新增时默认为1
		scScoreCredit.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreCredit.setCreateMan(user.getId().toString());
			scScoreCredit.setCreateName(user.getNickName());
		} else {
			scScoreCredit.setCreateMan("");
			scScoreCredit.setCreateName("");
		}
		boolean flag = scScoreCreditDao.insert(scScoreCredit);
		scScoreCreditHisDao.insert(scScoreCredit);
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScScoreCredit scScoreCredit) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreCredit.setModifyMan(user.getId().toString());
			scScoreCredit.setModifyName(user.getNickName());
		} else {
			scScoreCredit.setModifyMan("");
			scScoreCredit.setModifyName("");
		}
		scScoreCredit.setModifyTime(DateUtils.getDate(new Date()));
		scScoreCredit.setVersion(scScoreCredit.getVersion() + 1);// 修改是版本号自增1
		boolean flag = scScoreCreditDao.updateByPrimaryKey(scScoreCredit);
		scScoreCreditHisDao.insert(scScoreCredit);
		return flag;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScScoreCredit scScoreCredit) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScScoreCredit scScoreCredit) {
		return scScoreCreditDao.listAllCount(scScoreCredit);
	}

	@Override
	public ScScoreCredit getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 新增时根据分查询，分数范围是否存在
	 * 
	 * @param scoreCredit
	 * @return
	 */
	public List<ScScoreCredit> queryByScore(ScScoreCredit scoreCredit) {
		return scScoreCreditDao.queryByScore(scoreCredit);
	}

	/**
	 * 根据分数获取授信额度
	 * 
	 * @param score
	 * @param scoreTplId
	 * @return
	 */
	public ScScoreCredit getByScore(String score, String scoreTplId) {
		return scScoreCreditDao.getByScore(score, scoreTplId);
	}

}
