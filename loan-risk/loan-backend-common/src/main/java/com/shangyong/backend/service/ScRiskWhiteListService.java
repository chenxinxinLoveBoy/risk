package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScRiskWhiteList;
/**
 * 风控白名单service
 * @author yin
 *
 */
public interface ScRiskWhiteListService {
	
	/**
	 * 查询所有风控白名单
	 * @param scRiskWhiteList
	 * @return
	 */
	public List<ScRiskWhiteList> findAll(ScRiskWhiteList scRiskWhiteList);
	
	/**
	 * 根据id查询风控白名单
	 * @param scRiskWhiteList
	 * @return
	 */
	public  ScRiskWhiteList  findByid(ScRiskWhiteList scRiskWhiteList);
	
	/**
	 * 统计
	 * @param scRiskWhiteList
	 * @return
	 */
	public int findAllCount(ScRiskWhiteList scRiskWhiteList);
	 
	/**
	 * 更新风控白名单客户表bean
	 * @param scRiskWhiteList
	 * @return
	 */
	public boolean updateByPrimaryKeySelective(ScRiskWhiteList scRiskWhiteList); 
	
	/**
	 * 添加风控白名单客户表
	 * @param scRiskWhiteList
	 * @return
	 */
	public boolean save(ScRiskWhiteList scRiskWhiteList); 

	/**
	 * 根据对象查询是否在白名单 - 返回数量
	 * @param scRiskWhiteList
	 * @return
	 */
	int queryWhiteCount(ScRiskWhiteList scRiskWhiteList);

	
	public ScRiskWhiteList findByCodeAppName(ScRiskWhiteList scRiskWhiteList);
	
	/**
	 *删除风控白名单
	 * @param scRiskWhiteList
	 * @return
	 */
	public boolean  deleteById(ScRiskWhiteList scRiskWhiteList);
}
