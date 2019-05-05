package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.PageHelperBo;

/**
 * 数据测试明细表bean
 * @author xk
 * @date Sat Sep 23 18:07:05 CST 2017
 **/
public class CuCustomerCheckApply extends PageHelperBo{

	/**数据测试单号**/
	private String customerCheckApplyId;

	/**批次号**/
	private String customerCheckCodeId;

	/**客户姓名**/
	private String name;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**命中规则编号**/
	private String banCode;

	/**命中规则描述**/
	private String banDescription;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**备注**/
	private String remark;


	public CuCustomerCheckApply() {
		super();
	}
	public CuCustomerCheckApply(String customerCheckApplyId,String customerCheckCodeId,String name,String certCode,String phoneNum,String banCode,String banDescription,String createTime,String modifyTime,String remark) {
		super();
		this.customerCheckApplyId = customerCheckApplyId;
		this.customerCheckCodeId = customerCheckCodeId;
		this.name = name;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.banCode = banCode;
		this.banDescription = banDescription;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
	}
	public void setCustomerCheckApplyId(String customerCheckApplyId){
		this.customerCheckApplyId = customerCheckApplyId;
	}

	public String getCustomerCheckApplyId(){
		return this.customerCheckApplyId;
	}

	public void setCustomerCheckCodeId(String customerCheckCodeId){
		this.customerCheckCodeId = customerCheckCodeId;
	}

	public String getCustomerCheckCodeId(){
		return this.customerCheckCodeId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertCode(String certCode){
		this.certCode = certCode;
	}

	public String getCertCode(){
		return this.certCode;
	}

	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}

	public String getPhoneNum(){
		return this.phoneNum;
	}

	public void setBanCode(String banCode){
		this.banCode = banCode;
	}

	public String getBanCode(){
		return this.banCode;
	}

	public void setBanDescription(String banDescription){
		this.banDescription = banDescription;
	}

	public String getBanDescription(){
		return this.banDescription;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
