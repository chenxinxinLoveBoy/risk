package com.shangyong.backend.dao.approval;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.FaceRecognitionScore;

/**
 * FaceRecognitionScoreDao数据库操作接口类bean
 * 
 * @date Thu Aug 03 13:49:44 CST 2017
 **/
@Mapper
public interface ApproveFaceRecognitionScoreDao {

	/**
	 * 
	 * 查询 （根据用户编号查询）
	 * 
	 **/
	FaceRecognitionScore getEntityById(String platformCustomerId);

}