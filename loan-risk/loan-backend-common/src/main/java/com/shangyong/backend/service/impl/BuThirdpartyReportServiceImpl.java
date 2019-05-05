package com.shangyong.backend.service.impl;

import java.util.*;

import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuThirdpartyReportServiceImpl implements BuThirdpartyReportService {

	@Autowired
	public BuThirdpartyReportDao buThirdpartyReportDao;

	@Override
	public List<BuThirdpartyReport> getEntityById(String buApplicationId) {  
		return buThirdpartyReportDao.getEntityById(buApplicationId);
	}

	@Override
	public List<BuThirdpartyReport> findAll(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteEntity(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveEntity(BuThirdpartyReport record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public int updateEntity(BuThirdpartyReport record) {
		// TODO Auto-generated method stub
		buThirdpartyReportDao.updateReportForJsonPath(record);
		return 0;
	}

	@Override
	public int saveEntityList(List<BuThirdpartyReport> list) {
		return buThirdpartyReportDao.saveEntityList(list);
	}

	@Override
	public String getTaskId(BuThirdpartyReport record) {
		return buThirdpartyReportDao.getTaskId(record);
	}
	
	@Override
	public String getTaskReport(String record) {
		return buThirdpartyReportDao.getTaskReport(record);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public int updateByReport( Map<String, Object> map ){
		int flag = buThirdpartyReportDao.updateByReport(map);
		return flag;
	}
	/**
	 *根据申请编号、任务类型查询 
	 */
	@Override
	public String getTaskIdByList(String applicationId,String taskType){
		BuThirdpartyReport record = new BuThirdpartyReport();
		record.setBuApplicationId(applicationId);
		record.setTaskType(taskType);
		String taskId = getTaskId(record);
		return taskId;
	}
	@Override
	public int listAllCount(String buApplicationId) {
		return buThirdpartyReportDao.listAllCount(buApplicationId);
	}

	@Override
	public List<BuThirdpartyReport> listViewAll(String buApplicationId) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("01001", "芝麻信用评分");
		map.put("02001", "芝麻信用欺诈清单");
		map.put("03001", "同盾贷前审核");
		map.put("04001", "聚信立蜜蜂");
		map.put("05001", "聚信立蜜罐");
		map.put("10001", "聚信立基础数据");
		map.put("07001", "91征信");
		map.put("08001", "白骑士欺诈");
		map.put("09001", "宜信");
		map.put("11001", "葫芦索伦");
		map.put("12001", "小视科技 银行");
		map.put("12002", "小视科技 网贷");
		map.put("40004", "芝麻信用行业关注名单");
		map.put("13001", "小牛准入包");
		List<BuThirdpartyReport> liReports=buThirdpartyReportDao.listViewAll(buApplicationId);
		for (BuThirdpartyReport buThirdpartyReport : liReports) {
			 for (String key : map.keySet()) {
				if(buThirdpartyReport.getTaskType().equals(key.trim()))
				{
					buThirdpartyReport.setTaskName(map.get(key));
					break;
				}
			 }
		}
		return liReports;
	}

	/**
	 * 通过taskType和applicationId获取响应josn报文存储地址
	 * @param buThirdpartyReport
	 * @return
	 */
	@Override
	public String getJosnStoragePathBytaskTypeApplicationId(BuThirdpartyReport buThirdpartyReport) {
		String  JosnStoragePath = buThirdpartyReportDao.getJosnStoragePathBytaskTypeApplicationId(buThirdpartyReport);
		return JosnStoragePath;
	}


	/**
	 * 创建或更新第三方报表
	 * reportTaskId 为null则新建记录
	 * ken add to 2017/10/18 22:20
	 * @param applicationId 单号 非空
	 * @param reportTaskId 报告任务ID 可空
	 * @param reportTaskType 报告类型 非空
	 * @param fileName 文件名称 可空
	 * @return
	 */
	@Override
	public int saveOrUpdateThirdpartyReport(String applicationId,String reportTaskId,String reportTaskType,String fileName) {
		//组装报告参数
		BuThirdpartyReport thirdpartyReport = new BuThirdpartyReport();
		thirdpartyReport.setBuApplicationId(applicationId);
		thirdpartyReport.setTaskType(reportTaskType);

		//报告ID未传入
		if(!StringUtil.checkNotNull(reportTaskId)){
			//从数据库中查看报告ID是否存在
			reportTaskId = getTaskId(thirdpartyReport);
			//数据库不存在，则新建记录
			if(!StringUtil.checkNotNull(reportTaskId)) {
				//不存在时，直接新建一个报告记录并记录路径信息
				thirdpartyReport.setThirdpartyReportId(UUIDUtils.getUUID());
				thirdpartyReport.setTaskId("noext");
				thirdpartyReport.setJosnStoragePath(fileName);
				List<BuThirdpartyReport> buThirdpartyReports = new ArrayList();
				buThirdpartyReports.add(thirdpartyReport);
				return saveEntityList(buThirdpartyReports);
			}
		}
		//存在报告则更新路径信息
		thirdpartyReport.setJosnStoragePath(fileName);
		thirdpartyReport.setTaskId(reportTaskId);
		thirdpartyReport.setUpdateTime(DateUtils.getDate(new Date()));
		return updateEntity(thirdpartyReport);
	}
}
