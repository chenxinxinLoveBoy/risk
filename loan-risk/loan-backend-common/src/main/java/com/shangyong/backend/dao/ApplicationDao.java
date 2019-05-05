package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ApplicationCount;
import com.shangyong.backend.entity.ApplicationPersonCount;

@Mapper
public interface  ApplicationDao {

	/**
	 * 查询全部
	 * @param t
	 * @return
	 */
	public List<Application> findAll(Application t);

	/**
	 *
	 * @param application
	 * @return
	 */
	public Application getEntityById(Application application);

	/**
	 *
	 * @param application
	 * @return
	 */
	public Application getEntityByIds(Application application);

	/**
	 *
	 * @param t
	 * @return
	 */
	public boolean saveEntity(Application t);

	/**
	 * 更新
	 *
	 * @param t
	 * @return
	 */
	public boolean updateEntity(Application t);

	/**
	 * 更新Application对象
	 *
	 * @param t
	 * @return
	 */
	public boolean updateEntityByApplicationId(Application t);

	/**
	 *
	 * @param t
	 * @return
	 */
	public boolean deleteEntity(Application t);

	/**
	 *
	 * @param t
	 * @return
	 */
	public int findAllCount(Application t);

	/**
	 * 根据流水号查询
	 *
	 * @param id
	 * @return
	 */
	public Application getEntityById(String id);

	/**
	 * 根据主键id查询
	 *
	 * @param id
	 * @return
	 */
	public Application getById(String id);

	/**
	 * 根据customer_id查询申请单
	 *
	 * @param customerId
	 * @return
	 */
	public Application getByCustomerId(String customerId);

	/**
	 * 查询初始化待分配模板的借款申请
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getInitAppLicationList(Map<String, Object> map);

	/**
	 * 查询借款申请表的用户信息给同盾
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAppLicationList(Map<String, Object> map);

	/**
	 * 查询聚信立错误次数大于最大错误数的信息
	 *
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAppLicationListByJxl(Map<String, Object> map);
	
	/**
	 * 同盾接口走完，更新步骤标识、审批状态、审批结果描述
	 *
	 * @param map
	 * @return
	 */
	public int updateApplicationByStep(Map<String, Object> map);

	/**
	 * 查询申请表中不是处于"待审核"状态的客户信息
	 *
	 * @return
	 */
	public List<Map<String, Object>> getRiskAppList();

	/**
	 * 查询借款申请表中不是【待审核】状态，且步骤标识不为0，且未推送通知给App的数据
	 *
	 * @return
	 */
	public List<Application> getAppStatusByPush();

	/**
	 * 成功通知App后，修改借款申请表的【是否通知App】字段is_push_app：0-否、1-是
	 *
	 * @param param
	 * @return
	 */
	public int updateIsPushApp(String param);

	/**
	 * 调用发送大数据成功后，修改借款申请表的【是否通知App】字段is_push_app：0-否、1-是、2：已发送、3：已回调
	 *
	 * @param param
	 * @return
	 */
	public int updateBigDataIsPushApp(String param);
	
	/**
	 * 查询（审核结果统计）
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> queryResultCountInfo(ApplicationCount applicationCount);
	
	/**
	 * 查询（审核结果统计）
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> queryResultCount(ApplicationCount applicationCount);
	
	
	/**
	 * 查询（提现审核结果统计）
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> queryTxResultCount(ApplicationCount applicationCount);

	/**
	 * @param applicationCount
	 * @return
	 */
	public int listResultCount(ApplicationCount applicationCount);

	/**
	 * 保存有风险的信息
	 * @param application
	 * @return
	 */
	public boolean saveRiskyApplication(Application application);
	
	/**
	 * 查询所有用户数量
	 * @param t
	 * @return
	 */
	public int findAllCustomerCount(Application t);

	/**
	 * 查询所有用户
	 * @param t
	 * @return
	 */
	public List<Application> findAllCustomer(Application t);

	/**
	 * 通过率查询
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> getPassingRate(ApplicationCount applicationCount);

	/**
	 * 查询前一天所有订单
	 * @param map 
	 * @return
	 */
	public List<Map<String, Object>> getYesterdayApplicationList(Map<String, Object> map);

	/**
	 * 获取过往审批未通过，审批步骤小于4的申请
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPreviouslyApplicationList(Map<String, Object> map);
	
	/**
	 * 查询前一天审批未通过，审批步骤小于4的申请
	 * @param map 
	 * @return
	 */
	public List<Map<String, Object>> queryYesterdayApplicationList(Map<String, Object> map);

