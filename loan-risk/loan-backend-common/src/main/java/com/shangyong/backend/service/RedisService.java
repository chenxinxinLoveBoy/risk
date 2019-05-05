package com.shangyong.backend.service;


public interface RedisService {
	
	/**
     * 根据用户名，查询用户信息放入redis
     *
     * @param username 用户名
     */
	public void setRedis(String username);
	
	/**
     * 根据用户名，查询用户信息 查redis
     *
     * @param username 用户名
     */
	public String getRedis(String username);

}
