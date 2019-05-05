package com.shangyong.backend.service.impl;

import java.util.ArrayList;
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
import com.shangyong.backend.dao.ScFraudRuleTplDao;
import com.shangyong.backend.dao.ScFraudRuleTplHisDao;
import com.shangyong.backend.entity.ScFraudRuleTpl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.ValidTemplateData;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

/**
 * 欺诈项模版service实现
 * 
 * @author hc
 *
 */
@Service
public class ScFraudRuleTplServiceImpl implements BaseService<ScFraudRuleTpl> {

	Logger logger = LoggerFactory.getLogger(ScFraudRuleTplServiceImpl.class);

	@Autowired
	private ScFraudRuleTplDao scFraudRuleTplDao;

	@Autowired
	private ScFraudRuleTplHisDao scFraudRuleTplHisDao;

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	@Override
	public List<ScFraudRuleTpl> findAll(ScFraudRuleTpl scFraudRuleTpl) {
		return scFraudRuleTplDao.findAll(scFraudRuleTpl);
	}

	@Override
	public ScFraudRuleTpl getEntityById(ScFraudRuleTpl scFraudRuleTpl) {
		return scFraudRuleTplDao.getEntityById(scFraudRuleTpl);
	}

	@Override
	@Transactional
	public boolean saveEntity(ScFraudRuleTpl scFraudRuleTpl) {
		boolean flag = false;
		scFraudRuleTpl.setLevel(0);//弃用字段，默认给0
		scFraudRuleTpl.setTplPercent("0");//弃用字段，默认给0
		scFraudRuleTpl.setVersion(1);
		scFraudRuleTpl.setCreateTime(DateUtils.getDate(new Date()));
		scFraudRuleTpl.setModifyTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRuleTpl.setModifyMan(user.getId().toString());
			scFraudRuleTpl.setModifyName(user.getNickName());
			scFraudRuleTpl.setCreateMan(user.getId().toString());
			scFraudRuleTpl.setCreateName(user.getNickName());
		} else {
			scFraudRuleTpl.setModifyMan("");
			scFraudRuleTpl.setModifyName("");
			scFraudRuleTpl.setCreateMan("");
			scFraudRuleTpl.setCreateName("");
		}

