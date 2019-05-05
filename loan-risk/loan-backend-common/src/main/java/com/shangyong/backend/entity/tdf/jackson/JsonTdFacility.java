/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.tdf.jackson;

/**
 * 系统配置文件
 * 同盾Jackson转换类
 * */
public class JsonTdFacility {
	
	/** url地址**/
    private String url;
    /** 合作方标识**/
    private String partner_code;
    /** 安卓应用密钥**/
    private String android;
    /** IOS应用密钥**/
    private String ios;
    /** 安卓注册事件**/
    private String register_and;
    /** IOS注册事件**/
    private String register_ios;
    /** 可选支持API实时返回设备或解析信息**/
    private String resp_detail_type;
    public String getResp_detail_type() {
		return resp_detail_type;
	}
	public void setResp_detail_type(String resp_detail_type) {
		this.resp_detail_type = resp_detail_type;
	}
	public void setUrl(String url) {
         this.url = url;
     }
     public String getUrl() {
         return url;
     }

    public void setPartner_code(String partner_code) {
         this.partner_code = partner_code;
     }
     public String getPartner_code() {
         return partner_code;
     }

    public void setAndroid(String android) {
         this.android = android;
     }
     public String getAndroid() {
         return android;
     }

    public void setIos(String ios) {
         this.ios = ios;
     }
     public String getIos() {
         return ios;
     }

    public void setRegister_and(String register_and) {
         this.register_and = register_and;
     }
     public String getRegister_and() {
         return register_and;
     }

    public void setRegister_ios(String register_ios) {
         this.register_ios = register_ios;
     }
     public String getRegister_ios() {
         return register_ios;
     }

}