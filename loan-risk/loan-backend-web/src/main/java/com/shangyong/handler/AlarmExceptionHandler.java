package com.shangyong.handler;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.exception.AlarmException;
import com.shangyong.backend.entity.ScAlarm;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一处理需要报警的异常
 * @author
 */
@ControllerAdvice
public class AlarmExceptionHandler {

    @Autowired
    private IScAlarm scAlarm;

    @ExceptionHandler(AlarmException.class)
    @ResponseBody
    public void handler(HttpServletResponse response, AlarmException exception) {
        ScAlarm alarmBo = scAlarm.contains(exception.getAlarmCode(), exception.getMessage(), exception.getAlarmThirdPartyCreditInvestigationEnum().getCode());
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.CODE, alarmBo.getAlarmCode());
        map.put(Constants.MESSAGE, alarmBo.getAlarmMsg());
        SpringUtils.renderJson(response, map);
    }
}
