
package com.shangyong.shangyong.service;

import java.util.List;
import java.util.Map;

/**
 * @Description
 *
 * @author lz
 *
 * @date 2018年7月25日  
 *
 */
public interface NineOneCreditApplicationProcessService {

	/**
	 * 处理在91征信步骤查询的待审核申请单List
	 * @param list
	 */
	void process(List<Map<String, Object>> list);
}

