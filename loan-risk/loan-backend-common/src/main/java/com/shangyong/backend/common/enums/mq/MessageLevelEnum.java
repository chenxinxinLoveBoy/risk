package com.shangyong.backend.common.enums.mq;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息错误级
 */
public enum MessageLevelEnum {
    WARN(1, "警告"), BUSINESS_ERROR(2, "业务处理错误"), MESSAGE_MISSING(3, "消息丢失");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MessageLevelEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (MessageLevelEnum type : MessageLevelEnum.values()) {
            if (type.code == code)
                return type.name;
        }
        return "";
    }

    public static List<JSONObject> returns() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (MessageLevelEnum type : MessageLevelEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", type.getCode());
            object.put("name", type.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }
}
