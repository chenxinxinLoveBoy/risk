package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 用户手机通讯录记录bean
 * @author hxf
 * @date Wed Aug 02 14:01:33 CST 2017
 **/
public class CustomerDirectories implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**记录序号**/
	private String directoriesId;

	/**序号**/
	private Long customerCollectMessageId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**APP客户编号**/
	private String customerId;

	/**联系人姓名**/
	private String contactName;

	/**联系人号码**/
	private String contactPhone;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**号码类型：1--手机，2--个人固话，3--其它**/
	private Integer ifMobile;

	/**固话区号**/
	private String districtNumber;

	/**联系人状态、1:正常 2:异类号码 3:异常号码**/
	private Integer directoriesState;

	/**扩展字段、记录联系人异常原因**/
	private String extend;

	public String getDirectoriesId() {
		return directoriesId;
	}
	
	public void setDirectoriesId(String directoriesId) {
		this.directoriesId = directoriesId;
	}

	public Long getCustomerCollectMessageId() {
		return customerCollectMessageId;
	}

	public void setCustomerCollectMessageId(Long customerCollectMessageId) {
		this.customerCollectMessageId = customerCollectMessageId;
	}

	public Integer getAppName() {
		return appName;
	}

	public void setAppName(Integer appName) {
		this.appName = appName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIfMobile() {
		return ifMobile;
	}

	public void setIfMobile(Integer ifMobile) {
		this.ifMobile = ifMobile;
	}

	public String getDistrictNumber() {
		return districtNumber;
	}

	public void setDistrictNumber(String districtNumber) {
		this.districtNumber = districtNumber;
	}

	public Integer getDirectoriesState() {
		return directoriesState;
	}

	public void setDirectoriesState(Integer directoriesState) {
		this.directoriesState = directoriesState;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
	
}
