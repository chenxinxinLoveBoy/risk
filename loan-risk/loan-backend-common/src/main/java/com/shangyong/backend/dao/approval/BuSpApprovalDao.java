package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.approval.BuSpApproval;

 
/**
 * BuSpApprovalDao数据库操作接口类bean
 * 
 * @date Thu Aug 03 13:49:44 CST 2017
 **/
@Mapper
public interface BuSpApprovalDao {
	
	/***
	 * 查询所有操作日志
	 * @return
	 */
	public List<BuSpApproval> getBuSpApprovalList(BuSpApproval buSpApproval);
	
	/**
	 * 统计
	 * @return
	 */
	public int listAllCount(BuSpApproval buSpApproval);
	
	/**
	 * 根据id查询
	 * @param buSpApproval
	 * @return
	 */
	public BuSpApproval getEntityById(BuSpApproval buSpApproval);
	 
	/**
	 * 
	 * 添加
	 * 
	 **/
	boolean saveEntity(BuSpApproval buSpApproval);
	
	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(BuSpApproval record);
}
