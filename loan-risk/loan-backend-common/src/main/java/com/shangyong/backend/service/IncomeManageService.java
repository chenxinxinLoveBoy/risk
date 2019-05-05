package com.shangyong.backend.service;

import java.util.Map;

import com.shangyong.backend.bo.IncomeManageBo;

public interface IncomeManageService {
	
	public Map list(IncomeManageBo incomeManageBo);
	
	public Map getSummaryData(IncomeManageBo incomeManageBo);

}
