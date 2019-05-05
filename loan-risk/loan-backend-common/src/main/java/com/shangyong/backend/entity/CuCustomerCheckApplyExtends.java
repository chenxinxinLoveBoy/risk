package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.PageHelperBo;

/**
 * 数据测试明细扩展表bean
 * @author kenzhao
 * @date Sun Sep 24 16:59:24 CST 2017
 **/
public class CuCustomerCheckApplyExtends extends PageHelperBo{

	/**数据测试扩展编号**/
	private Integer customerCheckApplyExtendsId;

	/**数据测试单号**/
	private String customerCheckApplyId;

	/**征信机构 （ 05001- 聚信立蜜罐  07001-91征信 08001-白骑士欺诈 09001-宜信  11001-葫芦索伦  12001-小视科技 银行  12002-小视科技 网贷 40004- 芝麻信用行业关注名单）**/
	private String taskType;

	/**处理状态（1——待处理，2——处理成功，3——处理失败）**/
	private Integer applyState;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	/**备注**/
	private String remark;

	/**
	 * 页面显示用，非数据库字段
	 */
	private String errorInfo;
	

	public CuCustomerCheckApplyExtends() {
		super();
	}
	public CuCustomerCheckApplyExtends(Integer customerCheckApplyExtendsId,String customerCheckApplyId,String taskType,Integer applyState,String createTime,String modifyTime,String remark) {
		super();
		this.customerCheckApplyExtendsId = customerCheckApplyExtendsId;
		this.customerCheckApplyId = customerCheckApplyId;
		this.taskType = taskType;
		this.applyState = applyState;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.remark = remark;
	}
	
	 
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public void setCustomerCheckApplyExtendsId(Integer customerCheckApplyExtendsId){
		this.customerCheckApplyExtendsId = customerCheckApplyExtendsId;
	}

	public Integer getCustomerCheckApplyExtendsId(){
		return this.customerCheckApplyExtendsId;
	}

	public void setCustomerCheckApplyId(String customerCheckApplyId){
		this.customerCheckApplyId = customerCheckApplyId;
	}

	public String getCustomerCheckApplyId(){
		return this.customerCheckApplyId;
	}

	public void setTaskType(String taskType){
		this.taskType = taskType;
	}

	public String getTaskType(){
		return this.taskType;
	}

	public void setApplyState(Integer applyState){
		this.applyState = applyState;
	}

	public Integer getApplyState(){
		return this.applyState;
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
	@Override
	public String toString() {
		return "CuCustomerCheckApplyExtends [customerCheckApplyExtendsId=" + customerCheckApplyExtendsId
				+ ", customerCheckApplyId=" + customerCheckApplyId + ", taskType=" + taskType + ", applyState="
				+ applyState + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", remark=" + remark + "]";
	}
	
}
