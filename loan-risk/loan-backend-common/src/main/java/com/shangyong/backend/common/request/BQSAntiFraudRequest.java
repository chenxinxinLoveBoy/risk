package com.shangyong.backend.common.request;


/**
 * 白骑士，反欺诈参数类，这里为可选填参数
 * @author xiajiyun
 *
 */
public class BQSAntiFraudRequest extends BQSAntiFraudAbstractRequest{

	private static final long serialVersionUID = 1391885119788656377L;

	/**
	 * 当前设备指纹会话标识，用于事件中关联设备指纹明细信息如有用到设备相关规则时需上送
	 */
	private String tokenKey;
	
	/**
	 * 事件发生时间格式yyyy-MM-dd HH:mm:ss如未上送则使用系统当前时间
	 */
	private String occurTime;
	
	/**
	 * 用户在商户端芝麻信用授权ID芝麻信用类规则服务需上送
	 */
	private String zmOpenId;
	
	/**
	 * 应用平台类型，h5/web/ios/android
	 */
	private String platform;
	
	/**
	 * 用户账号
	 */
	private String account;
	
	
	/**
	 * 用户邮箱
	 */
	private String email;
	
	
	/**
	 * 用户住址
	 */
	private String address;
	
	/**
	 * 用户所在城市
	 */
	private String addressCity;
	
	/**
	 * 用户联系人姓名
	 */
	private String contactsName;
	
	/**
	 * 用户联系人手机号
	 */
	private String contactsMobile;
	
	/**
	 * 用户第二联系人姓名
	 */
	private String contactsNameSec;
	
	/**
	 * 用户第二联系人手机号
	 */
	private String contactsMobileSec;
	
	/**
	 * 用户工作单位名称
	 */
	private String organization;
	
	/**
	 * 用户工作单位地址
	 */
	private String organizationAddress; 
	
	/**
	 * 学历
	 */
	private String education;
	
	/**
	 * 毕业院校名称
	 */
	private String graduateSchool;
	
	/**
	 * 毕业院校城市
	 */
	private String graduateCity;
	
	/**
	 * 是否已婚
	 */
	private String marriage;
	
	/**
	 * 收货人
	 */
	private String deliverName;
	
	/**
	 * 收货人手机号
	 */
	private String deliverMobileNo;
	
	/**
	 * 收货人街道地址信息
	 */
	private String deliverAddressStreet;
	
	/**
	 * 收货人县或区信息
	 */
	private String deliverAddressCounty;
	
	/**
	 * 收货人城市信息
	 */
	private String deliverAddressCity;
	
	/**
	 * 收货人省份信息
	 */
	private String deliverAddressProvince;
	
	/**
	 * 收货人国家信息
	 */
	private String deliverAddressCountry;
	
	/**
	 * 订单号
	 */
	private String orderId;
	
	/**
	 * 卖家或收款人账号
	 */
	private String payeeAccount;
	
	/**
	 * 卖家或收款人姓名
	 */
	private String payeeName;
	
	/**
	 * 卖家或收款人邮箱
	 */
	private String payeeEmail;
	
	/**
	 * 卖家或收款人手机
	 */
	private String payeeMobile;
	
	/**
	 * 卖家或收款人座机
	 */
	private String payeePhone;
	
	/**
	 * 卖家或收款人身份证
	 */
	private String payeeIdNumber;
	
	/**
	 * 卖家或收款人银行卡号
	 */
	private String payeeCardNumber;
	
	/**
	 * 金额（通用）
	 */
	private String amount;
	
	/**
	 * 商品详情/清单
	 */
	private String items;
	
	/**
	 * 商品数量
	 */
	private Integer itemNum;
	
	/**
	 * 商品金额
	 */
	private String itemAmount;
	
	/**
	 * 支付方式
	 */
	private String payMethod;
	
	/**
	 * 支付金额
	 */
	private String payAmount;
	
	/**
	 * 支付卡号
	 */
	private String payAccount;
	
	/**
	 * 商户名称
	 */
	private String merchantName;
	
