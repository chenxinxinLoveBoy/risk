package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.BuBoOrderform;

@Mapper
public interface BuBoOrderformDao {
	int deleteByPrimaryKey(String boOrderformId);

	int insert(BuBoOrderform record);

	int insertSelective(BuBoOrderform record);

	BuBoOrderform selectByPrimaryKey(String boOrderformId);

	int updateByPrimaryKeySelective(BuBoOrderform record);

	int updateByPrimaryKey(BuBoOrderform record);

	List<Map> custLoanSummaryList(Map params);

	Integer custLoanSummaryCount(Map params);

	List<Map> incomeManageList(Map params);

	Integer incomeManageCount(Map params);

	List<Map> incomeManageSummaryData(Map params);

	List<Map> optDataSummary(Map par);

	List<Map> optDataList(Map<String, Object> par);

	Integer optDataListCount(Map<String, Object> par);
	
	/**
	 * 
	 * 获取需要提前一天短信通知的所有未还款且未逾期客户订单信息
	 * 
	 **/
	List<BuBoOrderform> getAllPrepaymentBuBoOrderform();

	/**
	 * 
	 * 获取需要还款日当天短信通知的所有未还款且未逾期的客户订单信息
	 * 
	 **/
	List<BuBoOrderform> getAllRepaymentBuBoOrderform();

	/**
	 * 
	 * 获取所有未还款且已逾期的客户订单信息
	 * 
	 **/
	List<BuBoOrderform> getAllOverdueBuBoOrderform();
}