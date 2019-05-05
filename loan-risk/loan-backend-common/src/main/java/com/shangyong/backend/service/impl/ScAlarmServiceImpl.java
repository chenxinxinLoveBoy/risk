package com.shangyong.backend.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmPriorityEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.enums.alarm.AlarmTypeEnum;
import com.shangyong.backend.dao.ScAlarmDao;
import com.shangyong.backend.entity.AlarmCallBackInfo;
import com.shangyong.backend.entity.ScAlarm;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;

import net.sf.json.JSONObject;

@Service
public class ScAlarmServiceImpl implements IScAlarm {

    private static Logger logger = LoggerFactory.getLogger(ScAlarmServiceImpl.class);

    @Autowired
    private ScAlarmDao scAlarmDao;
    @Autowired
    private SysParamRedisService sysParamRedisService;
    @Override
    public boolean saveScAlarmBo(ScAlarm alarmBo) {
        return scAlarmDao.insert(alarmBo);
    }

    @Override
    public boolean updateScAlarmBo(ScAlarm alarmBo) {
        return scAlarmDao.updateByPrimaryKeySelective(alarmBo);
    }

    @Override
    public boolean deleteScAlarmBo(Integer alarmId) {
        return scAlarmDao.deleteByPrimaryKey(alarmId);
    }

    @Override
    public ScAlarm getScAlarmBo(Integer alarmId) {
        return scAlarmDao.selectOne(new ScAlarm().setAlarmId(alarmId));
    }

    @Override
    public void contains(AlarmCodeEnum alarmCode, String alarmMsg, AlarmThirdPartyCreditInvestigationEnum thirdPartyCreditInvestigation) {
        if (StringUtils.isEmpty(alarmMsg)) {
            logger.error("alarmMsg：消息信息为空!!!");
            return;
        }

        ScAlarm alarm = new ScAlarm();
        alarm.setAlarmCode(alarmCode.getCode());
        alarm.setAlarmMsg(alarmMsg);
        alarm.setThirdPartyCreditInvestigation(thirdPartyCreditInvestigation.getCode());
        alarm(alarm);
    }

    @Deprecated
    @Override
    public ScAlarm contains(String alarmCode, String alarmMsg, int thirdPartyCreditInvestigation) {
        if (StringUtils.isEmpty(alarmMsg)) {
            logger.error("消息信息为空!!!");
            return null;
        }

        ScAlarm alarm = new ScAlarm();
        alarm.setAlarmCode(alarmCode);
        alarm.setAlarmMsg(alarmMsg);
        alarm.setThirdPartyCreditInvestigation(thirdPartyCreditInvestigation);
        alarm(alarm);
        return alarm;
    }

    @Deprecated
    @Override
    public void contains(String alarmString) {
        if (StringUtils.isEmpty(alarmString)) {
            logger.error("消息信息为空!!!");
            return;
        }
        alarm(new ScAlarm().setAlarmMsg(alarmString));
    }

    @Override
    public List<ScAlarm> listScAlarmBo(ScAlarm alarmBo) {
        return scAlarmDao.selectList(alarmBo);
    }

    @Override
    public int count(ScAlarm alarmBo) {
        return scAlarmDao.count(alarmBo);
    }

