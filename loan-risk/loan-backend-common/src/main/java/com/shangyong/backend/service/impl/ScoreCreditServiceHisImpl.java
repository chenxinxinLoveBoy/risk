package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScScoreCreditHisDao;
import com.shangyong.backend.entity.ScScoreCredit;
import com.shangyong.backend.service.ScoreCreditServiceHis;

@Service
public class ScoreCreditServiceHisImpl implements ScoreCreditServiceHis {

	@Autowired
	private ScScoreCreditHisDao scScoreCreditHisDao;
	
	@Override
	public int listAllCount(ScScoreCredit scoreCredit) {
		return scScoreCreditHisDao.listAllCount(scoreCredit);
	}

	@Override
	public List<ScScoreCredit> listAll(ScScoreCredit scoreCredit) {
		return scScoreCreditHisDao.listAll(scoreCredit);
	}

}
