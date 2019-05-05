package com.shangyong.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 风控白名单客户表bean
 * @author xk
 * @date Mon Jun 19 14:46:07 CST 2017
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScRiskWhiteList extends BaseBo{

	/**风控白名单客户表序号**/
	private Integer whiteListId;

	/**APP名称：1-闪贷；2-速贷**/
	private String appName;

	/**客户姓名**/
	private String name;

	/**证件类型 ： 1.身份证 2.护照 3.其他**/
	private String certType;

	/**证件号码**/
	private String certCode;

	/**手机号**/
	private String phoneNum;

	/**状态（01-正常、02-失效）**/
	private String state;

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


	public ScRiskWhiteList() {
		super();
	}
	public ScRiskWhiteList(Integer whiteListId,String appName,String name,String certType,String certCode,String phoneNum,String state,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.whiteListId = whiteListId;
		this.appName = appName;
		this.name = name;
		this.certType = certType;
		this.certCode = certCode;
		this.phoneNum = phoneNum;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setWhiteListId(Integer whiteListId){
		this.whiteListId = whiteListId;
	}

	public Integer getWhiteListId(){
		return this.whiteListId;
	}

	public void setAppName(String appName){
		this.appName = appName;
	}

	public String getAppName(){
		return this.appName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setCertType(String certType){
		this.certType = certType;
	}

	public String getCertType(){
		return this.certType;
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

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
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
