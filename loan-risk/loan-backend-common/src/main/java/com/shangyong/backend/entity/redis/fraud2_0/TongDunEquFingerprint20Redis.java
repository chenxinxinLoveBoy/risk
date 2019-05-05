package com.shangyong.backend.entity.redis.fraud2_0;

import java.util.HashMap;

/**
 * 同盾设备指纹 2.0
 */
public class TongDunEquFingerprint20Redis {

    private String phoneBrandCode;

    public String getPhoneBrandCode() {
        return phoneBrandCode;
    }

    public void setPhoneBrandCode(String phoneBrandCode) {
        this.phoneBrandCode = phoneBrandCode;
    }

    public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("phoneBrandCode", null == this.phoneBrandCode ? "unknow" : this.phoneBrandCode);
        return map;
    }
}