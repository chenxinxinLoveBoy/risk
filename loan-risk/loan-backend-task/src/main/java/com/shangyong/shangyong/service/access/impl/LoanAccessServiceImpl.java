package com.shangyong.shangyong.service.access.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.*;
import com.shangyong.backend.common.enums.*;
import com.shangyong.backend.dao.BuApplicationDetailDao;
import com.shangyong.backend.dao.CuIcePersonDao;
import com.shangyong.backend.dubbo.impl.GetCustomerInfoService;
import com.shangyong.backend.entity.*;
import com.shangyong.backend.entity.approval.CustomerDirectories;
import com.shangyong.backend.entity.redis.LinkManMergeRedis;
import com.shangyong.backend.entity.redis.fraud1_8.LinkMan18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.LinkMan20Redis;
import com.shangyong.backend.service.*;
import com.shangyong.backend.service.approval.service.CustomerDirectoriesService;
import com.shangyong.backend.service.approval.service.impl.CustomerDirectoriesServiceImpl;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.impl.ScBanControlServiceImpl;
import com.shangyong.backend.service.impl.ScTemplateServiceImpl;
import com.shangyong.backend.utils.BanCodeRelUtil;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.DirectoriesRuleUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.AppAddressBook;
import com.shangyong.shangyong.service.access.LoanAccessService;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;
import net.sf.json.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 申请单信息 service实现类
 *
 * @author
 * @date
 */
@Service
public class LoanAccessServiceImpl implements LoanAccessService {

    private static Logger logger = LoggerFactory.getLogger("LoanAccessServiceImpl");

    @Autowired
    private ApplicationServiceImpl applicationServiceImpl;

    @Autowired
    private SysParamRedisService sysParamRedisService;
    
    @Autowired
	private GetCustomerInfoService getCustomerInfoService;
    
    @Autowired
	private CustomerDirectoriesService customerDirectoriesService;
    
    @Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

    @Autowired
    private BuApplicationDetailDao buApplicationDetailDao;

	@Autowired
	private ScTemplateServiceImpl scTemplateService;
	
	@Autowired
	private ScBanControlServiceImpl scBanControlService;

	@Autowired
	private RiskRuleCheckService riskRuleCheckService;
	
	@Autowired
	private BlacklistService blacklistService;
    
    @Autowired
    private BuThirdpartyReportService buThirdpartyReportService;
    
    @Autowired
    private MongoUtils mongoUtils;
    
    @Autowired
    private ZhiMaIndustryDetailsListService zhiMaIndustryDetailsListService;

	@Autowired
	private ScRiskWhiteListRedisService scRiskWhiteListRedisService;
	
	@Autowired
	private CuIcePersonDao cuIcePersonDao;
	
