package com.shangyong.backend.service.xczx.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.xczx.XczxQueryUserLogDao;
import com.shangyong.backend.entity.xczx.XczxQueryUserLog;
import com.shangyong.backend.service.xczx.XczxQueryUserLogService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

@Service
public class XczxQueryUserLogServiceImpl implements XczxQueryUserLogService{

	@SuppressWarnings("unused")
	@Autowired
	private XczxQueryUserLogDao dao;
	
	@Override
	public void saveEntity(String realName,String idCard,String type) {
		
		//先保存redis中，日终然后将数据保存mysql，避免外部接口调用，一直刷数据库
		//redis 文件夹 SHARED_REDIS_CODE
		
		String redisKey = Constants.SHARED_REDIS_CODE + ":" + idCard;
		
		XczxQueryUserLog log = new XczxQueryUserLog();
		
		String dataStr = DateUtils.getDate(new Date());
		
		log.setApplicationQueryId(UUIDUtils.getUUID());
		log.setRealName(realName);
		log.setIdCard(idCard);
		log.setCreateTime(dataStr);
		log.setModifyTime(dataStr);
		log.setType(type);
		
		//如果存在相同数据，删除原来的，保留最新一条
		if(RedisUtils.exists(redisKey)){
			RedisUtils.del(redisKey);
		}
		RedisUtils.set(redisKey,JSONObject.fromObject(log).toString());
		dao.saveEntity(log);
	}

}
