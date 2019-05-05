package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.User;

public interface UserService {

	/**
     * 根据用户名，查询用户信息
     *
     * @param username 用户名
     */
	public User findByUserame(String username);
	
	/**
     * 查询所有用户信息
     */
	public List<User> getAllUser();
	
	/**
	 * 新增用户信息
	 * @param username
	 * @param pwd
	 * @param email
	 * @return
	 */
	public User saveUser(String username, String pwd, String email);
}
