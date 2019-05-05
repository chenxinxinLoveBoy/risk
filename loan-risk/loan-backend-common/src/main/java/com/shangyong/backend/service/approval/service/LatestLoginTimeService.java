package com.shangyong.backend.service.approval.service;

import java.util.List;

import com.shangyong.backend.entity.CuCustomerExpand;

/**
 * app应用列表
 * @author hxf
 * @date 2017/8/15
 **/
public interface LatestLoginTimeService {

	/**查询500条备注为null的数据**/
	public List<CuCustomerExpand> findListEntity();
	
	/**更新备注为1**/
	public boolean updateEntity(CuCustomerExpand customerExpand);
}
