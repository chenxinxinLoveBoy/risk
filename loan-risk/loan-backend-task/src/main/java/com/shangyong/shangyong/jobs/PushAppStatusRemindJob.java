package com.shangyong.shangyong.jobs;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.shangyong.authcenter.dubbo.impl.AuthMoneyInfoService;
import com.shangyong.backend.common.DictConstant;
import com.shangyong.backend.common.consts.RedisKeyConstants;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.mq.RabbitSms;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.RabbitMqServer;
import com.shangyong.backend.service.TdLoanInterfaceService;
import com.shangyong.entity.BaseResult;
import com.shangyong.shangyong.utils.SpringContextUtils;
import com.shangyong.utils.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.shangyong.msgcenter.smsService.SmsService;

/**
 * 
 * @Author wqk
 * @Date 2018年7月25日
 * @Description 返回审批状态给App，通知App是否审批通过
 * 				定时查询不是【待审核】，且未通知App的数据。
 * 				根据订单申请表的is_push_app：字段判断是否已推送，推送成功则更新is_push_app字段
 *
 */
@Component
public class PushAppStatusRemindJob implements Job {

	private static Logger pushAppStatusLogger = (Logger) LoggerFactory.getLogger("pushAppStatus");
	
	@Autowired
	private TdLoanInterfaceService tdLoanInterfaceService;

	@Autowired
	private AuthMoneyInfoService authMoneyInfoService;
	
	@Autowired
	private IScAlarm iScAlarm;
	
	@Autowired
    RabbitMqServer rabbitMqServer;

	@Reference(version="1.0.0",retries=-1,timeout=15000)
	//private SmsService smsService;
	
	private static final String CODE = "200";
	
	private static final String APPROVALFAILED = "0";
	
	private static final String BUSINESSTYPE = "5";
	
	private static final String VER = "666";
	
//    @Override
//    public SaturnJobReturn handleJavaJob(String s, Integer shardItem, String s1,
//                                         SaturnJobExecutionContext saturnJobExecutionContext) throws InterruptedException {
//    	scheduler();
//        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
//    }
	
	public void scheduler() {
		pushAppStatusLogger.info("[推送app审批结果] 定时任务获取待处理数据，当前任务处理心跳正常");
		List<Application> dataList = tdLoanInterfaceService.getAppStatusByPush();
		if (CollectionUtils.isNotEmpty(dataList)) {
			pushAppStatusLogger.info("[推送app审批结果] 获取需推送APP数据列表, 待处理数据条数： " + dataList.size());
            for (Application application : dataList) {
				BaseResult<T> baseResult = new BaseResult<T>();
				try {
					String appSerialNumber = application.getAppSerialNumber();

					Map<String, String> params = new HashMap<String, String>( );

					//APP应用客户编号
					params.put("customerId", application.getCustomerId());
					//APP应用请求流水号
					params.put("serialNumber", application.getAppSerialNumber());
					//授信额度
					params.put("authMoney", String.valueOf(application.getCreditMoney()));
					//是否通过（1-待审批、2-审批通过、3-审批未通过、4-待人工确认）
					String isPass = application.getAuditingState();
					if(DictConstant.AUDITING_STATE_TWO.equals(isPass)){
						params.put("isPass", DictConstant.AUDITING_STATE);
					}else{
						params.put("isPass", APPROVALFAILED);
					}

					pushAppStatusLogger.info("[推送app审批结果] [appSerialNumber=" + appSerialNumber + "] 参数：" + params);

					//DUBBO调用APP的接口，返回审批状态给APP，通知APP是否审批通过
					baseResult = authMoneyInfoService.updateAuthMoney(params);

					pushAppStatusLogger.info("[推送app审批结果] [appSerialNumber=" + appSerialNumber
							+ "] 调用APP接口返回信息 [code=" + baseResult.getCode()
							+ "] [message=" + baseResult.getMessage()+"]");

					if(StringUtils.isNotBlank(baseResult.getCode())){
						//通知App返回成功，更新借款信息表的是否推送字段
						if(CODE.equals(baseResult.getCode())){
							//借款申请单号
							int count = tdLoanInterfaceService.updateIsPushApp(appSerialNumber);
							//如果审核状态为2并且更新完成推送状态后发送短信
							if (DictConstant.AUDITING_STATE_TWO.equals(isPass) && count > 0) {
                                //发送手机短信
								sendMessage(application.getPhoneNum(),application.getAppName());
								for(FraudBizEnum fraudBizEnum: FraudBizEnum.values()) {
									String key10 = RedisKeyConstants.buildFraudScoresKey(application.getApplicationId(), fraudBizEnum);
									String key18 = RedisKeyConstants.buildFraudScoresKey1_8(application.getApplicationId(), fraudBizEnum);
									String key20 = RedisKeyConstants.buildFraudScoresKey2_0(application.getApplicationId(), fraudBizEnum);
									RedisUtils.del(key10);
									RedisUtils.del(key18);
									RedisUtils.del(key20);
								}
							}
							pushAppStatusLogger.info("[推送app审批结果] [appSerialNumber=" + appSerialNumber + "] 更新审批单推送标志成功：" + count);
						}else{
							pushAppStatusLogger.error("[推送app审批结果] [appSerialNumber=" + appSerialNumber
									+ "] [baseResult=" + JSON.toJSONString(baseResult)
									+ "] APP返回失败 ");
						}
					}
				} catch (Exception e) {
					iScAlarm.sendDingdingMsg("[推送app审批结果] 推送失败 [customerId=" + application.getCustomerId()
							+ "] [=" + application.getAppSerialNumber()
							+ "] 错误信息：" + e.getMessage(), "2");

					pushAppStatusLogger.error("[推送app审批结果] 推送失败 [baseResult=" + JSON.toJSONString(baseResult)
							+ "] [param=" + JSON.toJSONString(application) + "]",
							e);
				}
			}
		}else{
			pushAppStatusLogger.info("[推送app审批结果] 获取需推送APP待处理数据0条，当前定时任务处理结束");
		}
	}

	/**
	 * 用户审核通过后发送通知短信
	 * @param phoneNum
	 */
	private void sendMessage(String phoneNum,String appName){
		try {
			RabbitSms smsReqInfo = new RabbitSms();
			smsReqInfo.setPhoneNum(phoneNum);
			smsReqInfo.setBusinessType(BUSINESSTYPE);
			smsReqInfo.setVerCodel(VER);
			smsReqInfo.setAppName(appName);
			rabbitMqServer.sendSms(smsReqInfo);
		} catch (Exception e) {
			pushAppStatusLogger.info("[推送app审批结果] 用户审核通过，发送通知失败:"+e.getMessage(), e);
		}
	}

    /**
     * 这是个静态方法，在executor初始化时会调用，并生成供saturn使用的实现类对象
     */
    public static Object getObject() {
    	PushAppStatusRemindJob instance = (PushAppStatusRemindJob) SpringContextUtils.getBean(PushAppStatusRemindJob.class);
        return instance;
    }

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		scheduler();
	}
}
