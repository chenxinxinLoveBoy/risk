package com.shangyong.backend.service.jg.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.jg.JgCustomDivisor;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.jg.JgCustomDivisorService;
import com.shangyong.backend.utils.SpringIocUtils;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

@Service
public class JgCustomDivisorServiceImpl implements JgCustomDivisorService {

	private static Logger LOGGER=LoggerFactory.getLogger("jgDivisor");	
	
	public static String url = "http://api.data.jiguang.cn/v1/ctm/shangyong/factors";
	
	@Override
	public void getJqCustomDivisor(Application application) {
		LOGGER.info("【jgDivisor】开始获取用户"+application.getApplicationId()+"的极光因子数据");
    	SysParamRedisService sysParamRedisService = SpringIocUtils.getBean(SysParamRedisService.class);
    	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.JG_FQZ_CODE);
    	String devkey = sysParam.getParamValueTwo();
    	String devSecret = sysParam.getParamValueThree();
    	String AUTH_STR =Base64Utils.encodeToString((devkey+":"+devSecret).getBytes());
    	Map<String, String> headers=new HashMap<>();
    	headers.put("Authorization", "Basic "+AUTH_STR);
        headers.put("accept", "application/json");
    	
    	Assert.hasText(application.getApplicationId(),"ApplicationId参数为空");
    	Assert.hasText(application.getName(),"Name参数为空");
		Assert.hasText(application.getCertCode(),"CertCode参数为空");
		Assert.hasText(application.getPhoneNum(),"PhoneNum参数为空");
    	String name=application.getName();//用户姓名
    	String id_number=application.getCertCode();//获取身份证信息
    	String phone=application.getPhoneNum();
    	String applicationId = application.getApplicationId();
    	url+="?name="+name+"&id_number="+id_number+"&phone="+phone;

