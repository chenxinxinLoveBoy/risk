package com.shangyong.backend.service.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.bo.MailSenderInfo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmTypeEnum;
import com.shangyong.backend.entity.AlarmCallBackInfo;
import com.shangyong.backend.entity.ScAlarm;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IAlarmCallBackService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.backend.utils.SimpleMailSender;
import com.shangyong.utils.ManDaoSmsUtils;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;

/**
 * 报警回调
 * 钉钉默认报警参数 BACKEND_DEFAULT_DINGDING_ALARM
 * 邮件参数 BACKEND_EMAIL_ALARM
 */
@Service
public class AlarmCallBackServiceImpl implements IAlarmCallBackService {

    private static Logger logger = LoggerFactory.getLogger("alarmLog");

    @Autowired
    private SysParamRedisService sysParamRedisService;// 参数配置实现类

    /**
     * 接收rabbitmq回调的数据
     *
     * @param json
     * @return true 消息消费成功,false 消息消费失败
     */
    @Override
    public boolean callBack(String json) {
        if (StringUtils.isEmpty(json)) {
            logger.error("rabbitmq回调报警数据为空");
            return false;
        }

        ScAlarm scAlarm = null;
        String ip = "";
        try {
            AlarmCallBackInfo info = JSON.parseObject(json, AlarmCallBackInfo.class);
            if (null != info) {
                scAlarm = info.getScAlarm();
                ip = info.getIp();
            }
        } catch (Exception e) {
            logger.error("rabbitmq回调报警数据，json转换异常", e);
            return false;
        }

        if (null == scAlarm) {
            logger.error("rabbitmq回调报警数据，报警信息为空");
            return false;
        }

        StringBuffer sb = new StringBuffer();
        sb.append("系统时间：");
        sb.append(DateUtils.parseToDateTimeStr(new Date()));
        sb.append("，报警服务器ip：");
        sb.append(ip);
        sb.append("，报警信息：");
        sb.append(scAlarm.getAlarmMsg());

        String message = sb.toString();
        Integer alarmType = scAlarm.getAlarmType();

        if (alarmType == AlarmTypeEnum.DD.getCode()) {
            dingDingAlarm(scAlarm, message);
        } else if (alarmType == AlarmTypeEnum.DEFUAL_DINGDING.getCode()) {
            defaultDingDingAlarm(scAlarm, message);
        } else if (alarmType == AlarmTypeEnum.MSG.getCode()) {
            msgAlarm(scAlarm, message);
        } else if (alarmType == AlarmTypeEnum.DD_AND_MSG.getCode()) {
            dingDingAlarm(scAlarm, message);
            msgAlarm(scAlarm, message);
        } else if (alarmType == AlarmTypeEnum.EMAIL.getCode()) {
            emailAlarm(scAlarm, message);
        }
        return true;
    }

    /**
     * 钉钉报警
     *
     * @param scAlarm
     * @param message
     * @return
     */
    private void dingDingAlarm(ScAlarm scAlarm, String message) {
        JSONObject dingdingMsg = new JSONObject();
        dingdingMsg.put("msgtype", "text");
        JSONObject msg = new JSONObject();
        msg.put("content", message);
        dingdingMsg.put("text", msg);
        try {
            String sendResult = RiskHttpClientUtil.doPostDingdingJson(scAlarm.getDingdingUrl(), dingdingMsg.toString());
            logger.info("钉钉报警结果:" + sendResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 默认钉钉报警
     *
     * @param scAlarm
     * @param message
     * @return
     */
    private void defaultDingDingAlarm(ScAlarm scAlarm, String message) {
        JSONObject dingdingMsg = new JSONObject();
        dingdingMsg.put("msgtype", "text");
        JSONObject msg = new JSONObject();
        msg.put("content", message);
        dingdingMsg.put("text", msg);
        try {
            SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(scAlarm.getAlarmParam());
            if (null == sysParam || StringUtils.isEmpty(sysParam.getParamValueOne())) {
                logger.error("默认钉钉报警参数未配置或为空");
                return;
            }
            String sendResult = RiskHttpClientUtil.doPostDingdingJson(sysParam.getParamValueOne(), dingdingMsg.toString());
            logger.info("默认钉钉报警结果:" + sendResult);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 短信报警
     *
     * @param scAlarm
     * @param message
     * @return
     */
    private void msgAlarm(ScAlarm scAlarm, String message) {
        try {
            message = URLEncoder.encode(message, "utf-8");
            for (String phone : StringUtils.split(scAlarm.getPhone(), "|||")) {
                String sendResult = ManDaoSmsUtils.getSms(phone, message);
                logger.info("短信报警结果:" + sendResult);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 邮件报警
     *
     * @param scAlarm
     * @param message
     * @return
     */
    private void emailAlarm(ScAlarm scAlarm, String message) {
        boolean result = false;
        try {
            SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.BACKEND_EMAIL_ALARM);
            if (null == sysParam
                    || StringUtils.isEmpty(sysParam.getParamValueOne())
                    || StringUtils.isEmpty(sysParam.getParamValueTwo())
                    || StringUtils.isEmpty(sysParam.getParamValueThree())
                    || StringUtils.isEmpty(sysParam.getParamValueFour())) {
                logger.error("邮件报警参数未配置或为空");
            }
            MailSenderInfo senderInfo = new MailSenderInfo();
            senderInfo.setSubject("错误报警");
            senderInfo.setContent(message);
            senderInfo.setToAddress(StringUtils.join(StringUtils.split(scAlarm.getEmail(), "|||"), ";"));
            senderInfo.setMailServerHost(sysParam.getParamValueOne());
            senderInfo.setFromAddress(sysParam.getParamValueTwo());
            senderInfo.setUserName(sysParam.getParamValueThree());
            senderInfo.setPassword(sysParam.getParamValueFour());//您的邮箱密码
            senderInfo.setValidate(true);

            /*
             * true 发送成功
             * false 发送失败
             */
            result = SimpleMailSender.sendTextMail(senderInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("email报警结果:" + (result ? "成功" : "失败"));
    }
}