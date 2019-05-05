package com.shangyong.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.enums.CalfResultEnum;
import com.shangyong.backend.dao.UserDao;
import com.shangyong.backend.entity.User;
import com.shangyong.backend.service.RedisService;
import com.shangyong.backend.utils.ExceptionUtils;
import com.shangyong.exception.CalfException;
import com.shangyong.utils.RedisUtils;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	
	@Override
	public void setRedis(String username) throws CalfException {
		/*User user = userDao.findByUsername(username);
		if (user == null) {
			ExceptionUtils.initCalfException(CalfResultEnum.FAIL_OBJECT_NULL);
		}
		redisUtils.set(username, user.toString());*/
		redisUtils.set(username,username);
	}

	@Override
	public String getRedis(String username) {
		return redisUtils.get(username);
	}

}
