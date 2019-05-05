package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 逾期名单bean
 * @author xiajiyun
 * @date Tue Aug 22 18:13:48 CST 2017
 **/
public class OverdueList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8699495384795460770L;

	/**逾期名单序号**/
	private String overdueListId;

	/**申请单编号**/
	private String applicationId;

	/**会话号**/
	private String guid;

	/**查询结果 1.查询成功 2.查询无结果 -1.异常情况**/
	private Integer result;

	/**状态描述信息、结果说明**/
	private String message;

	/**客户姓名**/
	private String name;

	/**手机号**/
	private String mobile;

	/**证件号码**/
	private String idCard;

	/**查询平台 1.银行 2.网贷**/
	private String platformType;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**是否命中： 0 - 不是  1 - 是**/
	private Integer state;

	/**备注**/
	private String remark;


	public OverdueList() {
		super();
	}
	public OverdueList(String overdueListId,String applicationId,String guid,Integer result,String message,String name,String mobile,String idCard,String platformType,String createTime,String modifyTime,Integer state,String remark) {
		super();
		this.overdueListId = overdueListId;
		this.applicationId = applicationId;
		this.guid = guid;
		this.result = result;
		this.message = message;
		this.name = name;
		this.mobile = mobile;
		this.idCard = idCard;
		this.platformType = platformType;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.state = state;
		this.remark = remark;
	}
	public void setOverdueListId(String overdueListId){
		this.overdueListId = overdueListId;
	}

	public String getOverdueListId(){
		return this.overdueListId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getGuid(){
		return this.guid;
	}

	public void setResult(Integer result){
		this.result = result;
	}

	public Integer getResult(){
		return this.result;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return this.message;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return this.mobile;
	}

	public void setIdCard(String idCard){
		this.idCard = idCard;
	}

	public String getIdCard(){
		return this.idCard;
	}

	public void setPlatformType(String platformType){
		this.platformType = platformType;
	}

	public String getPlatformType(){
		return this.platformType;
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

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}