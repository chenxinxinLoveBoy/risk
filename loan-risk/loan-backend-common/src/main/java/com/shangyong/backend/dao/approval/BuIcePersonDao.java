package com.shangyong.backend.dao.approval;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.approval.BuIcePerson;

/**
 * BuIcePersonDao数据库操作接口类bean
 * @author hxf
 * @date Sun Aug 13 15:23:15 CST 2017
 **/
@Mapper
public interface BuIcePersonDao{


	/**
	 * 
	 * 查询所有的催收号码
	 * 
	 **/
	List<BuIcePerson>  findAllEntity();
}