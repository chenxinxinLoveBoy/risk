package com.shangyong.shangyong.service.impl;


import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.service.RabbitMqServer;
import com.shangyong.shangyong.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 报警 业务
 *
 * @Author caisheng
 * @Date 2018年8月3日
 * @Description 申请单 失败次数 超过4次 报警
 *
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    private static Logger logger = LoggerFactory.getLogger("alarmJob");

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private RabbitMqServer rabbitMqServer;

    private static int FAILURE_TIMES_MAX = 10;

    @Override
    public void alarmFailApplication() {
        Long count = applicationDao.selectFailureTimeMoreThanFourTimes();

        if(null != count && count > FAILURE_TIMES_MAX){
            rabbitMqServer.sendMqForAlarmAppFailureTimes(count);
            logger.info("[申请单失败次数] 当前失败次数超过"+FAILURE_TIMES_MAX+"申请单为" + (null == count ? 0 : count.longValue()));
        }else{
            logger.info("[申请单失败次数] 当前失败次数超过"+FAILURE_TIMES_MAX+"申请单为" + (null == count ? 0 : count.longValue()) + " 不予发送报警消息");
        }
    }
}
