/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqsrep.jsonbean;
import java.util.Date;

/**
 * Auto-generated: 2017-12-14 18:42:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WebDataSources {

    private String source;
    private String sourceType;
    private String storeTime;
    private String sourceTime;
    private Boolean passRealName;
    private String realNameInfo;
    private Boolean equalToPetitioner;
    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    public void setSourceType(String sourceType) {
         this.sourceType = sourceType;
     }
     public String getSourceType() {
         return sourceType;
     }


    public void setSourceTime(String sourceTime) {
         this.sourceTime = sourceTime;
     }
     public String getSourceTime() {
         return sourceTime;
     }

   

    public String getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(String storeTime) {
		this.storeTime = storeTime;
	}
	public Boolean getPassRealName() {
		return passRealName;
	}
	public void setPassRealName(Boolean passRealName) {
		this.passRealName = passRealName;
	}
	public Boolean getEqualToPetitioner() {
		return equalToPetitioner;
	}
	public void setEqualToPetitioner(Boolean equalToPetitioner) {
		this.equalToPetitioner = equalToPetitioner;
	}
	public void setRealNameInfo(String realNameInfo) {
         this.realNameInfo = realNameInfo;
     }
     public String getRealNameInfo() {
         return realNameInfo;
     }

}