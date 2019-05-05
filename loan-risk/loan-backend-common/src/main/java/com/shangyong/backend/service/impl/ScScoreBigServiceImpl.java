package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScScoreBigDao;
import com.shangyong.backend.dao.ScScoreBigHisDao;
import com.shangyong.backend.dao.ScScoreSmallDao;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

@Service
public class ScScoreBigServiceImpl implements BaseService<ScScoreBig> {

	@Autowired
	private ScScoreBigDao scScoreBigDao;// 大类dao

	@Autowired
	private ScScoreSmallDao scScoreSmallDao;// 小类dao

	@Autowired
	private ScScoreBigHisDao scScoreBigHisDao;

	public ScScoreBig queryByScoreBigCode(String scoreBigCode) {
		return scScoreBigDao.queryByScoreBigCode(scoreBigCode);
	}

	@Override
	public List<ScScoreBig> findAll(ScScoreBig scScoreBig) {
		return scScoreBigDao.getAll(scScoreBig);
	}

	@Override
	public ScScoreBig getEntityById(String scoreBigId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreBig getEntityById(Integer scoreBigId) {
		return scScoreBigDao.selectByPrimaryKey(scoreBigId);
	}

	@Override
	public ScScoreBig getEntityById(ScScoreBig scScoreBig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScScoreBig scScoreBig) {
		scScoreBig.setVersion(1);// 新增时默认为1
		scScoreBig.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreBig.setCreateMan(user.getId().toString());
			scScoreBig.setCreateName(user.getNickName());
		} else {
			scScoreBig.setCreateMan("");
			scScoreBig.setCreateName("");
		}
		scScoreBig.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = scScoreBigDao.insertSelective(scScoreBig);
		scScoreBigHisDao.saveEntity(scScoreBig);// 存入历史表
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		// RedisUtils.hsetEx(RedisCons.RISK_SC_SCORE_BIG,
		// scScoreBig.getScoreBigCode(),
		// SysParamUtils.ObjectToJson(scScoreBig), 31536000);
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScScoreBig scScoreBig) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreBig.setModifyMan(user.getId().toString());
			scScoreBig.setModifyName(user.getNickName());
		} else {
			scScoreBig.setModifyMan("");
			scScoreBig.setModifyName("");
		}
		scScoreBig.setModifyTime(DateUtils.getDate(new Date()));
		scScoreBig.setVersion(scScoreBig.getVersion() + 1);// 修改是版本号自增1
		boolean flag = scScoreBigDao.updateByPrimaryKeySelective(scScoreBig);

		// 修改大类下对应小类的的状态
		if ("1".equals(scScoreBig.getUpdateScScoreSmall())) {
			ScScoreSmall small = new ScScoreSmall();
			small.setScoreBigId(scScoreBig.getScoreBigId());// 大类序号
			small.setState("02");// 置为失效
			boolean smallFlag = updatescScoreSmall(small);
			// 如小类修改失败，回滚
			if (!smallFlag) {
				throw new RuntimeException("类：ScScoreBigServiceImpl，方法名：updateEntity（），修改小类状态异常！！！！！");
			}
		}
		scScoreBigHisDao.saveEntity(scScoreBig);// 存入历史表
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		// RedisUtils.hsetEx(RedisCons.RISK_SC_SCORE_BIG,
		// scScoreBig.getScoreBigCode(),
		// SysParamUtils.ObjectToJson(scScoreBig), 31536000);
		return flag;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScScoreBig scScoreBig) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScScoreBig scScoreBig) {
		return scScoreBigDao.listAllCount(scScoreBig);
	}

	/**
	 * 修改小类的状态
	 * 
	 * @param small
	 * @return
	 */
	public boolean updatescScoreSmall(ScScoreSmall small) {
		return scScoreSmallDao.updatescScoreSmall(small);
	}

}
