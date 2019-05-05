package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.MenuBo;

@Mapper
public interface MenuDao {
	
	/**
	 * 统计
	 * @param uUserBo
	 * @return
	 */
	public int listAllCount(MenuBo menu);
	
	/**
	 * 查询所有List集合
	 * @param menu
	 * @return
	 */
	public List<MenuBo> listAll(MenuBo menu);
	
	/**
	 * 页面列表List
	 * @param menu
	 * @return
	 */
	public List<MenuBo> listViewAll(MenuBo menu);
	
	
	/**
	 * 保存
	 * @param uUserBo
	 * @return
	 */
	public boolean save(MenuBo menu);
	
	/**
	 * 删除
	 * @param uUserBo
	 * @return
	 */
	public boolean delete(MenuBo menu);
	
	
	/**
	 * 根据id获取对象信息
	 * @param uUserBo
	 * @return
	 */
	public MenuBo getObjectById(MenuBo menu);
	
	/**
	 * 修改
	 * @param uUserBo
	 * @return
	 */
	public boolean update(MenuBo menu);
	
	/**
	 * 获取一级菜单
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getMenu1(MenuBo menu);
	
	/**
	 * 获取左边的2/3级菜单
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getLeftMenu(MenuBo menu);
	
	/**
	 * 查询每个菜单对应的角色权限，用于权限初始化用
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getMenuRoleAll(MenuBo menu);
	
	/**
	 * 查询当前位置是否存在表
	 * @param rownum
	 * @return
	 */
	public Integer selectLocationExist(Integer rownum);
	
	
	/**
	 * 
	 * @param rownum
	 * @return
	 */
	public Integer updateLocationRownum(Integer rownum);
	
	
	/**
	 * 当前菜单最大条数
	 * @param rownum
	 * @return
	 */
	public Integer selectMenuMaximum(Integer rownum);
	
	/**
	 * 根据菜单名称得到序号
	 * @param menuName
	 * @return
	 */
	public Integer selectMenuSerialNumber(String menuName);
}
