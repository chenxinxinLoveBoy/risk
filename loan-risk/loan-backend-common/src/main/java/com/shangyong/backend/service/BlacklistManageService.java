package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.BuBlacklistManage;

public interface BlacklistManageService {

	/**
	 * 
	 * 添加
	 * 
	 **/
	public int saveEntity(BuBlacklistManage record);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<BuBlacklistManage> listAll(BuBlacklistManage buBlacklistManage);

	/**
	 * 
	 * 统计所有
	 * 
	 **/
	public int listAllSum(BuBlacklistManage buBlacklistManage);
}