	/**
	 * 查询总授信额度金额
	 * @param applicationCount
	 * @return
	 */
	public int queryTotalCreditMoney(ApplicationCount applicationCount);

	/**
	 * 按时间段查询用户分布
	 * 
	 * @param applicationPersonCount
	 * @return
	 */
	public List<ApplicationPersonCount> getApplicationPersonByTime(ApplicationPersonCount applicationPersonCount);

	/**
	 * 按时间段查询通过申请用户分布
	 * 
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationPersonCount> getPassApplicationPersonByTime(ApplicationPersonCount applicationCount);

	/**
	 *
	 * @param application
	 * @return
	 */
	public int getApplicationFromScoreDetailSmallStatisticsCount(Application application);

	/**
	 *
	 * @param application
	 * @return
	 */
	public int getApplicationFromFraudDetailSmallStatisticsCount(Application application);
	
	/**
	 * 查询命中小类评分规则的所有申请单信息
	 * 
	 * @param application
	 * @return
	 */
	public List<Application> getApplicationFromScoreDetailSmallStatistics(Application application);
	
	/**
	 * 查询命中小类欺诈分规则的所有申请单信息
	 * 
	 * @param application
	 * @return
	 */
	public List<Application> getApplicationFromFraudDetailSmallStatistics(Application application);

	/**
	 * 查询总人数
	 * @param application
	 * @return
	 */
	public int findPersonCount(Application application);
	
	/**
	 * 技术人员专用
	 * @param t
	 * @return
	 */
	public List<Application> jsfindAll(Application t);
	
	/**
	 * 技术人员专用
	 * @param t
	 * @return
	 */
	public int jsfindAllCount(Application t);

	/**
	 * 技术人员专用
	 *
	 * @param platformId
	 * @return
	 */
	public Application getEntityByPlatformId(@Param("platformId") String platformId);

	/**
	 * 技术人员专用
	 *
	 * @param id
	 * @return
	 */
	public Application getObjectById(String id);
	
	/**
	 * 技术人员专用
	 * @param application
	 * @return
	 */
	public int findJsPersonCount(Application application);

	/**
	 * 更新错误次数
	 * @param application
	 * @return
	 */
	public boolean updatefailureTimes(Application application);

	/**
	 * 审批系统案件一览相关
	 * @param application
	 * @return 
	 */
	public int findApproveInfoCount(Application application);

	/** 
	 * 审批系统案件一览相关
	 * @param application
	 * @return 
	 */ 
	public List<Map<String, Object>> findApproveInfo(Application application);

	/**
	 * 审批系统公共案件池相关
	 * @param application
	 * @return
	 */
	public int findPublicApproveCount(Application application);

	/**
	 * 审批系统公共案件池相关
	 * @param application
	 * @return
	 */
	public List<Application> findPublicApprove(Application application);

	/**
	 * 审批相关批量更新审批人用户编号
	 * @param tApplication
	 * @return
	 */
	public boolean updateAuditMan(Application tApplication);

	/**
	 * 审批案件个人
	 * @param application
	 * @return
	 */
	public List<Application> findPrivateApproveApprove(Application application);

	/**
	 * 审批案件个人
	 * @param application
	 * @return 
	 */
	public int findPrivateApproveCount(Application application);
 
    /**
	 * 查询借款申请表、待人工确认状态且审批人编号为空的数据
	 * @return
	 */
	public int getRgApprovalCount();
	
	/**
	 * 统计公共安件池案件总数
	 * @param application
	 * @return
	 */
    public int findAjCount(Application application);
    
    /**
     * 统计已处理的个人案件数
     * @return
     */
	public int findAjClCount(Application application);
	
	/**
	 * 统计待处理的个人案件数
	 *
	 * @param application
	 * @return
	 */
	public int findAjUntrCount(Application application);

	/**
     * 统计个人案件数
     * @return
     */
	public int findGrCount(Application application);
	
	/**
	 * 根据平台用户编号查询最新申请单编号
	 * @param platformId
	 * @return
	 */
	public Application getApplicationIdByPlatformId(String platformId);
	
	/**
	 * 根据app客户编号和平台名称查询客户标识
	 * @param customerId
	 * @param appName
	 * @return
	 */
	public String getCustomerAppLevel(@Param("customerId") String customerId, @Param("appName") String appName);
	
