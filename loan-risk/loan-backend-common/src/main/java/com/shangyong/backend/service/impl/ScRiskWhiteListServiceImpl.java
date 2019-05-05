package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.ScRiskWhiteListDao;
import com.shangyong.backend.entity.ScRiskWhiteList;
import com.shangyong.backend.service.ScRiskWhiteListService;
import com.shangyong.backend.utils.DateUtils;
/**
 * 白名单Redis相关service实现
 * @author hc
 *
 */
@Service
public class ScRiskWhiteListServiceImpl implements ScRiskWhiteListService {
	
	@Autowired
	private ScRiskWhiteListDao scRiskWhiteListDao;

	/**
	 * 获取所有白名单信息
	 */
	@Override
	public List<ScRiskWhiteList> findAll(ScRiskWhiteList scRiskWhiteList) { 
		return scRiskWhiteListDao.findAll(scRiskWhiteList);
	}
	/**
	 * 通过id获取对象信息
	 */
	@Override
	public  ScRiskWhiteList findByid(ScRiskWhiteList scRiskWhiteList) { 
		return scRiskWhiteListDao.findByid(scRiskWhiteList);
	}
	/**
	 * 统计总数
	 */
	@Override
	public int findAllCount(ScRiskWhiteList scRiskWhiteList) { 
		int count = scRiskWhiteListDao.findAllCount(scRiskWhiteList);
		return count;
	}

	/**
	 * 根据对象查询是否在白名单 - 返回数量
	 */
	@Override
	public int queryWhiteCount(ScRiskWhiteList scRiskWhiteList) { 
		int count = scRiskWhiteListDao.queryWhiteCount(scRiskWhiteList);
		return count;
	}
	
	
	/***
	 * 根据id更新对象信息
	 */
	@Override
	@Transactional
	public boolean updateByPrimaryKeySelective(ScRiskWhiteList scRiskWhiteList) {  
		scRiskWhiteList.setModifyTime(DateUtils.getDate(new Date()));
		boolean temp = scRiskWhiteListDao.updateByPrimaryKeySelective(scRiskWhiteList);
		if(temp){
			// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
//			RedisUtils.hsetEx(RedisCons.RISK_SC_WHITE_CERCODE, scRiskWhiteList.getCertCode()+"_"+scRiskWhiteList.getAppName(), SysParamUtils.ObjectToJson(scRiskWhiteList));
			return temp;	
		}
		return false;
	}

	/**
	 * 保存对象信息
	 */
	@Override
	@Transactional
	public boolean save(ScRiskWhiteList scRiskWhiteList) { 
		boolean flag = false; 
		scRiskWhiteList.setCreateTime(DateUtils.getDate(new Date())); 
		scRiskWhiteList.setModifyTime(DateUtils.getDate(new Date()));
		int count = scRiskWhiteListDao.saveEntity(scRiskWhiteList);
		if(count > 0){
			// 白名单  -->更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
//			RedisUtils.hsetEx(RedisCons.RISK_SC_WHITE_CERCODE, scRiskWhiteList.getCertCode()+"_"+scRiskWhiteList.getAppName(), SysParamUtils.ObjectToJson(scRiskWhiteList));
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据身份证AppName获取对象信息
	 */ 
	@Override
	public ScRiskWhiteList findByCodeAppName(ScRiskWhiteList scRiskWhiteList) { 
		return scRiskWhiteListDao.findByCodeAppName(scRiskWhiteList);
	}
	/**
	 * 根据whiteListId删除白名单用户
	 */
	@Override
	public boolean deleteById(ScRiskWhiteList scRiskWhiteList) {
		Integer whiteListId = scRiskWhiteList.getWhiteListId();
		return scRiskWhiteListDao.deleteById(whiteListId);
	} 
}