		int count = scFraudRuleTplDao.saveEntity(scFraudRuleTpl);
		scFraudRuleTplHisDao.insert(scFraudRuleTpl);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScFraudRuleTpl scFraudRuleTpl) {
		boolean flag = false;

		scFraudRuleTpl.setVersion(scFraudRuleTpl.getVersion() + 1);// 修改是版本号自增1
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRuleTpl.setModifyMan(user.getId().toString());
			scFraudRuleTpl.setModifyName(user.getNickName());
		} else {
			scFraudRuleTpl.setModifyMan("");
			scFraudRuleTpl.setModifyName("");
		}
		scFraudRuleTpl.setModifyTime(DateUtils.getDate(new Date()));

		ScFraudRuleTpl scOld = scFraudRuleTplDao.getEntityById(scFraudRuleTpl);
		if (scOld == null) {
			throw new RuntimeException("要修改的记录不存在");
		}
		scFraudRuleTpl.setLevel(0);//弃用字段，默认给0
		scFraudRuleTpl.setTplPercent("0");//弃用字段，默认给0
		int temp = scFraudRuleTplDao.updateEntity(scFraudRuleTpl);
		ScFraudRuleTpl scNew = scFraudRuleTplDao.getEntityById(scFraudRuleTpl);
		scFraudRuleTplHisDao.insert(scNew);

		if (temp > 0) {
			flag = true;
		}
		return flag;
	}

	public double getSumTplPercent(String defaultFraudTemplateId,String fraudRuleTplId) {
		return scFraudRuleTplDao.getSumTplPercent(defaultFraudTemplateId,fraudRuleTplId);
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	public int listAllCount(ScFraudRuleTpl scFraudRuleTpl) {
		int count = scFraudRuleTplDao.listAllCount(scFraudRuleTpl);
		return count;
	}

	public ScFraudRuleTpl findCode(ScFraudRuleTpl scFraudRuleTpl) {
		return scFraudRuleTplDao.findCode(scFraudRuleTpl);

	}

	/**
	 * 获取当前有效模板
	 * 
	 * @return map结构 { "templIds" : list //模板ID集合 "percents" : list //对应比例
	 *         "templSize"： list //占比条数 "recordSize" : Integer //总记录数 }
	 */
	public List<ValidTemplateData> getFraudRuleTpl(Integer recordSize) {
		if (recordSize <= 0)
			return null;
		String default_fraud_template_id = "1";
		try {
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
			default_fraud_template_id = sysParam.getParamValueOne(); // 默认模板ID
		} catch (Exception e) {
			logger.info("-->>get default_ban_template_id from sysparamsredis error", e);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("default_fraud_template_id", default_fraud_template_id);
		List<ScFraudRuleTpl> list = scFraudRuleTplDao.getFraudRuleTpl(params); // 当前有效模板
		List<ValidTemplateData> tempDataList = new ArrayList<ValidTemplateData>();
		
		double surplusPercent = 100;//剩余比例
		double residueRecordSize = recordSize;
		ValidTemplateData tempData = null;
		if (CollectionUtils.isNotEmpty(list)) {
			int len = list.size();
			for (int i = 0; i < len; i++) {
				ScFraudRuleTpl tpl = list.get(i);
				tempData = new ValidTemplateData();
				double per = Double.parseDouble(tpl.getTplPercent());
				tempData.setTemplateId(tpl.getFraudRuleTplId());
				int tempSize = 0;
				if (surplusPercent > per) {
					tempSize = (int)Math.floor(recordSize * per / 100);
					tempData.setTemplateSize(tempSize);
					tempData.setPercent(per);
				} else if (surplusPercent > 0) {
					tempData.setPercent(surplusPercent);
					tempData.setTemplateSize((int)residueRecordSize);
				} else {
					break;
				}
				residueRecordSize = residueRecordSize - tempSize;
				surplusPercent = surplusPercent - per;
				tempDataList.add(tempData);
			}
		}

		if (surplusPercent > 0) { // 模板比例不足 默认模板补充
			tempData = new ValidTemplateData();
			tempData.setTemplateId(default_fraud_template_id);
			tempData.setPercent(surplusPercent);
			tempData.setTemplateSize((int)residueRecordSize);
			tempDataList.add(tempData);
		}
		return tempDataList;
	}

	/**
	 * 获取目标分配结果
	 * @param recordSize
	 * @return { "001":10, "002":20}
	 */
	public Map<String,Object> getFraudRuleTmpl(Integer recordSize) {
	    logger.info("-->>模板引擎分配欺诈项模板，处理开始，共处理条数：" + recordSize);

		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
		String defaultFraudTemplateId = sysParam.getParamValueOne(); //默认模板ID
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("defaultFraudTemplateId", defaultFraudTemplateId);
		List<ScFraudRuleTpl> list = scFraudRuleTplDao.getFraudRuleTpl(params); //当前有效模板
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
 
		int surplusTemplSize = recordSize; //剩余记录条数
		if (CollectionUtils.isNotEmpty(list)) {
			//循环有效欺诈项模板
		    for (int i=0; i<list.size(); i++) {
		    	//根据单个模板设定的权重计算处理条数
		    	ScFraudRuleTpl tpl = list.get(i);
		    	String tplId = tpl.getFraudRuleTplId();
		    	String percent = tpl.getTplPercent();
		    	//根据权重和数据总条数计算单个模板需处理的记录数
		    	Double size = Double.valueOf(percent) * recordSize;
		    	//向上取整，若分配数量超过剩下的审批单的数量，则只分配当前所有的审批单数量
		    	int count = (int)Math.ceil(size);//分配数量
		    	count = count > surplusTemplSize ? surplusTemplSize : count;
		    	
			    logger.info("模板ID:[" + tplId + "]配置的权重为[" + percent + "]-->>模板引擎分配至【"+ tplId+"="+tpl.getFraudRuleTplName()+"】的记录数：" + count);
			    //分配后放入对象中
			    if(templateMap.containsKey(tplId.toString())){
			    	throw new RuntimeException("模板引擎分配欺诈项模板ID疑似重复！");
			    }
			    templateMap.put(tplId.toString(), count);
			    
			   //消费已分配的size，如果小于等于0，处理结束 
		    	surplusTemplSize = surplusTemplSize - count;
		    	 logger.info("剩余记录条数：" + surplusTemplSize);
		    	if(surplusTemplSize<=0){
		    		break;
		    	}
		    }
		}
		//未分配完的申请单走默认模板处理
		if(surplusTemplSize>0){
		    if(templateMap.containsKey(defaultFraudTemplateId)){
		    	throw new RuntimeException("模板引擎分配默认欺诈项模板ID疑似重复！");
		    }
		    templateMap.put(defaultFraudTemplateId, surplusTemplSize);
		    logger.info("-->>模板引擎分配至【"+ defaultFraudTemplateId+"=基础模板】，处理条数：" + surplusTemplSize);
		}
	    logger.info("-->>模板引擎分配欺诈项模板，处理结束，共处理条数：" + recordSize);
		return templateMap;
	}
	
	@Override
	public ScFraudRuleTpl getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRuleTpl getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScFraudRuleTpl t) {
		// TODO Auto-generated method stub
		return false;
	}

}
