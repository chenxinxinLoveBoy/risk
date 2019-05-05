package com.shangyong.backend.entity;


/**
 * 客户信息拓展表bean
 * @author xuke
 * @date Thu Aug 24 16:59:30 CST 2017
 **/
public class CuCustomerExpand {

	/**客户拓展信息编号**/
	private String cusInfoId;

	/**APP应用客户编号**/
	private String customerId;

	/**APP名称：1-闪贷；2-速贷；3-贷款管家**/
	private Integer appName;

	/**客户最新登录时间**/
	private String latestLoginTime;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public CuCustomerExpand() {
		super();
	}
	public CuCustomerExpand(String cusInfoId,String customerId,Integer appName,String latestLoginTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.cusInfoId = cusInfoId;
		this.customerId = customerId;
		this.appName = appName;
		this.latestLoginTime = latestLoginTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCusInfoId(String cusInfoId){
		this.cusInfoId = cusInfoId;
	}

	public String getCusInfoId(){
		return this.cusInfoId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setLatestLoginTime(String latestLoginTime){
		this.latestLoginTime = latestLoginTime;
	}

	public String getLatestLoginTime(){
		return this.latestLoginTime;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
