package com.shangyong.backend.service.xczx;

import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.xczx.PkgHeader;

public interface XczxCallBackService {

	/**
	 * 91征信回调接口数据同步服务
	 */
	public void dataSynchronized(PkgHeader reqPkg) throws  Exception;
	
	
	/**
	 * 根据对象条件查询申请单扩展表数据
	 * @param obj
	 * @return
	 */
	public BuThirdpartyReport getInfoByObj(BuThirdpartyReport obj);
	
	
}