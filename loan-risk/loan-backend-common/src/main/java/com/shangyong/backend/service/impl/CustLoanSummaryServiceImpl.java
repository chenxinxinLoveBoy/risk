package com.shangyong.backend.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.CustLoanSumBo;
import com.shangyong.backend.dao.BuBoOrderformDao;
import com.shangyong.backend.service.CustLoanSummaryService;
import com.shangyong.backend.utils.JsonUtil;

@Service
public class CustLoanSummaryServiceImpl implements CustLoanSummaryService {

	private static Logger logger = LoggerFactory.getLogger(CustLoanSummaryServiceImpl.class);
	
	@Autowired
	private BuBoOrderformDao buBoOrderformDao;
	
	@Override
	public Map list(CustLoanSumBo custLoanSumListBo) {
		Map resultMap = new HashMap();
		String appChannel = custLoanSumListBo.getAppChannel();
		String idCard = custLoanSumListBo.getIdCard();
		String startTime = custLoanSumListBo.getStartTime();
		String endTime = custLoanSumListBo.getEndTime();
		
		Map par = new HashMap();
		if(StringUtils.isNotBlank(appChannel)) par.put("appChannel", appChannel);
		if(StringUtils.isNotBlank(idCard)) par.put("idCard", idCard);
		if(StringUtils.isNotBlank(startTime)) par.put("startTime", startTime);
		if(StringUtils.isNotBlank(endTime)) par.put("endTime", endTime);
		par.put("pageIndex", custLoanSumListBo.getPageIndex());
		par.put("pageSize", custLoanSumListBo.getPageSize());

		List<CustLoanSumBo> list = new ArrayList<CustLoanSumBo>();
		Integer count = buBoOrderformDao.custLoanSummaryCount(par);
		if (count > 0) {
			List<Map> mapList = buBoOrderformDao.custLoanSummaryList(par);
			if (mapList!=null && !mapList.isEmpty()) {
				for(Map map : mapList) {
					CustLoanSumBo custLoanSumBo = new CustLoanSumBo();
					if(map.get("customer_id") != null) custLoanSumBo.setCustomerId((String)map.get("customer_id"));
					if(map.get("cert_code") != null) custLoanSumBo.setIdCard((String)map.get("cert_code"));
					if(map.get("phone_num") != null) custLoanSumBo.setMobile((String)map.get("phone_num"));
					if(map.get("sumMoney") != null) custLoanSumBo.setLoanSumMoney(((BigDecimal)map.get("sumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					if(map.get("loanSumCount") != null) custLoanSumBo.setLoanSumCount(((Long)map.get("loanSumCount")).intValue());
					if(map.get("readyRepaySumMoney") != null) custLoanSumBo.setReadyRepaySumMoney(((BigDecimal)map.get("readyRepaySumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					if(map.get("overdueSumMoney") != null) custLoanSumBo.setOverdueMoney(((BigDecimal)map.get("overdueSumMoney")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
					if(map.get("overdueCount") != null) custLoanSumBo.setOverdueCount(((BigDecimal)map.get("overdueCount")).intValue());
					if(map.get("customerName") != null) custLoanSumBo.setCustomerName((String)map.get("customerName"));
					if(map.get("platformId") != null) custLoanSumBo.setPlatformId((String)map.get("platformId"));
					list.add(custLoanSumBo);
				}
			}
		}
		resultMap.put("list", list);
		resultMap.put("count", count);
		logger.info("-->>res list:" + JsonUtil.toJson(resultMap));
		return resultMap;
	}
}
