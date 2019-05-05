package com.shangyong.backend.common.enums.mq;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息状态
 */
public enum MessageStatusEnum {

    NORMAL(0,"正常"),NOT_CONSUMED(1,"未消费"),CONSUMED_FAIL(2,"消费处理失败");

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

    MessageStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (MessageStatusEnum type : MessageStatusEnum.values()) {
            if (type.code == code)
                return type.name;
        }
        return "";
    }

    public static List<JSONObject> returns() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (MessageStatusEnum type : MessageStatusEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", type.getCode());
            object.put("name", type.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }
}