    	try {
			String result = RiskHttpClientUtil.doGetFromHeader(url, headers);
			if (result==null) {
				LOGGER.info("【jgDivisor】获取用户极光因子数据为空,applicationId:"+applicationId);
				new RuntimeException("请求返回为空");
			}
			JSONObject object = JSONObject.fromObject(result);
			String code = object.getString("code");
			
			if ("2000".equals(code)) {
				LOGGER.info("【jgDivisor】成功获取用户极光因子数据,applicationId:"+applicationId);
				JSONObject data = object.getJSONObject("data");
				savejiguangCustomDivisor(application,data);
			}else if ("2001".equals(code)) {
				LOGGER.info("【jgDivisor】获取用户极光因子数据成功，但极光因子数据为空,applicationId:"+applicationId+",message:"+object.getString("message"));
			}else if ("4005".equals(code)) {
				LOGGER.info("【jgDivisor】极光因子未授权,权限过期,调用次数超次,或调用频率超频等"+",message:"+object.getString("message"));
				new RuntimeException("极光因子未授权");
			}else {
				LOGGER.info("【jgDivisor】极光因子系统内部异常,applicationId:"+applicationId+",message:"+object.getString("message"));
				new RuntimeException("极光因子系统内部异常");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}

	public JgCustomDivisor savejiguangCustomDivisor(Application application, JSONObject object) {
		
		JgCustomDivisor divisor = new JgCustomDivisor();
		divisor.setJgCustomDivisorId(UUIDUtils.getUUID());
		divisor.setBuApplicationId(application.getApplicationId());
		divisor.setName(application.getName());
		divisor.setIdNumber(application.getCertCode());
		divisor.setPhone(application.getPhoneNum());
		divisor.setCreateTime(DateUtils.getDate(new Date()));
		divisor.setFinFinanceTotalOpenWorkTime0w1w(object.containsKey("FIN_Finance_total_open_work_time_0W_1W")?object.getString("FIN_Finance_total_open_work_time_0W_1W"):null);
		divisor.setSopGeneralAll0m1m(object.containsKey("SOP_General_all_0M_1M")?object.getString("SOP_General_all_0M_1M"):null);
		divisor.setFinLoanSmallInstalling2m3m(object.containsKey("FIN_Loan_Small_installing_2M_3M")?object.getString("FIN_Loan_Small_installing_2M_3M"):null);
		divisor.setFinBankAll0m1m(object.containsKey("FIN_Bank_all_0M_1M")?object.getString("FIN_Bank_all_0M_1M"):null);
		divisor.setFinLoanSmallAvgUsetime0m1m(object.containsKey("FIN_Loan_Small_avg_usetime_0M_1M")?object.getString("FIN_Loan_Small_avg_usetime_0M_1M"):null);
		divisor.setFinLoanAvgUsetime1m2m(object.containsKey("FIN_Loan_avg_usetime_1M_2M")?object.getString("FIN_Loan_avg_usetime_1M_2M"):null);
		divisor.setFinLoanTotalOpen0w1w(object.containsKey("FIN_Loan_total_open_0W_1W")?object.getString("FIN_Loan_total_open_0W_1W"):null);
		divisor.setFinFinanceAvgUsetime2m3m(object.containsKey("FIN_Finance_avg_usetime_2M_3M")?object.getString("FIN_Finance_avg_usetime_2M_3M"):null);
		divisor.setMediaVedioplayerInstalling1m2m(object.containsKey("MEDIA_VedioPlayer_installing_1M_2M")?object.getString("MEDIA_VedioPlayer_installing_1M_2M"):null);
		divisor.setFinLoanSmallAll1m2m(object.containsKey("FIN_Loan_Small_all_1M_2M")?object.getString("FIN_Loan_Small_all_1M_2M"):null);
		divisor.setFinLoanSmallInstalling0m1m(object.containsKey("FIN_Loan_Small_installing_0M_1M")?object.getString("FIN_Loan_Small_installing_0M_1M"):null);
		divisor.setFinLoanSmallTotalTime2m3m(object.containsKey("FIN_Loan_Small_total_time_2M_3M")?object.getString("FIN_Loan_Small_total_time_2M_3M"):null);
		divisor.setLbsWifiCnt0m1m(object.containsKey("LBS_Wifi_Cnt_0M_1M")?object.getString("LBS_Wifi_Cnt_0M_1M"):null);
		divisor.setSopGeneralInstalling0m1m(object.containsKey("SOP_General_installing_0M_1M")?object.getString("SOP_General_installing_0M_1M"):null);
		divisor.setFinLoanTotalTime0w1w(object.containsKey("FIN_Loan_total_time_0W_1W")?object.getString("FIN_Loan_total_time_0W_1W"):null);
		divisor.setFinLoanTotalOpen1m2m(object.containsKey("FIN_Loan_total_open_1M_2M")?object.getString("FIN_Loan_total_open_1M_2M"):null);
		divisor.setFinFinanceTotalTime1m2m(object.containsKey("FIN_Finance_total_time_1M_2M")?object.getString("FIN_Finance_total_time_1M_2M"):null);
		divisor.setFinLoanOtherAll1m2m(object.containsKey("FIN_Loan_Other_all_1M_2M")?object.getString("FIN_Loan_Other_all_1M_2M"):null);
		divisor.setFinLoanAll1m2m(object.containsKey("FIN_Loan_all_1M_2M")?object.getString("FIN_Loan_all_1M_2M"):null);
		divisor.setFinLoanSmallTotalOpen0m1m(object.containsKey("FIN_Loan_Small_total_open_0M_1M")?object.getString("FIN_Loan_Small_total_open_0M_1M"):null);
		divisor.setLbsUploadDateCnt1m2m(object.containsKey("LBS_Upload_Date_Cnt_1M_2M")?object.getString("LBS_Upload_Date_Cnt_1M_2M"):null);
		divisor.setFinLoanAvgUsetime0w1w(object.containsKey("FIN_Loan_avg_usetime_0W_1W")?object.getString("FIN_Loan_avg_usetime_0W_1W"):null);
		divisor.setFinLoanTotalTime2m3m(object.containsKey("FIN_Loan_total_time_2M_3M")?object.getString("FIN_Loan_total_time_2M_3M"):null);
		divisor.setFinLoanSmallTotalOpenWorkTime0w1w(object.containsKey("FIN_Loan_Small_total_open_work_time_0W_1W")?object.getString("FIN_Loan_Small_total_open_work_time_0W_1W"):null);
		divisor.setMutiRepaySuccessAppCnt0m1m(object.containsKey("MUTI_Repay_Success_APP_cnt_0M_1M")?object.getString("MUTI_Repay_Success_APP_cnt_0M_1M"):null);
		divisor.setFinFinanceInstalling2m3m(object.containsKey("FIN_Finance_installing_2M_3M")?object.getString("FIN_Finance_installing_2M_3M"):null);
		divisor.setFinLoanAll0m1m(object.containsKey("FIN_Loan_all_0M_1M")?object.getString("FIN_Loan_all_0M_1M"):null);
		divisor.setLbsCell4gRation0m1m(object.containsKey("LBS_Cell_4G_Ration_0M_1M")?object.getString("LBS_Cell_4G_Ration_0M_1M"):null);
		divisor.setFinLoanSmallAvgUsetimeWorkTime0w1w(object.containsKey("FIN_Loan_Small_avg_usetime_work_time_0W_1W")?object.getString("FIN_Loan_Small_avg_usetime_work_time_0W_1W"):null);
		divisor.setFinLoanTotalTime1m2m(object.containsKey("FIN_Loan_total_time_1M_2M")?object.getString("FIN_Loan_total_time_1M_2M"):null);
		divisor.setLbsGpsRatio1m2m(object.containsKey("LBS_Gps_Ratio_1M_2M")?object.getString("LBS_Gps_Ratio_1M_2M"):null);
		divisor.setFinFinanceTotalTime2m3m(object.containsKey("FIN_Finance_total_time_2M_3M")?object.getString("FIN_Finance_total_time_2M_3M"):null);
		divisor.setFinLoanOtherAll0m1m(object.containsKey("FIN_Loan_Other_all_0M_1M")?object.getString("FIN_Loan_Other_all_0M_1M"):null);
		divisor.setFinFinanceTotalOpen2m3m(object.containsKey("FIN_Finance_total_open_2M_3M")?object.getString("FIN_Finance_total_open_2M_3M"):null);
		divisor.setFinBankInstalling0m1m(object.containsKey("FIN_Bank_installing_0M_1M")?object.getString("FIN_Bank_installing_0M_1M"):null);
		divisor.setFinDebitPctInstalling2m3m(object.containsKey("FIN_Debit_PCT_installing_2M_3M")?object.getString("FIN_Debit_PCT_installing_2M_3M"):null);
		divisor.setFinLoanSmallAvgUsetime2m3m(object.containsKey("FIN_Loan_Small_avg_usetime_2M_3M")?object.getString("FIN_Loan_Small_avg_usetime_2M_3M"):null);
		divisor.setFinLoanSmallInstalling1m2m(object.containsKey("FIN_Loan_Small_installing_1M_2M")?object.getString("FIN_Loan_Small_installing_1M_2M"):null);
		divisor.setFinFinanceTotalTimeWorkTime0w1w(object.containsKey("FIN_Finance_total_time_work_time_0W_1W")?object.getString("FIN_Finance_total_time_work_time_0W_1W"):null);
		divisor.setFinLoanSmallTotalOpen0w1w(object.containsKey("FIN_Loan_Small_total_open_0W_1W")?object.getString("FIN_Loan_Small_total_open_0W_1W"):null);
		divisor.setFinPaymentAll0m1m(object.containsKey("FIN_Payment_all_0M_1M")?object.getString("FIN_Payment_all_0M_1M"):null);
		divisor.setFinLoanTotalTimeWorkTime0w1w(object.containsKey("FIN_Loan_total_time_work_time_0W_1W")?object.getString("FIN_Loan_total_time_work_time_0W_1W"):null);
		divisor.setFinFinanceAvgUsetime0m1m(object.containsKey("FIN_Finance_avg_usetime_0M_1M")?object.getString("FIN_Finance_avg_usetime_0M_1M"):null);
		divisor.setFinLoanSmallTotalTime0m1m(object.containsKey("FIN_Loan_Small_total_time_0M_1M")?object.getString("FIN_Loan_Small_total_time_0M_1M"):null);
		divisor.setFinLoanTotalOpen2m3m(object.containsKey("FIN_Loan_total_open_2M_3M")?object.getString("FIN_Loan_total_open_2M_3M"):null);
		divisor.setFinFinanceTotalTime0m1m(object.containsKey("FIN_Finance_total_time_0M_1M")?object.getString("FIN_Finance_total_time_0M_1M"):null);
		divisor.setLifFoodEntertainmentInstalling1m2m(object.containsKey("LIF_Food&Entertainment_installing_1M_2M")?object.getString("LIF_Food&Entertainment_installing_1M_2M"):null);
		divisor.setFinLoanAvgUsetime2m3m(object.containsKey("FIN_Loan_avg_usetime_2M_3M")?object.getString("FIN_Loan_avg_usetime_2M_3M"):null);
		divisor.setLbsWorkPopDensity0m1m(object.containsKey("LBS_Work_Pop_Density_0M_1M")?object.getString("LBS_Work_Pop_Density_0M_1M"):null);
		divisor.setSopGeneralInstalling1m2m(object.containsKey("SOP_General_installing_1M_2M")?object.getString("SOP_General_installing_1M_2M"):null);
		divisor.setLbsUploadDateCnt2m3m(object.containsKey("LBS_Upload_Date_Cnt_2M_3M")?object.getString("LBS_Upload_Date_Cnt_2M_3M"):null);
		divisor.setFinFinanceAvgUsetimeWorkTime0w1w(object.containsKey("FIN_Finance_avg_usetime_work_time_0W_1W")?object.getString("FIN_Finance_avg_usetime_work_time_0W_1W"):null);
		divisor.setFinDebitPctAll0m1m(object.containsKey("FIN_Debit_PCT_all_0M_1M")?object.getString("FIN_Debit_PCT_all_0M_1M"):null);
		divisor.setFinLoanInstalling2m3m(object.containsKey("FIN_Loan_installing_2M_3M")?object.getString("FIN_Loan_installing_2M_3M"):null);
		divisor.setFinLoanSmallTotalOpen1m2m(object.containsKey("FIN_Loan_Small_total_open_1M_2M")?object.getString("FIN_Loan_Small_total_open_1M_2M"):null);
		divisor.setFinFinanceAll1m2m(object.containsKey("FIN_Finance_all_1M_2M")?object.getString("FIN_Finance_all_1M_2M"):null);
		divisor.setNetImeiPhoneImsiCnt(object.containsKey("NET_IMEI_Phone_IMSI_cnt")?object.getString("NET_IMEI_Phone_IMSI_cnt"):null);
		divisor.setFinFinanceInstalling0m1m(object.containsKey("FIN_Finance_installing_0M_1M")?object.getString("FIN_Finance_installing_0M_1M"):null);
		divisor.setFinLoanUninstall0m1m(object.containsKey("FIN_Loan_uninstall_0M_1M")?object.getString("FIN_Loan_uninstall_0M_1M"):null);
		divisor.setFinPaymentInstalling0m1m(object.containsKey("FIN_Payment_installing_0M_1M")?object.getString("FIN_Payment_installing_0M_1M"):null);
		divisor.setLbsLbsCnt0m1m(object.containsKey("LBS_Lbs_Cnt_0M_1M")?object.getString("LBS_Lbs_Cnt_0M_1M"):null);
		divisor.setFinLoanSmallAvgUsetime1m2m(object.containsKey("FIN_Loan_Small_avg_usetime_1M_2M")?object.getString("FIN_Loan_Small_avg_usetime_1M_2M"):null);
		divisor.setFinFinanceAvgUsetime1m2m(object.containsKey("FIN_Finance_avg_usetime_1M_2M")?object.getString("FIN_Finance_avg_usetime_1M_2M"):null);
		divisor.setFinFinanceAvgUsetime0w1w(object.containsKey("FIN_Finance_avg_usetime_0W_1W")?object.getString("FIN_Finance_avg_usetime_0W_1W"):null);
		divisor.setFinFinanceTotalOpen0m1m(object.containsKey("FIN_Finance_total_open_0M_1M")?object.getString("FIN_Finance_total_open_0M_1M"):null);
		divisor.setFinLoanSmallTotalTime0w1w(object.containsKey("FIN_Loan_Small_total_time_0W_1W")?object.getString("FIN_Loan_Small_total_time_0W_1W"):null);
		divisor.setFinDebitPctInstalling1m2m(object.containsKey("FIN_Debit_PCT_installing_1M_2M")?object.getString("FIN_Debit_PCT_installing_1M_2M"):null);
		divisor.setFinLoanInstalling0m1m(object.containsKey("FIN_Loan_installing_0M_1M")?object.getString("FIN_Loan_installing_0M_1M"):null);
		divisor.setFinDebitPctInstalling0m1m(object.containsKey("FIN_Debit_PCT_installing_0M_1M")?object.getString("FIN_Debit_PCT_installing_0M_1M"):null);
		divisor.setFinFinanceTotalTime0w1w(object.containsKey("FIN_Finance_total_time_0W_1W")?object.getString("FIN_Finance_total_time_0W_1W"):null);
		divisor.setSocChatFriendInstalling0m1m(object.containsKey("SOC_Chat_Friend_installing_0M_1M")?object.getString("SOC_Chat_Friend_installing_0M_1M"):null);
		divisor.setFinPaymentGeneralInstalling1m2m(object.containsKey("FIN_Payment_General_installing_1M_2M")?object.getString("FIN_Payment_General_installing_1M_2M"):null);
		divisor.setMutiRepaySuccessCnt0m1m(object.containsKey("MUTI_Repay_Success_cnt_0M_1M")?object.getString("MUTI_Repay_Success_cnt_0M_1M"):null);
		divisor.setLbsLbsCnt1m2m(object.containsKey("LBS_Lbs_Cnt_1M_2M")?object.getString("LBS_Lbs_Cnt_1M_2M"):null);
		divisor.setDeviceStability0m1m(object.containsKey("DEVICE_Stability_0M_1M")?object.getString("DEVICE_Stability_0M_1M"):null);
		divisor.setFinFinanceTotalOpen1m2m(object.containsKey("FIN_Finance_total_open_1M_2M")?object.getString("FIN_Finance_total_open_1M_2M"):null);
		divisor.setFinLoanSmallTotalTime1m2m(object.containsKey("FIN_Loan_Small_total_time_1M_2M")?object.getString("FIN_Loan_Small_total_time_1M_2M"):null);
		divisor.setFinLoanTotalOpen0m1m(object.containsKey("FIN_Loan_total_open_0M_1M")?object.getString("FIN_Loan_total_open_0M_1M"):null);
		divisor.setFinLoanAvgUsetimeWorkTime0w1w(object.containsKey("FIN_Loan_avg_usetime_work_time_0W_1W")?object.getString("FIN_Loan_avg_usetime_work_time_0W_1W"):null);
		divisor.setFinLoanSmallAvgUsetime0w1w(object.containsKey("FIN_Loan_Small_avg_usetime_0W_1W")?object.getString("FIN_Loan_Small_avg_usetime_0W_1W"):null);
		divisor.setBusSoftwareInstalling0m1m(object.containsKey("BUS_Software_installing_0M_1M")?object.getString("BUS_Software_installing_0M_1M"):null);
		divisor.setLbsCityCnt2m3m(object.containsKey("LBS_City_Cnt_2M_3M")?object.getString("LBS_City_Cnt_2M_3M"):null);
		divisor.setNetImeiPhoneCnt(object.containsKey("NET_IMEI_Phone_cnt")?object.getString("NET_IMEI_Phone_cnt"):null);
		divisor.setNetImeiAbnormal(object.containsKey("NET_IMEI_Abnormal")?object.getString("NET_IMEI_Abnormal"):null);
		divisor.setFinLoanTotalTime0m1m(object.containsKey("FIN_Loan_total_time_0M_1M")?object.getString("FIN_Loan_total_time_0M_1M"):null);
		divisor.setFinLoanInstalling1m2m(object.containsKey("FIN_Loan_installing_1M_2M")?object.getString("FIN_Loan_installing_1M_2M"):null);
		divisor.setFinFinanceAll0m1m(object.containsKey("FIN_Finance_all_0M_1M")?object.getString("FIN_Finance_all_0M_1M"):null);
		divisor.setFinLoanSmallTotalOpen2m3m(object.containsKey("FIN_Loan_Small_total_open_2M_3M")?object.getString("FIN_Loan_Small_total_open_2M_3M"):null);
		divisor.setLbsGpsRatio2m3m(object.containsKey("LBS_Gps_Ratio_2M_3M")?object.getString("LBS_Gps_Ratio_2M_3M"):null);
		divisor.setLbsUploadDateCnt0m1m(object.containsKey("LBS_Upload_Date_Cnt_0M_1M")?object.getString("LBS_Upload_Date_Cnt_0M_1M"):null);
		divisor.setStatusHaveChildInstalling0m1m(object.containsKey("STATUS_Have_Child_installing_0M_1M")?object.getString("STATUS_Have_Child_installing_0M_1M"):null);
		divisor.setFinLoanSmallAll0m1m(object.containsKey("FIN_Loan_Small_all_0M_1M")?object.getString("FIN_Loan_Small_all_0M_1M"):null);
		divisor.setFinFinanceInstalling1m2m(object.containsKey("FIN_Finance_installing_1M_2M")?object.getString("FIN_Finance_installing_1M_2M"):null);
		divisor.setSopGeneralInstalling2m3m(object.containsKey("SOP_General_installing_2M_3M")?object.getString("SOP_General_installing_2M_3M"):null);
		divisor.setMutiHaveOverdue15d0m1m(object.containsKey("MUTI_Have_Overdue_15D_0M_1M")?object.getString("MUTI_Have_Overdue_15D_0M_1M"):null);
		divisor.setFinPaymentInstalling1m2m(object.containsKey("FIN_Payment_installing_1M_2M")?object.getString("FIN_Payment_installing_1M_2M"):null);
		divisor.setEduBooksInstalling0m1m(object.containsKey("EDU_Books_installing_0M_1M")?object.getString("EDU_Books_installing_0M_1M"):null);
		divisor.setFinLoanAvgUsetime0m1m(object.containsKey("FIN_Loan_avg_usetime_0M_1M")?object.getString("FIN_Loan_avg_usetime_0M_1M"):null);
		divisor.setFinFinanceTotalOpen0w1w(object.containsKey("FIN_Finance_total_open_0W_1W")?object.getString("FIN_Finance_total_open_0W_1W"):null);
		divisor.setFinLoanSmallTotalTimeWorkTime0w1w(object.containsKey("FIN_Loan_Small_total_time_work_time_0W_1W")?object.getString("FIN_Loan_Small_total_time_work_time_0W_1W"):null);
		return divisor;
	}

}
