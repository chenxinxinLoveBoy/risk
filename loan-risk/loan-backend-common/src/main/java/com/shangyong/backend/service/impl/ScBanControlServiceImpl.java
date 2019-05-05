package com.shangyong.backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScBanControlDao;
import com.shangyong.backend.dao.ScBanControlHisDao;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;
import org.springframework.util.CollectionUtils;

@Service
public class ScBanControlServiceImpl implements BaseService<ScBanControl> {
 
	@Autowired
	private ScBanControlDao scBanControlDao;
	@Autowired
	private ScBanControlHisDao scBanControlHisDao;
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	public List<ScBanControl> queryByBanCode(String banCode) {
		return scBanControlDao.queryByBanCode(banCode);
}

	public ScBanControl queryByBanCodeAndId(String banCode,String defaultFraudTemplateId) {
		return scBanControlDao.queryByBanCodeAndId(banCode,defaultFraudTemplateId);
	}
	
	public ScBanControl queryByBanCodeAndTplId(String banCode) {
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String banControlTplId = sysParam.getParamValueOne(); //默认模板ID

		return scBanControlDao.queryByBanCodeAndTplId(banControlTplId, banCode);
	}

	
	@Override
	public List<ScBanControl> findAll(ScBanControl scBanControl) {
		return scBanControlDao.getAll(scBanControl);
	}

	@Override
	public ScBanControl getEntityById(String banControlId) { 
		return null;
	}

	@Override
	public ScBanControl getEntityById(Integer banControlId) {
		return scBanControlDao.selectByPrimaryKey(banControlId);
	}

