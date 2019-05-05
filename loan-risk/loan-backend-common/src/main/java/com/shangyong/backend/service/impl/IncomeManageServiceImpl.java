package com.shangyong.backend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.IncomeMaSummaryBo;
import com.shangyong.backend.bo.IncomeManageBo;
import com.shangyong.backend.dao.BuBoOrderformDao;
import com.shangyong.backend.service.IncomeManageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JsonUtil;
import com.shangyong.exception.CalfException;

/**
 * 
 * 平台收益管理业务处理
 * @author zhouhl
 *
 */
@Service
public class IncomeManageServiceImpl implements IncomeManageService	 {
	
	private Logger logger = LoggerFactory.getLogger(IncomeManageServiceImpl.class);

	@Autowired
	private BuBoOrderformDao buBoOrderformDao;
	
	@Override
	public Map list(IncomeManageBo incomeManageBo) {
		String appChannel = incomeManageBo.getAppChannel();
		String calcUnit = incomeManageBo.getCalcUnit();
		String startTime = incomeManageBo.getStartTime();
		String endTime = incomeManageBo.getEndTime();
		
		if (StringUtils.isBlank(calcUnit)) {
			logger.info("-->>收益列表查询 缺少参数， calcUnit is empty");
			throw new CalfException(CodeUtils.BACKEND_PRA_MISS.getCode(), CodeUtils.BACKEND_PRA_MISS.getMessage());
		}
		
		Map par = new HashMap();
		if(StringUtils.isNotBlank(appChannel)) par.put("appChannel", appChannel);
		if(StringUtils.isNotBlank(calcUnit)) par.put("calcUnit", calcUnit);
		if(StringUtils.isNotBlank(startTime)) par.put("startTime", startTime);
		if(StringUtils.isNotBlank(endTime)) par.put("endTime", endTime);
		par.put("pageIndex", incomeManageBo.getPageIndex());
		par.put("pageSize", incomeManageBo.getPageSize());

		Map resultMap = new HashMap();
		
		Integer count = buBoOrderformDao.incomeManageCount(par);
		List<IncomeManageBo> list = new ArrayList<IncomeManageBo>();
		
		if (count > 0) {
			List<Map> mapList = buBoOrderformDao.incomeManageList(par); //列表数据
			for (Map m : mapList) {
				IncomeManageBo incomeManage = new IncomeManageBo();
				if(m.get("loanSumMoney") != null) incomeManage.setLoanSumMoney(((BigDecimal)m.get("loanSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("hasRepaySumMoney") != null) incomeManage.setHasRepaySumMoney(((BigDecimal)m.get("hasRepaySumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("overdueSumMoney") != null) incomeManage.setOverdueSumMoney(((BigDecimal)m.get("overdueSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("sumLoanInterest") != null) incomeManage.setSumLoanInterest(((BigDecimal)m.get("sumLoanInterest")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("managementCost") != null) incomeManage.setManagementCost(((BigDecimal)m.get("managementCost")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("inquiryFee") != null) incomeManage.setInquiryFee(((BigDecimal)m.get("inquiryFee")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				incomeManage.setServiceSumMoney((new BigDecimal(incomeManage.getSumLoanInterest())
						.add(new BigDecimal(incomeManage.getManagementCost())).add(new BigDecimal(incomeManage.getInquiryFee()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString()); //合计服务费（元）
				if(m.get("sumDelayMoney") != null) incomeManage.setSumDelayMoney(((BigDecimal)m.get("sumDelayMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("sumRenewalMoney") != null) incomeManage.setSumRenewalMoney(((BigDecimal)m.get("sumRenewalMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("realIncomeProfit") != null) incomeManage.setRealIncomeProfit(((BigDecimal)m.get("realIncomeProfit")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("shouldIncomeProfit") != null) incomeManage.setShouldIncomeProfit(((BigDecimal)m.get("shouldIncomeProfit")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				incomeManage.setSumProfit((new BigDecimal(incomeManage.getRealIncomeProfit()).add(new BigDecimal(incomeManage.getShouldIncomeProfit()))).toString()); //利润总额
				if(m.get("startTime") != null) incomeManage.setStartTime(m.get("startTime").toString());
				if(m.get("endTime") != null) incomeManage.setEndTime(m.get("endTime").toString());
				list.add(incomeManage);
			}
		}
		resultMap.put("count", count);
		resultMap.put("list", list);
		logger.info("-->>resultMap:" + JsonUtil.toJson(resultMap));
		return resultMap;
	}
	
	@Override
	public Map getSummaryData(IncomeManageBo incomeManageBo) {
		String appChannel = incomeManageBo.getAppChannel();
		String calcUnit = incomeManageBo.getCalcUnit();
		String startTime = incomeManageBo.getStartTime();
		String endTime = incomeManageBo.getEndTime();
		
		if (StringUtils.isBlank(calcUnit)) {
			logger.info("-->>收益列表查询 缺少参数， calcUnit is empty");
			throw new CalfException(CodeUtils.BACKEND_PRA_MISS.getCode(), CodeUtils.BACKEND_PRA_MISS.getMessage());
		}
		
		Map<String,Object> par = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(appChannel)) par.put("appChannel", appChannel);
		if(StringUtils.isNotBlank(calcUnit)) par.put("calcUnit", calcUnit);
		if(StringUtils.isNotBlank(startTime)) par.put("startTime", startTime);
		if(StringUtils.isNotBlank(endTime)) par.put("endTime", endTime);
		par.put("pageIndex", incomeManageBo.getPageIndex());
		par.put("pageSize", incomeManageBo.getPageSize());

		Map<String,Object> resultMap = new HashMap<String,Object>();
		//列表上面的统计数据
		List summaryDataList = buBoOrderformDao.incomeManageSummaryData(par);
		if (summaryDataList != null && summaryDataList.size() > 0 && summaryDataList.get(0) != null) {
			IncomeMaSummaryBo incomeMaSummaryBo = this.installIncomeMaSummaryBo((Map)summaryDataList.get(0));
			resultMap.put("incomeMaSummaryBo", incomeMaSummaryBo);
		} else {
			resultMap.put("incomeMaSummaryBo", new IncomeMaSummaryBo());
		}
		logger.info("-->>incomemanagerserviceimpl getSummaryData resultMap:" +resultMap);
		return resultMap;
	}
	
	private IncomeMaSummaryBo installIncomeMaSummaryBo(Map map) {
		IncomeMaSummaryBo incomeMaSummaryBo = new IncomeMaSummaryBo();
		if(map.get("loanSumMoney") != null) incomeMaSummaryBo.setLoanSumMoney(((BigDecimal)map.get("loanSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		if(map.get("overdueSumMoney") != null) incomeMaSummaryBo.setOverdueSumMoney(((BigDecimal)map.get("overdueSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		if(map.get("realIncomeProfit") != null) incomeMaSummaryBo.setRealIncomeProfit(((BigDecimal)map.get("realIncomeProfit")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		if(map.get("hasRepaySumMoney") != null) incomeMaSummaryBo.setHasRepaySumMoney(((BigDecimal)map.get("hasRepaySumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		if(map.get("shouldIncomeProfit") != null) incomeMaSummaryBo.setShouldIncomeProfit(((BigDecimal)map.get("shouldIncomeProfit")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		incomeMaSummaryBo.setSumProfit((new BigDecimal(incomeMaSummaryBo.getRealIncomeProfit()).add(new BigDecimal(incomeMaSummaryBo.getShouldIncomeProfit()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		if(map.get("serviceSumMoney") != null) incomeMaSummaryBo.setServiceSumMoney(((BigDecimal)map.get("serviceSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		return incomeMaSummaryBo;
	}
	
}
