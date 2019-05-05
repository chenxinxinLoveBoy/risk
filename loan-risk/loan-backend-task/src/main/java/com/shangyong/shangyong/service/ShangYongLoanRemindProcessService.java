
package com.shangyong.shangyong.service;

import com.shangyong.backend.entity.Application;

import java.util.List;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */
public interface ShangYongLoanRemindProcessService {
	
	/**
	 * 更新审批单模板信息
	 * @param list
	 */
	void process(List<Application> list);
}

