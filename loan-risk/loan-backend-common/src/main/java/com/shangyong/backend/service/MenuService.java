package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.bo.MenuBo;

public interface MenuService {
	
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
}
