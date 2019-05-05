package com.shangyong.backend.service.checkapply;

import java.util.Map;

/**
 * cu_customer_check_apply表的service
 * @author xiajiyun
 *
 */
public interface CuCustomerCheckApplyService {
	
	/**
	 * add xiajiyun
	 * 根据id查询对象信息
	 */
	public int getEntityById(Map<String, String> map);
	
	/**
	 * add xiajiyun
	 * 修改对象信息
	 * @param map
	 * @return
	 */
	public boolean updateEntity(Map<String, String> map);
}
