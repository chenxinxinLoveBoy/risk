package com.shangyong.backend.entity.bqsrep.vo;


/**
 * 白骑士资信报告
 * @author cheng
 * @date 2018年1月9日
 */
public class BqsReportPage {
	
	/**用户凭证**/
	private String partnerId;
	/**身份证**/
	private String certNo;
	/**时间戳**/
	private String timeStamp;
	/**手机号**/
	private String mobile;
	/**token值**/
	private String token;
	/**名字**/
	private String name;
	
	private String url;
	
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BqsReportPage() {
		super();
	}
	public BqsReportPage(String partnerId, String certNo, String timeStamp, String mobile, String token, String name) {
		super();
		this.partnerId = partnerId;
		this.certNo = certNo;
		this.timeStamp = timeStamp;
		this.mobile = mobile;
		this.token = token;
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
}
