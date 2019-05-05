package com.shangyong.backend.dao.xczx;
/**
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.xczx.XczxApplicationData;

@Mapper
public interface XczxApplicationDataDao {
	
	/**
	 * 保存用户91数据报告
	 * @param list
	 * @return
	 */
	int saveEntitys(List<XczxApplicationData> list);
	
	List<XczxApplicationData> getDataInfo(XczxApplicationData xczxApplicationData);
	
	int findAllCount(XczxApplicationData xczxApplicationData);
}
