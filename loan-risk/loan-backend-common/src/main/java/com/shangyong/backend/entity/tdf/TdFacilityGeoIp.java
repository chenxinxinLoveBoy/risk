package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹地理位置信息表bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityGeoIp {

	/**主键id**/
	private String tdFacilityGeoIpId;

	/**申请单编号**/
	private String buApplicationId;

	/**ip地址所处城市**/
	private String city;

	/**ip地址所处城市的id**/
	private String cityId;

	/**IP地址所处国家**/
	private String country;

	/**IP地址所处国家id**/
	private String countryId;

	/**IP地址所处省份**/
	private String province;

	/**IP地址所处省份id**/
	private String provinceId;

	/**IP地址所处县**/
	private String county;

	/**	IP地址所处街道**/
	private String street;
	
	/** LBS详细地址*/
	private String lbsAddress;
	
	/** LBS省份*/
	private String lbsProvince;
	
	/** LBS城市*/
	private String lbsCity;

	/**互联网服务提供商**/
	private String isp;

	/**纬度**/
	private String latitude;

	/**经度**/
	private String longitude;

	/**	ip的整数表示形式**/
	private String lip;

	/**0代表真实地理位置,1代表客户输入位置**/
	private String type;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	public TdFacilityGeoIp() {
		super();
	}

	public TdFacilityGeoIp(String tdFacilityGeoIpId, String buApplicationId, String city, String cityId, String country,
			String countryId, String province, String provinceId, String county, String street, String lbsAddress,
			String lbsProvince, String lbsCity, String isp, String latitude, String longitude, String lip, String type,
			String createTime, String createMan, String modifyTime, String modifyMan, String remark) {
		super();
		this.tdFacilityGeoIpId = tdFacilityGeoIpId;
		this.buApplicationId = buApplicationId;
		this.city = city;
		this.cityId = cityId;
		this.country = country;
		this.countryId = countryId;
		this.province = province;
		this.provinceId = provinceId;
		this.county = county;
		this.street = street;
		this.lbsAddress = lbsAddress;
		this.lbsProvince = lbsProvince;
		this.lbsCity = lbsCity;
		this.isp = isp;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lip = lip;
		this.type = type;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}

	public String getTdFacilityGeoIpId() {
		return tdFacilityGeoIpId;
	}

	public void setTdFacilityGeoIpId(String tdFacilityGeoIpId) {
		this.tdFacilityGeoIpId = tdFacilityGeoIpId;
	}

	public String getBuApplicationId() {
		return buApplicationId;
	}

	public void setBuApplicationId(String buApplicationId) {
		this.buApplicationId = buApplicationId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLbsAddress() {
		return lbsAddress;
	}

	public void setLbsAddress(String lbsAddress) {
		this.lbsAddress = lbsAddress;
	}

	public String getLbsProvince() {
		return lbsProvince;
	}

	public void setLbsProvince(String lbsProvince) {
		this.lbsProvince = lbsProvince;
	}

	public String getLbsCity() {
		return lbsCity;
	}

	public void setLbsCity(String lbsCity) {
		this.lbsCity = lbsCity;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLip() {
		return lip;
	}

	public void setLip(String lip) {
		this.lip = lip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TdFacilityGeoIp [tdFacilityGeoIpId=" + tdFacilityGeoIpId + ", buApplicationId=" + buApplicationId
				+ ", city=" + city + ", cityId=" + cityId + ", country=" + country + ", countryId=" + countryId
				+ ", province=" + province + ", provinceId=" + provinceId + ", county=" + county + ", street=" + street
				+ ", lbsAddress=" + lbsAddress + ", lbsProvince=" + lbsProvince + ", lbsCity=" + lbsCity + ", isp="
				+ isp + ", latitude=" + latitude + ", longitude=" + longitude + ", lip=" + lip + ", type=" + type
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