    /**
     * 借款申请单的准入检查
     * 更新审批单准入规则信息
	 *
	 * @param application
	 * @return
	 */
	@Transactional
	@Override
    public boolean processApplication(Application application){
    	boolean flagOldUser = false;
        if(ObjectUtils.isEmpty(null)){
        	logger.info("[准入规则] 准入检查的申请单数量为0");
            return flagOldUser;
        }
		String applicationId = application.getApplicationId();
		logger.info("[准入规则] 开始校验，申请单号：" + applicationId);

		//进件流程,准入包的操作也记录到借款申请扩展表中；
		buThirdpartyReportService.saveOrUpdateThirdpartyReport(applicationId,null, Constants.THIRDPARTY_REPORT_XIAO_NIU,"");
		try{
			/**
			 * 判断是否是老用户
			 */
			if(Constants.APP_LEVEL.equals(application.getAppLevel())){
				SysParam sysParamSwitch = sysParamRedisService.querySysParamByParamValueRedis(Constants.SWITCH_OLD_USER_OVER_30);
				if(null != sysParamSwitch && ScParamSwitchEnum.ON.getCode().equals(sysParamSwitch.getParamValueOne())){
					// 距上一次风控通过的时间  > 30 走新用户准入；  <=30天走老用户模型
					Map<String,String> param = new HashMap<String,String>();
					param.put("customerId", application.getCustomerId());
					Application lastApplication = applicationServiceImpl.findLastApplicationByCustomerId(param);
					Date lastApplicationDate = DateUtils.convertStringToDate(lastApplication.getCreateTime());
					Date currentTime = new Date();
					int betweenDays = DateUtils.daysBetween(lastApplicationDate, currentTime);
					if(betweenDays <= 30) {
						flagOldUser =processApplicationForOldUser(application);
						return flagOldUser;
					}
				}else{
					flagOldUser =processApplicationForOldUser(application);
					return flagOldUser;
				}
			}
			//获取准入规则状态 1：生效 0：失效
			SysParam loanAccessFlag = sysParamRedisService.querySysParamByParamValueRedis(Constants.XIAO_NIU_RULE_ON_OFF);
			if(loanAccessFlag != null){
				//1 开， 0 关 为1的时候校验准入规则
				if (DictConstant.IS_FAILURE_ONE.equals(loanAccessFlag.getParamValueOne())) {
					//准入规则，默认为新用户
					String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;
					//获取准入规则的模块ID
					SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);
					SysParam isAllStep = sysParamRedisService.querySysParamByParamValueRedis(Constants.ISUSE_ALL_STEP);
					String flag = isAllStep.getParamValueOne();
					//准入规则校验
					RuleResult ruleResult = this.validateAccessRule(application,flag);
					if(ruleResult != null && ruleResult.getIsblack()){
						if("1".equals(flag)){
							application.setIsStep(Constants.RULE_ID_SET_STEP);
							application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
							application.setAuditResult(ruleResult.getMessage());
							application.setBanCode(ruleResult.getBlackCode());
							// 能正常走下去的都要清空失败次数和异常信息
							application.setFailureTimes("0");
							application.setErrorDescription("");
                            application.setBlacklistDsSource(ruleResult.getDsSource());
                            application.setBanClassCode(ruleResult.getBanCodeClassCode());

							applicationServiceImpl.updateEntity(application);

							List<Content> contents = ruleResult.getContents();
							//插入所有的命中项
							saveBuApplicationDetail(applicationId, contents);
						}else{
							List<Content> contents = ruleResult.getContents();
							//插入所有的命中项
							saveBuApplicationDetail(applicationId, contents);

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

							application.setBlacklistDsSource(ruleResult.getDsSource());
							application.setBanClassCode(ruleResult.getBanCodeClassCode());
							applicationServiceImpl.updateEntity(application);
						}
					} else {
						//未命中的申请单进行下一步校验
						application.setIsStep(Constants.RULE_ID_SET_STEP);
						application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
						//能正常走下去的都要清空失败次数和异常信息
						application.setFailureTimes("0");
						application.setErrorDescription("");
						applicationServiceImpl.updateEntity(application);
					}
				}else{
					//老用户直接进行外下一步审核，跳过准入
					application.setIsStep(Constants.RULE_ID_SET_STEP);
					application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
					applicationServiceImpl.updateEntity(application);
				}
			}

		}catch (Exception ex){
			//命中的申请单直接拒绝
			application.setAuditingTime(DateUtils.parseToDateTimeStr(new Date()));
			application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
			application.setFailureTimes("1");
			String errorInfo = ex.getMessage();
			application.setErrorDescription(StringUtils.substring(errorInfo,0,450));
			applicationServiceImpl.updateEntity(application);
			logger.error("[准入规则] 校验[applicationId=" + applicationId+"]异常", ex);
		}
        logger.info("[准入规则] 借款申请单准入规则更新审批单处理条数： " + application.getApplicationId() + "，处理结束");
        return flagOldUser;
    }

    /**
     * 插入所有的命中项
     */
    private void saveBuApplicationDetail(String applicationId, List<Content> contents){
        if(CollectionUtils.isEmpty(contents)){
            return;
        }
        for(Content content : contents){
            saveBuApplicationDetail(applicationId, content);
        }
    }

    /**
     * 保存 application_detail 数据
     * @param applicationId
     * @param content
     */
    private void saveBuApplicationDetail(String applicationId, Content content){
        if(null == content){
            return;
        }
        BuApplicationDetail applicationDetail=new BuApplicationDetail();
        applicationDetail.setBuApplicationDetailId(UUIDUtils.getUUID());
        applicationDetail.setApplicationId(applicationId);
        applicationDetail.setCreateTime(DateUtils.getDate(new Date()));
        applicationDetail.setBanCode(content.getBlackCode());
        applicationDetail.setState(3);
        applicationDetail.setDescrip(content.getMessage());
        applicationDetail.setStepNum(Integer.valueOf(Constants.SY_RULE_ID));
        applicationDetail.setBanClassCode(content.getBanCodeClassCode());
        applicationDetail.setBlacklistDsSource(content.getDsSource());

        int saveEntity = buApplicationDetailDao.saveEntity(applicationDetail);
        if (saveEntity>0) {
            logger.info("黑名单备表信息入库成功：当前步骤号" + Integer.parseInt(Constants.SY_RULE_ID) + "命中信息：" + content.getMessage());
        }
    }

	/**
	 * 准入规则校验
	 * @param application
	 * @param flagUp
	 * @return
	 * @throws Exception
	 */
    private RuleResult validateAccessRule(Application application, String flagUp) throws Exception {
		RuleResult result = new RuleResult();
		RuleResult resultSy = new RuleResult();
		List<Content> contents = new ArrayList<Content>();
		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		String appLevel = application.getAppLevel();
		String certCode = application.getCertCode();
		String appName = application.getAppName();
		String phoneNum = application.getPhoneNum();
		String idCode = certCode.substring(0,4);
		//户籍地址校验
		String registeredAddress = certCode.substring(0,2);
		//白名单校验
		logger.info("[准入规则校验] 开始校验白名单信息 [applicationId="+applicationId+"] [appName="+appName+"] [certCode="+certCode+"]");
		boolean isWhite = scRiskWhiteListRedisService.queryRiskWhite(appName, certCode, Constants.STATE_NORMAL);
		if(isWhite){
			result.setMessage("[准入规则校验] 白名单客户");
			return result;
		}

		String banCodeTplId = "";

		//准入规则，默认为新用户
		String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;

		logger.info("[准入规则校验] 开始校验 [applicationId="+applicationId+"] [appSerialNumber="+appSerialNumber + "]");

		//获取准入规则的模块ID
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);

		banCodeTplId = sysParam.getParamValueOne();
		
		//获取准入规则禁止项模板
		ScBanControlTpl scBanControlTpl = scTemplateService.getEntityById(banCodeTplId);

		//规则停用，则继续校验下一个规则
		if(scBanControlTpl == null || Constants.STATE_FORBIDDEN.equals(scBanControlTpl.getState())){
		    logger.error("[准入规则校验] 规则停用 [applicationId=" + applicationId + "] [banCodeTplId=" + banCodeTplId  + "]");
		    throw new RuntimeException("[准入规则校验] 规则停用 [applicationId=" + applicationId + "] [banCodeTplId=" + banCodeTplId  + "]");
		}

		//根据模板ID获取所有禁止项集合
		ScBanControl scBanControlParam = new ScBanControl();
		scBanControlParam.setBanControlTplId(banCodeTplId);
		scBanControlParam.setState(Constants.STATE_NORMAL);
		List<ScBanControl> scBanControlList = scBanControlService.findAll(scBanControlParam);
		if (CollectionUtils.isEmpty(scBanControlList) || scBanControlList.size() < 1) {
		    logger.error("[准入规则校验] 规则模板中的生效规则项不存在，模板号：" + banCodeTplId);
		    throw new RuntimeException("[准入规则校验] 规则模板中的生效规则项不存在，模板号：" + banCodeTplId);
		}
		
		Object checkValue = null;
		for (int i = 0; i < scBanControlList.size(); i++) {
			ScBanControl scBanControlElem = scBanControlList.get(i);
			String banCode = scBanControlElem.getBanCode();
			
			// 判断芝麻分是否中禁止项
			if (BanCodeEnum.ZHI_MA_SCORES.getCode().equals(banCode)) {
				resultSy = validateZhiMaScoreBanCode(application, scBanControlElem, banCodeTplId);
				if(null != resultSy && resultSy.getIsblack()) {
					return resultSy;
				}
			}
			// 判断行业清单风险等级是否中禁止项
			if (BanCodeEnum.ZHI_MA_INDUSTRY.getCode().equals(banCode)) {
                resultSy = validateZhiMaIndustryBanCode(application, scBanControlElem, banCodeTplId);
                if(null != resultSy && resultSy.getIsblack()) {
                    return resultSy;
                }
			}
			// 判断紧急联系人命中黑名单是否中禁止项
			if(BanCodeEnum.BLACKLIST_EMERGENCY_CONTACT.getCode().equals(banCode)){
                resultSy = validateBlacklistEmergencyContactBanCode(scBanControlElem, application, banCodeTplId);
                if(null != resultSy && resultSy.getIsblack()) {
                    return resultSy;
                }
			}
		}

		//校验是否验证黑名单
        IsBlacklistResult flag = this.checkCusBlack(banCodeTplId, appName, applicationId, certCode, appLevel, phoneNum);
		if (flag.isBlacklistFlag()) {
			buildResult(result, flag, contents);
			result.setContents(contents);
			logger.info("[准入规则校验] 用户命中黑名单 [applicationId="+applicationId +"]");
			return result;
		}
		if (Constants.APP_LEVEL.equals(appLevel)) {
			if (!CollectionUtils.isEmpty(contents) && contents.size() > 0) {
				result.setContents(contents);
				return result;				
			}else{
				return result;
			}
		}

		//根据申请单的平台客户编号，获取客户资料
		CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
		cuPlatformCustomer.setCustomerId(application.getCustomerId());
		cuPlatformCustomer.setAppName(application.getAppName());

		//身份证过期时间
		String expirationDate = "";
		//年龄
		int age = 0;
		//身份证所在区域
		String issueInstitution = "";
		//公司名称
		String companyName = null;
		//公司行业
		String companyIndustry = null;
		try {
			CustomerInfo customerInfo = getCustomerInfoService.getEntityById(cuPlatformCustomer);
			if(customerInfo == null || customerInfo.getCuPlatformCustomer() == null){
		        logger.error("[准入规则校验] [applicationId="+applicationId + "] 未找到用户信息请确认用户存在，或检查dubbo服务是否正常");
		        throw new RuntimeException("[准入规则校验] [applicationId="+applicationId + "] 未找到用户信息请确认用户存在，或检查dubbo服务是否正常");
			}

			application.setPlatformId(customerInfo.getCuPlatformCustomer().getPlatformCustomerId());

			//身份证过期时间
			expirationDate = customerInfo.getCuPlatformCustomer().getExpirationDate();
			//年龄
			age = customerInfo.getCuPlatformCustomer().getAge();
			//身份证所在区域
			issueInstitution = customerInfo.getCuPlatformCustomer().getIssueInstitution();
			//公司名称
			if(customerInfo.getCuCustomerCompany() != null){
				companyName = customerInfo.getCuCustomerCompany().getCompanyName();
			}
			//公司行业
			String company = customerInfo.getCuCustomerCompany().getCompanyIndustry();
			if (StringUtils.isNotBlank(company)) {
				companyIndustry = company;
			}
		} catch (Exception ex) {
		    throw new RuntimeException("[准入规则校验] [applicationId="+applicationId + "] 获取用户资料失败，错误信息：" + ex.getMessage(), ex);
		}
		logger.info("[准入规则校验] 获取基本信息成功 [applicationId="+applicationId
				+ "] [expirationDate=" + expirationDate
				+ "] [age=" + age
				+ "] [issueInstitution=" + issueInstitution
				+ "] [companyName=" + companyName + "]");

		//手机通讯录个数
		int telDirectoryNum = -1;

		for (ScBanControl scBanControl: scBanControlList) {
			boolean isCheck = true;
			String banCode = scBanControl.getBanCode();//禁止项编号
//				String ifRefuse = scBanControl.getIfRefuse();//是否添加拒绝黑名单
//				String creditType = scBanControl.getCreditType();//征信机构类型
//				String ruleName = scBanControl.getRuleName();//禁止项规则名称


			if(BanCodeEnum.XIAO_NIU_CARD_AGE_CEILING.getCode().equals(banCode)){
				//校验用户年龄限制
				checkValue = age;
			}else if(BanCodeEnum.XIAO_NIU_CARD_AGE_FLOOR.getCode().equals(banCode)){
				//用户年龄限制
				checkValue = age;
			}else if(BanCodeEnum.XIAO_NIU_CARD_REGION.getCode().equals(banCode)){
				//用户户口地址黑名单区域校验
				checkValue = issueInstitution;

			}else if(BanCodeEnum.HONG_CHANG_CARD_UNIT.getCode().equals(banCode)){
				//用户户口身份证区域校验
				checkValue = idCode;
			}else if(BanCodeEnum.CENSUS_REGISTER_AREA.getCode().equals(banCode)){
				//用户户籍区域校验
				checkValue = registeredAddress;
			}else if(BanCodeEnum.HONG_CHENG_CHARACTER_WU.getCode().equals(banCode)){
				checkValue = companyName;
			}else if(BanCodeEnum.HONG_CHANG_COMPANY_UNIT.getCode().equals(banCode)){
				//用户行业校验
				checkValue = companyIndustry;
			}else if (BanCodeEnum.XIAO_NIU_CARD_VALID.getCode().equals(banCode)) {
				//用户身份证有效期校验
				try {
					expirationDate = expirationDate.split("-")[1];
					Date endDate = DateUtils.convertStringToDate(DateUtils.FORMAT_STR1,expirationDate);
					checkValue = DateUtils.daysBetween(new Date(),endDate);
				} catch (Exception e) {
					logger.error("[准入规则校验] [applicationId="+applicationId + "] 身份证过期时间格式不正确过期时间=" + expirationDate);
					checkValue = 1;
				}

			}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode().equals(banCode)){
				//用户单位限制
				checkValue = companyName;

			}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT_NUM.getCode().equals(banCode)){
				//用户单位限制
				if(companyName != null){
					//全数字，包含负数
					String regEx = "^-?[0-9]+$";
					Pattern pat = Pattern.compile(regEx);
					Matcher mat = pat.matcher(companyName);
					if (mat.matches()) {
						//直接返回
						//命中则直接返回，结束
						checkValue = scBanControl.getRuleComparisonValue();
					}else{
						checkValue = companyName;
					}
				}else {
					checkValue = companyName;
				}
			//用户手机通讯录数量校验（ios由于权限的限制，默认跳过）
			}else if(DictConstant.XIAO_NIU_CONTACTS.equals(banCode)){
				
				/*String source = application.getSource();
				if ("1".equals(source)) {
					//iOS直接通过
					telDirectoryNum = 99999;
					
				}else {	*/
				Map<String, Object> paramMap = new HashMap<String,Object>();
				paramMap.put("customerId", application.getCustomerId());
				// 查询 mogongdb
				List<Order> orderList = new ArrayList<Order>();
				Order order = new Order(Direction.DESC,"createTimeLong");
				orderList.add(order);
				AppAddressBook appAddressBook = (AppAddressBook) mongoUtils.findByClazz(paramMap, orderList, AppAddressBook.class);

				LinkManMergeRedis linkManMergeRedis = new LinkManMergeRedis();
				if(appAddressBook != null){
					JSONArray jsonArray = JSONArray.fromObject(appAddressBook.getJsonInfo());
					if (jsonArray != null) {
						List<CustomerDirectories> directories = DirectoriesRuleUtils.paresToCustomerDirectories(jsonArray);
						
						telDirectoryNum = customerDirectoriesService.regulateDirectoriesByNelist(applicationId, application.getCustomerId(), directories);

						ScLinkmanTypeCount count = CustomerDirectoriesServiceImpl.checkNormalByContactName(directories);
						
						linkManMergeRedis.setNormalLinkmanCnt(String.valueOf(count.getNormalLinkmanCnt()));
						linkManMergeRedis.setAlienLinkmanCnt(String.valueOf(count.getAlienLinkmanCnt()));
						linkManMergeRedis.setAlienNum8or12LinkmanCnt(String.valueOf(count.getAlienNum8or12LinkmanCnt()));
						linkManMergeRedis.setLinkmanCnt(String.valueOf(count.getLinkmanCnt()));
					}
				}
				// 放入到redis中
				// 评分卡 1.8
				LinkMan18Redis linkMan18Redis = new LinkMan18Redis();
				BeanUtils.copyProperties(linkManMergeRedis, linkMan18Redis);
				String key = RedisConstant.buildFraudScoresKey1_8(application.getApplicationId(), FraudBizEnum.ADDRESS_BOOK);
				RedisFraudUtils.hmset(key, linkMan18Redis.toMap());

				// 评分卡 2.0
				LinkMan20Redis linkMan20Redis = new LinkMan20Redis();
				BeanUtils.copyProperties(linkManMergeRedis, linkMan20Redis);
				String key20 = RedisConstant.buildFraudScoresKey2_0(application.getApplicationId(), FraudBizEnum.ADDRESS_BOOK);
				RedisFraudUtils.hmset(key20, linkMan20Redis.toMap());

//					}
				checkValue = telDirectoryNum;
			}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode().equals(banCode)){
				checkValue = companyName;
			}else{
		        isCheck = false;
		    }
			//进行用户规则校验，根据入库黑名单状态，将用户添加到我们自己的黑名单数据库中
		    if (isCheck) {
		    	resultSy = this.checkValueForAdmittance(scBanControl, application, banCodeTplId, checkValue);
		        if(resultSy.getIsblack()) {
		        	result.setIsblack(resultSy.getIsblack());
		        	result.setBlackCode(resultSy.getBlackCode());
		        	result.setMessage(resultSy.getMessage());
                    result.setBanCodeClassCode(resultSy.getBanCodeClassCode());
                    result.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
                    result.setState(RuleResult.STATUS_YES);

		        	Content content = new Content();
		        	content.setState(ContentStateEnum.HIT.getCode());
		        	content.setBlackCode(result.getBlackCode());
		        	content.setMessage(result.getMessage());
                    content.setBanCodeClassCode(resultSy.getBanCodeClassCode());
                    content.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
		        	contents.add(content);
		        }
		    }
		}

		if (contents != null && contents.size()>0) {
			result.setContents(contents);
			return result;
		}

		logger.info("[准入规则校验] [applicationId="+applicationId + "] 规则校验结束");
		result.setState(RuleResult.STATUS_NO);
		result.setIsblack(false);
		return result;
	}

	/**
	 * 中黑明单 和 设备黑名单 后构建 结果
	 * @param result
	 * @param isBlacklistResult
	 * @param contents
	 */
	private void buildResult(RuleResult result, IsBlacklistResult isBlacklistResult, List<Content> contents){
		result.setState(RuleResult.STATUS_YES);
		result.setIsblack(isBlacklistResult.isBlacklistFlag());
		result.setBlackCode(isBlacklistResult.getBanCode());
		result.setMessage(isBlacklistResult.getRemark());
		result.setDsSource(isBlacklistResult.getDsSource());
		result.setBanCodeClassCode(isBlacklistResult.getClassCode());

		Content content = new Content();
		content.setState(ContentStateEnum.HIT.getCode());
		content.setBlackCode(isBlacklistResult.getBanCode());
		content.setMessage(isBlacklistResult.getRemark());
		content.setDsSource(isBlacklistResult.getDsSource());
		content.setBanCodeClassCode(isBlacklistResult.getClassCode());

		contents.add(content);

	}

	/**
	 * 校验 芝麻分 禁止项
     * @param application
     * @param scBanControl
     * @param banCodeTplId
     */
	private RuleResult validateZhiMaScoreBanCode(Application application, ScBanControl scBanControl, String banCodeTplId){
		String zhiMaScore = application.getZhiMaScore();
		//判断芝麻分是否为null值  如果不为null执行禁止项，为null直接通过
		if(StringUtils.isNotBlank(zhiMaScore)){
			String checkValue = zhiMaScore.replaceAll(StringUtil.SPACE_STR, "");
			logger.info("[准入规则校验] [芝麻分]禁止项任务开始处理 [applicationId=" + application.getApplicationId()
					+ "] [banCode=" + scBanControl.getBanCode()
					+ "] [banCodeTplId=" + scBanControl.getBanControlId()
					+ "] [checkValue=" + checkValue  + "]");

			//芝麻分禁止项准入规则检查
			RuleResult result = this.checkValueForAdmittance(scBanControl, application, banCodeTplId, checkValue);

            List<Content> contents = new ArrayList<>();
            Content content = new Content();
            content.setState(ContentStateEnum.HIT.getCode());
            content.setBlackCode(result.getBlackCode());
            content.setMessage(result.getMessage());
            content.setDsSource(result.getDsSource());
            content.setBanCodeClassCode(BanCodeRelUtil.REL_BAN_CLASS_CODE.get(result.getBlackCode()));
            contents.add(content);

            logger.info("[准入规则校验] [芝麻分] [applicationId=" + application.getApplicationId() + "] 命中芝麻分禁止项");
            result.setContents(contents);
			return result;
		}else{
			logger.info("[准入规则校验] [芝麻分] [applicationId=" + application.getApplicationId() + "] 用户芝麻分为null");
			return null;
		}
	}

    /**
     * 校验 芝麻行业清单 禁止项
     * @param application
     * @param scBanControl
     * @param banCodeTplId
     * @return
     */
	private RuleResult validateZhiMaIndustryBanCode(Application application, ScBanControl scBanControl, String banCodeTplId){
        List<ZhiMaIndustryDetails> zhiMaIndustryDetailsList = zhiMaIndustryDetailsListService.getEntityById(application.getApplicationId());

        //如果行业清单数据不为空执行禁止项，如果行业清单数据为空直接通过
        if(!zhiMaIndustryDetailsList.isEmpty() && zhiMaIndustryDetailsList.size() > 0){
            for (ZhiMaIndustryDetails zhiMaIndustryDetails : zhiMaIndustryDetailsList) {
                //行业关注清单禁止项准入规则检查
                //由原先的风险等级改为行业类型、风险类型、风险等级同时命中 --曾繁棋修改
                String checkValue = zhiMaIndustryDetails.getBizCode()
                        + "_" + zhiMaIndustryDetails.getZhiMaType()
                        + "_" + zhiMaIndustryDetails.getZhiMaLevel();

                RuleResult result = this.checkValueForAdmittance(scBanControl, application, banCodeTplId, checkValue);
                logger.info("[准入规则校验] [芝麻行业清单]禁止项检查 [applicationId=" + application.getApplicationId()
                        + "] [banCode= " + scBanControl.getBanCode()
                        + "] [banCodeTplId=" + banCodeTplId
                        + "] [checkValue" + checkValue
                        + "] [result=" + result + "]");
                if(result.getIsblack()) {
                    List<Content> contents = new ArrayList<>();

                    Content content = new Content();
                    content.setState(ContentStateEnum.HIT.getCode());
                    content.setBlackCode(result.getBlackCode());
                    content.setMessage(result.getMessage());
                    content.setDsSource(result.getDsSource());
                    content.setBanCodeClassCode(BanCodeRelUtil.REL_BAN_CLASS_CODE.get(result.getBlackCode()));
                    contents.add(content);

                    result.setContents(contents);
                    logger.info("[准入规则校验] [芝麻行业清单] [applicationId=" + application.getApplicationId() + "] 命中行业清单禁止项");
                    return result;
                }
            }
        }
        logger.info("[准入规则校验] [芝麻行业清单] [applicationId=" + application.getApplicationId() + "] 用户芝麻行业清单数据为空");
        return null;
    }

    /**
     * 判断紧急联系人命中黑名单是否中禁止项
     * @param scBanControl
     * @param application
     * @param banCodeTplId
     * @return
     */
    private RuleResult validateBlacklistEmergencyContactBanCode(ScBanControl scBanControl, Application application, String banCodeTplId){
        //获取紧急联系人
        List<CuIcePerson> cuIcePersonList = cuIcePersonDao.getEntityByApplicationId(application.getApplicationId());

        if(!CollectionUtils.isEmpty(cuIcePersonList)){
            //紧急联系人准入规则检查
            RuleResult result = new RuleResult();

            BuBlacklist self =  new BuBlacklist();
            self.setsNumber(application.getApplicationId());
            self.setName(application.getName());
            self.setPhoneNum(application.getPhoneNum());
            self.setCertCode(application.getCertCode());
            self.setCertType(application.getCertType());
            self.setCustomerId(application.getCustomerId());
            self.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());

            List<BuBlacklist> buBlackCuIcePersonList = new ArrayList<>();

            for(CuIcePerson elem : cuIcePersonList){
                BuBlacklist buBlacklist = new BuBlacklist();
                buBlacklist.setPhoneNum(elem.getPhoneNum());
                buBlackCuIcePersonList.add(buBlacklist);
            }
            IsBlacklistResult isBlacklistResult = blacklistService.contactsIsBlacklist(self, buBlackCuIcePersonList);
            if(isBlacklistResult.isBlacklistFlag()){

                result.setIsblack(true);
                result.setState(RuleResult.STATUS_YES);
                result.setBlackCode(isBlacklistResult.getBanCode());
                result.setMessage(isBlacklistResult.getRemark());
                result.setBanCodeClassCode(isBlacklistResult.getClassCode());
                result.setDsSource(isBlacklistResult.getDsSource());

                List<Content> contents = new ArrayList<>();
                Content content = new Content();
                content.setState(ContentStateEnum.HIT.getCode());
                content.setBlackCode(result.getBlackCode());
                content.setMessage(result.getMessage());
                content.setBanCodeClassCode(isBlacklistResult.getClassCode());
                content.setDsSource(isBlacklistResult.getDsSource());

                contents.add(content);
                result.setContents(contents);

                logger.info("[准入规则校验] [applicationId=" + application.getApplicationId() + "] 命中 [用户紧急联系人黑名单]");
                return result;
            }
        }
        logger.info("[准入规则校验] [applicationId=" + application.getApplicationId() + "] 未命中 [用户紧急联系人黑名单]  ");
        return null;
    }

    /**
	 * 准入规则检查
	 * @param scBanControl 禁止项规则信息
	 * @param application 申请单对象
	 * @param banCodeTplId 禁止项模板id
	 * @param checkValue 校验参数
	 * @return
	 */
	private RuleResult checkValueForAdmittance(ScBanControl scBanControl, Application application, String banCodeTplId, Object checkValue){
		RuleResult result = new RuleResult();

		//检验白名单信息
		String appName = application.getAppName();
		String certCode = application.getCertCode();

		/**禁止项规则技术比对值**/
		String ruleValue = scBanControl.getRuleComparisonValue();
		/**技术比对值类型（01-数值、02-字符、03-集合）**/
		String ruleComparisonType = scBanControl.getRuleComparisonType();
		/**技术校验规则 **/
		String validateRule = scBanControl.getValidateRule();
		/**禁止项规则名称**/
		String ruleName = scBanControl.getRuleName();
		/** 征信机构类型**/
		String creditType = scBanControl.getCreditType();
		/** 是否添加拒绝名单:0-否，1-是 **/
		String ifRefuse = scBanControl.getIfRefuse();

		String banCode = scBanControl.getBanCode();
		String codeName = banCode + ruleName ; //命中规则code和名称
		
		boolean flag  = false;
		/** 校验规则是否命中，true命中 **/
		flag  = this.riskRuleCheckService.ruleCheck(ruleComparisonType, validateRule, ruleValue, checkValue);

		//命中则直接返回，结束
		if (flag) {
			if (ScBanControlIfRefuseEnum.ADD.getCode().equals(ifRefuse)) {
				//审批拒绝的信息，添加黑名单表
				BuBlacklist buBlacklist = new BuBlacklist();
				buBlacklist.setBlacklistId(UUIDUtils.getUUID());
				buBlacklist.setAppName(appName);
				buBlacklist.setPlatformId(application.getPlatformId());
				buBlacklist.setCertCode(application.getCertCode());
				buBlacklist.setCertType(application.getCertType());
				buBlacklist.setCustomerId(application.getCustomerId());
				buBlacklist.setPhoneNum(application.getPhoneNum());
				buBlacklist.setName(application.getName());
				buBlacklist.setRejectType(creditType);
				buBlacklist.setIsFailure("1");
				buBlacklist.setBanCode(banCode);

				String classCode = BanCodeRelUtil.REL_BAN_CLASS_CODE.get(banCode);
				buBlacklist.setClassCode(classCode);

				buBlacklist.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
				buBlacklist.setsNumber(application.getApplicationId());
				buBlacklist.setRemark(ruleName);

				boolean saveBlack = blacklistService.saveEntity(buBlacklist);
				if (saveBlack) {
					logger.info("[准入规则校验] 黑名单信息入库成功 [applicationId=" + application.getApplicationId()
							+ "] [appName=" + application.getAppName()
							+ "] [certCode=" + certCode +"]");
				}
			}
			result.setIsblack(flag);
			result.setState(RuleResult.STATUS_YES);
			result.setBlackCode(scBanControl.getBanCode());
			result.setMessage(ruleName);
			result.setBanCodeClassCode(BanCodeRelUtil.REL_BAN_CLASS_CODE.get(scBanControl.getBanCode()));

			result.setExt("[准入规则校验] [applicationId=" + application.getApplicationId()
					+ "] 检测准入规则项，命中 [banCodeTplId=" + banCodeTplId
					+ "] [ruleName=" + ruleName
					+ "] [banCode=" + banCode
					+ "] [checkValue=" + checkValue+"]");
		}

		logger.info("[准入规则校验] [applicationId=" + application.getApplicationId()
				+ "] 检测准入规则项 [banCodeTplId=" + banCodeTplId
				+ "] [state=" + scBanControl.getState()
				+ "] [message=" + codeName
				+ "] [isblack=" + flag
				+ "] [ruleValue=" + ruleValue
				+ "] [checkValue=" + checkValue +"]");
		return result;
	}

	/**
	 * 根据证件号码判断是否存在闪贷拒绝名单,如存在，则更新审批单状态为审批不通过
	 * @param banControlTplId 禁止项编号
	 * @param appName	应用编号
	 * @param applicationId  申请单编号
	 * @param certNo	证件号码
	 * @param appLevel 老用户标识
	 * @return 在拒绝名单为true,不存在则为false
	 * @throws Throwable
	 */
	private IsBlacklistResult checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String appLevel, String phoneNum){
		
		boolean flag = false;

		IsBlacklistResult temp = blacklistService.userIsBlacklist(certNo, phoneNum, null);

    	logger.info("[准入规则校验] [applicationId=" + applicationId + "] 检测结果 [blackFlag = " + temp.isBlacklistFlag()+"]");

		//用户存在黑名单中
		if (temp.isBlacklistFlag()) {
			temp.setClassCode(BanCodeRelUtil.REL_BAN_CLASS_CODE.get(BanCodeEnum.BLACKLIST_USER.getCode()));

			flag = true;
			SysParam isUseAllStep = sysParamRedisService.querySysParamByParamValueRedis(Constants.ISUSE_ALL_STEP);
			String isUseAllStepFlag = isUseAllStep.getParamValueOne();
		    if("1".equals(isUseAllStepFlag)){
		       return temp;
		    }
			Map<String, Object> blackMap = new HashMap<String, Object>();
			blackMap.put("isStep", Constants.NO_PASS_STEP); //步骤标识：99 表示被黑名单拒绝
			blackMap.put("auditResult", "用户存在洪澄科技拒绝名单");
			blackMap.put("auditingState", Constants.NOPASS_SP_STAATE);//审核不通过
			blackMap.put("banCode", BanCodeEnum.BLACKLIST_USER.getCode());//禁止项规则对应编号
			blackMap.put("applicationId",applicationId);
			blackMap.put("currentStep", Constants.SY_RULE_ID);//当前潘多拉黑名单
			blackMap.put("blacklistDsSource", temp.getDsSource());
			blackMap.put("banClassCode", temp.getClassCode());

			int updateCount = tdLoanInterfaceService.updateApplicationByStep(blackMap);
			logger.info("[准入规则校验] [applicationId=" + applicationId + "] 用户证件号码存在洪澄科技拒绝名单,更新审批单状态为审批不通过,更新记录数：" + updateCount);
		}
		return temp;
	}

	/**
	 * 老用户 处理
	 *  判断完黑名单后，直接发送消息给rabbitmq 进行 评分模型计算
	 * @param application
	 */
	private boolean processApplicationForOldUser(Application application){
		//直接判断用户是否存在与黑名单中
		//直接判断用户是否存在与黑名单中
		SysParam sysParamSwitch = sysParamRedisService.querySysParamByParamValueRedis(Constants.SWITCH_OLD_USER_MATCH_BLACKLIST);

		boolean isPass = true;
		if(null != sysParamSwitch && ScParamSwitchEnum.ON.getCode().equals(sysParamSwitch.getParamValueOne())){
			boolean blackFlag = blacklistService.isInBlacklist(application.getCertCode(), application.getPhoneNum(), "");
			if(blackFlag){
				// 已经是黑名单
				application.setAuditingState(DictConstant.AUDITING_STATE_THREE);
				application.setAuditingTime(DateUtils.parseToDateTimeStr(new Date()));
				application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
				application.setAuditResult(Constants.BLACK_STEP_NAME);
				application.setBanCode(Constants.BLACK_STEP);
				application.setIsStep(Constants.SY_RULE_ID);
				application.setFailureTimes("0");
				application.setErrorDescription("");
				applicationServiceImpl.updateEntity(application);

				Content content = new Content();
				content.setState(ContentStateEnum.HIT.getCode());
				content.setBlackCode(Constants.BLACK_STEP);
				content.setMessage(Constants.BLACK_STEP_NAME);
				saveBuApplicationDetail(application.getApplicationId(), content);

				logger.info("[准入规则] [application=" + application.getApplicationId() + "] 老用户命中黑名单-拒绝");
				isPass = false;
			}
		}

		if (isPass) {
			//查询 系统参数
			SysParam sysParam = this.sysParamRedisService.querySysParamByParamValueRedis(Constants.SC_PARAM_HZ_OLD_USER_FRAUD_TPL_ID);

			if(null == sysParam || StringUtils.isBlank(sysParam.getParamValueOne())){
				throw new RuntimeException("[准入规则] 老用户[评分模板决策]配置错误:没有查询[" + Constants.SC_PARAM_HZ_OLD_USER_FRAUD_TPL_ID + "]向配置");
			}

			application.setFraudTplId(sysParam.getParamValueOne());

			/*
			 * 注意: 这里的步骤号 要设置成 SH_CREDIT_STEP ,
			 *       因为 mq-consumer-risk 处理完后会对 步骤号 +1 变成9 ，9代表所有的步骤已经处理完成
			 */
			application.setIsStep(Constants.SH_CREDIT_STEP);
			application.setAuditingState(Constants.MQ_QZF_STATE);
			application.setModifyTime(DateUtils.parseToDateTimeStr(new Date()));
			application.setFailureTimes("0");
			application.setErrorDescription("");
			applicationServiceImpl.updateEntity(application);
			return true;
		}
		return false;
	}


}
