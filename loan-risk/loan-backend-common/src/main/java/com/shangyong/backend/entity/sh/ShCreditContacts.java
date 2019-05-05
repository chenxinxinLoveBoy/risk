package com.shangyong.backend.entity.sh;


/**
 * 上海资信用户联系人信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditContacts {

	/**主键自增id**/
	private String shCreditContactsId;

	/**申请单编号**/
	private String applicationId;

	/**联系人姓名**/
	private String contactsName;

	/**联系人关系**/
	private String contactsRelation;

	/**联系电话**/
	private String contactsNumber;

	/**联系人获取时间**/
	private String contactsTime;

	/**联系人分类:1第一联系人信息,2第二联系人信息**/
	private String contactsType;

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


	public ShCreditContacts() {
		super();
	}
	public ShCreditContacts(String shCreditContactsId,String applicationId,String contactsName,String contactsRelation,String contactsNumber,String contactsTime,String contactsType,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditContactsId = shCreditContactsId;
		this.applicationId = applicationId;
		this.contactsName = contactsName;
		this.contactsRelation = contactsRelation;
		this.contactsNumber = contactsNumber;
		this.contactsTime = contactsTime;
		this.contactsType = contactsType;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditContactsId(String shCreditContactsId){
		this.shCreditContactsId = shCreditContactsId;
	}

	public String getShCreditContactsId(){
		return this.shCreditContactsId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setContactsName(String contactsName){
		this.contactsName = contactsName;
	}

	public String getContactsName(){
		return this.contactsName;
	}

	public void setContactsRelation(String contactsRelation){
		this.contactsRelation = contactsRelation;
	}

	public String getContactsRelation(){
		return this.contactsRelation;
	}

	public void setContactsNumber(String contactsNumber){
		this.contactsNumber = contactsNumber;
	}

	public String getContactsNumber(){
		return this.contactsNumber;
	}

	public void setContactsTime(String contactsTime){
		this.contactsTime = contactsTime;
	}

	public String getContactsTime(){
		return this.contactsTime;
	}

	public void setContactsType(String contactsType){
		this.contactsType = contactsType;
	}

	public String getContactsType(){
		return this.contactsType;
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
