package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.UserRoleBo;
import com.shangyong.backend.dao.UserRoleDao;
import com.shangyong.backend.service.BaseService;

/**
 * @author xiajiyun
 *
 */
@Service
public class UserRoleServiceImpl implements BaseService<UserRoleBo> {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public List<UserRoleBo> findAll(UserRoleBo userRoleBo) {
		return userRoleDao.findAll(userRoleBo);
	}

	@Override
	public UserRoleBo getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleBo getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleBo getEntityById(UserRoleBo userRoleBo) {
		return userRoleDao.getEntityById(userRoleBo);
	}

	@Override
	public boolean saveEntity(UserRoleBo userRoleBo) {
		return userRoleDao.saveEntity(userRoleBo);
	}

	@Override
	public boolean updateEntity(UserRoleBo userRoleBo) {
		return userRoleDao.updateEntity(userRoleBo);
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(UserRoleBo userRoleBo) {
		return userRoleDao.deleteEntity(userRoleBo);
	}

	/**
	 * 统计
	 * @param uUserBo
	 * @return
	 */
	public int findAllCount(UserRoleBo role){
		return userRoleDao.findAllCount(role);
	}
	
	/**
	 * 统计角色名是否有重复 
	 * @param role
	 * @return
	 */
	public int findRoleNameAllCount(UserRoleBo role){
		return userRoleDao.findRoleNameAllCount(role);
	}
	
	
	/**
	 * 根据user_id 查询当前用户的角色
	 * @param userRoleBoBo
	 * @return
	 */
	public UserRoleBo getEntityByUserId(UserRoleBo userRoleBo){
		return userRoleDao.getEntityByUserId(userRoleBo);
	}
	
}
