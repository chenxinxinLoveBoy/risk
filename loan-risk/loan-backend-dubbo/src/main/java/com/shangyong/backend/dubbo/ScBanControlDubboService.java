package com.shangyong.backend.dubbo;

import com.shangyong.backend.entity.ScBanControl;

import java.util.List;

public interface ScBanControlDubboService {

	/**
	 * 查询（根据禁止项规则对应编号查询）
	 * 
	 * @param banCode
	 * @return
	 */

	public ScBanControl queryByBanCode(String banCode);

	/**
	 * 根据日期查询当天修改的禁止项规则的所有历史记录
	 * 
	 * @param date--2017-08-30
	 * @return
	 */
	public List<ScBanControl> getAll(String date);
}
