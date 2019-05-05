package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScAppBigChannel;
import com.shangyong.backend.entity.ScAppSmallChannel;

public interface ScAppSmallChannelService {
	/**
	 * 查询当前大类下的所有小类
	 * @param bigName
	 * @return
	 */
	public List<ScAppSmallChannel> findAllByBigId(ScAppSmallChannel record);
	
	/**
	 * 统计
	 * @param record
	 * @return
	 */
	public int queryAllCountByBigId(ScAppSmallChannel record);
	
	/**
	 * 
	 * 添加
	 * 
	 **/
	public boolean saveEntity(ScAppSmallChannel record);
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public ScAppSmallChannel  getEntityById(String channelSmallId);
	
	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	public boolean updateEntity(ScAppSmallChannel record);
	
	/**
	 * 通过小类名称查询所属大类对象
	 * @param channelSmallName
	 * @return
	 */
	public ScAppBigChannel findObjBySmallName(String channelSmallName);
}