	@Override
	public ScBanControl getEntityById(ScBanControl scBanControl) { 
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScBanControl scBanControl) {
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String banControlTplId = sysParam.getParamValueOne(); //默认模板ID

		scBanControl.setBanControlTplId(banControlTplId);
		scBanControl.setVersion(1);// 新增时默认为1
		scBanControl.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scBanControl.setCreateMan(user.getId().toString());
			scBanControl.setCreateName(user.getNickName());
		} else {
			scBanControl.setCreateMan("");
			scBanControl.setCreateName("");
		}
		scBanControl.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = scBanControlDao.insertSelective(scBanControl);
		scBanControlHisDao.saveEntity(scBanControl);
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_BAN_CONTROL, banControlTplId+"_"+scBanControl.getBanCode(),
				SysParamUtils.ObjectToJson(scBanControl), 31536000);
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScBanControl scBanControl) {
		String banControlTplId = scBanControl.getBanControlTplId();
		scBanControl.setModifyTime(DateUtils.getDate(new Date()));
		//scBanControl.setVersion(scBanControl.getVersion() + 1);// 修改是版本号自增1
		ScBanControl scBanOld= scBanControlDao.selectByPrimaryKey(scBanControl.getBanControlId());
		
		if(scBanOld == null){
			throw new RuntimeException("要修改的记录不存在");
		}
		
		boolean flag = scBanControlDao.updateByPrimaryKeySelective(scBanControl);
		Date date = new Date();
		String date2 = DateUtils.getDate(date);
		scBanOld.setModifyTime(date2);
		//保存修该之前的记录
		scBanControlHisDao.saveEntity(scBanOld);

		String banCode=scBanOld.getBanCode();

		//查询最新数据
		List<ScBanControl> list = scBanControlDao.queryByBanCode(banCode);

		ScBanControl scBanNew = scBanControlDao.selectByPrimaryKey(scBanControl.getBanControlId());
		if(!CollectionUtils.isEmpty(list) || list.size() > 1){
			for(ScBanControl elem : list){
				if (elem.getBanControlId().longValue() != scBanNew.getBanControlId().longValue()){
                    //先保存之前的记录
				    scBanControlHisDao.saveEntity(elem);

					//更新其他相同 banCode 的记录
					elem.setRuleName(scBanNew.getRuleName());
					elem.setCreditType(scBanNew.getCreditType());
					elem.setRuleDetail(scBanNew.getRuleDetail());
					elem.setRuleComparisonValue(scBanNew.getRuleComparisonValue());
					elem.setRuleComparisonType(scBanNew.getRuleComparisonType());
					elem.setIfRefuse(scBanNew.getIfRefuse());
					elem.setModifyTime(scBanNew.getModifyTime());
					elem.setModifyMan(scBanNew.getModifyMan());
					elem.setModifyName(scBanNew.getModifyName());
					elem.setRemark(scBanNew.getRemark());
					scBanControlDao.updateByPrimaryKeySelective(elem);
				}
			}
		}

		//判断redis中key是否存在，存在便删除该key
		if(RedisUtils.hexists(RedisCons.RISK_SC_BAN_CONTROL, banControlTplId+"_"+banCode)){
			RedisUtils.hdelEx(RedisCons.RISK_SC_BAN_CONTROL,banControlTplId+"_"+ banCode);
		}

		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SC_BAN_CONTROL,banControlTplId+"_"+scBanNew.getBanCode(), SysParamUtils.ObjectToJson(scBanNew),31536000);

		return flag;
	}

	@Override
	public boolean deleteEntity(String banControlId) { 
		return false;
	}

	@Override
	public boolean deleteEntity(ScBanControl scBanControl) { 
		return false;
	}

	public int listAllCount(ScBanControl scBanControl) {
		return scBanControlDao.listAllCount(scBanControl);
	}

	public List<ScBanControl> getAll(ScBanControl scBanControl) {
		return scBanControlDao.queryAll(scBanControl);
	}

	@Transactional
	public boolean saveEntityBatch(ScBanControl scBanControl) {
		boolean flag =false; 
		List<ScBanControl> scBanControlList = new ArrayList<ScBanControl>(); 
		if(scBanControl != null &&  scBanControl.getIds().length > 0){
			for (int i = 0; i < scBanControl.getIds().length; i++) {//循环所勾选的多条或一条禁止项
				if(StringUtils.isNotBlank(scBanControl.getIds()[i])){ 
					String sinfo = scBanControl.getIds()[i];  
					ScBanControl info =scBanControlDao.selectByPrimaryKey(Integer.parseInt(sinfo));//根据禁止项查询对应信息 
					info.setBanControlTplId(scBanControl.getBanControlTplId()); //模板id 
					scBanControlList.add(info); 
					scBanControlHisDao.saveEntity(info);
				}
			} 
			int count  = scBanControlDao.saveListEntity(scBanControlList);
			if(count > 0 ){
				flag = true;
			} 
			return flag;
		}else{
			return flag;
		} 
	}

	/**
	 * banControlTplId 当前模板id
	 * defaultTemplates 默认模板
	 * @param scBanControl
	 * @return
	 */
	public int listAllTemplateCount(ScBanControl scBanControl) {  
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String banControlTplId = sysParam.getParamValueOne(); //默认模板ID

		Map<String,Object>  map = new HashMap<String, Object>();
		map.put("defaultTemplates", banControlTplId);//默认模板
		map.put("banControlTplId", scBanControl.getBanControlTplId());//当前模板id
		map.put("banCode", scBanControl.getBanCode());
		map.put("ruleName", scBanControl.getRuleName());
		map.put("creditType", scBanControl.getCreditType());
		map.put("state", scBanControl.getState());
		map.put("ifRefuse", scBanControl.getIfRefuse());
		return scBanControlDao.listAllTemplateCount(map);
	}
	/**
	 * banControlTplId 当前模板id
	 * defaultTemplates 默认模板
	 * pageIndex 分页参数
	 * pageSize	分页参数
	 * @param scBanControl
	 * @return
	 */
	public List<ScBanControl> findAllTemplate(ScBanControl scBanControl) {  
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME);
		String banControlTplId = sysParam.getParamValueOne(); //默认模板ID

		Map<String,Object>  map = new HashMap<String, Object>();
		map.put("defaultTemplates", banControlTplId);//默认模板
		map.put("banControlTplId", scBanControl.getBanControlTplId());//当前模板id
		map.put("banCode", scBanControl.getBanCode());
		map.put("ruleName", scBanControl.getRuleName());
		map.put("creditType", scBanControl.getCreditType());
		map.put("state", scBanControl.getState());
		map.put("ifRefuse", scBanControl.getIfRefuse());
		map.put("banControlTplId", scBanControl.getBanControlTplId());//当前模板id
		return scBanControlDao.findAllTemplate(map);
	}

	/**
	 * 统计当前模板下关联的禁止项
	 * @param scBanControl
	 * @return
	 */
	public int listTemplateCount(ScBanControl scBanControl) { 
		int temp = scBanControlDao.listTemplateCount(scBanControl);
		return temp;
	}

	/**
	 * 查询当前模板下关联的禁止项 分页
	 * @param scBanControl
	 * @return
	 */
	public List<ScBanControl> findTemplate(ScBanControl scBanControl) { 
		List<ScBanControl> info = scBanControlDao.findTemplate(scBanControl);
		return info;
	}
	
}
