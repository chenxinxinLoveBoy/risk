package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.approval.CuCustomerCallRecord;

/**
 * CuCustomerCallRecordDao(客户手机通话记录表)数据库操作接口类bean
 * @author hxf
 * @date Thu Aug 24 16:31:50 CST 2017
 **/

@Mapper
public interface CuCustomerCallRecordDao{

	/**
	 * 
	 * 添加(批量)
	 * 
	 **/
	int saveAllEntity(@Param("list") List<CuCustomerCallRecord> record);
}