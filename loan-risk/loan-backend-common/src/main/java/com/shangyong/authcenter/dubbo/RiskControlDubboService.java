package com.shangyong.authcenter.dubbo;

import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

import com.shangyong.entity.BaseResult;

public interface RiskControlDubboService {

	BaseResult<T> increaseQuota(String quotaProportio);
	
	BaseResult<T> backExpTask(Map<String, Object> param);
}
