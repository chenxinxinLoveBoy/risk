package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.approval.TdPlatformCheck;

@Mapper
public interface TdPlatformCheckDao {
	
	public List<TdPlatformCheck> getListById(String applicationId);
	
}
