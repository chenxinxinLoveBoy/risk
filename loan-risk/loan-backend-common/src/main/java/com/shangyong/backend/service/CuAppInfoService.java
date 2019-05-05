package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.CuAppInfo;

public interface CuAppInfoService {
	//查询对象信息
	public List<CuAppInfo> getEntityById(CuAppInfo cuAppInfo);
	//统计
	public int listAllCount(CuAppInfo cuAppInfo);
}
