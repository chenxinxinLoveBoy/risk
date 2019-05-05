package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.dao.FaceRecognitionScoreDao;
import com.shangyong.backend.entity.FaceRecognitionScore;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * @author xk
 *
 */
@Service
public class FaceRecognitionScoreServiceImpl implements BaseService<FaceRecognitionScore> {

	@Autowired
	private FaceRecognitionScoreDao faceRecognitionScoreDao;

	@Override
	public FaceRecognitionScore getEntityById(String platformCustomerId) {
		return faceRecognitionScoreDao.getEntityById(platformCustomerId);
	}

	@Override
	@Transactional
	public boolean saveEntity(FaceRecognitionScore faceRecognitionScore) {
		faceRecognitionScore.setAuthenticationNumber(UUIDUtils.getUUID());
		faceRecognitionScore.setModifyTime(DateUtils.getDate(new Date()));
		return faceRecognitionScoreDao.saveEntity(faceRecognitionScore);
	}

	@Override
	@Transactional
	public boolean updateEntity(FaceRecognitionScore faceRecognitionScore) {
		faceRecognitionScore.setModifyTime(DateUtils.getDate(new Date()));
		return faceRecognitionScoreDao.updateEntity(faceRecognitionScore);
	}

	@Override
	public List<FaceRecognitionScore> findAll(FaceRecognitionScore t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FaceRecognitionScore getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(FaceRecognitionScore t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FaceRecognitionScore getEntityById(FaceRecognitionScore t) {
		// TODO Auto-generated method stub
		return null;
	}

}
