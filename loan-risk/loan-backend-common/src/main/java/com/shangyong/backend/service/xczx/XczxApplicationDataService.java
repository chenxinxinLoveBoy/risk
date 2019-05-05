package com.shangyong.backend.service.xczx;

import java.util.List;

import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.xczx.XczxApplicationData;

import net.sf.json.JSONArray;

public interface XczxApplicationDataService {

	/**
	 * 保存91征信推送的数据报告
	 * @param obj
	 * @return
	 */
	public int saveEntitys(List<XczxApplicationData> list);
	
	
	/**
	 * 获取相应数据的数量
	 * @return
	 */
	public int findAllCount(XczxApplicationData xczxApplicationData);
	
	/**
	 * 获取相应申请单id数据报告
	 * @param applicationId
	 * @return
	 */
	public List<XczxApplicationData> getDataInfo(XczxApplicationData xczxApplicationData);
	
	
	/**
	 * 通过91征信返回的报告数据进行入库操作
	 * @param trxNo
	 * @param loanArray
	 * @param thirdPartyReportId
	 * @param buApplicationId
	 */
	public void saveLoans(String trxNo, JSONArray loanArray, String thirdPartyReportId, String buApplicationId)throws Exception;
	
	
	/**
	 * 修改客户借款申请扩展表 响应josn报文存储地址
	 * @param obj 申请扩展表对象
	 */
	public boolean updateJsonPath(BuThirdpartyReport obj);
}
