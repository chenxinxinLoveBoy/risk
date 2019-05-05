package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.ScDictBig;

/**
 * 数据字典大类
 * @author yin
 *
 */
public interface ScDictBigService {

	public int listAllCount();

	public List<ScDictBig> listViewAll(ScDictBig scDictBig);

	@SuppressWarnings("rawtypes")
	List<Map> findById(Integer dictBigId);

	public boolean update(ScDictBig scDictBig);

	public int getCountDicBigCode(ScDictBig scDictBig);

	public boolean save(ScDictBig scDictBig);

	public ScDictBig getObjectById(ScDictBig scDictBig);

	public boolean delete(ScDictBig scDictBig);
 
 

}
