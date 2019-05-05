package com.shangyong.backend.common.enums.alarm;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息报警配置(优先级)
 */
public enum AlarmPriorityEnum {
    VERY_SERIOUS(0, "非常严重"), SERIOUS(1, "严重"), ORDINARY(2, "普通");

    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    AlarmPriorityEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (AlarmPriorityEnum priority : AlarmPriorityEnum.values()) {
            if (priority.code == code) {
                return priority.name;
            }
        }
        return "";
    }

    public static List<JSONObject> returnPrioritys() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (AlarmPriorityEnum priority : AlarmPriorityEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", priority.getCode());
            object.put("name", priority.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }
}
