package com.shangyong.backend.service;

import java.util.Map;

import com.shangyong.backend.bo.OperationDataBo;

public interface OperationDataService {
	
	public Map<String,Object> list(OperationDataBo operationData);

	public Map<String,Object> getSummaryData(OperationDataBo operationDataBo);

}
