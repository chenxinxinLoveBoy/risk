package com.shangyong.backend.service;

import java.util.List;

import com.shangyong.backend.entity.ScScoreCredit;

public interface ScoreCreditServiceHis {

	/**
	 * 统计
	 * @param scoreCredit
	 * @return
	 */
	public int listAllCount(ScScoreCredit scoreCredit);

	/**
	 * 查询所有
	 * @param scoreCredit
	 * @return
	 */
	public List<ScScoreCredit> listAll(ScScoreCredit scoreCredit);

}
