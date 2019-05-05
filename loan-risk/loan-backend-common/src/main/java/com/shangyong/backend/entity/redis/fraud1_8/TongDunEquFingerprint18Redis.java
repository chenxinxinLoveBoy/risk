package com.shangyong.backend.entity.redis.fraud1_8;

import java.util.HashMap;

/**
 * 同盾设备指纹 1.8
 */
public class TongDunEquFingerprint18Redis {

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