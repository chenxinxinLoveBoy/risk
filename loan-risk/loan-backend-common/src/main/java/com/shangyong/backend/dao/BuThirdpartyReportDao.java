package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.entity.BuThirdpartyReport;

/**
 * BuThirdpartyReportDao数据库操作接口类bean
 * @author xxj
 * @date Tue Jun 27 15:03:19 CST 2017
 **/

@Mapper
public interface BuThirdpartyReportDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	List<BuThirdpartyReport>  getEntityById(String buApplicationId);
	
	
	/**
	 * 
	 * 查询（根据借款申请编号和任务类型查询）
	 * 
	 **/
	String  getTaskId(BuThirdpartyReport record);
	
	
	String  getTaskReport(String record);

	/**
	 * 
	 * 查询所有（根据主键ID查询）
	 * 
	 **/
	List<BuThirdpartyReport>  findAll(@Param("id") String id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteEntity(@Param("id") String id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	@Transactional
	int saveEntity(BuThirdpartyReport record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateEntity(BuThirdpartyReport record);

	/**
	 * 批量插入
	 * @param list
	 * @return
	 */
	int saveEntityList(List<BuThirdpartyReport> list);
	 
	public int updateByReport(Map<String, Object> map);

	/**
	 * 查询（根据对象查询所对应的数据）
	 * @param buApplicationId
	 * @return
	 */
	BuThirdpartyReport  getXczxByApplicationId(BuThirdpartyReport obj);

	/**
	 * 修改，添加报表的Json文件路径 （匹配有值的字段）
	 * @param record
	 * taskId/buApplicationId/taskType  必须有值，否则更新失败
	 * @return
	 */
	int updateReportForJsonPath(BuThirdpartyReport record);

	
	int listAllCount(String buApplicationId);

	
	List<BuThirdpartyReport> listViewAll(String buApplicationId);
	
	
	/**
	 * 根据开始日期和结束日期查询数据
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<BuThirdpartyReport>  getAccordingoDateEntityById(@Param("startTime") String startTime, @Param("endTime") String endTime);
	
	/**
	 * 查询第三方报告表create_man = '1'数据
	 * @param tableName
	 * @return
	 */
	List<BuThirdpartyReport>  queryThirdartyReport(@Param("tableName") String tableName);
	
	
	String getJosnStoragePathBytaskTypeApplicationId(BuThirdpartyReport buThirdpartyReport);

}