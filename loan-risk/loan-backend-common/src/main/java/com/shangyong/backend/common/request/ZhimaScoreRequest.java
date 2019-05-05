package com.shangyong.backend.common.request;

/**
 * 参数对象（此对象的参数为非必选项）
 * @author xiajiyun
 *
 */
public class ZhimaScoreRequest extends ZhimaRequest{
	
	private static final long serialVersionUID = 5027064049941794932L;

	// 这里的参数非必选

	/**
	 * （非必选参数）手机号，手机号码。中国大陆合法手机号，长度11位，不含国家代码
	 */
	private String mobile;
	
	
	/**
	 * （非必选参数）电子邮箱。合法email，字母小写，特殊符号以半角形式出现
	 */
	private String email;
	
	
	/**
	 * （非必选参数）   银行卡号。中国大陆银行发布的银行卡:借记卡长度19位；信用卡长度16位；各位的取值位[0,9]的整数；不含虚拟卡
	 */
	private String bankCard;
	
	
	/**
	 * （非必选参数）地址信息。省+市+区/县+详细地址，其中省+市+区/县可以为空，长度不超过256，不要包含特殊字符，如","，"\"，"|"，"&"，"^"
	 */
	private String address;
	
	
	/**
	 * （非必选参数） ip地址。以"."分割的四段Ip，如 x.x.x.x，x为[0,255]之间的整数
	 */
	private String ip;
	
	
	/**
	 * （非必选参数）物理地址。支持格式如下：xx:xx:xx:xx:xx:xx，xx-xx-xx-xx-xx-xx，xxxxxxxxxxxx，x取值范围[0,9]之间的整数及A，B，C，D，E，F
	 */
	private String mac;
	
	
	/**
	 * （非必选参数） wifi的物理地址。支持格式如下：xx:xx:xx:xx:xx:xx，xx-xx-xx-xx-xx-xx，xxxxxxxxxxxx，x取值范围[0,9]之间的整数及A，B，C，D，E，F
	 */
	private String wifimac;
	
	
	/**
	 * （非必选参数）国际移动设备标志。15位长度数字 
	 */
	private String imei;
	
	/**
	 * 商户请求的唯一标志，长度64位以内字符串，仅限字母数字下划线组合。
	 * 该标识作为业务调用的唯一标识，商户要保证其业务唯一性，使用相同transaction_id的查询，芝麻在一段时间内（一般为1天）返回首次查询结果，超过有效期的查询即为无效并返回异常，有效期内的重复查询不重新计费 
	 */
	private String transactionId;
	
	/**
	 * MQ用的参数
	 * cu_customer_check_apply表的主键id
	 */
	private String customerCheckApplyId;
	
	/**
	 * MQ用的参数
	 * cu_customer_check_apply表批次号   
	 */
	private String customerCheckCodeId;
	
	

	public String getCustomerCheckApplyId() {
		return customerCheckApplyId;
	}


	public void setCustomerCheckApplyId(String customerCheckApplyId) {
		this.customerCheckApplyId = customerCheckApplyId;
	}


	public String getCustomerCheckCodeId() {
		return customerCheckCodeId;
	}


	public void setCustomerCheckCodeId(String customerCheckCodeId) {
		this.customerCheckCodeId = customerCheckCodeId;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBankCard() {
		return bankCard;
	}


	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getMac() {
		return mac;
	}


	public void setMac(String mac) {
		this.mac = mac;
	}


	public String getWifimac() {
		return wifimac;
	}


	public void setWifimac(String wifimac) {
		this.wifimac = wifimac;
	}


	public String getImei() {
		return imei;
	}


	public void setImei(String imei) {
		this.imei = imei;
	} 
	
	
 
}
