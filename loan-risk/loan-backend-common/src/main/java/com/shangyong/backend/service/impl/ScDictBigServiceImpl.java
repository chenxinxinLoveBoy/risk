package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScDictBigDao;
import com.shangyong.backend.entity.ScDictBig;
import com.shangyong.backend.service.ScDictBigService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

@Service
public class ScDictBigServiceImpl implements ScDictBigService {
	
	@Autowired
	private ScDictBigDao scDictBigDao;
 
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> findById(Integer scDictBig) {
		return scDictBigDao.getAll(scDictBig);
	}

	@Override
	public int listAllCount() { 
		int temp = scDictBigDao.listAllCount();
		return temp;
	}

	@Override
	public List<ScDictBig> listViewAll(ScDictBig scDictBig) {
		return scDictBigDao.listViewAll(scDictBig);
	}

	@Override
	@Transactional
	public boolean update(ScDictBig scDictBig) { 
		scDictBig.setModifyTime(DateUtils.getDate(new Date()));
		scDictBig.setModifyMan(TokenManager.getUser().getModifyMan()); 
		boolean	flag = scDictBigDao.update(scDictBig);
		if(flag){
			RedisUtils.hsetEx(RedisCons.RISK_DICT_BIG_CODE, scDictBig.getDicBigCode(), SysParamUtils.ObjectToJson(scDictBig), 31536000);
			// ken add to 2017/9/18 21:29 desc 去除字典MAP
			RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP);
		} 
		return flag;
	}
 

	@Override
	@Transactional
	public boolean save(ScDictBig scDictBig) {
		boolean falg = false; 
		scDictBig.setCreateMan(TokenManager.getUser().getCreateMan()); 
		scDictBig.setCreateTime(DateUtils.getDate(new Date())); 
		scDictBig.setVersion(1);
		int count = scDictBigDao.save(scDictBig);
		if(count >0){
			falg = true;
			// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
			RedisUtils.hsetEx(RedisCons.RISK_DICT_BIG_CODE, scDictBig.getDicBigCode(), SysParamUtils.ObjectToJson(scDictBig), 31536000);
			// ken add to 2017/9/18 21:29 desc 去除字典MAP
			RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP);
		}
		return falg;
	}

	@Override
	public ScDictBig getObjectById(ScDictBig scDictBig) {
		return scDictBigDao.getObjectById(scDictBig);
	}

	@Override
	public boolean delete(ScDictBig scDictBig) {
		return scDictBigDao.delete(scDictBig);
	}

	@Override
	public int getCountDicBigCode(ScDictBig scDictBig) { 
		return scDictBigDao.getCountDicBigCode(scDictBig);
	}


}
