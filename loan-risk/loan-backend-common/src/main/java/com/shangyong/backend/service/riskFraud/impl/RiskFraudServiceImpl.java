package com.shangyong.backend.service.riskFraud.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.shangyong.backend.common.BQSConstant;
import com.shangyong.backend.common.RiskFraudConstants;
import com.shangyong.backend.dao.CuPlatformCustomerDao;
import com.shangyong.backend.dao.ScFraudScoreDao;
import com.shangyong.backend.dao.ScFraudScoreDetailDao;
import com.shangyong.backend.dao.bqsrep.BqsRepCrossValidationServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMnoSixMobileServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepMonthUsedServiceDao;
import com.shangyong.backend.dao.bqsrep.BqsRepPetitionerServiceDao;
import com.shangyong.backend.dao.td.TdPlatformDetailServiceDao;
import com.shangyong.backend.dao.tdReport.TdReportAllContactDao;
import com.shangyong.backend.dao.tdReport.TdReportContinueSilenceDao;
import com.shangyong.backend.dao.tdReport.TdReportDetailDao;
import com.shangyong.backend.dao.tdReport.TdReportMobileInfoDao;
import com.shangyong.backend.dao.tdReport.TdReportPerMonthDao;
import com.shangyong.backend.dao.tdReport.TdReportSilenceStatsDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ScFraudScore;
import com.shangyong.backend.entity.ScFraudScoreDetail;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.bqsrep.BqsRepCrossValidation;
import com.shangyong.backend.entity.bqsrep.BqsRepMonthUsed;
import com.shangyong.backend.entity.bqsrep.BqsRepPetitioner;
import com.shangyong.backend.entity.tdReport.TdReportAllContact;
import com.shangyong.backend.entity.tdReport.TdReportMobileInfo;
import com.shangyong.backend.entity.tdReport.TdReportPerMonth;
import com.shangyong.backend.entity.tdReport.TdReportSilenceStats;
import com.shangyong.backend.service.RiskFraudScoreService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.riskFraud.RiskFraudService;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.UUIDUtils;

@Service
public class RiskFraudServiceImpl implements RiskFraudService {
	
	@Autowired
	private RiskFraudScoreService riskFraudScoreService;//欺诈分
	
	@Autowired
	private BqsRepCrossValidationServiceDao bqsRepCrossValidationServiceDao;//白骑士
	
	@Autowired
	private BqsRepPetitionerServiceDao bqsRepPetitionerServiceDao;//白骑士主表
	
	@Autowired
	private TdReportMobileInfoDao tdReportMobileInfoDao;//数据魔盒
	
	@Autowired
	private TdReportSilenceStatsDao tdReportSilenceStatsDao;
	
	@Autowired
	private TdReportContinueSilenceDao tdReportContinueSilenceDao;
	
	@Autowired
	private CuPlatformCustomerDao cuPlatformCustomerDao;
	
	@Autowired
	private BqsRepMonthUsedServiceDao bqsRepMonthUsedServiceDao;//白骑士月使用信息
	
	@Autowired 
	private TdReportAllContactDao tdReportAllContactDao;
	
	@Autowired
	private BqsRepMnoSixMobileServiceDao bqsRepMnoSixMobileServiceDao;
	
	@Autowired
	private TdReportPerMonthDao tdReportPerMonthDao;
	
	@Autowired
	private TdPlatformDetailServiceDao tdPlatformDetailServiceDao;
	
	@Autowired
	private TdReportDetailDao tdReportDetailDao;
	
	@Autowired
	private ScFraudScoreDao scFraudScoreDao;
	
