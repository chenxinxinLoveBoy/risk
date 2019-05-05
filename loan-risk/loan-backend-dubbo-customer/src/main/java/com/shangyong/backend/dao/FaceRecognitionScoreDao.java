package com.shangyong.backend.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.FaceRecognitionScore;

/**
 * FaceRecognitionScoreDao数据库操作接口类bean
 * 
 * @date Thu Aug 03 13:49:44 CST 2017
 **/
@Mapper
public interface FaceRecognitionScoreDao {

	FaceRecognitionScore getEntityById(String platformCustomerId);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	boolean saveEntity(FaceRecognitionScore faceRecognitionScore);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	boolean updateEntity(FaceRecognitionScore faceRecognitionScore);

}