    private void alarm(ScAlarm alarm) {
        ScAlarm scAlarm = null;

        scAlarm = scAlarmDao.selectOne(new ScAlarm().setAlarmMsg(alarm.getAlarmMsg()));

        if (scAlarm == null) {

            if (StringUtils.isNotEmpty(alarm.getAlarmCode()) && alarm.getThirdPartyCreditInvestigation() != null) {
                scAlarm = scAlarmDao.selectOne(new ScAlarm().setAlarmCode(alarm.getAlarmCode()).setThirdPartyCreditInvestigation(alarm.getThirdPartyCreditInvestigation()));

                if (null != scAlarm){
                    scAlarm.setAlarmMsg(alarm.getAlarmMsg());
                }
            }
        }

        /**
         * 第三方征信平台专用
         */
//        if (StringUtils.isNotEmpty(alarm.getAlarmCode()) && StringUtils.isNotEmpty(alarm.getAlarmMsg())) {
//            scAlarm = scAlarmDao.selectOne(new ScAlarm().setAlarmCode(alarm.getAlarmCode()).setThirdPartyCreditInvestigation(alarm.getThirdPartyCreditInvestigation()));
//
//            if (null != scAlarm)
//                scAlarm.setAlarmMsg(alarm.getAlarmMsg());
//
//            /**
//             * 根据错误信息查询报警配置并进行报警
//             */
//        } else if (StringUtils.isNotEmpty(alarm.getAlarmMsg()) && StringUtils.isEmpty(alarm.getAlarmCode())) {
//            scAlarm = scAlarmDao.selectOne(new ScAlarm().setAlarmMsg(alarm.getAlarmMsg()));
//        }

        AlarmCallBackInfo info = null;

        if (null == scAlarm) {
            info = new AlarmCallBackInfo();
            alarm.setAlarmCode(AlarmCodeEnum.CUSTOM.getCode());
            alarm.setAlarmType(AlarmTypeEnum.DEFUAL_DINGDING.getCode());
            alarm.setPriority(AlarmPriorityEnum.VERY_SERIOUS.getCode());
            alarm.setThirdPartyCreditInvestigation(AlarmThirdPartyCreditInvestigationEnum.CUSTOM.getCode());
            alarm.setAlarmParam(Constants.BACKEND_DEFAULT_DINGDING_ALARM);
            info.setMsgId(UUIDUtils.getUUID());
            info.setScAlarm(alarm);
            info.setSendTime(DateUtils.getDate(new Date()));
        }

        if (null != scAlarm && scAlarm.getAlarmType() != AlarmTypeEnum.NO.getCode()) {
            info = new AlarmCallBackInfo();
            info.setMsgId(UUIDUtils.getUUID());
            info.setScAlarm(scAlarm);
            info.setSendTime(DateUtils.getDate(new Date()));
        }
        if (null != info) {
            try {
                InetAddress addr = InetAddress.getLocalHost();//获得本机IP  ;
                String ip = addr.getHostAddress();
                info.setIp(ip);
            } catch (UnknownHostException e) {
                info.setIp("获取报警服务器ip失败：" + e.getMessage());
                logger.error(e.getMessage(), e);
            }
//            RabbitUtil.send(JSON.toJSONString(info), MQConstants.MQ_EXCHANGE_ERROR_DING_BACKEND, MQConstants.MQ_ROUTING_ERROR_DING_BACKEND);
        }
    }

	@Override
	public void sendDingdingMsg(String msgContent, String msgType) {
		try {
			SysParam dingSendMsgSetting = sysParamRedisService.querySysParamByParamValueRedis(Constants.DINGDING_SEND_MSG);
			//获取钉钉发送信息token请求地址
			String tokenUr=dingSendMsgSetting.getParamValueOne();
			String tokenResult=RiskHttpClientUtil.doGet(tokenUr);
			JSONObject getTokenObj=JSONObject.fromObject(tokenResult);
			String sendMsgUrl=dingSendMsgSetting.getParamValueTwo();
			String chatid1=dingSendMsgSetting.getParamValueThree();
			String chatid2=dingSendMsgSetting.getParamValueFour();
			sendMsgUrl+=getTokenObj.getString("access_token");
			Map<String, Object> restMap=new HashMap<>();
			String chatid="";
			if("1".equals(msgType)){
				chatid=chatid1;
			}else{
				chatid=chatid2;
			}
			restMap.put("msgtype", "text");
			restMap.put("chatid", chatid);//群ID
			JSONObject contentObj=new JSONObject();
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String alarmTime="报警时间："+sf.format(new Date())+"详情：";
			
			contentObj.put("content", alarmTime+msgContent);
			restMap.put("text", contentObj);
			String jsonStr = JSON.toJSONString(restMap);
			RiskHttpClientUtil.doPostJson(sendMsgUrl, jsonStr);
		} catch (Exception e) {
			  logger.error("钉钉报警失败", e);
		}
	}
}
