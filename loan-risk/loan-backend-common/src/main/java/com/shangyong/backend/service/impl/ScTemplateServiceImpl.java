package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScBanControlTplDao;
import com.shangyong.backend.dao.ScBanControlTplHisDao;
import com.shangyong.backend.entity.ScBanControlTpl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils; 

/**
 * 禁止项模版service实现
 * @author hc
 *
 */
@Service
public class ScTemplateServiceImpl implements BaseService<ScBanControlTpl> {
	
	Logger logger = LoggerFactory.getLogger(ScTemplateServiceImpl.class);
	
	@Autowired
	private ScBanControlTplDao scBanControlTplDao;
	
	@Autowired
	private ScBanControlTplHisDao scBanControlTplHisDao;
	
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	
	@Override
	public List<ScBanControlTpl> findAll(ScBanControlTpl scBanControlTpl) { 
		return scBanControlTplDao.findAll(scBanControlTpl);
	}
 
	@Override
	public ScBanControlTpl getEntityById(String id) {
		try {
			return getEntityById(Integer.parseInt(id));
		} catch (Exception e) {
			return null;
		}
	}
  

	@Override
	public ScBanControlTpl getEntityById(Integer id) { 
		return scBanControlTplDao.getEntityById(id);
	}

	@Override
	public ScBanControlTpl getEntityById(ScBanControlTpl scBanControlTpl) { 
		return scBanControlTplDao.getEntityById(scBanControlTpl);
	}

	@Override
	@Transactional
	public boolean saveEntity(ScBanControlTpl scBanControlTpl) { 
		boolean flag = false;
		scBanControlTpl.setCreateMan(TokenManager.getUser().getCreateMan());
		scBanControlTpl.setVersion(1);
		scBanControlTpl.setCreateTime(DateUtils.getDate(new Date()));
		scBanControlTpl.setModifyTime(DateUtils.getDate(new Date()));
		String operation = scBanControlTpl.getOperation();
		if( StringUtils.isBlank(operation) ){
			scBanControlTpl.setOperation("1");//操作标志 默认 1 为 手动 2 为自动 
		}
		scBanControlTpl.setLevel(0);//弃用字段，默认给0
		scBanControlTpl.setTplPercent("0");//弃用字段，默认给0
		int count = scBanControlTplDao.saveEntity(scBanControlTpl);
		scBanControlTplHisDao.insert(scBanControlTpl);
		if(count > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScBanControlTpl scBanControlTpl) {
		boolean falg = false;
		scBanControlTpl.setModifyTime(DateUtils.getDate(new Date()));
		scBanControlTpl.setLevel(0);//弃用字段，默认给0
		scBanControlTpl.setTplPercent("0");//弃用字段，默认给0
		int temp = scBanControlTplDao.updateEntity(scBanControlTpl);
		ScBanControlTpl infonew = scBanControlTplDao.getEntityById(scBanControlTpl); 
		scBanControlTplHisDao.insert(infonew);
		if(temp > 0){
			falg = true;
		}
		return falg;
	}

	@Override
	public boolean deleteEntity(String id) { 
		return false;
	}

	@Override
	public boolean deleteEntity(ScBanControlTpl scBanControlTpl) { 
		return false;
	}

	public int listAllCount(ScBanControlTpl scBanControlTpl) { 
		int count = scBanControlTplDao.listAllCount(scBanControlTpl);
		return count;
	}

	public ScBanControlTpl findCode(ScBanControlTpl scBanControlTpl) {
		return scBanControlTplDao.findCode(scBanControlTpl); 
		
	}

	/**
	 * 获取目标分配结果
	 * @param recordSize
	 * @return { "001":10, "002":20}
	 */
	public Map<String,Object> getBanCodeTmpl(Integer recordSize) {
	    logger.info("-->>模板引擎分配禁止项模板，处理开始，共处理条数：" + recordSize);

		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String defaultBanTemplateId = sysParam.getParamValueOne(); //默认模板ID
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("default_ban_template_id", defaultBanTemplateId);
		List<ScBanControlTpl> list = scBanControlTplDao.getValidTmpl(params); //当前有效模板
		
		Map<String, Object> templateMap = new HashMap<String, Object>();
 
		int surplusTemplSize = recordSize; //剩余记录条数
		if (CollectionUtils.isNotEmpty(list)) {
			//循环有效禁止项模板
		    for (int i=0; i<list.size(); i++) {
		    	//根据单个模板设定的权重计算处理条数
		    	ScBanControlTpl tpl = list.get(i);
		    	Integer tplId = tpl.getBanControlTplId();
		    	String percent = tpl.getTplPercent();
		    	//根据权重和数据总条数计算单个模板需处理的记录数
		    	Double size = Double.valueOf(percent) * recordSize;
		    	//向上取整，若分配数量超过剩下的审批单的数量，则只分配当前所有的审批单数量
		    	int count = (int)Math.ceil(size);//分配数量
		    	count = count > surplusTemplSize ? surplusTemplSize : count;
		    	
			    logger.info("模板ID:[" + tplId + "]配置的权重为[" + percent + "]-->>模板引擎分配至【"+ tplId+"="+tpl.getBanTplName()+"】的记录数：" + count);
			    //分配后放入对象中
			    if(templateMap.containsKey(tplId.toString())){
			    	throw new RuntimeException("模板引擎分配禁止项模板ID疑似重复！");
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
		    if(templateMap.containsKey(defaultBanTemplateId)){
		    	throw new RuntimeException("模板引擎分配默认禁止项模板ID疑似重复！");
		    }
		    templateMap.put(defaultBanTemplateId, surplusTemplSize);
		    logger.info("-->>模板引擎分配至【"+ defaultBanTemplateId+"=基础模板】，处理条数：" + surplusTemplSize);
		}
	    logger.info("-->>模板引擎分配禁止项模板，处理结束，共处理条数：" + recordSize);
		return templateMap;
	}

	/**
	 * 查询生效状态模板的百分比
	 * @param id 需要排除的模板id
	 * @return
	 */
	public double queryNormalPercent(String id){
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String defaultBanTemplateId = sysParam.getParamValueOne(); //默认模板ID

		return this.scBanControlTplDao.queryNormalPercent(id, defaultBanTemplateId);
	}
}
