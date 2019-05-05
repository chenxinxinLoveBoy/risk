package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.User;

/**
 * 用户DAO接口类
 * @author zhangze
 *
 */
@Mapper
public interface UserDao {
	/**
     * 根据用户名，查询用户信息
     *
     * @param username 用户名
     */
    User findByUsername(@Param("username") String username);
    
    /**
     * 查询所有用户信息
     */
	public List<User> getAllUser();
	
	/**
	 * 新增用户信息
	 * @param username
	 * @param pwd
	 * @param email
	 */
	public int saveUser(@Param("username") String username, @Param("pwd") String pwd, @Param("email") String email);
}
