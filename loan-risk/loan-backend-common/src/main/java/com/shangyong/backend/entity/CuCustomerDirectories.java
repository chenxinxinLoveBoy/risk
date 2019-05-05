package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 用户手机通讯录记录bean
 * @author xiajiyun
 * @date Sat Aug 12 22:40:57 CST 2017
 **/
public class CuCustomerDirectories extends BaseBo {

	/**记录序号**/
	private String directoriesId;

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


	public CuCustomerDirectories() {
		super();
	}
	public CuCustomerDirectories(String directoriesId,Integer appName,String customerId,String contactName,String contactPhone,String createTime,String modifyTime,Integer ifMobile,String districtNumber,Integer directoriesState,String extend) {
		super();
		this.directoriesId = directoriesId;
		this.appName = appName;
		this.customerId = customerId;
		this.contactName = contactName;
		this.contactPhone = contactPhone;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.ifMobile = ifMobile;
		this.districtNumber = districtNumber;
		this.directoriesState = directoriesState;
		this.extend = extend;
	}
	public void setDirectoriesId(String directoriesId){
		this.directoriesId = directoriesId;
	}

	public String getDirectoriesId(){
		return this.directoriesId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setContactName(String contactName){
		this.contactName = contactName;
	}

	public String getContactName(){
		return this.contactName;
	}

	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}

	public String getContactPhone(){
		return this.contactPhone;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setIfMobile(Integer ifMobile){
		this.ifMobile = ifMobile;
	}

	public Integer getIfMobile(){
		return this.ifMobile;
	}

	public void setDistrictNumber(String districtNumber){
		this.districtNumber = districtNumber;
	}

	public String getDistrictNumber(){
		return this.districtNumber;
	}

	public void setDirectoriesState(Integer directoriesState){
		this.directoriesState = directoriesState;
	}

	public Integer getDirectoriesState(){
		return this.directoriesState;
	}

	public void setExtend(String extend){
		this.extend = extend;
	}

	public String getExtend(){
		return this.extend;
	}
	@Override
	public String toString() {
		return "CuCustomerDirectories [directoriesId=" + directoriesId + ", appName=" + appName + ", customerId="
				+ customerId + ", contactName=" + contactName + ", contactPhone=" + contactPhone + ", createTime="
				+ createTime + ", modifyTime=" + modifyTime + ", ifMobile=" + ifMobile + ", districtNumber="
				+ districtNumber + ", directoriesState=" + directoriesState + ", extend=" + extend + "]";
	}
	
	

}
