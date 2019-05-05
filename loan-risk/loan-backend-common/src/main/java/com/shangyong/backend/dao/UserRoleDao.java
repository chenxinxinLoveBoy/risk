package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.UserRoleBo;

@Mapper
public interface UserRoleDao {
	
	/**
	 * 统计
	 * @param uUserBo
	 * @return
	 */
	public int findAllCount(UserRoleBo userRoleBo);
	
	/**
	 * 查询所有List集合
	 * @param menu
	 * @return
	 */
	public List<UserRoleBo> findAll(UserRoleBo userRoleBo);
	 
	/**
	 * 保存
	 * @param uUserBo
	 * @return
	 */
	public boolean saveEntity(UserRoleBo userRoleBo);
	
	/**
	 * 统计角色名是否有重复 
	 * @param role
	 * @return
	 */
	public int findRoleNameAllCount(UserRoleBo userRoleBo);
	
	/**
	 * 查询对象信息
	 * @param UserRoleBo
	 * @return
	 */
	public UserRoleBo getEntityById(UserRoleBo userRoleBo);
	
	/**
	 * 修改信息
	 * @param UserRoleBo
	 * @return
	 */
	public boolean updateEntity(UserRoleBo userRoleBo);
	
	/**
	 * 删除
	 * @param UserRoleBo
	 * @return
	 */
	public boolean deleteEntity(UserRoleBo userRoleBo);
	
	/**
	 * 根据user_id 查询当前用户的角色
	 * @param userRoleBoBo
	 * @return
	 */
	public UserRoleBo getEntityByUserId(UserRoleBo userRoleBo);
	
	/**
	 * 根据roleId删除
	 * @param UserRoleBo
	 * @return
	 */
	public boolean deleteById(Integer roleId);
	  
}
