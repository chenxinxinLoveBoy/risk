package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.BuPromoteDetailed;

public interface BuPromoteDetailedService {
	/**
	 * 
	 * 查询所有
	 * 
	 **/
	public List<BuPromoteDetailed>  findAllByObj(BuPromoteDetailed buPromoteDetailed);
	
	/**
	 * 统计
	 * @return
	 */
	public int queryAllCount(BuPromoteDetailed buPromoteDetailed);
	
	
	public boolean pushMqMsg(String[] promoteDetailedId);
	
	
	/**
	 * add: xiajiyun
	 * 查询所有id
	 * 
	 **/
	public Map<String, Object>  findAllId(BuPromoteDetailed buPromoteDetailed);
	
	/**
	 * 
	 * 查询所有异常单子
	 * 
	 **/
	public List<BuPromoteDetailed>  findAllByIsError(BuPromoteDetailed buPromoteDetailed);

	/**
	 * 根据taskType customerId 查询信息
	 * @param buPromoteDetailed
	 * @return
	 */
	public String getJosnStoragePathBytaskTypeCustomerId(BuPromoteDetailed buPromoteDetailed);
	
	
}