	/**
	 * 评分明细
	 */
	@Autowired
	private ScFraudScoreDetailDao scFraudScoreDetailDao;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	@Override
	public String riskFraud(String fraudTplId,Application application) {
		
		String applicationId = application.getApplicationId();
		String certCode = application.getCertCode();
		String customerId = application.getCustomerId();
		String mobileWebsite = application.getMobileWebsite();
		Assert.hasText(certCode, "身份证不能为空");
		
		Assert.hasText(applicationId, "申请单号为空");
		//欺诈分模板编号
		Assert.hasText(fraudTplId, "欺诈分模板为空");
		//查询是白骑士报告还是数据魔盒
		Assert.hasText(customerId, "客户编号为空");
		try {
			
			if (StringUtils.isBlank(mobileWebsite)) {
				//运营商报告
				saveRiskFraudBqs(fraudTplId,applicationId);
			}else{
				//同盾数据魔盒
				saveRiskFraudTd(fraudTplId,applicationId);
			}
			//身份证性别
			saveRiskFraudSS(fraudTplId,certCode,applicationId);
			//同盾多投
			saveTdCount(fraudTplId, applicationId);
			//获得总得分
			BigDecimal allScore = queryAllScore(applicationId,fraudTplId,customerId);

			return allScore.toString();
		} catch (Exception e) {
			throw new RuntimeException("欺诈分内部系统错误：",e);
		} finally {
		}
	}

	/**计算总分**/
	private BigDecimal queryAllScore(String applicationId, String fraudTplId,String customerId) {
		ScFraudScoreDetail scFraudScoreDetail2 = new ScFraudScoreDetail();
		scFraudScoreDetail2.setApplicationId(applicationId);
		scFraudScoreDetail2.setFraudTplId(fraudTplId);
		List<ScFraudScoreDetail> list = scFraudScoreDetailDao.queryAllScore(scFraudScoreDetail2);
		SysParam parameter = sysParamRedisService.querySysParamByParamValueRedis(RiskFraudConstants.HZ_QZF_SCORE + fraudTplId);
		String basicsScore1 = parameter.getParamValueOne();
		BigDecimal sum = new BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			ScFraudScoreDetail scFraudScoreDetail = list.get(i);
			BigDecimal score = new BigDecimal(scFraudScoreDetail.getScore());
			sum = sum.add(score);
		}
		BigDecimal basicsScore = new BigDecimal(basicsScore1);
		BigDecimal subtract = basicsScore.add(sum);
		ScFraudScore scFraudScore = new ScFraudScore();
		scFraudScore.setApplicationId(applicationId);
		scFraudScore.setScFraudScoreId(UUIDUtils.getUUID());
		scFraudScore.setFraudTplId(fraudTplId);
		scFraudScore.setCreateTime(DateUtils.getDate(new Date()));
		scFraudScore.setCustomerId(customerId);
		scFraudScore.setScore(subtract.toString());
		//入库
		int saveEntity = scFraudScoreDao.saveEntity(scFraudScore);
		Assert.isTrue(saveEntity>0,"欺诈总分入库失败");
		return subtract;
	}
