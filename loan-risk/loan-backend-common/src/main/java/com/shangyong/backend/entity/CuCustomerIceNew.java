package com.shangyong.backend.entity;


/**
 * 紧急联系人bean
 * @author kenzhao
 * @date Sat Jan 20 19:21:24 CST 2018
 **/
public class CuCustomerIceNew {

	/**紧急联系人编号**/
	private String icePersonId;

	/**客户编码**/
	private String customerId;

	/**紧急联系人类型编号(11-父亲；12-母亲；13-儿子；14-女儿；15-配偶；16-兄弟；17-姐妹；21-同学；22-亲戚；23-同事；24-朋友；25-其他；) **/
	private Integer iceCode;

	/**真实姓名**/
	private String iceName;

	/**手机号码**/
	private String icePhone;

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


	public CuCustomerIceNew() {
		super();
	}
	public CuCustomerIceNew(String icePersonId,String customerId,Integer iceCode,String iceName,String icePhone,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.icePersonId = icePersonId;
		this.customerId = customerId;
		this.iceCode = iceCode;
		this.iceName = iceName;
		this.icePhone = icePhone;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setIcePersonId(String icePersonId){
		this.icePersonId = icePersonId;
	}

	public String getIcePersonId(){
		return this.icePersonId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setIceCode(Integer iceCode){
		this.iceCode = iceCode;
	}

	public Integer getIceCode(){
		return this.iceCode;
	}

	public void setIceName(String iceName){
		this.iceName = iceName;
	}

	public String getIceName(){
		return this.iceName;
	}

	public void setIcePhone(String icePhone){
		this.icePhone = icePhone;
	}

	public String getIcePhone(){
		return this.icePhone;
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