	/**
	 * 根据app客户编号和平台名称查询申请单编号
	 *
	 * @param customerId
	 * @param appName
	 * @return
	 */
	public String getApplicationIdByCustomerIdAndAppName(@Param("customerId") String customerId, @Param("appName") String appName);
	
	/**
	 * 个人处理案件数
	 * @param application
	 * @return
	 */
	public int findGrClAjCount(Application application);

	/**
	 * 根据条件查询申请单号
	 * @param application
	 * @return
	 */
	public List<Application> findApplicationIdByDate(Application application);

	/**
	 * 统计与大数据的交互记录，大数据待审批，审批通过，审批不通过
	 * @param application
	 * @return
	 */
	public int countHbaseMutual(Application application);

	/**
	 * 统计每个步骤待审批的单子
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> countStepNumber(ApplicationCount applicationCount);

	/**
	 *
	 * @param applicationCount
	 * @return
	 */
	public int countHbasePendingeview(ApplicationCount applicationCount);

	/**
	 *
	 * @param applicationCount
	 * @return
	 */
	public int countToBeAuditedManually(ApplicationCount applicationCount);

	/**
	 *
	 * @param Application
	 * @return
	 */
	public int countNumber(Application Application);
	
	/**
	 * 根据申请单编号查询对应欺诈模板编号
	 *
	 * @param applicationId
	 * @return
	 */
	public String getFraudTplIdByApplicationId(String applicationId);
	 
	/**
	 * 统计每个步骤待审批的单子中的异常单子
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> countStepNumberDetail(ApplicationCount applicationCount);
	
	/**
	 *  统计人工审核单数
	 * @param applicationPersonCount
	 * @return
	 */
	public List<ApplicationPersonCount> getStatisticalAuditNumber(ApplicationPersonCount applicationPersonCount);
	
	/**
	 * 按时间段查询（人工审核审核数）
	 * 
	 * @param applicationPersonCount
	 * @return
	 */
	public List<ApplicationPersonCount> getRgApprovePersonByTime(ApplicationPersonCount applicationPersonCount);

	/**
	 *
	 * @param applicationPersonCount
	 * @return
	 */
	public List<ApplicationPersonCount> getPassRgApprovePersonByTime(ApplicationPersonCount applicationPersonCount);

	/**
	 *
	 * @param applicationPersonCount
	 * @return
	 */
	public List<ApplicationPersonCount> getNoPassRgApprovePersonByTime(ApplicationPersonCount applicationPersonCount);
	
	/**
	 * 统计每个步骤待审批的单子
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> txCountStepNumber(ApplicationCount applicationCount);

	/**
	 *
	 * @param applicationCount
	 * @return
	 */
	public List<ApplicationCount> txCountStepNumberDetail(ApplicationCount applicationCount);

	/**
	 *
	 * @param param
	 * @return
	 */
	public Application selectApplicationByParam(Map<String, String> param);
	
	/**
	 * 审批订单为“信息重新采集”状态的数据
	 * @return
	 */
	public List<Application> findOperatorInfoFailure();
	
	/**
	 * 修改is_call_sucess的状态
	 * @param applicationId
	 * @return
	 */
	public int updateIsCallSucess(String applicationId);
	
	/**
	 * 
	 * @param application
	 * @return
	 */
	public int findYcCount(Application application);
	public int findYcUntrCount(Application application);
	public int findYcClCount(Application application);
	public int findYlCount(Application application);
	public int findYlUntrCount(Application application);
	public int findYlClCount(Application application);
	
	
	/**
	 * 根据app_serial_number查询application_id
	 *
	 * @param appSerialNumber
	 * @return
	 */
	Application findByAppSerialNumber(@Param("appSerialNumber") String appSerialNumber);

	/**
	 * 
	 * @Description 通过customerId和auditing_state查询
	 * @author ty
	 * @Date 2018年4月17日 上午10:34:11
	 * @param param
	 * @return
	 */
	Application findByCustomerIdAndAuditingState(Map<String, String> param);
	
	/**
	 * findLast2ApplicationByCustomerId:查询最近两笔
	 * @param param
	 * @return
	 */
	List<Application> findLast2ApplicationByCustomerId(Map<String, String> param);
	
	/**
	 *  通过customerId 查询用户所有申请单
	 * @param customerId 客户ID
	 * @return List<Application>
	 */
	public List<Application> getApplicationListByCustomerId(String customerId);

	/**
	 * 统计 失败次数超过 4次的 申请单
	 * @return
	 */
	public Long selectFailureTimeMoreThanFourTimes();
}
