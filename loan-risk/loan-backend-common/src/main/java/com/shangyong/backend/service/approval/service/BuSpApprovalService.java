package com.shangyong.backend.service.approval.service;

import java.util.List;
import com.shangyong.backend.entity.approval.BuSpApproval;

public interface BuSpApprovalService {
	/***
	 * 查询所有操作日志
	 * @param id
	 * @return
	 */
	public List<BuSpApproval> getBuSpApprovalList(BuSpApproval buSpApproval);
	/**
	 * 统计
	 * @return
	 */
	public int listAllCount(BuSpApproval buSpApproval);
	
	public BuSpApproval getEntityById(BuSpApproval buSpApproval);

	public boolean saveEntity(BuSpApproval buSpApproval);
	
	public boolean updateEntity(BuSpApproval buSpApproval);
	/**
	 * 添加备注
	 * @param buSpApproval
	 * @return
	 */
	public boolean saveRgAuditingRemark(BuSpApproval buSpApproval);

}
