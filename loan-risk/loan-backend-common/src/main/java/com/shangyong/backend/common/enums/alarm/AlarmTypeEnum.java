package com.shangyong.backend.common.enums.alarm;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 信息报警配置(报警方式)
 */
public enum AlarmTypeEnum {
    DD(0, "钉钉"),
    MSG(1, "短信"),
    EMAIL(2, "邮件"),
    DD_AND_MSG(3, "钉钉和短信"),
    NO(99, "无"),
    DEFUAL_DINGDING(111, "默认钉钉报警");

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

    AlarmTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (AlarmTypeEnum type : AlarmTypeEnum.values()) {
            if (type.code == code) {
                return type.name;
            }
        }
        return "";
    }

    public static List<JSONObject> returnTypes() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (AlarmTypeEnum type : AlarmTypeEnum.values()) {
            if (type.getCode() != AlarmTypeEnum.DEFUAL_DINGDING.code) {
                JSONObject object = new JSONObject();
                object.put("code", type.getCode());
                object.put("name", type.getName());
                jsonObjects.add(object);
            }
        }
        return jsonObjects;
    }
}
