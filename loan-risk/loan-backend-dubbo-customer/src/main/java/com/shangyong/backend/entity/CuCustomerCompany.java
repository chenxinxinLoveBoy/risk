package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 平台客户所属公司信息bean
 * 
 * @author xk
 * @date Thu Jun 01 10:41:13 CST 2017
 **/
public class CuCustomerCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 客户公司编号 **/
	private String customerCompanyId;

	/** 平台用户账号 **/
	private String platformCustomerId;

	/** APP应用客户编号 **/
	private String customerId;

	/** 公司行业 **/
	private String companyIndustry;

	/** 公司名称 **/
	private String companyName;

	/** 公司电话 **/
	private String companyTel;

	/** 公司地址 **/
	private String companyAddress;

	/** 街道地址 **/
	private String streetAddress;

	/** 客户工作时长 **/
	private String workingHours;

	/** 经度 **/
	private String lng;

	/** 纬度 **/
	private String lat;

	/** 工作照片存储URL **/
	private String workPhoto;

	/** 省 **/
	private String province;

	/** 市 **/
	private String city;

	/** 区 **/
	private String area;

	/** 创建时间 **/
	private String createTime;

	/** 修改时间 **/
	private String modifyTime;

	/** 备注 **/
	private String remark;

	/** ADVANCED-高级资深人员、INTERMEDIATES-中级技术人员、BEGINNERS-初级、助理人员、PRACTICE-见习专员、SENIOR-高层管理人员、MIDDLE-中层管理人员、JUNIOR-基层管理人员、NORMAL-普通员工 **/
	private String professionId;

	public CuCustomerCompany() {
		super();
	}

	public CuCustomerCompany(String customerCompanyId, String platformCustomerId, String customerId,
			String companyIndustry, String companyName, String companyTel, String companyAddress, String streetAddress,
			String workingHours, String lng, String lat, String workPhoto, String province, String city, String area,
			String createTime, String modifyTime, String remark, String professionId) {
		super();
		this.customerCompanyId = customerCompanyId;
		this.platformCustomerId = platformCustomerId;
		this.customerId = customerId;
		this.companyIndustry = companyIndustry;
		this.companyName = companyName;
		this.companyTel = companyTel;
		this.companyAddress = companyAddress;
		this.streetAddress = streetAddress;
		this.workingHours = workingHours;
		this.lng = lng;
		this.lat = lat;
		this.workPhoto = workPhoto;
		this.province = province;
		this.city = city;
		this.area = area;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
		this.professionId = professionId;
	}

	public String getCustomerCompanyId() {
		return customerCompanyId;
	}

	public void setCustomerCompanyId(String customerCompanyId) {
		this.customerCompanyId = customerCompanyId;
	}

	public String getPlatformCustomerId() {
		return platformCustomerId;
	}

	public void setPlatformCustomerId(String platformCustomerId) {
		this.platformCustomerId = platformCustomerId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getWorkPhoto() {
		return workPhoto;
	}

	public void setWorkPhoto(String workPhoto) {
		this.workPhoto = workPhoto;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProfessionId() {
		return professionId;
	}

	public void setProfessionId(String professionId) {
		this.professionId = professionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
