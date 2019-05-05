package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.ScDictSmall;
/**
 * 数据字典小类
 * @author yin
 *
 */
public interface ScDictSmallService {

	public int listAllCount(ScDictSmall scDictSmall);

	public List<ScDictSmall> listViewAll(ScDictSmall scDictSmall);

	@SuppressWarnings("rawtypes")
	List<Map> findById(ScDictSmall scDictSmall);

	public boolean update(ScDictSmall scDictSmall);

	public int getCountDicSmsCodde(ScDictSmall scDictSmall);

	public boolean save(ScDictSmall scDictSmall);

	public ScDictSmall getObjectById(ScDictSmall scDictSmall);
 
}
