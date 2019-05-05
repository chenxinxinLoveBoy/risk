/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqs.jsonbean;

/**
 * Auto-generated: 2017-12-10 18:25:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class BaiQiShiBean {

    private String resultCode;
    private String resultDesc;
    private Data data;
    public void setResultCode(String resultCode) {
         this.resultCode = resultCode;
     }
     public String getResultCode() {
         return resultCode;
     }

    public void setResultDesc(String resultDesc) {
         this.resultDesc = resultDesc;
     }
     public String getResultDesc() {
         return resultDesc;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }
	@Override
	public String toString() {
		return "BaiQiShiBean [resultCode=" + resultCode + ", resultDesc=" + resultDesc + ", data=" + data + "]";
	}

}