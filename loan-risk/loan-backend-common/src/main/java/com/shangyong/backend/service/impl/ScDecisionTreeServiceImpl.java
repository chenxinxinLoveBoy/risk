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
import com.shangyong.backend.dao.ScDecisionTreeDao;
import com.shangyong.backend.dao.ScDecisionTreeHisDao;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

/**
 * 信用评分项模版service实现
 * 
 *
 */
@Service
public class ScDecisionTreeServiceImpl{

	Logger logger = LoggerFactory.getLogger(ScDecisionTreeServiceImpl.class);

	@Autowired
	private ScDecisionTreeDao scDecisionTreeDao;

	@Autowired
	private ScDecisionTreeHisDao scDecisionTreeHisDao;

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	@Transactional
	public boolean saveEntity(ScDecisionTree scDecisionTree) {
		boolean flag = false;
		scDecisionTree.setVersion(1);
		scDecisionTree.setCreateTime(DateUtils.getDate(new Date()));
		scDecisionTree.setModifyTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scDecisionTree.setModifyMan(user.getId().toString());
			scDecisionTree.setModifyName(user.getNickName());
			scDecisionTree.setCreateMan(user.getId().toString());
			scDecisionTree.setCreateName(user.getNickName());
		} else {
			scDecisionTree.setModifyMan("");
			scDecisionTree.setModifyName("");
			scDecisionTree.setCreateMan("");
			scDecisionTree.setCreateName("");
		}

		int count = scDecisionTreeDao.saveEntity(scDecisionTree);
		scDecisionTreeHisDao.insert(scDecisionTree);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}


	/**
	 * 获取目标分配结果
	 * 
	 * @param recordSize
	 * @return { "001":10, "002":20}
	 **/

	public Map<String, Object> getDecisionTreeTmpl(Integer recordSize) {
		logger.info("-->>模板引擎分配决策树，处理开始，共处理条数：" + recordSize);

		//获取默认的决策树配置
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_TREE_TEMPLATE_ID_REDIS_KEY_NAME);
		//决策树默认模板id
		String defaultTreeTemplateId = sysParam.getParamValueOne(); // 默认模板ID
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("defaultTreeTemplateId", defaultTreeTemplateId);
		
		//查询所有除了基础决策树之外的有效决策树信息
		List<ScDecisionTree> list = scDecisionTreeDao.getScDecisionTree(params);

		Map<String, Object> templateMap = new HashMap<String, Object>();

		int surplusTemplSize = recordSize; // 剩余记录条数
		
		if (CollectionUtils.isNotEmpty(list)) {
			// 循环有效欺诈项模板
			for (int i = 0; i < list.size(); i++) {
				// 根据单个模板设定的权重计算处理条数
				ScDecisionTree tpl = list.get(i);
				Integer tplId = tpl.getDecisionTreeId();
				String percent = tpl.getTplPercent();
				// 根据权重和数据总条数计算单个模板需处理的记录数
				Double size = Double.valueOf(percent) * recordSize;
				// 向上取整，若分配数量超过剩下的审批单的数量，则只分配当前所有的审批单数量
				int count = (int) Math.ceil(size);// 分配数量
				count = count > surplusTemplSize ? surplusTemplSize : count;

				logger.info("模板ID:[" + tplId + "]配置的权重为[" + percent + "]-->>模板引擎分配至【" + tplId + "="
						+ tpl.getDecisionTreeName() + "】的记录数：" + count);
				// 分配后放入对象中
				if (templateMap.containsKey(tplId.toString())) {
					throw new RuntimeException("模板引擎分配决策树ID疑似重复！");
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
			if (templateMap.containsKey(defaultTreeTemplateId)) {
				throw new RuntimeException("模板引擎分配默认决策树ID疑似重复！");
			}
			templateMap.put(defaultTreeTemplateId, surplusTemplSize);
			logger.info("-->>模板引擎分配至【" + defaultTreeTemplateId + "=基础模板】，处理条数：" + surplusTemplSize);
		}
		logger.info("-->>模板引擎分配决策树，处理结束，共处理条数：" + recordSize);
		return templateMap;
	}
	
	/**
	 * 用于大数据决策树切换
	 * @param openDecisionTreeId
	 * @param closeDecisionTreeId
	 * @return
	 */
	@Transactional
	public boolean switchDecisionTree(int openDecisionTreeId, int closeDecisionTreeId) {
		ScDecisionTree sc = new ScDecisionTree();
		sc.setDecisionTreeId(openDecisionTreeId);
		sc.setState("01");// 有效
		int count1 = scDecisionTreeDao.updateEntity(sc);
		
		ScDecisionTree sc1 = new ScDecisionTree();
		sc1.setDecisionTreeId(closeDecisionTreeId);
		sc1.setState("02");// 失效
		int count2 = scDecisionTreeDao.updateEntity(sc1);
		if (count1 > 0 && count2 > 0) {
			sc = scDecisionTreeDao.getEntityById(sc);//查询刚刚打开的决策树
			scDecisionTreeHisDao.insert(sc);//保存记录到历史表中
			
			sc1 = scDecisionTreeDao.getEntityById(sc1);//查询刚刚关闭的决策树
			scDecisionTreeHisDao.insert(sc1);//保存记录到历史表中
			return true;
		} else {
			return false;
		}
	}
	

	public ScDecisionTree getEntityByDecisionTreeId(String decisionTreeId) {
		return scDecisionTreeDao.getEntityByDecisionTreeId(decisionTreeId);
	}
	
	
	@Transactional
	public boolean updateEntity(ScDecisionTree scDecisionTree) {
		boolean flag = false;
		scDecisionTree.setVersion(scDecisionTree.getVersion() + 1);// 修改是版本号自增1
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scDecisionTree.setModifyMan(user.getId().toString());
			scDecisionTree.setModifyName(user.getNickName());
		} else {
			scDecisionTree.setModifyMan("");
			scDecisionTree.setModifyName("");
		}
		scDecisionTree.setModifyTime(DateUtils.getDate(new Date()));

		ScDecisionTree scOld = scDecisionTreeDao.getEntityById(scDecisionTree);
		if (scOld == null) {
			throw new RuntimeException("要修改的记录不存在");
		}
		int temp = scDecisionTreeDao.updateEntity(scDecisionTree);
		ScDecisionTree scNew = scDecisionTreeDao.getEntityById(scDecisionTree);
		scDecisionTreeHisDao.insert(scNew);

		if (temp > 0) {
			flag = true;
		}
		return flag;
	}

	public double getSumTplPercent(String defaultTreeTemplateId, String decisionTreeId) {
		return scDecisionTreeDao.getSumTplPercent(defaultTreeTemplateId, decisionTreeId);
	}

	public int listAllCount(ScDecisionTree scDecisionTree) {
		int count = scDecisionTreeDao.listAllCount(scDecisionTree);
		return count;
	}

	public ScDecisionTree findCode(ScDecisionTree scDecisionTree) {
		return scDecisionTreeDao.findCode(scDecisionTree);
	}
	
	public List<ScDecisionTree> findAll(ScDecisionTree scDecisionTree) {
		return scDecisionTreeDao.findAll(scDecisionTree);
	}

	public ScDecisionTree getEntityById(ScDecisionTree scDecisionTree) {
		return scDecisionTreeDao.getEntityById(scDecisionTree);
	}
}
