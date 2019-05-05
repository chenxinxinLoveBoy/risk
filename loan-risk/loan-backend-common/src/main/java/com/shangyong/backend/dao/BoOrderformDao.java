package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.BoOrderform;

/**
 * 订单信息数据库操作接口类
 * @author xixinghui
 *
 */
@Mapper
public interface BoOrderformDao {
	
	/**
	 * 统计查询数据量
	 * @param t    订单信息entity对象
	 * @return
	 */
	public List<BoOrderform> findAll(BoOrderform t);

	public BoOrderform getEntityById(BoOrderform t);

	/**
	 * 查询所有订单信息
	 * @param t    订单信息entity对象
	 * @return
	 */
	public int findAllCount(BoOrderform t);
}
