package com.shangyong.backend.common.enums.mq;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息消费类型
 */
public enum MessageIsConsumptionEnum {

    NOCONSUMPTION("1", "未消费"), CONSUMPTIONOK("2", "消费成功"), CONSUMPTIONFAIL("3", "消费失败");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MessageIsConsumptionEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (MessageIsConsumptionEnum type : MessageIsConsumptionEnum.values()) {
            if (type.code.equals(code))
                return type.name;
        }
        return "";
    }

    public static List<JSONObject> returns() {
        List<JSONObject> jsonObjects = new ArrayList();
        for (MessageIsConsumptionEnum type : MessageIsConsumptionEnum.values()) {
            JSONObject object = new JSONObject();
            object.put("code", type.getCode());
            object.put("name", type.getName());
            jsonObjects.add(object);
        }
        return jsonObjects;
    }
}
