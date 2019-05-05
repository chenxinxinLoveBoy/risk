package com.shangyong.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.ScDictSmallDao;
import com.shangyong.backend.entity.ScDictSmall;
import com.shangyong.backend.service.ScDictSmallService;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.RedisUtils;

@Service
public class ScDictSmallServiceImpl implements ScDictSmallService {
	
	@Autowired
	private ScDictSmallDao scDictSmallDao;

	@Override
	public int listAllCount(ScDictSmall scDictSmall) { 
		return scDictSmallDao.listAllCount(scDictSmall);
	}

	@Override
	public List<ScDictSmall> listViewAll(ScDictSmall scDictSmall) { 
		return scDictSmallDao.listViewAll(scDictSmall);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> findById(ScDictSmall scDictSmall) { 
		return scDictSmallDao.findById(scDictSmall);
	}

	@Override
	@Transactional
	public boolean update(ScDictSmall scDictSmall) {
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_DICT_SMALL_CODE, scDictSmall.getDicSmallCode(), SysParamUtils.ObjectToJson(scDictSmall), 31536000);

		// ken add to 2017/9/18 21:29 desc 去除字典MAP
		RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP);
		// ken add to 2017/9/18 21:29 desc 去除字典MAP
		RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_STATUS);
		return scDictSmallDao.update(scDictSmall);
	}

	@Override
	public int getCountDicSmsCodde(ScDictSmall scDictSmall) { 
		return scDictSmallDao.getCountDicSmsCodde(scDictSmall);
	}

	@Override
	@Transactional
	public boolean save(ScDictSmall scDictSmall) { 
		boolean falg = false;
		int temp = scDictSmallDao.save(scDictSmall);
		if(temp >0){ 
			falg = true;
			// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
			RedisUtils.hsetEx(RedisCons.RISK_DICT_SMALL_CODE, scDictSmall.getDicSmallCode(), SysParamUtils.ObjectToJson(scDictSmall), 31536000);
			// ken add to 2017/9/18 21:29 desc 去除字典MAP
			RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_MAP);
			RedisUtils.del(RedisCons.GET_RISK_DICT_SMALL_CODE_STATUS);
		}
		
		return falg;
	} 
	
	@Override
	public ScDictSmall getObjectById(ScDictSmall scDictSmall) { 
		return scDictSmallDao.getObjectById(scDictSmall);
	}
 
}
