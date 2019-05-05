package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.SystemLogBo;

@Mapper
public interface SystemLogDao {

	/**
	 * 统计
	 * 
	 * @param uUserBo
	 * @return
	 */
	public int findAllCount(SystemLogBo systemLog);

	/**
	 * 查询所有List集合
	 * 
	 * @param menu
	 * @return
	 */
	public List<SystemLogBo> findAll(SystemLogBo systemLog);

	/**
	 * 保存
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean saveEntity(SystemLogBo systemLog);

	/**
	 * 统计角色名是否有重复
	 * 
	 * @param role
	 * @return
	 */
	public int findRoleNameAllCount(SystemLogBo systemLog);

	/**
	 * 查询对象信息
	 * 
	 * @param roleBo
	 * @return
	 */
	public SystemLogBo getEntityById(SystemLogBo systemLog);

	/**
	 * 修改信息
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean updateEntity(SystemLogBo systemLog);

	/**
	 * 删除
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean deleteEntity(SystemLogBo systemLog);

}
