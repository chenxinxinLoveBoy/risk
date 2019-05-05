package com.shangyong.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmPriorityEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.enums.alarm.AlarmTypeEnum;
import com.shangyong.backend.entity.ScAlarm;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/backend/alarm")
public class AlarmController {
    private static Logger logger = Logger.getLogger(AlarmController.class);

    @Autowired
    private IScAlarm scAlarmServiceImpl;

    @PostMapping("/save.do")
    public void save(HttpServletRequest request, HttpServletResponse response, ScAlarm scAlarm) {
        try {
            if (scAlarm != null && scAlarm.getAlarmId() != null) {// 修改
                scAlarm.setModifyTime(new Date());
                boolean flag = scAlarmServiceImpl.updateScAlarmBo(scAlarm);
                if (flag) {
                    JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
                    return;
                } else {
                    JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
                    return;
                }
            } else {// 新增
                scAlarm.setCreateTime(new Date());
                boolean flag = scAlarmServiceImpl.saveScAlarmBo(scAlarm);
                if (flag) {
                    JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
                    return;
                } else {
                    JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
                    return;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @GetMapping("/get/{aralmId}")
    public void get(@PathVariable("aralmId") Integer aralmId, HttpServletResponse response) {
        try {
            ScAlarm scAlarm = scAlarmServiceImpl.getScAlarmBo(aralmId);
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
            map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
            map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
            map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
            map1.put("alarmObject", scAlarm);
            map.put(Constants.DATA, map1);
            SpringUtils.renderJson(response, map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @PostMapping("/page.do")
    public void pageFind(HttpServletResponse response, ScAlarm scAlarm) {
        try {
            int count = scAlarmServiceImpl.count(scAlarm);// 统计
            List<ScAlarm> scAlarms = scAlarmServiceImpl.listScAlarmBo(scAlarm);
            JSONUtils.toListJSON(response, scAlarms, count);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @GetMapping("/delete/{aralmId}")
    public void delete(@PathVariable("aralmId") Integer aralmId, HttpServletResponse response) {
        try {
            boolean flag = scAlarmServiceImpl.deleteScAlarmBo(aralmId);
            if (flag) {
                JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
                return;
            } else {
                JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
                return;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    @GetMapping("/getAlarmEnums")
    public void getAlarmEnums(HttpServletResponse response) {
        try {
            Map<String, List<JSONObject>> map = new HashMap();
            map.put("credits", AlarmThirdPartyCreditInvestigationEnum.returnCredits());
            map.put("types", AlarmTypeEnum.returnTypes());
            map.put("prioritys", AlarmPriorityEnum.returnPrioritys());
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }
}
