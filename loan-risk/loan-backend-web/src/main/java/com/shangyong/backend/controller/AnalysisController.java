package com.shangyong.backend.controller;

import com.shangyong.backend.bo.CustLoanSumBo;
import com.shangyong.backend.bo.IncomeManageBo;
import com.shangyong.backend.bo.OperationDataBo;
import com.shangyong.backend.service.CustLoanSummaryService;
import com.shangyong.backend.service.IncomeManageService;
import com.shangyong.backend.service.OperationDataService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.JsonUtil;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/analysis/")
public class AnalysisController {
	
	private static Logger logger = LoggerFactory.getLogger(AnalysisController.class);
	
	@Autowired
	private CustLoanSummaryService custLoanSummaryService;
	
	@Autowired
	private IncomeManageService incomeManageService;
	
	@Autowired
	private OperationDataService operationDataService;

	/**
	 * 用户借款汇总列表
	 * @param CustLoanSumListBo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "custLoanSummaryList")
	public void custLoanSummaryList(CustLoanSumBo custLoanSumBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("-->>custLoanSumBo:" + JsonUtil.toJson(custLoanSumBo));
			Map resultMap = custLoanSummaryService.list(custLoanSumBo);
			List<CustLoanSumBo> list  = (List<CustLoanSumBo>)resultMap.get("list");
			JSONUtils.toListJSON(response, list, (Integer)resultMap.get("count"));
		} catch (Exception e) {
			logger.info("-->>custLoanSummaryList--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
	/**
	 * 平台收益管理
	 * @param incomeManageBo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "incomeManageList")
	public void incomeManageList(IncomeManageBo incomeManageBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			Map listMap = incomeManageService.list(incomeManageBo);
			List<IncomeManageBo> list = (List<IncomeManageBo>)listMap.get("list");
			JSONUtils.toListJSON(response, list, (Integer)listMap.get("count"));
		} catch (Exception e) {
			logger.info("-->>incomeManageList--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
	/**
	 * 平台收益管理 -- 获得表顶统计数据
	 * @param incomeManageBo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "incomeManage/getSummaryData")
	public void incomeManageSummaryData(IncomeManageBo incomeManageBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			Map listMap = incomeManageService.getSummaryData(incomeManageBo);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, listMap);
		} catch (Exception e) {
			logger.info("-->>getSummaryData--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
	/**
	 * 平台运营数据列表
	 * @param operationDataBo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "operationDataList")
	public void operationDataList(OperationDataBo operationDataBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			Map listMap = operationDataService.list(operationDataBo);
			List<OperationDataBo> list = (List<OperationDataBo>)listMap.get("list");
			JSONUtils.toListJSON(response, list, (Integer)listMap.get("count"));
		} catch (Exception e) {
			logger.info("-->>operationDataList--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
	/**
	 * 平台运营数据顶部统计数据
	 * @param operationDataBo
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "operationData/getSummaryData")
	public void operationDataSummaryData(OperationDataBo operationDataBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			Map list = operationDataService.getSummaryData(operationDataBo);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, list);
		} catch (Exception e) {
			logger.info("-->>operationDataList--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
	/**
	 * 调外部接口获得平台总注册数等统计数据
	 */
	@RequestMapping(value = "operationData/getTotalSummaryData")
	public void getTotalSummaryData(OperationDataBo operationDataBo , HttpServletRequest request, HttpServletResponse response) {
		try {
			String httpRes = RiskHttpClientUtil.doPost(PropertiesUtil.get("sd.appBackEnd.server") + PropertiesUtil.get("sd.appbackend.gettotaldata.url"));
			logger.info("-->>getTotalSummaryData--httpRes:" + httpRes);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, JsonUtil.toObject(httpRes, Map.class));
		} catch (Exception e) {
			logger.info("-->>operationDataList--error", e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
		}
	}
	
}
