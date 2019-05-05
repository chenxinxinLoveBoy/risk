package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.BuThirdpartyReport;

/**
 *
 * @author
 * @date
 */
public interface BuThirdpartyReportService {

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public List<BuThirdpartyReport>  getEntityById(String buApplicationId);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	public List<BuThirdpartyReport>  findAll(String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	public int deleteEntity(String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	public int saveEntity(BuThirdpartyReport record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	public int updateEntity(BuThirdpartyReport record);

	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	public int saveEntityList(List<BuThirdpartyReport> list);
	
	
	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	public String  getTaskId(BuThirdpartyReport record);
	
	public String getTaskIdByList(String applicationId, String taskType);
	
	public String getTaskReport(String applicationId);
	
	public int updateByReport(Map<String, Object> map);
	
	/**
	 * 统计条数
	 * @param buApplicationId
	 * @return
	 */
	public int listAllCount(String buApplicationId);
	/**
	 * 分页
	 * @param buApplicationId
	 * @return
	 */
	public List<BuThirdpartyReport> listViewAll(String buApplicationId);

	/**
	 * 通过taskType和applicationId获取响应josn报文存储地址
	 * @param buThirdpartyReport
	 * @return
	 */
	public String  getJosnStoragePathBytaskTypeApplicationId(BuThirdpartyReport buThirdpartyReport);


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
	public int saveOrUpdateThirdpartyReport(String applicationId, String reportTaskId, String reportTaskType, String fileName);
}
