package com.shangyong.backend.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.ScBanControlDao;
import com.shangyong.backend.dao.ScDecisionTreeDao;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.service.ScBanControlRedisService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

/**
 * 禁止项规则控制表 查询接口实现
 * 
 * @author xk
 *
 */
@Service
public class ScBanControlRedisServiceImpl implements ScBanControlRedisService {
	@Autowired
	private ScBanControlDao scBanControlDao;

	@Autowired
	private ScDecisionTreeDao scDecisionTreeDao;

	private Logger logger = LoggerFactory.getLogger(ScBanControlRedisServiceImpl.class);


	@Override
	public ScBanControl queryScBanControlByBanCodeRedis(String decisionTreeId, String banCode){
		ScDecisionTree scDecisionTree = new ScDecisionTree();
		scDecisionTree.setDecisionTreeId(Integer.parseInt(decisionTreeId));
		scDecisionTree = scDecisionTreeDao.getEntityById(scDecisionTree);
		if(scDecisionTree!=null && Constants.STATE_NORMAL.equals(scDecisionTree.getState())){
			return queryScBanControlByBanCode(scDecisionTree.getBanControlTplId().toString(), banCode);
		}
		return null;
	}

	@Override
	public ScBanControl queryScBanControlByBanCode(String tplId, String banCode){
		ScBanControl scBanControl = new ScBanControl();
		// 判断系统参数信息是否已存在
		String value = RedisUtils.hget(RedisCons.RISK_SC_BAN_CONTROL, tplId+"_"+banCode);
		if (StringUtils.isNotBlank(value)) {
			try {
				// 转换redis中的json为参数实体
				scBanControl = (ScBanControl) SysParamUtils.JsonToBean(value, ScBanControl.class);
			} catch (Throwable e) {
				logger.error("querySysParamByBanCodeRedis()String转Json服务异常：" + e.getMessage(), e);
				throw new RuntimeException("转Json服务异常,异常信息：" + e.getMessage(), e);
			}
		} else {
			scBanControl = refreshScBanControlByBanCodeRedis(tplId, banCode);
		}
		return scBanControl;
	}

	private ScBanControl refreshScBanControlByBanCodeRedis(String tplId, String banCode) {
		ScBanControl scBanControl = scBanControlDao.queryByBanCodeAndTplId(tplId, banCode);
		if (scBanControl == null) {
			logger.error("根据禁止项模板编号【"+tplId+"】，规则对应编号【" + banCode + "】查询无数据返回, 请确认禁止项规则对应编号是否已配置生效");
			return null;
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_BAN_CONTROL, tplId+"_"+banCode, SysParamUtils.ObjectToJson(scBanControl), 31536000);
		return scBanControl;
	}
	
}
