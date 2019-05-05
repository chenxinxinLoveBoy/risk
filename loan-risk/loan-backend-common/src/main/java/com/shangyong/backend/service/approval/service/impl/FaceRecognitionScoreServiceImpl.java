package com.shangyong.backend.service.approval.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.approval.ApproveFaceRecognitionScoreDao;
import com.shangyong.backend.entity.FaceRecognitionScore;
import com.shangyong.backend.service.approval.service.FaceRecognitionScoreService;

@Service
public class FaceRecognitionScoreServiceImpl implements FaceRecognitionScoreService {

	@Autowired
	private ApproveFaceRecognitionScoreDao approveFaceRecognitionScoreDao;

	@Override
	public FaceRecognitionScore findFaceRecognitionScore(String platformCustomerId) {
		return approveFaceRecognitionScoreDao.getEntityById(platformCustomerId);
	}
}