	/**
	 * 银行卡卡号
	 */
	private String bankCardNo;
	
	/**
	 * 银行卡持卡人姓名
	 */
	private String bankCardName;
	
	/**
	 * 银行卡预留手机号
	 */
	private String bankCardMobile;
	
	/**
	 * 信用卡卡号
	 */
	private String creditCardNo;
	
	/**
	 * 信用卡持卡人姓名
	 */
	private String creditCardName;
	
	/**
	 * 信用卡预留手机号
	 */
	private String creditCardMobile;

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}

	public String getZmOpenId() {
		return zmOpenId;
	}

	public void setZmOpenId(String zmOpenId) {
		this.zmOpenId = zmOpenId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
 

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsMobile() {
		return contactsMobile;
	}

	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}

	public String getContactsNameSec() {
		return contactsNameSec;
	}

	public void setContactsNameSec(String contactsNameSec) {
		this.contactsNameSec = contactsNameSec;
	}

	public String getContactsMobileSec() {
		return contactsMobileSec;
	}

	public void setContactsMobileSec(String contactsMobileSec) {
		this.contactsMobileSec = contactsMobileSec;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationAddress() {
		return organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getGraduateCity() {
		return graduateCity;
	}

	public void setGraduateCity(String graduateCity) {
		this.graduateCity = graduateCity;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverMobileNo() {
		return deliverMobileNo;
	}

	public void setDeliverMobileNo(String deliverMobileNo) {
		this.deliverMobileNo = deliverMobileNo;
	}

	public String getDeliverAddressStreet() {
		return deliverAddressStreet;
	}

	public void setDeliverAddressStreet(String deliverAddressStreet) {
		this.deliverAddressStreet = deliverAddressStreet;
	}

	public String getDeliverAddressCounty() {
		return deliverAddressCounty;
	}

	public void setDeliverAddressCounty(String deliverAddressCounty) {
		this.deliverAddressCounty = deliverAddressCounty;
	}

	public String getDeliverAddressCity() {
		return deliverAddressCity;
	}

	public void setDeliverAddressCity(String deliverAddressCity) {
		this.deliverAddressCity = deliverAddressCity;
	}

	public String getDeliverAddressProvince() {
		return deliverAddressProvince;
	}

	public void setDeliverAddressProvince(String deliverAddressProvince) {
		this.deliverAddressProvince = deliverAddressProvince;
	}

	public String getDeliverAddressCountry() {
		return deliverAddressCountry;
	}

	public void setDeliverAddressCountry(String deliverAddressCountry) {
		this.deliverAddressCountry = deliverAddressCountry;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeEmail() {
		return payeeEmail;
	}

	public void setPayeeEmail(String payeeEmail) {
		this.payeeEmail = payeeEmail;
	}

	public String getPayeeMobile() {
		return payeeMobile;
	}

	public void setPayeeMobile(String payeeMobile) {
		this.payeeMobile = payeeMobile;
	}

	public String getPayeePhone() {
		return payeePhone;
	}

	public void setPayeePhone(String payeePhone) {
		this.payeePhone = payeePhone;
	}

	public String getPayeeIdNumber() {
		return payeeIdNumber;
	}

	public void setPayeeIdNumber(String payeeIdNumber) {
		this.payeeIdNumber = payeeIdNumber;
	}

	public String getPayeeCardNumber() {
		return payeeCardNumber;
	}

	public void setPayeeCardNumber(String payeeCardNumber) {
		this.payeeCardNumber = payeeCardNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}


	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getItemAmount() {
		return itemAmount;
	}

	public void setItemAmount(String itemAmount) {
		this.itemAmount = itemAmount;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankCardMobile() {
		return bankCardMobile;
	}

	public void setBankCardMobile(String bankCardMobile) {
		this.bankCardMobile = bankCardMobile;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCreditCardName() {
		return creditCardName;
	}

	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}

	public String getCreditCardMobile() {
		return creditCardMobile;
	}

	public void setCreditCardMobile(String creditCardMobile) {
		this.creditCardMobile = creditCardMobile;
	}
	
	
}