/**
	 * 身份证性别
	 * @param fraudTplId
	 * @param customerId
	 */
	private void saveRiskFraudSS(String fraudTplId, String certCode,String applicationId) {
		String str = StringUtils.substring(certCode, certCode.length()-2, certCode.length()-1);
		Integer value = Integer.valueOf(str);
		if (0 == value%2) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.SFZ_SEX,"2", applicationId,"02");
		}else{
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.SFZ_SEX,"1", applicationId,"02");
		}
	}
	/**同盾P2P网贷7天多头**/
	private void saveTdCount(String fraudTplId,String applicationId){
		String count = tdPlatformDetailServiceDao.queryByAid(applicationId);
		if (StringUtils.isBlank(count)) {
			count = "0";
		}
		Integer count1 = Integer.valueOf(count);
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.TD_P2P_DT,count1, applicationId,"01");
	}
	/**
	 * 白骑士
	 * @param fraudTplId
	 * @param applicationId
	 * @throws ParseException 
	 */
	private void saveRiskFraudBqs(String fraudTplId,String applicationId) throws ParseException {
		//白骑士
		BqsRepPetitioner bqsRepPetitioner = bqsRepPetitionerServiceDao.queryById(applicationId);
		if (bqsRepPetitioner == null || bqsRepPetitioner.getBqsPetitionerId() == null) {
			throw new RuntimeException("白骑士报告主键获取失败");
		}
		String bqsPetitionerId = bqsRepPetitioner.getBqsPetitionerId();
		String createTime = bqsRepPetitioner.getCreateTime();//白骑士获取时间
		BqsRepCrossValidation bqsRepCrossValidation = null;
		String type = null;
		//入网时长
		type = BQSConstant.OPENTIME;
		BqsRepCrossValidation bqsRepCrossValidation2 = new BqsRepCrossValidation();
		bqsRepCrossValidation2.setBqsPetitionerId(bqsPetitionerId);
		bqsRepCrossValidation2.setType(type);
		bqsRepCrossValidation = bqsRepCrossValidationServiceDao.queryRWById(bqsRepCrossValidation2);
		if (bqsRepCrossValidation == null || StringUtils.isBlank(bqsRepCrossValidation.getResult())) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_RW_TIME,"未知", applicationId,"02");
		}else{
			String result = bqsRepCrossValidation.getResult();
			Date smdate = DateUtils.convertStringToDate(result);
			Date cTime = DateUtils.convertStringToDate(createTime);
			int daysBetween = DateUtils.daysBetween(smdate, cTime);
			BigDecimal bigDecimal = new BigDecimal(daysBetween);
			BigDecimal divide = bigDecimal.divide(new BigDecimal(30), 5, BigDecimal.ROUND_HALF_UP);
			saveRwTime(divide,fraudTplId, applicationId);
		}
		
		//静默3天次数
		type = BQSConstant.NOTCALLANDSMSDAYCOUNT;
		bqsRepCrossValidation2.setType(type);
		bqsRepCrossValidation= bqsRepCrossValidationServiceDao.queryRWById(bqsRepCrossValidation2);
		if (bqsRepCrossValidation == null || StringUtils.isBlank(bqsRepCrossValidation.getEvidence())) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_TIME, "未知", applicationId,"02");
		}else{
			String result = bqsRepCrossValidation.getEvidence();
			Pattern pattern1 = Pattern.compile("([0-9]*?)次");//Pattern.compile("([\\s\\S]*?)次");
			Matcher matcher1 = pattern1.matcher(result);
			if(matcher1.find()){
				result=matcher1.group(1);
			}
			BigDecimal bigDecimal = new BigDecimal(result);
			saveJmCount(bigDecimal, fraudTplId, applicationId);
		}
		
		//静默天数占比
		String evidence = bqsRepCrossValidation.getEvidence();
		if (StringUtils.isBlank(evidence)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_ZB, "未知", applicationId,"02");
		}else{
			String stateTime = evidence.substring(evidence.indexOf("在")+1,evidence.indexOf("~"));
			String endTime = evidence.substring(evidence.indexOf("~")+1,evidence.indexOf("时"));
			
		try {
			Date stateTime1 = DateUtils.convertStringToDate(stateTime);
			Date endTime1 = DateUtils.convertStringToDate(endTime);
			int daysBetween = DateUtils.daysBetween(stateTime1, endTime1);
			BigDecimal daysBetween1 = new BigDecimal(daysBetween);
			String result = bqsRepCrossValidation.getResult();
			result = result.replace("天","");
			BigDecimal jm = new BigDecimal(result);
			saveJmZb(jm,daysBetween1, fraudTplId, applicationId);
		} catch (Exception e) {
			throw new RuntimeException("静默时间转换失败");
			}
		}
		
		//夜间通话
		type = BQSConstant.NIGHTCALLCOUNT;
		bqsRepCrossValidation2.setType(type);
		bqsRepCrossValidation = bqsRepCrossValidationServiceDao.queryRWById(bqsRepCrossValidation2);
		List<BqsRepMonthUsed> list = bqsRepMonthUsedServiceDao.queryById(bqsPetitionerId);
		String result = bqsRepCrossValidation.getResult();
		int sum = 0;
		for (BqsRepMonthUsed bqsRepMonthUsed : list) {
			String callCount = bqsRepMonthUsed.getCallCount();
			sum = sum + Integer.valueOf(callCount);
		}
			BigDecimal bigDecimal1 = new BigDecimal(sum);
			BigDecimal bigDecimal2 = new BigDecimal(result);
			saveYjTime(bigDecimal1,bigDecimal2, fraudTplId, applicationId);

		BigDecimal hcSix = new BigDecimal(0.00000);//呼出次数（6个月）
		BigDecimal hrSix = new BigDecimal(0.00000);//呼入次数（6个月）
		BigDecimal hcThree = new BigDecimal(0.00000);//呼出次数（3个月）
		BigDecimal hrThree = new BigDecimal(0.00000);//呼入次数（3个月）
		BigDecimal thSix = new BigDecimal(0.00000);//通话总次数(6个月)
		BigDecimal thThree = new BigDecimal(0.00000);//通话总次数(3个月)
		BigDecimal threeHrTime = new BigDecimal(0.00000); //呼入时长(3个月)
		BigDecimal threeHcTime = new BigDecimal(0.00000); //呼出时长(3个月)
		BigDecimal sixHrTime = new BigDecimal(0.00000); //呼入时长(6个月)
		BigDecimal sixHcTime = new BigDecimal(0.00000); //呼出时长(6个月)
		BqsRepMonthUsed bqsRepMonthUsed = list.get(0);
		String month = bqsRepMonthUsed.getMonth();
		Date date1 = new SimpleDateFormat("yyyy-MM").parse(month); 
		Date date2 = new SimpleDateFormat("yyyy-MM").parse(createTime); 
		if (date1.getTime() == date2.getTime()) {
			list.remove(0);
		}	
		//获取所有的次数
		for (int i = 0; i < list.size(); i++) {
			String count1 = list.get(i).getOriginatingCallCount();//主叫
			String count2 = list.get(i).getTerminatingCallCount();//被叫
			String callCount = list.get(i).getCallCount();//通话次数
			String count3 = list.get(i).getOriginatingCallTime();//主叫时长
			String count4 = list.get(i).getTerminatingCallTime();//被叫时长
			if (StringUtils.isBlank(count1)) {
				count1 = "0.00000";
			}
			if (StringUtils.isBlank(count2)) {
				count2 = "0.00000";
			}
			if (StringUtils.isBlank(callCount)) {
				callCount = "0.00000";
			}
			if (StringUtils.isBlank(count3)) {
				count3 = "0.00000";
			}
			if (StringUtils.isBlank(count4)) {
				count4 = "0.00000";
			}
			
			//3个月的呼入呼出
			if (i<3) {
				hcThree = hcThree.add(new BigDecimal(count1)); //呼出
				hrThree = hrThree.add(new BigDecimal(count2)); //呼入
				thThree = thThree.add(new BigDecimal(callCount));//3个月总通话次数
				threeHcTime = threeHcTime.add(new BigDecimal(count3));//三个月呼出通话时间
				threeHrTime = threeHrTime.add(new BigDecimal(count4));//三个月呼入通话时间
			}
			hcSix = hcSix.add(new BigDecimal(count1));
			hrSix = hrSix.add(new BigDecimal(count2));
			thSix = thSix.add(new BigDecimal(callCount));
			sixHrTime = sixHrTime.add(new BigDecimal(count4));//六个月呼入通话时间
			sixHcTime = sixHcTime.add(new BigDecimal(count3));//六个月呼出通话时间
		}
		
		//呼入次数/呼出次数（6个月）
		saveSixHrHc(hrSix, hcSix, fraudTplId, applicationId);
	
		//3个月呼入次数/6个月呼入次数
		saveThreeHcSixhR(hrThree, hrSix, fraudTplId, applicationId);
		
		//有通话联系人个数(6个月)
		int count = bqsRepMnoSixMobileServiceDao.queryCountById(bqsPetitionerId);
		saveSixLxCount(count, fraudTplId, applicationId);
		
		//呼入次数/呼出次数（3个月）
		saveThreeHrThreeHc(hrThree, hcThree, fraudTplId, applicationId);
		
		//呼入次数/呼出次数（1个月）
		if (CollectionUtils.isEmpty(list)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_ONE,"未知", applicationId,"02");
		}else{
			BqsRepMonthUsed bqsRepMonthUsed2 = list.get(0);
			String originatingCallCount = bqsRepMonthUsed2.getOriginatingCallCount();
			String terminatingCallCount = bqsRepMonthUsed2.getTerminatingCallCount();
			if (null == originatingCallCount) {
				originatingCallCount = "0";
			}
			if (null == terminatingCallCount) {
				terminatingCallCount = "0";
			}
			BigDecimal hcOne = new BigDecimal(originatingCallCount);
			BigDecimal hrOne = new BigDecimal(terminatingCallCount);
			saveOneHrHc(hrOne, hcOne, fraudTplId, applicationId);
		}
		
		
		//3个月通话次数/6个月通话次数
		saveThreeThSixTh(thThree, thSix, fraudTplId, applicationId);
		
		//通话次数>=10联系人数(6个月)
		int countLx = bqsRepMnoSixMobileServiceDao.queryCountLxById(bqsPetitionerId);
		saveCountLx(countLx, fraudTplId, applicationId);
		//3个月呼入时长/6个月呼入时长
		saveHrTime(threeHrTime,sixHrTime,fraudTplId, applicationId);
		//呼入次数>=10联系人数
		int countHr = bqsRepMnoSixMobileServiceDao.queryCountHrById(bqsPetitionerId);
		saveHrCount(countHr, fraudTplId, applicationId);
		//3个月呼出次数/6个月呼出次数
		saveThreeHcSixHc(hcThree, hcSix, fraudTplId, applicationId);
	}
	/**
	 * 数据魔盒
	 * @param fraudTplId
	 * @param applicationId
	 * @throws ParseException 
	 */
	private void saveRiskFraudTd(String fraudTplId,String applicationId) throws ParseException{
		
		//入网时长
		TdReportMobileInfo tdReportMobileInfo = tdReportMobileInfoDao.queryRWById(applicationId);
		if (tdReportMobileInfo == null || StringUtils.isBlank(tdReportMobileInfo.getMobileNetAge())) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_RW_TIME,"未知", applicationId,"02");	
		}else{
			BigDecimal bigDecimal = new BigDecimal(tdReportMobileInfo.getMobileNetAge());
			saveRwTime(bigDecimal, fraudTplId, applicationId);
		}
		
		//静默3天次数
		TdReportSilenceStats tdReportSilenceStats = tdReportSilenceStatsDao.querybyId(applicationId);
		if (tdReportSilenceStats == null || StringUtils.isBlank(tdReportSilenceStats.getContinueSilenceDayOver30call0msgSend6month())) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_TIME, "未知", applicationId,"02");
		}else{
			BigDecimal bigDecimal = new BigDecimal(tdReportSilenceStats.getContinueSilenceDayOver30call0msgSend6month());
			saveJmCount(bigDecimal, fraudTplId, applicationId);
		}
		
		//静默天数占比

		BigDecimal bigDecimal = new BigDecimal("180");
		BigDecimal jm = new BigDecimal(tdReportSilenceStats.getSilenceDay0call0msgSend6month());
		saveJmZb(jm,bigDecimal, fraudTplId, applicationId);

		//夜间通话
		TdReportAllContact tdReportAllContact =	tdReportAllContactDao.queryById(applicationId);
		if (tdReportAllContact == null || StringUtils.isBlank(tdReportAllContact.getCallCount6month())) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId,RiskFraudConstants.YJ_TH_ZB,"未知", applicationId,"02");
		}else{
			String callCount6month = tdReportAllContact.getCallCount6month();//6月总通话次数
			String callCountLateNight6month = tdReportAllContact.getCallCountLateNight6month();//callCountLateNight6month
			int all = Integer.valueOf(callCount6month);
			int lateNight = Integer.valueOf(callCountLateNight6month);
				BigDecimal bigDecimal1 = new BigDecimal(all);
				BigDecimal bigDecimal2 = new BigDecimal(lateNight);
				saveYjTime(bigDecimal1, bigDecimal2, fraudTplId, applicationId);
		}
		List<TdReportPerMonth> perMonths = tdReportPerMonthDao.queryMonthById(applicationId);
		BigDecimal hcSix = new BigDecimal(0.00000);//呼出次数（6个月）
		BigDecimal hrSix = new BigDecimal(0.00000);//呼入次数（6个月）
		BigDecimal hcThree = new BigDecimal(0.00000);//呼出次数（3个月）
		BigDecimal hrThree = new BigDecimal(0.00000);//呼入次数（3个月）
		BigDecimal thSix = new BigDecimal(0.00000);//通话总次数(6个月)
		BigDecimal thThree = new BigDecimal(0.00000);//通话总次数(3个月)
		BigDecimal threeHrTime = new BigDecimal(0.00000); //呼入时长(3个月)
		BigDecimal threeHcTime = new BigDecimal(0.00000); //呼出时长(3个月)
		BigDecimal sixHrTime = new BigDecimal(0.00000); //呼入时长(6个月)
		BigDecimal sixHcTime = new BigDecimal(0.00000); //呼出时长(6个月)
		TdReportPerMonth tdReportPerMonth = perMonths.get(0);
		String month = tdReportPerMonth.getMonth();
		String createTime = tdReportPerMonth.getCreateTime();//白骑士获取时间
		Date date1 = new SimpleDateFormat("yyyy-MM").parse(month); 
		Date date2 = new SimpleDateFormat("yyyy-MM").parse(createTime); 
		if (date1.getTime() == date2.getTime()) {
			perMonths.remove(0);
		}
		//获取所有的次数
		for (int i = 0; i < perMonths.size(); i++) {
			String count1 = perMonths.get(i).getCallCountActive();//主叫
			String count2 = perMonths.get(i).getCallCountPassive();//被叫
			String callCount = perMonths.get(i).getCallCount();//通话次数
			String count3 = perMonths.get(i).getCallTimeActive();//呼出时长
			String count4 = perMonths.get(i).getCallTimePassive();//呼入时长
			if (StringUtils.isBlank(count1)) {
				count1 = "0.00000";
			}
			if (StringUtils.isBlank(count2)) {
				count2 = "0.00000";
			}
			if (StringUtils.isBlank(callCount)) {
				callCount = "0.00000";
			}
			if (StringUtils.isBlank(count3)) {
				count3 = "0.00000";
			}
			if (StringUtils.isBlank(count4)) {
				count4 = "0.00000";
			}
			
			//3个月的呼入呼出
			if (i<3) {
				hcThree = hcThree.add(new BigDecimal(count1)); //呼出
				hrThree = hrThree.add(new BigDecimal(count2)); //呼入
				thThree = thThree.add(new BigDecimal(callCount));//3个月总通话次数
				threeHrTime = threeHrTime.add(new BigDecimal(count4));//三个月呼入通话时间
				threeHcTime = threeHcTime.add(new BigDecimal(count3));//三个月呼出通话时间
			}
			hcSix = hcSix.add(new BigDecimal(count1));
			hrSix = hrSix.add(new BigDecimal(count2));
			thSix = thSix.add(new BigDecimal(callCount));
			sixHrTime = sixHrTime.add(new BigDecimal(count4));//六个月呼入通话时间
			sixHcTime = sixHcTime.add(new BigDecimal(count3));//六个月呼出通话时间
		}
		//呼入次数/呼出次数（6个月）
		saveSixHrHc(hrSix, hcSix, fraudTplId, applicationId);
		
		//3个月呼入次数/6个月呼入次数
		saveThreeHcSixhR(hrThree, hrSix, fraudTplId, applicationId);
		
		//有通话联系人个数
		String contactCount6month = tdReportAllContact.getContactCount6month();
		if (null == contactCount6month) {
			contactCount6month = "0";
		}
		int count = Integer.valueOf(contactCount6month);
		saveSixLxCount(count, fraudTplId, applicationId);
		
		//呼入次数/呼出次数（3个月）
		saveThreeHrThreeHc(hrThree, hcThree, fraudTplId, applicationId);
		
		//3个月通话次数/6个月通话次数
		saveThreeThSixTh(thThree, thSix, fraudTplId, applicationId);
		
		//1个月呼入/呼出
		
		if (CollectionUtils.isEmpty(perMonths)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_ONE,"未知", applicationId,"02");
		}else{
			TdReportPerMonth tdReportPerMonth2 = perMonths.get(0);
			String callCountActive = tdReportPerMonth2.getCallCountActive();//主叫
			String callCountPassive = tdReportPerMonth2.getCallCountPassive();//被叫
			if (null == callCountActive) {
				callCountActive = "0";
			}
			if (null == callCountPassive) {
				callCountPassive = "0";
			}
			BigDecimal hcOne = new BigDecimal(callCountActive);
			BigDecimal hrOne = new BigDecimal(callCountPassive);
			saveOneHrHc(hrOne, hcOne, fraudTplId, applicationId);
		}
		
		
		//通话次数>=10联系人数(6个月)
		String contactCountCallCountOver106month = tdReportAllContact.getContactCountCallCountOver106month();
		if (null == contactCountCallCountOver106month) {
			contactCountCallCountOver106month = "0";
		}
		Integer countLx = Integer.valueOf(contactCountCallCountOver106month);
		saveCountLx(countLx, fraudTplId, applicationId);
		
		//3个月呼入时长/6个月呼入时长
		saveHrTime(threeHrTime, sixHrTime, fraudTplId, applicationId);
		
		//呼入次数>=10联系人数
		int countHr = tdReportDetailDao.queryHrCountById(applicationId);
		saveHrCount(countHr, fraudTplId, applicationId);
		//3个月呼出次数/6个月呼出次数
		saveThreeHcSixHc(hcThree, hcSix, fraudTplId, applicationId);
	}
	
	/**6个月联系人个数**/	
	private void saveSixLxCount(int count,String fraudTplId,String applicationId) {
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_TH_NUMBER,count, applicationId,"01");
	}
	/**3个月通话次数/6个月通话次数**/
	private void saveThreeThSixTh(BigDecimal thThree,BigDecimal thSix,String fraudTplId,String applicationId){
		if (0 == thSix.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.THREE_TH_SIX_TH,"未知", applicationId,"02");
		}else{
			BigDecimal divide = thThree.divide(thSix, 5, RoundingMode.HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.THREE_TH_SIX_TH,divide, applicationId,"01");
		}
	}
	/**通话次数>=10联系人数**/
	private void saveCountLx(Integer countLx,String fraudTplId,String applicationId){
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_TH_LX,countLx, applicationId,"01");
	}
	/**(1个月呼入呼出)**/
	private void saveOneHrHc(BigDecimal hrOne,BigDecimal hcOne,String fraudTplId,String applicationId){
		if (0 == hcOne.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_ONE,"未知", applicationId,"02");
		}else{
			BigDecimal divide = hrOne.divide(hcOne, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_ONE,divide, applicationId,"01");
		}
	}
	/**3个月呼入时长/6个月呼入时长**/
	private void saveHrTime(BigDecimal threeHrTime,BigDecimal sixHrTime,String fraudTplId,String applicationId){
		if (0 == sixHrTime.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.THREE_HR_SIX_HR,"未知", applicationId,"02");
		}else{
			BigDecimal divide = threeHrTime.divide(sixHrTime, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.THREE_HR_SIX_HR,divide, applicationId,"01");
		}
	}
	/**呼入次数>=10联系人数**/
	private void saveHrCount(Integer countHr,String fraudTplId,String applicationId){
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_HR_LX,countHr, applicationId,"01");
	}
	/**3个月呼出次数/6个月呼出次数**/
	private void saveThreeHcSixHc(BigDecimal hcThree,BigDecimal hcSix,String fraudTplId,String applicationId){
		if (0 == hcSix.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HC_THREE_HC_SIX,"未知", applicationId,"02");
		}else{
			BigDecimal divide = hcThree.divide(hcSix, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HC_THREE_HC_SIX,divide, applicationId,"01");
		}
	}
	/**入网时长**/
	private void saveRwTime(BigDecimal bigDecimal,String fraudTplId,String applicationId){
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_RW_TIME, bigDecimal, applicationId,"01");	
	}
	/**静默三天次数**/
	private void saveJmCount(BigDecimal bigDecimal,String fraudTplId,String applicationId){
		riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_TIME,bigDecimal, applicationId,"01");	
	}
	/**静默天数占比**/
	private void saveJmZb(BigDecimal bigDecimal1,BigDecimal bigDecimal2,String fraudTplId,String applicationId){
		if (0 == bigDecimal2.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_ZB, "未知", applicationId,"02");
		}else{
			BigDecimal divide = bigDecimal1.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YYS_JM_ZB,divide, applicationId,"01");
		}
	}
	/**夜间通话占比**/
	private void saveYjTime(BigDecimal bigDecimal1,BigDecimal bigDecimal2,String fraudTplId,String applicationId){
		if (0 == bigDecimal1.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.YJ_TH_ZB, "未知", applicationId,"02");
		}else{
			BigDecimal divide = bigDecimal2.divide(bigDecimal1, 2, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId,RiskFraudConstants.YJ_TH_ZB,divide, applicationId,"01");
		}
	}
	/**呼入次数/呼出次数（6个月）**/
	private void saveSixHrHc(BigDecimal hrSix,BigDecimal hcSix,String fraudTplId,String applicationId){
		if (0 == hcSix.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_SIX,"未知", applicationId,"02");
		}else {
			BigDecimal divide = hrSix.divide(hcSix, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_SIX,divide, applicationId,"01");	
		}
	}
	/**3个月呼入次数/6个月呼入次数**/
	private void saveThreeHcSixhR(BigDecimal hrThree,BigDecimal hrSix,String fraudTplId,String applicationId){
		if (0 == hrSix.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_THREE_SIX,"未知", applicationId,"02");
		}else{
			BigDecimal divide = hrThree.divide(hrSix, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_THREE_SIX,divide, applicationId,"01");
		}
	}
	/**呼入次数/呼出次数（3个月）**/
	private void saveThreeHrThreeHc(BigDecimal hrThree,BigDecimal hcThree,String fraudTplId,String applicationId){
		if (0 == hcThree.compareTo(BigDecimal.ZERO)) {
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_THREE,"未知", applicationId,"02");
		}else{
			BigDecimal divide = hrThree.divide(hcThree, 5, BigDecimal.ROUND_HALF_UP);
			riskFraudScoreService.queryApplyScoreApi(fraudTplId, RiskFraudConstants.HR_HC_THREE,divide, applicationId,"01");
		}
	}

}
