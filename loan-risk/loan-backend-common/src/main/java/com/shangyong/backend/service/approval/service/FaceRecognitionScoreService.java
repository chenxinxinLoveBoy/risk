package com.shangyong.backend.service.approval.service;

import com.shangyong.backend.entity.FaceRecognitionScore;

/**
 * 客户人脸识别评分
 * @author hxf
 * @date 2017/8/10
 **/
public interface FaceRecognitionScoreService {

	/**通过平台用户编号查询客户人脸识别评分**/
	public FaceRecognitionScore findFaceRecognitionScore(String platformCustomerId);
}
