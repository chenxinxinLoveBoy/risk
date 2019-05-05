package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScScoreTplDao;
import com.shangyong.backend.dao.ScScoreTplHisDao;
import com.shangyong.backend.entity.ScScoreTpl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

/**
 * 信用评分项模版service实现
 * 
 *
 */
@Service
public class ScScoreTplServiceImpl implements BaseService<ScScoreTpl> {

	Logger logger = LoggerFactory.getLogger(ScScoreTplServiceImpl.class);

	@Autowired
	private ScScoreTplDao scScoreTplDao;

	@Autowired
	private ScScoreTplHisDao scScoreTplHisDao;

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	@Override
	public List<ScScoreTpl> findAll(ScScoreTpl scScoreTpl) {
		return scScoreTplDao.findAll(scScoreTpl);
	}

	@Override
	public ScScoreTpl getEntityById(ScScoreTpl scScoreTpl) {
		return scScoreTplDao.getEntityById(scScoreTpl);
	}

	@Override
	@Transactional
	public boolean saveEntity(ScScoreTpl scScoreTpl) {
		boolean flag = false;
		scScoreTpl.setVersion(1);
		scScoreTpl.setLevel(0);//弃用字段，默认给0
		scScoreTpl.setTplPercent("0");//弃用字段，默认给0
		scScoreTpl.setCreateTime(DateUtils.getDate(new Date()));
		scScoreTpl.setModifyTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreTpl.setModifyMan(user.getId().toString());
			scScoreTpl.setModifyName(user.getNickName());
			scScoreTpl.setCreateMan(user.getId().toString());
			scScoreTpl.setCreateName(user.getNickName());
		} else {
			scScoreTpl.setModifyMan("");
			scScoreTpl.setModifyName("");
			scScoreTpl.setCreateMan("");
			scScoreTpl.setCreateName("");
		}

		int count = scScoreTplDao.saveEntity(scScoreTpl);
		scScoreTplHisDao.insert(scScoreTpl);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScScoreTpl scScoreTpl) {
		boolean flag = false;
		scScoreTpl.setVersion(scScoreTpl.getVersion() + 1);// 修改是版本号自增1
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scScoreTpl.setModifyMan(user.getId().toString());
			scScoreTpl.setModifyName(user.getNickName());
		} else {
			scScoreTpl.setModifyMan("");
			scScoreTpl.setModifyName("");
		}
		scScoreTpl.setModifyTime(DateUtils.getDate(new Date()));

		ScScoreTpl scOld = scScoreTplDao.getEntityById(scScoreTpl);
		if (scOld == null) {
			throw new RuntimeException("要修改的记录不存在");
		}
		scScoreTpl.setLevel(0);//弃用字段，默认给0
		scScoreTpl.setTplPercent("0");//弃用字段，默认给0
		int temp = scScoreTplDao.updateEntity(scScoreTpl);
		ScScoreTpl scNew = scScoreTplDao.getEntityById(scScoreTpl);
		scScoreTplHisDao.insert(scNew);

		if (temp > 0) {
			flag = true;
		}
		return flag;
	}

	public double getSumTplPercent(String defaultScoreTemplateId, String scoreTplId) {
		return scScoreTplDao.getSumTplPercent(defaultScoreTemplateId, scoreTplId);
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	public int listAllCount(ScScoreTpl scScoreTpl) {
		int count = scScoreTplDao.listAllCount(scScoreTpl);
		return count;
	}

	public ScScoreTpl findCode(ScScoreTpl scScoreTpl) {
		return scScoreTplDao.findCode(scScoreTpl);

	}

	/**
	 * 获取目标分配结果
	 * 
	 * @param recordSize
	 * @return { "001":10, "002":20}
	 */
	public Map<String, Object> getFraudRuleTmpl(Integer recordSize) {
		logger.info("-->>模板引擎分配信用评分项模板，处理开始，共处理条数：" + recordSize);

		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_SCORE_TEMPLATE_ID_REDIS_KEY_NAME);
		String defaultScoreTemplateId = sysParam.getParamValueOne(); // 默认模板ID
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("defaultScoreTemplateId", defaultScoreTemplateId);
		List<ScScoreTpl> list = scScoreTplDao.getScScoreTpl(params); // 当前有效模板

		Map<String, Object> templateMap = new HashMap<String, Object>();

		int surplusTemplSize = recordSize; // 剩余记录条数
		if (CollectionUtils.isNotEmpty(list)) {
			// 循环有效欺诈项模板
			for (int i = 0; i < list.size(); i++) {
				// 根据单个模板设定的权重计算处理条数
				ScScoreTpl tpl = list.get(i);
				Integer tplId = tpl.getScoreTplId();
				String percent = tpl.getTplPercent();
				// 根据权重和数据总条数计算单个模板需处理的记录数
				Double size = Double.valueOf(percent) * recordSize;
				// 向上取整，若分配数量超过剩下的审批单的数量，则只分配当前所有的审批单数量
				int count = (int) Math.ceil(size);// 分配数量
				count = count > surplusTemplSize ? surplusTemplSize : count;

				logger.info("模板ID:[" + tplId + "]配置的权重为[" + percent + "]-->>模板引擎分配至【" + tplId + "="
						+ tpl.getScoreTplName() + "】的记录数：" + count);
				// 分配后放入对象中
				if (templateMap.containsKey(tplId.toString())) {
					throw new RuntimeException("模板引擎分配欺诈项模板ID疑似重复！");
				}
				templateMap.put(tplId.toString(), count);

				// 消费已分配的size，如果小于等于0，处理结束
				surplusTemplSize = surplusTemplSize - count;
				logger.info("剩余记录条数：" + surplusTemplSize);
				if (surplusTemplSize <= 0) {
					break;
				}
			}
		}
		// 未分配完的申请单走默认模板处理
		if (surplusTemplSize > 0) {
			if (templateMap.containsKey(defaultScoreTemplateId)) {
				throw new RuntimeException("模板引擎分配默认信用评分项模板ID疑似重复！");
			}
			templateMap.put(defaultScoreTemplateId, surplusTemplSize);
			logger.info("-->>模板引擎分配至【" + defaultScoreTemplateId + "=基础模板】，处理条数：" + surplusTemplSize);
		}
		logger.info("-->>模板引擎分配欺诈项模板，处理结束，共处理条数：" + recordSize);
		return templateMap;
	}

	@Override
	public ScScoreTpl getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScScoreTpl getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScScoreTpl t) {
		// TODO Auto-generated method stub
		return false;
	}

}
