package com.shangyong.backend.entity.sh;


/**
 * 上海资信特殊交易信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditSpecialDeal {

	/**主键自增id**/
	private String specialDealId;

	/**申请单编号**/
	private String applicationId;

	/**记录来源**/
	private String recordSource;

	/**记录类型**/
	private String recordType;

	/**发生日期**/
	private String occurrenceDate;

	/**变更月数**/
	private String changeMonths;

	/**发生金额**/
	private String actualAmount;

	/**明细信息**/
	private String detail;

	/**信息获取日期**/
	private String specialDealTime;

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


	public ShCreditSpecialDeal() {
		super();
	}
	public ShCreditSpecialDeal(String specialDealId,String applicationId,String recordSource,String recordType,String occurrenceDate,String changeMonths,String actualAmount,String detail,String specialDealTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.specialDealId = specialDealId;
		this.applicationId = applicationId;
		this.recordSource = recordSource;
		this.recordType = recordType;
		this.occurrenceDate = occurrenceDate;
		this.changeMonths = changeMonths;
		this.actualAmount = actualAmount;
		this.detail = detail;
		this.specialDealTime = specialDealTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setSpecialDealId(String specialDealId){
		this.specialDealId = specialDealId;
	}

	public String getSpecialDealId(){
		return this.specialDealId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setRecordSource(String recordSource){
		this.recordSource = recordSource;
	}

	public String getRecordSource(){
		return this.recordSource;
	}

	public void setrecordType(String recordType){
		this.recordType = recordType;
	}

	public String getrecordType(){
		return this.recordType;
	}

	public void setOccurrenceDate(String occurrenceDate){
		this.occurrenceDate = occurrenceDate;
	}

	public String getOccurrenceDate(){
		return this.occurrenceDate;
	}

	public void setChangeMonths(String changeMonths){
		this.changeMonths = changeMonths;
	}

	public String getChangeMonths(){
		return this.changeMonths;
	}

	public void setActualAmount(String actualAmount){
		this.actualAmount = actualAmount;
	}

	public String getActualAmount(){
		return this.actualAmount;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

	public void setSpecialDealTime(String specialDealTime){
		this.specialDealTime = specialDealTime;
	}

	public String getSpecialDealTime(){
		return this.specialDealTime;
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
