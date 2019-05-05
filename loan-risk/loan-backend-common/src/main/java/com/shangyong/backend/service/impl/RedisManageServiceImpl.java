package com.shangyong.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangyong.backend.service.RedisManageService;
import com.shangyong.utils.RedisUtils;

@Service
public class RedisManageServiceImpl implements RedisManageService {

	
	@Override
	public List<String> getList(String key) {
		long lenth=RedisUtils.llen(key);//获取list长度
		List<String> list=RedisUtils.lrange(key, 0, lenth-1);
		return list;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Map<String,String> map=RedisUtils.hgetAll(key);
		return map;
	}

	@Override
	public String getString(String key) {
		return RedisUtils.get(key);
	}

}
