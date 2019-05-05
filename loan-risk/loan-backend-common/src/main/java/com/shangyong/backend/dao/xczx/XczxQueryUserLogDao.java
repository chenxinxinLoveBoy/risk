package com.shangyong.backend.dao.xczx;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.xczx.XczxQueryUserLog;

@Mapper
public interface XczxQueryUserLogDao {

	
	int saveEntity(XczxQueryUserLog obj);
}
