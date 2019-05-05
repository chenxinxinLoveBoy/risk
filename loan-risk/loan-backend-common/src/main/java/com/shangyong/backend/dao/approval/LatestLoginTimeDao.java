package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.CuCustomerExpand;

/**
 * AppInfoDao(客户手机应用列表记录)数据库操作接口类bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
@Mapper
public interface LatestLoginTimeDao{

	/**
	 * 查询500条备注为null的数据
	 * @return
	 */
	List<CuCustomerExpand>  findListEntity();
	
	/**
	 * 更新备注为1
	 * @return
	 */
	boolean updateEntity(CuCustomerExpand cuCustomerExpand);
}