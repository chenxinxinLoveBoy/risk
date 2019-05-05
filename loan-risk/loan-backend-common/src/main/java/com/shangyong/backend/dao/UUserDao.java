package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.ZTree;

@Mapper
public interface UUserDao {

	/**
	 * 统计
	 * 
	 * @param uUserBo
	 * @return
	 */
	public int listAllCount(UUserBo uUserBo);

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
	public Integer save(UUserBo uUserBo);

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
	 * 根据map查询
	 * 
	 * @param uUserBo
	 * @return
	 */
	public UUserBo selectByMap(Map map);

	/**
	 * 统计登录帐号
	 * 
	 * @param uUserBo
	 * @return
	 */
	public int getCountUserName(UUserBo uUserBo);
	
	
	/**
	 * 加载用户列表树
	 * @param user
	 * @return
	 */
	public List<ZTree> getUserTree(UUserBo user);
	
	/**
	 * 修改, 根据id,手机号
	 * 
	 * @param uUserBo
	 * @return
	 */
	public boolean updateByObject(UUserBo uUserBo);
	
	/**
	 * 禁用帐号
	 * @param uUserBo
	 * @return
	 */
	public boolean disable(UUserBo uUserBo);
	
	/**
	 * 冻结帐号
	 * @param uUserBo
	 * @return
	 */
	public boolean updateIsFreeze(UUserBo uUserBo);

}
