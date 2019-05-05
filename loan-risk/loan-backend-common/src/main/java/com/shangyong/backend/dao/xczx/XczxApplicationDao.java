package com.shangyong.backend.dao.xczx;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.xczx.XczxApplication;

@Mapper
public interface  XczxApplicationDao {
	/**
	 * 
	 * @param xczxApplication 
	 * @return
	 */
	public List<XczxApplication> queryBackApplication(XczxApplication xczxApplication);
}
