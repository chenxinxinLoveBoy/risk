package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScAppBigChannel;

public interface ScAppBigChannelService {
	/**
	 * 根据大类名称查询
	 * @param bigName
	 * @return
	 */
	public List<ScAppBigChannel> findAllByBigName(ScAppBigChannel record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	public int queryAllCountByBigName(ScAppBigChannel record);
	
	/**
	 * 
	 * 添加
	 * 
	 **/
	public boolean saveEntity(ScAppBigChannel record);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public ScAppBigChannel  getEntityById(String channelBigId);
	
	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	public boolean updateEntity(ScAppBigChannel record);
}
