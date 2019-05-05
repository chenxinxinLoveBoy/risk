package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

/**
 * 缓存管理service
 * @author lyc
 *
 */
public interface RedisManageService {
	
	public List<String> getList(String key);
	
	public Map<String,String> hgetAll(String key);
	
	public String getString(String key);
}
