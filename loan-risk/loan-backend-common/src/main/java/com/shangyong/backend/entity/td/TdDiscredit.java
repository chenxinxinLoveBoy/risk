package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdDiscredit {

	/****/
	private String tdDiscreditId;

	/****/
	private String tdRiskItemsId;

	/**规则描述**/
	private String description;

	/**信贷逾期次数**/
	private String discreditTimes;

	/**逾期入库时间**/
	private String overdueTime;

	/**逾期金额区间**/
	private String overdueAmountRange;

	/**逾期时间区间**/
	private String overdueDayRange;

	/**逾期笔数**/
	private String overdueCount;


	public TdDiscredit() {
		super();
	}
	public TdDiscredit(String tdDiscreditId,String tdRiskItemsId,String description,String discreditTimes,String overdueTime,String overdueAmountRange,String overdueDayRange,String overdueCount) {
		super();
		this.tdDiscreditId = tdDiscreditId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.description = description;
		this.discreditTimes = discreditTimes;
		this.overdueTime = overdueTime;
		this.overdueAmountRange = overdueAmountRange;
		this.overdueDayRange = overdueDayRange;
		this.overdueCount = overdueCount;
	}
	public void setTdDiscreditId(String tdDiscreditId){
		this.tdDiscreditId = tdDiscreditId;
	}

	public String getTdDiscreditId(){
		return this.tdDiscreditId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDiscreditTimes(String discreditTimes){
		this.discreditTimes = discreditTimes;
	}

	public String getDiscreditTimes(){
		return this.discreditTimes;
	}

	public void setOverdueTime(String overdueTime){
		this.overdueTime = overdueTime;
	}

	public String getOverdueTime(){
		return this.overdueTime;
	}

	public void setOverdueAmountRange(String overdueAmountRange){
		this.overdueAmountRange = overdueAmountRange;
	}

	public String getOverdueAmountRange(){
		return this.overdueAmountRange;
	}

	public void setOverdueDayRange(String overdueDayRange){
		this.overdueDayRange = overdueDayRange;
	}

	public String getOverdueDayRange(){
		return this.overdueDayRange;
	}

	public void setOverdueCount(String overdueCount){
		this.overdueCount = overdueCount;
	}

	public String getOverdueCount(){
		return this.overdueCount;
	}

}
