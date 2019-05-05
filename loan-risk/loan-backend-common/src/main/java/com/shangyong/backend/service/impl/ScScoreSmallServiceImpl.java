package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScScoreSmallDao;
import com.shangyong.backend.dao.ScScoreSmallHisDao;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScScoreSmallServiceImpl implements BaseService<ScScoreSmall> {

	private static Logger logger = LoggerFactory.getLogger(ScScoreSmallServiceImpl.class);
	@Autowired
	private ScScoreSmallDao scScoreSmallDao;
	@Autowired
	private ScScoreSmallHisDao scScoreSmallHisDao;
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	public ScScoreSmall queryByScoreSmallCode(String scoreSmallCode) {
		return scScoreSmallDao.queryByScoreSmallCode(scoreSmallCode);
	}

	@Override
	public List<ScScoreSmall> findAll(ScScoreSmall scScoreSmall) {
		return scScoreSmallDao.getAll(scScoreSmall);
	}

	@Override
	public ScScoreSmall getEntityById(String scoreSmallId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreSmall getEntityById(Integer scoreSmallId) {
		return scScoreSmallDao.selectByPrimaryKey(scoreSmallId);
	}

	@Override
	public ScScoreSmall getEntityById(ScScoreSmall scScoreSmall) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScScoreSmall scScoreSmall) {
		scScoreSmall.setVersion(1);// 新增时默认为1
		scScoreSmall.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreSmall.setCreateMan(user.getId().toString());
			scScoreSmall.setCreateName(user.getNickName());
		} else {
			scScoreSmall.setCreateMan("");
			scScoreSmall.setCreateName("");
		}
		scScoreSmall.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = scScoreSmallDao.insertSelective(scScoreSmall);
		scScoreSmallHisDao.saveEntity(scScoreSmall);// 存入历史表
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScScoreSmall scScoreSmall) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreSmall.setModifyMan(user.getId().toString());
			scScoreSmall.setModifyName(user.getNickName());
		} else {
			scScoreSmall.setModifyMan("");
			scScoreSmall.setModifyName("");
		}
		scScoreSmall.setModifyTime(DateUtils.getDate(new Date()));
		scScoreSmall.setVersion(scScoreSmall.getVersion() + 1);// 修改是版本号自增1
		boolean flag = scScoreSmallDao.updateByPrimaryKeySelective(scScoreSmall);

		ScScoreSmall scSc = scScoreSmallDao.selectByPrimaryKey(scScoreSmall.getScoreSmallId());
		scScoreSmallHisDao.saveEntity(scSc);// 存入历史表
		return flag;
	}

	@Override
	public boolean deleteEntity(String banControlId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScScoreSmall scScoreSmall) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScScoreSmall scScoreSmall) {
		return scScoreSmallDao.listAllCount(scScoreSmall);
	}

	public int listAllCountUnion(String scoreBigCode, String scoreSmallCode, String scoreRuleName, String state, String scoreTplId,String scoreBigId, String scoreSmallId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("scoreBigCode", scoreBigCode);
		param.put("scoreSmallCode", scoreSmallCode);
		param.put("scoreRuleName", scoreRuleName);
		param.put("state", state);
		param.put("scoreTplId", scoreTplId);
		param.put("scoreSmallId", scoreSmallId);
		param.put("scoreBigId", scoreBigId);
		return scScoreSmallDao.listAllCountUnion(param);
	}

	public List<Map<String, String>> findAllUnion(String scoreBigCode, String scoreSmallCode, String scoreRuleName,
			Integer pageIndex, Integer pageSize, String state, String scoreTplId,String scoreBigId, String scoreSmallId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("scoreBigCode", scoreBigCode);
		param.put("scoreSmallCode", scoreSmallCode);
		param.put("scoreRuleName", scoreRuleName);
		param.put("pageIndex", pageIndex);
		param.put("pageSize", pageSize);
		param.put("state", state);
		param.put("scoreTplId", scoreTplId);
		param.put("scoreSmallId", scoreSmallId);
		param.put("scoreBigId", scoreBigId);
		return scScoreSmallDao.getAllUnion(param);
	}

	@Transactional
	public boolean saveEntityBatch(ScScoreSmall scScoreSmall) {
		boolean flag = false;
		List<ScScoreSmall> scScoreSmallList = new ArrayList<ScScoreSmall>();
		if (scScoreSmall != null && scScoreSmall.getIds().length > 0) {
			for (int i = 0; i < scScoreSmall.getIds().length; i++) {// 循环所勾选的多条或一条信用评分项
				if (StringUtils.isNotBlank(scScoreSmall.getIds()[i])) {
					String sinfo = scScoreSmall.getIds()[i];
					ScScoreSmall info = scScoreSmallDao.selectByPrimaryKey(Integer.parseInt(sinfo));// 根据信用评分项查询对应信息
					info.setScoreTplId(scScoreSmall.getScoreTplId()); // 模板id
					scScoreSmallList.add(info);
					scScoreSmallHisDao.saveEntity(info);// 存入历史表
				}
			}
			int count = scScoreSmallDao.saveListEntity(scScoreSmallList);
			if (count > 0) {
				flag = true;
			}
			return flag;
		} else {
			return flag;
		}
	}

	/**
	 * defaultTemplates 默认模板
	 * 
	 * @return
	 */
	public int listAllTemplateCount(ScScoreSmall scScoreSmall) {
		Map<String, Object> map = new HashMap<String, Object>();
		String default_score_template_id = "1";
		try {
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_SCORE_TEMPLATE_ID_REDIS_KEY_NAME);
			default_score_template_id = sysParam.getParamValueOne(); // 默认模板ID
		} catch (Exception e) {
			logger.info("-->>get default_score_template_id from sysparamsredis error", e);
		}
		map.put("defaultTemplates", default_score_template_id);// 默认模板
		map.put("scoreTplId", scScoreSmall.getScoreTplId());// 当前模板id
		map.put("scoreSmallCode", scScoreSmall.getScoreSmallCode());
		map.put("scoreRuleName", scScoreSmall.getScoreRuleName());
		map.put("state", scScoreSmall.getState());
		return scScoreSmallDao.listAllTemplateCount(map);
	}

	/**
	 * defaultTemplates 默认模板
	 * 
	 * @return
	 */
	public List<ScScoreSmall> findAllTemplate(ScScoreSmall scScoreSmall) {
		Map<String, Object> map = new HashMap<String, Object>();
		String default_score_template_id = "1";
		try {
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
			default_score_template_id = sysParam.getParamValueOne(); // 默认模板ID
		} catch (Exception e) {
			logger.info("-->>get default_score_template_id from sysparamsredis error", e);
		}
		map.put("defaultTemplates", default_score_template_id);// 默认模板
		map.put("scoreTplId", scScoreSmall.getScoreTplId());// 当前模板id
		map.put("scoreSmallCode", scScoreSmall.getScoreSmallCode());
		map.put("scoreRuleName", scScoreSmall.getScoreRuleName());
		map.put("state", scScoreSmall.getState());
		return scScoreSmallDao.findAllTemplate(map);
	}

	/**
	 * 统计当前模板下关联的信用评分项
	 * 
	 * @return
	 */
	public int listTemplateCount(ScScoreSmall scScoreSmall) {
		int temp = scScoreSmallDao.listTemplateCount(scScoreSmall);
		return temp;
	}

	/**
	 * 查询当前模板下关联的信用评分分页
	 * 
	 * @return
	 */
	public List<ScScoreSmall> findTemplate(ScScoreSmall scScoreSmall) {
		List<ScScoreSmall> info = scScoreSmallDao.findTemplate(scScoreSmall);
		return info;
	}

	public boolean updateScScoreSmallByBigId(ScScoreSmall scScoreSmall) {
		return scScoreSmallDao.updateScScoreSmallByBigId(scScoreSmall);
	}

	public List<ScScoreSmall> queryByScoreBigIdAndTplId(ScScoreSmall scScoreSmall) {
		return scScoreSmallDao.queryByScoreBigIdAndTplId(scScoreSmall);
	}

	public ScScoreSmall queryByScoreSmallCodeAndTplId(ScScoreSmall scScoreSmall) {
		return scScoreSmallDao.queryByScoreSmallCodeAndTplId(scScoreSmall);
	}

	public double getSumPercent(String scoreTplId) {
		return scScoreSmallDao.getSumPercent(scoreTplId);
	}

	/**
	 * 加载菜单列表树
 	 * @return
	 */
	public List<ZTree> getScScoreTree(String scoreTplId) {
		return scScoreSmallDao.getScScoreTree(scoreTplId);
	}
	

}
