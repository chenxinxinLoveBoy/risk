package com.shangyong.backend.service.access.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangyong.backend.common.*;
import com.shangyong.backend.common.enums.*;

import com.shangyong.backend.utils.BanCodeRelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.dubbo.impl.GetCustomerInfoService;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BlacklistResult;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.CustomerInfo;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.ScBanControlTpl;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.service.CheckCusBlackService;
import com.shangyong.backend.service.RiskRuleCheckService;
import com.shangyong.backend.service.ScRiskWhiteListRedisService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.impl.ScBanControlRedisServiceImpl;
import com.shangyong.backend.service.impl.ScBanControlServiceImpl;
import com.shangyong.backend.service.impl.ScDecisionTreeServiceImpl;
import com.shangyong.backend.service.impl.ScTemplateServiceImpl;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;

/**
 * 信用风险规则校验获取接口
 * @author xiangxianjin
 *
 */
@Service
public class RiskRuleServiceImpl implements RiskRuleService {
	private static Logger logger = LoggerFactory.getLogger("rule");
	
	@Autowired
	private ScBanControlRedisServiceImpl scBanControlRedisServiceImpl;

	@Autowired
	private ScBanControlServiceImpl scBanControlService;

	@Autowired
	private ScTemplateServiceImpl scTemplateService;

	@Autowired
	private RiskRuleCheckService riskRuleCheckServiceImpl;

	@Autowired
	private ScRiskWhiteListRedisService scRiskWhiteListRedisServiceImpl;

	@Autowired
	private BlacklistService blacklistService;

	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;

	@Autowired
	private SysParamRedisService sysParamRedisService;

	@Autowired
	private GetCustomerInfoService getCustomerInfoService;

	@Autowired
	private CheckCusBlackService checkCusBlackService;
	
	@Override
	public RuleResult querySafeRuleApi(Application application, List<Map<String, Object>> checkList){
		RuleResult result = checkSafeRuleBySystem(checkList, application);
		return result;
	}

	@Override
	public RuleResult querySafeRuleApi(Application application, List<Map<String, Object>> checkList, boolean isPresent){
		RuleResult result;
		if (isPresent) {
			result = checkSafeRuleBySystem(checkList, application,isPresent);
		} else {
			result = checkSafeRuleBySystem(checkList, application);
		}
		return result;
	}

