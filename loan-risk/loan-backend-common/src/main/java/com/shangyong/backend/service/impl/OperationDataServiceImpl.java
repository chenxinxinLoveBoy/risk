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

import com.shangyong.backend.bo.OperationDataBo;
import com.shangyong.backend.bo.OperationDataSummaryBo;
import com.shangyong.backend.dao.BuBoOrderformDao;
import com.shangyong.backend.service.OperationDataService;
import com.shangyong.backend.utils.JsonUtil;

/**
 * 
 * 平台运营数据业务处理
 * @author zhouhl
 */
@Service
public class OperationDataServiceImpl implements OperationDataService {
	
	private Logger logger = LoggerFactory.getLogger(OperationDataServiceImpl.class);
	
	@Autowired
	private BuBoOrderformDao buBoOrderformDao;

	@Override
	public Map list(OperationDataBo operationDataBo) {
		String appChannel = operationDataBo.getAppChannel();
		String calcUnit = operationDataBo.getCalcUnit();
		String startTime = operationDataBo.getStartTime();
		String endTime = operationDataBo.getEndTime();
		
		Map<String,Object> par = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(appChannel)) par.put("appChannel", appChannel);
		if(StringUtils.isNotBlank(calcUnit)) par.put("calcUnit", calcUnit);
		if(StringUtils.isNotBlank(startTime)) par.put("startTime", startTime);
		if(StringUtils.isNotBlank(endTime)) par.put("endTime", endTime);
		par.put("pageIndex", operationDataBo.getPageIndex());
		par.put("pageSize", operationDataBo.getPageSize());

		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<OperationDataBo> list = new ArrayList<OperationDataBo>();
		Integer count = buBoOrderformDao.optDataListCount(par); //总数
		if (count != null && count > 0) {
			List<Map> mapList = buBoOrderformDao.optDataList(par);
			for (Map m : mapList) {
				OperationDataBo optDataBo = new OperationDataBo();
				optDataBo.setStartTime((String)m.get("startTime"));
				optDataBo.setEndTime((String)m.get("endTime"));
				optDataBo.setNewRegUserNum((Integer)m.get("newRegUserNum"));
				if(m.get("loanSumMoney")!=null) optDataBo.setLoanSumMoney(((BigDecimal)m.get("loanSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("applayNum") != null) optDataBo.setApplayNum(((Long)m.get("applayNum")).intValue());
				if(m.get("loanSumCount")!=null) optDataBo.setLoanSumCount(((Long)m.get("loanSumCount")).intValue());
				if(optDataBo.getLoanSumCount()!=null && optDataBo.getApplayNum() != 0) optDataBo.setLoanRate(new BigDecimal(optDataBo.getLoanSumCount()).divide(new BigDecimal(optDataBo.getApplayNum()), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");	
				if(m.get("overdueSumMoney") != null) optDataBo.setOverdueSumMoney(((BigDecimal)m.get("overdueSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				if(m.get("m0OverdueCount")!=null) optDataBo.setM0OverdueCount(((BigDecimal)m.get("m0OverdueCount")).intValue());
				if(m.get("m1OverdueCount")!=null) optDataBo.setM1OverdueCount(((BigDecimal)m.get("m1OverdueCount")).intValue());
				if(m.get("m2OverdueCount")!=null) optDataBo.setM2OverdueCount(((BigDecimal)m.get("m2OverdueCount")).intValue());
				if(m.get("overdueCount") != null && optDataBo.getApplayNum() != 0) 
					optDataBo.setLoanBadRate(((BigDecimal)m.get("overdueCount")).divide(new BigDecimal(optDataBo.getApplayNum()), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)) + "%");
				list.add(optDataBo);
			}
		}
		resultMap.put("count", count);
		resultMap.put("list", list);
		return resultMap;
	}

	@Override
	public Map getSummaryData(OperationDataBo operationDataBo) {
		String appChannel = operationDataBo.getAppChannel();
		String calcUnit = operationDataBo.getCalcUnit();
		String startTime = operationDataBo.getStartTime();
		String endTime = operationDataBo.getEndTime();
		
		Map<String,Object> par = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(appChannel)) par.put("appChannel", appChannel);
		if(StringUtils.isNotBlank(calcUnit)) par.put("calcUnit", calcUnit);
		if(StringUtils.isNotBlank(startTime)) par.put("startTime", startTime);
		if(StringUtils.isNotBlank(endTime)) par.put("endTime", endTime);
		par.put("pageIndex", operationDataBo.getPageIndex());
		par.put("pageSize", operationDataBo.getPageSize());

		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map> mapList = buBoOrderformDao.optDataSummary(par);

		OperationDataSummaryBo optDataBo = new OperationDataSummaryBo();
		if (mapList != null && !mapList.isEmpty() && mapList.get(0) != null) {
			Map m = mapList.get(0);
			Long applayNum = (Long)m.get("applayNum") ;
//			if(m.get("totalUser") != null) optDataBo.setTotalUser((Integer)m.get("totalUser"));
			if(m.get("loanTotalUser") != null) optDataBo.setLoanTotalUser(((Long)m.get("loanTotalUser")).intValue());
			if(m.get("newRegUserNum") != null) optDataBo.setNewRegUserNum((Integer)m.get("newRegUserNum"));
			if(m.get("loanSumMoney") != null) optDataBo.setLoanSumMoney(((BigDecimal)m.get("loanSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			if(m.get("applayNum") != null) optDataBo.setApplayNum(applayNum.intValue());
			if(m.get("overdueSumMoney") != null) optDataBo.setOverdueSumMoney(((BigDecimal)m.get("overdueSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			if(m.get("loanSumCount") != null) optDataBo.setLoanSumCount(((Long)m.get("loanSumCount")).intValue());	
			if(optDataBo.getLoanSumCount() != null && optDataBo.getApplayNum()!=0) optDataBo.setLoanRate((new BigDecimal(optDataBo.getLoanSumCount()).divide(new BigDecimal(optDataBo.getApplayNum()), 2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)) + "%");
			if(m.get("overdueCount")!=null && optDataBo.getLoanSumCount()!=0) optDataBo.setLoanBadRate((((BigDecimal)m.get("overdueCount")).divide(new BigDecimal(optDataBo.getLoanSumCount()), 2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100)).toString() + "%");
			if(m.get("m0OverdueCount") != null) optDataBo.setM0OverdueCount(((BigDecimal)m.get("m0OverdueCount")).intValue());
			if(m.get("m1OverdueCount") != null) optDataBo.setM1OverdueCount(((BigDecimal)m.get("m1OverdueCount")).intValue());
			if(m.get("m2OverdueCount") != null) optDataBo.setM2OverdueCount(((BigDecimal)m.get("m2OverdueCount")).intValue());
			logger.info("-->>optDataBo:" + JsonUtil.toJson(optDataBo));
		}
		
		resultMap.put("optDataBo", optDataBo);
		return resultMap;
	}

}
