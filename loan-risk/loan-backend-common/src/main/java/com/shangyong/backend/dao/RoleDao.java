package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.RoleBo;
import com.shangyong.backend.bo.ZTree;

@Mapper
public interface RoleDao {

	/**
	 * 统计
	 * 
	 * @param uUserBo
	 * @return
	 */
	public int findAllCount(RoleBo role);

	/**
	 * 查询所有List集合
	 * 
	 * @param menu
	 * @return
	 */
	public List<RoleBo> findAll(RoleBo role);

	/**
	 * 保存
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean saveEntity(RoleBo role);

	/**
	 * 统计角色名是否有重复
	 * 
	 * @param role
	 * @return
	 */
	public int findRoleNameAllCount(RoleBo role);

	/**
	 * 查询对象信息
	 * 
	 * @param roleBo
	 * @return
	 */
	public RoleBo getEntityById(RoleBo roleBo);

	/**
	 * 修改信息
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean updateEntity(RoleBo roleBo);

	/**
	 * 删除
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean deleteEntity(RoleBo roleBo);

	/**
	 * 加载树菜单
	 * 
	 * @param menu
	 * @return
	 */
	public List<ZTree> getTree(RoleBo roleBo);

	/**
	 * 保存树所有选择的节点
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean saveTree(Map<String, Object> map);

	/**
	 * 删除角色拥有的菜单
	 * 
	 * @param roleBo
	 * @return
	 */
	public boolean deleteTree(RoleBo roleBo);

}
