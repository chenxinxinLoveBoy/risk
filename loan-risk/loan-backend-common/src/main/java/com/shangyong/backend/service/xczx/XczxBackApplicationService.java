package com.shangyong.backend.service.xczx;

import java.util.List;

import com.shangyong.backend.entity.xczx.XczxApplication;

public interface XczxBackApplicationService {
	/**
	 * 根据对象条件查询申请单扩展表数据
	 * @param obj
	 * @return
	 */
	public List<XczxApplication> queryBackApplication(XczxApplication obj);
	
	
}