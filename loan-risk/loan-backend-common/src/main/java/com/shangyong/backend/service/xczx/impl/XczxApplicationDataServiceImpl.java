package com.shangyong.backend.service.xczx.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.utils.BanCodeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RedisConstant;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.dao.xczx.XczxApplicationDataDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.redis.fraud1_1.ZX9111Redis;
import com.shangyong.backend.entity.redis.fraud1_8.ZX9118Redis;
import com.shangyong.backend.entity.redis.fraud2_0.ZX9120Redis;
import com.shangyong.backend.entity.xczx.XczxApplicationData;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.xczx.TaskCallBackService;
import com.shangyong.backend.service.xczx.XczxApplicationDataService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.utils.RedisFraudUtils;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class XczxApplicationDataServiceImpl implements XczxApplicationDataService{

	@Autowired
	private XczxApplicationDataDao xczxApplicationDataDao;
	
	@Autowired
	private BuThirdpartyReportDao buThirdpartyReportDao;
	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Autowired
	private RiskRuleService riskRuleService;
	
	@Autowired
	private TaskCallBackService taskCallBackService;
	
	@Override
	public int saveEntitys(List<XczxApplicationData> list) {

		return xczxApplicationDataDao.saveEntitys(list);
	}

	@SuppressWarnings("unused")
	@Override
	public void saveLoans(String trxNo, JSONArray loanArray, String thirdPartyReportId, String buApplicationId)throws Exception{
		
		List<XczxApplicationData> loans = new ArrayList<XczxApplicationData>();
		
		List<Map<String,Object>> checkList = new ArrayList<Map<String,Object>>();
		
		BigDecimal refusedV = new BigDecimal(0);//拒单机构占比-历史记录  借款状态 0.未知1.拒贷2.批贷已放款4.借款人放弃申请5.审核中6.待放款  占比=拒贷/总记录数 (记录不存在，传0，取100分)
		BigDecimal overdueV = new BigDecimal(0);//逾期记录占比  0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清  逾期占比=（M2~M6+)/总记录数(记录不存在，传0，取100分)
		BigDecimal refundV = new BigDecimal(1);//已还款占比  0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清  已还款占比=（1或者9)/总记录数（(记录不存在，传1，取100分)）
		
		int refusedCount = 0;//拒单总次数
		int overdueCount = 0;//逾期总次数
		int refundCount = 0;//已还款总次数
		int loanSize = 0;//借款记录总条数
		int lendCnt = 0;
		int lendCntX1 = 0;
		int lendCntX5 = 0;
		long contractDate = 0l;
		String overdueRegex = "[3-8]";
		
		//需要存入redis
		Integer lendMoney91 = 0;
		Integer lendCntX391 = 0;
		Integer latestLendMoney91 = 0;
		int minGapDays = -1;
		boolean flag = false;

		ZX9111Redis data11 = new ZX9111Redis();
		ZX9118Redis data18 = new ZX9118Redis();
		ZX9120Redis data20 = new ZX9120Redis();
		
		Application application = applicationDao.getObjectById(buApplicationId);

		if(CollectionUtils.isNotEmpty(loanArray)){
			XczxApplicationData xczxApplicationData = null;

			String dateStr = DateUtils.getDate(new Date());
			int size = loanArray.size();
			loanSize = loanArray.size();
 			
			for(int i = 0; i < loanArray.size(); i ++){
				xczxApplicationData = new XczxApplicationData();
				JSONObject jsonObj = loanArray.getJSONObject(i);
				xczxApplicationData.setGuid(trxNo);
				xczxApplicationData.setCreateTime(dateStr);
				xczxApplicationData.setModifyTime(dateStr);
				xczxApplicationData.setApplicationDataId(UUIDUtils.getUUID());
				xczxApplicationData.setBuApplicationId(buApplicationId);
				xczxApplicationData.setArrearsAmount(jsonObj.getString("arrearsAmount"));
				xczxApplicationData.setBorrowAmount(jsonObj.getString("borrowAmount"));
				xczxApplicationData.setBorrowState(jsonObj.getString("borrowState"));
				xczxApplicationData.setBorrowType(jsonObj.getString("borrowType"));
				xczxApplicationData.setContractDate(jsonObj.getString("contractDate"));
				
				//如果xczx_application_data没有数据存入unknown，只要有数据就不是unknown
				flag = true;
				if("2".equals(xczxApplicationData.getBorrowState())){

					lendMoney91 = handlerLendMoney91(lendMoney91,xczxApplicationData.getBorrowAmount());
					lendCntX391 = handlerLendCntX391(lendCntX391,xczxApplicationData.getBorrowAmount());
					
					//判断是否是最后一次成功借款
					if (Long.parseLong(xczxApplicationData.getContractDate()) > contractDate) {
						latestLendMoney91 = handlerlatestLendMoney91(latestLendMoney91,xczxApplicationData.getBorrowAmount());
						String auditingDateStr = application.getAuditingTime();
						Date auditingDate = DateUtils.convertStringToDate(DateUtils.dateTimePattern, auditingDateStr);
						contractDate = Long.parseLong(xczxApplicationData.getContractDate());
						minGapDays = (int) ((auditingDate.getTime() - Long.parseLong(xczxApplicationData.getContractDate())) / (double) (1000 * 60 * 60 * 24));
					}else if(Long.parseLong(xczxApplicationData.getContractDate()) == contractDate){
						latestLendMoney91 = latestLendMoney91 +handlerlatestLendMoney91(latestLendMoney91,xczxApplicationData.getBorrowAmount());
					}
					
				}
				
				xczxApplicationData.setCompanyCode(jsonObj.getString("companyCode"));
//				xczxApplicationData.setContractDate(jsonObj.getString("contractDate"));
				xczxApplicationData.setLoanPeriod(jsonObj.getString("loanPeriod"));
				xczxApplicationData.setRepayState(jsonObj.getString("repayState"));
				xczxApplicationData.setThirdpartyReportId(thirdPartyReportId);
				loans.add(xczxApplicationData);
				// 借款状态： 0.未知1.拒贷2.批贷已放款4.借款人放弃申请5.审核中6.待放款 （3同6意义相同）
				if("2".equals(jsonObj.getString("borrowState"))){
					refusedCount ++;
					lendCnt ++;
				}
				
				if(jsonObj.getString("repayState").matches(overdueRegex)){
					overdueCount ++;
				}
				//还款状态： 0.未知1.正常2.M1 3.M2 4.M3 5.M4 6.M5 7.M6 8.M6+ 9.已还清
				if("1".equals(jsonObj.getString("repayState")) || "9".equals(jsonObj.getString("repayState"))){
					refundCount ++;
				}
				
				if ("2".equals(xczxApplicationData.getBorrowState()) && "-7".equals(xczxApplicationData.getBorrowAmount())) {
					lendCntX1 ++;
				}
				
				if ("2".equals(xczxApplicationData.getBorrowState()) && "-3".equals(xczxApplicationData.getBorrowAmount())) {
					lendCntX5 ++;
				}
				
				BanCodeUtil.addCheckPoint(checkList,BanCodeEnum.NINE_ONE_CREDIT_M1_ABOVE.getCode(),jsonObj.getString("repayState"));//添加禁止项数据
				
				if (i % Constants.BATCH_INSERT_SIZE == 0 || i == (size - 1)) {
					
					xczxApplicationDataDao.saveEntitys(loans);
					loans.clear();
				}
			}
			
			refusedV = new BigDecimal(refusedCount).divide(new BigDecimal(loanSize),20,RoundingMode.HALF_UP);
			overdueV = new BigDecimal(overdueCount).divide(new BigDecimal(loanSize),20,RoundingMode.HALF_UP);
			refundV = new BigDecimal(refundCount).divide(new BigDecimal(loanSize),20,RoundingMode.HALF_UP);
		}
		
		if(flag){
			if (-1 == minGapDays) {
				data18.setMinGapDays91(ZX9118Redis.DEFAULT_MIN_GAP_DAYS_91);
				data20.setMinGapDays91(ZX9120Redis.DEFAULT_MIN_GAP_DAYS_91);
			}else{
				data18.setMinGapDays91(String.valueOf(minGapDays));
				data20.setMinGapDays91(String.valueOf(minGapDays));
			}
			
			data11.setLendMoney91(String.valueOf(lendMoney91));//借款总额
			data11.setLendCntX391(String.valueOf(lendCntX391));//借款次数
			
			data18.setLendCnt91(String.valueOf(lendCnt));//借款状态
			data18.setLendCntX191(String.valueOf(lendCntX1));//还款状态
			data18.setLendCntX591(String.valueOf(lendCntX5));//合同金额
			data18.setLatestLendMoney91(String.valueOf(latestLendMoney91));//最后一次借款金额
			data18.setLendMoney91(String.valueOf(lendMoney91));//借款总额
			
			data20.setLendCnt91(String.valueOf(lendCnt));//借款状态
			data20.setLendCntX191(String.valueOf(lendCntX1));//还款状态
			data20.setLatestLendMoney91(String.valueOf(latestLendMoney91));//最后一次借款金额
			data20.setLendMoney91(String.valueOf(lendMoney91));//借款总额
		}
		
		String key = RedisConstant.buildFraudScoresKey(buApplicationId, FraudBizEnum.ZX91);//欺诈评分 FRAUD:SCORES:申请编号:zx91
		String key18 = RedisConstant.buildFraudScoresKey1_8(buApplicationId, FraudBizEnum.ZX91);//欺诈评分1.8 FRAUD:SCORES:1.8:申请编号:zx91
		String key20 = RedisConstant.buildFraudScoresKey2_0(buApplicationId, FraudBizEnum.ZX91);//欺诈评分2.0 FRAUD:SCORES:2.0:申请编号:zx91
		RedisFraudUtils.hmset(key, data11.toMap());
        RedisFraudUtils.hmset(key18, data18.toMap());
        RedisFraudUtils.hmset(key20, data20.toMap());
        
		String appLevel = application.getAppLevel();
		
		if(application == null){
			throw new RuntimeException("91征信数据报告获取-->调用禁止项-->application 为空");
		}
		
		RuleResult resultObj = riskRuleService.querySafeRuleApi(application,checkList);//调用禁止项，获取用户校验结果
		
		if(resultObj == null){
			throw new RuntimeException("91征信数据报告获取-->调用taskCallBackService-->resultObj 为空");
		}
		
		taskCallBackService.callBackUpdateStatus(resultObj, buApplicationId,appLevel);//推送数据报告到主流程上
		
		//调用评分项进行信用评分 暂时功能不开放 后续开放
//		//拒单评分项调用
//		riskScoreServiceImpl.queryApplyScoreApi(application.getScoreTplId(), RuleConstants.SCORE_REFUSE_ORG_PERCENT, refusedV, buApplicationId);
//		//逾期评分项调用
//		riskScoreServiceImpl.queryApplyScoreApi(application.getScoreTplId(), RuleConstants.SCORE_OVERDUE_PERCENT, overdueV, buApplicationId);
//		//还款评分项调用
//		riskScoreServiceImpl.queryApplyScoreApi(application.getScoreTplId(), RuleConstants.SCORE_RETURN_LOAN_PERCENT, refundV, buApplicationId);

	}
	//获取借款次数
	private Integer handlerLendCntX391(Integer lendCntX391, String borrowAmount) {
		if("-5".equals(borrowAmount)){
			lendCntX391 = lendCntX391 + 1;
		}
		return lendCntX391;
	}
	//获取借款总额
	private Integer handlerLendMoney91(Integer lendMoney91, String borrowAmount) {
		if(StringUtils.isNotEmpty(borrowAmount)){
			if("-7".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 500;
			}else if("-6".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 1500;
			}else if("-5".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 2500;
			}else if("-4".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 3500;
			}else if("-3".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 5000;
			}else if("-2".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 7000;
			}else if("-1".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 9000;
			}else if("0".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 0;
			}else if("1".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 15000;
			}else if("2".equals(borrowAmount)){
				lendMoney91 = lendMoney91 + 30000;
			}else{
				lendMoney91 = lendMoney91 + (Integer.parseInt(borrowAmount) * 20000) - 10000;
			}
		}
		return lendMoney91;
	}
	//获取最后一次借款金额
	private Integer handlerlatestLendMoney91(Integer latestLendMoney91,String borrowAmount){
		if(StringUtils.isNotEmpty(borrowAmount)){
			if("-7".equals(borrowAmount)){
				latestLendMoney91 =  500;
			}else if("-6".equals(borrowAmount)){
				latestLendMoney91 =  1500;
			}else if("-5".equals(borrowAmount)){
				latestLendMoney91 =  2500;
			}else if("-4".equals(borrowAmount)){
				latestLendMoney91 =  3500;
			}else if("-3".equals(borrowAmount)){
				latestLendMoney91 =  5000;
			}else if("-2".equals(borrowAmount)){
				latestLendMoney91 =  7000;
			}else if("-1".equals(borrowAmount)){
				latestLendMoney91 =  9000;
			}else if("0".equals(borrowAmount)){
				latestLendMoney91 =  0;
			}else if("1".equals(borrowAmount)){
				latestLendMoney91 =  15000;
			}else if("2".equals(borrowAmount)){
				latestLendMoney91 =  30000;
			}else{
				latestLendMoney91 = Integer.parseInt(borrowAmount)*20000-10000;
			}
		}
		return latestLendMoney91;
	}
	@Override
	public boolean updateJsonPath(BuThirdpartyReport obj) {
		
		boolean flag = false;
		
		if(buThirdpartyReportDao.updateEntity(obj) > 0){
			
			flag = true;
		}
		
		return flag;
	}
	

	@Override
	public List<XczxApplicationData> getDataInfo(XczxApplicationData xczxApplicationData) {
		return xczxApplicationDataDao.getDataInfo(xczxApplicationData);
	}

	@Override
	public int findAllCount(XczxApplicationData xczxApplicationData) {
		return xczxApplicationDataDao.findAllCount(xczxApplicationData);
	}
}
