package com.shangyong.backend.entity.sh;


/**
 * 上海资信查询信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditSearchInformation {

	/**主键自增id**/
	private String searchInformationId;

	/**申请单编号**/
	private String applicationId;

	/**查询者类型**/
	private String querierType;

	/**查询原因**/
	private String cause;

	/**查询日期**/
	private String queryDate;

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


	public ShCreditSearchInformation() {
		super();
	}
	public ShCreditSearchInformation(String searchInformationId,String applicationId,String querierType,String cause,String queryDate,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.searchInformationId = searchInformationId;
		this.applicationId = applicationId;
		this.querierType = querierType;
		this.cause = cause;
		this.queryDate = queryDate;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setSearchInformationId(String searchInformationId){
		this.searchInformationId = searchInformationId;
	}

	public String getSearchInformationId(){
		return this.searchInformationId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setQuerierType(String querierType){
		this.querierType = querierType;
	}

	public String getQuerierType(){
		return this.querierType;
	}

	public void setCause(String cause){
		this.cause = cause;
	}

	public String getCause(){
		return this.cause;
	}

	public void setQueryDate(String queryDate){
		this.queryDate = queryDate;
	}

	public String getQueryDate(){
		return this.queryDate;
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
