package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 催收号码拨打关联表bean
 * @author xiajiyun
 * @date Sun Aug 13 15:23:15 CST 2017
 **/
public class BuIcePerson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1709351073585592070L;

	/**催收关联编号**/
	private String contactnId;

	/**联系次数**/
	private String contactNum;

	/**手机号码**/
	private String contactMobile;

	/**机构名称**/
	private String orgName;

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


	public BuIcePerson() {
		super();
	}
	public BuIcePerson(String contactnId,String contactNum,String contactMobile,String orgName,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.contactnId = contactnId;
		this.contactNum = contactNum;
		this.contactMobile = contactMobile;
		this.orgName = orgName;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setContactnId(String contactnId){
		this.contactnId = contactnId;
	}

	public String getContactnId(){
		return this.contactnId;
	}

	public void setContactNum(String contactNum){
		this.contactNum = contactNum;
	}

	public String getContactNum(){
		return this.contactNum;
	}

	public void setContactMobile(String contactMobile){
		this.contactMobile = contactMobile;
	}

	public String getContactMobile(){
		return this.contactMobile;
	}

	public void setOrgName(String orgName){
		this.orgName = orgName;
	}

	public String getOrgName(){
		return this.orgName;
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
