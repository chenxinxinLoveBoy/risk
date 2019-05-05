package com.shangyong.backend.common.enums.mq;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息管道类型
 */
public enum MessageChannelTypeEnum {
    SEND(0, "发送"), RECEIVE(1, "接收");

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

    MessageChannelTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (MessageChannelTypeEnum type : MessageChannelTypeEnum.values()) {
            if (type.code == code)
                return type.name;
        }
        return "";
    }

    public static List<JSONObject> returns() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (MessageChannelTypeEnum type : MessageChannelTypeEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", type.getCode());
            object.put("name", type.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }
}
