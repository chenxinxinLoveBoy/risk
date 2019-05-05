/**
  * Copyright 2018 bejson.com 
  */
package com.shangyong.backend.entity.tdReport.jsonbean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Auto-generated: 2018-03-16 11:24:14
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@JsonAutoDetect
public class Info_match {
	@JsonProperty("real_name_check_yys")
    private String realNameCheckYys;
	@JsonProperty("email_check_yys")
    private String emailCheckYys;
	@JsonProperty("identity_code_check_yys")
    private String identityCodeCheckYys;
	@JsonProperty("home_addr_check_yys")
    private String homeAddrCheckYys;
	public String getRealNameCheckYys() {
		return realNameCheckYys;
	}
	public void setRealNameCheckYys(String realNameCheckYys) {
		this.realNameCheckYys = realNameCheckYys;
	}
	public String getEmailCheckYys() {
		return emailCheckYys;
	}
	public void setEmailCheckYys(String emailCheckYys) {
		this.emailCheckYys = emailCheckYys;
	}
	public String getIdentityCodeCheckYys() {
		return identityCodeCheckYys;
	}
	public void setIdentityCodeCheckYys(String identityCodeCheckYys) {
		this.identityCodeCheckYys = identityCodeCheckYys;
	}
	public String getHomeAddrCheckYys() {
		return homeAddrCheckYys;
	}
	public void setHomeAddrCheckYys(String homeAddrCheckYys) {
		this.homeAddrCheckYys = homeAddrCheckYys;
	}
  
}