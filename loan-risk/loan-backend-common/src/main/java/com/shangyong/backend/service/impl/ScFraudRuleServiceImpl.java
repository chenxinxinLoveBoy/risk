package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScFraudRuleDao;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudRuleHis;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

@Service
public class ScFraudRuleServiceImpl implements BaseService<ScFraudRule> {

	private static Logger logger = LoggerFactory.getLogger(ScFraudRuleServiceImpl.class);

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	@Autowired
	private ScFraudRuleDao scFraudRuleDao;

	@Autowired
	private ScFraudRuleHisServiceImpl scFraudRuleHisService;

	@Override
	public List<ScFraudRule> findAll(ScFraudRule scFraudRule) {
		return scFraudRuleDao.findAll(scFraudRule);
	}

	@Override
	public ScFraudRule getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRule getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return scFraudRuleDao.getEntityById(id);
	}

	@Override
	public ScFraudRule getEntityById(ScFraudRule scFraudRule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScFraudRule scFraudRule) {
		scFraudRule.setVersion(1);// 新增时默认为1
		scFraudRule.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRule.setCreateMan(user.getId().toString());
			scFraudRule.setCreateName(user.getNickName());
		} else {
			scFraudRule.setCreateMan("");
			scFraudRule.setCreateName("");
		}
		scFraudRule.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = scFraudRuleDao.saveEntity(scFraudRule);
		ScFraudRuleHis scFraudRuleHis = new ScFraudRuleHis();
		BeanUtils.copyProperties(scFraudRule, scFraudRuleHis);
		scFraudRuleHisService.saveEntity(scFraudRuleHis);
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_FRAUD_RULE, scFraudRule.getFraudRuleTplId() + "_" + scFraudRule.getFraudRuleCode(),
				SysParamUtils.ObjectToJson(scFraudRule), 31536000);
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScFraudRule scFraudRule) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRule.setModifyMan(user.getId().toString());
			scFraudRule.setModifyName(user.getNickName());
		} else {
			scFraudRule.setModifyMan("");
			scFraudRule.setModifyName("");
		}
		scFraudRule.setModifyTime(DateUtils.getDate(new Date()));
		scFraudRule.setVersion(scFraudRule.getVersion() + 1);// 修改是版本号自增1
		ScFraudRule scFraudRuleOld = scFraudRuleDao.getEntityById(scFraudRule.getFraudRuleId());

		if (scFraudRuleOld == null) {
			throw new RuntimeException("要修改的记录不存在");
		}

		boolean flag = scFraudRuleDao.updateEntity(scFraudRule);

		ScFraudRule scFraudRuleNew = scFraudRuleDao.getEntityById(scFraudRule.getFraudRuleId());
		ScFraudRuleHis scFraudRuleHis = new ScFraudRuleHis();
		BeanUtils.copyProperties(scFraudRuleNew, scFraudRuleHis);
		scFraudRuleHisService.saveEntity(scFraudRuleHis);
		String fraudRuleTplId = scFraudRule.getFraudRuleTplId();
		String fraudRuleCode = scFraudRuleOld.getFraudRuleCode();
		// 判断redis中key是否存在，存在便删除该key
		if (RedisUtils.hexists(RedisCons.RISK_SC_FRAUD_RULE, fraudRuleTplId + "_" + fraudRuleCode)) {
			RedisUtils.hdelEx(RedisCons.RISK_SC_FRAUD_RULE, fraudRuleTplId + "_" + fraudRuleCode);
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_FRAUD_RULE,fraudRuleTplId+"_"+scFraudRuleNew.getFraudRuleCode(), SysParamUtils.ObjectToJson(scFraudRuleNew),31536000);
		return flag;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScFraudRule scFraudRule) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScFraudRule scFraudRule) {
		return scFraudRuleDao.listAllCount(scFraudRule);
	}

	public List<ScFraudRule> queryByFraudRuleCode(String fraudRuleCode) {
		return scFraudRuleDao.queryByFraudRuleCode(fraudRuleCode);
	}

	@Transactional
	public boolean saveEntityBatch(ScFraudRule scFraudRule) {
		boolean flag = false;
		List<ScFraudRule> scFraudRuleList = new ArrayList<ScFraudRule>();
		if (scFraudRule != null && scFraudRule.getIds().length > 0) {
			for (int i = 0; i < scFraudRule.getIds().length; i++) {// 循环所勾选的多条或一条欺诈规则项
				if (StringUtils.isNotBlank(scFraudRule.getIds()[i])) {
					String sinfo = scFraudRule.getIds()[i];
					ScFraudRule info = scFraudRuleDao.getEntityById(Integer.parseInt(sinfo));// 根据禁止项查询对应信息
					info.setFraudRuleTplId(scFraudRule.getFraudRuleTplId()); // 模板id
					scFraudRuleList.add(info);
					//存入历史表
					ScFraudRuleHis scFraudRuleHis = new ScFraudRuleHis();
					BeanUtils.copyProperties(info, scFraudRuleHis);
					scFraudRuleHisService.saveEntity(scFraudRuleHis);
				}
			}
			int count = scFraudRuleDao.saveListEntity(scFraudRuleList);
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
	public int listAllTemplateCount(ScFraudRule scFraudRule) {
		Map<String, Object> map = new HashMap<String, Object>();
		String default_fraud_template_id = "1";
		try {
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
			default_fraud_template_id = sysParam.getParamValueOne(); // 默认模板ID
		} catch (Exception e) {
			logger.info("-->>get default_fraud_template_id from sysparamsredis error", e);
		}
		map.put("defaultTemplates", default_fraud_template_id);// 默认模板
		map.put("fraudRuleTplId", scFraudRule.getFraudRuleTplId());// 当前模板id
  		map.put("fraudRuleCode", scFraudRule.getFraudRuleCode());
		map.put("ruleName", scFraudRule.getRuleName());
		map.put("creditType", scFraudRule.getCreditType());
		map.put("state", scFraudRule.getState());
		return scFraudRuleDao.listAllTemplateCount(map);
	}

	/**
	 * defaultTemplates 默认模板
	 * 
	 * @return
	 */
	public List<ScFraudRule> findAllTemplate(ScFraudRule scFraudRule) {
		Map<String, Object> map = new HashMap<String, Object>();
		String default_fraud_template_id = "1";
		try {
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
			default_fraud_template_id = sysParam.getParamValueOne(); // 默认模板ID
		} catch (Exception e) {
			logger.info("-->>get default_fraud_template_id from sysparamsredis error", e);
		}
		map.put("defaultTemplates", default_fraud_template_id);// 默认模板
		map.put("fraudRuleTplId", scFraudRule.getFraudRuleTplId());// 当前模板id
 		map.put("fraudRuleCode", scFraudRule.getFraudRuleCode());
		map.put("ruleName", scFraudRule.getRuleName());
		map.put("creditType", scFraudRule.getCreditType());
		map.put("state", scFraudRule.getState());
 		return scFraudRuleDao.findAllTemplate(map);
	}

	/**
	 * 统计当前模板下关联的欺诈规则项
	 * 
	 * @return
	 */
	public int listTemplateCount(ScFraudRule scFraudRule) {
		int temp = scFraudRuleDao.listTemplateCount(scFraudRule);
		return temp;
	}

	/**
	 * 查询当前模板下关联的欺诈规则分页
	 * 
	 * @return
	 */
	public List<ScFraudRule> findTemplate(ScFraudRule scFraudRule) {
		List<ScFraudRule> info = scFraudRuleDao.findTemplate(scFraudRule);
		return info;
	}

	public boolean updateFraudRuleByBigId(ScFraudRule fraudRule) {
		return scFraudRuleDao.updateFraudRuleByBigId(fraudRule);
	}

	public List<ScFraudRule> queryByFraudRuleBigIdAndTplId(ScFraudRule scFraudRule) {
		return scFraudRuleDao.queryByFraudRuleBigIdAndTplId(scFraudRule);
	}

	public ScFraudRule queryByFraudRuleCodeAndTplId(ScFraudRule scFraudRule) {
		return scFraudRuleDao.queryByFraudRuleCodeAndTplId(scFraudRule);
	}
	
	public double getSumPercent(String fraudRuleTplId) {
		return scFraudRuleDao.getSumPercent(fraudRuleTplId);
	}
	
	/**
	 * 加载菜单列表树
 	 * @return
	 */
	public List<ZTree> getScFraudRuleTree(String fraudRuleTplId) {
		return scFraudRuleDao.getScFraudRuleTree(fraudRuleTplId);
	}
	
}