	/**
	 * 从系统中查询校验规则，风控规则校验
	 * @param checkList						List<Map<String,Object>> checkList 检测项
	 * @param application					申请单信息
	 * @return RuleResult				
	 * @throws Throwable
	 */
	private RuleResult checkSafeRuleBySystem(List<Map<String,Object>> checkList, Application application){
		RuleResult result = new RuleResult();

		List<Content> contents = new ArrayList<Content>();

		//检验白名单信息
		String appName = application.getAppName();
		String certCode = application.getCertCode();
		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		String banCodeTplId = application.getBanCodeTplId();
		String decisionTreeId = application.getDecisionTreeId();

		if (Constants.APP_LEVEL.equals(application.getAppLevel())) {
			ScDecisionTree scDecisionTree = scDecisionTreeService.getEntityByDecisionTreeId(decisionTreeId);
			if (scDecisionTree == null) {
				logger.info("查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
				throw new RuntimeException("查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
			}
			// 实施类型(01-大数据)
			if (!DecisionTreeImplementTypeEnum.BIG_DATA.getCode().equals(scDecisionTree.getImplementType())) {
				logger.info("老客户直接通过");
				result.setMessage("老客户直接通过");
				return result;
			}
		}
		logger.info("开始校验白名单信息：appName="+appName+",certCode="+certCode);
		boolean isWhite = scRiskWhiteListRedisServiceImpl.queryRiskWhite(appName, certCode, Constants.STATE_NORMAL);
		if(isWhite){
			result.setMessage("白名单客户");
			return result;
		}

		logger.info("开始校验风险项，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);

		for(Map<String,Object> map : checkList){
			for(String key : map.keySet()){
				//被检测内容
				Object checkValue = map.get(key);
				//从系统中获取检测规则对象
				ScBanControl scBanControl = scBanControlRedisServiceImpl.queryScBanControlByBanCode(banCodeTplId, key);

				//规则停用，则继续校验下一个规则
				if(scBanControl == null || Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
					continue;
				}
				
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

				/** 校验规则是否命中，true命中，分数入库**/
				boolean flag  = this.riskRuleCheckServiceImpl.ruleCheck(ruleComparisonType, validateRule, ruleValue, checkValue);
				logger.info("检测项规则名称，模板编号=" + banCodeTplId
						+ "，message=" + (key + ruleName)
						+ "，isblack=" + flag
						+ "，ruleValue=" + ruleValue
						+ "，checkValue="+checkValue);
				//命中立刻返回
				if(flag){
					Content content = new Content();
					String classCode = BanCodeRelUtil.REL_BAN_CLASS_CODE.get(key);
					if(ScBanControlIfRefuseEnum.ADD.getCode().equals(ifRefuse)){
						//审批拒绝的信息，添加黑名单表
						BuBlacklist buBlacklist = new BuBlacklist();
						buBlacklist.setBlacklistId(UUIDUtils.getUUID());
						buBlacklist.setAppName(application.getAppName());
						buBlacklist.setPlatformId(application.getPlatformId());
						buBlacklist.setCertCode(application.getCertCode());
						buBlacklist.setCertType(application.getCertType());
						buBlacklist.setCustomerId(application.getCustomerId());
						buBlacklist.setPhoneNum(application.getPhoneNum());
						buBlacklist.setName(application.getName());
						buBlacklist.setRejectType(creditType);
						buBlacklist.setIsFailure("1");
						buBlacklist.setBanCode(key);

						buBlacklist.setClassCode(classCode);

						buBlacklist.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
						buBlacklist.setsNumber(application.getApplicationId());
						buBlacklist.setRemark(ruleName);

						boolean saveBlack = blacklistService.saveEntity(buBlacklist);
						if(saveBlack){
							logger.info("黑名单信息入库成功：appName="+appName+",certCode="+certCode);
						}
					}
					result.setIsblack(flag);
					result.setState(RuleResult.STATUS_YES);
					result.setBlackCode(key);
					result.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
					result.setBanCodeClassCode(classCode);
					result.setMessage(ruleName);

					content.setState(ContentStateEnum.HIT.getCode());
					content.setBlackCode(key);
					content.setMessage(ruleName);
					content.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
					content.setBanCodeClassCode(classCode);

					contents.add(content);
					logger.info("命中检查项，校验风险项完成，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);
				}
			}
		}
		result.setContents(contents);
		logger.info("未命中检查项，校验风险项完成，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);
		return result;
	}

	/**
	 * 从系统中查询校验规则，风控规则校验
	 * @param checkList						List<Map<String,Object>> checkList 检测项
	 * @param application					申请单信息
	 * @return RuleResult
	 * @throws Throwable
	 */
	public RuleResult checkSafeRuleBySystem(List<Map<String,Object>> checkList, Application application,boolean isPresent){
		RuleResult result = new RuleResult();
		//检验白名单信息
		String appName = application.getAppName();
		String certCode = application.getCertCode();
		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		String banCodeTplId = application.getBanCodeTplId();
		String decisionTreeId = application.getDecisionTreeId();

		if (!isPresent) {
			if (Constants.APP_LEVEL.equals(application.getAppLevel())) {
				ScDecisionTree scDecisionTree = scDecisionTreeService.getEntityByDecisionTreeId(decisionTreeId);
				if (scDecisionTree == null) {
					logger.info("查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
					throw new RuntimeException("查询决策树为空，申请单编号：" + applicationId + ",决策树编号:" + decisionTreeId);
				}
				if (!("01").equals(scDecisionTree.getImplementType())) {// 实施类型(01-大数据)
					logger.info("老客户直接通过");
					result.setMessage("老客户直接通过");
					return result;
				}
			}
		}

		logger.info("开始校验白名单信息：appName="+appName+",certCode="+certCode);
		boolean isWhite = scRiskWhiteListRedisServiceImpl.queryRiskWhite(appName, certCode, Constants.STATE_NORMAL);
		if(isWhite){
			result.setMessage("白名单客户");
			return result;
		}
		logger.info("开始校验风险项，申请单信息：申请单编号 ="+applicationId + "isWhite:" + isWhite + ",app应用流水号="+appSerialNumber);
		for(Map<String,Object> map : checkList){
			for(String key : map.keySet()){

				// ken add to 2017/10/23 10:12 desc
				ScBanControlTpl scBanControlTpl = scTemplateService.getEntityById(banCodeTplId);

				//规则停用，则继续校验下一个规则
				if(scBanControlTpl == null || Constants.STATE_FORBIDDEN.equals(scBanControlTpl.getState())){
					logger.error("规则停用，模板号：" + banCodeTplId);
					throw new RuntimeException("规则停用，模板号：" + banCodeTplId);
				}

				Object checkValue = map.get(key);//被检测内容
				//从系统中获取检测规则对象
				ScBanControl scBanControl = scBanControlRedisServiceImpl.queryScBanControlByBanCode(banCodeTplId, key);

				//规则停用，则继续校验下一个规则
				if(scBanControl == null || Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
					continue;
				}

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

				String codeName = key + ruleName ; //命中规则code和名称

				logger.info("检测项规则名称，模板编号="+banCodeTplId+"，message="+codeName);

				/** 校验规则是否命中，true命中，分数入库**/
				boolean flag  = this.riskRuleCheckServiceImpl.ruleCheck(ruleComparisonType, validateRule, ruleValue, checkValue);
				logger.info("--->检测项规则名称，模板编号="+banCodeTplId+"，message="+codeName+"，isblack="+flag+"，ruleValue="+ruleValue+"，checkValue="+checkValue);
				//命中立刻返回
				if(flag){
					if("1".equals(ifRefuse)){
//						BuBlacklist black = new BuBlacklist();
//						black.setCertCode(certCode);//身份证
//						black.setPhoneNum(application.getPhoneNum());//手机号
//						int count = blacklistService.listAllCount(black);
//						logger.info("查询黑名单信息：count="+count+",certCode="+certCode);

						BlacklistResult blacklistResult = blacklistService.isInBlacklistTable(certCode,application.getPhoneNum(),"");
						int count = blacklistResult.getCount();
						logger.info("查询黑名单信息：count=" + count + ",certCode=" + certCode);
						if (count < Constants.APPLICATION_BLACK_NUM) {
//						if(count==0){
							//审批拒绝的信息，添加黑名单表
							BuBlacklist buBlacklist = new BuBlacklist();
							buBlacklist.setBlacklistId(UUIDUtils.getUUID());//拒绝编号
							buBlacklist.setAppName(application.getAppName());//APP名称：1-闪贷；2-速贷
							buBlacklist.setPlatformId(application.getPlatformId());//平台用户账号
							buBlacklist.setCertCode(application.getCertCode());//证件号码
							buBlacklist.setCertType(application.getCertType());//证件类型 ： 1.身份证 2.护照 3.其他
							buBlacklist.setCustomerId(application.getCustomerId());//APP应用客户编号
							buBlacklist.setPhoneNum(application.getPhoneNum());//手机号
							buBlacklist.setName(application.getName());//客户姓名
							buBlacklist.setRejectType(creditType);//被拒绝平台类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚）
							buBlacklist.setIsFailure("1");//是否失效（0-是，1-否）
							buBlacklist.setBanCode(key);
							buBlacklist.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
							buBlacklist.setsNumber(application.getApplicationId());
							buBlacklist.setRemark(ruleName);//备注
							boolean saveBlack = blacklistService.saveEntity(buBlacklist);
							if(saveBlack){
								logger.info("黑名单信息入库成功：appName="+appName+",certCode="+certCode);
							}
						}
					}

					result.setIsblack(flag);
					result.setState(1);
					result.setBlackCode(key);
					result.setMessage(ruleName);
					result.setState(1);
					logger.info("命中检查项，校验风险项完成，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);
					return result;
				}
			}
		}
		logger.info("未命中检查项，校验风险项完成，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);
		return result;
	}

	@Override
	public RuleResult checkSafeRuleBySystem(Application application) throws Exception {
		RuleResult result = new RuleResult();

		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		String appLevel = application.getAppLevel();
		String certCode = application.getCertCode();
		String appName = application.getAppName();
		String phoneNum = application.getPhoneNum();

		logger.info("开始校验白名单信息：appName="+appName+"，certCode="+certCode);
		boolean isWhite = scRiskWhiteListRedisServiceImpl.queryRiskWhite(appName, certCode, Constants.STATE_NORMAL);
		if(isWhite){
			result.setState(0);
			result.setIsblack(false);
			result.setMessage("白名单客户");
			logger.info("单号命中白名单：applicationId="+applicationId);
			return result;
		}

		// 获取参数值
		SysParam xiaoNiuRuleONOff = sysParamRedisService.querySysParamByParamValueRedis(Constants.XIAO_NIU_RULE_ON_OFF);

		String banCodeTplId = "";

		//准入规则，默认为新用户
		String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;

		if (Constants.APP_LEVEL.equals(appLevel)) {
			//老用户准入规则
			paramAdmittance = DictConstant.OLD_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;
			if(xiaoNiuRuleONOff != null){
				//1 开， 0 关 开关未打开的直接通过
				//老用户
				if (!DictConstant.IS_FAILURE_ONE.equals(xiaoNiuRuleONOff.getParamValueTwo())) {
					result.setState(0);
					result.setIsblack(false);
					return  result;
				}
			}
		}else{
			if(xiaoNiuRuleONOff != null){
				//1 开， 0 关 开关未打开的直接通过
				//新用户
				if (!DictConstant.IS_FAILURE_ONE.equals(xiaoNiuRuleONOff.getParamValueOne())) {
					result.setState(0);
					result.setIsblack(false);
					return  result;
				}
			}
		}
		logger.info("开始校验准入规则，申请单编号："+applicationId + "，app应用流水号="+appSerialNumber);

		//获取准入规则的模块ID
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);

		if(sysParam != null && StringUtil.checkNotNull(sysParam.getParamValueOne())){
			banCodeTplId = sysParam.getParamValueOne();

			// ken add to 2017/10/12 18:14 desc  1015 
			ScBanControlTpl scBanControlTpl = scTemplateService.getEntityById(banCodeTplId);

			//规则停用，则继续校验下一个规则
			if(scBanControlTpl == null || Constants.STATE_FORBIDDEN.equals(scBanControlTpl.getState())){
                logger.error("规则停用，模板号：" + banCodeTplId);
                throw new RuntimeException("规则停用，模板号：" + banCodeTplId);
			}

			// ken add to 2017/10/19 17:59 desc  校验是否验证黑名单
			boolean flag = checkCusBlackService.checkCusBlack(banCodeTplId,appName,applicationId,certCode,appLevel,phoneNum);

			if (flag) {
				result.setState(1);
				result.setIsblack(flag);
				result.setMessage("用户存在闪贷拒绝名单");
				logger.info("用户存在闪贷拒绝名单：applicationId="+applicationId);
				return result;
			}
			//根据模板ID获取所有的规则项集合
			ScBanControl scBanControlParam = new ScBanControl();
			scBanControlParam.setBanControlTplId(banCodeTplId);
			scBanControlParam.setState(Constants.STATE_NORMAL);
			List<ScBanControl> scBanControlList = scBanControlService.findAll(scBanControlParam);

			// ken add to 2017/10/12 18:14 desc  1015 
			if (scBanControlList == null || scBanControlList.size() < 1) {
                logger.error("规则模板中的生效规则项不存在，模板号：" + banCodeTplId);
                throw new RuntimeException("规则模板中的生效规则项不存在，模板号：" + banCodeTplId);
			}

			//根据申请单的平台客户编号，获取客户资料
			CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();

//			cuPlatformCustomer.setPlatformCustomerId(application.getPlatformId());
			cuPlatformCustomer.setCustomerId(application.getCustomerId());
			cuPlatformCustomer.setAppName(application.getAppName());


			//身份证过期时间
			String expirationDate = "";
			//年龄 默认让过
			int age = 0;
			//身份证所在区域
			String issueInstitution = "";
			//公司名称
			String companyName = null;
			try {

				CustomerInfo customerInfo = getCustomerInfoService.getEntityById(cuPlatformCustomer);

				if(customerInfo == null || customerInfo.getCuPlatformCustomer() == null){
                    logger.error("未找到用户信息请确认用户存在，或检查dubbo服务是否正常");
                    throw new RuntimeException("未找到用户信息请确认用户存在，或检查dubbo服务是否正常");
				}

				application.setPlatformId(customerInfo.getCuPlatformCustomer().getPlatformCustomerId());

				//身份证过期时间
				expirationDate = customerInfo.getCuPlatformCustomer().getExpirationDate();
				//年龄
				age = customerInfo.getCuPlatformCustomer().getAge();
				//身份证所在区域
				issueInstitution = customerInfo.getCuPlatformCustomer().getIssueInstitution();
				//公司名称
				companyName = null;
				if(customerInfo.getCuCustomerCompany() != null){
					companyName = customerInfo.getCuCustomerCompany().getCompanyName();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
                logger.error("获取用户资料失败，错误信息：" + ex.getMessage());
                throw new RuntimeException("获取用户资料失败，错误信息：" + ex.getMessage());
			}
			logger.info("校验准入规则，获取基本信息成功，申请单编号："+applicationId+"，expirationDate="+expirationDate+"，age="+age+"，issueInstitution="+issueInstitution+"，companyName="+companyName);

			//手机通讯录个数
			int telDirectoryNum = -1;
//			//身份证过期时间
//			String expirationDate = "2017-12-07";
//			//年龄
//			int age = 15;
//			//身份证所在区域
//			String issueInstitution = "876445655";
//			//公司名称
//			String companyName = "233544";
//			//手机通讯录个数
//			int telDirectoryNum = -1;

			Object checkValue = null;


            for (ScBanControl scBanControl: scBanControlList
					) {
				//规则停用，则继续校验下一个规则
//				if(scBanControl == null || Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
//					continue;
//				}

				boolean isCheck = true;
				String banCode = scBanControl.getBanCode();
				String ifRefuse = scBanControl.getIfRefuse();
				/** 征信机构类型**/
				String creditType = scBanControl.getCreditType();
				/**禁止项规则名称**/
				String ruleName = scBanControl.getRuleName();


				//根据规则编号，进行规则匹配并设置检验值
				if(BanCodeEnum.XIAO_NIU_CARD_AGE_CEILING.getCode().equals(banCode)){
					checkValue = age;
				}else if(BanCodeEnum.XIAO_NIU_CARD_AGE_FLOOR.getCode().equals(banCode)){
					checkValue = age;
				}else if(BanCodeEnum.XIAO_NIU_CARD_REGION.getCode().equals(banCode)){
					checkValue = issueInstitution;
				}else if (BanCodeEnum.XIAO_NIU_CARD_VALID.getCode().equals(banCode)) {
					try {
						expirationDate = expirationDate.split("-")[1];
						Date endDate = DateUtils.convertStringToDate(DateUtils.FORMAT_STR1,expirationDate);
						checkValue = DateUtils.daysBetween(new Date(),endDate);
					} catch (Exception e) {
						logger.error("身份证过期时间格式不正确");
						checkValue = 999;
//						//命中则直接返回，结束
//						result.setState(1);
//						result.setIsblack(true);
//						result.setMessage("身份证过期时间格式不正确,过期时间=" + customerInfo.getCuPlatformCustomer().getExpirationDate());
//						return result;
						throw new RuntimeException("身份证过期时间格式不正确,过期时间=" + expirationDate);
					}
				}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode().equals(banCode)){
					checkValue = companyName;
				}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT_NUM.getCode().equals(banCode)){
					if(companyName != null){
						//全数字，包含负数
						String regEx = "^-?[0-9]+$";
						Pattern pat = Pattern.compile(regEx);
						Matcher mat = pat.matcher(companyName);
						if (mat.matches()) {
							//直接返回
							//命中则直接返回，结束
//						return getRuleResultForError(result, banCodeTplId, scBanControl, banCode, scBanControl.getRuleName(),companyName);
							checkValue = scBanControl.getRuleComparisonValue();
						}else{
							checkValue = companyName;
						}
					}else {
						checkValue = companyName;
					}
				}else if(DictConstant.XIAO_NIU_CONTACTS.equals(banCode)){
					String source = application.getSource();
					if ("1".equals(source)) {
						//iOS直接通过
						telDirectoryNum = 99999;
					}else {
						//安卓的验证个数
//						Map<String, Object> paramMap = new HashMap<String, Object>();
//						paramMap.put("customerId", application.getCustomerId());
//						paramMap.put("appName", Integer.parseInt(application.getAppName()));
//						AppAddressBook appAddressBook = (AppAddressBook) mongoUtils.findByClazz(paramMap, null, AppAddressBook.class);
//
//						if(appAddressBook != null){
//							JSONArray jsonArray = JSONArray.fromObject(appAddressBook.getJsonInfo().get("list"));
//							if (jsonArray != null) {
//								List<CustomerDirectories> directories = JSONArray.toList(jsonArray,CustomerDirectories.class);
//
//								telDirectoryNum = customerDirectoriesService.regulateDirectoriesNelist(directories);
//							}
//						}
					}
					checkValue = telDirectoryNum;
				}else if(BanCodeEnum.XIAO_NIU_CARD_UNIT.getCode().equals(banCode)){
					checkValue = companyName;
				}else {
                    isCheck = false;
                }
                if (isCheck) {
                    result = checkValueForAdmittance(scBanControl,application,banCodeTplId,checkValue);
                    if(result.getIsblack()) {
                        return result;
                    }
                }
			}
		}

		logger.info("校验准入规则结束：申请单编号 ="+applicationId);

//		logger.info("未命中检查项，校验风险项完成，申请单信息：申请单编号 ="+applicationId + ",app应用流水号="+appSerialNumber);
		result.setState(0);
		result.setIsblack(false);
		return result;
	}

	private RuleResult getRuleResultForError(RuleResult result, String banCodeTplId, ScBanControl scBanControl, String banCode,String ruleName, Object checkValue) {
		//直接返回
		//命中则直接返回，结束
		result.setIsblack(true);
		result.setBlackCode(banCode);
		result.setMessage("命中准入规则项模板编号="+banCodeTplId+"，模板名称="+ruleName+",检查值=" + checkValue);
		result.setExt("检测准入规则项，命中模板编号="+banCodeTplId+"模板名称="+ruleName +"，规则编号="+banCode+",检查值=" + checkValue);
		result.setState(1);
		return result;
	}

	/**
	 * 准入规则检查
	 * @param scBanControl
	 * @param application
	 * @param banCodeTplId
	 * @param checkValue
	 * @return
	 */
	private RuleResult checkValueForAdmittance(ScBanControl scBanControl,Application application,String banCodeTplId,Object checkValue){
		RuleResult result = new RuleResult();
		//检验白名单信息
		String appName = application.getAppName();
		String certCode = application.getCertCode();
		String applicationId = application.getApplicationId();
		String appSerialNumber = application.getAppSerialNumber();
		String appLevel = application.getAppLevel();

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

//		logger.info("检测项规则名称，模板编号="+banCodeTplId+"，message="+codeName);
		boolean flag  = false;

			/** 校验规则是否命中，true命中 **/
		flag  = this.riskRuleCheckServiceImpl.ruleCheck(ruleComparisonType, validateRule, ruleValue, checkValue);

		//命中则直接返回，结束
		if (flag) {
			if ("1".equals(ifRefuse)) {
//				BuBlacklist black = new BuBlacklist();
//				black.setCertCode(certCode);//身份证
//				black.setPhoneNum(application.getPhoneNum());//手机号
//				int count = blacklistService.listAllCount(black);
				BlacklistResult blacklistResult = blacklistService.isInBlacklistTable(certCode,application.getPhoneNum(),"");
				int count = blacklistResult.getCount();
				logger.info("查询黑名单信息：count=" + count + ",certCode=" + certCode);
				if (count < Constants.APPLICATION_BLACK_NUM) {
					//审批拒绝的信息，添加黑名单表
					BuBlacklist buBlacklist = new BuBlacklist();
					buBlacklist.setBlacklistId(UUIDUtils.getUUID());//拒绝编号
					buBlacklist.setAppName(appName);//APP名称：1-闪贷；2-速贷
					buBlacklist.setPlatformId(application.getPlatformId());//平台用户账号
					buBlacklist.setCertCode(application.getCertCode());//证件号码
					buBlacklist.setCertType(application.getCertType());//证件类型 ： 1.身份证 2.护照 3.其他
					buBlacklist.setCustomerId(application.getCustomerId());//APP应用客户编号
					buBlacklist.setPhoneNum(application.getPhoneNum());//手机号
					buBlacklist.setName(application.getName());//客户姓名
					buBlacklist.setRejectType(creditType);//被拒绝平台类型（01-同盾、02-聚信立蜜蜂、03-聚信立蜜罐、04-芝麻信用、05-91信用卡、06-宜信、07-中智诚）
					buBlacklist.setIsFailure("1");//是否失效（0-是，1-否）
					buBlacklist.setBanCode(banCode);
					buBlacklist.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
					buBlacklist.setsNumber(application.getApplicationId());
					buBlacklist.setRemark(ruleName);//备注
					boolean saveBlack = blacklistService.saveEntity(buBlacklist);
					if (saveBlack) {
						logger.info("黑名单信息入库成功：appName=" + application.getAppName() + ",certCode=" + certCode);
					}
				}
			}
			result.setIsblack(flag);
			result.setBlackCode(scBanControl.getBanCode());
			result.setMessage(ruleName);
			result.setExt("检测准入规则项，命中模板编号="+banCodeTplId+"模板名称="+ruleName +"，规则编号="+banCode+",检查值=" + checkValue);
		}

		logger.info("--->检测准入规则项，模板编号="+banCodeTplId+"，state="+scBanControl.getState()+"，message="+codeName+"，isblack="+flag+"，ruleValue="+ruleValue+"，checkValue="+checkValue);
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
	private boolean checkCusBlack(String banControlTplId, String appName, String applicationId, String certNo, String appLevel, String phoneNum){
		boolean flag = false;

		//从系统中获取检测规则对象
		ScBanControl scBanControl = scBanControlRedisServiceImpl.queryScBanControlByBanCode(banControlTplId, BanCodeEnum.BLACKLIST_USER.getCode());

		//有配置规则，规则失效，则继续校验下一个规则
		if(scBanControl == null || Constants.STATE_FORBIDDEN.equals(scBanControl.getState())){
			logger.info("用户黑名单检测状态失效，不检测黑名单");
			return flag;
		}

		//根据身份证、手机号、设备id查询黑名单表，如果有数据直接return返回，跟新状态
		flag = blacklistService.isInBlacklist(certNo, phoneNum, "");

		logger.info("检测结果blackFlag = " + flag);
		return flag;
	}
}