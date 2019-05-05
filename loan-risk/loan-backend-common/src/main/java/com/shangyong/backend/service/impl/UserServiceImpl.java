package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.UserDao;
import com.shangyong.backend.entity.User;
import com.shangyong.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/*
     * 根据用户名，查询用户信息
     * @param username 用户名
     */
	@Override
	public User findByUserame(String username) {
		return userDao.findByUsername(username);
	}
	
	/**
     * 查询所有用户信息
     */
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
	/**
	 * 新增用户信息
	 * @param username
	 * @param pwd
	 * @param email
	 * @return
	 */
	@Transactional
	public User saveUser(String username, String pwd, String email) {
		userDao.saveUser(username, pwd, email);
//		int c = 1/0;
		return userDao.findByUsername(username);
	}

}
