package com.shangyong.backend.service;

import java.util.List;

/**
 * 所有service实现类的公共接口
 * @author xiajiyun
 *
 * @param <T>
 */
public interface BaseService<T> {

	/**
	 * 查询所有
	 * @return
	 */
	public List<T> findAll(T t);
	
	/**
	 * 根据主键id获取对象
	 * @param id
	 * @return
	 */
	public T getEntityById(String id);
	
	/**
	 * 根据主键id获取对象
	 * @param id
	 * @return
	 */
	public T getEntityById(Integer id);
	
	/**
	 * 根据多个条件获取对象
	 * @param id
	 * @return
	 */
	public T getEntityById(T t);
	
	/**
	 * 保存对象信息
	 * @param t
	 * @return
	 */
	public boolean saveEntity(T t);
	
	/**
	 * 修改对象信息
	 * @param t
	 * @return
	 */
	public boolean updateEntity(T t);
	
	/**
	 * 根据id删除对象信息（逻辑删除）
	 * @param id
	 * @return
	 */
	public boolean deleteEntity(String id);
	
	/**
	 * 删除对象信息（物理删除）
	 * @param t
	 * @return
	 */
	public boolean deleteEntity(T t);
}
