package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.bo.UUserBo;

public interface UUserService {

	/**
	 * 查询所有List集合
	 * 
	 * @param menu
	 * @return
	 */
	public List<UUserBo> listAll(UUserBo uUserBo);

	/**
	 * 保存
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean save(UUserBo uUserBo);

	/**
	 * 删除
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean delete(UUserBo uUserBo);

	/**
	 * 根据id获取对象信息
	 * 
	 * @param uUserBo
	 * @return
	 */
	public UUserBo getObjectById(UUserBo uUserBo);

	/**
	 * 修改
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean update(UUserBo uUserBo);

	/**
	 * 删除多个
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean deleteAll(UUserBo uUserBo);

	/**
	 * 统计
	 * 
	 * @param uUserBo
	 * @return
	 */
	public int listAllCount(UUserBo uUserBo);
	
	
	/**
	 * 禁用帐号
	 * @param uUserBo
	 * @return
	 */
	public boolean disable(UUserBo uUserBo);
	
}
