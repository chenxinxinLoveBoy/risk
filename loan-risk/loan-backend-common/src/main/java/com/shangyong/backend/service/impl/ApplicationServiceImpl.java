package com.shangyong.backend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.common.enums.BlackListDsSourceEnum;
import com.shangyong.backend.common.enums.SourceEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.DictConstant;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.BuApplicationDetailDao;
import com.shangyong.backend.dao.SysParamDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ApplicationCount;
import com.shangyong.backend.entity.ApplicationPersonCount;
import com.shangyong.backend.entity.BlacklistResult;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.ScRiskWhiteListRedisService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

/**
 * 申请单信息 service实现类
 *
 * @author xixinghui
 */
@Service
public class ApplicationServiceImpl implements BaseService<Application> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private ApplicationServiceImpl applicationServiceImpl;

    @Autowired
    private SysParamRedisService sysParamRedisService;

    @Autowired
    private RiskRuleService riskRuleService;
    /**
     * 风控Redis白名单service接口
     */
    @Autowired
    private ScRiskWhiteListRedisService scRiskWhiteListRedisServiceImpl;

    @Autowired
    private BuThirdpartyReportService buThirdpartyReportServiceImpl;

    @Autowired
    private SysParamDao sysParamDao;
    
    @Autowired
    private BuApplicationDetailDao buApplicationDetailDao;

    /**
     * 统计查询数据量
     *
     * @param application
     * @return
     */
    public int findAllCount(Application application) {
        return applicationDao.findAllCount(application);
    }

    /**
     * 查询所有申请单信息
     *
     * @param application
     * @return
     */
    @Override
    public List<Application> findAll(Application application) {
        List<Application> applicationList = applicationDao.findAll(application); 
        return applicationList;
    }

    /**
     * 取得时间字段的年月日时分秒
     *
     * @param str
     * @return
     */
    private String formatTimeString(String str) {
        return str.split("\\.")[0];
    }

    public Application getEntityByApplicationId(String applicationId){
		return applicationDao.getById(applicationId);
    	
    }
    /**
     * 根据APP应用请求流水号查询订单
     */
    @Override
    public Application getEntityById(String id) {
        return applicationDao.getEntityById(id);
    }
    
    @Override
    public Application getEntityById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Application getEntityById(Application application) {
        return applicationDao.getEntityById(application);
    }

    @Override
    @Transactional
    public boolean saveEntity(Application application) {
        // 审批状态：待审核
        application.setAuditingState(Constants.DAI_SP_STAATE);
        CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
        cuPlatformCustomer.setAppName(application.getAppName());
        cuPlatformCustomer.setCustomerId(application.getCustomerId());
        return applicationDao.saveEntity(application);
    }

    @Override
    @Transactional
    public boolean updateEntity(Application application) {
        return applicationDao.updateEntity(application);
    }

    public boolean updateErrorDescription(String applicationId, String errorMessage) {
        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setErrorDescription(StringUtils.substring(errorMessage, 0, 450));
        application.setFailureTimes("1");//用来区分updateEntity方法，判断失败次数是否+1
        return applicationDao.updateEntity(application);
    }
    public boolean updateErrorDescriptionBqsReport(String applicationId, String errorMessage) {
    	Application info = applicationDao.getObjectById(applicationId);
        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setErrorDescription(StringUtils.substring(errorMessage, 0, 450));
        application.setFailureTimes("1");//用来区分updateEntity方法，判断失败次数是否+1
        String failureTimes = info.getFailureTimes();
        if ("4".equals(failureTimes)) {
    		application.setAuditingState("5");
		}
        return applicationDao.updateEntity(application);
    }
    
    public boolean updateErrorDescriptionTdRepot(String applicationId, String errorMessage) {
    	Application info = applicationDao.getObjectById(applicationId);
    	String failureTimes = info.getFailureTimes();
    	Application application = new Application();
        application.setApplicationId(applicationId);
        application.setErrorDescription(StringUtils.substring(errorMessage, 0, 450));
        application.setFailureTimes("1");//用来区分updateEntity方法，判断失败次数是否+1
    	if ("4".equals(failureTimes)) {
    		int count = buApplicationDetailDao.refuseStepCount(applicationId);
    		if (count>0) {
    			application.setAuditingState("3");//审批未通过
    			application.setAuditingRejectCode("1");//禁止项拒绝
			}else{
				application.setAuditingState("4");
			}
		}
        return applicationDao.updateEntity(application);
    }

    @Override
    public boolean deleteEntity(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteEntity(Application application) {
        return applicationDao.deleteEntity(application);
    }

    // 查询借款申请表的用户信息给同盾
    public List<Map<String, Object>> getAppLicationList(Map<String, Object> map) {
        return applicationDao.getAppLicationList(map);
    }

    public List<ApplicationCount> queryResultCountInfo(ApplicationCount applicationCount) {
        return applicationDao.queryResultCountInfo(applicationCount);
    }

    public List<ApplicationCount> countStepNumber(ApplicationCount applicationCount) {
        applicationCount.setAuditingState(String.valueOf(1));
        List<ApplicationCount> returnList = applicationDao.countStepNumber(applicationCount);
        SysParam param = sysParamDao.queryByParamValue(Constants.APP_FAILURE_COUNT);
        if (null == param || StringUtils.isEmpty(param.getParamValueOne())) {
            applicationCount.setFailureTimes("0");
        } else {
            applicationCount.setFailureTimes(param.getParamValueOne());
        }
        List<ApplicationCount> list = applicationDao.countStepNumberDetail(applicationCount);
        if (list != null && list.size() > 0) {
            for (ApplicationCount detailCount : returnList) {
                for (ApplicationCount detail : list) {
                    if (detail.getIsStep().equals(detailCount.getIsStep())) {
                        detailCount.setCountDetail(detail.getCountDetail());
                        ;
                    }
                }
                if (!StringUtils.isNotBlank(detailCount.getCountDetail())) {
                    detailCount.setCountDetail("0");
                }
            }
        } else {
            for (ApplicationCount detailCount : returnList) {
                detailCount.setCountDetail("0");
            }
        }
        return returnList;
    }

    /**
     * 统计大数据待审批数量和人工待审核数量
     *
     * @param applicationCount
     * @return
     */
    public Map<String, Integer> countHbaseAndartificialNumber(ApplicationCount applicationCount) {
        Map<String, Integer> count = new HashMap<>();
        count.put("hbaseNumber", applicationDao.countHbasePendingeview(applicationCount));
        count.put("artificialNumber", applicationDao.countToBeAuditedManually(applicationCount));
        Application application = new Application();
        application.setStartTime(applicationCount.getStartTime());
        application.setEndTime(applicationCount.getEndTime());
        application.setIsPushApp("0");
        count.put("countNumber", applicationDao.countNumber(application));
        return count;
    }
    
    /**
	 * 统计提现流程每个步骤待审批的单子 
     *
     * @param applicationCount
     * @return
     */
    public List<ApplicationCount> txCountStepNumber(ApplicationCount applicationCount) {
        applicationCount.setAuditingState(String.valueOf(1));
        List<ApplicationCount> returnList = applicationDao.txCountStepNumber(applicationCount);
        List<ApplicationCount> list = applicationDao.txCountStepNumberDetail(applicationCount);
        if (list != null && list.size() > 0) {
            for (ApplicationCount detailCount : returnList) {
                for (ApplicationCount detail : list) {
                    if (detail.getIsStep().equals(detailCount.getIsStep())) {
                        detailCount.setCountDetail(detail.getCountDetail());
                    }
                }
                if (!StringUtils.isNotBlank(detailCount.getCountDetail())) {
                    detailCount.setCountDetail("0");
                }
            }
        } else {
            for (ApplicationCount detailCount : returnList) {
                detailCount.setCountDetail("0");
            }
        }
        return returnList;
    }


    public int listResultCount(ApplicationCount applicationCount) {
        return applicationDao.listResultCount(applicationCount);
    }

    public List<ApplicationCount> queryResultCount(ApplicationCount applicationCount) {
        return applicationDao.queryResultCount(applicationCount);
    }
    
	/**
	 * 查询（提现审核结果统计）
	 * @param applicationCount
	 * @return
	 */
    public List<ApplicationCount> queryTxResultCount(ApplicationCount applicationCount) {
        return applicationDao.queryTxResultCount(applicationCount);
    }

    /**
     * 保存有风险的申请信息
     *
     * @param application
     * @return
     */
    public boolean saveRiskyApplication(Application application) {
        String uuid = UUIDUtils.getUUID();
        application.setApplicationId(uuid);
        application.setAuditingState(Constants.NOPASS_SP_STAATE);
        application.setIsPushApp(Constants.IS_PUSH_APP);
        application.setRemark("由App上送的问题用户");
        // 同盾
        application.setIsStep(Constants.TD_EQUIPMENT_STEP);
        // CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
        // cuPlatformCustomer.setAppName(application.getAppName());
        // cuPlatformCustomer.setCustomerId(application.getCustomerId());
        // CuPlatformCustomer cuPlatformCustomerInfo =
        // cuPlatformCustomerService.getEntityById(cuPlatformCustomer);
        // if(cuPlatformCustomerInfo == null
        // ||
        // StringUtils.isEmpty(cuPlatformCustomerInfo.getPlatformCustomerId()))
        // {
        // return false;
        // }
        // application.setPlatformId(cuPlatformCustomerInfo.getPlatformCustomerId());
        addBlackList(application);
        return applicationDao.saveRiskyApplication(application);
    }

    private void addBlackList(Application application) {
        String certCode = application.getCertCode();
        String appName = application.getAppName();
//        BuBlacklist black = new BuBlacklist();
//        black.setCertCode(application.getCertCode());// 身份证
//        black.setPhoneNum(application.getPhoneNum());//手机号
//        int count = blacklistService.listAllCount(black);

        BlacklistResult blacklistResult = blacklistService.isInBlacklistTable(certCode, application.getPhoneNum(), "");
        int count = blacklistResult.getCount();
        if (count < Constants.APPLICATION_BLACK_NUM) {
            // 审批拒绝的信息，添加黑名单表
            BuBlacklist buBlacklist = new BuBlacklist();
            buBlacklist.setBlacklistId(UUIDUtils.getUUID());// 拒绝编号
            buBlacklist.setAppName(application.getAppName());// APP名称：1-闪贷；2-速贷
            buBlacklist.setPlatformId(application.getPlatformId());// 平台用户账号
            buBlacklist.setCertCode(application.getCertCode());// 证件号码
            buBlacklist.setCertType(application.getCertType());// 证件类型 ： 1.身份证 2.护照 3.其他
            buBlacklist.setCustomerId(application.getCustomerId());// APP应用客户编号
            buBlacklist.setPhoneNum(application.getPhoneNum());// 手机号
            buBlacklist.setName(application.getName());// 客户姓名
            buBlacklist.setRejectType("01");// 被拒绝平台类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚）
            buBlacklist.setIsFailure("1");// 是否失效（0-是，1-否）
            buBlacklist.setBanCode(application.getBanCode());
            buBlacklist.setDsSource(BlackListDsSourceEnum.APP_SYNC.getCode());
            buBlacklist.setsNumber(application.getApplicationId());
            buBlacklist.setRemark(application.getAuditResult());// 备注
            boolean saveBlack = blacklistService.saveEntity(buBlacklist);
            if (!saveBlack) {
                throw new RuntimeException("黑名单信息入库失败：appName=" + appName + ",certCode=" + certCode);
            }
        }
    }

    /**
     * 统计查询数据量
     *
     * @param application
     * @return
     */
    public int findAllCustomerCount(Application application) {
        return applicationDao.findAllCustomerCount(application);
    }

    /**
     * 查询所有申请单信息
     *
     * @param application
     * @return
     */
    public List<Application> findAllCustomer(Application application) {
        List<Application> applicationList = applicationDao.findAllCustomer(application);
        for (Application applicationBean : applicationList) {
            if (StringUtils.isNotEmpty(applicationBean.getCreateTime())){
                applicationBean.setCreateTime(formatTimeString(applicationBean.getCreateTime()));
            }
            if (StringUtils.isNotEmpty(applicationBean.getAuditingTime())){
                applicationBean.setAuditingTime(formatTimeString(applicationBean.getAuditingTime()));
            }

        }
        return applicationList;
    }

    public List<ApplicationPersonCount> getPassingRate(ApplicationPersonCount applicationPersonCount) {

        List<ApplicationPersonCount> sumList = applicationDao.getApplicationPersonByTime(applicationPersonCount);
        List<ApplicationPersonCount> passList = applicationDao.getPassApplicationPersonByTime(applicationPersonCount);
        for (ApplicationPersonCount sumCount : sumList) {
            int size = passList.size();
            for (int i = 0; i < passList.size(); i++) {
                if (StringUtils.equals(sumCount.getTimeValue(), passList.get(i).getTimeValue())) {
                    sumCount.setRate(caculateRate(passList.get(i).getCount(), sumCount.getCount()));
                    sumCount.setPassPersonCount(passList.get(i).getPersonCount());
                    break;
                } else if (i == (size - 1)) {
                    sumCount.setRate("0");
                    sumCount.setPassPersonCount("0");
                }
            }
        }
        return sumList;
    }

    public List<ApplicationPersonCount> getApplicationPersonByTime(ApplicationPersonCount applicationPersonCount) {
        return applicationDao.getApplicationPersonByTime(applicationPersonCount);
    }

    private String caculateRate(String numeratorStr, String denominatorStr) {
        BigDecimal numerator = new BigDecimal(numeratorStr);
        BigDecimal denominator = new BigDecimal(denominatorStr);
        BigDecimal rate = numerator.multiply(new BigDecimal("100")).divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
        return rate.toString();
    }

    /**
     * 命中小类评分规则的所有申请单信息数据量
     *
     * @param application
     * @return
     */
    public int getApplicationFromScoreDetailSmallStatisticsCount(Application application) {
        return applicationDao.getApplicationFromScoreDetailSmallStatisticsCount(application);
    }

    /**
     * 查询命中小类评分规则的所有申请单信息
     *
     * @param application
     * @return
     */
    public List<Application> getApplicationFromScoreDetailSmallStatistics(Application application) {
        List<Application> applicationList = applicationDao.getApplicationFromScoreDetailSmallStatistics(application);
        for (Application applicationBean : applicationList) {
            if (StringUtils.isNotEmpty(applicationBean.getCreateTime()))
                applicationBean.setCreateTime(formatTimeString(applicationBean.getCreateTime()));
            if (StringUtils.isNotEmpty(applicationBean.getAuditingTime()))
                applicationBean.setAuditingTime(formatTimeString(applicationBean.getAuditingTime()));
        }
        return applicationList;
    }

    /**
     * 命中小类欺诈分规则的所有申请单信息数据量
     *
     * @param application
     * @return
     */
    public int getApplicationFromFraudDetailSmallStatisticsCount(Application application) {
        return applicationDao.getApplicationFromFraudDetailSmallStatisticsCount(application);
    }

    /**
     * 技术人员专用
     *
     * @param application
     * @return
     */
    public int findJsPersonCount(Application application) {
        return applicationDao.findJsPersonCount(application);
    }

    /**
     * 查询命中小类欺诈分规则的所有申请单信息
     *
     * @param application
     * @return
     */
    public List<Application> getApplicationFromFraudDetailSmallStatistics(Application application) {
        List<Application> applicationList = applicationDao.getApplicationFromFraudDetailSmallStatistics(application);
        for (Application applicationBean : applicationList) {
            if (StringUtils.isNotEmpty(applicationBean.getCreateTime()))
                applicationBean.setCreateTime(formatTimeString(applicationBean.getCreateTime()));
            if (StringUtils.isNotEmpty(applicationBean.getAuditingTime()))
                applicationBean.setAuditingTime(formatTimeString(applicationBean.getAuditingTime()));
        }
        return applicationList;
    }

    /**
     * 查询总授信额度金额
     *
     * @param applicationCount
     * @return
     */
    public int queryTotalCreditMoney(ApplicationCount applicationCount) {
        return applicationDao.queryTotalCreditMoney(applicationCount);
    }

    /**
     * 查询总人数
     *
     * @param application
     * @return
     */
    public int findPersonCount(Application application) {
        return applicationDao.findPersonCount(application);
    }

    /**
     * 技术人员专用
     *
     * @param application
     * @return
     */
    public int jsfindAllCount(Application application) {
        return applicationDao.jsfindAllCount(application);
    }

    /**
     * 技术人员专用
     *
     * @param application
     * @return
     */
    public List<Application> jsfindAll(Application application) {
        return applicationDao.jsfindAll(application);
    }

    /**
     * 技术人员专用
     *
     * @param id
     * @return
     */
    public Application getObjectById(String id) {
        return applicationDao.getObjectById(id);
    }

    @Transactional
    public boolean updatefailureTimes(Application application) {
        return applicationDao.updatefailureTimes(application);
    }

    /**
     * 批量清除错误次数
     *
     * @param application
     * @return
     */
    @Transactional
    public boolean updateBatch(Application application) {
        Application tApplication = new Application();
        boolean flag = false;
        if (application != null && application.getIds().length > 0) {
            for (int i = 0; i < application.getIds().length; i++) {
                if (StringUtils.isNotBlank(application.getIds()[i])) {
                    String id = application.getIds()[i];
                    tApplication.setApplicationId(id);
                    tApplication.setFailureTimes("0");
                    flag = applicationDao.updatefailureTimes(tApplication);
                }
            }
            return flag;
        } else {
            return flag;
        }
    }

    /**
     * 审批系统案件一览相关
     *
     * @param application
     * @return
     */
    public List<Map<String, Object>> findApproveInfo(Application application) {
        return applicationDao.findApproveInfo(application);
    }

    /**
     * 审批系统案件一览相关
     *
     * @param application
     * @return
     */
    public int findApproveInfoCount(Application application) {
        int count = applicationDao.findApproveInfoCount(application);
        return count;
    }

    public int findPublicApproveCount(Application application) {
        int count = applicationDao.findPublicApproveCount(application);
        return count;
    }

    public List<Application> findPublicApprove(Application application) {
        return applicationDao.findPublicApprove(application);
    }

    @Transactional
    public boolean updateBatchAuditMan(Application application) {
        Application tApplication = new Application();
        boolean flag = false;
        UUserBo user = TokenManager.getUser();
        if (application != null && application.getIds().length > 0) {
            for (int i = 0; i < application.getIds().length; i++) {
                if (StringUtils.isNotBlank(application.getIds()[i])) {
                    String id = application.getIds()[i];
                    tApplication.setApplicationId(id);
                    tApplication.setAuditMan(user.getId().toString());
                    flag = applicationDao.updateAuditMan(tApplication);
                }
            }
            return flag;
        } else {
            return flag;
        }
    }

    /**
     * 审批相关个人案件信息
     *
     * @param application
     * @return
     */
    public List<Application> findPrivateApproveApprove(Application application) {
        UUserBo user = TokenManager.getUser();
        application.setAuditMan(user.getId().toString());
        return applicationDao.findPrivateApproveApprove(application);
    }

    /**
     * 审批相关个人案件信息
     *
     * @param application
     * @return
     */
    public int findPrivateApproveCount(Application application) {
        UUserBo user = TokenManager.getUser(); 
        application.setAuditMan(user.getId().toString());
        int count = applicationDao.findPrivateApproveCount(application);
        return count;
    }

    /**
     * 统计案件总数
     *
     * @param application
     * @return
     */
    public int findAjCount(Application application) {
        int count = applicationDao.findAjCount(application);
        return count;
    }

    /**
     * 统计待处理的个人案件数
     *
     * @return
     */
    public int findAjUntrCount(Application application) {
        int ii = applicationDao.findAjUntrCount(application);
        return ii;
    }

    /**
     * 统计已处理的个人案件数
     *
     * @return
     */
    public int findAjClCount(Application application) {
        int i = applicationDao.findAjClCount(application);
        return i;
    }

    /**
     * 个人案件总数
     *
     * @return
     */
    public int findGrCount(Application application) {
        int gr = applicationDao.findGrCount(application);
        return gr;
    }
    //当日异常案件池数
    public int findYcCount(Application application){
    	int count = applicationDao.findYcCount(application);
    	return count;
    }
    //当日异常未处理案件件数
    public int findYcUntrCount(Application application) {
        int count = applicationDao.findYcUntrCount(application);
        return count;
    }
    //当日异常已处理案件件数
    public int findYcClCount(Application application) {
        int count = applicationDao.findYcClCount(application);
        return count;
    }
    //当日异类案件池数
    public int findYlCount(Application application){
    	int count = applicationDao.findYlCount(application);
    	return count;
    }
    //当日异类未处理案件件数
    public int findYlUntrCount(Application application) {
        int count = applicationDao.findYlUntrCount(application);
        return count;
    }
    //当日异类已处理案件件数
    public int findYlClCount(Application application) {
        int count = applicationDao.findYlClCount(application);
        return count;
    }
    /**
     * 根据平台用户编号获取最新申请单编号
     *
     * @param platformId
     * @return
     */
    public Application getApplicationIdByPlatformId(String platformId) {
        return applicationDao.getApplicationIdByPlatformId(platformId);
    }

    /**
     * 根据app客户编号和平台名称查询客户标识
     */
    public String getCustomerAppLevel(String customerId, String appName) {
        return applicationDao.getCustomerAppLevel(customerId, appName);
    }

    /**
     * 根据app客户编号和平台名称查询申请单编号
     */
    public String getApplicationIdByCustomerIdAndAppName(String customerId, String appName) {
        return applicationDao.getApplicationIdByCustomerIdAndAppName(customerId, appName);
    }
    
    public int findGrClAjCount(Application application) {
        int temp = applicationDao.findGrClAjCount(application); 
        return temp;
    }

    /**
     * 根据申请单编号查询对应欺诈模板编号
     */
    public String getFraudTplIdByApplicationId(String applicationId) {
        return applicationDao.getFraudTplIdByApplicationId(applicationId);
    }
    
    
    public int getHistoricalSurplusCount(ApplicationCount applicationCount) {
        int temp = applicationDao.countToBeAuditedManually(applicationCount); 
        return temp;
    }
    

    /**
     * 统计审核数
     *
     * @param applicationPersonCount
     * @return
     */
    public List<ApplicationPersonCount> getStatisticalAuditNumber(
            ApplicationPersonCount applicationPersonCount) {
        List<ApplicationPersonCount> sumList = applicationDao.getStatisticalAuditNumber(applicationPersonCount);
        return sumList;
//		if(sumList.size()>1)
//		{
//		
//			if(StringUtils.equals(sumList.get(0).getTimeValue(), sumList.get(1).getTimeValue()))
//			{
//				 sumList.get(0).setRate(caculateRate(sumList.get(0).getCount(), sumList.get(1).getCount()));
//				 sumList.get(0).setPassPersonCount(sumList.get(1).getPersonCount());
//				 sumList.remove(1);
//				
//			}
//		}
//		if(sumList.size()==1)
//		{
//			sumList.get(0).setRate("0");
//			 sumList.get(0).setPassPersonCount("0");
//		}
    }

    /**
     * 统计人工审核每个人的审核通过数、未通过数和通过率
     *
     * @param applicationPersonCount
     */
    public List<ApplicationPersonCount> getRgApprovalRate(
            ApplicationPersonCount applicationPersonCount) {
        List<ApplicationPersonCount> sumList = applicationDao.getRgApprovePersonByTime(applicationPersonCount);
//	        List<ApplicationPersonCount> passList = applicationDao.getPassRgApprovePersonByTime(applicationPersonCount);
//	        List<ApplicationPersonCount> noPassList = applicationDao.getNoPassRgApprovePersonByTime(applicationPersonCount);
//	        int j=0;
        for (ApplicationPersonCount sumCount : sumList) {
//	            int size = passList.size();
//	            for (int i = j; i < passList.size(); i++) {
//	            	j++;
//	                if (StringUtils.equals(sumCount.getTimeValue(), passList.get(i).getTimeValue())) {
            sumCount.setRate(caculateRate(sumCount.getPassPersonCount(), sumCount.getCount()));
//	                    sumCount.setPassPersonCount(passList.get(i).getPersonCount()==null?"0":passList.get(i).getPersonCount());
//	                    break;
//	                } else if (i == (size - 1)) {
//	                    sumCount.setRate("0");
//	                    sumCount.setPassPersonCount("0");
//	                }
//	            }
//	            for (int j = 0; j < noPassList.size(); j++) {
//	                if (StringUtils.equals(sumCount.getTimeValue(), noPassList.get(j).getTimeValue())) {
//	                    sumCount.setNoPassPersonCount(noPassList.get(j).getPersonCount());
//	                    break;
//	                } else if (j == (size - 1)) {
//	                    sumCount.setPassPersonCount("0");
//	                }
//	            }
        }
        return sumList;
    }

    /**
     * 个人案件详情页面sql
     *
     * @param application
     * @return
     */
    public Application getEntityByIds(Application application) {
        return applicationDao.getEntityByIds(application);
    }


    /**
     * 借款申请单的准入检查
     * 更新审批单准入规则信息
     * @param list
     */
	@Transactional
    @Deprecated
    public void processApplication(List<Application> list){
        if(CollectionUtils.isEmpty(list)){
            logger.error("准入检查的申请单数量为0");
            return;
        }
        for(Application application : list){

            String certCode = application.getCertCode();
            String appName = application.getAppName();
            String applicationId = application.getApplicationId();
            String appLevel = application.getAppLevel();
            String phoneNum = application.getPhoneNum();
            logger.info("开始校验准入规则，申请单号：" + applicationId);
            // ken add to 2017/10/18 22:28 desc  进件流程,准入包的操作也记录到借款申请扩展表中；
            buThirdpartyReportServiceImpl.saveOrUpdateThirdpartyReport(applicationId,null,Constants.THIRDPARTY_REPORT_XIAO_NIU,"");
            if (!Constants.APP_LEVEL.equals(appLevel)) {
                try{
                    // 获取参数值
                    SysParam xiaoNiuRuleONOff = sysParamRedisService.querySysParamByParamValueRedis(Constants.XIAO_NIU_RULE_ON_OFF);
                    if(xiaoNiuRuleONOff != null){
                        //1 开， 0 关 为1的时候校验准入规则
                        if (DictConstant.IS_FAILURE_ONE.equals(xiaoNiuRuleONOff.getParamValueOne())) {
                            //准入规则，默认为新用户
                            String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;
                            //获取准入规则的模块ID
                            SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);

                            logger.info("准入规则开始校验白名单信息：appName="+appName+",certCode="+certCode);

                            //准入规则校验
                            RuleResult ruleResult = riskRuleService.checkSafeRuleBySystem(application);
                            if(ruleResult != null && ruleResult.getIsblack()){
                                //命中的申请单直接拒绝
                                application.setAuditingState(DictConstant.AUDITING_STATE_THREE);
                                application.setAuditingTime(DateUtils.parseToDateTimeStr(new Date()));
                                application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
                                application.setAuditResult(ruleResult.getMessage());
                                application.setBanCode(ruleResult.getBlackCode());
                                application.setIsStep(Constants.SY_RULE_ID);
                                application.setFailureTimes("0");
                                application.setErrorDescription("");
                                application.setBanCodeTplId(sysParam.getParamValueOne());
                                applicationServiceImpl.updateEntity(application);
                            } else {
                                //未命中的申请单进行黑名单审核
                                application.setIsStep(Constants.RULE_ID_SET_STEP);
                                application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
                              //application.setDistributionTime(DateUtils.parseToDateTimeStr(new Date()));
                                //能正常走下去的都要清空失败次数和异常信息
                                application.setFailureTimes("0");
                                application.setErrorDescription("");
                                applicationServiceImpl.updateEntity(application);
                            }

                        }else{
                            //老用户直接进行外部黑名单审核，跳过准入
                            application.setIsStep(Constants.RULE_ID_SET_STEP);
                            application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
//				application.setDistributionTime(DateUtils.parseToDateTimeStr(new Date()));
                            applicationServiceImpl.updateEntity(application);
                        }
                    }

                }catch (Exception ex){
                    //命中的申请单直接拒绝
//					application.setAuditingState(DictConstant.AUDITING_STATE_THREE);
                    application.setAuditingTime(DateUtils.parseToDateTimeStr(new Date()));
                    application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
//					application.setAuditResult("准入规则检查异常");
                    application.setFailureTimes("1");
                    String errorInfo = ex.getMessage();
                    application.setErrorDescription(StringUtils.substring(errorInfo,0,450));
                    applicationServiceImpl.updateEntity(application);
                }
            }else {
                //老用户直接进行黑名单审核
                application.setIsStep(Constants.RULE_ID_SET_STEP);
                application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
//				application.setDistributionTime(DateUtils.parseToDateTimeStr(new Date()));
                applicationServiceImpl.updateEntity(application);
            }
        }

        logger.info("借款申请单准入规则更新审批单处理条数： " + list.size() + "，处理结束");

    }

	public Application selectApplicationByParam(Map<String, String> param) {
		return applicationDao.selectApplicationByParam(param);
	}
	
	public Application findByCustomerIdAndAuditingState(Map<String, String> param) {
		return applicationDao.findByCustomerIdAndAuditingState(param);
	}
	
	
	public Application findLastApplicationByCustomerId(Map<String, String> param) {
		return applicationDao.findLast2ApplicationByCustomerId(param).get(1);
	}
	
}
